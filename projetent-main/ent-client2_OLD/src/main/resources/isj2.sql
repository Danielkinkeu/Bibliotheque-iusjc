-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mar. 10 mai 2022 à 17:38
-- Version du serveur : 5.7.36
-- Version de PHP : 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `isj2`
--

-- --------------------------------------------------------

--
-- Structure de la table `annee_academique`
--

DROP TABLE IF EXISTS `annee_academique`;
CREATE TABLE IF NOT EXISTS `annee_academique` (
  `code` bigint(20) NOT NULL,
  `date_creation` datetime NOT NULL,
  `date_modification` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `statut_vie` varchar(255) NOT NULL,
  `date_cloture` datetime DEFAULT NULL,
  `date_debut` datetime DEFAULT NULL,
  `createur` bigint(20) NOT NULL,
  `modificateur` bigint(20) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `UK_swqgx3p4a54nx9d5jcbbneu74` (`signature`),
  UNIQUE KEY `UK_jqa4m3yuqd3ima4er9ekfhfng` (`date_debut`),
  KEY `FKtqlf20e4qf8ny1hhsiyhqya2` (`createur`),
  KEY `FK9g8gq4q2l69gmbl2tharmi1yp` (`modificateur`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `anonymat`
--

DROP TABLE IF EXISTS `anonymat`;
CREATE TABLE IF NOT EXISTS `anonymat` (
  `code` bigint(20) NOT NULL,
  `date_creation` datetime NOT NULL,
  `date_modification` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `statut_vie` varchar(255) NOT NULL,
  `numero_anonymat` varchar(255) DEFAULT NULL,
  `numero_table` int(11) DEFAULT NULL,
  `createur` bigint(20) NOT NULL,
  `modificateur` bigint(20) NOT NULL,
  `est_inscrit` bigint(20) DEFAULT NULL,
  `evaluation` bigint(20) DEFAULT NULL,
  `note` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `UKqflnolv6obmqu47eqhuxyru8a` (`est_inscrit`,`evaluation`),
  UNIQUE KEY `UK_55a4i09wfumlnog9a4kj8ufww` (`signature`),
  KEY `FKoqtavm5egvnjbtp4dln1e1mk7` (`createur`),
  KEY `FK26hvdd7v6gvgvji1pfqiqgujk` (`modificateur`),
  KEY `FK22e311hjwk5cgh5tc4enr2lhp` (`evaluation`),
  KEY `FK825cra02ih3j4x6q93rau3j9g` (`note`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `candidat`
--

DROP TABLE IF EXISTS `candidat`;
CREATE TABLE IF NOT EXISTS `candidat` (
  `code` bigint(20) NOT NULL,
  `date_creation` datetime NOT NULL,
  `date_modification` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `statut_vie` varchar(255) NOT NULL,
  `date_naissance` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `sexe` varchar(255) NOT NULL,
  `statut` varchar(255) NOT NULL,
  `telephone` varchar(13) DEFAULT NULL,
  `ecole_origine` varchar(255) DEFAULT NULL,
  `lieu_naissance` varchar(255) DEFAULT NULL,
  `nom_de_la_mere` varchar(255) DEFAULT NULL,
  `nom_du_pere` varchar(255) DEFAULT NULL,
  `profession_de_la_mere` varchar(255) DEFAULT NULL,
  `profession_du_pere` varchar(255) DEFAULT NULL,
  `region_origine` varchar(255) DEFAULT NULL,
  `telephone_de_la_mere` varchar(255) DEFAULT NULL,
  `telephone_du_pere` varchar(255) DEFAULT NULL,
  `createur` bigint(20) NOT NULL,
  `modificateur` bigint(20) NOT NULL,
  `classe` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `UK_kp7fp5gge2qfbd7o3oh7wycll` (`signature`),
  KEY `FKrgohgr53dt6qf7fhpxbw8dgpo` (`createur`),
  KEY `FKmj9y2pvoyknxto7ugfqfag5nh` (`modificateur`),
  KEY `FKckmqib4w3f07136wt7mkuj11u` (`classe`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `classe`
--

DROP TABLE IF EXISTS `classe`;
CREATE TABLE IF NOT EXISTS `classe` (
  `code` bigint(20) NOT NULL,
  `date_creation` datetime NOT NULL,
  `date_modification` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `statut_vie` varchar(255) NOT NULL,
  `createur` bigint(20) NOT NULL,
  `modificateur` bigint(20) NOT NULL,
  `niveau` bigint(20) DEFAULT NULL,
  `specialite` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `UK24ihvdhfsc0penqk6ekck742b` (`niveau`,`specialite`),
  UNIQUE KEY `UK_lrmrqvh45diosw3hf0lsglik7` (`signature`),
  KEY `FKtv9myr4ovctpegm1y4dibwgi` (`createur`),
  KEY `FKogr8upv8d1fua8fp4kbseol0q` (`modificateur`),
  KEY `FK4unvyc4qj6a9scpch68qtm1xj` (`specialite`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `discipline`
--

DROP TABLE IF EXISTS `discipline`;
CREATE TABLE IF NOT EXISTS `discipline` (
  `code` bigint(20) NOT NULL,
  `date_creation` datetime NOT NULL,
  `date_modification` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `statut_vie` varchar(255) NOT NULL,
  `date_retard` datetime DEFAULT NULL,
  `heure_justifie` int(11) DEFAULT NULL,
  `nb_heures_absences` int(11) DEFAULT NULL,
  `nb_retards` int(11) DEFAULT NULL,
  `createur` bigint(20) NOT NULL,
  `modificateur` bigint(20) NOT NULL,
  `etudiant` bigint(20) DEFAULT NULL,
  `semestre` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `UKd4ux1e0woobmpqvg9e8ayr88s` (`etudiant`,`semestre`),
  UNIQUE KEY `UK_g9p019ftpe4r66o1xh6ra6878` (`signature`),
  KEY `FKl3h1nlgxbeh4g84dpilexmt5v` (`createur`),
  KEY `FK17qqlm99pftl9j3njyvu481lr` (`modificateur`),
  KEY `FKcdu9wb0xujcwfnxygmc49jyaf` (`semestre`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `droit`
--

DROP TABLE IF EXISTS `droit`;
CREATE TABLE IF NOT EXISTS `droit` (
  `code` bigint(20) NOT NULL,
  `date_creation` datetime NOT NULL,
  `date_modification` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `statut_vie` varchar(255) NOT NULL,
  `categorie` varchar(255) NOT NULL,
  `ecriture` bit(1) DEFAULT NULL,
  `lecture` bit(1) DEFAULT NULL,
  `modification` bit(1) DEFAULT NULL,
  `suppression` bit(1) DEFAULT NULL,
  `createur` bigint(20) NOT NULL,
  `modificateur` bigint(20) NOT NULL,
  `role` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `UK_jf9o4fnv64ohva4e71l1str2o` (`signature`),
  KEY `FKn6yo8tnj2a4afsdff9hk2cn5h` (`createur`),
  KEY `FKkf9dueb82ejssjaswb9xl0lst` (`modificateur`),
  KEY `FKr2yr2w50usmy2ahoy8d76aiqn` (`role`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `email`
--

DROP TABLE IF EXISTS `email`;
CREATE TABLE IF NOT EXISTS `email` (
  `objet` varchar(255) DEFAULT NULL,
  `code` bigint(20) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `enseignant`
--

DROP TABLE IF EXISTS `enseignant`;
CREATE TABLE IF NOT EXISTS `enseignant` (
  `code` bigint(20) NOT NULL,
  `date_creation` datetime NOT NULL,
  `date_modification` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `statut_vie` varchar(255) NOT NULL,
  `date_naissance` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `sexe` varchar(255) NOT NULL,
  `statut` varchar(255) NOT NULL,
  `telephone` varchar(13) DEFAULT NULL,
  `qualification` varchar(255) DEFAULT NULL,
  `createur` bigint(20) NOT NULL,
  `modificateur` bigint(20) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `UK_f2cktgex39i7stskdkw31856r` (`signature`),
  KEY `FK62mp9usc5mpg20n76kspspgvx` (`createur`),
  KEY `FKen5dl1lyix01ct0d07xt6pk08` (`modificateur`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `enseignement`
--

DROP TABLE IF EXISTS `enseignement`;
CREATE TABLE IF NOT EXISTS `enseignement` (
  `code` bigint(20) NOT NULL,
  `date_creation` datetime NOT NULL,
  `date_modification` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `statut_vie` varchar(255) NOT NULL,
  `credit` double DEFAULT NULL,
  `heures_de_cours` int(11) DEFAULT NULL,
  `programme_de_cours` varchar(1020) DEFAULT NULL,
  `createur` bigint(20) NOT NULL,
  `modificateur` bigint(20) NOT NULL,
  `semestre` bigint(20) DEFAULT NULL,
  `ue` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `UKngaxph8cbvyptq9dfjcjiscj` (`semestre`,`ue`),
  UNIQUE KEY `UK_ta6o1wub888wcgwu6tvoxtjl1` (`signature`),
  KEY `FKetpfil0gxuk4vckvjtbw79c1` (`createur`),
  KEY `FKgv8kb5o37nuh6piln511060rt` (`modificateur`),
  KEY `FKjwbx5yto58u1rm2aqgfnnroy2` (`ue`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `enseignement_enseignants`
--

DROP TABLE IF EXISTS `enseignement_enseignants`;
CREATE TABLE IF NOT EXISTS `enseignement_enseignants` (
  `code_enseignement` bigint(20) NOT NULL,
  `code_enseignant` bigint(20) NOT NULL,
  KEY `FK201p4d11rmthcrqw1k4shoim0` (`code_enseignant`),
  KEY `FKf40ksyje7jlfp0w9m2hf99gg5` (`code_enseignement`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `envoi_message`
--

DROP TABLE IF EXISTS `envoi_message`;
CREATE TABLE IF NOT EXISTS `envoi_message` (
  `code` bigint(20) NOT NULL,
  `date_creation` datetime NOT NULL,
  `date_modification` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `statut_vie` varchar(255) NOT NULL,
  `date_envoi` datetime DEFAULT NULL,
  `statut` varchar(255) DEFAULT NULL,
  `createur` bigint(20) NOT NULL,
  `modificateur` bigint(20) NOT NULL,
  `candidat` bigint(20) DEFAULT NULL,
  `message` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `UK_otqfnp08pi4qlsxhv5s338rpc` (`signature`),
  KEY `FKrcdke2i44nbh4fevhlt4l5d66` (`createur`),
  KEY `FKlhj2g1aicpu3fuuy9k6rngmne` (`modificateur`),
  KEY `FK43c4koyv0sn9s3272uyha1p1m` (`candidat`),
  KEY `FK1kv3hlebkvcv22kn3je54pxp` (`message`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `est_inscrit`
--

DROP TABLE IF EXISTS `est_inscrit`;
CREATE TABLE IF NOT EXISTS `est_inscrit` (
  `code` bigint(20) NOT NULL,
  `date_creation` datetime NOT NULL,
  `date_modification` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `statut_vie` varchar(255) NOT NULL,
  `statut` varchar(255) DEFAULT NULL,
  `createur` bigint(20) NOT NULL,
  `modificateur` bigint(20) NOT NULL,
  `candidat_inscrit` bigint(20) DEFAULT NULL,
  `enseignement` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `UKndwj6ffdss2uatcsni75jg4ve` (`candidat_inscrit`,`enseignement`),
  UNIQUE KEY `UK_kq2g8hmerotw5a5j57gn0cj07` (`signature`),
  KEY `FKtmj6w0h3iiaoerilf61n1q60v` (`createur`),
  KEY `FK3ch6wfew01th4uelfakja3to8` (`modificateur`),
  KEY `FK76qj5pwceuji71u8fm3bsqr2v` (`enseignement`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

DROP TABLE IF EXISTS `etudiant`;
CREATE TABLE IF NOT EXISTS `etudiant` (
  `code_authentification` varchar(255) NOT NULL,
  `matricule` varchar(255) NOT NULL,
  `code` bigint(20) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `UK_11cw6xkucs8gpuyq1lwnk8jk1` (`code_authentification`),
  UNIQUE KEY `UK_tn2q9dbx4m888c3xgvxt7bt58` (`matricule`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `evaluation`
--

DROP TABLE IF EXISTS `evaluation`;
CREATE TABLE IF NOT EXISTS `evaluation` (
  `code` bigint(20) NOT NULL,
  `date_creation` datetime NOT NULL,
  `date_modification` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `statut_vie` varchar(255) NOT NULL,
  `date_evaluation` datetime DEFAULT NULL,
  `statut` varchar(255) NOT NULL,
  `createur` bigint(20) NOT NULL,
  `modificateur` bigint(20) NOT NULL,
  `type_evaluation` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `UK_95d13va0jy58insg1huj14f6e` (`signature`),
  UNIQUE KEY `UK_1rifn6uii9w84haw6ljuisad0` (`type_evaluation`),
  KEY `FKf7oy1x1luiy26t3fjuaivq44q` (`createur`),
  KEY `FKjte9pklvx47lxe632e4g57269` (`modificateur`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `filiere`
--

DROP TABLE IF EXISTS `filiere`;
CREATE TABLE IF NOT EXISTS `filiere` (
  `code` bigint(20) NOT NULL,
  `date_creation` datetime NOT NULL,
  `date_modification` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `statut_vie` varchar(255) NOT NULL,
  `createur` bigint(20) NOT NULL,
  `modificateur` bigint(20) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `UK_teswpig4cif4hbcky3bscixko` (`signature`),
  KEY `FKhsabmfq0809u71n6ery8ewrx9` (`createur`),
  KEY `FKqeiw7korq0vpfkxtye0ggg6n3` (`modificateur`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `historique_note`
--

DROP TABLE IF EXISTS `historique_note`;
CREATE TABLE IF NOT EXISTS `historique_note` (
  `code` bigint(20) NOT NULL,
  `date_creation` datetime NOT NULL,
  `date_modification` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `statut_vie` varchar(255) NOT NULL,
  `valeur_note` double NOT NULL,
  `createur` bigint(20) NOT NULL,
  `modificateur` bigint(20) NOT NULL,
  `note` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `UK_17bin07m3uk0qy9or6rc82tux` (`signature`),
  KEY `FK6jtfsfgcocothg8jhpabvd48q` (`createur`),
  KEY `FKmxxbf2hs95xk8lri3jm0p02jn` (`modificateur`),
  KEY `FKnhoos8op2nbq8pojghlqjv56i` (`note`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

DROP TABLE IF EXISTS `message`;
CREATE TABLE IF NOT EXISTS `message` (
  `code` bigint(20) NOT NULL,
  `date_creation` datetime NOT NULL,
  `date_modification` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `statut_vie` varchar(255) NOT NULL,
  `contenu` varchar(255) DEFAULT NULL,
  `destinataire` varchar(255) DEFAULT NULL,
  `emetteur` varchar(255) DEFAULT NULL,
  `createur` bigint(20) NOT NULL,
  `modificateur` bigint(20) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `UK_c4369qvafpbdi0ihuw5n2bf7p` (`signature`),
  KEY `FKhwyy1g38dt18440657edhs5sa` (`createur`),
  KEY `FKboeogt6mhbnhl2i3dy7c7jwcj` (`modificateur`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `module`
--

DROP TABLE IF EXISTS `module`;
CREATE TABLE IF NOT EXISTS `module` (
  `code` bigint(20) NOT NULL,
  `date_creation` datetime NOT NULL,
  `date_modification` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `statut_vie` varchar(255) NOT NULL,
  `code_module` varchar(255) DEFAULT NULL,
  `createur` bigint(20) NOT NULL,
  `modificateur` bigint(20) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `UK_o5eivr3ymhq5d27r1k6psqtmb` (`signature`),
  UNIQUE KEY `UK_t085aljqp479ff31f5ky4spf2` (`code_module`),
  KEY `FKnuo31jdyq18hq0chu6gii3jay` (`createur`),
  KEY `FK6loujm8dbo02rw2uq22ogibjn` (`modificateur`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `niveau`
--

DROP TABLE IF EXISTS `niveau`;
CREATE TABLE IF NOT EXISTS `niveau` (
  `code` bigint(20) NOT NULL,
  `date_creation` datetime NOT NULL,
  `date_modification` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `statut_vie` varchar(255) NOT NULL,
  `numero` int(11) DEFAULT NULL,
  `createur` bigint(20) NOT NULL,
  `modificateur` bigint(20) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `UK_se5yc80tmgst8uam2cwum86wx` (`signature`),
  KEY `FK5dqggj8l3tdm0qcspbilhw0rq` (`createur`),
  KEY `FKkw6ldawvna2dnc19v1811ku8o` (`modificateur`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `note`
--

DROP TABLE IF EXISTS `note`;
CREATE TABLE IF NOT EXISTS `note` (
  `code` bigint(20) NOT NULL,
  `date_creation` datetime NOT NULL,
  `date_modification` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `statut_vie` varchar(255) NOT NULL,
  `numero_table` int(11) DEFAULT NULL,
  `valeur_note` double NOT NULL,
  `createur` bigint(20) NOT NULL,
  `modificateur` bigint(20) NOT NULL,
  `est_inscrit` bigint(20) DEFAULT NULL,
  `evaluation` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `UKkbvh99q5yy8raek6yg1r0bxst` (`est_inscrit`,`evaluation`),
  UNIQUE KEY `UK_72mob866s0dnvmx4beyguiehq` (`signature`),
  KEY `FKy5jg1dfflruxxvvlyutsg4nn` (`createur`),
  KEY `FKefvwu13yxpdutn98iq80k3iut` (`modificateur`),
  KEY `FK1c72scifonby8tkpkq72miyxl` (`evaluation`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `notecc`
--

DROP TABLE IF EXISTS `notecc`;
CREATE TABLE IF NOT EXISTS `notecc` (
  `code` bigint(20) NOT NULL,
  `date_creation` datetime NOT NULL,
  `date_modification` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `statut_vie` varchar(255) NOT NULL,
  `valeur_note` double NOT NULL,
  `createur` bigint(20) NOT NULL,
  `modificateur` bigint(20) NOT NULL,
  `candidat` bigint(20) NOT NULL,
  `type_note_cc` bigint(20) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `UK89yc811nft5iuf03lejj44fb5` (`candidat`,`type_note_cc`),
  UNIQUE KEY `UK_9evtp4i4qres6s2bbjil8x8ig` (`signature`),
  KEY `FKnr07if5ftcxs0htjom440l6nn` (`createur`),
  KEY `FK1uw1vi8qlyy0v0v73l9w19ayp` (`modificateur`),
  KEY `FK99d9sfb61ut58etbjehea0o5o` (`type_note_cc`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `code` bigint(20) NOT NULL,
  `date_creation` datetime NOT NULL,
  `date_modification` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `statut_vie` varchar(255) NOT NULL,
  `createur` bigint(20) NOT NULL,
  `modificateur` bigint(20) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `UK_meqij2822vi8y8mu35yx9sjnr` (`signature`),
  KEY `FKca9lualoqf8l6101hfsx5n21y` (`createur`),
  KEY `FKhqto4e85monmwdtrfibso2m0f` (`modificateur`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `semestre`
--

DROP TABLE IF EXISTS `semestre`;
CREATE TABLE IF NOT EXISTS `semestre` (
  `code` bigint(20) NOT NULL,
  `date_creation` datetime NOT NULL,
  `date_modification` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `statut_vie` varchar(255) NOT NULL,
  `date_cloture` datetime DEFAULT NULL,
  `date_debut` datetime DEFAULT NULL,
  `createur` bigint(20) NOT NULL,
  `modificateur` bigint(20) NOT NULL,
  `annee_academique` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `UKtjmxo0xbj2p5dbuskkgl72skp` (`annee_academique`,`date_debut`),
  UNIQUE KEY `UK_t36vxsujsk69ae5qr9ds2m0y0` (`signature`),
  KEY `FKpu11ys4d55iw460llrvfki7fi` (`createur`),
  KEY `FKbhrovx061g829el4bxpomucgs` (`modificateur`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `session`
--

DROP TABLE IF EXISTS `session`;
CREATE TABLE IF NOT EXISTS `session` (
  `code` bigint(20) NOT NULL,
  `date_creation` datetime NOT NULL,
  `date_modification` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `statut_vie` varchar(255) NOT NULL,
  `date_connection` datetime DEFAULT NULL,
  `date_deconnection` datetime DEFAULT NULL,
  `machine_cliente` varchar(255) DEFAULT NULL,
  `statut` varchar(255) NOT NULL,
  `createur` bigint(20) NOT NULL,
  `modificateur` bigint(20) NOT NULL,
  `utilisateur` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `UK_j6pdf9e8niastiyjthxc7v2le` (`signature`),
  KEY `FKri27qya8fw3ampk2wk20vhqhw` (`createur`),
  KEY `FK6620hcfbf2ekskpbggoeghv8q` (`modificateur`),
  KEY `FKpd70fxo08v66nw7mpj4erpw1y` (`utilisateur`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `sms`
--

DROP TABLE IF EXISTS `sms`;
CREATE TABLE IF NOT EXISTS `sms` (
  `code` bigint(20) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `specialite`
--

DROP TABLE IF EXISTS `specialite`;
CREATE TABLE IF NOT EXISTS `specialite` (
  `code` bigint(20) NOT NULL,
  `date_creation` datetime NOT NULL,
  `date_modification` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `statut_vie` varchar(255) NOT NULL,
  `createur` bigint(20) NOT NULL,
  `modificateur` bigint(20) NOT NULL,
  `filiere` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `UK9io72yj9jor9fna2duk8m11i3` (`filiere`,`libelle`),
  UNIQUE KEY `UK_exeroh98uteryvp4aqimmnbxf` (`signature`),
  KEY `FKpt3xwgejlnwulufwdiite4s8q` (`createur`),
  KEY `FKfwjj8al7o0x1kh62v8yukwdme` (`modificateur`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `typenotecc`
--

DROP TABLE IF EXISTS `typenotecc`;
CREATE TABLE IF NOT EXISTS `typenotecc` (
  `code` bigint(20) NOT NULL,
  `date_creation` datetime NOT NULL,
  `date_modification` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `statut_vie` varchar(255) NOT NULL,
  `numero_cc` int(11) NOT NULL,
  `pourcentage_cc` int(11) NOT NULL,
  `createur` bigint(20) NOT NULL,
  `modificateur` bigint(20) NOT NULL,
  `enseignement` bigint(20) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `UK1jnwwq6e59vhwv1hceg4ekq4j` (`numero_cc`,`enseignement`),
  UNIQUE KEY `UK_htvtot1orn6p968oa4ncn3mmp` (`signature`),
  KEY `FK5odob9ccwlknvnfy5sbqwk3nc` (`createur`),
  KEY `FKgqhtj1d1sl57hbib2ykoc2guj` (`modificateur`),
  KEY `FKoe20yrihyxiywpn5wo7hwq9ga` (`enseignement`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `type_evaluation`
--

DROP TABLE IF EXISTS `type_evaluation`;
CREATE TABLE IF NOT EXISTS `type_evaluation` (
  `code` bigint(20) NOT NULL,
  `date_creation` datetime NOT NULL,
  `date_modification` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `statut_vie` varchar(255) NOT NULL,
  `pourcentage` float DEFAULT NULL,
  `createur` bigint(20) NOT NULL,
  `modificateur` bigint(20) NOT NULL,
  `enseignement` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `UK_a3nl56a846n5twg1sjd72f9ip` (`signature`),
  KEY `FKltv1xvi6lmfr48wgixkkyd80u` (`createur`),
  KEY `FK8i7sidthkt77n2iborl637bay` (`modificateur`),
  KEY `FK8hvpa659w0s7dxxpsykqfkh3t` (`enseignement`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `ue`
--

DROP TABLE IF EXISTS `ue`;
CREATE TABLE IF NOT EXISTS `ue` (
  `code` bigint(20) NOT NULL,
  `date_creation` datetime NOT NULL,
  `date_modification` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `statut_vie` varchar(255) NOT NULL,
  `code_ue` varchar(255) DEFAULT NULL,
  `credits` double DEFAULT NULL,
  `statut` varchar(255) NOT NULL,
  `createur` bigint(20) NOT NULL,
  `modificateur` bigint(20) NOT NULL,
  `module` bigint(20) DEFAULT NULL,
  `niveau` bigint(20) DEFAULT NULL,
  `specialite` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `UK_fd4vwpoclkj79gup883k5322r` (`signature`),
  UNIQUE KEY `UK_coy6lcouaqcjv0qjl5wcrqhc1` (`code_ue`),
  KEY `FK41yoptwlgewuh2je0ea5htdj6` (`createur`),
  KEY `FKnylp4kyr810b8qx2n1msl0x9p` (`modificateur`),
  KEY `FK84eqmm0xb2agn4y7dlue7e4up` (`module`),
  KEY `FKtpcju8pqbenklq3oie8k3h07n` (`niveau`),
  KEY `FKbafyoc30fd7drcocax8jmx27q` (`specialite`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `code` bigint(20) NOT NULL,
  `date_creation` datetime NOT NULL,
  `date_modification` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `statut_vie` varchar(255) NOT NULL,
  `date_naissance` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `sexe` varchar(255) NOT NULL,
  `statut` varchar(255) NOT NULL,
  `telephone` varchar(13) DEFAULT NULL,
  `login` varchar(255) NOT NULL,
  `mot_de_passe` varchar(255) NOT NULL,
  `createur` bigint(20) NOT NULL,
  `modificateur` bigint(20) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `UK_18vwp4resqussqmlpqnymfqxk` (`login`),
  UNIQUE KEY `UK_r2pd0ycq6b84ovdvxq7hp82cx` (`signature`),
  KEY `FK2mjmr794svc287xjcid05c1tw` (`createur`),
  KEY `FK3k2w81chxx9yff39c0rtmebc8` (`modificateur`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur_roles`
--

DROP TABLE IF EXISTS `utilisateur_roles`;
CREATE TABLE IF NOT EXISTS `utilisateur_roles` (
  `code_utilisateur` bigint(20) NOT NULL,
  `code_role` bigint(20) NOT NULL,
  KEY `FKqb7gow4lc2dkt10d7umghase8` (`code_role`),
  KEY `FKjdfk58cmwvdyffgd1k96xj1hd` (`code_utilisateur`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
