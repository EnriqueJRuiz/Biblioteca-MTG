-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-04-2017 a las 11:18:28
-- Versión del servidor: 5.7.14
-- Versión de PHP: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `mtg_gestion`
--
DROP DATABASE `mtg_gestion`;
CREATE DATABASE IF NOT EXISTS `mtg_gestion` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `mtg_gestion`;

DELIMITER $$
--
-- Procedimientos
--
DROP PROCEDURE IF EXISTS `ampliacionCartaContenido`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ampliacionCartaContenido` (IN `pcodigo` INT(11))  BEGIN
	SELECT a.codigo as codigo, a.nombre as nombre, a.siglas as siglas, a.icono as icono, a.imagen as imagen, a.flanzamiento as flanzamiento, a.principal as principal, a.especial as especial, a.basica as basica 
	,am.codigo as principalcodigo, am.nombre as principalnombre
    ,c.nombre as cartanombre, c.codigo as cartacodigo, c.rareza as cartarareza, c.numero as cartanumero
    ,co.codigo as codicolor, co.nombre as nomcolor, co.icono as iconcolor
FROM ampliacion as a 
	INNER JOIN ampliacion as am ON a.principal=am.codigo
    LEFT JOIN carta as c ON a.codigo = c.ampliacion_codigo
    LEFT JOIN combinacion_colores as cc on c.codigo= cc.carta_codigo
	LEFT JOIN color as co on cc.color_codigo = co.codigo
WHERE a.codigo = pcodigo ORDER BY cartanumero;
END$$

DROP PROCEDURE IF EXISTS `ampliacionCreate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ampliacionCreate` (IN `pnombre` VARCHAR(100), IN `psiglas` VARCHAR(3), IN `picono` VARCHAR(250), IN `pimagen` VARCHAR(250), IN `pflanzamiento` DATE, IN `pprincipal` INT(11), IN `pespecial` BOOLEAN, IN `pbasica` BOOLEAN, OUT `pcodigo` INT(11))  BEGIN
	INSERT INTO ampliacion (nombre,siglas,icono,imagen,flanzamiento,principal,especial,basica) VALUE (pnombre,UPPER(psiglas),picono,pimagen,pflanzamiento,pprincipal,pespecial,pbasica);
    
    SET pcodigo = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS `ampliacionDeleteF`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ampliacionDeleteF` (IN `pcodigo` INT(11))  BEGIN
	DELETE FROM ampliacion WHERE codigo = pcodigo;
END$$

DROP PROCEDURE IF EXISTS `ampliaciongetAll`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ampliaciongetAll` ()  BEGIN
SELECT a.codigo as codigo, a.nombre as nombre, a.siglas as siglas, a.icono as icono, a.imagen as imagen, a.flanzamiento as flanzamiento, a.principal as principal, a.especial as especial, a.basica as basica 
	,am.codigo as principalcodigo, am.nombre as principalnombre
FROM ampliacion as a 
	INNER JOIN ampliacion as am ON a.principal=am.codigo
WHERE a.codigo> 0
ORDER BY flanzamiento;
END$$

DROP PROCEDURE IF EXISTS `ampliaciongetByBloque`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ampliaciongetByBloque` ()  BEGIN
SELECT a.codigo as codigo, a.nombre as nombre, a.siglas as siglas, a.icono as icono, a.imagen as imagen, a.flanzamiento as flanzamiento, a.principal as principal, a.especial as especial, a.basica as basica 
	,am.codigo as principalcodigo, am.nombre as principalnombre
FROM ampliacion as a 
	INNER JOIN ampliacion as am ON a.principal=am.codigo
ORDER BY principalnombre;
END$$

DROP PROCEDURE IF EXISTS `ampliaciongetById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ampliaciongetById` (IN `pcodigo` INT(11))  BEGIN
	SELECT a.codigo as codigo, a.nombre as nombre, a.siglas as siglas, a.icono as icono, a.imagen as imagen, a.flanzamiento as flanzamiento, a.principal as principal, a.especial as especial, a.basica as basica 
	,am.codigo as principalcodigo, am.nombre as principalnombre
FROM ampliacion as a 
	INNER JOIN ampliacion as am ON a.principal=am.codigo
WHERE a.codigo=pcodigo;
END$$

DROP PROCEDURE IF EXISTS `ampliaciongetByNombre`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ampliaciongetByNombre` ()  BEGIN
SELECT a.codigo as codigo, a.nombre as nombre, a.siglas as siglas, a.icono as icono, a.imagen as imagen, a.flanzamiento as flanzamiento, a.principal as principal, a.especial as especial, a.basica as basica 
	,am.codigo as principalcodigo, am.nombre as principalnombre
FROM ampliacion as a 
	INNER JOIN ampliacion as am ON a.principal=am.codigo
ORDER BY nombre;
END$$

DROP PROCEDURE IF EXISTS `ampliaciongetBySiglas`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ampliaciongetBySiglas` ()  BEGIN
SELECT a.codigo as codigo, a.nombre as nombre, a.siglas as siglas, a.icono as icono, a.imagen as imagen, a.flanzamiento as flanzamiento, a.principal as principal, a.especial as especial, a.basica as basica 
	,am.codigo as principalcodigo, am.nombre as principalnombre
FROM ampliacion as a 
	INNER JOIN ampliacion as am ON a.principal=am.codigo
ORDER BY siglas;
END$$

DROP PROCEDURE IF EXISTS `ampliaciongetPrincial`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ampliaciongetPrincial` ()  BEGIN
SELECT a.codigo as codigo, a.nombre as nombre, a.siglas as siglas, a.icono as icono, a.imagen as imagen, a.flanzamiento as flanzamiento, a.principal as principal, a.especial as especial, a.basica as basica 
	,am.codigo as principalcodigo, am.nombre as principalnombre
FROM ampliacion as a 
	INNER JOIN ampliacion as am ON a.principal=am.codigo
WHERE a.principal = a.codigo
ORDER BY flanzamiento;
END$$

DROP PROCEDURE IF EXISTS `ampliacionUpdate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ampliacionUpdate` (IN `pnombre` VARCHAR(100), IN `pcodigo` INT(3), IN `pbasica` INT(1), IN `psiglas` VARCHAR(100), IN `picono` VARCHAR(250), IN `pimagen` VARCHAR(250), IN `pflanzamiento` DATE, IN `pprincipal` INT(11), IN `pespecial` INT(1))  BEGIN
	UPDATE ampliacion SET nombre = pnombre, basica = pbasica, siglas = UPPER(psiglas), icono = picono, imagen = pimagen, flanzamiento = pflanzamiento, principal = pprincipal, especial = pespecial
    
    WHERE codigo=pcodigo;
END$$

DROP PROCEDURE IF EXISTS `ampliacionupdateprincipal`$$
CREATE DEFINER=`root`@`127.0.0.1` PROCEDURE `ampliacionupdateprincipal` (IN `pcodigo` INT(11))  BEGIN
	UPDATE ampliacion SET principal = pcodigo WHERE pcodigo = codigo;
END$$

DROP PROCEDURE IF EXISTS `cartaByAmpliacion`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `cartaByAmpliacion` (IN `pampliacion` INT(11))  BEGIN
 SELECT c.codigo as codigo, c.nombre as nombre, c.texto as texto, c.rareza as rareza, c.coste as coste,
 c.subtipo as subtipo, c.tipo as tipo, c.supertipo as supertipo, c.numero as numero, c.imagen as imagen,
 c.ampliacion_codigo as ampliacion_codigo, a.nombre as ampliacion, co.codigo as codicolor, co.nombre as nomcolor, co.icono as iconcolor
 
 FROM carta as c
	LEFT JOIN  ampliacion as a on c.ampliacion_codigo = a.codigo
	LEFT JOIN combinacion_colores as cc on c.codigo= cc.carta_codigo
	LEFT JOIN color as co on cc.color_codigo = co.codigo 
WHERE c.ampliacion_codigo = pampliacion
ORDER BY a.nombre;
END$$

DROP PROCEDURE IF EXISTS `cartaColorCreate`$$
CREATE DEFINER=`root`@`127.0.0.1` PROCEDURE `cartaColorCreate` (IN `pcarta_codigo` INT(11), IN `pcolor_codigo` INT(11))  BEGIN
	INSERT INTO combinacion_colores(carta_codigo,color_codigo) VALUE  (pcarta_codigo,pcolor_codigo);
END$$

DROP PROCEDURE IF EXISTS `cartaColorDeleteF`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `cartaColorDeleteF` (IN `pcodigo` INT(11))  BEGIN
	DELETE FROM combinacion_colores WHERE carta_codigo = pcodigo;
END$$

DROP PROCEDURE IF EXISTS `cartaCreate`$$
CREATE DEFINER=`root`@`127.0.0.1` PROCEDURE `cartaCreate` (IN `pnombre` VARCHAR(100), IN `pnumero` INT(3), IN `pcosteDeMana` VARCHAR(20), IN `pimagen` VARCHAR(250), IN `ptexto` TEXT, IN `prareza` VARCHAR(100), IN `psubtipo` VARCHAR(100), IN `psupertipo` VARCHAR(100), IN `ptipo` VARCHAR(100), IN `pcodigo_ampliacion` INT(11), OUT `pcodigo` INT)  BEGIN
	INSERT INTO carta(nombre,texto,ampliacion_codigo,rareza,coste,supertipo,tipo,subtipo,numero,imagen) VALUE  (LOWER(pnombre),LOWER(ptexto),pcodigo_ampliacion,LOWER(prareza),LOWER(pcosteDeMana),LOWER(psupertipo),LOWER(ptipo),LOWER(psubtipo),pnumero,LOWER(pimagen));
    
    SET pcodigo = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS `cartaDeleteF`$$
CREATE DEFINER=`root`@`127.0.0.1` PROCEDURE `cartaDeleteF` (IN `pcodigo` INT(11))  BEGIN
	DELETE FROM carta WHERE codigo = pcodigo;
END$$

DROP PROCEDURE IF EXISTS `cartagetAll`$$
CREATE DEFINER=`root`@`127.0.0.1` PROCEDURE `cartagetAll` ()  BEGIN
 SELECT c.codigo as codigo, c.nombre as nombre, c.texto as texto, c.rareza as rareza, c.coste as coste,
 c.subtipo as subtipo, c.tipo as tipo, c.supertipo as supertipo, c.numero as numero, c.imagen as imagen,
 c.ampliacion_codigo as ampliacion_codigo, a.nombre as ampliacion
 
 FROM carta as c
LEFT JOIN  ampliacion as a on c.ampliacion_codigo = a.codigo
LEFT JOIN combinacion_colores as cc on c.codigo= cc.carta_codigo;
END$$

DROP PROCEDURE IF EXISTS `cartagetAllOnly`$$
CREATE DEFINER=`root`@`127.0.0.1` PROCEDURE `cartagetAllOnly` ()  BEGIN
 SELECT c.codigo as codigo, c.nombre as nombre, c.texto as texto, c.rareza as rareza, c.coste as coste,
 c.subtipo as subtipo, c.tipo as tipo, c.supertipo as supertipo, c.numero as numero, c.imagen as imagen,
 c.ampliacion_codigo as ampliacion_codigo, a.nombre as ampliacion, co.codigo as codicolor, co.nombre as nomcolor, co.icono as iconcolor
 
 FROM carta as c
LEFT JOIN  ampliacion as a on c.ampliacion_codigo = a.codigo
LEFT JOIN combinacion_colores as cc on c.codigo= cc.carta_codigo
LEFT JOIN color as co on cc.color_codigo = co.codigo 
ORDER BY a.nombre;
END$$

DROP PROCEDURE IF EXISTS `cartagetByAmpliacion`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `cartagetByAmpliacion` (IN `pampliacion` INT(11))  BEGIN
 SELECT c.codigo as codigo, c.nombre as nombre, c.texto as texto, c.rareza as rareza, c.coste as coste,
 c.subtipo as subtipo, c.tipo as tipo, c.supertipo as supertipo, c.numero as numero, c.imagen as imagen,
 c.ampliacion_codigo as ampliacion_codigo, a.nombre as ampliacion, co.codigo as codicolor, co.nombre as nomcolor, co.icono as iconcolor
 
 FROM carta as c
	LEFT JOIN  ampliacion as a on c.ampliacion_codigo = a.codigo
	LEFT JOIN combinacion_colores as cc on c.codigo= cc.carta_codigo
	LEFT JOIN color as co on cc.color_codigo = co.codigo 
WHERE c.ampliacion_codigo = pampliacion
ORDER BY a.nombre;
END$$

DROP PROCEDURE IF EXISTS `cartagetById`$$
CREATE DEFINER=`root`@`127.0.0.1` PROCEDURE `cartagetById` (IN `pcodigo` INT(11))  BEGIN

 SELECT c.codigo as codigo, c.nombre as nombre, c.texto as texto, c.rareza as rareza, c.coste as coste,
 c.subtipo as subtipo, c.tipo as tipo, c.supertipo as supertipo, c.numero as numero, c.imagen as imagen,
 c.ampliacion_codigo as ampliacion_codigo, a.nombre as ampliacion, co.codigo as codicolor, co.nombre as nomcolor, co.icono as iconcolor
 
 FROM carta as c
	LEFT JOIN  ampliacion as a on c.ampliacion_codigo = a.codigo
	LEFT JOIN combinacion_colores as cc on c.codigo= cc.carta_codigo
	LEFT JOIN color as co on cc.color_codigo = co.codigo 
    
    WHERE c.codigo = pcodigo;

END$$

DROP PROCEDURE IF EXISTS `cartaUpdate`$$
CREATE DEFINER=`root`@`127.0.0.1` PROCEDURE `cartaUpdate` (IN `pnombre` VARCHAR(100), IN `pnumero` INT(3), IN `pcosteDeMana` VARCHAR(20), IN `pimagen` VARCHAR(250), IN `ptexto` TEXT, IN `prareza` VARCHAR(100), IN `psubtipo` VARCHAR(100), IN `psupertipo` VARCHAR(100), IN `ptipo` VARCHAR(100), IN `pcodigo_ampliacion` INT(11), IN `pcodigo` INT(11))  BEGIN

	UPDATE carta SET  nombre = LOWER(pnombre),texto =LOWER(ptexto),ampliacion_codigo = pcodigo_ampliacion,rareza = LOWER(prareza), coste=LOWER(pcosteDeMana), supertipo=LOWER(psupertipo),tipo=LOWER(ptipo),subtipo=LOWER(psubtipo),numero=pnumero,imagen=LOWER(pimagen) 
    
    WHERE codigo=pcodigo;

END$$

DROP PROCEDURE IF EXISTS `colorCreate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `colorCreate` (IN `pnombre` VARCHAR(100), IN `picono` VARCHAR(250), OUT `pcodigo` INT(11))  BEGIN
	INSERT INTO color (nombre,icono) VALUE (LOWER(pnombre),picono);
    
	SET pcodigo = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS `colorDeleteF`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `colorDeleteF` (IN `pcodigo` INT(11))  BEGIN
	DELETE FROM color WHERE codigo = pcodigo;
END$$

DROP PROCEDURE IF EXISTS `colorgetAll`$$
CREATE DEFINER=`root`@`127.0.0.1` PROCEDURE `colorgetAll` ()  BEGIN
	SELECT co.codigo as codigo, co.nombre as nombre, co.icono as icono FROM color as co;
END$$

DROP PROCEDURE IF EXISTS `colorgetBycarta`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `colorgetBycarta` (IN `pcodigo` INT(11))  BEGIN
	SELECT cc.codigo as codigo FROM combinacion_colores as cc WHERE color_codigo=pcodigo;
END$$

DROP PROCEDURE IF EXISTS `colorgetByIcono`$$
CREATE DEFINER=`root`@`127.0.0.1` PROCEDURE `colorgetByIcono` (IN `picono` VARCHAR(250))  BEGIN
	SELECT co.codigo as codigo, co.nombre as nombre, co.icono as icono FROM color as co WHERE co.icono=LOWER(picono);
END$$

DROP PROCEDURE IF EXISTS `colorgetById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `colorgetById` (IN `pcodigo` INT(11))  BEGIN
	SELECT co.codigo as codigo, co.nombre as nombre, co.icono as icono FROM color as co WHERE codigo=pcodigo;
END$$

DROP PROCEDURE IF EXISTS `colorgetByNombre`$$
CREATE DEFINER=`root`@`127.0.0.1` PROCEDURE `colorgetByNombre` (IN `pnombre` VARCHAR(45))  BEGIN
	SELECT co.codigo as codigo, co.nombre as nombre, co.icono as icono FROM color as co WHERE co.nombre=LOWER(pnombre);
END$$

DROP PROCEDURE IF EXISTS `colorUpdate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `colorUpdate` (IN `pnombre` VARCHAR(100), IN `pcodigo` INT(11), IN `picono` VARCHAR(100))  BEGIN
	UPDATE color SET nombre = pnombre, icono = picono
    
    WHERE codigo=pcodigo;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ampliacion`
--

DROP TABLE IF EXISTS `ampliacion`;
CREATE TABLE `ampliacion` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(100) CHARACTER SET latin1 NOT NULL,
  `siglas` varchar(3) CHARACTER SET latin1 NOT NULL,
  `icono` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `imagen` varchar(250) CHARACTER SET latin1 DEFAULT NULL,
  `flanzamiento` date NOT NULL,
  `principal` int(11) DEFAULT NULL,
  `especial` tinyint(4) DEFAULT NULL,
  `basica` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `ampliacion`
--

INSERT INTO `ampliacion` (`codigo`, `nombre`, `siglas`, `icono`, `imagen`, `flanzamiento`, `principal`, `especial`, `basica`) VALUES
(0, 'sin bloque', 'SN', '', '', '1970-01-01', 0, 0, 0),
(1, 'Fourth Edition', '4ED', '', 'EN_4ED_SetLogo.png', '1995-05-03', 1, 0, 1),
(2, 'Eighth Edition', '8ED', '', 'EN_8ED_SetLogo.png', '2003-07-28', 2, 0, 1),
(3, 'Limited Edition Alpha', 'LEA', NULL, NULL, '1993-08-03', 3, 0, 1),
(4, 'Limited Edition Beta', 'LEB', NULL, NULL, '1993-10-15', 4, 0, 1),
(5, 'Aether Revolt', 'AER', NULL, NULL, '2017-01-20', 6, 0, 0),
(6, 'Kaladesh', 'KLD', NULL, NULL, '2016-09-24', 6, 0, 0),
(7, 'Eldritch Moon', 'EMN', NULL, NULL, '2016-07-16', 8, 0, 0),
(8, 'Shadows over Innistrad', 'SOI', NULL, NULL, '2016-04-02', 8, 0, 0),
(9, 'Battle for Zendikar', 'BFZ', NULL, NULL, '2015-09-26', 9, 0, 0),
(10, 'Oath of the Gatewatch', 'OGW', NULL, NULL, '2016-01-22', 9, 0, 0),
(11, 'Amonkhet', 'AKH', NULL, NULL, '2017-04-22', 11, 0, 0),
(12, 'Unlimited Edition', '2ED', NULL, NULL, '1993-12-03', 12, 0, 1),
(13, 'Revised Edition', '3ED', '', 'EN_3ED_SetLogo.png', '1994-04-03', 13, 0, 1),
(14, 'Fifth Edition', '5ED', '', 'EN_5ED_SetLogo.png', '1997-03-24', 14, 0, 1),
(15, 'Classic Sixth Edition', '6ED', '', 'EN_6ED_SetLogo.png', '1999-04-28', 15, 0, 1),
(16, 'Seventh Edition', '7ED', '', 'EN_7ED_SetLogo.png', '2001-04-11', 16, 0, 1),
(17, 'Ninth Edition', '9ED', '', 'EN_9ED_SetLogo.png', '2005-07-29', 17, 0, 1),
(18, 'Tenth Edition', '10E', '', 'EN_10E_SetLogo.png', '2007-07-13', 18, 0, 1),
(19, 'Magic 2010', 'M10', '', 'EN_M10_SetLogo.png', '2009-07-17', 19, 0, 1),
(20, 'Magic 2011', 'M11', '', 'EN_M11_SetLogo.png', '2010-07-16', 20, 0, 1),
(21, 'Magic 2012', 'M12', '', 'EN_M12_SetLogo.png', '2011-07-15', 21, 0, 1),
(22, 'Magic 2013', 'M13', NULL, NULL, '2012-07-13', 22, 0, 1),
(23, 'Magic 2014 Core Set', 'M14', NULL, NULL, '2013-07-19', 23, 0, 1),
(24, 'Magic 2015 Core Set', 'M15', NULL, NULL, '2014-07-18', 24, 0, 1),
(25, 'Magic Origins', 'ORI', NULL, NULL, '2015-07-17', 25, 0, 1),
(26, 'Arabian Nights', 'ARN', '', 'EN_ARN_SetLogo.png', '1993-12-03', 26, 0, 0),
(27, 'Antiquities', 'ATQ', '', 'EN_ATQ_SetLogo.png', '1994-03-03', 27, 0, 0),
(28, 'Legends', 'LEG', '', 'EN_LED_SetLogo.png', '1994-06-03', 28, 0, 0),
(29, 'The Dark', 'DRK', '', 'EN_DRK_SetLogo.png', '1994-08-03', 29, 0, 0),
(30, 'Fallen Empires', 'FEM', '', 'EN_FEM_SetLogo.png', '1994-11-03', 30, 0, 0),
(31, 'Homelands', 'HML', '', 'EN_HML_SetLogo.png', '1995-10-14', 31, 0, 0),
(32, 'Ice Age', 'ICE', '', 'EN_ICE_SetLogo.png', '1995-06-03', 32, 0, 0),
(33, 'Alliance', 'ALL', '', 'EN_ALL_SetLogo.png', '1996-06-10', 32, 0, 0),
(34, 'Coldsnap', 'CSP', '', 'EN_CSP_SetLogo.png', '2006-07-21', 32, 0, 0),
(35, 'Mirage', 'MIR', '', 'EN_MIR_SetLogo.png', '1996-09-21', 35, 0, 0),
(36, 'Visiones', 'VIS', '', 'EN_VIS_SetLogo.png', '1997-02-03', 35, 0, 0),
(37, 'Weatherlight', 'WTH', '', 'EN_WTH_SetLogo.png', '1997-06-09', 35, 0, 0),
(38, 'Tempest', 'TMP', '', 'EN_TEM_SetLogo.png', '1997-10-13', 38, 0, 0),
(39, 'Stronghold', 'STH', '', 'EN_STH_SetLogo_0.png', '1998-02-21', 38, 0, 0),
(40, 'Exodus', 'EXO', '', 'EN_EXO_SetLogo_0.png', '1998-06-06', 38, 0, 0),
(41, 'Urza\'s Saga', 'USG', '', 'EN_USG_SetLogo.png', '1998-10-12', 41, 0, 0),
(42, 'Urza\'s Legacy', 'ULG', '', 'EN_ULG_SetLogo.png', '1999-02-15', 41, 0, 0),
(43, 'Urza\'s Destiny', 'UDS', '', 'EN_UDS_SetLogo.png', '1999-06-07', 41, 0, 0),
(44, 'Mercadian Masques', 'MMQ', '', 'EN_MMQ_SetLogo.png', '1999-09-25', 44, 0, 0),
(45, 'Nemesis', 'NMS', '', 'EN_NEM_SetLogo.png', '2000-02-14', 44, 0, 0),
(46, 'Prophecy', 'PCY', '', 'EN_PCY_SetLogo.png', '2000-06-05', 44, 0, 0),
(47, 'Invasion', 'INV', '', 'EN_INV_SetLogo.png', '2000-10-02', 47, 0, 0),
(48, 'Planeshift', 'PLS', '', 'EN_PLS_SetLogo.png', '2001-02-05', 47, 0, 0),
(49, 'Apocalypse', 'APC', '', 'EN_APC_SetLogo.png', '2001-06-04', 47, 0, 0),
(50, 'Odyssey', 'ODY', '', 'EN_ODY_SetLogo.png', '2001-10-01', 50, 0, 0),
(51, 'Torment', 'TOR', '', 'EN_TOR_SetLogo.png', '2002-02-04', 50, 0, 0),
(52, 'Judgment', 'JUD', '', 'EN_JUD_SetLogo.png', '2002-05-27', 50, 0, 0),
(53, 'Onslaught', 'ONS', '', 'EN_ONS_SetLogo.png', '2002-10-07', 53, 0, 0),
(54, 'Legions', 'LGN', '', 'EN_LGN_SetLogo.png', '2003-02-03', 53, 0, 0),
(55, 'Scourge', 'SCG', '', 'EN_SCG_SetLogo.png', '2003-05-26', 53, 0, 0),
(56, 'Mirrodin', 'MRD', '', 'EN_MRD_SetLogo.png', '2003-10-03', 56, 0, 0),
(57, 'Darksteel', 'DTS', '', 'EN_DST_SetLogo.png', '2004-02-06', 56, 0, 0),
(58, 'Fifth Dawn', '5DN', '', 'EN_5DN_SetLogo.png', '2004-06-04', 56, 0, 0),
(59, 'Champions of Kamigawa', 'CHK', '', 'EN_CHK_SetLogo.png', '2004-10-01', 59, 0, 0),
(60, 'Betrayers of Kamigawa', 'BOK', '', 'EN_BOK_SetLogo.png', '2005-02-04', 59, 0, 0),
(61, 'Saviors of Kamigawa', 'SOK', '', 'EN_SOK_SetLogo.png', '2005-06-03', 59, 0, 0),
(62, 'Ravnica: City of Guilds', 'RAV', '', 'EN_RAV_SetLogo.png', '2005-10-07', 62, 0, 0),
(63, 'Guildpact', 'GPT', '', 'EN_GPT_SetLogo.png', '2006-02-03', 62, 0, 0),
(64, 'Dissension', 'DIS', '', 'EN_DIS_SetLogo.png', '2006-05-05', 62, 0, 0),
(65, 'Time Spiral', 'TSP', '', 'EN_TSP_SetLogo.png', '2006-10-06', 65, 0, 0),
(66, 'Planar Chaos', 'PLC', '', 'EN_PLC_SetLogo.png', '2007-02-02', 65, 0, 0),
(67, 'Future Sight', 'FUT', '', 'EN_FUT_SetLogo.png', '2007-05-04', 65, 0, 0),
(68, 'Lorwyn', 'LRW', '', 'EN_LRW_SetLogo.png', '2007-10-12', 68, 0, 0),
(69, 'Morningtide', 'MOR', '', 'EN_MOR_SetLogo_0.png', '2008-02-01', 68, 0, 0),
(70, 'Shadowmoor', 'SHM', '', 'EN_SHM_SetLogo_0.png', '2008-05-02', 70, 0, 0),
(71, 'Eventide', 'EVE', '', 'EN_EVE_SetLogo_1.png', '2008-07-25', 70, 0, 0),
(80, 'Shards of Alara', 'ALA', '', 'EN_ALA_SetLogo.png', '2008-10-03', 80, 0, 0),
(81, 'Conflux', 'CON', '', 'EN_CON_SetLogo.png', '2009-02-06', 80, 0, 0),
(82, 'Alara Reborn', 'ARB', '', 'EN_ARB_SetLogo.png', '2009-04-30', 80, 0, 0),
(83, 'Zendikar', 'ZEN', '', 'EN_ZEN_SetLogo.png', '2009-10-02', 83, 0, 0),
(84, 'Worldwake', 'WWK', '', 'EN_WWK_SetLogo.png', '2010-02-05', 83, 0, 0),
(85, 'Rise of the Eldrazi', 'ROE', '', 'EN_ROE_SetLogo.png', '2010-04-23', 83, 0, 0),
(86, 'Scars of Mirrodin', 'SOM', '', 'EN_SOM_SetLogo.png', '2010-10-01', 86, 0, 0),
(87, 'Mirrodin Besieged', 'MBS', '', 'EN_MBS_SetLogo.png', '2011-02-04', 86, 0, 0),
(88, 'New Phyrexia', 'NPH', '', 'EN_NPH_SetLogo.png', '2011-05-13', 86, 0, 0),
(89, 'Innistrad', 'ISD', NULL, NULL, '2011-09-30', 89, 0, 0),
(90, 'Dark Ascension', 'DKA', NULL, NULL, '2012-02-03', 89, 0, 0),
(91, 'Avacyn Restored', 'AVR', NULL, NULL, '2012-05-04', 89, 0, 0),
(92, 'Return to Ravnica', 'RTR', NULL, NULL, '2012-10-05', 92, 0, 0),
(93, 'Gatecrash', 'GTC', NULL, NULL, '2013-02-01', 92, 0, 0),
(94, 'Dragon\'s Maze', 'DGM', NULL, NULL, '2013-05-03', 92, 0, 0),
(95, 'Theros', 'THS', NULL, NULL, '2013-09-27', 95, 0, 0),
(96, 'Born of the Gods', 'BNG', NULL, NULL, '2014-02-07', 95, 0, 0),
(97, 'Journey into Nyx', 'JOU', NULL, NULL, '2014-05-02', 95, 0, 0),
(98, 'Khans of Tarkir', 'KTK', NULL, NULL, '2014-09-26', 98, 0, 0),
(99, 'Fate Reforged', 'FRF', NULL, NULL, '2015-01-23', 98, 0, 0),
(100, 'Dragons of Tarkir', 'DTK', NULL, NULL, '2015-03-27', 98, 0, 0),
(101, 'Scars of Mirrodin333', '123', '', '', '2017-04-18', 101, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carta`
--

DROP TABLE IF EXISTS `carta`;
CREATE TABLE `carta` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `texto` text COLLATE utf8_unicode_ci,
  `ampliacion_codigo` int(11) NOT NULL,
  `rareza` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `coste` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `supertipo` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tipo` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `subtipo` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `numero` int(3) DEFAULT NULL,
  `imagen` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `carta`
--

INSERT INTO `carta` (`codigo`, `nombre`, `texto`, `ampliacion_codigo`, `rareza`, `coste`, `supertipo`, `tipo`, `subtipo`, `numero`, `imagen`) VALUES
(1, 'black lotus', '{t}, sacrifice black lotus: add three mana of any one color to your mana pool.', 3, 'rara', '0', '', 'artefacto', '', 1, 'black_lotus.jpg'),
(48, 'mox opal', 'metalcraft — {t}: add one mana of any color to your mana pool. activate this ability only if you control three or more artifacts.', 86, 'mitica', '0', '', 'artefacto', '', 179, 'mox_opal.jpg'),
(49, 'aaa123456789', '', 3, '', '3', '', '', '', 3, ''),
(54, 'aa', '', 3, 'rara', '', '', 'criatura', '', 0, ''),
(55, 'ice age', '', 101, 's/n', '', '', 's/n', '', 0, ''),
(56, 'asdasd', '', 3, 's/n', '', '', 's/n', '', 0, '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `color`
--

DROP TABLE IF EXISTS `color`;
CREATE TABLE `color` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `icono` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `color`
--

INSERT INTO `color` (`codigo`, `nombre`, `icono`) VALUES
(1, 'Blanco', 'Mana_W.png'),
(2, 'Azul', 'Mana_U.png'),
(3, 'Negro', 'Mana_B.png'),
(4, 'Rojo', 'Mana_R.png'),
(5, 'Verde', 'Mana_G.png'),
(6, 'Incoloro', 'Mana_C.png'),
(9, 'enrique javier', 'EN_NPH_SetLogo.png');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `combinacion_colores`
--

DROP TABLE IF EXISTS `combinacion_colores`;
CREATE TABLE `combinacion_colores` (
  `codigo` int(11) NOT NULL,
  `color_codigo` int(11) NOT NULL,
  `carta_codigo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `combinacion_colores`
--

INSERT INTO `combinacion_colores` (`codigo`, `color_codigo`, `carta_codigo`) VALUES
(74, 1, 49),
(75, 2, 49),
(76, 3, 49),
(77, 4, 49),
(78, 5, 49),
(83, 6, 48),
(84, 6, 1),
(85, 2, 54),
(86, 3, 54),
(87, 9, 55),
(88, 9, 56);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ampliacion`
--
ALTER TABLE `ampliacion`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `fk_loksea_idx` (`principal`);

--
-- Indices de la tabla `carta`
--
ALTER TABLE `carta`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `fk_carta_ampliacion_codigo_idx` (`ampliacion_codigo`);

--
-- Indices de la tabla `color`
--
ALTER TABLE `color`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `combinacion_colores`
--
ALTER TABLE `combinacion_colores`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `fk_combinacion_colores_carta_codigo_idx` (`carta_codigo`),
  ADD KEY `fk_combinacion_colores_color_codigo_idx` (`color_codigo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ampliacion`
--
ALTER TABLE `ampliacion`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=103;
--
-- AUTO_INCREMENT de la tabla `carta`
--
ALTER TABLE `carta`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;
--
-- AUTO_INCREMENT de la tabla `color`
--
ALTER TABLE `color`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT de la tabla `combinacion_colores`
--
ALTER TABLE `combinacion_colores`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=89;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `ampliacion`
--
ALTER TABLE `ampliacion`
  ADD CONSTRAINT `fk_pricipal_ampliacion_ampliacion` FOREIGN KEY (`principal`) REFERENCES `ampliacion` (`codigo`) ON DELETE CASCADE ON UPDATE SET NULL;

--
-- Filtros para la tabla `carta`
--
ALTER TABLE `carta`
  ADD CONSTRAINT `fk_color_ampliacion_codigo` FOREIGN KEY (`ampliacion_codigo`) REFERENCES `ampliacion` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `combinacion_colores`
--
ALTER TABLE `combinacion_colores`
  ADD CONSTRAINT `fk_combinacion_colores_carta_codigo` FOREIGN KEY (`carta_codigo`) REFERENCES `carta` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_combinacion_colores_color_codigo` FOREIGN KEY (`color_codigo`) REFERENCES `color` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
