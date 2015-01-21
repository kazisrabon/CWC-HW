-- phpMyAdmin SQL Dump
-- version 2.10.1
-- http://www.phpmyadmin.net
-- 
-- Host: localhost
-- Generation Time: Jan 20, 2015 at 02:48 PM
-- Server version: 5.0.41
-- PHP Version: 5.2.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- 
-- Database: `android_api`
-- 
CREATE DATABASE `android_api` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `android_api`;

-- --------------------------------------------------------

-- 
-- Table structure for table `users`
-- 

CREATE TABLE `users` (
  `uid` int(11) NOT NULL auto_increment,
  `unique_id` varchar(23) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `encrypted_password` varchar(80) NOT NULL,
  `salt` varchar(10) NOT NULL,
  `created_at` datetime default NULL,
  `updated_at` datetime default NULL,
  PRIMARY KEY  (`uid`),
  UNIQUE KEY `unique_id` (`unique_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

-- 
-- Dumping data for table `users`
-- 

INSERT INTO `users` (`uid`, `unique_id`, `name`, `email`, `encrypted_password`, `salt`, `created_at`, `updated_at`) VALUES 
(1, '54b59a3fa0d084.43030873', 'kazisrabon', 'bit0424@iit.du.ac.bd', 'bOlAisYX2wDTPIkbmY2y3R1RlHgzMzFjYmVmNGE4', '331cbef4a8', '2015-01-13 14:20:47', NULL),
(2, '54b870fe8fcee7.85019801', 'Shafiq ullah sohag', 'bit0404@iit.du.ac.bd', 'iPGxQZ89VkOEbbTKDSNymDTkZ7FmY2NmZTRjYmE1', 'fccfe4cba5', '2015-01-15 18:01:34', NULL);
