package com.example.mindbridge.service;

import java.util.List;

public interface AdminDashboardService {

    // Get recent activity items for admin dashboard
    List<ActivityItem> getRecentActivities(int limit);

    // Simple activity item for dashboard
    static class ActivityItem {
        private final String message;
        private final String timeAgo;

        public ActivityItem(String message, String timeAgo) {
            this.message = message;
            this.timeAgo = timeAgo;
        }

        public String getMessage() { return message; }
        public String getTimeAgo() { return timeAgo; }
    }
}
