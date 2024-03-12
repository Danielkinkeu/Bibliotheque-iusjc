CREATE DATABASE  IF NOT EXISTS `gestionintervention` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gestionintervention`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: gestionintervention
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `departement`
--

DROP TABLE IF EXISTS `departement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departement` (
  `id_departement` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_departement`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departement`
--

LOCK TABLES `departement` WRITE;
/*!40000 ALTER TABLE `departement` DISABLE KEYS */;
INSERT INTO `departement` VALUES (1,'scolarite');
/*!40000 ALTER TABLE `departement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `email`
--

DROP TABLE IF EXISTS `email`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `email` (
  `id_email` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `piece_jointe` tinyblob,
  `subject` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_email`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email`
--

LOCK TABLES `email` WRITE;
/*!40000 ALTER TABLE `email` DISABLE KEYS */;
INSERT INTO `email` VALUES (1,'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :releve de note.\n		Nous comptons sur votre expertise et votre engage','jordan.belom@institutsaintjean.org',NULL,'NOUVELLE DEMANDE D\'INTERVENTION'),(2,'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :releve de note.\n		Nous comptons sur votre expertise et votre engage','jordan.belom@institutsaintjean.org',NULL,'NOUVELLE DEMANDE D\'INTERVENTION'),(3,'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :releve de note.\n		Nous comptons sur votre expertise et votre engage','jordan.belom@institutsaintjean.org',NULL,'NOUVELLE DEMANDE D\'INTERVENTION'),(4,'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :releve de note.\n		Nous comptons sur votre expertise et votre engage','jordan.belom@institutsaintjean.org',NULL,'NOUVELLE DEMANDE D\'INTERVENTION'),(5,'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :releve de note.\n		Nous comptons sur votre expertise et votre engage','jordan.belom@institutsaintjean.org',NULL,'NOUVELLE DEMANDE D\'INTERVENTION'),(6,'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :releve de note.\n		Nous comptons sur votre expertise et votre engage','jordan.belom@institutsaintjean.org',NULL,'NOUVELLE DEMANDE D\'INTERVENTION');
/*!40000 ALTER TABLE `email` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `email_message`
--

DROP TABLE IF EXISTS `email_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `email_message` (
  `id_email` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `piece_jointe` tinyblob,
  `subject` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_email`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_message`
--

LOCK TABLES `email_message` WRITE;
/*!40000 ALTER TABLE `email_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `email_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `etudiant`
--

DROP TABLE IF EXISTS `etudiant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `etudiant` (
  `code` bigint NOT NULL AUTO_INCREMENT,
  `code_authentification` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `matricule` varchar(255) DEFAULT NULL,
  `nom_etudiant` varchar(255) DEFAULT NULL,
  `prenom_etudiant` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `etudiant`
--

LOCK TABLES `etudiant` WRITE;
/*!40000 ALTER TABLE `etudiant` DISABLE KEYS */;
INSERT INTO `etudiant` VALUES (1,'111','nounamoestrella@gmail.com','2021i009','nouna','moestrella'),(2,'233','belomjordan@gmail.com','2021i008','jordan','belom');
/*!40000 ALTER TABLE `etudiant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `intervention`
--

DROP TABLE IF EXISTS `intervention`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `intervention` (
  `id_intervention` bigint NOT NULL AUTO_INCREMENT,
  `date_creation_intervention` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `file` varchar(255) DEFAULT NULL,
  `motif` int DEFAULT NULL,
  `nom_departement` int DEFAULT NULL,
  `statut` varchar(255) DEFAULT NULL,
  `departement_id` bigint DEFAULT NULL,
  `etudiant_id` bigint DEFAULT NULL,
  `personnel_id` bigint DEFAULT NULL,
  `id_sous_intervention` bigint DEFAULT NULL,
  PRIMARY KEY (`id_intervention`),
  KEY `FKnckksgrydtdqkw48llbmiy1xp` (`departement_id`),
  KEY `FKq5h9ytleq5d0gxh3c5veoglj8` (`etudiant_id`),
  KEY `FKat9q9fa0k8n5k1dkug8x3jgf0` (`personnel_id`),
  KEY `FK47490ihfwtcf6xooyc89bhakc` (`id_sous_intervention`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `intervention`
--

LOCK TABLES `intervention` WRITE;
/*!40000 ALTER TABLE `intervention` DISABLE KEYS */;
INSERT INTO `intervention` VALUES (1,NULL,'sdasdasdas',NULL,NULL,NULL,'traite',1,1,1,1),(2,'2023-10-14 21:10:30','EmailTest',NULL,NULL,NULL,'nonTraite',1,1,NULL,1),(3,'2023-10-14 21:10:45','EmailTest',NULL,NULL,NULL,'nonTraite',1,1,NULL,1),(4,'2023-10-14 21:10:54','EmailTest',NULL,NULL,NULL,'nonTraite',1,1,NULL,1),(5,'2023-10-15 16:14:02','EmailTest',NULL,NULL,NULL,'nonTraite',1,1,NULL,1),(6,'2023-10-15 16:27:03','EmailTest',NULL,NULL,NULL,'nonTraite',1,1,NULL,1),(7,'2023-10-15 17:57:16','EmailTest',NULL,NULL,NULL,'nonTraite',1,1,NULL,1);
/*!40000 ALTER TABLE `intervention` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `piecejointe`
--

DROP TABLE IF EXISTS `piecejointe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `piecejointe` (
  `id_piece_jointe` bigint NOT NULL AUTO_INCREMENT,
  `chemin_stockage` varchar(255) DEFAULT NULL,
  `nom_fichier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_piece_jointe`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `piecejointe`
--

LOCK TABLES `piecejointe` WRITE;
/*!40000 ALTER TABLE `piecejointe` DISABLE KEYS */;
/*!40000 ALTER TABLE `piecejointe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id_role` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id_role`),
  UNIQUE KEY `UK_ofx66keruapi6vyqpv6f2or37` (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sous_intervention`
--

DROP TABLE IF EXISTS `sous_intervention`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sous_intervention` (
  `id_sous_intervention` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `id_departement` bigint DEFAULT NULL,
  PRIMARY KEY (`id_sous_intervention`),
  KEY `FKgnwwhqdtptw403e1fk3ws7rek` (`id_departement`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sous_intervention`
--

LOCK TABLES `sous_intervention` WRITE;
/*!40000 ALTER TABLE `sous_intervention` DISABLE KEYS */;
INSERT INTO `sous_intervention` VALUES (1,'releve de note',1);
/*!40000 ALTER TABLE `sous_intervention` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utilisateur` (
  `code` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `nom_personnel` varchar(255) DEFAULT NULL,
  `prenom_personnel` varchar(255) DEFAULT NULL,
  `departement_id` bigint DEFAULT NULL,
  PRIMARY KEY (`code`),
  KEY `FKcvbl11mgy6f9etn3trbxoh79j` (`departement_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilisateur`
--

LOCK TABLES `utilisateur` WRITE;
/*!40000 ALTER TABLE `utilisateur` DISABLE KEYS */;
INSERT INTO `utilisateur` VALUES (1,'etiennenkot2@gmail.com','nkot','etienne',1);
/*!40000 ALTER TABLE `utilisateur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'gestionintervention'
--

--
-- Dumping routines for database 'gestionintervention'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-15 21:38:31
