-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 10, 2026 at 08:00 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mindbridge`
--

-- --------------------------------------------------------

--
-- Table structure for table `answer_option`
--

CREATE TABLE `answer_option` (
  `id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `option_text` varchar(255) NOT NULL,
  `option_score` int(11) NOT NULL,
  `is_correct` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `answer_option`
--

INSERT INTO `answer_option` (`id`, `question_id`, `option_text`, `option_score`, `is_correct`) VALUES
(1, 1, 'Not at all', 0, b'0'),
(2, 1, 'Nearly every day', 3, b'0'),
(3, 1, 'Several days', 1, b'0'),
(4, 1, 'More than half the days', 2, b'0'),
(5, 2, 'Several days', 1, b'0'),
(6, 2, 'More than half the days', 2, b'0'),
(7, 2, 'Not at all', 0, b'0'),
(8, 2, 'Nearly every day', 3, b'0'),
(9, 3, 'More than half the days', 2, b'0'),
(10, 3, 'Not at all', 0, b'0'),
(11, 3, 'Nearly every day', 3, b'0'),
(12, 3, 'Several days', 1, b'0'),
(13, 4, 'Nearly every day', 3, b'0'),
(14, 4, 'Several days', 1, b'0'),
(15, 4, 'More than half the days', 2, b'0'),
(16, 4, 'Not at all', 0, b'0'),
(17, 5, 'Several days', 1, b'0'),
(18, 5, 'More than half the days', 2, b'0'),
(19, 5, 'Not at all', 0, b'0'),
(20, 5, 'Nearly every day', 3, b'0'),
(21, 6, 'More than half the days', 2, b'0'),
(22, 6, 'Not at all', 0, b'0'),
(23, 6, 'Nearly every day', 3, b'0'),
(24, 6, 'Several days', 1, b'0'),
(25, 7, 'Nearly every day', 3, b'0'),
(26, 7, 'Several days', 1, b'0'),
(27, 7, 'More than half the days', 2, b'0'),
(28, 7, 'Not at all', 0, b'0'),
(29, 8, 'More than half the days', 2, b'0'),
(30, 8, 'Not at all', 0, b'0'),
(31, 8, 'Nearly every day', 3, b'0'),
(32, 8, 'Several days', 1, b'0'),
(33, 9, 'Not at all', 0, b'0'),
(34, 9, 'Nearly every day', 3, b'0'),
(35, 9, 'Several days', 1, b'0'),
(36, 9, 'More than half the days', 2, b'0'),
(37, 10, 'Several days', 1, b'0'),
(38, 10, 'More than half the days', 2, b'0'),
(39, 10, 'Not at all', 0, b'0'),
(40, 10, 'Nearly every day', 3, b'0'),
(41, 11, 'More than half the days', 2, b'0'),
(42, 11, 'Not at all', 0, b'0'),
(43, 11, 'Nearly every day', 3, b'0'),
(44, 11, 'Several days', 1, b'0'),
(45, 12, 'Nearly every day', 3, b'0'),
(46, 12, 'Several days', 1, b'0'),
(47, 12, 'More than half the days', 2, b'0'),
(48, 12, 'Not at all', 0, b'0'),
(49, 13, 'Several days', 1, b'0'),
(50, 13, 'More than half the days', 2, b'0'),
(51, 13, 'Not at all', 0, b'0'),
(52, 13, 'Nearly every day', 3, b'0'),
(53, 14, 'More than half the days', 2, b'0'),
(54, 14, 'Not at all', 0, b'0'),
(55, 14, 'Nearly every day', 3, b'0'),
(56, 14, 'Several days', 1, b'0'),
(57, 15, 'Nearly every day', 3, b'0'),
(58, 15, 'Several days', 1, b'0'),
(59, 15, 'More than half the days', 2, b'0'),
(60, 15, 'Not at all', 0, b'0'),
(61, 16, 'More than half the days', 2, b'0'),
(62, 16, 'Not at all', 0, b'0'),
(63, 16, 'Nearly every day', 3, b'0'),
(64, 16, 'Several days', 1, b'0');

-- --------------------------------------------------------

--
-- Table structure for table `appointments`
--

CREATE TABLE `appointments` (
  `id` bigint(20) NOT NULL,
  `date` date NOT NULL,
  `notes` varchar(1000) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `time` time(6) NOT NULL,
  `type` varchar(255) NOT NULL,
  `counselor_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `decline_reason` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `appointments`
--

INSERT INTO `appointments` (`id`, `date`, `notes`, `status`, `time`, `type`, `counselor_id`, `student_id`, `decline_reason`) VALUES
(2, '2025-12-12', '', 'APPROVED', '09:00:00.000000', 'IN_PERSON', 1, 2, NULL),
(3, '2025-12-22', '', 'APPROVED', '11:00:00.000000', 'IN_PERSON', 1, 2, NULL),
(6, '2026-01-31', '', 'DECLINED', '15:00:00.000000', 'IN_PERSON', 5, 2, ''),
(7, '2026-01-22', '', 'DECLINED', '11:00:00.000000', 'IN_PERSON', 6, 2, ''),
(8, '2026-02-12', '', 'DECLINED', '14:00:00.000000', 'IN_PERSON', 1, 2, 'I am not free'),
(9, '2026-01-23', '', 'APPROVED', '09:00:00.000000', 'IN_PERSON', 4, 2, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `assessment_answer`
--

CREATE TABLE `assessment_answer` (
  `id` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  `answer_option_id` int(11) NOT NULL,
  `attempt_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `assessment_attempt`
--

CREATE TABLE `assessment_attempt` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `question_set_id` int(11) NOT NULL,
  `attempt_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `total_score` int(11) DEFAULT NULL,
  `severity_level` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `assessment_attempt`
--

INSERT INTO `assessment_attempt` (`id`, `user_id`, `question_set_id`, `attempt_date`, `total_score`, `severity_level`) VALUES
(11, 2, 1, '2025-12-18 07:19:41', 11, 'Moderate Depression'),
(12, 2, 2, '2025-12-18 11:44:24', 5, 'Mild Anxiety'),
(13, 2, 2, '2025-12-18 11:49:45', 21, 'Severe Anxiety');

-- --------------------------------------------------------

--
-- Table structure for table `forum_replies`
--

CREATE TABLE `forum_replies` (
  `id` bigint(20) NOT NULL,
  `content` text NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `thread_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `forum_replies`
--

INSERT INTO `forum_replies` (`id`, `content`, `created_at`, `created_by`, `thread_id`) VALUES
(1, 'I am happy for you!\r\n', '2025-12-06 14:57:08.000000', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `forum_threads`
--

CREATE TABLE `forum_threads` (
  `id` bigint(20) NOT NULL,
  `anonymous` bit(1) NOT NULL,
  `content` text DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `likes` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `forum_threads`
--

INSERT INTO `forum_threads` (`id`, `anonymous`, `content`, `created_at`, `likes`, `title`, `updated_at`, `created_by`) VALUES
(1, b'1', 'hellooo', '2025-12-03 09:01:40.000000', 5, 'Im happy hehe', '2025-12-03 09:01:40.000000', 2),
(2, b'0', 'Feeling exhausted and boring', '2025-12-06 14:56:39.000000', 2, 'Feeling Tired', '2025-12-06 14:56:39.000000', 2);

-- --------------------------------------------------------

--
-- Table structure for table `learning_module`
--

CREATE TABLE `learning_module` (
  `id` bigint(20) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `short_description` varchar(500) DEFAULT NULL,
  `full_content` text DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `duration` varchar(255) DEFAULT NULL,
  `display_order` int(11) DEFAULT NULL,
  `active` tinyint(1) DEFAULT 1,
  `learning_objectives` text DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `content` text DEFAULT NULL,
  `objectives` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `learning_module`
--

INSERT INTO `learning_module` (`id`, `title`, `short_description`, `full_content`, `category`, `description`, `duration`, `display_order`, `active`, `learning_objectives`, `created_at`, `updated_at`, `content`, `objectives`) VALUES
(1, 'Understanding Anxiety', 'Learn about anxiety disorders', '', 'Mental Health Basics', 'Learn about anxiety disorders, their symptoms, and coping strategies.', '15 min', 1, 1, 'Understand core concepts and definitions\r\nLearn practical coping strategies\r\nKnow when and how to seek help\r\nAccess support resources', '2025-11-29 16:27:56', '2025-12-18 14:57:42', '<h2>What is Anxiety?</h2>\r\n<p>Anxiety is a natural human response to stress, but when it becomes persistent and overwhelming, it can develop into an anxiety disorder. Anxiety disorders are among the most common mental health conditions, affecting millions of people worldwide.</p>\r\n\r\n<h2>Types of Anxiety Disorders</h2>\r\n<ul>\r\n    <li><strong>Generalized Anxiety Disorder (GAD):</strong> Persistent and excessive worry about everyday things</li>\r\n    <li><strong>Panic Disorder:</strong> Recurrent panic attacks with intense physical symptoms</li>\r\n    <li><strong>Social Anxiety Disorder:</strong> Intense fear of social situations and judgment</li>\r\n    <li><strong>Phobias:</strong> Extreme fear of specific objects or situations</li>\r\n</ul>\r\n\r\n<h2>Common Symptoms</h2>\r\n<div class=\"symptoms\">\r\n    <div class=\"physical\">\r\n        <h3>Physical Symptoms:</h3>\r\n        <ul>\r\n            <li>Rapid heartbeat and palpitations</li>\r\n            <li>Sweating and trembling</li>\r\n            <li>Shortness of breath</li>\r\n            <li>Muscle tension</li>\r\n            <li>Fatigue and insomnia</li>\r\n        </ul>\r\n    </div>\r\n    <div class=\"emotional\">\r\n        <h3>Emotional Symptoms:</h3>\r\n        <ul>\r\n            <li>Constant worry and apprehension</li>\r\n            <li>Feeling restless or on edge</li>\r\n            <li>Difficulty concentrating</li>\r\n            <li>Irritability</li>\r\n            <li>Anticipating the worst</li>\r\n        </ul>\r\n    </div>\r\n</div>\r\n\r\n<h2>Coping Strategies</h2>\r\n<ol>\r\n    <li><strong>Breathing Exercises:</strong> Practice deep breathing techniques like 4-7-8 breathing</li>\r\n    <li><strong>Mindfulness:</strong> Stay present through meditation and grounding exercises</li>\r\n    <li><strong>Physical Activity:</strong> Regular exercise to reduce stress hormones</li>\r\n    <li><strong>Cognitive Restructuring:</strong> Challenge and change negative thought patterns</li>\r\n    <li><strong>Limit Stimulants:</strong> Reduce caffeine and alcohol intake</li>\r\n</ol>\r\n\r\n<h2>When to Seek Help</h2>\r\n<p>Consider professional help if:</p>\r\n<ul>\r\n    <li>Anxiety interferes with daily life and relationships</li>\r\n    <li>You experience frequent panic attacks</li>\r\n    <li>You avoid situations due to anxiety</li>\r\n    <li>You use substances to cope with anxiety</li>\r\n    <li>You have thoughts of self-harm</li>\r\n</ul>\r\n\r\n<h2>Resources</h2>\r\n<ul>\r\n    <li>National Alliance on Mental Illness (NAMI) Helpline: 1-800-950-NAMI</li>\r\n    <li>Crisis Text Line: Text HOME to 741741</li>\r\n    <li>Therapy directories: PsychologyToday.com, GoodTherapy.org</li>\r\n    <li>Mental health apps: Calm, Headspace, Woebot</li>\r\n</ul>', NULL),
(2, 'Depression Awareness', 'Understanding depression', '', 'Mental Health Basics', 'Understanding depression: symptoms, causes, and seeking help.', '18 min', 2, 1, 'Recognize symptoms of depression\r\nUnderstand causes and risk factors\r\nLearn about treatment options\r\nKnow when to seek professional help', '2025-11-29 16:27:56', '2025-12-18 14:57:29', '<h2>Understanding Depression</h2>\r\n<p>Depression is more than just feeling sad—it\'s a serious medical condition that affects how you feel, think, and handle daily activities. It requires understanding and treatment, and with the right care, most people can get better.</p>\r\n\r\n<h2>Types of Depression</h2>\r\n<ul>\r\n    <li><strong>Major Depressive Disorder:</strong> Severe symptoms that interfere with daily life</li>\r\n    <li><strong>Persistent Depressive Disorder:</strong> Chronic depression lasting two years or more</li>\r\n    <li><strong>Postpartum Depression:</strong> Depression occurring after childbirth</li>\r\n    <li><strong>Seasonal Affective Disorder:</strong> Depression related to seasonal changes</li>\r\n    <li><strong>Bipolar Depression:</strong> Alternating periods of depression and mania</li>\r\n</ul>\r\n\r\n<h2>Symptoms and Signs</h2>\r\n<div class=\"symptoms-grid\">\r\n    <div class=\"emotional\">\r\n        <h3>Emotional Symptoms:</h3>\r\n        <ul>\r\n            <li>Persistent sad, anxious, or \"empty\" mood</li>\r\n            <li>Feelings of hopelessness or pessimism</li>\r\n            <li>Irritability or frustration</li>\r\n            <li>Loss of interest in activities once enjoyed</li>\r\n            <li>Feelings of guilt, worthlessness, or helplessness</li>\r\n        </ul>\r\n    </div>\r\n    <div class=\"physical\">\r\n        <h3>Physical Symptoms:</h3>\r\n        <ul>\r\n            <li>Fatigue and decreased energy</li>\r\n            <li>Sleep disturbances (insomnia or oversleeping)</li>\r\n            <li>Appetite or weight changes</li>\r\n            <li>Difficulty concentrating or making decisions</li>\r\n            <li>Unexplained aches and pains</li>\r\n        </ul>\r\n    </div>\r\n</div>\r\n\r\n<h2>Causes and Risk Factors</h2>\r\n<ul>\r\n    <li><strong>Biological:</strong> Brain chemistry, genetics, hormones</li>\r\n    <li><strong>Environmental:</strong> Trauma, stress, major life changes</li>\r\n    <li><strong>Psychological:</strong> Personality traits, coping styles</li>\r\n    <li><strong>Medical:</strong> Chronic illness, medication side effects</li>\r\n</ul>\r\n\r\n<h2>Treatment Options</h2>\r\n<ol>\r\n    <li><strong>Psychotherapy:</strong> CBT, interpersonal therapy, psychodynamic therapy</li>\r\n    <li><strong>Medication:</strong> Antidepressants (SSRIs, SNRIs)</li>\r\n    <li><strong>Lifestyle Changes:</strong> Exercise, nutrition, sleep hygiene</li>\r\n    <li><strong>Brain Stimulation Therapies:</strong> ECT, TMS for treatment-resistant cases</li>\r\n    <li><strong>Alternative Therapies:</strong> Light therapy, acupuncture, supplements</li>\r\n</ol>\r\n\r\n<h2>Self-Help Strategies</h2>\r\n<ul>\r\n    <li>Establish a daily routine</li>\r\n    <li>Set realistic goals and break tasks into smaller steps</li>\r\n    <li>Practice self-compassion and challenge negative thoughts</li>\r\n    <li>Connect with supportive people</li>\r\n    <li>Engage in activities you used to enjoy</li>\r\n</ul>\r\n\r\n<h2>Emergency Resources</h2>\r\n<ul>\r\n    <li>National Suicide Prevention Lifeline: 1-800-273-8255</li>\r\n    <li>Crisis Text Line: Text HOME to 741741</li>\r\n    <li>Emergency Services: 911 or local emergency number</li>\r\n    <li>NAMI Helpline: 1-800-950-NAMI (6264)</li>\r\n</ul>', NULL),
(3, 'Stress Management Techniques', 'Discover stress management techniques', '', 'Coping Skills', 'Discover practical techniques to manage stress in your daily life.', '20 min', 3, 1, 'Identify stress triggers\r\nLearn relaxation techniques\r\nDevelop healthy coping mechanisms\r\nCreate a stress management plan', '2025-11-29 16:27:56', '2025-12-18 14:58:06', '<h2>Understanding Stress</h2>\r\n<p>Stress is your body\'s response to any demand or challenge. While short-term stress can be beneficial, chronic stress can negatively impact your physical and mental health. Learning to manage stress is crucial for overall wellbeing.</p>\r\n\r\n<h2>Types of Stress</h2>\r\n<ul>\r\n    <li><strong>Acute Stress:</strong> Short-term stress from recent or anticipated demands</li>\r\n    <li><strong>Episodic Acute Stress:</strong> Frequent acute stress episodes</li>\r\n    <li><strong>Chronic Stress:</strong> Long-term stress from ongoing situations</li>\r\n    <li><strong>Eustress:</strong> Positive stress that motivates and energizes</li>\r\n    <li><strong>Distress:</strong> Negative stress that causes anxiety and discomfort</li>\r\n</ul>\r\n\r\n<h2>Common Stress Triggers</h2>\r\n<div class=\"triggers\">\r\n    <div class=\"work\">\r\n        <h3>Work/School:</h3>\r\n        <ul>\r\n            <li>Heavy workload or tight deadlines</li>\r\n            <li>Job insecurity or difficult relationships</li>\r\n            <li>Lack of control or support</li>\r\n            <li>Work-life imbalance</li>\r\n        </ul>\r\n    </div>\r\n    <div class=\"personal\">\r\n        <h3>Personal Life:</h3>\r\n        <ul>\r\n            <li>Relationship conflicts</li>\r\n            <li>Financial pressures</li>\r\n            <li>Health concerns</li>\r\n            <li>Major life transitions</li>\r\n        </ul>\r\n    </div>\r\n</div>\r\n\r\n<h2>Physical Effects of Chronic Stress</h2>\r\n<ul>\r\n    <li>Weakened immune system</li>\r\n    <li>Increased risk of heart disease</li>\r\n    <li>Digestive problems</li>\r\n    <li>Sleep disturbances</li>\r\n    <li>Muscle tension and pain</li>\r\n    <li>Hormonal imbalances</li>\r\n</ul>\r\n\r\n<h2>Stress Management Techniques</h2>\r\n<ol>\r\n    <li><strong>Breathing Exercises:</strong>\r\n        <ul>\r\n            <li>4-7-8 Breathing: Inhale 4 seconds, hold 7 seconds, exhale 8 seconds</li>\r\n            <li>Box Breathing: 4-second inhale, 4-second hold, 4-second exhale, 4-second pause</li>\r\n            <li>Diaphragmatic breathing for deep relaxation</li>\r\n        </ul>\r\n    </li>\r\n    <li><strong>Progressive Muscle Relaxation:</strong>\r\n        <ul>\r\n            <li>Tense and release muscle groups systematically</li>\r\n            <li>Start from toes and work upward to facial muscles</li>\r\n        </ul>\r\n    </li>\r\n    <li><strong>Mindfulness Meditation:</strong>\r\n        <ul>\r\n            <li>Daily practice of 10-20 minutes</li>\r\n            <li>Focus on present moment without judgment</li>\r\n            <li>Use guided meditations if needed</li>\r\n        </ul>\r\n    </li>\r\n</ol>\r\n\r\n<h2>Developing Healthy Coping Mechanisms</h2>\r\n<ul>\r\n    <li><strong>Physical Activity:</strong> 30 minutes of moderate exercise most days</li>\r\n    <li><strong>Time Management:</strong> Prioritize tasks and learn to say no</li>\r\n    <li><strong>Social Support:</strong> Connect with friends and family regularly</li>\r\n    <li><strong>Hobbies:</strong> Engage in activities you enjoy</li>\r\n    <li><strong>Professional Help:</strong> Seek therapy for chronic stress management</li>\r\n</ul>\r\n\r\n<h2>Creating Your Stress Management Plan</h2>\r\n<ol>\r\n    <li>Identify your main stress sources</li>\r\n    <li>Choose 2-3 techniques that work for you</li>\r\n    <li>Schedule regular practice times</li>\r\n    <li>Monitor your stress levels weekly</li>\r\n    <li>Adjust your plan as needed</li>\r\n    <li>Celebrate small successes</li>\r\n</ol>', NULL),
(4, 'Building Resilience', 'Build mental toughness', '', 'Personal Growth', 'Learn how to bounce back from challenges and build mental toughness.', '25 min', 4, 1, 'Understand resilience factors\r\nDevelop problem-solving skills\r\nLearn adaptive thinking patterns\r\nBuild support networks', '2025-11-29 16:27:56', '2025-12-18 14:58:31', '<h2>What is Resilience?</h2>\r\n<p>Resilience is the ability to bounce back from adversity, trauma, tragedy, threats, or significant sources of stress. It\'s not about avoiding difficult experiences but about navigating through them and emerging stronger.</p>\r\n\r\n<h2>The Science of Resilience</h2>\r\n<p>Research shows that resilience involves:</p>\r\n<ul>\r\n    <li><strong>Neuroplasticity:</strong> The brain\'s ability to form new neural connections</li>\r\n    <li><strong>Stress Response System:</strong> How our bodies and minds adapt to stress</li>\r\n    <li><strong>Protective Factors:</strong> Internal and external resources that buffer against adversity</li>\r\n    <li><strong>Post-Traumatic Growth:</strong> Positive psychological change after trauma</li>\r\n</ul>\r\n\r\n<h2>Key Resilience Factors</h2>\r\n<div class=\"factors\">\r\n    <div class=\"internal\">\r\n        <h3>Internal Factors:</h3>\r\n        <ul>\r\n            <li>Self-awareness and emotional regulation</li>\r\n            <li>Optimism and positive thinking patterns</li>\r\n            <li>Adaptability and flexibility</li>\r\n            <li>Problem-solving skills</li>\r\n            <li>Sense of purpose and meaning</li>\r\n        </ul>\r\n    </div>\r\n    <div class=\"external\">\r\n        <h3>External Factors:</h3>\r\n        <ul>\r\n            <li>Strong social support networks</li>\r\n            <li>Access to resources and opportunities</li>\r\n            <li>Community connections</li>\r\n            <li>Mentors and role models</li>\r\n            <li>Safe and stable environment</li>\r\n        </ul>\r\n    </div>\r\n</div>\r\n\r\n<h2>Building Resilience Skills</h2>\r\n<ol>\r\n    <li><strong>Develop Self-Awareness:</strong>\r\n        <ul>\r\n            <li>Practice mindfulness and reflection</li>\r\n            <li>Identify your strengths and values</li>\r\n            <li>Recognize your emotional triggers</li>\r\n        </ul>\r\n    </li>\r\n    <li><strong>Cultivate Positive Thinking:</strong>\r\n        <ul>\r\n            <li>Challenge negative thought patterns</li>\r\n            <li>Practice gratitude daily</li>\r\n            <li>Reframe challenges as opportunities</li>\r\n        </ul>\r\n    </li>\r\n    <li><strong>Enhance Problem-Solving Skills:</strong>\r\n        <ul>\r\n            <li>Break problems into manageable steps</li>\r\n            <li>Brainstorm multiple solutions</li>\r\n            <li>Learn from past experiences</li>\r\n        </ul>\r\n    </li>\r\n</ol>\r\n\r\n<h2>Adaptive Thinking Patterns</h2>\r\n<ul>\r\n    <li><strong>Growth Mindset:</strong> View challenges as opportunities to learn</li>\r\n    <li><strong>Realistic Optimism:</strong> Hope for the best while preparing for difficulties</li>\r\n    <li><strong>Self-Compassion:</strong> Treat yourself with kindness during tough times</li>\r\n    <li><strong>Perspective-Taking:</strong> See the bigger picture and long-term view</li>\r\n</ul>\r\n\r\n<h2>Building Support Networks</h2>\r\n<ol>\r\n    <li><strong>Identify Your Support System:</strong>\r\n        <ul>\r\n            <li>Family members you can rely on</li>\r\n            <li>Close friends who understand you</li>\r\n            <li>Mentors or coaches</li>\r\n            <li>Professional support (therapists, support groups)</li>\r\n        </ul>\r\n    </li>\r\n    <li><strong>Strengthen Existing Relationships:</strong>\r\n        <ul>\r\n            <li>Communicate openly and honestly</li>\r\n            <li>Offer support to others</li>\r\n            <li>Schedule regular check-ins</li>\r\n        </ul>\r\n    </li>\r\n    <li><strong>Expand Your Network:</strong>\r\n        <ul>\r\n            <li>Join groups with shared interests</li>\r\n            <li>Volunteer in your community</li>\r\n            <li>Attend workshops or classes</li>\r\n        </ul>\r\n    </li>\r\n</ol>\r\n\r\n<h2>Daily Resilience Practices</h2>\r\n<ul>\r\n    <li>Morning reflection on your intentions</li>\r\n    <li>Evening gratitude journaling</li>\r\n    <li>Regular physical activity</li>\r\n    <li>Mindfulness or meditation practice</li>\r\n    <li>Weekly connection with supportive people</li>\r\n    <li>Monthly review of challenges and learnings</li>\r\n</ul>', NULL),
(5, 'Mindfulness & Meditation', 'Introduction to mindfulness', '', 'Coping Skills', 'Introduction to mindfulness practices and meditation techniques.', '22 min', 5, 1, 'Learn mindfulness basics\r\nPractice meditation techniques\r\nApply mindfulness in daily life\r\nUnderstand benefits for mental health', '2025-11-29 16:27:56', '2025-12-18 14:57:54', '<h2>Introduction to Mindfulness</h2>\r\n<p>Mindfulness is the practice of purposely focusing your attention on the present moment—and accepting it without judgment. It\'s a skill that can be developed through meditation and other training, with benefits for both mental and physical health.</p>\r\n\r\n<h2>The Science Behind Mindfulness</h2>\r\n<p>Research demonstrates that mindfulness practice can:</p>\r\n<ul>\r\n    <li>Reduce stress, anxiety, and depression</li>\r\n    <li>Improve focus, memory, and cognitive flexibility</li>\r\n    <li>Enhance emotional regulation and self-awareness</li>\r\n    <li>Lower blood pressure and improve immune function</li>\r\n    <li>Increase gray matter density in brain regions associated with learning and memory</li>\r\n</ul>\r\n\r\n<h2>Core Principles of Mindfulness</h2>\r\n<ol>\r\n    <li><strong>Present-Moment Awareness:</strong> Focusing on here and now</li>\r\n    <li><strong>Non-Judgment:</strong> Observing without labeling experiences as good or bad</li>\r\n    <li><strong>Acceptance:</strong> Acknowledging reality as it is</li>\r\n    <li><strong>Patience:</strong> Allowing experiences to unfold naturally</li>\r\n    <li><strong>Beginner\'s Mind:</strong> Seeing things as if for the first time</li>\r\n    <li><strong>Non-Striving:</strong> Not trying to achieve any particular outcome</li>\r\n</ol>\r\n\r\n<h2>Basic Meditation Techniques</h2>\r\n<div class=\"techniques\">\r\n    <div class=\"breathing\">\r\n        <h3>1. Breath Awareness Meditation:</h3>\r\n        <ol>\r\n            <li>Find a comfortable seated position</li>\r\n            <li>Close your eyes or lower your gaze</li>\r\n            <li>Focus on your natural breathing pattern</li>\r\n            <li>When your mind wanders, gently return to your breath</li>\r\n            <li>Start with 5 minutes daily, gradually increasing</li>\r\n        </ol>\r\n    </div>\r\n    <div class=\"body-scan\">\r\n        <h3>2. Body Scan Meditation:</h3>\r\n        <ol>\r\n            <li>Lie down comfortably on your back</li>\r\n            <li>Bring attention to your toes</li>\r\n            <li>Gradually move attention up through your body</li>\r\n            <li>Notice sensations without judgment</li>\r\n            <li>Complete the scan from toes to head</li>\r\n        </ol>\r\n    </div>\r\n    <div class=\"loving-kindness\">\r\n        <h3>3. Loving-Kindness Meditation:</h3>\r\n        <ol>\r\n            <li>Start by directing loving thoughts to yourself</li>\r\n            <li>Extend these feelings to loved ones</li>\r\n            <li>Include neutral people in your life</li>\r\n            <li>Finally, include all beings everywhere</li>\r\n            <li>Use phrases like \"May you be happy, may you be safe\"</li>\r\n        </ol>\r\n    </div>\r\n</div>\r\n\r\n<h2>Mindfulness in Daily Life</h2>\r\n<ul>\r\n    <li><strong>Mindful Eating:</strong> Pay attention to tastes, textures, and sensations while eating</li>\r\n    <li><strong>Mindful Walking:</strong> Notice each step and your surroundings</li>\r\n    <li><strong>Mindful Listening:</strong> Give full attention when others are speaking</li>\r\n    <li><strong>Mindful Working:</strong> Focus completely on one task at a time</li>\r\n    <li><strong>Mindful Pausing:</strong> Take three mindful breaths before responding in conversations</li>\r\n</ul>\r\n\r\n<h2>Overcoming Common Challenges</h2>\r\n<ol>\r\n    <li><strong>Difficulty Focusing:</strong>\r\n        <ul>\r\n            <li>Start with shorter sessions (3-5 minutes)</li>\r\n            <li>Use guided meditations initially</li>\r\n            <li>Be patient and gentle with yourself</li>\r\n        </ul>\r\n    </li>\r\n    <li><strong>Restlessness or Boredom:</strong>\r\n        <ul>\r\n            <li>Try different meditation techniques</li>\r\n            <li>Incorporate movement (walking meditation)</li>\r\n            <li>Remember that meditation is practice, not perfection</li>\r\n        </ul>\r\n    </li>\r\n    <li><strong>Emotional Discomfort:</strong>\r\n        <ul>\r\n            <li>Observe emotions without getting caught in them</li>\r\n            <li>Remind yourself that all emotions pass</li>\r\n            <li>Seek professional guidance if needed</li>\r\n        </ul>\r\n    </li>\r\n</ol>\r\n\r\n<h2>Creating a Sustainable Practice</h2>\r\n<ol>\r\n    <li>Start with 5-10 minutes daily</li>\r\n    <li>Choose a consistent time and place</li>\r\n    <li>Use reminders or meditation apps</li>\r\n    <li>Join a meditation group or class</li>\r\n    <li>Be flexible and adapt as needed</li>\r\n    <li>Celebrate your commitment to practice</li>\r\n</ol>\r\n\r\n<h2>Resources for Continued Practice</h2>\r\n<ul>\r\n    <li>Apps: Headspace, Calm, Insight Timer, Ten Percent Happier</li>\r\n    <li>Books: \"The Miracle of Mindfulness\" by Thich Nhat Hanh, \"Wherever You Go, There You Are\" by Jon Kabat-Zinn</li>\r\n    <li>Online Courses: Mindfulness-Based Stress Reduction (MBSR)</li>\r\n    <li>Local: Meditation centers, yoga studios, community classes</li>\r\n</ul>', NULL),
(6, 'Sleep Hygiene', 'Optimize your sleep', '', 'Wellness', 'Optimize your sleep for better mental and physical health.', '15 min', 6, 1, 'Understand sleep cycles\r\nCreate optimal sleep environment\r\nDevelop bedtime routines\r\nAddress common sleep issues', '2025-11-29 16:27:56', '2025-12-18 14:58:41', '<h2>The Importance of Sleep</h2>\r\n<p>Sleep is not just \"downtime\" when your brain shuts off. It\'s an active period during which important processing, restoration, and strengthening occurs. Quality sleep is essential for physical health, emotional wellbeing, and cognitive function.</p>\r\n\r\n<h2>The Science of Sleep Cycles</h2>\r\n<p>Sleep occurs in cycles of approximately 90 minutes, each containing four stages:</p>\r\n<ol>\r\n    <li><strong>Stage 1 (NREM):</strong> Light sleep, easy to wake (1-7 minutes)</li>\r\n    <li><strong>Stage 2 (NREM):</strong> Body temperature drops, heart rate slows (10-25 minutes)</li>\r\n    <li><strong>Stage 3 (NREM):</strong> Deep sleep, physical restoration occurs (20-40 minutes)</li>\r\n    <li><strong>Stage 4 (REM):</strong> Dreaming, memory consolidation, brain restoration (10-60 minutes)</li>\r\n</ol>\r\n\r\n<h2>Sleep Requirements by Age</h2>\r\n<ul>\r\n    <li>Newborns (0-3 months): 14-17 hours daily</li>\r\n    <li>Infants (4-11 months): 12-15 hours</li>\r\n    <li>Toddlers (1-2 years): 11-14 hours</li>\r\n    <li>Preschoolers (3-5): 10-13 hours</li>\r\n    <li>School-age (6-13): 9-11 hours</li>\r\n    <li>Teenagers (14-17): 8-10 hours</li>\r\n    <li>Adults (18-64): 7-9 hours</li>\r\n    <li>Older adults (65+): 7-8 hours</li>\r\n</ul>\r\n\r\n<h2>Creating an Optimal Sleep Environment</h2>\r\n<div class=\"environment\">\r\n    <div class=\"bedroom\">\r\n        <h3>Bedroom Setup:</h3>\r\n        <ul>\r\n            <li><strong>Temperature:</strong> Keep between 60-67°F (15-19°C)</li>\r\n            <li><strong>Lighting:</strong> Use blackout curtains, eliminate blue light</li>\r\n            <li><strong>Noise:</strong> Use white noise machine or earplugs if needed</li>\r\n            <li><strong>Comfort:</strong> Invest in quality mattress and pillows</li>\r\n            <li><strong>Cleanliness:</strong> Keep bedroom clean and clutter-free</li>\r\n        </ul>\r\n    </div>\r\n    <div class=\"technology\">\r\n        <h3>Technology Management:</h3>\r\n        <ul>\r\n            <li>Avoid screens 1 hour before bedtime</li>\r\n            <li>Use blue light filters on devices</li>\r\n            <li>Keep phones and devices out of bedroom</li>\r\n            <li>Use traditional alarm clocks instead of phones</li>\r\n        </ul>\r\n    </div>\r\n</div>\r\n\r\n<h2>Developing a Bedtime Routine</h2>\r\n<ol>\r\n    <li><strong>Wind Down Period (60-90 minutes before bed):</strong>\r\n        <ul>\r\n            <li>Dim lights throughout your home</li>\r\n            <li>Engage in relaxing activities (reading, gentle stretching)</li>\r\n            <li>Take a warm bath or shower</li>\r\n            <li>Practice relaxation techniques (deep breathing, meditation)</li>\r\n        </ul>\r\n    </li>\r\n    <li><strong>Consistent Sleep Schedule:</strong>\r\n        <ul>\r\n            <li>Go to bed and wake up at the same time every day</li>\r\n            <li>Even on weekends, maintain similar schedule</li>\r\n            <li>Establish pre-sleep rituals your body recognizes</li>\r\n        </ul>\r\n    </li>\r\n</ol>\r\n\r\n<h2>Common Sleep Issues and Solutions</h2>\r\n<ol>\r\n    <li><strong>Insomnia:</strong>\r\n        <ul>\r\n            <li>Establish consistent sleep schedule</li>\r\n            <li>Create relaxing bedtime routine</li>\r\n            <li>Use bed only for sleep and intimacy</li>\r\n            <li>If unable to sleep after 20 minutes, get up and do something relaxing</li>\r\n        </ul>\r\n    </li>\r\n    <li><strong>Sleep Apnea:</strong>\r\n        <ul>\r\n            <li>Consult a sleep specialist</li>\r\n            <li>Consider CPAP therapy if prescribed</li>\r\n            <li>Maintain healthy weight</li>\r\n            <li>Avoid alcohol before bed</li>\r\n        </ul>\r\n    </li>\r\n    <li><strong>Restless Leg Syndrome:</strong>\r\n        <ul>\r\n            <li>Regular moderate exercise</li>\r\n            <li>Massage legs before bed</li>\r\n            <li>Avoid caffeine and nicotine</li>\r\n            <li>Consider iron supplements if deficient</li>\r\n        </ul>\r\n    </li>\r\n</ol>\r\n\r\n<h2>Sleep and Mental Health Connection</h2>\r\n<ul>\r\n    <li>Poor sleep increases risk of depression and anxiety</li>\r\n    <li>Sleep deprivation impairs emotional regulation</li>\r\n    <li>Quality sleep enhances problem-solving and creativity</li>\r\n    <li>Consistent sleep supports stress resilience</li>\r\n    <li>Sleep disturbances can be early warning signs of mental health issues</li>\r\n</ul>\r\n\r\n<h2>Nutrition for Better Sleep</h2>\r\n<ul>\r\n    <li><strong>Foods to Promote Sleep:</strong>\r\n        <ul>\r\n            <li>Complex carbohydrates (whole grains)</li>\r\n            <li>Lean proteins (turkey, fish)</li>\r\n            <li>Foods rich in magnesium (leafy greens, nuts)</li>\r\n            <li>Foods with melatonin (cherries, walnuts)</li>\r\n        </ul>\r\n    </li>\r\n    <li><strong>Foods to Avoid Before Bed:</strong>\r\n        <ul>\r\n            <li>Caffeine (after 2 PM)</li>\r\n            <li>Alcohol (disrupts sleep cycles)</li>\r\n            <li>Heavy, spicy, or fatty foods</li>\r\n            <li>Excessive fluids (to prevent nighttime bathroom trips)</li>\r\n        </ul>\r\n    </li>\r\n</ul>\r\n\r\n<h2>Professional Help for Sleep Issues</h2>\r\n<p>Consult a healthcare provider if you experience:</p>\r\n<ul>\r\n    <li>Persistent difficulty falling or staying asleep</li>\r\n    <li>Loud snoring or breathing interruptions during sleep</li>\r\n    <li>Excessive daytime sleepiness affecting daily life</li>\r\n    <li>Sleep issues lasting more than a few weeks</li>\r\n    <li>Sleep disturbances accompanied by other health concerns</li>\r\n</ul>', NULL),
(7, 'Social Connection', 'Build meaningful connections', '', 'Relationships', 'The importance of social support and building meaningful connections.', '18 min', 7, 1, 'Understand social support benefits\r\nDevelop communication skills\r\nBuild healthy relationships\r\nMaintain social connections', '2025-11-29 16:27:56', '2025-12-18 14:58:52', '<h2>The Power of Social Connection</h2>\r\n<p>Humans are inherently social beings. Our need to connect with others is as fundamental as our need for food, water, and shelter. Social connections significantly impact our mental and physical health, happiness, and even longevity.</p>\r\n\r\n<h2>The Science of Social Support</h2>\r\n<p>Research shows that strong social connections:</p>\r\n<ul>\r\n    <li>Reduce risk of premature death by 50%</li>\r\n    <li>Lower rates of anxiety and depression</li>\r\n    <li>Improve immune system function</li>\r\n    <li>Enhance cognitive function as we age</li>\r\n    <li>Increase pain tolerance</li>\r\n    <li>Improve recovery from illness and surgery</li>\r\n</ul>\r\n\r\n<h2>Types of Social Support</h2>\r\n<div class=\"support-types\">\r\n    <div class=\"emotional\">\r\n        <h3>1. Emotional Support:</h3>\r\n        <ul>\r\n            <li>Providing empathy, love, trust, and caring</li>\r\n            <li>Listening without judgment</li>\r\n            <li>Offering comfort during difficult times</li>\r\n            <li>Validating feelings and experiences</li>\r\n        </ul>\r\n    </div>\r\n    <div class=\"instrumental\">\r\n        <h3>2. Instrumental Support:</h3>\r\n        <ul>\r\n            <li>Tangible aid and service</li>\r\n            <li>Financial assistance when needed</li>\r\n            <li>Help with tasks or responsibilities</li>\r\n            <li>Providing resources or information</li>\r\n        </ul>\r\n    </div>\r\n    <div class=\"informational\">\r\n        <h3>3. Informational Support:</h3>\r\n        <ul>\r\n            <li>Advice, suggestions, and information</li>\r\n            <li>Sharing knowledge and experience</li>\r\n            <li>Providing guidance in decision-making</li>\r\n            <li>Offering different perspectives</li>\r\n        </ul>\r\n    </div>\r\n    <div class=\"belonging\">\r\n        <h3>4. Companionship Support:</h3>\r\n        <ul>\r\n            <li>Sense of social belonging</li>\r\n            <li>Shared activities and interests</li>\r\n            <li>Feeling part of a community</li>\r\n            <li>Reducing loneliness and isolation</li>\r\n        </ul>\r\n    </div>\r\n</div>\r\n\r\n<h2>Developing Communication Skills</h2>\r\n<ol>\r\n    <li><strong>Active Listening:</strong>\r\n        <ul>\r\n            <li>Give full attention to the speaker</li>\r\n            <li>Maintain eye contact and open body language</li>\r\n            <li>Reflect back what you\'ve heard</li>\r\n            <li>Avoid interrupting or planning your response</li>\r\n        </ul>\r\n    </li>\r\n    <li><strong>Assertive Communication:</strong>\r\n        <ul>\r\n            <li>Use \"I\" statements to express feelings</li>\r\n            <li>Be clear and direct about your needs</li>\r\n            <li>Respect others\' boundaries while maintaining your own</li>\r\n            <li>Practice saying no when necessary</li>\r\n        </ul>\r\n    </li>\r\n    <li><strong>Empathy Development:</strong>\r\n        <ul>\r\n            <li>Try to understand others\' perspectives</li>\r\n            <li>Validate others\' feelings even when you disagree</li>\r\n            <li>Ask open-ended questions to understand better</li>\r\n            <li>Practice putting yourself in others\' shoes</li>\r\n        </ul>\r\n    </li>\r\n</ol>\r\n\r\n<h2>Building Healthy Relationships</h2>\r\n<ol>\r\n    <li><strong>Foundation of Trust:</strong>\r\n        <ul>\r\n            <li>Be reliable and keep commitments</li>\r\n            <li>Be honest and transparent</li>\r\n            <li>Respect confidentiality</li>\r\n            <li>Admit mistakes and apologize sincerely</li>\r\n        </ul>\r\n    </li>\r\n    <li><strong>Mutual Respect:</strong>\r\n        <ul>\r\n            <li>Value each other\'s opinions and boundaries</li>\r\n            <li>Support each other\'s growth and independence</li>\r\n            <li>Avoid criticism and contempt</li>\r\n            <li>Celebrate each other\'s successes</li>\r\n        </ul>\r\n    </li>\r\n    <li><strong>Healthy Conflict Resolution:</strong>\r\n        <ul>\r\n            <li>Address issues directly but respectfully</li>\r\n            <li>Focus on the issue, not personal attacks</li>\r\n            <li>Take breaks when emotions run high</li>\r\n            <li>Seek compromise and win-win solutions</li>\r\n        </ul>\r\n    </li>\r\n</ol>\r\n\r\n<h2>Overcoming Social Anxiety</h2>\r\n<ol>\r\n    <li><strong>Start Small:</strong>\r\n        <ul>\r\n            <li>Begin with brief, low-pressure social interactions</li>\r\n            <li>Practice conversation skills with familiar people</li>\r\n            <li>Attend events with a trusted friend initially</li>\r\n        </ul>\r\n    </li>\r\n    <li><strong>Cognitive Restructuring:</strong>\r\n        <ul>\r\n            <li>Challenge negative thoughts about social situations</li>\r\n            <li>Replace catastrophizing with realistic thinking</li>\r\n            <li>Focus on others rather than self-consciousness</li>\r\n        </ul>\r\n    </li>\r\n    <li><strong>Exposure Techniques:</strong>\r\n        <ul>\r\n            <li>Gradually face feared social situations</li>\r\n            <li>Start with easier situations and build up</li>\r\n            <li>Practice relaxation techniques before social events</li>\r\n        </ul>\r\n    </li>\r\n</ol>\r\n\r\n<h2>Maintaining Social Connections</h2>\r\n<ol>\r\n    <li><strong>Regular Check-Ins:</strong>\r\n        <ul>\r\n            <li>Schedule regular calls or visits with important people</li>\r\n            <li>Use technology to stay connected with distant friends</li>\r\n            <li>Remember important dates and milestones</li>\r\n        </ul>\r\n    </li>\r\n    <li><strong>Quality Over Quantity:</strong>\r\n        <ul>\r\n            <li>Focus on depth rather than number of connections</li>\r\n            <li>Invest time in relationships that matter most</li>\r\n            <li>Be present during interactions (put away devices)</li>\r\n        </ul>\r\n    </li>\r\n    <li><strong>Community Involvement:</strong>\r\n        <ul>\r\n            <li>Join groups based on interests or values</li>\r\n            <li>Volunteer for causes you care about</li>\r\n            <li>Participate in community events or classes</li>\r\n        </ul>\r\n    </li>\r\n</ol>\r\n\r\n<h2>Digital Social Connections</h2>\r\n<p>While technology can help maintain connections, balance is key:</p>\r\n<ul>\r\n    <li>Use video calls for more personal connection than text</li>\r\n    <li>Schedule virtual social activities (game nights, book clubs)</li>\r\n    <li>Be mindful of social media comparison traps</li>\r\n    <li>Take regular digital detoxes</li>\r\n    <li>Use technology to enhance, not replace, in-person connections</li>\r\n</ul>\r\n\r\n<h2>Building Your Social Support Network</h2>\r\n<ol>\r\n    <li>Identify existing connections to strengthen</li>\r\n    <li>List activities or groups you\'d like to join</li>\r\n    <li>Set small, achievable social goals</li>\r\n    <li>Practice initiating conversations and invitations</li>\r\n    <li>Be patient - meaningful connections take time to develop</li>\r\n    <li>Remember that quality connections require mutual effort</li>\r\n</ol>', NULL),
(8, 'Self-Care Strategies', 'Develop self-care routine', '', 'Wellness', 'Develop a personalized self-care routine for mental wellbeing.', '20 min', 8, 1, 'Identify self-care needs\r\nCreate personalized routine\r\nBalance different life areas\r\nMaintain consistent practice', '2025-11-29 16:27:56', '2025-12-18 14:58:20', '<h2>Understanding Self-Care</h2>\r\n<p>Self-care is the practice of taking action to preserve or improve one\'s own health. It involves taking responsibility for your own wellbeing and taking time to attend to your physical, mental, and emotional needs. Self-care is not selfish—it\'s essential for sustainable wellbeing.</p>\r\n\r\n<h2>The Science of Self-Care</h2>\r\n<p>Regular self-care practice has been shown to:</p>\r\n<ul>\r\n    <li>Reduce stress and prevent burnout</li>\r\n    <li>Improve physical health and immune function</li>\r\n    <li>Enhance emotional regulation and resilience</li>\r\n    <li>Increase productivity and creativity</li>\r\n    <li>Improve relationships through better boundaries</li>\r\n    <li>Support long-term mental health maintenance</li>\r\n</ul>\r\n\r\n<h2>Dimensions of Self-Care</h2>\r\n<div class=\"dimensions\">\r\n    <div class=\"physical\">\r\n        <h3>1. Physical Self-Care:</h3>\r\n        <ul>\r\n            <li>Nutrition and hydration</li>\r\n            <li>Regular exercise and movement</li>\r\n            <li>Sleep hygiene and rest</li>\r\n            <li>Medical care and check-ups</li>\r\n            <li>Personal hygiene and grooming</li>\r\n        </ul>\r\n    </div>\r\n    <div class=\"emotional\">\r\n        <h3>2. Emotional Self-Care:</h3>\r\n        <ul>\r\n            <li>Emotional awareness and expression</li>\r\n            <li>Stress management techniques</li>\r\n            <li>Setting healthy boundaries</li>\r\n            <li>Practicing self-compassion</li>\r\n            <li>Seeking therapy or counseling when needed</li>\r\n        </ul>\r\n    </div>\r\n    <div class=\"mental\">\r\n        <h3>3. Mental Self-Care:</h3>\r\n        <ul>\r\n            <li>Continuous learning and growth</li>\r\n            <li>Mindfulness and meditation</li>\r\n            <li>Creative expression</li>\r\n            <li>Mental stimulation and challenges</li>\r\n            <li>Digital detox and information management</li>\r\n        </ul>\r\n    </div>\r\n    <div class=\"social\">\r\n        <h3>4. Social Self-Care:</h3>\r\n        <ul>\r\n            <li>Maintaining healthy relationships</li>\r\n            <li>Setting social boundaries</li>\r\n            <li>Asking for help when needed</li>\r\n            <li>Community involvement</li>\r\n            <li>Social activities that bring joy</li>\r\n        </ul>\r\n    </div>\r\n    <div class=\"spiritual\">\r\n        <h3>5. Spiritual Self-Care:</h3>\r\n        <ul>\r\n            <li>Connecting with personal values</li>\r\n            <li>Meditation or prayer</li>\r\n            <li>Time in nature</li>\r\n            <li>Reflection and journaling</li>\r\n            <li>Practicing gratitude</li>\r\n        </ul>\r\n    </div>\r\n    <div class=\"practical\">\r\n        <h3>6. Practical Self-Care:</h3>\r\n        <ul>\r\n            <li>Financial management</li>\r\n            <li>Organization and decluttering</li>\r\n            <li>Time management</li>\r\n            <li>Skill development</li>\r\n            <li>Home and work environment optimization</li>\r\n        </ul>\r\n    </div>\r\n</div>\r\n\r\n<h2>Identifying Your Self-Care Needs</h2>\r\n<ol>\r\n    <li><strong>Self-Assessment:</strong>\r\n        <ul>\r\n            <li>What areas of your life feel depleted?</li>\r\n            <li>When do you feel most energized and fulfilled?</li>\r\n            <li>What activities help you recharge?</li>\r\n            <li>What warning signs indicate you need more self-care?</li>\r\n        </ul>\r\n    </li>\r\n    <li><strong>Listening to Your Body and Mind:</strong>\r\n        <ul>\r\n            <li>Notice physical signs of stress or fatigue</li>\r\n            <li>Pay attention to emotional signals</li>\r\n            <li>Recognize cognitive signs like difficulty concentrating</li>\r\n            <li>Identify patterns in your energy levels</li>\r\n        </ul>\r\n    </li>\r\n</ol>\r\n\r\n<h2>Creating Your Personalized Self-Care Routine</h2>\r\n<ol>\r\n    <li><strong>Start with Basics:</strong>\r\n        <ul>\r\n            <li>Identify 2-3 non-negotiable self-care practices</li>\r\n            <li>Schedule them like important appointments</li>\r\n            <li>Start small and build gradually</li>\r\n            <li>Choose activities you genuinely enjoy</li>\r\n        </ul>\r\n    </li>\r\n    <li><strong>Daily Self-Care Practices:</strong>\r\n        <ul>\r\n            <li>Morning routine to set positive tone for day</li>\r\n            <li>Mid-day breaks for movement or mindfulness</li>\r\n            <li>Evening wind-down routine for better sleep</li>\r\n            <li>Quick self-check-ins throughout the day</li>\r\n        </ul>\r\n    </li>\r\n    <li><strong>Weekly Self-Care:</strong>\r\n        <ul>\r\n            <li>Longer self-care sessions (1-2 hours)</li>\r\n            <li>Social connection activities</li>\r\n            <li>Creative or learning pursuits</li>\r\n            <li>Planning and preparation for the week ahead</li>\r\n        </ul>\r\n    </li>\r\n</ol>\r\n\r\n<h2>Overcoming Common Self-Care Barriers</h2>\r\n<ol>\r\n    <li><strong>\"I don\'t have time\":</strong>\r\n        <ul>\r\n            <li>Start with 5-minute self-care practices</li>\r\n            <li>Combine self-care with existing activities</li>\r\n            <li>Recognize that self-care saves time by preventing burnout</li>\r\n            <li>Schedule self-care in your calendar</li>\r\n        </ul>\r\n    </li>\r\n    <li><strong>\"I feel guilty taking time for myself\":</strong>\r\n        <ul>\r\n            <li>Reframe self-care as necessary maintenance</li>\r\n            <li>Remember that caring for yourself enables you to care for others</li>\r\n            <li>Start with small, manageable self-care acts</li>\r\n            <li>Practice self-compassion and permission to prioritize yourself</li>\r\n        </ul>\r\n    </li>\r\n    <li><strong>\"Nothing seems to help\":</strong>\r\n        <ul>\r\n            <li>Experiment with different types of self-care</li>\r\n            <li>Consider professional help if needed</li>\r\n            <li>Look for underlying issues that may need addressing</li>\r\n            <li>Be patient - benefits often accumulate gradually</li>\r\n        </ul>\r\n    </li>\r\n</ol>\r\n\r\n<h2>Balancing Different Life Areas</h2>\r\n<ul>\r\n    <li><strong>Work-Life Balance:</strong>\r\n        <ul>\r\n            <li>Set clear work boundaries</li>\r\n            <li>Take regular breaks throughout workday</li>\r\n            <li>Create transition rituals between work and home</li>\r\n            <li>Use vacation time fully and unapologetically</li>\r\n        </ul>\r\n    </li>\r\n    <li><strong>Social Balance:</strong>\r\n        <ul>\r\n            <li>Balance social time with alone time</li>\r\n            <li>Learn to say no to social obligations when needed</li>\r\n            <li>Choose quality interactions over quantity</li>\r\n            <li>Schedule regular \"me time\" on your calendar</li>\r\n        </ul>\r\n    </li>\r\n</ul>\r\n\r\n<h2>Maintaining Consistent Practice</h2>\r\n<ol>\r\n    <li><strong>Create Self-Care Rituals:</strong>\r\n        <ul>\r\n            <li>Establish consistent routines</li>\r\n            <li>Create enjoyable self-care spaces</li>\r\n            <li>Use reminders and cues</li>\r\n            <li>Pair self-care with existing habits</li>\r\n        </ul>\r\n    </li>\r\n    <li><strong>Track Your Progress:</strong>\r\n        <ul>\r\n            <li>Keep a self-care journal</li>\r\n            <li>Notice improvements in mood and energy</li>\r\n            <li>Adjust your routine as needed</li>\r\n            <li>Celebrate self-care successes</li>\r\n        </ul>\r\n    </li>\r\n    <li><strong>Build Accountability:</strong>\r\n        <ul>\r\n            <li>Share your self-care goals with someone supportive</li>\r\n            <li>Join self-care challenge groups</li>\r\n            <li>Use apps to track habits</li>\r\n            <li>Schedule regular self-care check-ins</li>\r\n        </ul>\r\n    </li>\r\n</ol>\r\n\r\n<h2>Self-Care for Different Life Situations</h2>\r\n<ul>\r\n    <li><strong>During High Stress Periods:</strong> Focus on basic needs and simple self-care</li>\r\n    <li><strong>When Feeling Depressed:</strong> Start with gentle movement and basic hygiene</li>\r\n    <li><strong>During Grief or Loss:</strong> Allow rest and seek supportive connections</li>\r\n    <li><strong>When Busy:</strong> Integrate micro-self-care throughout day</li>\r\n    <li><strong>During Transitions:</strong> Maintain foundational self-care practices</li>\r\n</ul>\r\n\r\n<h2>Professional Support for Self-Care</h2>\r\n<p>Consider seeking professional help when:</p>\r\n<ul>\r\n    <li>Self-care feels impossible to implement</li>\r\n    <li>You experience persistent low mood or anxiety</li>\r\n    <li>Self-neglect patterns are deeply ingrained</li>\r\n    <li>You need help identifying appropriate self-care strategies</li>\r\n    <li>Self-care efforts don\'t improve your wellbeing</li>\r\n</ul>', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `mood_entries`
--

CREATE TABLE `mood_entries` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `entry_date` date DEFAULT NULL,
  `mood` varchar(255) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `mood_entries`
--

INSERT INTO `mood_entries` (`id`, `created_at`, `entry_date`, `mood`, `notes`, `student_id`) VALUES
(1, '2025-11-24 17:20:55.000000', '2025-11-24', 'Great', 'Finished all the tests', 2),
(2, '2025-11-24 17:24:43.000000', '2025-11-24', 'Awful', 'Umbrella gone', 2),
(3, '2025-12-03 09:00:51.000000', '2025-12-03', 'Great', '', 2),
(4, '2025-12-03 09:01:17.000000', '2025-12-03', 'Okay', '', 2);

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE `question` (
  `id` int(11) NOT NULL,
  `question_set_id` int(11) NOT NULL,
  `question_text` varchar(255) NOT NULL,
  `question_order` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`id`, `question_set_id`, `question_text`, `question_order`) VALUES
(1, 1, 'Little interest or pleasure in doing things', 1),
(2, 1, 'Feeling down, depressed, or hopeless', 2),
(3, 1, 'Trouble falling or staying asleep, or sleeping too much', 3),
(4, 1, 'Feeling tired or having little energy', 4),
(5, 1, 'Poor appetite or overeating', 5),
(6, 1, 'Feeling bad about yourself', 6),
(7, 1, 'Trouble concentrating', 7),
(8, 1, 'Moving or speaking slowly / being fidgety', 8),
(9, 1, 'Thoughts that you would be better off dead', 9),
(10, 2, 'Feeling nervous, anxious, or on edge', 1),
(11, 2, 'Not being able to stop or control worrying', 2),
(12, 2, 'Worrying too much about different things', 3),
(13, 2, 'Trouble relaxing', 4),
(14, 2, 'Being so restless that it is hard to sit still', 5),
(15, 2, 'Becoming easily annoyed or irritable', 6),
(16, 2, 'Feeling afraid something awful might happen', 7);

-- --------------------------------------------------------

--
-- Table structure for table `question_set`
--

CREATE TABLE `question_set` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `question_set`
--

INSERT INTO `question_set` (`id`, `name`, `description`) VALUES
(1, 'PHQ-9', 'Depression screening questionnaire'),
(2, 'GAD-7', 'Generalized anxiety disorder screening questionnaire');

-- --------------------------------------------------------

--
-- Table structure for table `quiz_attempt`
--

CREATE TABLE `quiz_attempt` (
  `id` bigint(20) NOT NULL,
  `student_id` int(11) NOT NULL,
  `module_id` bigint(20) NOT NULL,
  `score` int(11) DEFAULT NULL,
  `attempt_time` datetime DEFAULT current_timestamp(),
  `passed` tinyint(1) DEFAULT 0,
  `time_taken_seconds` int(11) DEFAULT NULL,
  `answers` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `quiz_attempt`
--

INSERT INTO `quiz_attempt` (`id`, `student_id`, `module_id`, `score`, `attempt_time`, `passed`, `time_taken_seconds`, `answers`) VALUES
(1, 2, 1, 85, '2025-11-29 16:29:38', 1, 300, '0,0,0,0,0'),
(2, 2, 2, 90, '2025-11-29 16:29:38', 1, 280, '0,1,0,0,0'),
(3, 2, 3, 78, '2025-11-29 16:29:38', 1, 320, '0,2,0,0,0'),
(4, 2, 4, 100, '2025-12-02 01:52:59', 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `quiz_question`
--

CREATE TABLE `quiz_question` (
  `id` bigint(20) NOT NULL,
  `module_id` bigint(20) DEFAULT NULL,
  `question` text NOT NULL,
  `correct_answer_index` int(11) DEFAULT NULL,
  `question_text` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `quiz_question`
--

INSERT INTO `quiz_question` (`id`, `module_id`, `question`, `correct_answer_index`, `question_text`) VALUES
(1, 1, 'What physical symptom is commonly associated with anxiety disorders?', 0, NULL),
(2, 1, 'Which type of anxiety disorder involves excessive worry about everyday situations?', 0, NULL),
(3, 1, 'The \"fight or flight\" response in anxiety is controlled by which body system?', 0, NULL),
(4, 1, 'Which of the following is a common cognitive symptom of anxiety?', 0, NULL),
(5, 1, 'What is a recommended immediate coping strategy for anxiety attacks?', 0, NULL),
(6, 2, 'Which of the following is a core symptom of major depressive disorder?', 0, NULL),
(7, 2, 'How long must symptoms persist for a depression diagnosis?', 1, NULL),
(8, 2, 'Which neurotransmitter is most commonly associated with depression?', 0, NULL),
(9, 2, 'What is anhedonia in the context of depression?', 0, NULL),
(10, 2, 'Which of these is considered a healthy coping mechanism for depression?', 0, NULL),
(11, 3, 'Which technique involves tensing and relaxing muscle groups?', 0, NULL),
(12, 3, 'What is the recommended minimum minutes of daily exercise for stress reduction?', 2, NULL),
(13, 3, 'Which breathing technique is most effective for immediate stress relief?', 0, NULL),
(14, 3, 'What does the \"A\" stand for in the ABC stress management model?', 0, NULL),
(15, 3, 'Which of these is a long-term stress management strategy?', 0, NULL),
(16, 4, 'What is resilience primarily about?', 0, NULL),
(17, 4, 'Which mindset is most associated with resilience?', 0, NULL),
(18, 4, 'What is a key characteristic of resilient people?', 0, NULL),
(19, 4, 'Which practice helps build emotional resilience?', 0, NULL),
(20, 4, 'What does \"cognitive reframing\" involve?', 0, NULL),
(21, 5, 'What is the primary goal of mindfulness?', 0, NULL),
(22, 5, 'How long is a typical beginner meditation session?', 1, NULL),
(23, 5, 'Which of these is a core component of mindfulness?', 0, NULL),
(24, 5, 'What is the \"body scan\" technique used for?', 0, NULL),
(25, 5, 'Which breathing pattern is commonly used in meditation?', 0, NULL),
(26, 6, 'What is the recommended hours of sleep for adults?', 2, NULL),
(27, 6, 'Which activity should be avoided before bedtime?', 0, NULL),
(28, 6, 'What is the ideal bedroom temperature for sleep?', 1, NULL),
(29, 6, 'Which hormone regulates sleep-wake cycles?', 0, NULL),
(30, 6, 'What is \"sleep latency\"?', 0, NULL),
(31, 7, 'What is social support primarily associated with?', 0, NULL),
(32, 7, 'Which factor most influences relationship quality?', 0, NULL),
(33, 7, 'What is \"active listening\"?', 0, NULL),
(34, 7, 'Which behavior strengthens social connections?', 0, NULL),
(35, 7, 'What is emotional intelligence important for in relationships?', 0, NULL),
(36, 8, 'What is the primary purpose of self-care?', 0, NULL),
(37, 8, 'Which of these is an example of emotional self-care?', 0, NULL),
(38, 8, 'How often should self-care activities be practiced?', 0, NULL),
(39, 8, 'What does \"setting boundaries\" involve in self-care?', 0, NULL),
(40, 8, 'Which self-care practice is most sustainable long-term?', 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `quiz_question_options`
--

CREATE TABLE `quiz_question_options` (
  `id` bigint(20) NOT NULL,
  `question_id` bigint(20) DEFAULT NULL,
  `option_text` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `quiz_question_options`
--

INSERT INTO `quiz_question_options` (`id`, `question_id`, `option_text`) VALUES
(1, 1, 'Rapid heartbeat and sweating'),
(2, 1, 'Improved digestion'),
(3, 1, 'Lower blood pressure'),
(4, 1, 'Enhanced vision'),
(5, 2, 'Generalized Anxiety Disorder (GAD)'),
(6, 2, 'Common cold'),
(7, 2, 'Seasonal allergies'),
(8, 2, 'Food intolerance'),
(9, 3, 'Nervous system'),
(10, 3, 'Digestive system'),
(11, 3, 'Skeletal system'),
(12, 3, 'Reproductive system'),
(13, 4, 'Catastrophic thinking'),
(14, 4, 'Improved memory'),
(15, 4, 'Enhanced creativity'),
(16, 4, 'Better problem-solving'),
(17, 5, 'Deep breathing exercises'),
(18, 5, 'Drinking caffeine'),
(19, 5, 'Avoiding the situation'),
(20, 5, 'Isolating yourself'),
(21, 6, 'Persistent sad or empty mood'),
(22, 6, 'Increased energy levels'),
(23, 6, 'Improved appetite'),
(24, 6, 'Reduced sleep needs'),
(25, 7, 'At least 2 weeks'),
(26, 7, 'More than 6 months'),
(27, 7, 'Exactly 1 month'),
(28, 7, 'Less than 1 week'),
(29, 8, 'Serotonin'),
(30, 8, 'Adrenaline'),
(31, 8, 'Insulin'),
(32, 8, 'Testosterone'),
(33, 9, 'Loss of interest in pleasure activities'),
(34, 9, 'Excessive happiness'),
(35, 9, 'Increased social activity'),
(36, 9, 'Improved concentration'),
(37, 10, 'Regular exercise and social contact'),
(38, 10, 'Avoiding all social situations'),
(39, 10, 'Self-medication with alcohol'),
(40, 10, 'Isolating from friends'),
(41, 11, 'Progressive muscle relaxation'),
(42, 11, 'Vigorous exercise'),
(43, 11, 'Caffeine consumption'),
(44, 11, 'Late-night work'),
(45, 12, '10 minutes'),
(46, 12, '20 minutes'),
(47, 12, '30 minutes'),
(48, 12, '60 minutes'),
(49, 13, 'Diaphragmatic breathing'),
(50, 13, 'Rapid shallow breathing'),
(51, 13, 'Breath holding'),
(52, 13, 'Hyperventilation'),
(53, 14, 'Awareness'),
(54, 14, 'Avoidance'),
(55, 14, 'Anger'),
(56, 14, 'Anxiety'),
(57, 15, 'Developing healthy lifestyle habits'),
(58, 15, 'Ignoring stress completely'),
(59, 15, 'Working longer hours'),
(60, 15, 'Multitasking constantly'),
(61, 16, 'Adapting well to adversity'),
(62, 16, 'Avoiding all challenges'),
(63, 16, 'Never experiencing emotions'),
(64, 16, 'Always being happy'),
(65, 17, 'Growth mindset'),
(66, 17, 'Fixed mindset'),
(67, 17, 'Avoidance mindset'),
(68, 17, 'Perfectionist mindset'),
(69, 18, 'Strong problem-solving skills'),
(70, 18, 'Avoiding social support'),
(71, 18, 'Blaming others for problems'),
(72, 18, 'Giving up easily'),
(73, 19, 'Journaling and reflection'),
(74, 19, 'Suppressing emotions'),
(75, 19, 'Avoiding self-awareness'),
(76, 19, 'Bottling up feelings'),
(77, 20, 'Changing perspective on situations'),
(78, 20, 'Ignoring problems'),
(79, 20, 'Blaming external factors'),
(80, 20, 'Avoiding responsibility'),
(81, 21, 'Present moment awareness'),
(82, 21, 'Emptying the mind completely'),
(83, 21, 'Achieving enlightenment'),
(84, 21, 'Controlling thoughts'),
(85, 22, '5-10 minutes'),
(86, 22, '10-20 minutes'),
(87, 22, '30-45 minutes'),
(88, 22, '60+ minutes'),
(89, 23, 'Non-judgmental observation'),
(90, 23, 'Critical analysis'),
(91, 23, 'Emotional suppression'),
(92, 23, 'Future planning'),
(93, 24, 'Body awareness and relaxation'),
(94, 24, 'Physical exercise'),
(95, 24, 'Medical diagnosis'),
(96, 24, 'Pain elimination'),
(97, 25, 'Deep abdominal breathing'),
(98, 25, 'Rapid chest breathing'),
(99, 25, 'Breath holding'),
(100, 25, 'Irregular gasping'),
(101, 26, '5-6 hours'),
(102, 26, '6-7 hours'),
(103, 26, '7-9 hours'),
(104, 26, '9+ hours'),
(105, 27, 'Using electronic devices'),
(106, 27, 'Reading a book'),
(107, 27, 'Taking a warm bath'),
(108, 27, 'Light stretching'),
(109, 28, 'Below 60°F (15°C)'),
(110, 28, '60-67°F (15-19°C)'),
(111, 28, '68-72°F (20-22°C)'),
(112, 28, 'Above 75°F (24°C)'),
(113, 29, 'Melatonin'),
(114, 29, 'Adrenaline'),
(115, 29, 'Cortisol'),
(116, 29, 'Insulin'),
(117, 30, 'Time taken to fall asleep'),
(118, 30, 'Total sleep time'),
(119, 30, 'Dream duration'),
(120, 30, 'Time spent in deep sleep'),
(121, 31, 'Better mental health outcomes'),
(122, 31, 'Increased stress levels'),
(123, 31, 'Financial success'),
(124, 31, 'Physical strength'),
(125, 32, 'Communication and trust'),
(126, 32, 'Wealth and status'),
(127, 32, 'Physical appearance'),
(128, 32, 'Educational background'),
(129, 33, 'Fully focusing on and understanding the speaker'),
(130, 33, 'Planning your response while listening'),
(131, 33, 'Interrupting to share similar experiences'),
(132, 33, 'Offering immediate solutions'),
(133, 34, 'Regular check-ins and support'),
(134, 34, 'Keeping score of favors'),
(135, 34, 'Avoiding difficult conversations'),
(136, 34, 'Expecting perfection'),
(137, 35, 'Understanding and managing emotions in relationships'),
(138, 35, 'Intellectual ability'),
(139, 35, 'Physical fitness'),
(140, 35, 'Financial knowledge'),
(141, 36, 'Maintaining physical and mental health'),
(142, 36, 'Being selfish'),
(143, 36, 'Avoiding responsibilities'),
(144, 36, 'Putting others last'),
(145, 37, 'Journaling and processing feelings'),
(146, 37, 'Ignoring emotions'),
(147, 37, 'Suppressing anger'),
(148, 37, 'Bottling up stress'),
(149, 38, 'Regularly and consistently'),
(150, 38, 'Only when feeling overwhelmed'),
(151, 38, 'Once a month'),
(152, 38, 'Only on weekends'),
(153, 39, 'Communicating your limits and needs'),
(154, 39, 'Avoiding all social interactions'),
(155, 39, 'Saying yes to everything'),
(156, 39, 'Ignoring your own needs'),
(157, 40, 'Incorporating small habits into daily routine'),
(158, 40, 'Occasional extravagant activities'),
(159, 40, 'Waiting until burnout occurs'),
(160, 40, 'Only practicing when motivated');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `id` int(11) NOT NULL,
  `student_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`id`, `student_id`) VALUES
(2, 'A23CS0219');

-- --------------------------------------------------------

--
-- Table structure for table `student_progress`
--

CREATE TABLE `student_progress` (
  `id` bigint(20) NOT NULL,
  `student_id` int(11) NOT NULL,
  `module_id` bigint(20) NOT NULL,
  `completed` tinyint(1) DEFAULT 0,
  `started_at` datetime DEFAULT current_timestamp(),
  `completed_at` datetime DEFAULT NULL,
  `quiz_score` int(11) DEFAULT NULL,
  `last_accessed` datetime DEFAULT current_timestamp(),
  `quiz_viewed` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student_progress`
--

INSERT INTO `student_progress` (`id`, `student_id`, `module_id`, `completed`, `started_at`, `completed_at`, `quiz_score`, `last_accessed`, `quiz_viewed`) VALUES
(9, 2, 1, 1, '2025-12-01 18:17:19', '2025-12-02 01:52:28', NULL, '2025-12-18 14:58:56', b'1'),
(10, 2, 2, 1, '2025-12-01 20:27:27', '2025-12-18 12:43:33', NULL, '2025-12-18 19:37:18', b'1'),
(11, 2, 4, 1, '2025-12-02 01:52:36', '2025-12-02 01:52:59', 100, '2025-12-18 19:38:28', b'1'),
(12, 2, 3, 0, '2025-12-18 12:43:38', NULL, NULL, '2026-01-07 08:34:07', b'1'),
(13, 2, 7, 0, '2025-12-18 12:43:42', NULL, NULL, '2025-12-18 19:58:26', b'1'),
(14, 2, 8, 0, '2025-12-18 12:48:18', NULL, NULL, '2025-12-18 16:13:36', b'1'),
(15, 2, 5, 0, '2025-12-18 16:13:19', NULL, NULL, '2025-12-18 19:37:54', b'1'),
(16, 2, 6, 0, '2025-12-18 19:38:06', NULL, NULL, '2025-12-18 19:38:06', b'1');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `student_id` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `user_type` varchar(31) NOT NULL,
  `role` varchar(255) NOT NULL,
  `availability` varchar(255) DEFAULT 'EVERYDAY',
  `specialty` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `full_name`, `username`, `email`, `student_id`, `department`, `password`, `user_type`, `role`, `availability`, `specialty`) VALUES
(1, 'Siti', 'counselorSiti', 'siti@gmail.com', NULL, NULL, '$2a$10$Utp8PZS5gNsnSYup66K3YuKPVoyau45cxBp7abZLxs/oEg4v7dk9K', 'COUNSELOR', 'COUNSELOR', 'EVERYDAY', 'General Counseling'),
(2, 'Chuah Hui Wen', 'chuahhw', 'chwen0912@gmail.com', 'A23CS0219', 'Computer Science', '$2a$10$bPhH9EfPgWXfVbVNpxCjgOclVx9iPn50zzIboe4menRLAPuXTieDm', 'STUDENT', 'STUDENT', NULL, NULL),
(3, 'Ali', 'adminAli', 'ali@gmail.com', NULL, NULL, '$2a$10$D1Bc/XArwIiwRr9QGZLbGuiZmIV6ZIodr47S1247VtbnscjPM1am.', 'ADMIN', 'ADMIN', NULL, NULL),
(4, 'MichaelChen', 'counselorMichael', 'michael@gmail.com', NULL, NULL, '$2a$10$Utp8PZS5gNsnSYup66K3YuKPVoyau45cxBp7abZLxs/oEg4v7dk9K', 'COUNSELOR', 'COUNSELOR', 'EVERYDAY', 'Clinical Psychology'),
(5, 'SarahWilliams', 'counselorSarah', 'sarah@gmail.com', NULL, NULL, '$2a$10$Utp8PZS5gNsnSYup66K3YuKPVoyau45cxBp7abZLxs/oEg4v7dk9K', 'COUNSELOR', 'COUNSELOR', 'EVERYDAY', 'Anxiety & Depression'),
(6, 'JamesRod', 'counselorJames', 'james@gmail.com', NULL, NULL, '$2a$10$Utp8PZS5gNsnSYup66K3YuKPVoyau45cxBp7abZLxs/oEg4v7dk9K', 'COUNSELOR', 'COUNSELOR', 'EVERYDAY', 'Student Counseling');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answer_option`
--
ALTER TABLE `answer_option`
  ADD PRIMARY KEY (`id`),
  ADD KEY `question_id` (`question_id`);

--
-- Indexes for table `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK7mqcr9r2b8q83aaxdwmmr5tfq` (`counselor_id`),
  ADD KEY `FKclmx9vbph60h4vtsmow3a0t2d` (`student_id`);

--
-- Indexes for table `assessment_answer`
--
ALTER TABLE `assessment_answer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKeke7uqteruwv471e0sgecgl71` (`answer_option_id`),
  ADD KEY `FKqi8cgei8uyp08byexbeq9gknr` (`attempt_id`),
  ADD KEY `FKsqnt848aa5qdatnvox8ketckx` (`question_id`);

--
-- Indexes for table `assessment_attempt`
--
ALTER TABLE `assessment_attempt`
  ADD PRIMARY KEY (`id`),
  ADD KEY `question_set_id` (`question_set_id`),
  ADD KEY `FK9ya7nea3weq8uhgk6sbxskhxf` (`user_id`);

--
-- Indexes for table `forum_replies`
--
ALTER TABLE `forum_replies`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK80e0gv6rdqa9ifx1qd4rpkyot` (`thread_id`),
  ADD KEY `FK7beh0rlqlf62v961vhb4jdarv` (`created_by`);

--
-- Indexes for table `forum_threads`
--
ALTER TABLE `forum_threads`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKtfjbg28mrc5fy2fo7har55o8a` (`created_by`);

--
-- Indexes for table `learning_module`
--
ALTER TABLE `learning_module`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mood_entries`
--
ALTER TABLE `mood_entries`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKh84lbl3senebidgq8i6qgxpwq` (`student_id`);

--
-- Indexes for table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`id`),
  ADD KEY `question_set_id` (`question_set_id`);

--
-- Indexes for table `question_set`
--
ALTER TABLE `question_set`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `quiz_attempt`
--
ALTER TABLE `quiz_attempt`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_quiz_attempt_student` (`student_id`),
  ADD KEY `idx_quiz_attempt_module` (`module_id`);

--
-- Indexes for table `quiz_question`
--
ALTER TABLE `quiz_question`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_quiz_question_module` (`module_id`);

--
-- Indexes for table `quiz_question_options`
--
ALTER TABLE `quiz_question_options`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_quiz_options_question` (`question_id`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `student_id` (`student_id`);

--
-- Indexes for table `student_progress`
--
ALTER TABLE `student_progress`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_student_module` (`student_id`,`module_id`),
  ADD UNIQUE KEY `UKj1sdla1y1jmaltmr2w9gv8sh6` (`student_id`,`module_id`),
  ADD KEY `idx_student_progress_student` (`student_id`),
  ADD KEY `idx_student_progress_module` (`module_id`),
  ADD KEY `idx_student_module` (`student_id`,`module_id`),
  ADD KEY `idx_student_completed` (`student_id`,`completed`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answer_option`
--
ALTER TABLE `answer_option`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=192;

--
-- AUTO_INCREMENT for table `appointments`
--
ALTER TABLE `appointments`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `assessment_answer`
--
ALTER TABLE `assessment_answer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `assessment_attempt`
--
ALTER TABLE `assessment_attempt`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `forum_replies`
--
ALTER TABLE `forum_replies`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `forum_threads`
--
ALTER TABLE `forum_threads`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `learning_module`
--
ALTER TABLE `learning_module`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `mood_entries`
--
ALTER TABLE `mood_entries`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `question_set`
--
ALTER TABLE `question_set`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `quiz_attempt`
--
ALTER TABLE `quiz_attempt`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `quiz_question`
--
ALTER TABLE `quiz_question`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `quiz_question_options`
--
ALTER TABLE `quiz_question_options`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=161;

--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `student_progress`
--
ALTER TABLE `student_progress`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `answer_option`
--
ALTER TABLE `answer_option`
  ADD CONSTRAINT `answer_option_ibfk_1` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`);

--
-- Constraints for table `appointments`
--
ALTER TABLE `appointments`
  ADD CONSTRAINT `FK7mqcr9r2b8q83aaxdwmmr5tfq` FOREIGN KEY (`counselor_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKclmx9vbph60h4vtsmow3a0t2d` FOREIGN KEY (`student_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `assessment_answer`
--
ALTER TABLE `assessment_answer`
  ADD CONSTRAINT `FKeke7uqteruwv471e0sgecgl71` FOREIGN KEY (`answer_option_id`) REFERENCES `answer_option` (`id`),
  ADD CONSTRAINT `FKqi8cgei8uyp08byexbeq9gknr` FOREIGN KEY (`attempt_id`) REFERENCES `assessment_attempt` (`id`),
  ADD CONSTRAINT `FKsqnt848aa5qdatnvox8ketckx` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`);

--
-- Constraints for table `assessment_attempt`
--
ALTER TABLE `assessment_attempt`
  ADD CONSTRAINT `FK9ya7nea3weq8uhgk6sbxskhxf` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `assessment_attempt_ibfk_1` FOREIGN KEY (`question_set_id`) REFERENCES `question_set` (`id`);

--
-- Constraints for table `forum_replies`
--
ALTER TABLE `forum_replies`
  ADD CONSTRAINT `FK7beh0rlqlf62v961vhb4jdarv` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FK80e0gv6rdqa9ifx1qd4rpkyot` FOREIGN KEY (`thread_id`) REFERENCES `forum_threads` (`id`);

--
-- Constraints for table `forum_threads`
--
ALTER TABLE `forum_threads`
  ADD CONSTRAINT `FKtfjbg28mrc5fy2fo7har55o8a` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`);

--
-- Constraints for table `mood_entries`
--
ALTER TABLE `mood_entries`
  ADD CONSTRAINT `FKh84lbl3senebidgq8i6qgxpwq` FOREIGN KEY (`student_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `question_ibfk_1` FOREIGN KEY (`question_set_id`) REFERENCES `question_set` (`id`);

--
-- Constraints for table `quiz_attempt`
--
ALTER TABLE `quiz_attempt`
  ADD CONSTRAINT `FKb0vawmgxwdgwqd52nh3vmeaby` FOREIGN KEY (`student_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKmn2vb2d1pke92hn152pv4ca1s` FOREIGN KEY (`module_id`) REFERENCES `learning_module` (`id`),
  ADD CONSTRAINT `quiz_attempt_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`);

--
-- Constraints for table `quiz_question`
--
ALTER TABLE `quiz_question`
  ADD CONSTRAINT `FK3ivnjc4qnggscgbsx5lp50q7k` FOREIGN KEY (`module_id`) REFERENCES `learning_module` (`id`);

--
-- Constraints for table `quiz_question_options`
--
ALTER TABLE `quiz_question_options`
  ADD CONSTRAINT `FKg5qpv7mfnxymsw6ncm4f02ie8` FOREIGN KEY (`question_id`) REFERENCES `quiz_question` (`id`);

--
-- Constraints for table `students`
--
ALTER TABLE `students`
  ADD CONSTRAINT `students_ibfk_1` FOREIGN KEY (`id`) REFERENCES `users` (`id`);

--
-- Constraints for table `student_progress`
--
ALTER TABLE `student_progress`
  ADD CONSTRAINT `FK679hy9hhha4829swq8efg3u0w` FOREIGN KEY (`student_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `student_progress_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`),
  ADD CONSTRAINT `student_progress_ibfk_2` FOREIGN KEY (`module_id`) REFERENCES `learning_module` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
