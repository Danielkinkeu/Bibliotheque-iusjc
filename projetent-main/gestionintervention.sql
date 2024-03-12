-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 26 fév. 2024 à 15:07
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.2.0
CREATE database gestionintervention;
USE gestionintervention;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gestionintervention`
--

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `id_categorie` bigint(20) NOT NULL,
  `intitule_categorie` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`id_categorie`, `intitule_categorie`) VALUES
(1, 'Portail Captif'),
(2, 'Mot de passe'),
(3, 'Demande');

-- --------------------------------------------------------

--
-- Structure de la table `departement`
--

CREATE TABLE `departement` (
  `id_departement` bigint(20) NOT NULL,
  `intitule_departement` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `departement`
--

INSERT INTO `departement` (`id_departement`, `intitule_departement`, `description`) VALUES
(1, 'IT', NULL),
(2, 'Pedagogie', NULL),
(3, 'Comptabilite', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `email`
--

CREATE TABLE `email` (
  `id_email` bigint(20) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `piece_jointe` tinyblob DEFAULT NULL,
  `email_etudiant` varchar(255) DEFAULT NULL,
  `email_personnel` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `email`
--

INSERT INTO `email` (`id_email`, `content`, `email`, `subject`, `piece_jointe`, `email_etudiant`, `email_personnel`) VALUES
(1, 'Cher(e) étudiant(e), \n\n		Nous avons bien reçu votre demande d\'intervention. Nous sommes heureux de vous informer qu\'elle a été prise en charge par :root root ayant pour Emailjerry.wafo@institutsaintjean.org.\n		Notre équipe se mobilise pour résoudre votre ', 'wafokamguejerry@gmail.com', 'PRISE EN CHARGE DE VOTRE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(2, 'Cher(e) étudiant(e), BOoooooooooouyaaa\r\n\r\n		Nous sommes heureux de vous informer que le traitement de votre demande d\'intervention a été terminé avec succès.\r\n		Nous espérons que le problème pour lequel vous avez demandé de l\'aide a été résolu de manière ', 'wafokamguejerry@gmail.com', 'FIN DE LA PRISE EN CHARGE DE VOTRE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(3, 'Cher(e) étudiant(e), \n\n		Nous avons bien reçu votre demande d\'intervention. Nous sommes heureux de vous informer qu\'elle a été prise en charge par :root root ayant pour Emailjerry.wafo@institutsaintjean.org.\n		Notre équipe se mobilise pour résoudre votre ', 'wafokamguejerry@gmail.com', 'PRISE EN CHARGE DE VOTRE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(4, 'Cher(e) étudiant(e), \r\n\r\n		Nous sommes heureux de vous informer que le traitement de votre demande d\'intervention a été terminé avec succès.\r\n		Nous espérons que le problème pour lequel vous avez demandé de l\'aide a été résolu de manière satisfaisante. Si', 'wafokamguejerry@gmail.com', 'FIN DE LA PRISE EN CHARGE DE VOTRE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(5, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Creation de mot de passe.\n		Nous comptons sur votre expertise et vo', 'jerry.wafo@institutsaintjean.org', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(6, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Creation de mot de passe.\n		Nous comptons sur votre expertise et vo', 'etiennenkot2@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(7, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Reinitialisation du mot de passe.\n		Nous comptons sur votre experti', 'jerry.wafo@institutsaintjean.org', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(8, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Reinitialisation du mot de passe.\n		Nous comptons sur votre experti', 'etiennenkot2@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(9, 'Cher(e) étudiant(e), \n\n		Nous avons bien reçu votre demande d\'intervention. Nous sommes heureux de vous informer qu\'elle a été prise en charge par :etiennenkot2@gmail.com etiennenkot2@gmail.com ayant pour Emailetiennenkot2@gmail.com.\n		Notre équipe se mob', 'nounamoestrella@gmail.com', 'PRISE EN CHARGE DE VOTRE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(10, 'Cher(e) étudiant(e), \r\n\r\n		Nous sommes heureux de vous informer que le traitement de votre demande d\'intervention a été terminé avec succès.\r\n		Nous espérons que le problème pour lequel vous avez demandé de l\'aide a été résolu de manière satisfaisante. Si', 'nounamoestrella@gmail.com', 'FIN DE LA PRISE EN CHARGE DE VOTRE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(11, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Creation de mot de passe.\n		Nous comptons sur votre expertise et vo', 'jerry.wafo@institutsaintjean.org', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(12, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Creation de mot de passe.\n		Nous comptons sur votre expertise et vo', 'etiennenkot2@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(13, 'Cher(e) étudiant(e), \n\n		Nous avons bien reçu votre demande d\'intervention. Nous sommes heureux de vous informer qu\'elle a été prise en charge par :etiennenkot2@gmail.com etiennenkot2@gmail.com ayant pour Emailetiennenkot2@gmail.com.\n		Notre équipe se mob', 'nounamoestrella@gmail.com', 'PRISE EN CHARGE DE VOTRE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(14, 'Cher(e) étudiant(e), \r\n\r\n		Nous sommes heureux de vous informer que le traitement de votre demande d\'intervention a été terminé avec succès.\r\n		Nous espérons que le problème pour lequel vous avez demandé de l\'aide a été résolu de manière satisfaisante. Si', 'nounamoestrella@gmail.com', 'FIN DE LA PRISE EN CHARGE DE VOTRE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(15, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Creation de mot de passe.\n		Nous comptons sur votre expertise et vo', 'jerry.wafo@institutsaintjean.org', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(16, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Creation de mot de passe.\n		Nous comptons sur votre expertise et vo', 'etiennenkot2@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(17, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Creation de mot de passe.\n		Nous comptons sur votre expertise et vo', 'jerry.wafo@institutsaintjean.org', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(18, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Creation de mot de passe.\n		Nous comptons sur votre expertise et vo', 'etiennenkot2@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(19, 'Cher(e) étudiant(e), \n\n		Nous avons bien reçu votre demande d\'intervention. Nous sommes heureux de vous informer qu\'elle a été prise en charge par :etiennenkot2@gmail.com etiennenkot2@gmail.com ayant pour Emailetiennenkot2@gmail.com.\n		Notre équipe se mob', 'nounamoestrella@gmail.com', 'PRISE EN CHARGE DE VOTRE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(20, 'Cher(e) étudiant(e), \n\n		Nous avons bien reçu votre demande d\'intervention. Nous sommes heureux de vous informer qu\'elle a été prise en charge par :etiennenkot2@gmail.com etiennenkot2@gmail.com ayant pour Emailetiennenkot2@gmail.com.\n		Notre équipe se mob', 'nounamoestrella@gmail.com', 'PRISE EN CHARGE DE VOTRE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(21, 'Cher(e) étudiant(e), \n\n		Nous avons bien reçu votre demande d\'intervention. Nous sommes heureux de vous informer qu\'elle a été prise en charge par :etiennenkot2@gmail.com etiennenkot2@gmail.com ayant pour Emailetiennenkot2@gmail.com.\n		Notre équipe se mob', 'nounamoestrella@gmail.com', 'PRISE EN CHARGE DE VOTRE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(22, 'Cher(e) étudiant(e), \r\n\r\n		Nous sommes heureux de vous informer que le traitement de votre demande d\'intervention a été terminé avec succès.\r\n		Nous espérons que le problème pour lequel vous avez demandé de l\'aide a été résolu de manière satisfaisante. Si', 'nounamoestrella@gmail.com', 'FIN DE LA PRISE EN CHARGE DE VOTRE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(23, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Creation de mot de passe.\n		Nous comptons sur votre expertise et vo', 'jerry.wafo@institutsaintjean.org', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(24, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Creation de mot de passe.\n		Nous comptons sur votre expertise et vo', 'etiennenkot2@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(25, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Creation de mot de passe.\n		Nous comptons sur votre expertise et vo', 'jerry.wafo@institutsaintjean.org', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(26, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Creation de mot de passe.\n		Nous comptons sur votre expertise et vo', 'etiennenkot2@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(27, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Reinitialisation du mot de passe.\n		Nous comptons sur votre experti', 'jerry.wafo@institutsaintjean.org', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(28, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Reinitialisation du mot de passe.\n		Nous comptons sur votre experti', 'etiennenkot2@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(29, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Creation de mot de passe.\n		Nous comptons sur votre expertise et vo', 'wafokamguejerry@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(30, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Creation de mot de passe.\n		Nous comptons sur votre expertise et vo', 'wafokamguejerry@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(31, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Compte egare.\n		Nous comptons sur votre expertise et votre engageme', 'wafokamguejerry@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(32, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Compte egare.\n		Nous comptons sur votre expertise et votre engageme', 'wafokamguejerry@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(33, 'Cher(e) étudiant(e), \n\n		Nous avons bien reçu votre demande d\'intervention. Nous sommes heureux de vous informer qu\'elle a été prise en charge par :etiennenkot2@gmail.com etiennenkot2@gmail.com ayant pour Emailwafokamguejerry@gmail.com.\n		Notre équipe se ', 'jerry.wafo@institutsaintjean.org', 'PRISE EN CHARGE DE VOTRE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(34, 'Cher(e) étudiant(e), \r\n\r\n		Nous sommes heureux de vous informer que le traitement de votre demande d\'intervention a été terminé avec succès.\r\n		Nous espérons que le problème pour lequel vous avez demandé de l\'aide a été résolu de manière satisfaisante. Si', 'jerry.wafo@institutsaintjean.org', 'FIN DE LA PRISE EN CHARGE DE VOTRE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(35, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Compte egare.\n		Nous comptons sur votre expertise et votre engageme', 'wafokamguejerry@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(36, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Compte egare.\n		Nous comptons sur votre expertise et votre engageme', 'wafokamguejerry@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(37, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Compte egare.\n		Nous comptons sur votre expertise et votre engageme', 'wafokamguejerry@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(38, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Compte egare.\n		Nous comptons sur votre expertise et votre engageme', 'wafokamguejerry@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(39, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Compte egare.\n		Nous comptons sur votre expertise et votre engageme', 'wafokamguejerry@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(40, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Compte egare.\n		Nous comptons sur votre expertise et votre engageme', 'wafokamguejerry@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(41, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Compte egare.\n		Nous comptons sur votre expertise et votre engageme', 'wafokamguejerry@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(42, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Compte egare.\n		Nous comptons sur votre expertise et votre engageme', 'wafokamguejerry@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(43, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Compte egare.\n		Nous comptons sur votre expertise et votre engageme', 'wafokamguejerry@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(44, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Compte egare.\n		Nous comptons sur votre expertise et votre engageme', 'wafokamguejerry@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(45, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Compte egare.\n		Nous comptons sur votre expertise et votre engageme', 'wafokamguejerry@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(46, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Compte egare.\n		Nous comptons sur votre expertise et votre engageme', 'wafokamguejerry@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(47, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Compte egare.\n		Nous comptons sur votre expertise et votre engageme', 'wafokamguejerry@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(48, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Compte egare.\n		Nous comptons sur votre expertise et votre engageme', 'wafokamguejerry@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(49, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Compte egare.\n		Nous comptons sur votre expertise et votre engageme', 'wafokamguejerry@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(50, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Compte egare.\n		Nous comptons sur votre expertise et votre engageme', 'wafokamguejerry@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(51, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Reinitialisation du mot de passe institutionnelle.\n		Nous comptons ', 'wafokamguejerry@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(52, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Reinitialisation du mot de passe institutionnelle.\n		Nous comptons ', 'wafokamguejerry@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(53, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Compte egare.\n		Nous comptons sur votre expertise et votre engageme', 'wafokamguejerry@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(54, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Compte egare.\n		Nous comptons sur votre expertise et votre engageme', 'wafokamguejerry@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(55, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Releve de note annuel.\n		Nous comptons sur votre expertise et votre', 'belomjordan@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(56, 'Cher(e) étudiant(e), \n\n		Nous avons bien reçu votre demande d\'intervention. Nous sommes heureux de vous informer qu\'elle a été prise en charge par :etiennenkot2@gmail.com etiennenkot2@gmail.com ayant pour Emailwafokamguejerry@gmail.com.\n		Notre équipe se ', 'jerry.wafo@institutsaintjean.org', 'PRISE EN CHARGE DE VOTRE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(57, NULL, 'jerry.wafo@institutsaintjean.org', 'FIN DE LA PRISE EN CHARGE DE VOTRE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(58, NULL, 'jerry.wafo@institutsaintjean.org', 'FIN DE LA PRISE EN CHARGE DE VOTRE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(59, NULL, 'jerry.wafo@institutsaintjean.org', 'FIN DE LA PRISE EN CHARGE DE VOTRE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(60, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Creation de compte.\n		Nous comptons sur votre expertise et votre en', 'wafokamguejerry@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(61, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Releve de note annuel.\n		Nous comptons sur votre expertise et votre', 'wafokamguejerry@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(62, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Releve de note annuel.\n		Nous comptons sur votre expertise et votre', 'belomjordan@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(63, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Releve de note annuel.\n		Nous comptons sur votre expertise et votre', 'wafokamguejerry@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(64, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Releve de note annuel.\n		Nous comptons sur votre expertise et votre', 'belomjordan@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(65, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Releve de note annuel.\n		Nous comptons sur votre expertise et votre', 'wafokamguejerry@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(66, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Releve de note annuel.\n		Nous comptons sur votre expertise et votre', 'wafokamguejerry@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(67, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Releve de note annuel.\n		Nous comptons sur votre expertise et votre', 'belomjordan@gmail.com', 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(68, 'Cher(e) étudiant(e), \n\n		Nous avons bien reçu votre demande d\'intervention. Nous sommes heureux de vous informer qu\'elle a été prise en charge par :etiennenkot2@gmail.com etiennenkot2@gmail.com ayant pour Emailwafokamguejerry@gmail.com.\n		Notre équipe se ', 'jerry.wafo@institutsaintjean.org', 'PRISE EN CHARGE DE VOTRE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(69, 'Cher(e) étudiant(e), \n\n		Nous avons bien reçu votre demande d\'intervention. Nous sommes heureux de vous informer qu\'elle a été prise en charge par :etiennenkot2@gmail.com etiennenkot2@gmail.com ayant pour Emailwafokamguejerry@gmail.com.\n		Notre équipe se ', 'jerry.wafo@institutsaintjean.org', 'PRISE EN CHARGE DE VOTRE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(70, 'Cher(e) étudiant(e), \r\n      \r\n      	Nous sommes heureux de vous informer que le traitement de votre demande d\'intervention a été terminé avec succès.\r\n\r\n      	Nous espérons que le problème pour lequel vous avez demandé de l\'aide a été résolu de manière', 'jerry.wafo@institutsaintjean.org', 'FIN DE LA PRISE EN CHARGE DE VOTRE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(71, 'Cher(e) étudiant(e), \r\n      \r\n      	Nous sommes heureux de vous informer que le traitement de votre demande d\'intervention a été terminé avec succès.\r\n\r\n      	Nous espérons que le problème pour lequel vous avez demandé de l\'aide a été résolu de manière', 'jerry.wafo@institutsaintjean.org', 'FIN DE LA PRISE EN CHARGE DE VOTRE DEMANDE D\'INTERVENTION', NULL, NULL, NULL),
(72, 'Cher(e) étudiant(e), \n\n		Nous avons bien reçu votre demande d\'intervention. Nous sommes heureux de vous informer qu\'elle a été prise en charge par :etiennenkot2@gmail.com etiennenkot2@gmail.com ayant pour Emailwafokamguejerry@gmail.com.\n		Notre équipe se ', 'jerry.wafo@institutsaintjean.org', 'PRISE EN CHARGE DE VOTRE DEMANDE D\'INTERVENTION', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `email_message`
--

CREATE TABLE `email_message` (
  `id_email` bigint(20) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `piece_jointe` tinyblob DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `email_etudiant` varchar(255) DEFAULT NULL,
  `email_personnel` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `email_message`
--

INSERT INTO `email_message` (`id_email`, `content`, `email`, `piece_jointe`, `subject`, `email_etudiant`, `email_personnel`) VALUES
(14, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Releve de note annuel.\n		Nous comptons sur votre expertise et votre', 'belomjordan@gmail.com', NULL, 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL),
(15, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Releve de note annuel.\n		Nous comptons sur votre expertise et votre', 'belomjordan@gmail.com', NULL, 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL),
(13, 'Cher Monsieur/Madame ,\n\n		Nous avons le plaisir de vous informer qu\'une nouvelle demande d\'intervention vient d\'être reçue pour votre département. L\'objet de cette demande est le suivant :Creation de compte.\n		Nous comptons sur votre expertise et votre en', 'wafokamguejerry@gmail.com', NULL, 'NOUVELLE DEMANDE D\'INTERVENTION', NULL, NULL),
(10, NULL, 'jerry.wafo@institutsaintjean.org', NULL, 'FIN DE LA PRISE EN CHARGE DE VOTRE DEMANDE D\'INTERVENTION', NULL, NULL),
(11, NULL, 'jerry.wafo@institutsaintjean.org', NULL, 'FIN DE LA PRISE EN CHARGE DE VOTRE DEMANDE D\'INTERVENTION', NULL, NULL),
(12, NULL, 'jerry.wafo@institutsaintjean.org', NULL, 'FIN DE LA PRISE EN CHARGE DE VOTRE DEMANDE D\'INTERVENTION', NULL, NULL),
(16, 'Cher(e) étudiant(e), \r\n      \r\n      	Nous sommes heureux de vous informer que le traitement de votre demande d\'intervention a été terminé avec succès.\r\n\r\n      	Nous espérons que le problème pour lequel vous avez demandé de l\'aide a été résolu de manière', 'jerry.wafo@institutsaintjean.org', NULL, 'FIN DE LA PRISE EN CHARGE DE VOTRE DEMANDE D\'INTERVENTION', NULL, NULL),
(17, 'Cher(e) étudiant(e), \r\n      \r\n      	Nous sommes heureux de vous informer que le traitement de votre demande d\'intervention a été terminé avec succès.\r\n\r\n      	Nous espérons que le problème pour lequel vous avez demandé de l\'aide a été résolu de manière', 'jerry.wafo@institutsaintjean.org', NULL, 'FIN DE LA PRISE EN CHARGE DE VOTRE DEMANDE D\'INTERVENTION', NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE `etudiant` (
  `code` bigint(20) NOT NULL,
  `code_authentification` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `matricule` varchar(255) DEFAULT NULL,
  `nom_etudiant` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prenom_etudiant` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`code`, `code_authentification`, `email`, `login`, `matricule`, `nom_etudiant`, `password`, `prenom_etudiant`) VALUES
(3, NULL, 'jerry.wafo@institutsaintjean.org', 'nounamoestrella@gmail.com', '2021l056', 'nounamo', NULL, 'estrella'),
(2, NULL, 'jerry.wafo@institutsaintjean.org', 'rootu', '2021i074', 'rootu', NULL, 'rootu');

-- --------------------------------------------------------

--
-- Structure de la table `intervention`
--

CREATE TABLE `intervention` (
  `id_intervention` bigint(20) NOT NULL,
  `date_creation_intervention` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `file` varchar(255) DEFAULT NULL,
  `motif` int(11) DEFAULT NULL,
  `nom_departement` int(11) DEFAULT NULL,
  `statut` varchar(255) DEFAULT NULL,
  `id_categorie` bigint(20) DEFAULT NULL,
  `departement_id` bigint(20) DEFAULT NULL,
  `etudiant_id` bigint(20) DEFAULT NULL,
  `personnel_id` bigint(20) DEFAULT NULL,
  `id_sous_intervention` bigint(20) DEFAULT NULL,
  `is_canceled` bit(1) NOT NULL,
  `is_taken` bit(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `intervention`
--

INSERT INTO `intervention` (`id_intervention`, `date_creation_intervention`, `description`, `file`, `motif`, `nom_departement`, `statut`, `id_categorie`, `departement_id`, `etudiant_id`, `personnel_id`, `id_sous_intervention`, `is_canceled`, `is_taken`) VALUES
(1, '2023-11-01 20:29:19', '', NULL, NULL, NULL, 'traite', 1, 1, 2, 1, 2, b'0', b'0'),
(2, '2023-11-01 20:31:19', 'Password Reinit', NULL, NULL, NULL, 'traite', 1, 1, 2, 1, 1, b'1', b'0'),
(3, '2023-11-02 07:58:06', 'Weshhhhhhhhhhh', NULL, NULL, NULL, 'traite', 1, 1, 3, 1, 2, b'0', b'0'),
(4, '2023-11-02 07:58:36', 'Projet termine', NULL, NULL, NULL, 'traite', 1, 1, 3, 1, 1, b'0', b'0'),
(5, '2023-11-02 08:25:35', 'demande', NULL, NULL, NULL, 'enCours', 1, 1, 3, 1, 2, b'0', b'0'),
(6, '2023-11-09 11:47:03', '', NULL, NULL, NULL, 'enCours', 1, 1, 3, 1, 2, b'0', b'0'),
(7, '2023-11-09 11:47:50', 'test', NULL, NULL, NULL, 'traite', 1, 1, 3, 1, 2, b'0', b'0'),
(8, '2023-11-13 11:16:11', 'fgfdg', NULL, NULL, NULL, 'nonTraite', 1, 1, 3, NULL, 2, b'0', b'0'),
(9, '2023-11-13 11:58:45', 'sdffsdfsdfe', NULL, NULL, NULL, 'nonTraite', 1, 1, 3, NULL, 2, b'0', b'0'),
(10, '2023-11-13 12:00:30', 'bel', NULL, NULL, NULL, 'nonTraite', 1, 1, 3, NULL, 1, b'0', b'0'),
(11, '2023-11-13 12:43:19', 'fv', NULL, NULL, NULL, 'nonTraite', 1, 1, 3, NULL, 2, b'0', b'0'),
(12, '2023-11-13 13:43:52', 'je ne connais plus mes id', NULL, NULL, NULL, 'traite', 1, 1, 3, 1, 1, b'0', b'0'),
(13, '2024-01-12 14:38:02', NULL, NULL, NULL, NULL, 'nonTraite', 2, 1, 2, NULL, 1, b'1', b'0'),
(14, '2024-01-12 14:51:56', NULL, NULL, NULL, NULL, 'nonTraite', 1, 1, 2, NULL, 1, b'1', b'0'),
(15, '2024-01-12 15:06:46', NULL, NULL, NULL, NULL, 'nonTraite', 1, 1, 2, NULL, 1, b'0', b'0'),
(16, '2024-01-12 15:09:58', 'csdfddsdsd', NULL, NULL, NULL, 'nonTraite', 1, 1, 2, NULL, 1, b'0', b'0'),
(17, '2024-01-12 15:13:33', NULL, NULL, NULL, NULL, 'nonTraite', 1, 1, 2, NULL, 1, b'0', b'0'),
(18, '2024-01-12 15:31:28', 'yaaaaaaaaaaaaaaaaaaaaaaaaa', NULL, NULL, NULL, 'nonTraite', 1, 1, 2, NULL, 1, b'1', b'0'),
(19, '2024-01-12 15:35:03', 'yaaaaaaaaaaaaaaaaaaaaaaaaa', NULL, NULL, NULL, 'nonTraite', 1, 1, 2, NULL, 1, b'0', b'0'),
(20, '2024-01-12 15:36:14', '', NULL, NULL, NULL, 'nonTraite', 1, 1, 2, NULL, 1, b'0', b'0'),
(21, '2024-01-12 15:39:11', 'sdvsdfs', NULL, NULL, NULL, 'nonTraite', 2, 1, 2, NULL, 3, b'0', b'0'),
(22, '2024-01-12 15:40:48', 'csdfddsdsd', NULL, NULL, NULL, 'nonTraite', 1, 1, 2, NULL, 1, b'0', b'0'),
(23, '2024-01-13 15:06:13', 'mjbmn', NULL, NULL, NULL, 'traite', 3, 2, 2, 1, 5, b'0', b'0'),
(24, '2024-01-15 10:33:20', 'Com[te egare', NULL, NULL, NULL, 'nonTraite', 1, 1, 2, NULL, 2, b'1', b'0'),
(25, '2024-01-15 10:35:50', 'm.,m', NULL, NULL, NULL, 'nonTraite', 3, 3, 2, NULL, 6, b'1', b'0'),
(26, '2024-01-15 10:38:38', 'annuel', NULL, NULL, NULL, 'nonTraite', 3, 2, 2, NULL, 5, b'0', b'0'),
(27, '2024-01-15 10:43:39', 'bouya', NULL, NULL, NULL, 'traite', 3, 2, 2, 1, 5, b'0', b'1'),
(28, '2024-01-15 13:54:18', 'Presentation Projet tutore', NULL, NULL, NULL, 'enCours', 3, 2, 2, 1, 5, b'0', b'1');

-- --------------------------------------------------------

--
-- Structure de la table `piecejointe`
--

CREATE TABLE `piecejointe` (
  `id_piece_jointe` bigint(20) NOT NULL,
  `contenu` longblob DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `intervention_id` bigint(20) DEFAULT NULL,
  `chemin_stockage` varchar(255) DEFAULT NULL,
  `nom_fichier` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `piecesjointes_email`
--

CREATE TABLE `piecesjointes_email` (
  `id_piece_jointe_email` bigint(20) NOT NULL,
  `contenu` longblob DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `email_id` bigint(20) DEFAULT NULL,
  `email_message_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `roles`
--

CREATE TABLE `roles` (
  `id_role` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `sous_intervention`
--

CREATE TABLE `sous_intervention` (
  `id_sous_intervention` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `id_categorie` bigint(20) DEFAULT NULL,
  `id_departement` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id_user` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `nom` varchar(100) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prenom` varchar(100) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `id_role` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `code` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `nom_personnel` varchar(255) DEFAULT NULL,
  `prenom_personnel` varchar(255) DEFAULT NULL,
  `departement_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`id_categorie`);

--
-- Index pour la table `departement`
--
ALTER TABLE `departement`
  ADD PRIMARY KEY (`id_departement`);

--
-- Index pour la table `email`
--
ALTER TABLE `email`
  ADD PRIMARY KEY (`id_email`);

--
-- Index pour la table `email_message`
--
ALTER TABLE `email_message`
  ADD PRIMARY KEY (`id_email`);

--
-- Index pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD PRIMARY KEY (`code`);

--
-- Index pour la table `intervention`
--
ALTER TABLE `intervention`
  ADD PRIMARY KEY (`id_intervention`),
  ADD KEY `FKmkgsh9m8uvr0ho9id8la8ppto` (`id_categorie`),
  ADD KEY `FKnckksgrydtdqkw48llbmiy1xp` (`departement_id`),
  ADD KEY `FKq5h9ytleq5d0gxh3c5veoglj8` (`etudiant_id`),
  ADD KEY `FKat9q9fa0k8n5k1dkug8x3jgf0` (`personnel_id`),
  ADD KEY `FK47490ihfwtcf6xooyc89bhakc` (`id_sous_intervention`);

--
-- Index pour la table `piecejointe`
--
ALTER TABLE `piecejointe`
  ADD PRIMARY KEY (`id_piece_jointe`),
  ADD KEY `FKbsd7v7ocw9289qs4vlhol57x3` (`intervention_id`);

--
-- Index pour la table `piecesjointes_email`
--
ALTER TABLE `piecesjointes_email`
  ADD PRIMARY KEY (`id_piece_jointe_email`),
  ADD KEY `FK1ytnqid329ppbj8mb7bwlrb10` (`email_id`),
  ADD KEY `FK371b73yo5mqw7n13t2ej7wf73` (`email_message_id`);

--
-- Index pour la table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id_role`),
  ADD UNIQUE KEY `UK_ofx66keruapi6vyqpv6f2or37` (`name`);

--
-- Index pour la table `sous_intervention`
--
ALTER TABLE `sous_intervention`
  ADD PRIMARY KEY (`id_sous_intervention`),
  ADD KEY `FKbxmanokyup90vku9g4rjhd3q5` (`id_categorie`),
  ADD KEY `FKgnwwhqdtptw403e1fk3ws7rek` (`id_departement`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD KEY `FKe0c8aqvw3liin6gxeysj13x2d` (`id_role`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`code`),
  ADD KEY `FKcvbl11mgy6f9etn3trbxoh79j` (`departement_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `id_categorie` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `departement`
--
ALTER TABLE `departement`
  MODIFY `id_departement` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `email`
--
ALTER TABLE `email`
  MODIFY `id_email` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=73;

--
-- AUTO_INCREMENT pour la table `email_message`
--
ALTER TABLE `email_message`
  MODIFY `id_email` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT pour la table `etudiant`
--
ALTER TABLE `etudiant`
  MODIFY `code` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `intervention`
--
ALTER TABLE `intervention`
  MODIFY `id_intervention` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT pour la table `piecejointe`
--
ALTER TABLE `piecejointe`
  MODIFY `id_piece_jointe` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT pour la table `piecesjointes_email`
--
ALTER TABLE `piecesjointes_email`
  MODIFY `id_piece_jointe_email` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `roles`
--
ALTER TABLE `roles`
  MODIFY `id_role` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `sous_intervention`
--
ALTER TABLE `sous_intervention`
  MODIFY `id_sous_intervention` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `code` bigint(20) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
