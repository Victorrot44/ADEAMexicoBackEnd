-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-03-2022 a las 01:32:53
-- Versión del servidor: 10.4.22-MariaDB
-- Versión de PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `prueba_adea_mexico`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `login` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `password` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `cliente` float NOT NULL,
  `email` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fecha_alta` date NOT NULL DEFAULT current_timestamp(),
  `fecha_baja` datetime DEFAULT NULL,
  `status` char(1) COLLATE utf8_spanish_ci NOT NULL DEFAULT 'A',
  `intentos` float NOT NULL DEFAULT 0,
  `fecha_revocado` date DEFAULT NULL,
  `fecha_vigencia` date DEFAULT NULL,
  `no_acceso` int(11) DEFAULT NULL,
  `apellido_paterno` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `apellido_materno` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `area` int(4) DEFAULT NULL,
  `fecha_modificacion` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `login`, `password`, `nombre`, `cliente`, `email`, `fecha_alta`, `fecha_baja`, `status`, `intentos`, `fecha_revocado`, `fecha_vigencia`, `no_acceso`, `apellido_paterno`, `apellido_materno`, `area`, `fecha_modificacion`) VALUES
(9, 'VUser518', 'MTIzNA==', 'Victor', 123, '', '2022-03-27', NULL, 'B', 0, NULL, '2022-03-27', NULL, '', '', NULL, '2022-03-27 17:16:55'),
(14, 'VUser193', 'YWRtaW4xMjM=', 'Victor', 123, '', '2022-03-27', NULL, 'R', 0, NULL, NULL, NULL, 'Rodriguez', 'Ramirez', NULL, '2022-03-27 17:17:00'),
(15, 'SuperAdmin', 'YWRtaW4xMjM=', 'Victor Manuel', 12, 'victor@example.com', '2022-03-27', NULL, 'A', 0, NULL, NULL, NULL, 'Rodriguez', 'Ramirez', 12, '2022-03-27 17:18:28');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login` (`login`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
