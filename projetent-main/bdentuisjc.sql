-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 20 fév. 2024 à 16:37
-- Version du serveur :  10.4.14-MariaDB
-- Version de PHP : 7.4.11
CREATE DATABASE bdentuisjc;
USE bdentuisjc;
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

CREATE TABLE `application` (
  `apllicationid` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `application`
--

INSERT INTO `application` (`apllicationid`, `libelle`) VALUES
(1, 'gestionote');

-- --------------------------------------------------------

--
-- Structure de la table `droit`
--

CREATE TABLE `droit` (
  `droitid` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `ecriture` tinyint(1) DEFAULT 0,
  `lecture` tinyint(1) DEFAULT 0,
  `modification` tinyint(1) DEFAULT 0,
  `suppression` tinyint(1) DEFAULT 0,
  `codeapplicationid` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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

CREATE TABLE `role` (
  `roleid` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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

CREATE TABLE `role_droit` (
  `code_roleid` bigint(20) NOT NULL,
  `code_droitid` bigint(20) NOT NULL
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

CREATE TABLE `user` (
  `userid` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `matricule` varchar(50) DEFAULT NULL,
  `filiere` varchar(50) DEFAULT NULL,
  `classe` varchar(50) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`userid`, `name`, `email`, `password`, `matricule`, `filiere`, `classe`, `image`) VALUES
(101, 'Nounamo', 'nounamoestrella@gmail.com', '$2a$10$gB2hhZQl9xOFQi9q6rhA6eesRlxuptaYldrkp2mBq7PMMP8prbfW2', '2', '51', '33602', '/photo/2021i074.jpg'),
(204, 'TAGNE', 'Elohim237@gmail.com', '$2a$10$gB2hhZQl9xOFQi9q6rhA6eesRlxuptaYldrkp2mBq7PMMP8prbfW2', NULL, NULL, NULL, '/photo/2021i074.jpg'),
(205, 'nkot', 'francine@gmail.com', '$2a$10$gB2hhZQl9xOFQi9q6rhA6eesRlxuptaYldrkp2mBq7PMMP8prbfW2', NULL, NULL, NULL, '/photo/2021i075.jpg'),
(207, 'nkot', 'etiennenkot2@gmail.com', '$2a$10$gB2hhZQl9xOFQi9q6rhA6eesRlxuptaYldrkp2mBq7PMMP8prbfW2', '1', NULL, NULL, '/photo/2021i074.jpg'),
(208, 'nkot', 'etiennenkot3@gmail.com', '$2a$10$gB2hhZQl9xOFQi9q6rhA6eesRlxuptaYldrkp2mBq7PMMP8prbfW2', '2', NULL, NULL, '/photo/2021i075.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `users_id_seq`
--

CREATE TABLE `users_id_seq` (
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

CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL,
  `passwordhash` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `user_role`
--

CREATE TABLE `user_role` (
  `code_userid` bigint(20) NOT NULL,
  `code_role` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user_role`
--

INSERT INTO `user_role` (`code_userid`, `code_role`) VALUES
(101, 103102),
(204, 102755),
(205, 102755),
(207, 102755),
(208, 102755);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `application`
--
ALTER TABLE `application`
  ADD PRIMARY KEY (`apllicationid`);

--
-- Index pour la table `droit`
--
ALTER TABLE `droit`
  ADD PRIMARY KEY (`droitid`),
  ADD KEY `codeapplicationid` (`codeapplicationid`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`roleid`);

--
-- Index pour la table `role_droit`
--
ALTER TABLE `role_droit`
  ADD KEY `code_roleid` (`code_roleid`),
  ADD KEY `code_droitid` (`code_droitid`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userid`);

--
-- Index pour la table `user_info`
--
ALTER TABLE `user_info`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`code_userid`,`code_role`),
  ADD KEY `code_role` (`code_role`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `application`
--
ALTER TABLE `application`
  MODIFY `apllicationid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `droit`
--
ALTER TABLE `droit`
  MODIFY `droitid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=102855;

--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `roleid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=103103;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `userid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=209;

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
