-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: activity_platform
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '活动名称',
  `img_ids` varchar(2000) DEFAULT NULL COMMENT '图片列表',
  `time` datetime DEFAULT NULL COMMENT '活动时间',
  `location` varchar(255) DEFAULT NULL COMMENT '活动地点',
  `content` varchar(5000) DEFAULT NULL COMMENT '活动内容',
  `promoter_id` int DEFAULT NULL COMMENT '发起人id',
  `is_examine` tinyint(1) DEFAULT '0' COMMENT '审核是否通过',
  PRIMARY KEY (`id`),
  UNIQUE KEY `activity_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (1,'读书会5','[3, 4]','2024-02-19 20:25:28','长沙','这是个读书交流会',5,1),(2,'学术交流会','[13, 14]','2024-01-21 20:22:11','上海','这是个学术交流会',3,1),(5,'交流会test1122','[13, 14]','2024-01-19 20:26:11','北京','这是个交流会',5,0),(6,'交流会test112332','[13, 14]','2024-01-19 20:26:11','北京','这是个交流会',5,0),(7,'Hello','[33]','2024-02-15 00:00:00','上海','安全会议',5,1),(8,'蛋仔派对','[34]','2024-04-25 00:00:00','杭州','休闲游戏',5,0),(9,'2024新年快乐','[35]','2024-05-23 00:00:00','南京','2024春节快乐！！',5,0),(10,'交流会tttest','[6, 7]','2024-01-19 20:26:11','北京','这是个交流会',5,0),(11,'GCU图像','[37]','2024-02-05 22:53:48','天津','论文发布',5,0),(12,'BMFF','[38]','2024-02-05 22:57:56','河北衡水','发论文',5,1);
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_detail`
--

DROP TABLE IF EXISTS `activity_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `a_id` int DEFAULT NULL COMMENT '关联的活动id',
  `v_id` bigint DEFAULT NULL COMMENT '视频id',
  `l_id` bigint DEFAULT NULL COMMENT '直播id',
  `viewers_count` int DEFAULT '0' COMMENT '观看人数',
  `likes_count` int DEFAULT '0' COMMENT '获得点赞数',
  `subscribers_count` int DEFAULT '0' COMMENT '订阅人数',
  `signup_fee` decimal(10,2) DEFAULT '0.00' COMMENT '是否免费',
  PRIMARY KEY (`id`),
  UNIQUE KEY `activity_detail_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_detail`
--

LOCK TABLES `activity_detail` WRITE;
/*!40000 ALTER TABLE `activity_detail` DISABLE KEYS */;
INSERT INTO `activity_detail` VALUES (3,5,16,16,0,0,1,0.00),(4,6,16,16,0,0,0,0.00),(5,1,16,16,0,0,1,0.00),(6,2,16,16,0,0,2,0.00),(7,7,NULL,NULL,0,0,0,0.00),(8,8,NULL,NULL,0,0,0,0.00),(9,9,NULL,NULL,0,0,0,0.00),(10,10,NULL,NULL,0,0,0,0.00),(11,11,NULL,NULL,0,0,0,0.00),(12,12,NULL,NULL,0,0,0,0.00);
/*!40000 ALTER TABLE `activity_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file_info`
--

DROP TABLE IF EXISTS `file_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `origin_name` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `file_info_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file_info`
--

LOCK TABLES `file_info` WRITE;
/*!40000 ALTER TABLE `file_info` DISABLE KEYS */;
INSERT INTO `file_info` VALUES (1,'草稿纸.txt','草稿纸1705804813345.txt'),(2,'草稿纸.txt','草稿纸1705804940149.txt'),(3,'草稿纸.txt','草稿纸1705805442335.txt'),(4,'草稿纸.txt','草稿纸1705806332874.txt'),(5,'草稿纸.txt','草稿纸1705806836635.txt'),(6,'草稿纸.txt','草稿纸1705807835962.txt'),(7,'草稿纸.txt','草稿纸1705807963614.txt'),(8,'长沙理工大学党员民主评议表.doc','长沙理工大学党员民主评议表1705807963631.doc'),(9,'草稿纸.txt','草稿纸1705808032567.txt'),(10,'F8885233A53EF28255BC33B7CCF885F3.jpg','F8885233A53EF28255BC33B7CCF885F31705817105558.jpg'),(11,'1.webp','11705823016839.webp'),(12,'2.webp','21705823016847.webp'),(13,'3.png','31705827014790.png'),(14,'2.png','21705827102716.png'),(15,'视障人士公交导乘系统——电视媒体报道.mp4','视障人士公交导乘系统——电视媒体报道1705827166150.mp4'),(16,'视障人士公交导乘系统——电视媒体报道.mp4','视障人士公交导乘系统——电视媒体报道1705999723452.mp4'),(17,'french_flag.jpg','french_flag1707053528485.jpg'),(18,'4.png','41707054664072.png'),(19,'4.png','41707054957139.png'),(20,'4.png','41707056589242.png'),(21,'4.png','41707056635253.png'),(22,'4.png','41707057318465.png'),(23,'4.png','41707057449485.png'),(24,'4.png','41707057566418.png'),(25,'4.png','41707057864792.png'),(26,'4.png','41707058064808.png'),(27,'4.png','41707058090507.png'),(28,'3.png','31707058204536.png'),(29,'4.png','41707058231018.png'),(30,'4.png','41707058803995.png'),(31,'4.png','41707059148016.png'),(32,'4.png','41707059191467.png'),(33,'4.png','41707059245031.png'),(34,'french_flag.jpg','french_flag1707141861515.jpg'),(35,'logo.png','logo1707144291639.png'),(36,'logo.png','logo1707144743198.png'),(37,'logo.png','logo1707144817192.png'),(38,'logo.png','logo1707145114729.png');
/*!40000 ALTER TABLE `file_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscribe`
--

DROP TABLE IF EXISTS `subscribe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subscribe` (
  `id` int NOT NULL AUTO_INCREMENT,
  `a_id` int DEFAULT NULL COMMENT '活动id',
  `u_id` int DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `subscribe_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscribe`
--

LOCK TABLES `subscribe` WRITE;
/*!40000 ALTER TABLE `subscribe` DISABLE KEYS */;
INSERT INTO `subscribe` VALUES (3,2,5),(6,5,5),(10,1,3),(12,2,3);
/*!40000 ALTER TABLE `subscribe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (00000000003,'16631808616','22348e96a565e1f6befe5721e707b914','AMkKBlHD'),(00000000005,'13574522165','47bc73dd29c3f1640c12397a9adf72ab','cwT55SJB');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_info` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `age` int DEFAULT '0',
  `sex` tinyint(1) DEFAULT '0',
  `phone_num` varchar(11) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `career` varchar(255) DEFAULT NULL COMMENT '职业',
  PRIMARY KEY (`id`),
  KEY `user_info_ibfk_1` (`phone_num`),
  CONSTRAINT `user_info_ibfk_1` FOREIGN KEY (`phone_num`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (00000000003,NULL,0,0,'16631808616',NULL,'学生'),(00000000005,'ghhh',20,1,'13574522165','127159727@qq.com','打工仔');
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-27 20:53:47
