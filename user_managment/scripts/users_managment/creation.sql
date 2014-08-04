
-- Database creation ---
CREATE DATABASE  `framework` ;

-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Jeu 22 Mai 2014 à 18:14
-- Version du serveur: 5.5.24-log
-- Version de PHP: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Base de données: `framework`
--

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLEDESC` varchar(255) COLLATE latin1_german1_ci DEFAULT NULL,
  `ROLENAME` varchar(255) COLLATE latin1_german1_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=4 ;

--
-- Contenu de la table `role`
--

INSERT INTO `role` (`ID`, `ROLEDESC`, `ROLENAME`) VALUES
(1, 'Admin', 'Admin'),
(2, 'Manager', 'Manager'),
(3, 'User', 'User');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USREMAIL` varchar(50) COLLATE latin1_german1_ci DEFAULT NULL,
  `USRLOGIN` varchar(50) COLLATE latin1_german1_ci DEFAULT NULL,
  `USRNOM` varchar(50) COLLATE latin1_german1_ci NOT NULL,
  `USRPASSWORD` varchar(64) COLLATE latin1_german1_ci DEFAULT NULL,
  `USRPHONE` varchar(50) COLLATE latin1_german1_ci DEFAULT NULL,
  `USRPRENOM` varchar(50) COLLATE latin1_german1_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=7 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`ID`, `USREMAIL`, `USRLOGIN`, `USRNOM`, `USRPASSWORD`, `USRPHONE`, `USRPRENOM`) VALUES
(1, 'admin@groupeidyal.com', 'admin', 'admin', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', '221334147854', 'admin'),
(2, 'iyade@groupeidyal.com', 'yade', 'yade', '9bba5c53a0545e0c80184b946153c9f58387e3bd1d4ee35740f29ac2e718b019', '775972243', 'Ibrahima'),
(3, 'addiop@groupeidyal.com', 'addiop', 'Diop', 'a1d0e80df7f90ab5cad313aa9235e4d0fb7a33303503e9f7966a55fc0d8c23e9', '773257416', 'Adama'),
(4, 'pdiedhiou@idyal.com', 'pdiedhiou', 'Diedhiou', 'd9c7abd2f526dde65e7cf401a11fa19e17d1c45909cb13510693098e67c370f4', '77 47851236', 'Paullette'),
(5, 'dysow@groupeidyal.com', 'dysow', 'SOW', '77cb141d760ec14a09e1a4f1e3373d5865b41d300adb854200ddd6096c9ed55f', '777049088', 'Diom Yero'),
(6, 'libasse@groupeidyal.com', 'libasse', 'libasse', '40214c47c949eda6d39eda8b26b8c4c2d253fc17fc2c39b1b6eeb72d79cfb18e', '', 'libasse');

-- --------------------------------------------------------

--
-- Structure de la table `user_roles`
--

CREATE TABLE IF NOT EXISTS `user_roles` (
  `Role_roleid` int(11) NOT NULL,
  `User_userid` int(11) NOT NULL,
  `login` varchar(50) COLLATE latin1_german1_ci DEFAULT NULL,
  `password` varchar(64) COLLATE latin1_german1_ci DEFAULT NULL,
  `rolename` varchar(20) COLLATE latin1_german1_ci DEFAULT NULL,
  PRIMARY KEY (`Role_roleid`,`User_userid`),
  KEY `FK_user_roles_User_userid` (`User_userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;

--
-- Contenu de la table `user_roles`
--

INSERT INTO `user_roles` (`Role_roleid`, `User_userid`, `login`, `password`, `rolename`) VALUES
(1, 1, 'admin', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Admin'),
(1, 5, NULL, NULL, NULL),
(2, 2, 'yade', '9bba5c53a0545e0c80184b946153c9f58387e3bd1d4ee35740f29ac2e718b019', 'Manager'),
(2, 3, 'addiop', 'a1d0e80df7f90ab5cad313aa9235e4d0fb7a33303503e9f7966a55fc0d8c23e9', 'Manager'),
(2, 4, NULL, NULL, NULL),
(2, 5, NULL, NULL, NULL),
(2, 6, NULL, NULL, NULL),
(3, 5, NULL, NULL, NULL),
(3, 6, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `user_role_view`
--
CREATE TABLE IF NOT EXISTS `user_role_view` (
   `login` varchar(50)
  ,`password` varchar(64)
  ,`rolename` varchar(255)
);
-- --------------------------------------------------------

--
-- Structure de la vue `user_role_view`
--
DROP TABLE IF EXISTS `user_role_view`;

CREATE ALGORITHM=UNDEFINED DEFINER=`idyalUser`@`localhost` SQL SECURITY DEFINER VIEW `user_role_view` AS select `u`.`USRLOGIN` AS `login`,`u`.`USRPASSWORD` AS `password`,`r`.`ROLENAME` AS `rolename` from ((`user` `u` join `role` `r`) join `user_roles` `ur`) where ((`ur`.`Role_roleid` = `r`.`ID`) and (`ur`.`User_userid` = `u`.`ID`));

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `user_roles`
--
ALTER TABLE `user_roles`
ADD CONSTRAINT `FK_user_roles_Role_roleid` FOREIGN KEY (`Role_roleid`) REFERENCES `role` (`ID`),
ADD CONSTRAINT `FK_user_roles_User_userid` FOREIGN KEY (`User_userid`) REFERENCES `user` (`ID`);

