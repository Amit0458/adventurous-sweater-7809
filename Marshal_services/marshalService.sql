-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: system_engineers_department
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `complains`
--

DROP TABLE IF EXISTS `complains`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `complains` (
  `reg_date` date NOT NULL,
  `Status` varchar(12) DEFAULT 'Panding',
  `comp_desc` varchar(255) NOT NULL,
  `closing_date` date DEFAULT NULL,
  `raised_by` int NOT NULL,
  `assign_to` int DEFAULT NULL,
  `comp_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`comp_id`),
  KEY `raised_by` (`raised_by`),
  KEY `complains_ibfk_2` (`assign_to`),
  CONSTRAINT `complains_ibfk_1` FOREIGN KEY (`raised_by`) REFERENCES `employees` (`emp_id`) ON DELETE CASCADE,
  CONSTRAINT `complains_ibfk_2` FOREIGN KEY (`assign_to`) REFERENCES `employees` (`emp_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complains`
--

LOCK TABLES `complains` WRITE;
/*!40000 ALTER TABLE `complains` DISABLE KEYS */;
INSERT INTO `complains` VALUES ('2023-02-25','Complete','dont Know','2023-02-26',4,1,2),('2023-02-25','Panding','dont Know',NULL,4,1,3),('2023-02-25','Panding','dont Know',NULL,4,1,4),('2023-02-25','Panding','dont Know',NULL,4,1,5),('2023-02-25','Working','dont Know','2023-02-25',4,1,6),('2023-02-26','Complete','','2023-02-26',6,9,7),('2023-02-26','Panding','',NULL,6,NULL,8),('2023-02-26','Panding','SOFTWARE',NULL,6,NULL,9),('2023-02-26','Panding','',NULL,6,NULL,10),('2023-02-26','Panding','Softewarefailure',NULL,4,NULL,11),('2023-02-26','Panding','SOMETHING ',NULL,3,1,12),('2023-02-26','Panding','cheking a problem ',NULL,7,NULL,13),('2023-02-26','Panding','somwthing going wrong ',NULL,4,NULL,14);
/*!40000 ALTER TABLE `complains` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `dep_id` int NOT NULL,
  `dep_name` varchar(24) DEFAULT NULL,
  PRIMARY KEY (`dep_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'Analyst'),(2,'Research'),(3,'Sales'),(4,'Acounting'),(5,'Frontend'),(6,'Backend'),(7,'Database_Expert'),(8,'HR'),(9,'Manager'),(10,'HOD'),(11,'Cleark');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `emp_id` int NOT NULL,
  `emp_name` varchar(24) NOT NULL,
  `job_name` varchar(20) NOT NULL,
  `manager_id` int DEFAULT NULL,
  `joining_date` date NOT NULL,
  `salary` decimal(10,2) DEFAULT NULL,
  `dep_id` int DEFAULT NULL,
  `email` varchar(24) NOT NULL,
  `phone` varchar(14) NOT NULL,
  `Country` varchar(24) NOT NULL,
  `Country_code` varchar(24) NOT NULL,
  PRIMARY KEY (`emp_id`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `phone_2` (`phone`),
  KEY `dep_id` (`dep_id`),
  KEY `manager_id` (`manager_id`),
  CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`dep_id`) REFERENCES `department` (`dep_id`) ON DELETE SET NULL,
  CONSTRAINT `employees_ibfk_2` FOREIGN KEY (`manager_id`) REFERENCES `employees` (`emp_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,'Ragbhbir Singh Bhamrah','Frontend Expert',NULL,'2023-02-03',70000.00,5,'raghbir@gmail.com','7602480352','India','+91'),(2,'Amit Chakraborty','Java Backend',NULL,'2022-08-29',110000.00,10,'amitkumar@gmail.com','9800255068','India','+91'),(3,'Ishan Yadav','Admin',NULL,'2020-10-26',150000.00,9,'admin','7602656867','India','+91'),(4,'MALIK REHAN MERANIK','Frontend',1,'2022-01-01',65000.00,5,'malikrehan@gmail.com','8978675432','India','+91'),(5,'Neelkanta Rao','Frontend',1,'2022-01-08',55000.00,5,'nealkantarao@gmail.com','8656754320','India','+91'),(6,'Akash Gaurav','Frontend',1,'2022-02-03',55000.00,5,'akashgaurav@gmail.com','9933547832','India','+91'),(7,'Jay Itkal','accountant',NULL,'2021-04-06',75000.00,4,'jay@gmail.com','8796875699','India','+91'),(8,'Akash Roy','Sales',NULL,'2021-03-02',35000.00,4,'akash@gmail.com','7602485699','India','+91'),(9,'Rohit Mahanta','Data operator',NULL,'2022-07-06',25000.00,4,'rohit@gmail.com','9635875699','India','+91'),(10,'Biswajit Singh','Data Analyist',NULL,'2021-03-01',65000.00,4,'sujit@gmail.com','9635948425','India','+91');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `engineers`
--

DROP TABLE IF EXISTS `engineers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `engineers` (
  `emp_id` int NOT NULL,
  `user_name` varchar(24) NOT NULL,
  `password` varchar(16) NOT NULL,
  `category` varchar(12) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`emp_id`),
  UNIQUE KEY `email` (`user_name`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `email_2` (`user_name`),
  CONSTRAINT `engineers_ibfk_2` FOREIGN KEY (`emp_id`) REFERENCES `employees` (`emp_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `engineers`
--

LOCK TABLES `engineers` WRITE;
/*!40000 ALTER TABLE `engineers` DISABLE KEYS */;
INSERT INTO `engineers` VALUES (8,'abc','abc','Software',6),(9,'test','12345','Hardware',4);
/*!40000 ALTER TABLE `engineers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hod`
--

DROP TABLE IF EXISTS `hod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hod` (
  `emp_Id` int NOT NULL,
  `name` varchar(30) NOT NULL,
  `email` varchar(24) NOT NULL,
  `password` varchar(16) NOT NULL,
  `incharge_of` varchar(12) NOT NULL,
  PRIMARY KEY (`emp_Id`),
  UNIQUE KEY `email` (`email`),
  CONSTRAINT `hod_ibfk_1` FOREIGN KEY (`emp_Id`) REFERENCES `employees` (`emp_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hod`
--

LOCK TABLES `hod` WRITE;
/*!40000 ALTER TABLE `hod` DISABLE KEYS */;
INSERT INTO `hod` VALUES (1,'Ragbhbir Singh Bhamrah','raghbir@gmail.com','ragh12345','hardware'),(2,'Amit Chakraborty','amitkumar51997@gmail.com','2648','Software'),(3,'Ishan Yadav','admin','admin','hardware');
/*!40000 ALTER TABLE `hod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regemployees`
--

DROP TABLE IF EXISTS `regemployees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `regemployees` (
  `emp_id` int NOT NULL,
  `usrename` varchar(24) NOT NULL,
  `password` varchar(24) NOT NULL,
  `rigister_date` date NOT NULL,
  PRIMARY KEY (`emp_id`),
  UNIQUE KEY `usrename` (`usrename`),
  CONSTRAINT `regemployees_ibfk_1` FOREIGN KEY (`emp_id`) REFERENCES `employees` (`emp_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regemployees`
--

LOCK TABLES `regemployees` WRITE;
/*!40000 ALTER TABLE `regemployees` DISABLE KEYS */;
INSERT INTO `regemployees` VALUES (1,'abc','abc','2023-02-26'),(4,'test','test','2023-02-25'),(6,'akash','12345','2023-02-26'),(7,'jay','jay','2023-02-26');
/*!40000 ALTER TABLE `regemployees` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-26 23:52:16
