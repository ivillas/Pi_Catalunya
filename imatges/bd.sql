-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Servidor: mysql:3306
-- Tiempo de generación: 20-05-2025 a las 10:21:54
-- Versión del servidor: 5.7.44
-- Versión de PHP: 8.2.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `PuntsInteresCat`
--
CREATE DATABASE IF NOT EXISTS `PuntsInteresCat` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `PuntsInteresCat`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Desti`
--

CREATE TABLE `Desti` (
  `id` varchar(4) NOT NULL,
  `provincia` varchar(25) DEFAULT NULL,
  `comarca` varchar(25) DEFAULT NULL,
  `ciutat` varchar(30) DEFAULT NULL,
  `CP` varchar(5) DEFAULT NULL,
  `esDePlatja` tinyint(1) NOT NULL,
  `esDeMontanya` tinyint(1) NOT NULL,
  `pis` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Desti`
--

INSERT INTO `Desti` (`id`, `provincia`, `comarca`, `ciutat`, `CP`, `esDePlatja`, `esDeMontanya`, `pis`) VALUES
('001', 'Lleida', 'La Noguera', 'Balaguer', '25600', 0, 1, '001,002,003'),
('002', 'Lleida', 'La Noguera', 'GERB', '25614', 0, 1, ''),
('003', 'Barcelona', 'Barcelones', 'Barcelona', '08080', 1, 0, NULL),
('004', 'Girona', 'Girones', 'Girona', '08641', 0, 1, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PuntInteres`
--

CREATE TABLE `PuntInteres` (
  `id` varchar(4) NOT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `descripcio` varchar(500) DEFAULT NULL,
  `tipus` varchar(500) DEFAULT NULL,
  `activitats` varchar(500) DEFAULT NULL,
  `desti` varchar(4) DEFAULT NULL,
  `imatge` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `PuntInteres`
--

INSERT INTO `PuntInteres` (`id`, `nom`, `descripcio`, `tipus`, `activitats`, `desti`, `imatge`) VALUES
('001', 'Castell Formos', 'El castell de balaguer', 'Gastronòmic,Cultural,Oci,Esport', 'Natació,Compres', '001', 'Castell_Formos_Balaguer.JPG'),
('002', 'Riu Segre', 'El riu de balaguer', 'Gastronòmic,Cultural,Oci,Esport', 'Natació,Compres', '001', 'riu segre.jpg'),
('003', 'Sant Cris', 'El san crist de balaguer	', 'Gastronòmic,Cultural,Oci,Esport', 'Natació,Compres', '001', 'El_Sant_Crist_Balaguer.JPG');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Desti`
--
ALTER TABLE `Desti`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `PuntInteres`
--
ALTER TABLE `PuntInteres`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
