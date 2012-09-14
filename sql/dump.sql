-- MySQL dump 10.13  Distrib 5.5.27, for osx10.6 (i386)
--
-- Host: localhost    Database: shopper
-- ------------------------------------------------------
-- Server version	5.5.27

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
-- Table structure for table `sp_brand`
--

DROP TABLE IF EXISTS `sp_brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sp_brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sp_brand`
--

LOCK TABLES `sp_brand` WRITE;
/*!40000 ALTER TABLE `sp_brand` DISABLE KEYS */;
INSERT INTO `sp_brand` VALUES (1,'barilla'),(2,'rigoni'),(3,''),(4,'de cecco');
/*!40000 ALTER TABLE `sp_brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sp_items`
--

DROP TABLE IF EXISTS `sp_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sp_items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `quantity` varchar(45) NOT NULL,
  `bought` int(11) NOT NULL,
  `note` varchar(250) DEFAULT NULL,
  `order` int(11) NOT NULL DEFAULT '0',
  `type` int(11) DEFAULT NULL,
  `brand` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `type_id` (`type`),
  KEY `brand_id` (`brand`),
  CONSTRAINT `brand_id` FOREIGN KEY (`brand`) REFERENCES `sp_brand` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `type_id` FOREIGN KEY (`type`) REFERENCES `sp_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sp_items`
--

LOCK TABLES `sp_items` WRITE;
/*!40000 ALTER TABLE `sp_items` DISABLE KEYS */;
INSERT INTO `sp_items` VALUES (6,'1','latte','2',0,'',0,7,NULL),(7,'1','pane','1',1,'',0,1,NULL),(8,'1','riso integrale','1',0,'',0,10,NULL),(14,'1','pasta','1',1,'',1,1,1),(15,'1','tortiglioni','2',1,'',2,1,4);
/*!40000 ALTER TABLE `sp_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sp_type`
--

DROP TABLE IF EXISTS `sp_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sp_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `plural` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sp_type`
--

LOCK TABLES `sp_type` WRITE;
/*!40000 ALTER TABLE `sp_type` DISABLE KEYS */;
INSERT INTO `sp_type` VALUES (1,'chilo','chili'),(2,'etto','etti'),(3,'grammo','grammi'),(4,'pezzo','pezzi'),(5,'filone','filoni'),(6,'pacco','pacchi'),(7,'litro','litri'),(8,'sacco','sacchi'),(9,'pagnotta','pagnotte'),(10,'confezione','confezioni');
/*!40000 ALTER TABLE `sp_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sp_user`
--

DROP TABLE IF EXISTS `sp_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sp_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sp_user`
--

LOCK TABLES `sp_user` WRITE;
/*!40000 ALTER TABLE `sp_user` DISABLE KEYS */;
INSERT INTO `sp_user` VALUES (1,'michele','apriti80'),(2,'andrea','pippo80');
/*!40000 ALTER TABLE `sp_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-08-24 19:20:19
