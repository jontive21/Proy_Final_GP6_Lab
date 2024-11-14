-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-11-2024 a las 06:06:29
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `restaurant_g6`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `id_mesa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id_cliente`, `nombre`, `id_mesa`) VALUES
(1, 'Jeremias', 1),
(2, 'Fabricio', 1),
(3, 'Julio', 1),
(4, 'kira', 2),
(5, 'kiko', 2),
(6, 'Jeremias', 1),
(7, 'Pablito', 1),
(8, 'Kiko', 5),
(9, 'kagumi', 5),
(10, 'juan', 5),
(11, 'yeyu', 5),
(12, 'xami', 5),
(13, 'arian', 3),
(14, 'Macumbero', 3),
(15, 'gallardo', 3),
(16, 'xawe', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_producto`
--

CREATE TABLE `detalle_producto` (
  `id_detalle_productos` int(11) NOT NULL,
  `id_pedido` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `monto` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalle_producto`
--

INSERT INTO `detalle_producto` (`id_detalle_productos`, `id_pedido`, `id_producto`, `cantidad`, `monto`) VALUES
(14, 9, 3, 3, 1500),
(15, 10, 2, 4, 1200),
(16, 10, 2, 4, 4800);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mesa`
--

CREATE TABLE `mesa` (
  `id_mesa` int(11) NOT NULL,
  `capacidad` int(11) DEFAULT NULL,
  `estado` varchar(10) NOT NULL DEFAULT 'Libre'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mesa`
--

INSERT INTO `mesa` (`id_mesa`, `capacidad`, `estado`) VALUES
(1, 2, 'Libre'),
(2, 2, 'Libre'),
(3, 2, 'Ocupada'),
(4, 4, 'libre'),
(5, 2, 'Libre');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mesero`
--

CREATE TABLE `mesero` (
  `id_mesero` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `dni` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mesero`
--

INSERT INTO `mesero` (`id_mesero`, `nombre`, `dni`) VALUES
(1, 'Juan Pérez', '12345678'),
(2, 'Jeremias', '39797677');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `id_pedido` int(11) NOT NULL,
  `id_mesa` int(11) DEFAULT NULL,
  `id_mesero` int(11) DEFAULT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `fecha_pedido` datetime DEFAULT current_timestamp(),
  `estado` varchar(10) NOT NULL,
  `pagado` varchar(10) NOT NULL,
  `monto` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`id_pedido`, `id_mesa`, `id_mesero`, `id_cliente`, `fecha_pedido`, `estado`, `pagado`, `monto`) VALUES
(1, 1, 2, 7, '2024-11-14 00:12:52', 'activo', 'no', 3600),
(2, 5, 2, 8, '2024-11-14 00:14:18', 'activo', 'no', 4200),
(3, 5, 2, 9, '2024-11-14 00:18:23', 'activo', 'no', 3200),
(4, 5, 2, 10, '2024-11-14 00:35:50', 'activo', 'no', 7500),
(5, 5, 2, 11, '2024-11-14 00:38:24', 'activo', 'no', 3600),
(6, 5, 2, 12, '2024-11-14 00:40:02', 'activo', 'no', 7500),
(7, 3, 2, 13, '2024-11-14 00:44:08', 'activo', 'no', 1800),
(8, 3, 2, 14, '2024-11-14 01:13:25', 'activo', 'no', 4800),
(9, 3, 2, 15, '2024-11-14 01:23:22', 'activo', 'no', 4500),
(10, 3, 2, 16, '2024-11-14 01:25:20', 'activo', 'no', 4800);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id_producto` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `tipo_producto` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id_producto`, `nombre`, `cantidad`, `precio`, `tipo_producto`) VALUES
(1, 'Pizza simple', 10, 500, 'comida'),
(2, 'Pizza Calabresa', 10, 1200, 'comida'),
(3, 'Lomo Completo', 15, 1500, 'comida'),
(4, 'Hamburguesa Doble', 20, 900, 'comida'),
(5, 'Taco de Pollo', 25, 800, 'comida'),
(6, 'Ensalada César', 18, 700, 'comida'),
(7, 'Cerveza Artesanal', 30, 500, 'bebida'),
(8, 'Gaseosa Coca-Cola', 50, 250, 'bebida'),
(9, 'Jugo de Naranja', 40, 300, 'bebida'),
(10, 'Agua Mineral', 60, 200, 'bebida'),
(11, 'Café Espresso', 35, 400, 'bebida');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_cliente`),
  ADD KEY `id_mesa` (`id_mesa`);

--
-- Indices de la tabla `detalle_producto`
--
ALTER TABLE `detalle_producto`
  ADD PRIMARY KEY (`id_detalle_productos`),
  ADD KEY `id_producto` (`id_producto`),
  ADD KEY `id_pedido` (`id_pedido`);

--
-- Indices de la tabla `mesa`
--
ALTER TABLE `mesa`
  ADD PRIMARY KEY (`id_mesa`);

--
-- Indices de la tabla `mesero`
--
ALTER TABLE `mesero`
  ADD PRIMARY KEY (`id_mesero`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id_pedido`),
  ADD KEY `id_mesa` (`id_mesa`),
  ADD KEY `id_mesero` (`id_mesero`),
  ADD KEY `id_cliente` (`id_cliente`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id_producto`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `detalle_producto`
--
ALTER TABLE `detalle_producto`
  MODIFY `id_detalle_productos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `mesa`
--
ALTER TABLE `mesa`
  MODIFY `id_mesa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `mesero`
--
ALTER TABLE `mesero`
  MODIFY `id_mesero` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id_pedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`id_mesa`) REFERENCES `mesa` (`id_mesa`);

--
-- Filtros para la tabla `detalle_producto`
--
ALTER TABLE `detalle_producto`
  ADD CONSTRAINT `detalle_producto_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`),
  ADD CONSTRAINT `detalle_producto_ibfk_3` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id_pedido`);

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`id_mesa`) REFERENCES `mesa` (`id_mesa`),
  ADD CONSTRAINT `pedido_ibfk_2` FOREIGN KEY (`id_mesero`) REFERENCES `mesero` (`id_mesero`),
  ADD CONSTRAINT `pedido_ibfk_3` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
