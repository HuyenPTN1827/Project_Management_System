CREATE DATABASE  IF NOT EXISTS `pms` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pms`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: pms
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `allocation`
--

DROP TABLE IF EXISTS `allocation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `allocation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_by` int DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_by` int DEFAULT NULL,
  `last_updated` datetime NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `effort_rate` int DEFAULT '0',
  `description` text,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `dept_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `project_id` int DEFAULT NULL,
  `project_role` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `allocation_ibfk_3` (`user_id`),
  KEY `allocation_ibfk_1` (`project_id`),
  KEY `allocation_ibfk_2_idx` (`project_role`),
  KEY `allocation_ibfk_4_idx` (`dept_id`),
  KEY `allocation_ibfk_5_idx` (`created_by`),
  KEY `allocation_ibfk_6_idx` (`updated_by`),
  CONSTRAINT `allocation_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `allocation_ibfk_2` FOREIGN KEY (`project_role`) REFERENCES `project_type_setting` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `allocation_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `allocation_ibfk_4` FOREIGN KEY (`dept_id`) REFERENCES `department` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `allocation_ibfk_5` FOREIGN KEY (`created_by`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `allocation_ibfk_6` FOREIGN KEY (`updated_by`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allocation`
--

LOCK TABLES `allocation` WRITE;
/*!40000 ALTER TABLE `allocation` DISABLE KEYS */;
INSERT INTO `allocation` VALUES (1,2,'2024-11-22 00:00:00',2,'2024-11-22 00:00:00','2024-11-22',NULL,0,NULL,1,5,55,2,4),(2,2,'2024-11-22 00:00:00',55,'2024-11-29 03:13:01','2024-11-22',NULL,0,NULL,1,5,55,1,4),(3,55,'2024-11-22 00:00:00',55,'2024-11-22 00:00:00','2024-11-22',NULL,0,NULL,1,5,56,2,4),(4,58,'2024-11-27 19:47:46',55,'2024-11-29 03:19:58','2024-11-17',NULL,0,NULL,1,4,60,4,1),(5,58,'2024-11-27 19:52:12',55,'2024-11-29 03:20:04','2024-11-18',NULL,0,NULL,1,4,60,5,1),(6,55,'2024-11-29 18:03:34',NULL,'2024-11-29 18:03:34','2024-11-29',NULL,56,'',1,5,57,1,3),(7,55,'2024-11-29 21:38:53',55,'2024-11-30 18:48:40','2024-11-20','2024-11-27',45,'insert done',1,5,56,1,2),(8,55,'2024-11-29 22:11:26',NULL,'2024-11-29 22:11:26','2024-11-11','2024-11-01',4,'',1,4,60,1,3),(9,55,'2024-11-30 01:54:04',NULL,'2024-11-30 01:54:04','2024-11-12','2024-10-28',40,'',1,6,2,1,4),(10,55,'2024-11-30 13:54:05',55,'2024-12-01 04:07:36','2024-11-20','2024-11-30',6,'',0,5,55,1,2),(11,55,'2024-12-01 04:21:07',55,'2024-12-04 23:42:22','2024-11-19','2024-12-03',9,'Validate done',0,4,60,1,4),(12,58,'2024-12-02 19:17:01',NULL,'2024-12-02 19:17:01','2024-12-02',NULL,0,NULL,1,7,60,6,1),(13,58,'2024-12-02 19:18:26',NULL,'2024-12-02 19:18:26','2024-12-02',NULL,0,NULL,1,7,60,7,1),(14,1,'2024-12-06 21:32:47',NULL,'2024-12-06 21:32:47','2024-12-06',NULL,53,'',1,5,58,4,19);
/*!40000 ALTER TABLE `allocation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `defect`
--

DROP TABLE IF EXISTS `defect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `defect` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_by` int DEFAULT NULL,
  `last_updated` datetime NOT NULL,
  `name` varchar(50) NOT NULL,
  `type` varchar(45) NOT NULL,
  `project_id` int DEFAULT NULL,
  `milestone_id` int DEFAULT NULL,
  `package_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `severity` int DEFAULT NULL,
  `priority` int NOT NULL,
  `deadline` date NOT NULL,
  `status` int NOT NULL,
  `details` text,
  `attached_file` longtext,
  `solution` text,
  PRIMARY KEY (`id`),
  KEY `defect_ibfk_1_idx` (`created_by`),
  KEY `defect_ibfk_1_idx1` (`project_id`),
  KEY `defect_ibfk_4_idx` (`package_id`),
  KEY `defect_ibfk_3_idx` (`milestone_id`),
  KEY `defect_ibfk_5_idx` (`user_id`),
  KEY `defect_ibfk_6_idx` (`severity`),
  CONSTRAINT `defect_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `defect_ibfk_2` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `defect_ibfk_3` FOREIGN KEY (`milestone_id`) REFERENCES `milestone` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `defect_ibfk_4` FOREIGN KEY (`package_id`) REFERENCES `work_package` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `defect_ibfk_5` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `defect_ibfk_6` FOREIGN KEY (`severity`) REFERENCES `setting` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `defect`
--

LOCK TABLES `defect` WRITE;
/*!40000 ALTER TABLE `defect` DISABLE KEYS */;
/*!40000 ALTER TABLE `defect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(45) NOT NULL,
  `name` varchar(50) NOT NULL,
  `details` text,
  `parent` int DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `department_ibfk_1_idx` (`parent`),
  CONSTRAINT `department_ibfk_1` FOREIGN KEY (`parent`) REFERENCES `department` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'EM','Executive Management',NULL,NULL,1),(2,'HR','Human Resources',NULL,1,1),(3,'F&A','Finance and Accounting',NULL,1,1),(4,'S&M','Sales and Marketing',NULL,1,1),(5,'IT','Information Technology','',1,1),(6,'R&D','Research and Development',NULL,1,1),(7,'OP','Operations',NULL,1,1),(8,'Legal','Legal',NULL,1,1),(9,'Admin','Administrative ',NULL,1,1),(10,'CS','Customer Service','',4,0),(15,'TIMD','Test insert Modal Done','Test insert Modal done',2,0),(16,'TIM','Test insert Modal ','',6,0);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dept_user`
--

DROP TABLE IF EXISTS `dept_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dept_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `dept_id` int DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `dept_user_ibfk_1_idx` (`user_id`),
  KEY `dept_user_ibfk_2_idx` (`dept_id`),
  KEY `dept_user_ibfk_3_idx` (`role_id`),
  CONSTRAINT `dept_user_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `dept_user_ibfk_2` FOREIGN KEY (`dept_id`) REFERENCES `department` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `dept_user_ibfk_3` FOREIGN KEY (`role_id`) REFERENCES `setting` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dept_user`
--

LOCK TABLES `dept_user` WRITE;
/*!40000 ALTER TABLE `dept_user` DISABLE KEYS */;
INSERT INTO `dept_user` VALUES (61,1,5,'2024-11-21','2024-11-23',0,2),(63,2,7,'2024-11-21','2024-11-27',0,3),(64,56,5,'2024-11-24',NULL,1,5),(65,55,5,'2024-11-24','2024-11-27',0,5),(66,1,5,'2024-11-26',NULL,1,NULL),(67,58,5,'2024-11-27',NULL,1,NULL),(68,57,5,'2024-11-27','2024-12-06',0,NULL),(69,59,3,NULL,NULL,0,NULL),(70,3,6,NULL,NULL,0,NULL),(71,2,6,'2024-11-27','2024-12-06',0,3),(72,55,5,'2024-11-27',NULL,1,NULL),(73,60,4,'2024-11-27','2024-11-29',0,NULL),(74,60,4,'2024-11-29',NULL,1,NULL),(75,2,5,'2024-12-05','2024-12-06',0,3),(76,2,5,'2024-12-06','2024-12-06',0,NULL),(77,2,3,'2024-12-06','2024-12-06',0,NULL),(78,2,5,'2024-12-06',NULL,1,NULL),(79,57,4,'2024-12-06',NULL,1,NULL),(80,64,4,NULL,NULL,0,NULL);
/*!40000 ALTER TABLE `dept_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eval_criteria`
--

DROP TABLE IF EXISTS `eval_criteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eval_criteria` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  `weight` decimal(5,2) NOT NULL,
  `value` decimal(5,2) NOT NULL,
  `description` text,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `phase_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `eval_criteria_ibfk_1_idx` (`phase_id`),
  CONSTRAINT `eval_criteria_ibfk_1` FOREIGN KEY (`phase_id`) REFERENCES `project_phase` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eval_criteria`
--

LOCK TABLES `eval_criteria` WRITE;
/*!40000 ALTER TABLE `eval_criteria` DISABLE KEYS */;
INSERT INTO `eval_criteria` VALUES (1,'Criteria 1','Quality',80.00,0.00,NULL,1,1),(2,'Criteria 2','Cost',70.00,0.00,NULL,1,2),(3,'Criteria 3','Delivery',60.00,0.00,NULL,1,3),(4,'Criteria 4','Qualitu',60.00,0.00,NULL,1,4),(5,'Testing','Qualitu',20.00,0.00,'Testing',0,4),(6,'Test update done','Cost',15.00,0.00,'Test update done',0,3);
/*!40000 ALTER TABLE `eval_criteria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue`
--

DROP TABLE IF EXISTS `issue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `issue` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_by` int DEFAULT NULL,
  `last_updated` datetime NOT NULL,
  `milestone_id` int DEFAULT NULL,
  `work_package` int DEFAULT NULL,
  `assignee` int DEFAULT NULL,
  `deadline` date DEFAULT NULL,
  `status` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `type` int DEFAULT NULL,
  `project_id` int DEFAULT NULL,
  `details` text,
  PRIMARY KEY (`id`),
  KEY `issue_ibfk_1_idx` (`project_id`),
  KEY `issue_ibfk_2_idx` (`created_by`),
  KEY `issue_ibfk_3_idx` (`milestone_id`),
  KEY `issue_ibfk_4_idx` (`work_package`),
  KEY `issue_ibfk_5_idx` (`assignee`),
  KEY `issue_ibfk_6_idx` (`type`),
  CONSTRAINT `issue_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `issue_ibfk_2` FOREIGN KEY (`created_by`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `issue_ibfk_3` FOREIGN KEY (`milestone_id`) REFERENCES `milestone` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `issue_ibfk_4` FOREIGN KEY (`work_package`) REFERENCES `work_package` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `issue_ibfk_5` FOREIGN KEY (`assignee`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `issue_ibfk_6` FOREIGN KEY (`type`) REFERENCES `setting` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue`
--

LOCK TABLES `issue` WRITE;
/*!40000 ALTER TABLE `issue` DISABLE KEYS */;
INSERT INTO `issue` VALUES (1,55,'2024-11-22 00:00:00',1,1,56,'2024-11-24',0,'Issue 1',39,2,NULL),(2,56,'2024-12-06 21:48:38',5,2,55,'2024-11-24',3,'Issue 2',40,1,''),(3,56,'2024-11-26 08:22:06',1,NULL,2,'2024-11-27',1,'Issue 3',41,3,NULL),(4,55,'2024-11-26 13:23:21',4,NULL,56,'2024-11-29',1,'Issue test insert',41,2,'Issue test done'),(5,55,'2024-12-06 21:48:31',7,NULL,55,'2024-11-28',1,'Issue update done',37,1,''),(6,55,'2024-11-29 00:29:00',4,NULL,56,'2024-11-12',3,'Issue test',43,2,''),(7,55,'2024-12-06 21:50:48',4,NULL,56,'2024-11-29',3,'Issue test',43,2,''),(8,55,'2024-12-06 21:51:10',4,NULL,56,'2024-11-30',3,'Issue test',43,2,''),(9,55,'2024-12-06 21:48:03',4,NULL,56,'2024-11-30',2,'Issue test',43,2,''),(10,55,'2024-11-29 00:29:42',8,NULL,55,'2024-11-30',2,'Issue test 1',38,1,''),(11,55,'2024-11-29 00:29:47',3,NULL,55,'2024-11-29',2,'Issue test 2',39,2,''),(12,58,'2024-12-06 21:51:27',9,NULL,58,'2024-12-06',2,'Issue 3',42,4,''),(13,58,'2024-12-06 21:39:40',9,NULL,60,'2024-12-10',0,'Issue 4',37,4,''),(14,58,'2024-12-07 03:42:11',9,NULL,58,'2024-12-13',1,'Issue 4',41,4,'');
/*!40000 ALTER TABLE `issue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `milestone`
--

DROP TABLE IF EXISTS `milestone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `milestone` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_by` int DEFAULT NULL,
  `last_updated` datetime NOT NULL,
  `name` varchar(50) NOT NULL,
  `parent_milestone` int DEFAULT NULL,
  `priority` int NOT NULL,
  `target_date` date NOT NULL,
  `status` int NOT NULL,
  `actual_date` date DEFAULT NULL,
  `details` text,
  `project_id` int DEFAULT NULL,
  `phase_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `milestone_ibfk_1` (`project_id`),
  KEY `milestone_ibfk_2` (`phase_id`),
  KEY `milestone_ibfk_2_idx` (`created_by`),
  CONSTRAINT `milestone_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `milestone_ibfk_2` FOREIGN KEY (`phase_id`) REFERENCES `project_phase` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `milestone_ibfk_3` FOREIGN KEY (`created_by`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `milestone`
--

LOCK TABLES `milestone` WRITE;
/*!40000 ALTER TABLE `milestone` DISABLE KEYS */;
INSERT INTO `milestone` VALUES (1,NULL,'2024-10-06 00:00:00','Iterarion 1',NULL,4,'2024-10-06',0,NULL,NULL,2,1),(2,NULL,'2024-10-06 00:00:00','Iterarion 2',NULL,3,'2024-10-20',1,NULL,NULL,2,2),(3,NULL,'2024-10-06 00:00:00','Iterarion 3',NULL,2,'2024-11-03',2,NULL,NULL,2,3),(4,NULL,'2024-10-06 00:00:00','Iterarion 4',NULL,1,'2024-11-17',2,NULL,NULL,2,4),(5,NULL,'2024-10-06 00:00:00','Iter 1',NULL,4,'2024-10-06',0,NULL,NULL,1,1),(6,NULL,'2024-10-06 00:00:00','Iter 2',NULL,3,'2024-10-20',1,NULL,NULL,1,2),(7,NULL,'2024-10-06 00:00:00','Iter 3',NULL,2,'2024-11-03',2,NULL,NULL,1,3),(8,NULL,'2024-10-06 00:00:00','Iter 4',NULL,1,'2024-11-17',2,NULL,NULL,1,4),(9,58,'2024-11-27 19:47:45','Project 3 - Main Milestone',NULL,1,'2024-12-07',0,NULL,NULL,4,NULL),(10,58,'2024-11-27 19:52:12','Project 4 - Main Milestone',NULL,1,'2024-12-07',0,NULL,NULL,5,NULL),(11,58,'2024-12-02 19:17:01','Project Test - Main Milestone',NULL,1,'2024-11-17',0,NULL,NULL,6,NULL),(12,58,'2024-12-02 19:18:26','Project Test - Main Milestone',NULL,1,'2024-11-17',0,NULL,NULL,7,NULL);
/*!40000 ALTER TABLE `milestone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `milestone_package`
--

DROP TABLE IF EXISTS `milestone_package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `milestone_package` (
  `id` int NOT NULL AUTO_INCREMENT,
  `milestone_id` int DEFAULT NULL,
  `package_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `milestone_package_ibfk_1` (`milestone_id`),
  KEY `milestone_package_ibfk_2` (`package_id`),
  CONSTRAINT `milestone_package_ibfk_1` FOREIGN KEY (`milestone_id`) REFERENCES `milestone` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `milestone_package_ibfk_2` FOREIGN KEY (`package_id`) REFERENCES `work_package` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `milestone_package`
--

LOCK TABLES `milestone_package` WRITE;
/*!40000 ALTER TABLE `milestone_package` DISABLE KEYS */;
/*!40000 ALTER TABLE `milestone_package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `code` varchar(45) NOT NULL,
  `estimated_effort` decimal(5,2) DEFAULT NULL,
  `start_date` date NOT NULL,
  `details` text,
  `end_date` date DEFAULT NULL,
  `last_updated` datetime NOT NULL,
  `status` int NOT NULL,
  `type_id` int DEFAULT NULL,
  `department_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `biz_term` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `project_ibfk_1` (`type_id`),
  KEY `project_ibfk_2` (`department_id`),
  KEY `project_ibfk_2_idx` (`user_id`),
  KEY `project_ibfk_4_idx` (`biz_term`),
  CONSTRAINT `project_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `project_type` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `project_ibfk_2` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `project_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `project_ibfk_4` FOREIGN KEY (`biz_term`) REFERENCES `setting` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'Project Management System','PMS',5.00,'2024-10-15',NULL,NULL,'2024-10-15 00:00:00',1,2,5,NULL,NULL),(2,'Project 1','PJ1',5.00,'2024-10-19','','2025-01-10','2024-12-04 23:41:33',2,1,6,60,NULL),(3,'Project 2','PJ2',8.00,'2024-10-19',NULL,NULL,'2024-10-19 00:00:00',1,2,5,NULL,NULL),(4,'Project 3','PRJ3',4.00,'2024-11-17','','2024-12-07','2024-11-27 19:50:00',0,3,4,60,NULL),(5,'Project 4','PRJ4',6.00,'2024-11-18','','2024-12-07','2024-11-28 15:17:50',1,4,4,60,NULL),(6,'Project Test','PRJT',6.00,'2024-11-25','Project Test','2024-11-17','2024-12-02 19:17:02',0,6,7,60,NULL),(7,'Project Test Update','PRJU',6.00,'2024-11-24','Project Test Update','2024-12-05','2024-12-02 19:22:49',0,6,4,60,NULL);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_criteria`
--

DROP TABLE IF EXISTS `project_criteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project_criteria` (
  `id` int NOT NULL AUTO_INCREMENT,
  `milestone_id` int NOT NULL,
  `project_id` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  `weight` decimal(5,2) NOT NULL,
  `value` decimal(5,2) NOT NULL,
  `description` text,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `project_criteria_ibfk_1_idx` (`milestone_id`),
  KEY `project_criteria_ibfk_2_idx` (`project_id`),
  CONSTRAINT `project_criteria_ibfk_1` FOREIGN KEY (`milestone_id`) REFERENCES `milestone` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `project_criteria_ibfk_2` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_criteria`
--

LOCK TABLES `project_criteria` WRITE;
/*!40000 ALTER TABLE `project_criteria` DISABLE KEYS */;
INSERT INTO `project_criteria` VALUES (1,1,1,'Criteria 1','Quality',80.00,0.00,NULL,1),(2,2,1,'Criteria 2','Cost',70.00,0.00,NULL,1),(3,3,1,'Criteria 3','Delivery',60.00,0.00,NULL,0),(4,4,1,'Criteria 4','Delivery',50.00,0.00,NULL,1);
/*!40000 ALTER TABLE `project_criteria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_phase`
--

DROP TABLE IF EXISTS `project_phase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project_phase` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `priority` int NOT NULL,
  `details` text,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `type_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `project_phase_ibfk_1` (`type_id`),
  CONSTRAINT `project_phase_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `project_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_phase`
--

LOCK TABLES `project_phase` WRITE;
/*!40000 ALTER TABLE `project_phase` DISABLE KEYS */;
INSERT INTO `project_phase` VALUES (1,'Planning',1,NULL,1,2),(2,'Design',2,NULL,1,2),(3,'Development',3,NULL,1,2),(4,'Testing',4,NULL,1,2),(5,'Test insert done',8,'Test insert done',0,2),(6,'Test update',5,'Test update',0,2),(7,'Test',5,'Test',0,22);
/*!40000 ALTER TABLE `project_phase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_type`
--

DROP TABLE IF EXISTS `project_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(45) NOT NULL,
  `name` varchar(50) NOT NULL,
  `details` text,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_type`
--

LOCK TABLES `project_type` WRITE;
/*!40000 ALTER TABLE `project_type` DISABLE KEYS */;
INSERT INTO `project_type` VALUES (1,'WF','Waterfall',NULL,1),(2,'AG','Agile ','',1),(3,'SC','Scrum','',1),(4,'VM','V-Model',NULL,1),(5,'SM','Spiral','Spiral',1),(6,'IM','Incremental',NULL,1),(7,'DO','DevOps',NULL,1),(18,'TI','Test insert','Test insert',0),(20,'TMD','Test modal done','Test modal done',0),(21,'TME','Test modal edit ','Modal edit done',0),(22,'T1','Test1','Test 1',0);
/*!40000 ALTER TABLE `project_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_type_setting`
--

DROP TABLE IF EXISTS `project_type_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project_type_setting` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `type` varchar(50) DEFAULT NULL,
  `value` varchar(50) DEFAULT NULL,
  `priority` int NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `description` text,
  `type_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `project_type_setting_ibfk_1_idx` (`type_id`),
  CONSTRAINT `project_type_setting_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `project_type` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_type_setting`
--

LOCK TABLES `project_type_setting` WRITE;
/*!40000 ALTER TABLE `project_type_setting` DISABLE KEYS */;
INSERT INTO `project_type_setting` VALUES (1,'Project Manager','Project Role','1',1,1,'Test update',NULL),(2,'BA','Project Role','2',2,1,NULL,2),(3,'Developer','Project Role','3',3,1,NULL,2),(4,'Tester','Project Role','4',4,1,NULL,2),(6,'Project Role','','Project Role',1,1,'',2),(7,'Test insert','','Test insert ',8,0,'Test insert done',22),(8,'Test update','Test insert done','Test update done',7,0,'Test update',22),(9,'Scope Status ','','Scope Status ',1,1,'',2),(10,'Scope Complexity ','','Scope Complexity ',1,1,'Scope Complexity ',2),(11,'Not Started','Scope Status ','Not Started',3,1,'',2),(12,'In Progress','Scope Status ','In Progress',2,1,'',2),(13,'Completed','Scope Status ','Completed',4,1,'',2),(14,'Blocked','Scope Status ','Blocked',1,1,'',2),(15,'Simple','Scope Complexity ','Simple',3,1,'',2),(16,'Medium','Scope Complexity ','Medium',2,1,'',2),(17,'Complex','Scope Complexity ','Complex',1,1,'',2),(18,'Project Role','','Project Role',1,1,'',3),(19,'Developer','Project Role','Developer',2,1,'',3),(20,'BA','Project Role','BA',1,1,'',3),(21,'Tester','Project Role','Tester',3,1,'',3);
/*!40000 ALTER TABLE `project_type_setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `setting`
--

DROP TABLE IF EXISTS `setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `setting` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `type` varchar(50) DEFAULT NULL,
  `value` varchar(50) DEFAULT NULL,
  `priority` int NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setting`
--

LOCK TABLES `setting` WRITE;
/*!40000 ALTER TABLE `setting` DISABLE KEYS */;
INSERT INTO `setting` VALUES (1,'User Role',NULL,'User Role',1,1,NULL),(2,'Admin','User Role','2',1,1,NULL),(3,'Department Manager','User Role','3',2,1,NULL),(4,'Project Manager','User Role','4',3,1,NULL),(5,'Member','User Role','5',4,1,NULL),(8,'Critical','Defect Severity','Critical',1,1,'AAAA'),(9,'High','Defect Severity','High',2,1,'Test insert'),(10,'Medium','Defect Severity','Medium',3,1,''),(11,'Low','Defect Severity','Low',4,1,'Test insert'),(33,'Defect Severity',NULL,'Defect Severity',1,1,''),(36,'Issue Type','','Issue',1,1,'Type of issues'),(37,'Technical Issues','Issue Type','Technical Issues',1,1,''),(38,'Management Issues','Issue Type','Management Issues',2,1,''),(39,'People Issues','Issue Type','People Issues',3,1,''),(40,'Requirement Issues','Issue Type','Requirement Issues',4,1,''),(41,'Quality Issues','Issue Type','Quality Issues',5,1,''),(42,'Development Environment Issues','Issue Type','Development Environment Issues',6,1,''),(43,'Stakeholder Issues','Issue Type','Stakeholder Issues',7,1,'');
/*!40000 ALTER TABLE `setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `topic` varchar(50) DEFAULT NULL,
  `details` text,
  `project_id` int DEFAULT NULL,
  `status` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `team_ibfk_1` (`project_id`),
  CONSTRAINT `team_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team_member`
--

DROP TABLE IF EXISTS `team_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team_member` (
  `id` int NOT NULL AUTO_INCREMENT,
  `team_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `team_member_ibfk_1_idx` (`team_id`),
  KEY `team_member_ibfk_2_idx` (`user_id`),
  CONSTRAINT `team_member_ibfk_1` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `team_member_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team_member`
--

LOCK TABLES `team_member` WRITE;
/*!40000 ALTER TABLE `team_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `team_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timesheet`
--

DROP TABLE IF EXISTS `timesheet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `timesheet` (
  `id` int NOT NULL AUTO_INCREMENT,
  `reporter` int DEFAULT NULL,
  `last_updated` datetime NOT NULL,
  `date` date DEFAULT NULL,
  `project_id` int DEFAULT NULL,
  `task` varchar(50) NOT NULL,
  `duration` decimal(5,2) NOT NULL,
  `package_id` int DEFAULT NULL,
  `process_id` int DEFAULT NULL,
  `status` int NOT NULL,
  `user_id` int DEFAULT NULL,
  `reject_reason` text,
  PRIMARY KEY (`id`),
  KEY `timesheet_ibfk_3_idx` (`package_id`),
  KEY `timesheet_ibfk_2` (`project_id`),
  KEY `timesheet_ibfk_1` (`reporter`),
  KEY `timesheet_ibfk_4_idx` (`process_id`),
  KEY `timesheet_ibfk_5_idx` (`user_id`),
  CONSTRAINT `timesheet_ibfk_1` FOREIGN KEY (`reporter`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `timesheet_ibfk_2` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `timesheet_ibfk_3` FOREIGN KEY (`package_id`) REFERENCES `work_package` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `timesheet_ibfk_4` FOREIGN KEY (`process_id`) REFERENCES `project_type_setting` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `timesheet_ibfk_5` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timesheet`
--

LOCK TABLES `timesheet` WRITE;
/*!40000 ALTER TABLE `timesheet` DISABLE KEYS */;
INSERT INTO `timesheet` VALUES (1,55,'2024-12-04 00:00:00','2024-12-04',6,'Test insert',5.00,1,9,4,55,NULL);
/*!40000 ALTER TABLE `timesheet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(50) NOT NULL,
  `username` varchar(45) NOT NULL,
  `email` varchar(50) NOT NULL,
  `mobile` varchar(10) DEFAULT NULL,
  `password` varchar(45) NOT NULL,
  `notes` text,
  `status` int NOT NULL DEFAULT '2',
  `role_id` int DEFAULT NULL,
  `avatar` longtext,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `user_ibfk_1` (`role_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `setting` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Admin','admin','admin@gmail.com','0904235978','Abc@123','',1,2,NULL),(2,'Phạm Ngọc Huyền','huyenptn','huyenptnhe160769@gmail.com','0355235054','Abc@123','',1,3,NULL),(3,'Đỗ Hải Long','longdh','longdh@gmail.com','0366904861','Abc@123','',2,5,NULL),(55,'Nguyễn Văn A','anv','anv@gmail.com','','Abc@123','',1,5,NULL),(56,'Nguyễn Văn B','bnv','bnv@gmail.com','','Abc@123','',1,5,NULL),(57,'Trần Thị C','ctt','ctt@gmail.com','','Abc@123','',1,4,NULL),(58,'Hoàng Văn D','dhv','dhv@gmail.com','','Abc@123','',1,5,NULL),(59,'Trương Thu H','htt1','htt1@gmail.com','0377956223','Abc@123','',2,5,NULL),(60,'Trần Văn Hoàng','hoangtv','hoangtv@gmail.com','','Abc@123','',1,4,NULL),(63,'Nguyễn Khánh Linh','linhnk','linhnk@gmail.com','','Abc@123',NULL,2,5,NULL),(64,'Mạc Huyền','mh1827','machuyen@gmail.com','','Abc@123','',2,3,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_type`
--

DROP TABLE IF EXISTS `user_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `type_id` int DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_type_ibfk_2` (`type_id`),
  KEY `user_type_ibfk_1` (`user_id`),
  KEY `user_type_ibfk_3_idx` (`role_id`),
  CONSTRAINT `user_type_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `user_type_ibfk_2` FOREIGN KEY (`type_id`) REFERENCES `project_type` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `user_type_ibfk_3` FOREIGN KEY (`role_id`) REFERENCES `setting` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_type`
--

LOCK TABLES `user_type` WRITE;
/*!40000 ALTER TABLE `user_type` DISABLE KEYS */;
INSERT INTO `user_type` VALUES (11,58,2,'2024-11-27',NULL,1,NULL),(12,57,2,'2024-11-27',NULL,1,NULL);
/*!40000 ALTER TABLE `user_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work_package`
--

DROP TABLE IF EXISTS `work_package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `work_package` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_by` int DEFAULT NULL,
  `last_updated` datetime NOT NULL,
  `title` varchar(50) NOT NULL,
  `complexity` int DEFAULT NULL,
  `planned_effort` int NOT NULL DEFAULT '0',
  `status` int DEFAULT '1',
  `actual_effort` int DEFAULT NULL,
  `details` text,
  `project_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `workpackage_ibfk_1` (`project_id`),
  KEY `workpackage_ibfk_2` (`user_id`),
  KEY `work_package_ibfk_3_idx` (`created_by`),
  KEY `work_package_ibfk_4_idx` (`complexity`),
  KEY `work_package_ibfk_5_idx` (`status`),
  CONSTRAINT `work_package_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `work_package_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `work_package_ibfk_3` FOREIGN KEY (`created_by`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `work_package_ibfk_4` FOREIGN KEY (`complexity`) REFERENCES `project_type_setting` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `work_package_ibfk_5` FOREIGN KEY (`status`) REFERENCES `project_type_setting` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work_package`
--

LOCK TABLES `work_package` WRITE;
/*!40000 ALTER TABLE `work_package` DISABLE KEYS */;
INSERT INTO `work_package` VALUES (1,2,'2024-11-22 00:00:00','Scope 1',16,5,12,5,NULL,2,55),(2,2,'2024-11-22 00:00:00','Scope 2',16,5,12,5,NULL,2,55);
/*!40000 ALTER TABLE `work_package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'pms'
--

--
-- Dumping routines for database 'pms'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-07  3:54:17
