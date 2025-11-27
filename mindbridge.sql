-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 24, 2025 at 01:22 PM
-- Server version: 10.1.25-MariaDB
-- PHP Version: 5.6.31

CREATE DATABASE mindbridge;

-- Select it
USE mindbridge;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
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
-- Table structure for table `mood_entries`
--

CREATE TABLE `mood_entries` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `entry_date` date DEFAULT NULL,
  `mood` varchar(255) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `student_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mood_entries`
--

INSERT INTO `mood_entries` (`id`, `created_at`, `entry_date`, `mood`, `notes`, `student_id`) VALUES
(1, '2025-11-24 17:20:55.000000', '2025-11-24', 'Great', 'Finished all the tests', 2),
(2, '2025-11-24 17:24:43.000000', '2025-11-24', 'Awful', 'Umbrella gone', 2);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `student_id` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `user_type` varchar(31) NOT NULL,
  `role` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `full_name`, `username`, `email`, `student_id`, `department`, `password`, `user_type`, `role`) VALUES
(1, 'Siti', 'counselorSiti', 'siti@gmail.com', NULL, NULL, '$2a$10$Utp8PZS5gNsnSYup66K3YuKPVoyau45cxBp7abZLxs/oEg4v7dk9K', 'COUNSELOR', 'COUNSELOR'),
(2, 'Chuah Hui Wen', 'chuahhw', 'chwen0912@gmail.com', 'A23CS0219', 'Computer Science', '$2a$10$bPhH9EfPgWXfVbVNpxCjgOclVx9iPn50zzIboe4menRLAPuXTieDm', 'STUDENT', 'STUDENT'),
(3, 'Ali', 'adminAli', 'ali@gmail.com', NULL, NULL, '$2a$10$D1Bc/XArwIiwRr9QGZLbGuiZmIV6ZIodr47S1247VtbnscjPM1am.', 'ADMIN', 'ADMIN'),
(4, 'MichaelChen', 'counselorMichael', 'michael@gmail.com', NULL, NULL, '$2a$10$Utp8PZS5gNsnSYup66K3YuKPVoyau45cxBp7abZLxs/oEg4v7dk9K', 'COUNSELOR', 'COUNSELOR'),
(5, 'SarahWilliams', 'counselorSarah', 'sarah@gmail.com', NULL, NULL, '$2a$10$Utp8PZS5gNsnSYup66K3YuKPVoyau45cxBp7abZLxs/oEg4v7dk9K', 'COUNSELOR', 'COUNSELOR'),
(6, 'JamesRod', 'counselorJames', 'james@gmail.com', NULL, NULL, '$2a$10$Utp8PZS5gNsnSYup66K3YuKPVoyau45cxBp7abZLxs/oEg4v7dk9K', 'COUNSELOR', 'COUNSELOR');
--
-- Indexes for dumped tables
--

--
-- Indexes for table `mood_entries`
--
ALTER TABLE `mood_entries`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKh84lbl3senebidgq8i6qgxpwq` (`student_id`);

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
-- AUTO_INCREMENT for table `mood_entries`
--
ALTER TABLE `mood_entries`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `mood_entries`
--
ALTER TABLE `mood_entries`
  ADD CONSTRAINT `FKh84lbl3senebidgq8i6qgxpwq` FOREIGN KEY (`student_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
