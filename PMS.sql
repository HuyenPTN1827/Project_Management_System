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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allocation`
--

LOCK TABLES `allocation` WRITE;
/*!40000 ALTER TABLE `allocation` DISABLE KEYS */;
INSERT INTO `allocation` VALUES (1,2,'2024-11-22 00:00:00',2,'2024-11-22 00:00:00','2024-11-22',NULL,0,NULL,1,5,55,2,4),(2,2,'2024-11-22 00:00:00',2,'2024-11-22 00:00:00','2024-11-22',NULL,0,NULL,1,5,55,1,4);
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
INSERT INTO `department` VALUES (1,'EM','Executive Management',NULL,NULL,1),(2,'HR','Human Resources',NULL,1,1),(3,'F&A','Finance and Accounting',NULL,1,1),(4,'S&M','Sales and Marketing',NULL,1,1),(5,'IT','Information Technology','',1,1),(6,'R&D','Research and Development',NULL,1,1),(7,'OP','Operations',NULL,1,1),(8,'Legal','Legal',NULL,1,1),(9,'Admin','Administrative ',NULL,1,1),(10,'CS','Customer Service','',4,0),(15,'TIMD','Test insert Modal Done','Test insert Modal done',5,0),(16,'TIM','Test insert Modal ','',6,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dept_user`
--

LOCK TABLES `dept_user` WRITE;
/*!40000 ALTER TABLE `dept_user` DISABLE KEYS */;
INSERT INTO `dept_user` VALUES (61,1,5,'2024-11-21','2024-11-23',0,1),(63,2,7,'2024-11-21',NULL,1,3),(64,56,5,'2024-11-24',NULL,1,7),(65,55,5,'2024-11-24',NULL,1,7);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue`
--

LOCK TABLES `issue` WRITE;
/*!40000 ALTER TABLE `issue` DISABLE KEYS */;
INSERT INTO `issue` VALUES (1,55,'2024-11-22 00:00:00',1,1,56,'2024-11-24',0,'Issue 1',39,2,NULL),(2,55,'2024-11-22 00:00:00',2,2,55,'2024-11-24',0,'Issue 2',40,1,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `milestone`
--

LOCK TABLES `milestone` WRITE;
/*!40000 ALTER TABLE `milestone` DISABLE KEYS */;
INSERT INTO `milestone` VALUES (1,NULL,'2024-10-06 00:00:00','Iterarion 1',NULL,4,'2024-10-06',0,NULL,NULL,2,1),(2,NULL,'2024-10-06 00:00:00','Iterarion 2',NULL,3,'2024-10-20',1,NULL,NULL,2,2),(3,NULL,'2024-10-06 00:00:00','Iterarion 3',NULL,2,'2024-11-03',2,NULL,NULL,2,3),(4,NULL,'2024-10-06 00:00:00','Iterarion 4',NULL,1,'2024-11-17',2,NULL,NULL,2,4),(5,NULL,'2024-10-06 00:00:00','Iter 1',NULL,4,'2024-10-06',0,NULL,NULL,1,1),(6,NULL,'2024-10-06 00:00:00','Iter 2',NULL,3,'2024-10-20',1,NULL,NULL,1,2),(7,NULL,'2024-10-06 00:00:00','Iter 3',NULL,2,'2024-11-03',2,NULL,NULL,1,3),(8,NULL,'2024-10-06 00:00:00','Iter 4',NULL,1,'2024-11-17',2,NULL,NULL,1,4);
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
  PRIMARY KEY (`id`),
  KEY `project_ibfk_1` (`type_id`),
  KEY `project_ibfk_2` (`department_id`),
  KEY `project_ibfk_2_idx` (`user_id`),
  CONSTRAINT `project_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `project_type` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `project_ibfk_2` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `project_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'Project Management System','PMS',5.00,'2024-10-15',NULL,NULL,'2024-10-15 00:00:00',1,2,5,NULL),(2,'Project 1','PJ1',5.00,'2024-10-19',NULL,NULL,'2024-10-19 00:00:00',2,1,6,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_type_setting`
--

LOCK TABLES `project_type_setting` WRITE;
/*!40000 ALTER TABLE `project_type_setting` DISABLE KEYS */;
INSERT INTO `project_type_setting` VALUES (1,'Project Manager','Project Role','1',1,1,'Test update',2),(2,'BA','Project Role','2',2,1,NULL,2),(3,'Developer','Project Role','3',3,1,NULL,2),(4,'Tester','Project Role','4',4,1,NULL,2),(6,'Project Role','','Project Role',1,1,'',2),(7,'Test insert done','','Test insert done',8,0,'Test insert done',22),(8,'Test update','Test insert done','Test update',7,0,'Test update',22),(9,'Scope Status ','','Scope Status ',1,1,'',2),(10,'Scope Complexity ','','Scope Complexity ',1,1,'Scope Complexity ',2),(11,'Not Started','Scope Status ','Not Started',3,1,'',2),(12,'In Progress','Scope Status ','In Progress',2,1,'',2),(13,'Completed','Scope Status ','Completed',4,1,'',2),(14,'Blocked','Scope Status ','Blocked',1,1,'',2),(15,'Simple','Scope Complexity ','Simple',3,1,'',2),(16,'Medium','Scope Complexity ','Medium',2,1,'',2),(17,'Complex','Scope Complexity ','Complex',1,1,'',2);
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
INSERT INTO `setting` VALUES (1,'Admin','User Role','1',1,1,NULL),(2,'PMO Manager','User Role','2',2,1,NULL),(3,'Department Manager','User Role','3',3,1,NULL),(4,'Project Manager','User Role','4',4,1,NULL),(5,'Project QA','User Role','5',5,1,NULL),(6,'Team Leader','User Role','6',6,1,NULL),(7,'Member','User Role','7',7,1,NULL),(8,'Critical','Defect Severity','Critical',1,1,'AAAA'),(9,'High','Defect Severity','High',2,1,'Test insert'),(10,'Medium','Defect Severity','Medium',3,1,''),(11,'Low','Defect Severity','Low',4,1,'Test insert'),(29,'User Role',NULL,'User Role',1,1,NULL),(33,'Defect Severity',NULL,'Defect Severity',1,1,''),(36,'Issue Type','','Issue',1,1,'Type of issues'),(37,'Technical Issues','Issue Type','Technical Issues',1,1,''),(38,'Management Issues','Issue Type','Management Issues',2,1,''),(39,'People Issues','Issue Type','People Issues',3,1,''),(40,'Requirement Issues','Issue Type','Requirement Issues',4,1,''),(41,'Quality Issues','Issue Type','Quality Issues',5,1,''),(42,'Development Environment Issues','Issue Type','Development Environment Issues',6,1,''),(43,'Stakeholder Issues','Issue Type','Stakeholder Issues',7,1,'');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timesheet`
--

LOCK TABLES `timesheet` WRITE;
/*!40000 ALTER TABLE `timesheet` DISABLE KEYS */;
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
  `status` int NOT NULL DEFAULT '3',
  `role_id` int DEFAULT NULL,
  `avatar` longtext,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `user_ibfk_1` (`role_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `setting` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Admin','admin','admin@gmail.com','0904235978','Abc@123','',1,1,NULL),(2,'Phạm Ngọc Huyền','huyenptn','huyenptnhe160769@gmail.com','0355235054','Abc@123','',1,3,NULL),(3,'Đỗ Hải Long','longdh','longdh@gmail.com','0366904861','Abc@123','',3,2,NULL),(55,'Nguyễn Văn A','anv','anv@gmail.com','','Abc@123','',1,7,NULL),(56,'Nguyễn Văn B','bnv','bnv@gmail.com','','Abc@123','',1,7,NULL);
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
  CONSTRAINT `user_type_ibfk_3` FOREIGN KEY (`role_id`) REFERENCES `project_type_setting` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_type`
--

LOCK TABLES `user_type` WRITE;
/*!40000 ALTER TABLE `user_type` DISABLE KEYS */;
INSERT INTO `user_type` VALUES (1,2,2,'2024-10-10',NULL,1,3),(2,3,2,'2024-10-13',NULL,1,1);
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

-- Dump completed on 2024-11-25  3:18:07
