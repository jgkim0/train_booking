-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: train_booking
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `payment_id` bigint NOT NULL AUTO_INCREMENT,
  `amount` int DEFAULT NULL,
  `paid_at` datetime(6) DEFAULT NULL,
  `status` enum('APPROVED','FAILED','READY','CANCELED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `booking_id` bigint DEFAULT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `FKqewrl4xrv9eiad6eab3aoja65` (`booking_id`),
  CONSTRAINT `FKqewrl4xrv9eiad6eab3aoja65` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`booking_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (4,5800,NULL,'CANCELED',4),(5,5800,NULL,'CANCELED',5);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `station`
--

DROP TABLE IF EXISTS `station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `station` (
  `station_id` bigint NOT NULL AUTO_INCREMENT,
  `station_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`station_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station`
--

LOCK TABLES `station` WRITE;
/*!40000 ALTER TABLE `station` DISABLE KEYS */;
INSERT INTO `station` VALUES (1,'서울'),(2,'광명'),(3,'오송'),(4,'대전'),(5,'동대구');
/*!40000 ALTER TABLE `station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `ticket_id` bigint NOT NULL AUTO_INCREMENT,
  `issued_at` datetime(6) DEFAULT NULL,
  `seat_id` bigint DEFAULT NULL,
  `ticket_no` bigint DEFAULT NULL,
  `booking_id` bigint DEFAULT NULL,
  PRIMARY KEY (`ticket_id`),
  KEY `FKrg7x158t96nucwslhq2bad6qm` (`booking_id`),
  CONSTRAINT `FKrg7x158t96nucwslhq2bad6qm` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`booking_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (1,'2026-03-15 20:02:46.570471',4,55,4),(2,'2026-03-15 20:19:56.196149',5,55,5);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seat` (
  `seat_id` bigint NOT NULL AUTO_INCREMENT,
  `seat_no` bigint DEFAULT NULL,
  `trip_id` bigint DEFAULT NULL,
  `status` enum('AVAILABLE','BOOKED','CANCELLED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `type` bigint DEFAULT NULL,
  PRIMARY KEY (`seat_id`),
  KEY `FKsvbxfdxjgv9tgp9iakpdarbr0` (`trip_id`),
  KEY `seat_ticket_pricing_FK` (`type`),
  CONSTRAINT `FKsvbxfdxjgv9tgp9iakpdarbr0` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`trip_id`),
  CONSTRAINT `seat_ticket_pricing_FK` FOREIGN KEY (`type`) REFERENCES `ticket_pricing` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
INSERT INTO `seat` VALUES (1,1000,1,'AVAILABLE',0,NULL),(2,1001,1,'AVAILABLE',0,NULL),(3,1002,1,'BOOKED',2,NULL),(4,1003,1,'BOOKED',1,NULL),(5,1004,1,'AVAILABLE',2,NULL),(6,1005,1,'AVAILABLE',0,NULL),(7,1006,1,'AVAILABLE',0,NULL),(8,1007,1,'AVAILABLE',0,NULL),(9,1008,1,'AVAILABLE',0,NULL),(10,1009,1,'AVAILABLE',0,NULL),(11,2000,2,'AVAILABLE',0,NULL),(12,2001,2,'AVAILABLE',0,NULL),(13,2002,2,'AVAILABLE',0,NULL),(14,2003,2,'AVAILABLE',0,NULL),(15,2004,2,'AVAILABLE',0,NULL),(16,2005,2,'AVAILABLE',0,NULL),(17,2006,2,'AVAILABLE',0,NULL),(18,2007,2,'AVAILABLE',0,NULL),(19,2008,2,'AVAILABLE',0,NULL),(20,2009,2,'AVAILABLE',0,NULL);
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_pricing`
--

DROP TABLE IF EXISTS `ticket_pricing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket_pricing` (
  `id` bigint NOT NULL,
  `type` varchar(100) DEFAULT NULL,
  `price` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ticket_pricing_id_IDX` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='티켓 가격 정책';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_pricing`
--

LOCK TABLES `ticket_pricing` WRITE;
/*!40000 ALTER TABLE `ticket_pricing` DISABLE KEYS */;
INSERT INTO `ticket_pricing` VALUES (1,'A',1000),(2,'B',1000);
/*!40000 ALTER TABLE `ticket_pricing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `booking_id` bigint NOT NULL AUTO_INCREMENT,
  `created_dt` datetime(6) DEFAULT NULL,
  `seat_id` bigint DEFAULT NULL,
  `trip_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `status` enum('CANCELED','CREATED','PAID') DEFAULT NULL,
  PRIMARY KEY (`booking_id`),
  KEY `FK7ryitbom1ln9okwlj2t9tt9ym` (`seat_id`),
  KEY `FKkp5ujmgvd2pmsehwpu2vyjkwb` (`trip_id`),
  CONSTRAINT `FK7ryitbom1ln9okwlj2t9tt9ym` FOREIGN KEY (`seat_id`) REFERENCES `seat` (`seat_id`),
  CONSTRAINT `FKkp5ujmgvd2pmsehwpu2vyjkwb` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`trip_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (3,'2026-03-15 17:54:31.029000',3,1,1,'CREATED'),(4,'2026-03-15 20:01:26.291000',4,1,1,'CANCELED'),(5,'2026-03-15 20:19:23.196000',5,1,1,'CANCELED');
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trip`
--

DROP TABLE IF EXISTS `trip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trip` (
  `arrival_time` datetime(6) DEFAULT NULL,
  `departure_time` datetime(6) DEFAULT NULL,
  `from_station_id` bigint DEFAULT NULL,
  `to_station_id` bigint DEFAULT NULL,
  `train_no` bigint DEFAULT NULL,
  `trip_id` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`trip_id`),
  KEY `FK3i2vv3r9wrxnyqdspds0t3fyd` (`from_station_id`),
  KEY `FKor5yuwh9usu2shw22s57c3uxr` (`to_station_id`),
  CONSTRAINT `FK3i2vv3r9wrxnyqdspds0t3fyd` FOREIGN KEY (`from_station_id`) REFERENCES `station` (`station_id`),
  CONSTRAINT `FKor5yuwh9usu2shw22s57c3uxr` FOREIGN KEY (`to_station_id`) REFERENCES `station` (`station_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trip`
--

LOCK TABLES `trip` WRITE;
/*!40000 ALTER TABLE `trip` DISABLE KEYS */;
INSERT INTO `trip` VALUES ('2026-01-30 13:05:00.000000','2026-01-30 11:32:00.000000',5,1,1,1),('2026-01-31 14:01:00.000000','2026-01-31 14:03:00.000000',1,5,2,2);
/*!40000 ALTER TABLE `trip` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-31 16:00:33
