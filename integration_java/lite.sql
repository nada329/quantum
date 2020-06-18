-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  jeu. 18 juin 2020 à 03:56
-- Version du serveur :  10.1.38-MariaDB
-- Version de PHP :  5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `lite`
--

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `discreption` varchar(2500) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `classe`
--

CREATE TABLE `classe` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `salle` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `specialite` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `classe`
--

INSERT INTO `classe` (`id`, `nom`, `salle`, `specialite`) VALUES
(1, 'az', 'az', 'az'),
(2, '3a12', '22', 'sss');

-- --------------------------------------------------------

--
-- Structure de la table `cours`
--

CREATE TABLE `cours` (
  `id` int(11) NOT NULL,
  `prof_id` int(11) DEFAULT NULL,
  `libelle` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `cours`
--

INSERT INTO `cours` (`id`, `prof_id`, `libelle`, `nom`, `image`) VALUES
(25, 12, 'Math', 'Cours Algebre', 'Algebre.pdf'),
(26, 12, 'Algorithme', 'Programmation C', 'C.pdf'),
(27, 12, 'math', 'math', 'Modèle de.docx');

-- --------------------------------------------------------

--
-- Structure de la table `eleve`
--

CREATE TABLE `eleve` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date_de_naissance` date NOT NULL,
  `classe_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `eleve`
--

INSERT INTO `eleve` (`id`, `nom`, `prenom`, `date_de_naissance`, `classe_id`) VALUES
(2, 'ahmed', 'kastalli', '2015-01-01', 1),
(3, 'ali', 'ali', '2015-01-01', 1),
(13, 'zarrad', 'nourhene', '2020-02-03', 1);

-- --------------------------------------------------------

--
-- Structure de la table `enseignant`
--

CREATE TABLE `enseignant` (
  `id` int(11) NOT NULL,
  `classe_id` int(11) DEFAULT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `matiere` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `event`
--

CREATE TABLE `event` (
  `idevent` int(11) NOT NULL,
  `nomEvent` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dateEvent` date NOT NULL,
  `nb_p` int(11) NOT NULL,
  `prix` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` longtext COLLATE utf8_unicode_ci,
  `adresse_event` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `type_event` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `fos_user`
--

CREATE TABLE `fos_user` (
  `id` int(11) NOT NULL,
  `username` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `fos_user`
--

INSERT INTO `fos_user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`) VALUES
(1, 'nour', '', 'nourhene@gmail.com', '', 0, NULL, '1234ll', NULL, NULL, NULL, ''),
(3, 'nourhene', 'nourhene', 'nourhen@esprit.tn', 'nourhen@esprit.tn', 1, NULL, '$2y$13$5JRYmQgTN7/r1fjhRKRXVePNg6VGlSmIHQGfQtt9ytaBWpsqoHrkW', '2020-02-13 09:19:57', NULL, NULL, 'a:0:{}'),
(4, 'admin1', 'admin1', 'user@user.fr', 'user@user.fr', 1, NULL, '$2y$13$hgsXSQSVLrN4ixtb8fi2fu7wpOwsRdiNTh1rxSygm5N6VsGnpcRP2', NULL, 'BOKHfuGOKXViSvpeJHbCsPmcX1d_y_MiX140gjUoDw0', NULL, 'a:1:{i:0;s:10:\"EVENT\";}'),
(5, 'admin', 'admin', 'admin@admin.fr', 'admin@admin.fr', 1, NULL, '$2y$13$DzNnthoO0tGw7Bpe3ydV9uyMnFnsdhIFZeJ4CG/z.tIDenvQ/Ndw6', NULL, 'Te3zJd7FlRVYUxNr0Pcu-ufyF1UnnASqyP_HkR16hbY', NULL, 'a:1:{i:0;s:10:\"ROLE_ADMIN\";}'),
(6, 'zarrad', 'zarrad', 'nourhen.zarrd@esprit.tn', 'nourhen.zarrd@esprit.tn', 1, NULL, '$2y$13$ZKcr7PxahG5B0q.46KgWpOSWanKZp9cp.nHUx41WhXg8iXdHnhel6', '2020-03-25 16:16:12', NULL, NULL, 'a:1:{i:0;s:10:\"ROLE_ADMIN\";}'),
(7, 'ahmed', 'ahmed', 'ahmed@ahmed.com', 'ahmed@ahmed.com', 1, NULL, '$2y$13$Hm1Emr7YnLpOQGgfDd45Hux8g.foph8f8fAl8DoJKBN1fimE7CvbO', '2020-02-27 00:07:30', NULL, NULL, 'a:0:{}'),
(12, 'ens1', 'ens1', 'd.ve.e@re.ra', 'd.ve.e@re.ra', 1, NULL, '$2y$10$ctIl7D2SIjmwD041Txbsu.qGAhtGtA5jc9Vu17smHhxmjRYqDLHcm', NULL, NULL, NULL, 'a:1:{i:0;s:10:\"ENSEIGNANT\";}'),
(13, 'nourhene12', 'nourhene12', 'nourhene.errd@gmil.com', 'nourhene.errd@gmil.com', 1, NULL, '$2y$10$ctIl7D2SIjmwD041Txbsu.qGAhtGtA5jc9Vu17smHhxmjRYqDLHcm', NULL, NULL, NULL, 'a:0:{}'),
(14, 'nourhene123', 'nourhene123', 'nourhene@gmail.com', 'nourhene@gmail.com', 1, NULL, '$2y$10$RZHrLd1ZzVNRRbV/xu.uPuoPKGIWHzw7JLZE6Nry4P2uLeq9WQPxq', NULL, NULL, NULL, 'a:0:{}');

-- --------------------------------------------------------

--
-- Structure de la table `inscription_event`
--

CREATE TABLE `inscription_event` (
  `id` int(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `numtel` varchar(30) NOT NULL,
  `nomevent` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `livre`
--

CREATE TABLE `livre` (
  `id` int(11) NOT NULL,
  `id_categorie` int(11) DEFAULT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `resume` varchar(5000) COLLATE utf8_unicode_ci NOT NULL,
  `photo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `auteur` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Nb_de_pages` int(11) NOT NULL,
  `editur` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `livres`
--

CREATE TABLE `livres` (
  `id_type` int(11) DEFAULT NULL,
  `idLivre` int(11) NOT NULL,
  `nom` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nbPersonnes` int(11) DEFAULT '0',
  `description` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `auteur` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `quantite` int(11) DEFAULT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `likes` int(11) NOT NULL DEFAULT '0',
  `dislikes` int(11) NOT NULL DEFAULT '0',
  `res` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `livres`
--

INSERT INTO `livres` (`id_type`, `idLivre`, `nom`, `nbPersonnes`, `description`, `auteur`, `quantite`, `image`, `likes`, `dislikes`, `res`) VALUES
(21, 160, 'aaa', 0, 'aaa', 'aaaa', 200, '30176572_2341127372570848_49594070_o.png', 0, 0, 0),
(7, 161, 'candide', 0, 'roman', 'voltaire', 55, 'Le_Livre_de_Poche_(logo,_ancien).png', 0, 0, 0);

-- --------------------------------------------------------

--
-- Structure de la table `prof`
--

CREATE TABLE `prof` (
  `id` int(11) NOT NULL,
  `nom_prof` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `prof`
--

INSERT INTO `prof` (`id`, `nom_prof`) VALUES
(1, 'prof'),
(12, 'ens1');

-- --------------------------------------------------------

--
-- Structure de la table `questions`
--

CREATE TABLE `questions` (
  `id` int(11) NOT NULL,
  `question` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `questions`
--

INSERT INTO `questions` (`id`, `question`) VALUES
(1, 'Quel nom donne-t-on au deuxième élément (nombre) d’une division ?'),
(2, 'Quel nom donne-t-on au deuxième élément ?'),
(21, 'Test Question !');

-- --------------------------------------------------------

--
-- Structure de la table `reponse`
--

CREATE TABLE `reponse` (
  `id` int(11) NOT NULL,
  `question` int(11) DEFAULT NULL,
  `valeur` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `reponse` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `reponse`
--

INSERT INTO `reponse` (`id`, `question`, `valeur`, `reponse`) VALUES
(1, 1, 'correct', '24'),
(2, 1, 'incorrect', '1247'),
(3, 1, 'incorrect', '697'),
(4, 2, 'incorrect', '5555'),
(5, 2, 'incorrect', '999'),
(6, 2, 'correct', '478'),
(17, 21, 'correct', 'a'),
(18, 21, 'incorrect', 'b'),
(19, 21, 'incorrect', 'c');

-- --------------------------------------------------------

--
-- Structure de la table `reponse_eleve`
--

CREATE TABLE `reponse_eleve` (
  `id` int(11) NOT NULL,
  `questions_id` int(11) DEFAULT NULL,
  `eleve_id` int(11) DEFAULT NULL,
  `score` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `reponse_eleve`
--

INSERT INTO `reponse_eleve` (`id`, `questions_id`, `eleve_id`, `score`) VALUES
(146, 1, 13, 10),
(147, 2, 13, 0),
(148, 21, 13, 10),
(149, 1, 13, 10),
(150, 2, 13, 0),
(151, 21, 13, 10);

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `duree` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `id_Livre` int(11) DEFAULT NULL,
  `id_User` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reservationlivre`
--

CREATE TABLE `reservationlivre` (
  `id` int(11) NOT NULL,
  `idLivre` int(11) NOT NULL,
  `idEleve` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `reservationlivre`
--

INSERT INTO `reservationlivre` (`id`, `idLivre`, `idEleve`) VALUES
(191, 149, 2),
(195, 150, 4),
(197, 148, 4),
(198, 149, 4),
(203, 156, 2),
(204, 157, 2),
(205, 158, 2),
(210, 157, 4);

-- --------------------------------------------------------

--
-- Structure de la table `type`
--

CREATE TABLE `type` (
  `idL` int(11) NOT NULL,
  `libelle` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `type`
--

INSERT INTO `type` (`idL`, `libelle`) VALUES
(7, 'Action'),
(21, 'Aventure'),
(22, 'Bande dessinée'),
(23, 'Sport'),
(24, 'literature'),
(25, '');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `id_c` int(11) DEFAULT NULL,
  `prenom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `phone` char(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `nom`, `id_c`, `prenom`, `phone`) VALUES
(2, 'abdou', 'abdou', 'abdessalem.smirani@esprit.tn', 'abdessalem.smirani@esprit.tn', 1, NULL, '$2y$13$JAzQ8n5HzEdMaZZT0CXyyeCTl2Vz9iuglgPknSv8ctRJ0/A2Ispdq', '2020-02-21 20:14:11', NULL, NULL, 'a:1:{i:0;s:5:\"ADMIN\";}', 'abdou', NULL, 'abdou', '0'),
(3, 'admin', 'admin', 'abdessalem.smiragni@esprit.tn', 'abdessalem.smiragni@esprit.tn', 1, NULL, '$2y$13$8R25zU2fBpTEuXHW76YbYeeur2OBYtiYZVgrUhR2CPi8fEbDmwpp.', '2020-03-23 13:16:52', NULL, NULL, 'a:1:{i:0;s:5:\"ADMIN\";}', 'admin', NULL, '', '0'),
(4, 'eleve', 'eleve', 'test@t.hr', 'test@t.hr', 1, NULL, '$2y$13$8R25zU2fBpTEuXHW76YbYeeur2OBYtiYZVgrUhR2CPi8fEbDmwpp.', '2020-02-19 09:26:14', NULL, NULL, 'a:1:{i:0;s:5:\"ELEVE\";}', 'eleve', NULL, 'eleve', '90798275'),
(5, 'eleve2', 'eleve2', 'aaa@b.B', 'aaa@b.b', 1, NULL, '$2y$13$PgojVM1qJiJ0db6SHue5CuE2rnPrlsoQ2Bn6NgxodJdZsSOPh1qUK', '2020-02-22 22:20:23', NULL, NULL, 'a:1:{i:0;s:5:\"ELEVE\";}', 'eleve2', NULL, '', '0'),
(6, 'eleve1', 'eleve1', 'aaa@b.Bx', 'aaa@b.bx', 1, NULL, '$2y$13$UfNfYluYCdsgktmI7/Ic3OfLvN6ez9JehvOYjUktPpb3FLdFuPx66', '2020-02-22 18:14:07', NULL, NULL, 'a:1:{i:0;s:5:\"ELEVE\";}', 'eleve1', NULL, '', '0'),
(7, 'eleve0', 'eleve0', 'r.rrr@dzsd.d', 'r.rrr@dzsd.d', 1, NULL, '$2y$13$igaDq/S/EZNLqPgOwpXS.OgGJbL29id.kYBIQehkR4bLVYw8ESjcC', '2020-02-26 11:27:20', NULL, NULL, 'a:1:{i:0;s:5:\"ELEVE\";}', 'eleve0', NULL, '', '0'),
(8, 'parent', 'parent', 'd.d@dqs.s', 'd.d@dqs.s', 1, NULL, '$2y$13$qYDl38rJaSnV0TaeyDn4weGL0hpSdjBb15LrEKukPyrZEy7OVC7cK', '2020-02-22 12:08:02', NULL, NULL, 'a:1:{i:0;s:6:\"PARENT\";}', 'parent', NULL, '', '0'),
(9, 'ens11', 'ens11', 'abdessalem.smirani@esprit.tna', 'abdessalem.smirani@esprit.tna', 1, NULL, '$2y$13$iAFQIPxXbZwwdeNTOJzu7euMTPbUiLgqHiyb9rhKutDSc1GSrKw7C', '2020-02-23 10:30:41', NULL, NULL, 'a:1:{i:0;s:10:\"ENSEIGNANT\";}', 'ens', NULL, '', '0'),
(10, 'ELELE', 'ELELEL', 'AHJKL', 'HBJK', 0, 'HBJNK', 'GHBJK', '2020-02-25 00:00:00', NULL, NULL, 'a:1:{i:0;s:10:\"ENSEIGNANT\";}', 'HHH', NULL, '', '0'),
(11, 'ens2', 'ens2', 'a.a@ffg.er', 'a.a@ffg.er', 1, NULL, '$2y$13$n3UM0OONlXaIJw/ABFZVUueSXg5CZsfS0ZhXkky4Wpsx4m8V1vw/K', '2020-02-25 15:09:06', NULL, NULL, 'a:1:{i:0;s:10:\"ENSEIGNANT\";}', 'ens2', NULL, 'ens', '0'),
(12, 'ens1', 'ens1', 'd.ve.e@re.ra', 'd.ve.e@re.ra', 1, NULL, '$2y$13$8R25zU2fBpTEuXHW76YbYeeur2OBYtiYZVgrUhR2CPi8fEbDmwpp.', '2020-02-25 15:09:24', NULL, NULL, 'a:1:{i:0;s:10:\"ENSEIGNANT\";}', 'ens1', NULL, 'ens1', '0'),
(13, 'esn', 'esn', 'a.d.d@dez.ez', 'a.d.d@dez.ez', 1, NULL, '$2y$13$Q865lylndP9qyPnl1Rcjx.hI6FqJ4C42JOwLSR3HUQwPLpGxhZ6PW', '2020-02-24 23:45:25', NULL, NULL, 'a:1:{i:0;s:10:\"ENSEIGNANT\";}', 'esn', NULL, 'esn', '0');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `classe`
--
ALTER TABLE `classe`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `cours`
--
ALTER TABLE `cours`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_FDCA8C9CABC1F7FE` (`prof_id`);

--
-- Index pour la table `eleve`
--
ALTER TABLE `eleve`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_ECA105F78F5EA509` (`classe_id`);

--
-- Index pour la table `enseignant`
--
ALTER TABLE `enseignant`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_81A72FA18F5EA509` (`classe_id`);

--
-- Index pour la table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`idevent`);

--
-- Index pour la table `fos_user`
--
ALTER TABLE `fos_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_957A647992FC23A8` (`username_canonical`),
  ADD UNIQUE KEY `UNIQ_957A6479A0D96FBF` (`email_canonical`),
  ADD UNIQUE KEY `UNIQ_957A6479C05FB297` (`confirmation_token`);

--
-- Index pour la table `inscription_event`
--
ALTER TABLE `inscription_event`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `livre`
--
ALTER TABLE `livre`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_AC634F99C9486A13` (`id_categorie`);

--
-- Index pour la table `livres`
--
ALTER TABLE `livres`
  ADD PRIMARY KEY (`idLivre`),
  ADD KEY `IDX_927187A47FE4B2B` (`id_type`);

--
-- Index pour la table `prof`
--
ALTER TABLE `prof`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_5FB6DEC7B6F7494E` (`question`);

--
-- Index pour la table `reponse_eleve`
--
ALTER TABLE `reponse_eleve`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_3487E94BCB134CE` (`questions_id`),
  ADD KEY `IDX_3487E94A6CC7B2` (`eleve_id`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_42C84955832721AD` (`id_Livre`),
  ADD KEY `IDX_42C84955A6816575` (`id_User`);

--
-- Index pour la table `reservationlivre`
--
ALTER TABLE `reservationlivre`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `type`
--
ALTER TABLE `type`
  ADD PRIMARY KEY (`idL`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_8D93D64992FC23A8` (`username_canonical`),
  ADD UNIQUE KEY `UNIQ_8D93D649A0D96FBF` (`email_canonical`),
  ADD UNIQUE KEY `UNIQ_8D93D649C05FB297` (`confirmation_token`),
  ADD KEY `IDX_8D93D649C12C7510` (`id_c`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `classe`
--
ALTER TABLE `classe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `cours`
--
ALTER TABLE `cours`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT pour la table `eleve`
--
ALTER TABLE `eleve`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `enseignant`
--
ALTER TABLE `enseignant`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `event`
--
ALTER TABLE `event`
  MODIFY `idevent` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `fos_user`
--
ALTER TABLE `fos_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT pour la table `inscription_event`
--
ALTER TABLE `inscription_event`
  MODIFY `id` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT pour la table `livre`
--
ALTER TABLE `livre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `livres`
--
ALTER TABLE `livres`
  MODIFY `idLivre` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=162;

--
-- AUTO_INCREMENT pour la table `prof`
--
ALTER TABLE `prof`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `questions`
--
ALTER TABLE `questions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT pour la table `reponse`
--
ALTER TABLE `reponse`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT pour la table `reponse_eleve`
--
ALTER TABLE `reponse_eleve`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=152;

--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reservationlivre`
--
ALTER TABLE `reservationlivre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=211;

--
-- AUTO_INCREMENT pour la table `type`
--
ALTER TABLE `type`
  MODIFY `idL` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `cours`
--
ALTER TABLE `cours`
  ADD CONSTRAINT `FK_FDCA8C9CABC1F7FE` FOREIGN KEY (`prof_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `eleve`
--
ALTER TABLE `eleve`
  ADD CONSTRAINT `FK_ECA105F78F5EA509` FOREIGN KEY (`classe_id`) REFERENCES `classe` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `enseignant`
--
ALTER TABLE `enseignant`
  ADD CONSTRAINT `FK_81A72FA18F5EA509` FOREIGN KEY (`classe_id`) REFERENCES `classe` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `livre`
--
ALTER TABLE `livre`
  ADD CONSTRAINT `FK_AC634F99C9486A13` FOREIGN KEY (`id_categorie`) REFERENCES `categorie` (`id`);

--
-- Contraintes pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD CONSTRAINT `FK_5FB6DEC7B6F7494E` FOREIGN KEY (`question`) REFERENCES `questions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reponse_eleve`
--
ALTER TABLE `reponse_eleve`
  ADD CONSTRAINT `FK_3487E94A6CC7B2` FOREIGN KEY (`eleve_id`) REFERENCES `eleve` (`id`),
  ADD CONSTRAINT `FK_3487E94BCB134CE` FOREIGN KEY (`questions_id`) REFERENCES `questions` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
