CREATE DATABASE  IF NOT EXISTS `clinicaiesit` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `clinicaiesit`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: clinicaiesit
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `antecedentes`
--

DROP TABLE IF EXISTS `antecedentes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `antecedentes` (
  `id_antecedentes` int NOT NULL AUTO_INCREMENT,
  `descripcion_antecedentes` varchar(100) NOT NULL,
  `tipo_antecedentes` enum('PATOLOGICOS HEREDOFAMILIARES','PERSONALES PATOLOGICOS') NOT NULL,
  `respuesta` varchar(150) NOT NULL,
  `detalle` varchar(250) DEFAULT NULL,
  `CURP_fk_antecedentes` varchar(18) NOT NULL,
  PRIMARY KEY (`id_antecedentes`),
  KEY `CURP_idx` (`CURP_fk_antecedentes`),
  CONSTRAINT `CURP_fk_antecedentes` FOREIGN KEY (`CURP_fk_antecedentes`) REFERENCES `paciente` (`CURP`)
) ENGINE=InnoDB AUTO_INCREMENT=444 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `antecedentes`
--

LOCK TABLES `antecedentes` WRITE;
/*!40000 ALTER TABLE `antecedentes` DISABLE KEYS */;
/*!40000 ALTER TABLE `antecedentes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `antecedentes_no_patologicos`
--

DROP TABLE IF EXISTS `antecedentes_no_patologicos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `antecedentes_no_patologicos` (
  `id_antecedentes_no_patologicos` int NOT NULL AUTO_INCREMENT,
  `CURP_fk_no_patologicos` varchar(18) NOT NULL,
  `frecuencia_lavado_dientes` varchar(100) NOT NULL,
  `usa_auxiliares_higiene` varchar(10) DEFAULT NULL,
  `tipos_auxiliares_higiene` varchar(100) DEFAULT NULL,
  `golosinas` varchar(10) DEFAULT NULL,
  `grupo_sanguineo` varchar(5) NOT NULL,
  `factor_rh` varchar(5) NOT NULL,
  `cartilla_vacunacion` varchar(10) NOT NULL,
  `esquema_completo` varchar(10) NOT NULL,
  `vacunas_faltantes` varchar(100) DEFAULT NULL,
  `antecedentes_alergicos` varchar(10) NOT NULL,
  `cual_alergicos` varchar(100) DEFAULT NULL,
  `antibioticos` varchar(100) DEFAULT NULL,
  `analgesicos` varchar(100) DEFAULT NULL,
  `anestesicos` varchar(100) DEFAULT NULL,
  `alimentos` varchar(100) DEFAULT NULL,
  `otras_alergias` varchar(100) DEFAULT NULL,
  `tiene_adicciones` varchar(10) NOT NULL,
  `tabaco` varchar(100) DEFAULT NULL,
  `alcohol` varchar(100) DEFAULT NULL,
  `otras_adicciones` varchar(100) DEFAULT NULL,
  `ha_sido_hospitalizado` varchar(10) NOT NULL,
  `fecha_hospitalizacion` date DEFAULT NULL,
  `motivo_hospitalizacion` varchar(150) DEFAULT NULL,
  `padecimiento_actual` varchar(100) DEFAULT NULL,
  `ha_sido_anestesiado` varchar(10) NOT NULL,
  `ha_recibido_transfusion` varchar(10) NOT NULL,
  `ha_recibido_perforaciones` varchar(10) NOT NULL,
  `consume_medicamento` varchar(10) NOT NULL,
  `embarazo` varchar(10) NOT NULL,
  `discapacidad` varchar(10) NOT NULL,
  `tiene_intervenciones` varchar(10) NOT NULL,
  `parte_cuerpo` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id_antecedentes_no_patologicos`),
  UNIQUE KEY `CURP_fk_no_patologicos_UNIQUE` (`CURP_fk_no_patologicos`),
  CONSTRAINT `CURP_fk_no_patologicos` FOREIGN KEY (`CURP_fk_no_patologicos`) REFERENCES `paciente` (`CURP`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `antecedentes_no_patologicos`
--

LOCK TABLES `antecedentes_no_patologicos` WRITE;
/*!40000 ALTER TABLE `antecedentes_no_patologicos` DISABLE KEYS */;
/*!40000 ALTER TABLE `antecedentes_no_patologicos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `control_evolucion`
--

DROP TABLE IF EXISTS `control_evolucion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `control_evolucion` (
  `id_control_evolucion` int NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `comentario_control` varchar(500) NOT NULL,
  `CURP_fk_control` varchar(18) NOT NULL,
  PRIMARY KEY (`id_control_evolucion`),
  KEY `CURP_fk_control_idx` (`CURP_fk_control`),
  CONSTRAINT `CURP_fk_control` FOREIGN KEY (`CURP_fk_control`) REFERENCES `paciente` (`CURP`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `control_evolucion`
--

LOCK TABLES `control_evolucion` WRITE;
/*!40000 ALTER TABLE `control_evolucion` DISABLE KEYS */;
/*!40000 ALTER TABLE `control_evolucion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diagnostico_tratamiento`
--

DROP TABLE IF EXISTS `diagnostico_tratamiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diagnostico_tratamiento` (
  `id_diagnostico` int NOT NULL AUTO_INCREMENT,
  `interpretacion_Rx` varchar(500) NOT NULL,
  `diagnostico` varchar(500) NOT NULL,
  `resumen_tratamiento` varchar(500) NOT NULL,
  `CURP_fk_diagnostico` varchar(18) NOT NULL,
  PRIMARY KEY (`id_diagnostico`),
  KEY `CURP_fk_diagnostico_idx` (`CURP_fk_diagnostico`),
  CONSTRAINT `CURP_fk_diagnostico` FOREIGN KEY (`CURP_fk_diagnostico`) REFERENCES `paciente` (`CURP`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diagnostico_tratamiento`
--

LOCK TABLES `diagnostico_tratamiento` WRITE;
/*!40000 ALTER TABLE `diagnostico_tratamiento` DISABLE KEYS */;
/*!40000 ALTER TABLE `diagnostico_tratamiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exploracion_cabezacuello`
--

DROP TABLE IF EXISTS `exploracion_cabezacuello`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exploracion_cabezacuello` (
  `id_exploracion_cabezacuello` int NOT NULL AUTO_INCREMENT,
  `exostosis` tinyint DEFAULT NULL,
  `endotosis` tinyint DEFAULT NULL,
  `dolicocefalico` tinyint DEFAULT NULL,
  `mesocefalico` tinyint DEFAULT NULL,
  `branquicefalico` tinyint DEFAULT NULL,
  `asimetria_transversal` tinyint DEFAULT NULL,
  `asimetria_longitudinal` tinyint DEFAULT NULL,
  `perfil_concavo` tinyint DEFAULT NULL,
  `perfil_convexo` tinyint DEFAULT NULL,
  `perfil_recto` tinyint DEFAULT NULL,
  `piel_normal` tinyint DEFAULT NULL,
  `piel_palida` tinyint DEFAULT NULL,
  `piel_cianotica` tinyint DEFAULT NULL,
  `piel_enrojecida` tinyint DEFAULT NULL,
  `musculos_hipotonicos` tinyint DEFAULT NULL,
  `musculos_hipertonicos` tinyint DEFAULT NULL,
  `musculos_espasticos` tinyint DEFAULT NULL,
  `cadena_ganglionar` tinyint DEFAULT NULL,
  `CURP_fk_exploracion` varchar(18) NOT NULL,
  PRIMARY KEY (`id_exploracion_cabezacuello`),
  KEY `CURP_fk_exploracion_idx` (`CURP_fk_exploracion`),
  CONSTRAINT `CURP_fk_exploracion` FOREIGN KEY (`CURP_fk_exploracion`) REFERENCES `paciente` (`CURP`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exploracion_cabezacuello`
--

LOCK TABLES `exploracion_cabezacuello` WRITE;
/*!40000 ALTER TABLE `exploracion_cabezacuello` DISABLE KEYS */;
/*!40000 ALTER TABLE `exploracion_cabezacuello` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exploracion_estomatognatico`
--

DROP TABLE IF EXISTS `exploracion_estomatognatico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exploracion_estomatognatico` (
  `id_estomatognatico` int NOT NULL AUTO_INCREMENT,
  `ruidos` tinyint DEFAULT NULL,
  `lateralidad` tinyint DEFAULT NULL,
  `apertura` tinyint DEFAULT NULL,
  `chasquidos` tinyint DEFAULT NULL,
  `crepitacion` tinyint DEFAULT NULL,
  `dificultad_abrirboca` tinyint DEFAULT NULL,
  `dolor_abertura_lateralidad` tinyint DEFAULT NULL,
  `fatiga_dolor_muscular` tinyint DEFAULT NULL,
  `disminuicion_abertura` tinyint DEFAULT NULL,
  `desviacion_abertura_cierre` tinyint DEFAULT NULL,
  `CURP_fk_estomatognatico` varchar(18) NOT NULL,
  PRIMARY KEY (`id_estomatognatico`),
  KEY `CURP_fk_estomatognatico_idx` (`CURP_fk_estomatognatico`),
  CONSTRAINT `CURP_fk_estomatognatico` FOREIGN KEY (`CURP_fk_estomatognatico`) REFERENCES `paciente` (`CURP`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exploracion_estomatognatico`
--

LOCK TABLES `exploracion_estomatognatico` WRITE;
/*!40000 ALTER TABLE `exploracion_estomatognatico` DISABLE KEYS */;
/*!40000 ALTER TABLE `exploracion_estomatognatico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `firma`
--

DROP TABLE IF EXISTS `firma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `firma` (
  `id_firma` int NOT NULL AUTO_INCREMENT,
  `firma` mediumblob NOT NULL,
  `curp` varchar(18) NOT NULL,
  PRIMARY KEY (`id_firma`),
  KEY `curp_idx` (`curp`),
  CONSTRAINT `curp` FOREIGN KEY (`curp`) REFERENCES `paciente` (`CURP`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `firma`
--

LOCK TABLES `firma` WRITE;
/*!40000 ALTER TABLE `firma` DISABLE KEYS */;
/*!40000 ALTER TABLE `firma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fotos_inicio`
--

DROP TABLE IF EXISTS `fotos_inicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fotos_inicio` (
  `id_fotos_inicio` int NOT NULL AUTO_INCREMENT,
  `fotos` mediumtext NOT NULL,
  `curp` varchar(18) NOT NULL,
  PRIMARY KEY (`id_fotos_inicio`),
  KEY `curp_idx` (`curp`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fotos_inicio`
--

LOCK TABLES `fotos_inicio` WRITE;
/*!40000 ALTER TABLE `fotos_inicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `fotos_inicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paciente`
--

DROP TABLE IF EXISTS `paciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paciente` (
  `id_paciente` int NOT NULL AUTO_INCREMENT,
  `nombre_paciente` varchar(100) NOT NULL,
  `CURP` varchar(18) NOT NULL,
  `sexo` varchar(10) NOT NULL,
  `edad` varchar(2) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `domicilio` varchar(150) NOT NULL,
  `telefono_casa` varchar(10) DEFAULT NULL,
  `telefono_celular` varchar(10) NOT NULL,
  `religion` varchar(50) DEFAULT NULL,
  `ocupacion` varchar(50) DEFAULT NULL,
  `escolaridad` varchar(50) DEFAULT NULL,
  `estado_civil` varchar(15) DEFAULT NULL,
  `derechohabiente` varchar(2) DEFAULT NULL,
  `medico_familiar` varchar(100) DEFAULT NULL,
  `numero_medico` varchar(10) DEFAULT NULL,
  `ultima_consulta` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id_paciente`),
  UNIQUE KEY `CURP_UNIQUE` (`CURP`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paciente`
--

LOCK TABLES `paciente` WRITE;
/*!40000 ALTER TABLE `paciente` DISABLE KEYS */;
/*!40000 ALTER TABLE `paciente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `periodo`
--

DROP TABLE IF EXISTS `periodo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `periodo` (
  `id_periodo` int NOT NULL AUTO_INCREMENT,
  `periodo` varchar(50) NOT NULL,
  PRIMARY KEY (`id_periodo`),
  UNIQUE KEY `periodo_UNIQUE` (`periodo`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periodo`
--

LOCK TABLES `periodo` WRITE;
/*!40000 ALTER TABLE `periodo` DISABLE KEYS */;
INSERT INTO `periodo` VALUES (11,'AGOSTO2024-ENERO2025'),(1,'AGOSTO2024-FEBRERO2025'),(6,'AGOSTO2025-ENERO2025'),(7,'AGOSTO2025-FEBRERO2026'),(8,'ENERO2020-JULIO2029'),(4,'FEBRERO2025-JULIO2024');
/*!40000 ALTER TABLE `periodo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `signos_vitales`
--

DROP TABLE IF EXISTS `signos_vitales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `signos_vitales` (
  `id_signos_vitales` int NOT NULL AUTO_INCREMENT,
  `temperatura` varchar(10) NOT NULL,
  `frecuencia_respiratoria` varchar(10) NOT NULL,
  `tension_arterial` varchar(10) NOT NULL,
  `frecuencia_cardiaca` varchar(10) NOT NULL,
  `peso` varchar(10) NOT NULL,
  `talla` varchar(10) NOT NULL,
  `CURP_fk_signos` varchar(18) NOT NULL,
  PRIMARY KEY (`id_signos_vitales`),
  UNIQUE KEY `CURP_fk_signos_UNIQUE` (`CURP_fk_signos`),
  KEY `CURP_idx` (`CURP_fk_signos`),
  CONSTRAINT `CURP_fk_signos` FOREIGN KEY (`CURP_fk_signos`) REFERENCES `paciente` (`CURP`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `signos_vitales`
--

LOCK TABLES `signos_vitales` WRITE;
/*!40000 ALTER TABLE `signos_vitales` DISABLE KEYS */;
/*!40000 ALTER TABLE `signos_vitales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tejidos_blandos`
--

DROP TABLE IF EXISTS `tejidos_blandos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tejidos_blandos` (
  `id_tejidos_blandos` int NOT NULL AUTO_INCREMENT,
  `ganglios` varchar(200) DEFAULT NULL,
  `glandulas_salivales` varchar(200) DEFAULT NULL,
  `labio_externo` varchar(200) DEFAULT NULL,
  `borde_bermellon` varchar(200) DEFAULT NULL,
  `labio_interno` varchar(200) DEFAULT NULL,
  `comisuras` varchar(200) DEFAULT NULL,
  `carrillos` varchar(200) DEFAULT NULL,
  `fondo_de_saco` varchar(200) DEFAULT NULL,
  `frenillos` varchar(200) DEFAULT NULL,
  `lengua_tercio_medio` varchar(200) DEFAULT NULL,
  `paladar_duro` varchar(200) DEFAULT NULL,
  `paladar_blando` varchar(200) DEFAULT NULL,
  `istmo_bucofaringe` varchar(200) DEFAULT NULL,
  `lengua_dorso` varchar(200) DEFAULT NULL,
  `lengua_bordes` varchar(200) DEFAULT NULL,
  `lengua_ventral` varchar(200) DEFAULT NULL,
  `piso_boca` varchar(200) DEFAULT NULL,
  `dientes` varchar(200) DEFAULT NULL,
  `mucosa_alveolar` varchar(200) DEFAULT NULL,
  `encia` varchar(200) DEFAULT NULL,
  `CURP_fk_tejidos` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`id_tejidos_blandos`),
  KEY `CURP_fk_tejidos_idx` (`CURP_fk_tejidos`),
  CONSTRAINT `CURP_fk_tejidos` FOREIGN KEY (`CURP_fk_tejidos`) REFERENCES `paciente` (`CURP`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tejidos_blandos`
--

LOCK TABLES `tejidos_blandos` WRITE;
/*!40000 ALTER TABLE `tejidos_blandos` DISABLE KEYS */;
/*!40000 ALTER TABLE `tejidos_blandos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutor`
--

DROP TABLE IF EXISTS `tutor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tutor` (
  `id_tutor` int NOT NULL AUTO_INCREMENT,
  `nombre_tutor` varchar(100) DEFAULT NULL,
  `edad_tutor` varchar(2) DEFAULT NULL,
  `domicilio_tutor` varchar(150) DEFAULT NULL,
  `telefono_casa_tutor` varchar(10) DEFAULT NULL,
  `celular_tutor` varchar(10) DEFAULT NULL,
  `CURP_fk_tutor` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`id_tutor`),
  KEY `CURP_fk_tutor_idx` (`CURP_fk_tutor`),
  CONSTRAINT `CURP_fk_tutor` FOREIGN KEY (`CURP_fk_tutor`) REFERENCES `paciente` (`CURP`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tutor`
--

LOCK TABLES `tutor` WRITE;
/*!40000 ALTER TABLE `tutor` DISABLE KEYS */;
/*!40000 ALTER TABLE `tutor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `matricula` varchar(8) NOT NULL,
  `nombre_usuario` varchar(100) NOT NULL,
  `usuario` varchar(25) NOT NULL,
  `contrasena` varchar(200) NOT NULL,
  `periodo` varchar(50) NOT NULL,
  `roles` enum('estudiante','administrador') DEFAULT NULL,
  PRIMARY KEY (`matricula`),
  UNIQUE KEY `matricula_UNIQUE` (`matricula`),
  UNIQUE KEY `usuario_UNIQUE` (`usuario`),
  KEY `periodo_idx` (`periodo`),
  CONSTRAINT `periodo` FOREIGN KEY (`periodo`) REFERENCES `periodo` (`periodo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES ('2003B003','jesus ramos pineda','Petriz.Pineda','123455','AGOSTO2025-ENERO2025',NULL),('2005B995','Eduardo de los Santoss','MAMPO','12345','AGOSTO2024-FEBRERO2025',NULL),('2103B001','Charly Mivchel Vasquez Villalobos','CMV','charly.m','FEBRERO2025-JULIO2024','administrador'),('2103B002','Vicente Guerra Orozco','VGO','eduardo022','AGOSTO2024-FEBRERO2025','estudiante'),('2103b003','Eduardo Vizarretea Sibaja','eduardo.viza','Vizoiiii','AGOSTO2024-FEBRERO2025','estudiante'),('2103B004','Alejandra Sosa Zarate','ASZ','AleAlee','AGOSTO2024-FEBRERO2025','estudiante'),('2103B005','Roldan de los Petriz','Roldan.Petriz','Petrizsibaja','AGOSTO2024-FEBRERO2025','estudiante'),('2103B006','Jesus Guerra Zarate','Jesus.Zarate','ArielJesus','AGOSTO2024-FEBRERO2025','estudiante'),('2103B007','Isaacar Guerra Orozco','IsaGO','compusmart','AGOSTO2024-FEBRERO2025','estudiante'),('2103B008','Sofia Vasquez Villalobos','RSVV','Rita.Sofi','AGOSTO2024-FEBRERO2025','estudiante'),('2103B009','Juan Daniel Vasquez Villalobos','Juan.Daniel','danielZ@12','AGOSTO2025-ENERO2025','estudiante'),('2103B010','Diego Antonio Vásquez Villalobos','Diego.A','DIego.Antonio','AGOSTO2024-FEBRERO2025','estudiante'),('2103B011','Johanna Maylet Cruz','Mayet.1','Johana2103','AGOSTO2025-FEBRERO2026','estudiante'),('2105B134','Borrego','Borrego.Iesit','Borrego','ENERO2020-JULIO2029',NULL),('2389Z004','Alan de Jesus Petriz','AlanViza','1234','FEBRERO2025-JULIO2024',NULL);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-30 12:40:43
