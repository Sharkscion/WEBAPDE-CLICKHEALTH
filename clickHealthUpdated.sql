CREATE DATABASE  IF NOT EXISTS `clickhealth` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `clickhealth`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: clickhealth
-- ------------------------------------------------------
-- Server version	5.6.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `appointments`
--

DROP TABLE IF EXISTS `appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointments` (
  `appointmentsID` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(100) NOT NULL,
  `concern` varchar(100) NOT NULL,
  `startTime` time NOT NULL,
  `appointmentDate` date NOT NULL,
  `patient_ID` int(11) NOT NULL,
  `doctor_ID` int(11) NOT NULL,
  `hospital_ID` int(11) NOT NULL,
  PRIMARY KEY (`appointmentsID`),
  UNIQUE KEY `appointmentsID_UNIQUE` (`appointmentsID`),
  KEY `patientID_idx` (`patient_ID`),
  KEY `doctorID` (`doctor_ID`),
  KEY `hospitalID_idx` (`hospital_ID`),
  CONSTRAINT `doctorID` FOREIGN KEY (`doctor_ID`) REFERENCES `doctor` (`licenseID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `hospitalID` FOREIGN KEY (`hospital_ID`) REFERENCES `hospital` (`hospitalID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `patientID` FOREIGN KEY (`patient_ID`) REFERENCES `patient` (`patientID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointments`
--

LOCK TABLES `appointments` WRITE;
/*!40000 ALTER TABLE `appointments` DISABLE KEYS */;
INSERT INTO `appointments` VALUES (1,'pending','GUTOM','10:00:00','2015-10-05',1,201545,2),(2,'pending','SIAO LONG PAO','10:00:00','2015-07-17',1,201545,1),(3,'approved','migraine','10:00:00','2008-05-05',1,201545,2);
/*!40000 ALTER TABLE `appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctor` (
  `licenseID` int(11) NOT NULL,
  `specialization` varchar(100) NOT NULL,
  `user_ID` int(11) NOT NULL,
  PRIMARY KEY (`licenseID`),
  UNIQUE KEY `licenseID_UNIQUE` (`licenseID`),
  KEY `userID_idx` (`user_ID`),
  CONSTRAINT `userID` FOREIGN KEY (`user_ID`) REFERENCES `user` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES (201545,'Pediatrician',2),(201556,'Optometrist',4),(1130088,'DoctorQuakQuak',5);
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctorschedule`
--

DROP TABLE IF EXISTS `doctorschedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctorschedule` (
  `scheduleID` int(11) NOT NULL AUTO_INCREMENT,
  `scheduleDate` date NOT NULL,
  `startTime` time NOT NULL,
  `endTime` time NOT NULL,
  `isAvailable` int(11) NOT NULL,
  `doctorScheduleID` int(11) NOT NULL,
  `hospitalScheduleID` int(11) NOT NULL,
  PRIMARY KEY (`scheduleID`),
  UNIQUE KEY `scheduleID_UNIQUE` (`scheduleID`),
  KEY `doctor_ID_idx` (`doctorScheduleID`),
  KEY `hospital_ID_idx` (`hospitalScheduleID`),
  CONSTRAINT `doctor_ID` FOREIGN KEY (`doctorScheduleID`) REFERENCES `doctor` (`licenseID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `hospital_ID` FOREIGN KEY (`hospitalScheduleID`) REFERENCES `hospital` (`hospitalID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctorschedule`
--

LOCK TABLES `doctorschedule` WRITE;
/*!40000 ALTER TABLE `doctorschedule` DISABLE KEYS */;
INSERT INTO `doctorschedule` VALUES (1,'2015-06-07','10:00:00','16:00:00',1,201545,1),(2,'2015-07-17','10:00:00','16:00:00',1,201556,2),(3,'2015-09-18','10:00:00','16:00:00',1,1130088,3),(4,'2015-08-08','10:00:00','16:00:00',1,201545,2);
/*!40000 ALTER TABLE `doctorschedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hospital`
--

DROP TABLE IF EXISTS `hospital`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hospital` (
  `hospitalID` int(11) NOT NULL AUTO_INCREMENT,
  `hospitalName` varchar(100) NOT NULL,
  `hospitalStreet` varchar(100) NOT NULL,
  `hospitalCity` varchar(100) NOT NULL,
  PRIMARY KEY (`hospitalID`),
  UNIQUE KEY `hospitalID_UNIQUE` (`hospitalID`),
  UNIQUE KEY `hospitalName_UNIQUE` (`hospitalName`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hospital`
--

LOCK TABLES `hospital` WRITE;
/*!40000 ALTER TABLE `hospital` DISABLE KEYS */;
INSERT INTO `hospital` VALUES (1,'Makati Med','Armosolo Street','Makati City'),(2,'Chinese General Hospital','Blumentritt Street','Manila'),(3,'Manila Med','UN Avenue','Manila');
/*!40000 ALTER TABLE `hospital` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient` (
  `patientID` int(11) NOT NULL AUTO_INCREMENT,
  `street` varchar(100) NOT NULL,
  `city` varchar(100) NOT NULL,
  `user_ID` int(11) NOT NULL,
  PRIMARY KEY (`patientID`),
  UNIQUE KEY `patientID_UNIQUE` (`patientID`),
  KEY `useID_idx` (`user_ID`),
  CONSTRAINT `useID` FOREIGN KEY (`user_ID`) REFERENCES `user` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (1,'Santo Domingo Street','Quezon City',1);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `firstname` varchar(100) NOT NULL,
  `type` varchar(100) NOT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `userID_UNIQUE` (`userID`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'shayaneTan','shayane_tan@yahoo.com','shayane','Tan','Shayane','patient'),(2,'xgbCote','xgb_cote@yahoo.com','winona','Cote','Xgb','doctor'),(3,'winonaErive','winona_erive@yahoo.com','xgb','Erive','Winona','patient'),(4,'randomDoc','randomDoc@yahoo.com','random','Random','Doc','doctor'),(5,'chengDoc','chengDoc@gmail.com','cheng','Cheng','Doc','doctor');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usercontact`
--

DROP TABLE IF EXISTS `usercontact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usercontact` (
  `userID` int(11) NOT NULL,
  `contactNo` varchar(150) NOT NULL,
  `type` varchar(100) NOT NULL,
  PRIMARY KEY (`userID`,`contactNo`,`type`),
  CONSTRAINT `user_ID` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usercontact`
--

LOCK TABLES `usercontact` WRITE;
/*!40000 ALTER TABLE `usercontact` DISABLE KEYS */;
INSERT INTO `usercontact` VALUES (1,'788-0297','Telephone'),(1,'shayane_tan@yahoo.com','E-mail'),(2,'xgbCote@MakatiMed.com','E-mail'),(3,'09989749388','Cellphone'),(4,'09984756667','Cellphone'),(4,'randomDoc@yahoo.com','E-mail');
/*!40000 ALTER TABLE `usercontact` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-03-24 10:59:26
