CREATE DATABASE  IF NOT EXISTS `ecommerce` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ecommerce`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: ecommerce
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mobile` char(12) NOT NULL,
  `password` varchar(100) NOT NULL,
  `address` varchar(200) NOT NULL DEFAULT 'India',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `mobile_UNIQUE` (`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'sumit','sumitkajla47@gmail.com','7734961217','sumit@123','jaipur'),(2,'sumit','sumitkajla477@gmail.com','77349612177','sumit@321','jaipur'),(3,'admin3','admin','123456789','admin','xyx'),(4,'kajla','kajla','1234','kajla','India'),(6,'kajla','kajla2','kajla2','kajla2','India'),(7,'test1','test1','test1','test1','India'),(8,'test2','test2','test2','test2','India'),(9,'test3','test3','test3','test3','India');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `group_order_id` int NOT NULL,
  `customer_id` int NOT NULL,
  `product_id` int NOT NULL,
  `quantity` int NOT NULL DEFAULT '1',
  `order_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `order_status` varchar(45) NOT NULL DEFAULT 'Ordered',
  `delivery_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_fk_idx` (`customer_id`),
  KEY `products_fk_idx` (`product_id`),
  CONSTRAINT `customer_fk` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `products_fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,1,1,1,1,'2024-05-19 17:17:01','Ordered',NULL),(2,2,3,5,1,'2024-05-19 18:23:40','Ordered',NULL),(3,3,3,44,1,'2024-05-19 18:24:06','Ordered',NULL),(4,4,3,43,1,'2024-05-19 18:24:13','Ordered',NULL),(5,5,3,37,1,'2024-05-19 18:24:22','Ordered',NULL),(6,6,1,7,1,'2024-05-19 18:25:38','Ordered',NULL),(7,7,1,11,1,'2024-05-19 18:25:42','Ordered',NULL),(8,8,7,7,1,'2024-05-20 15:04:06','Ordered',NULL),(9,9,8,43,1,'2024-05-20 15:06:11','Ordered',NULL),(10,10,7,42,1,'2024-05-20 20:39:53','Ordered',NULL),(11,11,3,27,1,'2024-05-21 13:43:10','Ordered',NULL),(12,11,3,28,1,'2024-05-21 13:43:10','Ordered',NULL),(13,12,8,39,1,'2024-05-21 14:30:29','Ordered',NULL),(14,12,8,44,1,'2024-05-21 14:30:29','Ordered',NULL),(15,12,8,45,1,'2024-05-21 14:30:29','Ordered',NULL),(16,13,3,4,1,'2024-05-21 16:53:58','Ordered',NULL),(17,14,8,11,1,'2024-05-21 17:18:44','Ordered',NULL),(18,15,3,47,1,'2024-05-21 18:46:07','Ordered',NULL),(19,15,3,48,1,'2024-05-21 18:46:07','Ordered',NULL),(20,15,3,50,1,'2024-05-21 18:46:07','Ordered',NULL),(21,15,3,36,1,'2024-05-21 18:46:07','Ordered',NULL),(22,16,9,22,1,'2024-05-21 18:51:15','Ordered',NULL),(23,17,9,46,1,'2024-05-21 18:51:19','Ordered',NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `descriptionl` varchar(45) DEFAULT NULL,
  `price` double NOT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'keyboard','keyboard with rgb',1500,99),(2,'mouse','wireless mouse',800,150),(3,'monitor','27 inch 4K monitor',25000,45),(4,'laptop','gaming laptop',75000,20),(5,'printer','laser printer',12000,35),(6,'desk','standing desk',10000,50),(7,'chair','ergonomic chair',7000,70),(8,'headset','gaming headset',3000,80),(9,'webcam','HD webcam',2500,60),(10,'microphone','USB microphone',4000,40),(11,'speakers','Bluetooth speakers',3500,90),(12,'router','Wi-Fi router',5000,100),(13,'tablet','Android tablet',15000,30),(14,'smartphone','latest smartphone',60000,25),(15,'powerbank','10000mAh powerbank',1500,150),(16,'camera','DSLR camera',55000,15),(17,'tripod','camera tripod',3000,50),(18,'external_hard_drive','1TB external hard drive',5000,80),(19,'usb_flash_drive','64GB USB flash drive',800,200),(20,'memory_card','128GB memory card',2000,150),(21,'graphics_card','high-end graphics card',35000,10),(22,'motherboard','gaming motherboard',12000,25),(23,'cpu','high-performance CPU',25000,20),(24,'ram','16GB DDR4 RAM',8000,60),(25,'ssd','512GB SSD',6000,100),(26,'cooling_fan','CPU cooling fan',2000,75),(27,'power_supply','750W power supply',7000,40),(28,'case','PC case',5000,35),(29,'laptop_bag','laptop bag',2000,120),(30,'keyboard_cover','keyboard cover',500,200),(31,'mouse_pad','gaming mouse pad',1000,180),(32,'stylus','stylus pen',1500,75),(33,'projector','LED projector',25000,10),(34,'smartwatch','fitness smartwatch',8000,50),(35,'bluetooth_adapter','Bluetooth adapter',1500,100),(36,'network_switch','8-port network switch',3000,40),(37,'ethernet_cable','CAT6 ethernet cable',500,300),(38,'vr_headset','VR headset',30000,20),(39,'drone','camera drone',45000,10),(40,'e_book_reader','e-book reader',10000,40),(41,'smart_light','smart light bulb',2000,150),(42,'robot_vacuum','robot vacuum cleaner',20000,30),(43,'air_purifier','air purifier',12000,25),(44,'fitness_tracker','fitness tracker',4000,70),(45,'electric_scooter','electric scooter',35000,10),(46,'smart_thermostat','smart thermostat',15000,20),(47,'security_camera','Wi-Fi security camera',8000,60),(48,'smart_doorbell','smart doorbell',10000,25),(49,'wireless_charger','wireless charger',3000,150),(50,'game_console','latest game console',50000,15);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-22 16:02:12
