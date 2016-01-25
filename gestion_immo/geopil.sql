-- phpMyAdmin SQL Dump
-- version 3.4.11.1deb2+deb7u1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Jeu 10 Décembre 2015 à 18:18
-- Version du serveur: 5.5.35
-- Version de PHP: 5.4.41-0+deb7u1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `gesimmo`
--

-- --------------------------------------------------------

--
-- Structure de la table `activite`
--

CREATE TABLE IF NOT EXISTS `activite` (
  `TYPE_ACTIVITE` varchar(15) NOT NULL,
  `id_activite` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE_CREATION` datetime DEFAULT NULL,
  `DATE_UPDATED` datetime DEFAULT NULL,
  `budget_prevu` double DEFAULT NULL,
  `budget_reel` double DEFAULT NULL,
  `date_deb_prevu` date DEFAULT NULL,
  `date_deb_reel` date DEFAULT NULL,
  `date_fin_prevu` date DEFAULT NULL,
  `date_fin_reel` date DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `etat` int(2) DEFAULT '1',
  `nbre_emploi_prevu` int(11) DEFAULT NULL,
  `nbre_emploi_rel` int(11) DEFAULT NULL,
  `activite_nom` varchar(60) NOT NULL,
  `ponderation` float DEFAULT NULL,
  `taux_realisation` float DEFAULT NULL,
  `type` varchar(40) NOT NULL DEFAULT 'PROJET',
  `version` int(11) DEFAULT '0',
  `id_parent` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_activite`),
  KEY `FK9D4BF2FB1FB84AF7` (`id_parent`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `activite_localisation`
--

CREATE TABLE IF NOT EXISTS `activite_localisation` (
  `ID_ACTIVITE` bigint(20) NOT NULL,
  `ID_LOCALISATION` bigint(20) NOT NULL,
  KEY `FKF7E941045C9531D2` (`ID_LOCALISATION`),
  KEY `FKF7E9410476C4DC48` (`ID_ACTIVITE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE IF NOT EXISTS `compte` (
  `ID_COMPTE` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE_CREATION` datetime DEFAULT NULL,
  `DATE_UPDATED` datetime DEFAULT NULL,
  `ETAT` int(11) DEFAULT NULL,
  `LOGIN` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  PRIMARY KEY (`ID_COMPTE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=113 ;

--
-- Contenu de la table `compte`
--

INSERT INTO `compte` (`ID_COMPTE`, `DATE_CREATION`, `DATE_UPDATED`, `ETAT`, `LOGIN`, `PASSWORD`) VALUES
(84, '2015-03-27 00:00:00', '2015-11-13 16:52:05', 1, 'dysow', '77cb141d760ec14a09e1a4f1e3373d5865b41d300adb854200ddd6096c9ed55f'),
(103, '2015-11-13 12:01:37', '2015-11-20 15:05:19', 1, 'mosow', '28903e17dcf1620a7273c44b1339260d0bf24b654ca6da33f6431e243bb074f6'),
(104, '2015-11-13 12:03:05', '2015-11-13 12:10:45', 1, 'ddiaw', 'b000297da4d786414408dca8b2db668ffa2857457700e42c61e87b8daa19739a'),
(105, '2015-11-13 12:03:52', '2015-11-13 12:10:56', 1, 'kcisse', 'a930033c2b72d315586706d59795b3151b80d2266898cb0215fe2163bc485a2f'),
(106, '2015-11-13 12:04:30', '2015-11-13 12:10:44', 1, 'athiang', 'fadb83098b20bf88a3abe9993ee836fcf6e51643b1698453be61cef8e353115a'),
(107, '2015-11-13 12:05:01', '2015-11-13 15:55:39', 0, 'isene', '73c821aa41862e5d5eb5ba1554a83e04233ea928d2e7be1d58418be65b241b97'),
(108, '2015-11-13 12:05:29', '2015-11-13 15:55:40', 0, 'msall', '52a80ed233204b9210ccc955e4b5e30fdb7dde79df9d7901bb84800c4ca1a41a'),
(112, '2015-11-13 15:34:48', '2015-11-13 15:54:48', 1, 'sniang', '9c3b0357e882b8a700dadcf6d37b5b23572c1494567e55fdcce673fed5f03978');

-- --------------------------------------------------------

--
-- Structure de la table `indicateur`
--

CREATE TABLE IF NOT EXISTS `indicateur` (
  `id_indicateur` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_creation` datetime DEFAULT NULL,
  `date_updated` datetime DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `etat` int(2) DEFAULT '1',
  `nom_indicateur` varchar(255) DEFAULT NULL,
  `unite_indicateur` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_indicateur`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `indicateur`
--

INSERT INTO `indicateur` (`id_indicateur`, `date_creation`, `date_updated`, `libelle`, `etat`, `nom_indicateur`, `unite_indicateur`) VALUES
(1, '2015-12-10 17:39:08', '2015-12-10 17:39:08', 'Nombre d''habitant', 1, 'Population', 'habitant');

-- --------------------------------------------------------

--
-- Structure de la table `job_execution_hist`
--

CREATE TABLE IF NOT EXISTS `job_execution_hist` (
  `ID_JOB` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE_CREATION` datetime DEFAULT NULL,
  `DATE_UPDATED` datetime DEFAULT NULL,
  `RAPPORT_GENERATION_PARAMS` varchar(255) DEFAULT NULL,
  `RAPPORT_NAME` varchar(255) DEFAULT NULL,
  `RAPPORT_PATH` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_JOB`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `journal`
--

CREATE TABLE IF NOT EXISTS `journal` (
  `id_journal` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_creation` datetime DEFAULT NULL,
  `date_updated` datetime DEFAULT NULL,
  `description` varchar(1024) DEFAULT NULL,
  `etat` int(2) DEFAULT '1',
  `libelle` varchar(255) DEFAULT NULL,
  `id_activite` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_journal`),
  KEY `FKAB64AF3776C4DC48` (`id_activite`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `localisation`
--

CREATE TABLE IF NOT EXISTS `localisation` (
  `id_localisation` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE_CREATION` datetime DEFAULT NULL,
  `DATE_UPDATED` datetime DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `etat` int(2) DEFAULT '1',
  `json_form_view` varchar(255) DEFAULT NULL,
  `latitude` varchar(50) DEFAULT NULL,
  `longitude` varchar(50) DEFAULT NULL,
  `local_nom` varchar(60) NOT NULL,
  `type` varchar(60) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  `local_nom_google` varchar(80) NOT NULL,
  `type_localisation` varchar(15) NOT NULL,
  `id_parent` bigint(20) DEFAULT NULL,
  `ID_USER` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_localisation`),
  KEY `FKC3A7DA40CACEAB3C` (`id_parent`),
  KEY `FKC3A7DA40AC26C6E8` (`ID_USER`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `localisation`
--

INSERT INTO `localisation` (`id_localisation`, `DATE_CREATION`, `DATE_UPDATED`, `description`, `etat`, `json_form_view`, `latitude`, `longitude`, `local_nom`, `type`, `version`, `local_nom_google`, `type_localisation`, `id_parent`, `ID_USER`) VALUES
(1, '2015-12-03 09:02:16', '2015-12-03 09:02:16', 'Region de dakar', 1, NULL, '14.748323213448648', ' -17.362947463989258', ' Dakar', 'REGION', 0, 'N1, Dakar, Sénégal', 'LOCALITE', NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `localisation_indicateur`
--

CREATE TABLE IF NOT EXISTS `localisation_indicateur` (
  `id_localisation_indicateur` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_creation` datetime DEFAULT NULL,
  `date_updated` datetime DEFAULT NULL,
  `valeur` varchar(255) DEFAULT NULL,
  `id_indicateur` bigint(20) DEFAULT NULL,
  `id_localisation` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_localisation_indicateur`),
  KEY `FK8E7B72755C9531D2` (`id_localisation`),
  KEY `FK8E7B72759D9867FE` (`id_indicateur`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `localisation_indicateur`
--

INSERT INTO `localisation_indicateur` (`id_localisation_indicateur`, `date_creation`, `date_updated`, `valeur`, `id_indicateur`, `id_localisation`) VALUES
(1, '2015-12-10 17:39:23', '2015-12-10 17:39:23', '1000000', 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `messagerie`
--

CREATE TABLE IF NOT EXISTS `messagerie` (
  `ID_PARAM` bigint(20) NOT NULL AUTO_INCREMENT,
  `PARAM_SMTP_EMAIL` varchar(255) DEFAULT NULL,
  `PARAM_SMTP_HOST` varchar(255) DEFAULT NULL,
  `PARAM_SMTP_PASSWORD` varchar(255) DEFAULT NULL,
  `PARAM_SMTP_PORT` varchar(255) DEFAULT NULL,
  `PARAM_SMTP_SSL` bit(1) DEFAULT NULL,
  `PARAM_SMTP_TRANSPORT` varchar(255) DEFAULT NULL,
  `PARAM_SMTP_USERNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_PARAM`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `messagerie_param`
--

CREATE TABLE IF NOT EXISTS `messagerie_param` (
  `ID_PARAM` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE_CREATION` datetime DEFAULT NULL,
  `DATE_UPDATED` datetime DEFAULT NULL,
  `PARAM_SMTP_EMAIL` varchar(255) DEFAULT NULL,
  `PARAM_SMTP_HOST` varchar(255) DEFAULT NULL,
  `PARAM_SMTP_PASSWORD` varchar(255) DEFAULT NULL,
  `PARAM_SMTP_PORT` int(11) DEFAULT NULL,
  `PARAM_SMTP_SSL` bit(1) DEFAULT NULL,
  `PARAM_SMTP_TRANSPORT` varchar(255) DEFAULT NULL,
  `PARAM_SMTP_USERNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_PARAM`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `parametremail`
--

CREATE TABLE IF NOT EXISTS `parametremail` (
  `ID_PARAMETREMAIL` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE_CREATION` datetime DEFAULT NULL,
  `DATE_UPDATED` datetime DEFAULT NULL,
  `ADRESSE_MESSAGERIE` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `NOM_UTILISATEUR` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PORT_POP3` varchar(255) DEFAULT NULL,
  `PORT_SMTP` varchar(255) DEFAULT NULL,
  `SERVEUR_MAIL_ENTRANT` varchar(255) DEFAULT NULL,
  `SERVEUR_MAIL_SORTANT` varchar(255) DEFAULT NULL,
  `typeCompte` varchar(255) DEFAULT NULL,
  `SERVEUR_MAIL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_PARAMETREMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `rapport_param`
--

CREATE TABLE IF NOT EXISTS `rapport_param` (
  `ID_PARAM` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE_CREATION` datetime DEFAULT NULL,
  `DATE_UPDATED` datetime DEFAULT NULL,
  `PARAM_RAPPORT_BENEFICIAIRE` varchar(255) DEFAULT NULL,
  `PARAM_RAPPORT_DONNES` varchar(255) DEFAULT NULL,
  `PARAM_RAPPORT_FORMAT` varchar(255) DEFAULT NULL,
  `PARAM_RAPPORT_FREQUENCE` varchar(255) DEFAULT NULL,
  `PARAM_RAPPORT_REPERTOIRE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_PARAM`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `ID_ROLE` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE_CREATION` datetime DEFAULT NULL,
  `DATE_UPDATED` datetime DEFAULT NULL,
  `ROLE_NAME` varchar(255) DEFAULT NULL,
  `ROLE_DESC` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_ROLE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `role`
--

INSERT INTO `role` (`ID_ROLE`, `DATE_CREATION`, `DATE_UPDATED`, `ROLE_NAME`, `ROLE_DESC`) VALUES
(1, '2015-11-09 00:00:00', '2015-11-20 00:00:00', 'ROLE_ADMIN', 'administrateur'),
(2, '2015-11-07 00:00:00', '2015-11-28 00:00:00', 'ROLE_USER', 'Utilisateur Simple'),
(3, '2015-11-01 00:00:00', '2015-11-12 00:00:00', 'ROLE_SUPER_ADMIN', 'Super Administrateur');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `ID_USER` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE_CREATION` datetime DEFAULT NULL,
  `DATE_UPDATED` datetime DEFAULT NULL,
  `ID_COMPTE` bigint(20) DEFAULT NULL,
  `USER_LOGGED` int(11) DEFAULT NULL,
  `USER_MAIL` varchar(255) DEFAULT NULL,
  `USER_NOM` varchar(255) DEFAULT NULL,
  `USER_PHONE` varchar(255) DEFAULT NULL,
  `USER_PRENOM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_USER`),
  KEY `FK36EBCBFFB8CE52` (`ID_COMPTE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=109 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`ID_USER`, `DATE_CREATION`, `DATE_UPDATED`, `ID_COMPTE`, `USER_LOGGED`, `USER_MAIL`, `USER_NOM`, `USER_PHONE`, `USER_PRENOM`) VALUES
(83, '2015-03-27 13:32:54', '2015-03-27 13:32:54', 84, 0, 'dysow@gandal.com', 'SOW', '00221775698541', 'Diom Pathé'),
(102, '2015-11-13 12:01:37', '2015-11-13 12:01:37', 103, 0, 'mosow@gandal.com', 'SOW', '00221771843646', 'Mouhamadou Bamba'),
(103, '2015-11-13 12:03:05', '2015-11-13 12:03:05', 104, 0, 'ddiaw@gandal.com', 'DIAW', '00221770361086', 'Dame'),
(104, '2015-11-13 12:03:52', '2015-11-13 12:03:52', 105, 0, 'kcisse@gandal.com', 'CISSE', '00221774789541', 'khadim'),
(105, '2015-11-13 12:04:30', '2015-11-13 12:04:30', 106, 0, 'athiang@gandal.com', 'THIANG', '00221771133245', 'Aldjouma'),
(106, '2015-11-13 12:05:01', '2015-11-13 12:05:01', 107, 0, 'isene@gandal.com', 'SENE', '00221774123658', 'Ibou'),
(107, '2015-11-13 12:05:29', '2015-11-13 12:05:29', 108, 0, 'msall@gandal.com', 'SALL', '00221778745632', 'Massaer'),
(108, '2015-11-13 15:34:48', '2015-11-13 15:54:36', 112, 0, 'sniang@gandal.com', 'NIANG', '00221774785654', 'Seydou');

-- --------------------------------------------------------

--
-- Structure de la table `user_role`
--

CREATE TABLE IF NOT EXISTS `user_role` (
  `ID_USER` bigint(20) NOT NULL,
  `ID_ROLE` bigint(20) NOT NULL,
  KEY `FK143BF46AAC23F03E` (`ID_ROLE`),
  KEY `FK143BF46AAC26C6E8` (`ID_USER`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `user_role`
--

INSERT INTO `user_role` (`ID_USER`, `ID_ROLE`) VALUES
(83, 3),
(83, 1),
(102, 1),
(104, 3),
(107, 1),
(107, 2),
(106, 1),
(106, 3),
(103, 1),
(103, 2),
(108, 1),
(108, 3),
(105, 1);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `activite`
--
ALTER TABLE `activite`
  ADD CONSTRAINT `FK9D4BF2FB1FB84AF7` FOREIGN KEY (`id_parent`) REFERENCES `activite` (`id_activite`);

--
-- Contraintes pour la table `activite_localisation`
--
ALTER TABLE `activite_localisation`
  ADD CONSTRAINT `FKF7E941045C9531D2` FOREIGN KEY (`ID_LOCALISATION`) REFERENCES `localisation` (`id_localisation`),
  ADD CONSTRAINT `FKF7E9410476C4DC48` FOREIGN KEY (`ID_ACTIVITE`) REFERENCES `activite` (`id_activite`);

--
-- Contraintes pour la table `journal`
--
ALTER TABLE `journal`
  ADD CONSTRAINT `FKAB64AF3776C4DC48` FOREIGN KEY (`id_activite`) REFERENCES `activite` (`id_activite`);

--
-- Contraintes pour la table `localisation`
--
ALTER TABLE `localisation`
  ADD CONSTRAINT `FKC3A7DA40AC26C6E8` FOREIGN KEY (`ID_USER`) REFERENCES `user` (`ID_USER`),
  ADD CONSTRAINT `FKC3A7DA40CACEAB3C` FOREIGN KEY (`id_parent`) REFERENCES `localisation` (`id_localisation`);

--
-- Contraintes pour la table `localisation_indicateur`
--
ALTER TABLE `localisation_indicateur`
  ADD CONSTRAINT `FK8E7B72755C9531D2` FOREIGN KEY (`id_localisation`) REFERENCES `localisation` (`id_localisation`),
  ADD CONSTRAINT `FK8E7B72759D9867FE` FOREIGN KEY (`id_indicateur`) REFERENCES `indicateur` (`id_indicateur`);

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK36EBCBFFB8CE52` FOREIGN KEY (`ID_COMPTE`) REFERENCES `compte` (`ID_COMPTE`);

--
-- Contraintes pour la table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK143BF46AAC23F03E` FOREIGN KEY (`ID_ROLE`) REFERENCES `role` (`ID_ROLE`),
  ADD CONSTRAINT `FK143BF46AAC26C6E8` FOREIGN KEY (`ID_USER`) REFERENCES `user` (`ID_USER`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
