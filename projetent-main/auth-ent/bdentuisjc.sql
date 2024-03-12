-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mar. 29 nov. 2022 à 15:47
-- Version du serveur : 5.7.36
-- Version de PHP : 7.4.26

use bdentuisj;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bdentuisjc`
--

-- --------------------------------------------------------

--
-- Structure de la table `application`
--

DROP TABLE IF EXISTS `application`;
CREATE TABLE IF NOT EXISTS `application` (
  `apllicationid` bigint(20) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`apllicationid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `application`
--

INSERT INTO `application` (`apllicationid`, `libelle`) VALUES
(1, 'gestionote');

-- --------------------------------------------------------

--
-- Structure de la table `droit`
--

DROP TABLE IF EXISTS `droit`;
CREATE TABLE IF NOT EXISTS `droit` (
  `droitid` bigint(20) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `ecriture` tinyint(1) DEFAULT '0',
  `lecture` tinyint(1) DEFAULT '0',
  `modification` tinyint(1) DEFAULT '0',
  `suppression` tinyint(1) DEFAULT '0',
  `codeapplicationid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`droitid`),
  KEY `codeapplicationid` (`codeapplicationid`)
) ENGINE=InnoDB AUTO_INCREMENT=102855 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `droit`
--

INSERT INTO `droit` (`droitid`, `libelle`, `description`, `ecriture`, `lecture`, `modification`, `suppression`, `codeapplicationid`) VALUES
(102852, 'Releve', 'juste le releve', 1, 1, 1, 1, 1),
(102854, 'lecture', 'juste le releve', 0, 1, 0, 0, 1);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `roleid` bigint(20) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=103103 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`roleid`, `libelle`, `description`) VALUES
(102755, 'Admin', 'rien que le user'),
(103102, 'User', 'rien que le user');

-- --------------------------------------------------------

--
-- Structure de la table `role_droit`
--

DROP TABLE IF EXISTS `role_droit`;
CREATE TABLE IF NOT EXISTS `role_droit` (
  `code_roleid` bigint(20) NOT NULL,
  `code_droitid` bigint(20) NOT NULL,
  KEY `code_roleid` (`code_roleid`),
  KEY `code_droitid` (`code_droitid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `role_droit`
--

INSERT INTO `role_droit` (`code_roleid`, `code_droitid`) VALUES
(103102, 102852),
(102755, 102854);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `userid` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `matricule` varchar(50) DEFAULT NULL,
  `filiere` varchar(50) DEFAULT NULL,
  `classe` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=209 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`userid`, `name`, `email`, `password`, `matricule`, `filiere`, `classe`) VALUES
(101, 'Nounamo', 'nounamoestrella@gmail.com', 'jesuisfandElohim', NULL, NULL, NULL),
(204, 'TAGNE', 'Elohim237@gmail.com', 'otaku', NULL, NULL, NULL),
(205, 'nkot', 'francine@gmail.com', '$2a$10$wpUvwLrnUaWaQmS3YQbIsemGtgD5iATGvDUha/uwbLeG1kCirEvvu', NULL, NULL, NULL),
(207, 'nkot', 'etiennenkot2@gmail.com', '$2a$10$wpUvwLrnUaWaQmS3YQbIsemGtgD5iATGvDUha/uwbLeG1kCirEvvu', NULL, NULL, NULL),
(208, 'nkot', 'etiennenkot3@gmail.com', '$2a$10$jQZEt7SGHHr6/HtsV9arU..WtnmBtGvDaBjtyTzanJQ.6KcUGBzAC', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `users_id_seq`
--

DROP TABLE IF EXISTS `users_id_seq`;
CREATE TABLE IF NOT EXISTS `users_id_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `users_id_seq`
--

INSERT INTO `users_id_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
CREATE TABLE IF NOT EXISTS `user_info` (
  `id` bigint(20) NOT NULL,
  `passwordhash` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE IF NOT EXISTS `user_role` (
  `code_userid` bigint(20) NOT NULL,
  `code_role` bigint(20) NOT NULL,
  PRIMARY KEY (`code_userid`,`code_role`),
  KEY `code_role` (`code_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user_role`
--

INSERT INTO `user_role` (`code_userid`, `code_role`) VALUES
(204, 102755),
(205, 102755),
(207, 102755),
(208, 102755),
(101, 103102);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `droit`
--
ALTER TABLE `droit`
  ADD CONSTRAINT `droit_ibfk_1` FOREIGN KEY (`codeapplicationid`) REFERENCES `application` (`apllicationid`);

--
-- Contraintes pour la table `role_droit`
--
ALTER TABLE `role_droit`
  ADD CONSTRAINT `role_droit_ibfk_1` FOREIGN KEY (`code_roleid`) REFERENCES `role` (`roleid`),
  ADD CONSTRAINT `role_droit_ibfk_2` FOREIGN KEY (`code_droitid`) REFERENCES `droit` (`droitid`);

--
-- Contraintes pour la table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`code_userid`) REFERENCES `user` (`userid`),
  ADD CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`code_role`) REFERENCES `role` (`roleid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
