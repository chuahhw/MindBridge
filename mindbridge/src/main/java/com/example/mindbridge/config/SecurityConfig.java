package com.example.mindbridge.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/register", "/login", "/css/**", "/js/**").permitAll()
                .requestMatchers("/counselor/**").hasRole("COUNSELOR")
                .requestMatchers("/student/**").hasRole("STUDENT")
                .requestMatchers("/forum/**").hasAnyRole("STUDENT", "COUNSELOR")
                .anyRequest().authenticated()
                
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(customAuthenticationSuccessHandler()) 
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            );

        return http.build();
    }

    /**
     * Role-based redirect after login.
     * Handles context path automatically.
     */
    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return (request, response, authentication) -> {
            String contextPath = request.getContextPath(); // e.g., "/mindbridge"

            boolean isCounselor = authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_COUNSELOR"));
            boolean isStudent = authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_STUDENT"));
            boolean isAdmin = authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

            if (isCounselor) {
                response.sendRedirect(contextPath + "/counselor/dashboard");
            } else if (isStudent) {
                response.sendRedirect(contextPath + "/student/dashboard");
            } else if (isAdmin) {
                response.sendRedirect(contextPath + "/admin/dashboard");
            } else {
                response.sendRedirect(contextPath + "/login?error=true");
            }
        };
    }
}
