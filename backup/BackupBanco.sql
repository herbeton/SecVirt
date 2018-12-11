-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: financeiro
-- ------------------------------------------------------
-- Server version	5.7.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cidade`
--

DROP TABLE IF EXISTS `cidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cidade` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `estado_codigo` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_3uxfxu3ar4lpub19hldftpikb` (`estado_codigo`),
  CONSTRAINT `FK_3uxfxu3ar4lpub19hldftpikb` FOREIGN KEY (`estado_codigo`) REFERENCES `estado` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cidade`
--

LOCK TABLES `cidade` WRITE;
/*!40000 ALTER TABLE `cidade` DISABLE KEYS */;
INSERT INTO `cidade` VALUES (1,'Natal',1),(2,'Parnamirim',1),(3,'Ares',1),(4,'Ceara Mirim',1),(5,'Pedro Velho',1),(6,'Sao Paulo',6),(7,'Fortaleza',5);
/*!40000 ALTER TABLE `cidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `dataCadastro` date NOT NULL,
  `liberado` bit(1) NOT NULL,
  `pessoa_codigo` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_mobustx1b8qkdhosqdhnog3lq` (`pessoa_codigo`),
  CONSTRAINT `FK_mobustx1b8qkdhosqdhnog3lq` FOREIGN KEY (`pessoa_codigo`) REFERENCES `pessoa` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'2015-06-09','\0',2),(2,'2018-11-01','',3),(3,'2018-11-28','',1);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado`
--

DROP TABLE IF EXISTS `estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `sigla` varchar(2) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado`
--

LOCK TABLES `estado` WRITE;
/*!40000 ALTER TABLE `estado` DISABLE KEYS */;
INSERT INTO `estado` VALUES (1,'Rio Grande do Norte','RN'),(3,'Santa Catarina','SC'),(5,'Ceara','CE'),(6,'Sao Paulo','SP'),(7,'Minas Gerais','MG'),(8,'Minas Gerais 3','MG'),(9,'Rio de Janeiro','RJ');
/*!40000 ALTER TABLE `estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedor`
--

DROP TABLE IF EXISTS `fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fornecedor` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(50) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedor`
--

LOCK TABLES `fornecedor` WRITE;
/*!40000 ALTER TABLE `fornecedor` DISABLE KEYS */;
INSERT INTO `fornecedor` VALUES (1,'Petrobras'),(2,'Navy Group'),(3,'COSERN');
/*!40000 ALTER TABLE `fornecedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionario` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `carteiraTrabalho` varchar(15) NOT NULL,
  `dataAdmissao` date NOT NULL,
  `pessoa_codigo` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_l9i1mm6ohy2192qfudb90xhc6` (`pessoa_codigo`),
  CONSTRAINT `FK_l9i1mm6ohy2192qfudb90xhc6` FOREIGN KEY (`pessoa_codigo`) REFERENCES `pessoa` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES (2,'34234','2010-10-16',1),(3,'4325','2010-11-16',2);
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historico`
--

DROP TABLE IF EXISTS `historico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `historico` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `horario` datetime NOT NULL,
  `observacoes` varchar(255) DEFAULT NULL,
  `produto_codigo` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_mqu402bbqhf0pqy2n6nfxc31n` (`produto_codigo`),
  CONSTRAINT `FK_mqu402bbqhf0pqy2n6nfxc31n` FOREIGN KEY (`produto_codigo`) REFERENCES `produto` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historico`
--

LOCK TABLES `historico` WRITE;
/*!40000 ALTER TABLE `historico` DISABLE KEYS */;
INSERT INTO `historico` VALUES (1,'2018-12-01 16:55:46','CEM',1),(2,'2018-12-01 16:56:36','CEM 2019',1);
/*!40000 ALTER TABLE `historico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itemvenda`
--

DROP TABLE IF EXISTS `itemvenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itemvenda` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `precoParcial` decimal(7,2) NOT NULL,
  `quantidade` smallint(6) NOT NULL,
  `produto_codigo` bigint(20) NOT NULL,
  `venda_codigo` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_afhuh5uapr0pkljm58r86bnof` (`produto_codigo`),
  KEY `FK_34u5r6crbeyi67pm4ptlha46u` (`venda_codigo`),
  CONSTRAINT `FK_34u5r6crbeyi67pm4ptlha46u` FOREIGN KEY (`venda_codigo`) REFERENCES `venda` (`codigo`),
  CONSTRAINT `FK_afhuh5uapr0pkljm58r86bnof` FOREIGN KEY (`produto_codigo`) REFERENCES `produto` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itemvenda`
--

LOCK TABLES `itemvenda` WRITE;
/*!40000 ALTER TABLE `itemvenda` DISABLE KEYS */;
INSERT INTO `itemvenda` VALUES (1,54.80,4,1,1),(2,1000.00,1,2,2),(3,27.40,2,1,2),(4,137.00,10,1,3),(6,3000.00,3,2,5);
/*!40000 ALTER TABLE `itemvenda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pessoa` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `bairro` varchar(30) NOT NULL,
  `celular` varchar(14) NOT NULL,
  `cep` varchar(10) NOT NULL,
  `complemento` varchar(10) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `email` varchar(100) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `numero` smallint(6) NOT NULL,
  `rg` varchar(12) NOT NULL,
  `rua` varchar(100) NOT NULL,
  `telefone` varchar(13) NOT NULL,
  `cidade_codigo` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_ru7rwevg7kj864u8vkyo8vbyi` (`cidade_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` VALUES (1,'T','23423','443','234','777.777.777-77','4532rfr','Joao',3,'43243','Nova','4324',1),(2,'KKK','324','455','33423','4234','fdgdfg2','Maria',3,'4','Desc','324',1),(3,'Nova Parnamirim','(84)99903-6814','59.151-250','Ap406 B6','086.976.614-71','herbeton7@gmail.com','Herbeton Farias Bispo',2400,'00.268.136-5','Av Abel Cabral','(84)9990-3681',2),(4,'8','(88)88888-8888','88.888-888','8','888.888.888-88','88@gg.com','8',8,'88.888.888-8','8','(88)8888-8888',7);
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(80) NOT NULL,
  `preco` decimal(6,2) NOT NULL,
  `quantidade` smallint(6) NOT NULL,
  `fornecedor_codigo` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_js72o9rt9rkmbkgwyc8u6vixg` (`fornecedor_codigo`),
  CONSTRAINT `FK_js72o9rt9rkmbkgwyc8u6vixg` FOREIGN KEY (`fornecedor_codigo`) REFERENCES `fornecedor` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (1,'Curso de Java',13.70,7,2),(2,'Curso Biribibo',1000.00,7,2);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `senha` varchar(32) NOT NULL,
  `tipo` char(1) NOT NULL,
  `pessoa_codigo` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_s8lrxfgvascltkib6t418n5ef` (`pessoa_codigo`),
  CONSTRAINT `FK_s8lrxfgvascltkib6t418n5ef` FOREIGN KEY (`pessoa_codigo`) REFERENCES `pessoa` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (6,'','f1c1592588411002af340cbaedd6fc33','G',1),(7,'','e10adc3949ba59abbe56e057f20f883e','G',3),(8,'','e10adc3949ba59abbe56e057f20f883e','B',4);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venda`
--

DROP TABLE IF EXISTS `venda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `venda` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `horario` datetime NOT NULL,
  `precoTotal` decimal(7,2) NOT NULL,
  `cliente_codigo` bigint(20) DEFAULT NULL,
  `funcionario_codigo` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_ass4ciu3lp1ipkofx918j7nm7` (`cliente_codigo`),
  KEY `FK_5twcbl0srrivgeocts0y19trf` (`funcionario_codigo`),
  CONSTRAINT `FK_5twcbl0srrivgeocts0y19trf` FOREIGN KEY (`funcionario_codigo`) REFERENCES `funcionario` (`codigo`),
  CONSTRAINT `FK_ass4ciu3lp1ipkofx918j7nm7` FOREIGN KEY (`cliente_codigo`) REFERENCES `cliente` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venda`
--

LOCK TABLES `venda` WRITE;
/*!40000 ALTER TABLE `venda` DISABLE KEYS */;
INSERT INTO `venda` VALUES (1,'2018-11-27 23:59:21',54.80,2,3),(2,'2018-11-28 00:45:54',1027.40,1,2),(3,'2018-12-01 17:09:18',137.00,2,2),(5,'2018-12-01 17:48:35',3000.00,2,2);
/*!40000 ALTER TABLE `venda` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-11  6:33:37
