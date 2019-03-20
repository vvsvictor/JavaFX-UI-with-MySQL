-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-03-2019 a las 20:50:28
-- Versión del servidor: 10.1.37-MariaDB
-- Versión de PHP: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dbclass`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `assignatura`
--

CREATE TABLE `assignatura` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `credits` int(11) DEFAULT NULL,
  `descripcio` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `assignatura`
--

INSERT INTO `assignatura` (`id`, `nom`, `credits`, `descripcio`) VALUES
(1, 'ifne', 9, 'f'),
(2, 'fsegf', 3, 'egterg'),
(3, 'afaef', 55, 'sfes');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `avaluacio`
--

CREATE TABLE `avaluacio` (
  `id` int(11) NOT NULL,
  `id_assignatura` int(11) NOT NULL,
  `id_estudiant` int(11) NOT NULL,
  `nota` float DEFAULT NULL,
  `any` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curs`
--

CREATE TABLE `curs` (
  `id` int(11) NOT NULL,
  `any` int(11) DEFAULT NULL,
  `id_professor` int(11) NOT NULL,
  `id_assignatura` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estudiant`
--

CREATE TABLE `estudiant` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `dni` varchar(20) DEFAULT NULL,
  `adreca` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `estudiant`
--

INSERT INTO `estudiant` (`id`, `nom`, `dni`, `adreca`) VALUES
(1, 'Victor Vivancos', '3944343L', 'Carrer cartellà'),
(2, 'Eduardo Torres', '39441061V', 'Calle Castor '),
(3, 'Hola', '3545', 'fgzrgzr'),
(4, 'Emili', '4545', 'fhaejf'),
(5, 'test', 'gdg', 'rdg'),
(6, 'kjp', '5555', 'vvm'),
(7, '', '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `professor`
--

CREATE TABLE `professor` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `departament` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `professor`
--

INSERT INTO `professor` (`id`, `nom`, `departament`) VALUES
(1, 'Emili', 'Programacio'),
(2, 'fgfh', 'fght'),
(3, 'ljppkj', 'hbbikhh'),
(4, 'knhj', '');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `assignatura`
--
ALTER TABLE `assignatura`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `avaluacio`
--
ALTER TABLE `avaluacio`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_assignatura` (`id_assignatura`),
  ADD KEY `id_estudiant` (`id_estudiant`);

--
-- Indices de la tabla `curs`
--
ALTER TABLE `curs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_assignatura` (`id_assignatura`),
  ADD KEY `id_professor` (`id_professor`);

--
-- Indices de la tabla `estudiant`
--
ALTER TABLE `estudiant`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `professor`
--
ALTER TABLE `professor`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `assignatura`
--
ALTER TABLE `assignatura`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `avaluacio`
--
ALTER TABLE `avaluacio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `curs`
--
ALTER TABLE `curs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `estudiant`
--
ALTER TABLE `estudiant`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `professor`
--
ALTER TABLE `professor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `avaluacio`
--
ALTER TABLE `avaluacio`
  ADD CONSTRAINT `avaluacio_ibfk_1` FOREIGN KEY (`id_assignatura`) REFERENCES `assignatura` (`id`),
  ADD CONSTRAINT `avaluacio_ibfk_2` FOREIGN KEY (`id_estudiant`) REFERENCES `estudiant` (`id`);

--
-- Filtros para la tabla `curs`
--
ALTER TABLE `curs`
  ADD CONSTRAINT `curs_ibfk_1` FOREIGN KEY (`id_assignatura`) REFERENCES `assignatura` (`id`),
  ADD CONSTRAINT `curs_ibfk_2` FOREIGN KEY (`id_professor`) REFERENCES `professor` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
