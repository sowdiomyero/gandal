-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Mer 21 Octobre 2015 à 16:47
-- Version du serveur: 5.5.24-log
-- Version de PHP: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `esecure`
--

-- --------------------------------------------------------


--
-- Structure de la table `compte`
--

CREATE TABLE IF NOT EXISTS `compte` (
  `ID_COMPTE` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE_CREATION` date DEFAULT NULL,
  `ETAT` int(11) DEFAULT NULL,
  `LOGIN` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `DATE_UPDATED` datetime DEFAULT NULL,
  PRIMARY KEY (`ID_COMPTE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=110 ;

--
-- Contenu de la table `compte`
--

INSERT INTO `compte` (`ID_COMPTE`, `DATE_CREATION`, `ETAT`, `LOGIN`, `PASSWORD`, `DATE_UPDATED`) VALUES
(42, '2015-02-20', 1, 'esecure', '09733bfd6de7c6bc5b0df98e68b8918cad54046e41265a28c7c27fbf5105be25', NULL), --esecure esecure
(73, '2015-02-20', 1, 'ibra', 'd1d0abe7dc8fcff02f17c3a26d0e3281f41d149e2d7fb85acd1d61db7bf5a287', '2015-03-27 15:32:02'),
(84, '2015-03-27', 1, 'dysow', '77cb141d760ec14a09e1a4f1e3373d5865b41d300adb854200ddd6096c9ed55f', '2015-03-27 13:34:15'), --dysow dysow
(85, '2015-03-27', 1, 'sowdiomyero', '8cfa3fce61fccd4ed12bdd528dc402b851ff99704a462770179007664fc52e51', '2015-03-27 15:59:52'),
(109, '2015-10-21', 1, 'admin', 'ffd689afb97db461091e69f33ab8c045f63582d9df68a6738537d206a7437d19', '2015-10-21 16:31:19'); -

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
-- Structure de la table `messagerie_param`
--

CREATE TABLE IF NOT EXISTS `messagerie_param` (
  `ID_PARAM` bigint(20) NOT NULL AUTO_INCREMENT,
  `PARAM_SMTP_EMAIL` varchar(255) DEFAULT NULL,
  `PARAM_SMTP_HOST` varchar(255) DEFAULT NULL,
  `PARAM_SMTP_PASSWORD` varchar(255) DEFAULT NULL,
  `PARAM_SMTP_PORT` int(11) DEFAULT NULL,
  `PARAM_SMTP_SSL` bit(1) DEFAULT NULL,
  `PARAM_SMTP_TRANSPORT` varchar(255) DEFAULT NULL,
  `PARAM_SMTP_USERNAME` varchar(255) DEFAULT NULL,
  `DATE_CREATION` datetime DEFAULT NULL,
  `DATE_UPDATED` datetime DEFAULT NULL,
  PRIMARY KEY (`ID_PARAM`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `parametre`
--

CREATE TABLE IF NOT EXISTS `parametre` (
  `ID_PARAM` bigint(20) NOT NULL AUTO_INCREMENT,
  `PARAM_SMTP_EMAIL` varchar(255) DEFAULT NULL,
  `PARAM_SMTP_HOST` varchar(255) DEFAULT NULL,
  `PARAM_SMTP_PASSWORD` varchar(255) DEFAULT NULL,
  `PARAM_SMTP_SSL` bit(1) DEFAULT NULL,
  `PARAM_SMTP_TRANSPORT` varchar(255) DEFAULT NULL,
  `PARAM_SMTP_USERNAME` varchar(255) DEFAULT NULL,
  `PARAM_SMTP_PORT` int(11) DEFAULT NULL,
  `PARAM_RAPPORT_BENEFICIAIRE` varchar(255) DEFAULT NULL,
  `PARAM_RAPPORT_DONNES` varchar(255) DEFAULT NULL,
  `PARAM_RAPPORT_FORMAT` varchar(255) DEFAULT NULL,
  `PARAM_RAPPORT_FREQUENCE` varchar(255) DEFAULT NULL,
  `PARAM_RAPPORT_REPERTOIRE` varchar(255) DEFAULT NULL,
  `ID_MESSAGERIE` bigint(20) DEFAULT NULL,
  `ID_RAPPORT` bigint(20) DEFAULT NULL,
  `DATE_CREATION` datetime DEFAULT NULL,
  `DATE_UPDATED` datetime DEFAULT NULL,
  PRIMARY KEY (`ID_PARAM`),
  KEY `FK747EB52FB94F0F69` (`ID_RAPPORT`),
  KEY `FK747EB52F91DE8D91` (`ID_MESSAGERIE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `parametre`
--

INSERT INTO `parametre` (`ID_PARAM`, `PARAM_SMTP_EMAIL`, `PARAM_SMTP_HOST`, `PARAM_SMTP_PASSWORD`, `PARAM_SMTP_SSL`, `PARAM_SMTP_TRANSPORT`, `PARAM_SMTP_USERNAME`, `PARAM_SMTP_PORT`, `PARAM_RAPPORT_BENEFICIAIRE`, `PARAM_RAPPORT_DONNES`, `PARAM_RAPPORT_FORMAT`, `PARAM_RAPPORT_FREQUENCE`, `PARAM_RAPPORT_REPERTOIRE`, `ID_MESSAGERIE`, `ID_RAPPORT`, `DATE_CREATION`, `DATE_UPDATED`) VALUES
(1, 'esecure.idyal@gmail.com', 'smtp.gmail.com', 'esecure.idyal', b'1', 'SSL', 'idyal.esecure@gmail.com', 5873, 'dyso@gmail.com', 'ALL_TRANSACTIONS', 'PDF', 'TOUS_LES_JOURS', 'dr', NULL, 1, NULL, NULL);

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
  PRIMARY KEY (`ID_PARAMETREMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `rapport_param`
--

CREATE TABLE IF NOT EXISTS `rapport_param` (
  `ID_PARAM` bigint(20) NOT NULL AUTO_INCREMENT,
  `PARAM_RAPPORT_BENEFICIAIRE` varchar(255) DEFAULT NULL,
  `PARAM_RAPPORT_DONNES` varchar(255) DEFAULT NULL,
  `PARAM_RAPPORT_FORMAT` varchar(255) DEFAULT NULL,
  `PARAM_RAPPORT_FREQUENCE` varchar(255) DEFAULT NULL,
  `PARAM_RAPPORT_REPERTOIRE` varchar(255) DEFAULT NULL,
  `DATE_CREATION` datetime DEFAULT NULL,
  `DATE_UPDATED` datetime DEFAULT NULL,
  PRIMARY KEY (`ID_PARAM`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `rapport_param`
--

INSERT INTO `rapport_param` (`ID_PARAM`, `PARAM_RAPPORT_BENEFICIAIRE`, `PARAM_RAPPORT_DONNES`, `PARAM_RAPPORT_FORMAT`, `PARAM_RAPPORT_FREQUENCE`, `PARAM_RAPPORT_REPERTOIRE`, `DATE_CREATION`, `DATE_UPDATED`) VALUES
(1, 'sowdiomyero@gmail.com', 'TRANSACTIONS_FAILED', 'PDF', 'TOUS_LES_LUNDIS', 'C:\\dev\\sauvegardes\\ESECURE\\rapports', NULL, '2015-03-25 12:52:16');

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `ID_ROLE` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(255) DEFAULT NULL,
  `ROLE_DESC` varchar(255) DEFAULT NULL,
  `DATE_CREATION` datetime DEFAULT NULL,
  `DATE_UPDATED` datetime DEFAULT NULL,
  PRIMARY KEY (`ID_ROLE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `role`
--

INSERT INTO `role` (`ID_ROLE`, `ROLE_NAME`, `ROLE_DESC`, `DATE_CREATION`, `DATE_UPDATED`) VALUES
(1, 'ROLE_ADMIN', 'administrateur', NULL, NULL),
(2, 'ROLE_USER', 'Utilisateur Simple', NULL, NULL),
(3, 'ROLE_SUPER_ADMIN', 'Super Administrateur', NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `ID_USER` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_LOGGED` int(11) DEFAULT NULL,
  `USER_MAIL` varchar(255) DEFAULT NULL,
  `USER_NOM` varchar(255) DEFAULT NULL,
  `USER_PHONE` varchar(255) DEFAULT NULL,
  `USER_PRENOM` varchar(255) DEFAULT NULL,
  `ID_COMPTE` bigint(20) DEFAULT NULL,
  `DATE_CREATION` datetime DEFAULT NULL,
  `DATE_UPDATED` datetime DEFAULT NULL,
  PRIMARY KEY (`ID_USER`),
  KEY `FK_ID_COMPTE` (`ID_COMPTE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=100 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`ID_USER`, `USER_LOGGED`, `USER_MAIL`, `USER_NOM`, `USER_PHONE`, `USER_PRENOM`, `ID_COMPTE`, `DATE_CREATION`, `DATE_UPDATED`) VALUES
(72, 1, 'ibra@khidma.org', 'Yade', '33669932494', 'Ibrahima', 73, NULL, '2015-03-27 15:32:07'),
(83, 0, 'dysow@gandal.com', 'SOW', NULL, 'Diom Pathé', 84, '2015-03-27 13:32:54', '2015-03-27 13:32:54'),
(84, 0, 'sowdiomyero@gmail.com', 'SOW', NULL, 'Diom Yero', 85, '2015-03-27 15:59:52', '2015-03-27 15:59:52'),
(99, 0, 'admin@gandal.com', 'Diom Yero', '33669932494', 'SOW', 109, '2015-10-21 16:31:19', '2015-10-21 16:31:19');

-- --------------------------------------------------------

--
-- Structure de la table `user_role`
--

CREATE TABLE IF NOT EXISTS `user_role` (
  `ID_USER` bigint(20) NOT NULL,
  `ID_ROLE` bigint(20) NOT NULL,
  KEY `FK_ID_ROLE` (`ID_ROLE`),
  KEY `FK_ID_USER` (`ID_USER`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `user_role`
--

INSERT INTO `user_role` (`ID_USER`, `ID_ROLE`) VALUES
(72, 1),
(83, 3),
(84, 2),
(83, 1),
(99, 2);

--
-- Contraintes pour les tables exportées
--


--
-- Contraintes pour la table `parametre`
--
ALTER TABLE `parametre`
  ADD CONSTRAINT `FK747EB52F91DE8D91` FOREIGN KEY (`ID_MESSAGERIE`) REFERENCES `messagerie_param` (`ID_PARAM`),
  ADD CONSTRAINT `FK747EB52FB94F0F69` FOREIGN KEY (`ID_RAPPORT`) REFERENCES `rapport_param` (`ID_PARAM`);

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK_ID_COMPTE` FOREIGN KEY (`ID_COMPTE`) REFERENCES `compte` (`ID_COMPTE`);

--
-- Contraintes pour la table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK_ID_ROLE` FOREIGN KEY (`ID_ROLE`) REFERENCES `role` (`ID_ROLE`),
  ADD CONSTRAINT `FK_ID_USER` FOREIGN KEY (`ID_USER`) REFERENCES `user` (`ID_USER`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
