-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 06, 2025 at 08:28 AM
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
  `student_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `appointments`
--

INSERT INTO `appointments` (`id`, `date`, `notes`, `status`, `time`, `type`, `counselor_id`, `student_id`) VALUES
(1, '2025-12-04', '', 'PENDING', '10:00:00.000000', 'IN_PERSON', 4, 2),
(2, '2025-12-12', '', 'APPROVED', '09:00:00.000000', 'IN_PERSON', 1, 2),
(3, '2025-12-22', '', 'APPROVED', '11:00:00.000000', 'IN_PERSON', 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `assessment_answer`
--

CREATE TABLE `assessment_answer` (
  `id` int(11) NOT NULL,
  `attempt_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `answer_option_id` int(11) NOT NULL,
  `score` int(11) NOT NULL
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
(2, 2, 1, '2025-12-01 21:16:27', 421, 'Normal'),
(3, 2, 1, '2025-12-01 21:17:30', 421, 'Normal'),
(4, 2, 1, '2025-12-01 21:46:03', 10, 'Normal'),
(5, 2, 1, '2025-12-05 07:59:12', 10, 'Normal');

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
(1, 'Understanding Anxiety', 'Learn about anxiety disorders', NULL, 'Mental Health Basics', 'Learn about anxiety disorders, their symptoms, and coping strategies.', '15 min', 1, 1, '• Understand core concepts and definitions\n• Learn practical coping strategies\n• Know when and how to seek help\n• Access support resources', '2025-11-29 16:27:56', '2025-11-29 16:27:56', NULL, NULL),
(2, 'Depression Awareness', 'Understanding depression', NULL, 'Mental Health Basics', 'Understanding depression: symptoms, causes, and seeking help.', '18 min', 2, 1, '• Recognize symptoms of depression\n• Understand causes and risk factors\n• Learn about treatment options\n• Know when to seek professional help', '2025-11-29 16:27:56', '2025-11-29 16:27:56', NULL, NULL),
(3, 'Stress Management Techniques', 'Discover stress management techniques', NULL, 'Coping Skills', 'Discover practical techniques to manage stress in your daily life.', '20 min', 3, 1, '• Identify stress triggers\n• Learn relaxation techniques\n• Develop healthy coping mechanisms\n• Create a stress management plan', '2025-11-29 16:27:56', '2025-11-29 16:27:56', NULL, NULL),
(4, 'Building Resilience', 'Build mental toughness', NULL, 'Personal Growth', 'Learn how to bounce back from challenges and build mental toughness.', '25 min', 4, 1, '• Understand resilience factors\n• Develop problem-solving skills\n• Learn adaptive thinking patterns\n• Build support networks', '2025-11-29 16:27:56', '2025-11-29 16:27:56', NULL, NULL),
(5, 'Mindfulness & Meditation', 'Introduction to mindfulness', NULL, 'Coping Skills', 'Introduction to mindfulness practices and meditation techniques.', '22 min', 5, 1, '• Learn mindfulness basics\n• Practice meditation techniques\n• Apply mindfulness in daily life\n• Understand benefits for mental health', '2025-11-29 16:27:56', '2025-11-29 16:27:56', NULL, NULL),
(6, 'Sleep Hygiene', 'Optimize your sleep', NULL, 'Wellness', 'Optimize your sleep for better mental and physical health.', '15 min', 6, 1, '• Understand sleep cycles\n• Create optimal sleep environment\n• Develop bedtime routines\n• Address common sleep issues', '2025-11-29 16:27:56', '2025-11-29 16:27:56', NULL, NULL),
(7, 'Social Connection', 'Build meaningful connections', NULL, 'Relationships', 'The importance of social support and building meaningful connections.', '18 min', 7, 1, '• Understand social support benefits\n• Develop communication skills\n• Build healthy relationships\n• Maintain social connections', '2025-11-29 16:27:56', '2025-11-29 16:27:56', NULL, NULL),
(8, 'Self-Care Strategies', 'Develop self-care routine', NULL, 'Wellness', 'Develop a personalized self-care routine for mental wellbeing.', '20 min', 8, 1, '• Identify self-care needs\n• Create personalized routine\n• Balance different life areas\n• Maintain consistent practice', '2025-11-29 16:27:56', '2025-11-29 16:27:56', NULL, NULL);

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
(9, 2, 1, 1, '2025-12-01 18:17:19', '2025-12-02 01:52:28', NULL, '2025-12-02 06:10:19', b'1'),
(10, 2, 2, 0, '2025-12-01 20:27:27', NULL, NULL, '2025-12-02 01:25:19', b'0'),
(11, 2, 4, 1, '2025-12-02 01:52:36', '2025-12-02 01:52:59', 100, '2025-12-02 01:52:59', b'1');

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
  ADD KEY `attempt_id` (`attempt_id`),
  ADD KEY `question_id` (`question_id`),
  ADD KEY `answer_option_id` (`answer_option_id`);

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
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `assessment_answer`
--
ALTER TABLE `assessment_answer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `assessment_attempt`
--
ALTER TABLE `assessment_attempt`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

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
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

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
  ADD CONSTRAINT `assessment_answer_ibfk_1` FOREIGN KEY (`attempt_id`) REFERENCES `assessment_attempt` (`id`),
  ADD CONSTRAINT `assessment_answer_ibfk_2` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`),
  ADD CONSTRAINT `assessment_answer_ibfk_3` FOREIGN KEY (`answer_option_id`) REFERENCES `answer_option` (`id`);

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
