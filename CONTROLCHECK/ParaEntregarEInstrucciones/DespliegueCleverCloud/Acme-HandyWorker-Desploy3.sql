-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: Acme-HandyWorker
-- ------------------------------------------------------
-- Server version	5.5.29

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
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_banned` bit(1) DEFAULT NULL,
  `is_suspicious` bit(1) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_awymvli3olnnumqow6wf060pa` (`email`),
  KEY `FK_i7xei45auwq1f6vu25985riuh` (`user_account`),
  CONSTRAINT `FK_i7xei45auwq1f6vu25985riuh` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor_mail_boxes`
--

DROP TABLE IF EXISTS `actor_mail_boxes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_mail_boxes` (
  `actor` int(11) NOT NULL,
  `mail_boxes` int(11) NOT NULL,
  UNIQUE KEY `UK_n1n6n39n56whljnwefipwm9fo` (`mail_boxes`),
  CONSTRAINT `FK_n1n6n39n56whljnwefipwm9fo` FOREIGN KEY (`mail_boxes`) REFERENCES `mail_box` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_mail_boxes`
--

LOCK TABLES `actor_mail_boxes` WRITE;
/*!40000 ALTER TABLE `actor_mail_boxes` DISABLE KEYS */;
INSERT INTO `actor_mail_boxes` VALUES (30,31),(30,32),(30,33),(30,34);
/*!40000 ALTER TABLE `actor_mail_boxes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor_social_profiles`
--

DROP TABLE IF EXISTS `actor_social_profiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_social_profiles` (
  `actor` int(11) NOT NULL,
  `social_profiles` int(11) NOT NULL,
  UNIQUE KEY `UK_4suhrykpl9af1ubs85ycbyt6q` (`social_profiles`),
  CONSTRAINT `FK_4suhrykpl9af1ubs85ycbyt6q` FOREIGN KEY (`social_profiles`) REFERENCES `social_profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_social_profiles`
--

LOCK TABLES `actor_social_profiles` WRITE;
/*!40000 ALTER TABLE `actor_social_profiles` DISABLE KEYS */;
/*!40000 ALTER TABLE `actor_social_profiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_banned` bit(1) DEFAULT NULL,
  `is_suspicious` bit(1) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jj3mmcc0vjobqibj67dvuwihk` (`email`),
  KEY `FK_7ohwsa2usmvu0yxb44je2lge` (`user_account`),
  CONSTRAINT `FK_7ohwsa2usmvu0yxb44je2lge` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (30,0,'Calle Aguditas Número 12','admin@gmail.com',NULL,NULL,'','Laura',NULL,NULL,'Exposito',29);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `comments_cus` varchar(255) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `cvv` int(11) DEFAULT NULL,
  `month` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `state` bit(1) DEFAULT NULL,
  `applier` int(11) NOT NULL,
  `fix_up` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_l0s4xjte7r4cwy2lm00o5ut2d` (`applier`),
  KEY `FK_l53lu2bm39nyqjghnfruyxpb8` (`fix_up`),
  CONSTRAINT `FK_l53lu2bm39nyqjghnfruyxpb8` FOREIGN KEY (`fix_up`) REFERENCES `fix_up` (`id`),
  CONSTRAINT `FK_l0s4xjte7r4cwy2lm00o5ut2d` FOREIGN KEY (`applier`) REFERENCES `handy_worker` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `nameen` varchar(255) DEFAULT NULL,
  `namees` varchar(255) DEFAULT NULL,
  `parent_category` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_lhvwmxrdrjma5forcymeuolqn` (`parent_category`),
  CONSTRAINT `FK_lhvwmxrdrjma5forcymeuolqn` FOREIGN KEY (`parent_category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (35,0,'Category','Categoría',NULL),(36,0,'Carpentry','Carpintería',35),(37,1,'Ceiling repair','Reparación de techos',51),(38,0,'Cleaning','Limpieza',35),(39,0,'Concrete work','Trabajo concreto',35),(40,0,'Doors','Puertas',36),(41,0,'Electrical wiring','Cableado eléctrico',35),(42,0,'Fan installation','Instalación de ventilador',39),(43,0,'Fence fixing','Fijación de vallas',39),(44,0,'Home security systems','Sistema de seguridad para el hogar',39),(45,0,'Insulation installation','Instalación de aislamiento',35),(46,0,'Lamp repairs','Reparación de lamparas',39),(47,0,'Moving','Mudanza',35),(48,0,'Painting','Pintura',35),(49,0,'Pest control','Control de plagas',39),(50,0,'Plumbing repairs','Reparación de fontanería',35),(51,0,'Roofing','Tejados',35),(52,0,'Shelf installation','Instalación de estantes',36),(53,0,'Solar panels','Paneles solares',39),(54,0,'SoundProofing','Insonorización',45),(55,0,'Sprinkler repair','Reparación de aspersores',39),(56,0,'Window repair','Reparación de ventanas',39);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `complaint`
--

DROP TABLE IF EXISTS `complaint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `complaint` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `attachment` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `fix_up` int(11) NOT NULL,
  `referee` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jlpo668tu0b8mmsjsg8g13inu` (`ticker`),
  KEY `FK_3q3lcgmlilg95pkaq6qmyj3kf` (`fix_up`),
  KEY `FK_n7kqs8a7c2q1jwjcc44oticll` (`referee`),
  CONSTRAINT `FK_n7kqs8a7c2q1jwjcc44oticll` FOREIGN KEY (`referee`) REFERENCES `referee` (`id`),
  CONSTRAINT `FK_3q3lcgmlilg95pkaq6qmyj3kf` FOREIGN KEY (`fix_up`) REFERENCES `fix_up` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaint`
--

LOCK TABLES `complaint` WRITE;
/*!40000 ALTER TABLE `complaint` DISABLE KEYS */;
/*!40000 ALTER TABLE `complaint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curriculum`
--

DROP TABLE IF EXISTS `curriculum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curriculum` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `perrec` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3ai7h3tynp97g8r0g93r84m8w` (`ticker`),
  KEY `FK_h6epth64l9bpd6uglvl48udb9` (`perrec`),
  CONSTRAINT `FK_h6epth64l9bpd6uglvl48udb9` FOREIGN KEY (`perrec`) REFERENCES `personal_record` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curriculum`
--

LOCK TABLES `curriculum` WRITE;
/*!40000 ALTER TABLE `curriculum` DISABLE KEYS */;
/*!40000 ALTER TABLE `curriculum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curriculum_edurec`
--

DROP TABLE IF EXISTS `curriculum_edurec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curriculum_edurec` (
  `curriculum` int(11) NOT NULL,
  `edurec` int(11) NOT NULL,
  UNIQUE KEY `UK_7d2se1un6kyd7w02xfol6r8e4` (`edurec`),
  KEY `FK_p22883arkjfktlpieqxsvdt9n` (`curriculum`),
  CONSTRAINT `FK_p22883arkjfktlpieqxsvdt9n` FOREIGN KEY (`curriculum`) REFERENCES `curriculum` (`id`),
  CONSTRAINT `FK_7d2se1un6kyd7w02xfol6r8e4` FOREIGN KEY (`edurec`) REFERENCES `educational_record` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curriculum_edurec`
--

LOCK TABLES `curriculum_edurec` WRITE;
/*!40000 ALTER TABLE `curriculum_edurec` DISABLE KEYS */;
/*!40000 ALTER TABLE `curriculum_edurec` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curriculum_endrec`
--

DROP TABLE IF EXISTS `curriculum_endrec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curriculum_endrec` (
  `curriculum` int(11) NOT NULL,
  `endrec` int(11) NOT NULL,
  UNIQUE KEY `UK_5wjjy62s0xpxnhwhnuwndy2ve` (`endrec`),
  KEY `FK_f0tu53dc911vxgcji78c46buk` (`curriculum`),
  CONSTRAINT `FK_f0tu53dc911vxgcji78c46buk` FOREIGN KEY (`curriculum`) REFERENCES `curriculum` (`id`),
  CONSTRAINT `FK_5wjjy62s0xpxnhwhnuwndy2ve` FOREIGN KEY (`endrec`) REFERENCES `endorser_record` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curriculum_endrec`
--

LOCK TABLES `curriculum_endrec` WRITE;
/*!40000 ALTER TABLE `curriculum_endrec` DISABLE KEYS */;
/*!40000 ALTER TABLE `curriculum_endrec` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curriculum_misrec`
--

DROP TABLE IF EXISTS `curriculum_misrec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curriculum_misrec` (
  `curriculum` int(11) NOT NULL,
  `misrec` int(11) NOT NULL,
  UNIQUE KEY `UK_1q6najrc1kbbdufwva53yj20c` (`misrec`),
  KEY `FK_3wmst2ddusg83pqp12sktliai` (`curriculum`),
  CONSTRAINT `FK_3wmst2ddusg83pqp12sktliai` FOREIGN KEY (`curriculum`) REFERENCES `curriculum` (`id`),
  CONSTRAINT `FK_1q6najrc1kbbdufwva53yj20c` FOREIGN KEY (`misrec`) REFERENCES `miscellaneous_record` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curriculum_misrec`
--

LOCK TABLES `curriculum_misrec` WRITE;
/*!40000 ALTER TABLE `curriculum_misrec` DISABLE KEYS */;
/*!40000 ALTER TABLE `curriculum_misrec` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curriculum_prorec`
--

DROP TABLE IF EXISTS `curriculum_prorec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curriculum_prorec` (
  `curriculum` int(11) NOT NULL,
  `prorec` int(11) NOT NULL,
  UNIQUE KEY `UK_hioxey4x8iflkxelsljslfpfc` (`prorec`),
  KEY `FK_t6i59gb8t78wrayl698m9wgrp` (`curriculum`),
  CONSTRAINT `FK_t6i59gb8t78wrayl698m9wgrp` FOREIGN KEY (`curriculum`) REFERENCES `curriculum` (`id`),
  CONSTRAINT `FK_hioxey4x8iflkxelsljslfpfc` FOREIGN KEY (`prorec`) REFERENCES `professional_record` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curriculum_prorec`
--

LOCK TABLES `curriculum_prorec` WRITE;
/*!40000 ALTER TABLE `curriculum_prorec` DISABLE KEYS */;
/*!40000 ALTER TABLE `curriculum_prorec` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_banned` bit(1) DEFAULT NULL,
  `is_suspicious` bit(1) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  `calification` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_dwk6cx0afu8bs9o4t536v1j5v` (`email`),
  KEY `FK_mbvdes9ypo1yu76so76owiyqx` (`user_account`),
  CONSTRAINT `FK_mbvdes9ypo1yu76so76owiyqx` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `educational_record`
--

DROP TABLE IF EXISTS `educational_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `educational_record` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `awarded_by` varchar(255) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `diploma` varchar(255) DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `start` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `educational_record`
--

LOCK TABLES `educational_record` WRITE;
/*!40000 ALTER TABLE `educational_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `educational_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endorsable`
--

DROP TABLE IF EXISTS `endorsable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endorsable` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_banned` bit(1) DEFAULT NULL,
  `is_suspicious` bit(1) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  `calification` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ggt00dfg0rj20kbula3uiba0e` (`email`),
  KEY `FK_h7encqxl3jdmtr40o61f5ja33` (`user_account`),
  CONSTRAINT `FK_h7encqxl3jdmtr40o61f5ja33` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endorsable`
--

LOCK TABLES `endorsable` WRITE;
/*!40000 ALTER TABLE `endorsable` DISABLE KEYS */;
/*!40000 ALTER TABLE `endorsable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endorsement`
--

DROP TABLE IF EXISTS `endorsement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endorsement` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `endorsable_receiver` int(11) NOT NULL,
  `endorsable_sender` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endorsement`
--

LOCK TABLES `endorsement` WRITE;
/*!40000 ALTER TABLE `endorsement` DISABLE KEYS */;
/*!40000 ALTER TABLE `endorsement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endorser_record`
--

DROP TABLE IF EXISTS `endorser_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endorser_record` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `linked_in` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endorser_record`
--

LOCK TABLES `endorser_record` WRITE;
/*!40000 ALTER TABLE `endorser_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `endorser_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finder`
--

DROP TABLE IF EXISTS `finder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finder` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `max_price` double DEFAULT NULL,
  `min_price` double DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  `warranty` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_n9t1ayk0x7h5vrsfuhygo043j` (`category`),
  KEY `FK_fsgvely8c4othsty26jul4qfl` (`warranty`),
  CONSTRAINT `FK_fsgvely8c4othsty26jul4qfl` FOREIGN KEY (`warranty`) REFERENCES `warranty` (`id`),
  CONSTRAINT `FK_n9t1ayk0x7h5vrsfuhygo043j` FOREIGN KEY (`category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finder`
--

LOCK TABLES `finder` WRITE;
/*!40000 ALTER TABLE `finder` DISABLE KEYS */;
/*!40000 ALTER TABLE `finder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finder_fix_ups`
--

DROP TABLE IF EXISTS `finder_fix_ups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finder_fix_ups` (
  `finder` int(11) NOT NULL,
  `fix_ups` int(11) NOT NULL,
  KEY `FK_8iy6r2aadpwh16u7y16rflci6` (`fix_ups`),
  KEY `FK_5whv2gcedl52m6fimw52a4rme` (`finder`),
  CONSTRAINT `FK_5whv2gcedl52m6fimw52a4rme` FOREIGN KEY (`finder`) REFERENCES `finder` (`id`),
  CONSTRAINT `FK_8iy6r2aadpwh16u7y16rflci6` FOREIGN KEY (`fix_ups`) REFERENCES `fix_up` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finder_fix_ups`
--

LOCK TABLES `finder_fix_ups` WRITE;
/*!40000 ALTER TABLE `finder_fix_ups` DISABLE KEYS */;
/*!40000 ALTER TABLE `finder_fix_ups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fix_up`
--

DROP TABLE IF EXISTS `fix_up`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fix_up` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  `customer` int(11) NOT NULL,
  `handy_worker` int(11) DEFAULT NULL,
  `warranty` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ggh4ccth132uv9ixibtbaeeff` (`category`),
  KEY `FK_9xybmia19n1lieny40tbbiexh` (`customer`),
  KEY `FK_1h2iutn02tybs06lvqpu2ncx8` (`handy_worker`),
  KEY `FK_j7o07wq9r0vdltfyqtobeq54u` (`warranty`),
  CONSTRAINT `FK_j7o07wq9r0vdltfyqtobeq54u` FOREIGN KEY (`warranty`) REFERENCES `warranty` (`id`),
  CONSTRAINT `FK_1h2iutn02tybs06lvqpu2ncx8` FOREIGN KEY (`handy_worker`) REFERENCES `handy_worker` (`id`),
  CONSTRAINT `FK_9xybmia19n1lieny40tbbiexh` FOREIGN KEY (`customer`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_ggh4ccth132uv9ixibtbaeeff` FOREIGN KEY (`category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fix_up`
--

LOCK TABLES `fix_up` WRITE;
/*!40000 ALTER TABLE `fix_up` DISABLE KEYS */;
/*!40000 ALTER TABLE `fix_up` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `handy_worker`
--

DROP TABLE IF EXISTS `handy_worker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `handy_worker` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_banned` bit(1) DEFAULT NULL,
  `is_suspicious` bit(1) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  `calification` double DEFAULT NULL,
  `make` varchar(255) DEFAULT NULL,
  `curriculum` int(11) DEFAULT NULL,
  `finder` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_47v1mbmh1hibj36yfp8jkplyq` (`email`),
  KEY `FK_qilb1l0o66abqy9o4fk8accvs` (`curriculum`),
  KEY `FK_s80hn9dk7bcwsqotvtoo6wxr3` (`finder`),
  KEY `FK_jpa4nvxb706tgsd90160obc6r` (`user_account`),
  CONSTRAINT `FK_jpa4nvxb706tgsd90160obc6r` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`),
  CONSTRAINT `FK_qilb1l0o66abqy9o4fk8accvs` FOREIGN KEY (`curriculum`) REFERENCES `curriculum` (`id`),
  CONSTRAINT `FK_s80hn9dk7bcwsqotvtoo6wxr3` FOREIGN KEY (`finder`) REFERENCES `finder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `handy_worker`
--

LOCK TABLES `handy_worker` WRITE;
/*!40000 ALTER TABLE `handy_worker` DISABLE KEYS */;
/*!40000 ALTER TABLE `handy_worker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('domain_entity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mail_box`
--

DROP TABLE IF EXISTS `mail_box`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mail_box` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `is_default` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mail_box`
--

LOCK TABLES `mail_box` WRITE;
/*!40000 ALTER TABLE `mail_box` DISABLE KEYS */;
INSERT INTO `mail_box` VALUES (31,0,'','inBox'),(32,0,'','outBox'),(33,0,'','spamBox'),(34,0,'','trashBox');
/*!40000 ALTER TABLE `mail_box` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_email_receiver`
--

DROP TABLE IF EXISTS `message_email_receiver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_email_receiver` (
  `message` int(11) NOT NULL,
  `email_receiver` varchar(255) DEFAULT NULL,
  KEY `FK_liutqmbcxyepo12ubskovbcq8` (`message`),
  CONSTRAINT `FK_liutqmbcxyepo12ubskovbcq8` FOREIGN KEY (`message`) REFERENCES `message` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_email_receiver`
--

LOCK TABLES `message_email_receiver` WRITE;
/*!40000 ALTER TABLE `message_email_receiver` DISABLE KEYS */;
/*!40000 ALTER TABLE `message_email_receiver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_mail_boxes`
--

DROP TABLE IF EXISTS `message_mail_boxes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_mail_boxes` (
  `messages` int(11) NOT NULL,
  `mail_boxes` int(11) NOT NULL,
  KEY `FK_knabsedyrdky6teljqipc93ey` (`mail_boxes`),
  KEY `FK_gahit8qsthpa89hjot9g5xpb3` (`messages`),
  CONSTRAINT `FK_gahit8qsthpa89hjot9g5xpb3` FOREIGN KEY (`messages`) REFERENCES `message` (`id`),
  CONSTRAINT `FK_knabsedyrdky6teljqipc93ey` FOREIGN KEY (`mail_boxes`) REFERENCES `mail_box` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_mail_boxes`
--

LOCK TABLES `message_mail_boxes` WRITE;
/*!40000 ALTER TABLE `message_mail_boxes` DISABLE KEYS */;
/*!40000 ALTER TABLE `message_mail_boxes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `miscellaneous_record`
--

DROP TABLE IF EXISTS `miscellaneous_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `miscellaneous_record` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `miscellaneous_record`
--

LOCK TABLES `miscellaneous_record` WRITE;
/*!40000 ALTER TABLE `miscellaneous_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `miscellaneous_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `note`
--

DROP TABLE IF EXISTS `note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `note` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `comment_customer` varchar(255) DEFAULT NULL,
  `comment_handy_worker` varchar(255) DEFAULT NULL,
  `comment_referee` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `customer` int(11) DEFAULT NULL,
  `handy_worker` int(11) DEFAULT NULL,
  `report` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bqbsuyl5qhcp3nwhc9rh9lloq` (`customer`),
  KEY `FK_kkhtok85tukm5ak78pf220ukm` (`handy_worker`),
  KEY `FK_jk2abb4f3s4qx304y1uwq59pi` (`report`),
  CONSTRAINT `FK_jk2abb4f3s4qx304y1uwq59pi` FOREIGN KEY (`report`) REFERENCES `report` (`id`),
  CONSTRAINT `FK_bqbsuyl5qhcp3nwhc9rh9lloq` FOREIGN KEY (`customer`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_kkhtok85tukm5ak78pf220ukm` FOREIGN KEY (`handy_worker`) REFERENCES `handy_worker` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `note`
--

LOCK TABLES `note` WRITE;
/*!40000 ALTER TABLE `note` DISABLE KEYS */;
/*!40000 ALTER TABLE `note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal_record`
--

DROP TABLE IF EXISTS `personal_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personal_record` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `linked_in` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_record`
--

LOCK TABLES `personal_record` WRITE;
/*!40000 ALTER TABLE `personal_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `personal_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phase`
--

DROP TABLE IF EXISTS `phase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phase` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `fix_up` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_akmj8r6jafn9h988wjii3owxr` (`fix_up`),
  CONSTRAINT `FK_akmj8r6jafn9h988wjii3owxr` FOREIGN KEY (`fix_up`) REFERENCES `fix_up` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phase`
--

LOCK TABLES `phase` WRITE;
/*!40000 ALTER TABLE `phase` DISABLE KEYS */;
/*!40000 ALTER TABLE `phase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professional_record`
--

DROP TABLE IF EXISTS `professional_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `professional_record` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `start` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professional_record`
--

LOCK TABLES `professional_record` WRITE;
/*!40000 ALTER TABLE `professional_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `professional_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quolet`
--

DROP TABLE IF EXISTS `quolet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quolet` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `is_final` bit(1) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `customer` int(11) DEFAULT NULL,
  `fix_up` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fh0m3o9o0snh4d7rqrt5h30m7` (`customer`),
  KEY `FK_i11qb7h8rrdor3mkk96i4do9x` (`fix_up`),
  CONSTRAINT `FK_i11qb7h8rrdor3mkk96i4do9x` FOREIGN KEY (`fix_up`) REFERENCES `fix_up` (`id`),
  CONSTRAINT `FK_fh0m3o9o0snh4d7rqrt5h30m7` FOREIGN KEY (`customer`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quolet`
--

LOCK TABLES `quolet` WRITE;
/*!40000 ALTER TABLE `quolet` DISABLE KEYS */;
/*!40000 ALTER TABLE `quolet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `referee`
--

DROP TABLE IF EXISTS `referee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `referee` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_banned` bit(1) DEFAULT NULL,
  `is_suspicious` bit(1) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r0pqap5x9e2rkp3g2r2r6fcq1` (`email`),
  KEY `FK_303c1oipw0t6mbnnpvtfv70w5` (`user_account`),
  CONSTRAINT `FK_303c1oipw0t6mbnnpvtfv70w5` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `referee`
--

LOCK TABLES `referee` WRITE;
/*!40000 ALTER TABLE `referee` DISABLE KEYS */;
/*!40000 ALTER TABLE `referee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `attachment` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_final` bit(1) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `complaint` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_48rqaecflpcs8unotw4drrtfw` (`complaint`),
  CONSTRAINT `FK_48rqaecflpcs8unotw4drrtfw` FOREIGN KEY (`complaint`) REFERENCES `complaint` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section`
--

DROP TABLE IF EXISTS `section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `section` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `number` int(11) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `tutorial` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6tcpk427cxiaiglbxvybc9fjh` (`tutorial`),
  CONSTRAINT `FK_6tcpk427cxiaiglbxvybc9fjh` FOREIGN KEY (`tutorial`) REFERENCES `tutorial` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section`
--

LOCK TABLES `section` WRITE;
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
/*!40000 ALTER TABLE `section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `social_profile`
--

DROP TABLE IF EXISTS `social_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `social_profile` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `link` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nick` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `social_profile`
--

LOCK TABLES `social_profile` WRITE;
/*!40000 ALTER TABLE `social_profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `social_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sponsor`
--

DROP TABLE IF EXISTS `sponsor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sponsor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_banned` bit(1) DEFAULT NULL,
  `is_suspicious` bit(1) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7inh7wiji1x5vpu5u3vh0funf` (`email`),
  KEY `FK_du2w5ldt8rvlvxvtr7vyxk7g3` (`user_account`),
  CONSTRAINT `FK_du2w5ldt8rvlvxvtr7vyxk7g3` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sponsor`
--

LOCK TABLES `sponsor` WRITE;
/*!40000 ALTER TABLE `sponsor` DISABLE KEYS */;
/*!40000 ALTER TABLE `sponsor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sponsorship`
--

DROP TABLE IF EXISTS `sponsorship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sponsorship` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `banner` varchar(255) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `cvv` int(11) DEFAULT NULL,
  `month` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `target` varchar(255) DEFAULT NULL,
  `sponsor` int(11) NOT NULL,
  `tutorial` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_huglhkud0ihqdljyou4eshra6` (`sponsor`),
  KEY `FK_hddl83ddicym7ft1xmg89d4c6` (`tutorial`),
  CONSTRAINT `FK_hddl83ddicym7ft1xmg89d4c6` FOREIGN KEY (`tutorial`) REFERENCES `tutorial` (`id`),
  CONSTRAINT `FK_huglhkud0ihqdljyou4eshra6` FOREIGN KEY (`sponsor`) REFERENCES `sponsor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sponsorship`
--

LOCK TABLES `sponsorship` WRITE;
/*!40000 ALTER TABLE `sponsorship` DISABLE KEYS */;
/*!40000 ALTER TABLE `sponsorship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutorial`
--

DROP TABLE IF EXISTS `tutorial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tutorial` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `moment` datetime DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `handy_worker` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_h3kf333e47c4tsjbt69k121gv` (`handy_worker`),
  CONSTRAINT `FK_h3kf333e47c4tsjbt69k121gv` FOREIGN KEY (`handy_worker`) REFERENCES `handy_worker` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tutorial`
--

LOCK TABLES `tutorial` WRITE;
/*!40000 ALTER TABLE `tutorial` DISABLE KEYS */;
/*!40000 ALTER TABLE `tutorial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `is_banned` bit(1) DEFAULT NULL,
  `is_suspicious` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_castjbvpeeus0r8lbpehiu0e4` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (29,0,NULL,NULL,'21232f297a57a5a743894a0e4a801fc3','admin');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account_authorities`
--

DROP TABLE IF EXISTS `user_account_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account_authorities` (
  `user_account` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_pao8cwh93fpccb0bx6ilq6gsl` (`user_account`),
  CONSTRAINT `FK_pao8cwh93fpccb0bx6ilq6gsl` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account_authorities`
--

LOCK TABLES `user_account_authorities` WRITE;
/*!40000 ALTER TABLE `user_account_authorities` DISABLE KEYS */;
INSERT INTO `user_account_authorities` VALUES (29,'ADMIN');
/*!40000 ALTER TABLE `user_account_authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warranty`
--

DROP TABLE IF EXISTS `warranty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `warranty` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `is_final` bit(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warranty`
--

LOCK TABLES `warranty` WRITE;
/*!40000 ALTER TABLE `warranty` DISABLE KEYS */;
/*!40000 ALTER TABLE `warranty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warranty_laws`
--

DROP TABLE IF EXISTS `warranty_laws`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `warranty_laws` (
  `warranty` int(11) NOT NULL,
  `laws` varchar(255) DEFAULT NULL,
  KEY `FK_ssb1vpe3jaukg4rim19cem3wu` (`warranty`),
  CONSTRAINT `FK_ssb1vpe3jaukg4rim19cem3wu` FOREIGN KEY (`warranty`) REFERENCES `warranty` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warranty_laws`
--

LOCK TABLES `warranty_laws` WRITE;
/*!40000 ALTER TABLE `warranty_laws` DISABLE KEYS */;
/*!40000 ALTER TABLE `warranty_laws` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warranty_terms`
--

DROP TABLE IF EXISTS `warranty_terms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `warranty_terms` (
  `warranty` int(11) NOT NULL,
  `terms` varchar(255) DEFAULT NULL,
  KEY `FK_9bnyykbd4x9g91q8ohpkwou3c` (`warranty`),
  CONSTRAINT `FK_9bnyykbd4x9g91q8ohpkwou3c` FOREIGN KEY (`warranty`) REFERENCES `warranty` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warranty_terms`
--

LOCK TABLES `warranty_terms` WRITE;
/*!40000 ALTER TABLE `warranty_terms` DISABLE KEYS */;
/*!40000 ALTER TABLE `warranty_terms` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-01 19:10:50
