-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 18-Jul-2019 às 21:15
-- Versão do servidor: 10.1.36-MariaDB
-- versão do PHP: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `teofilo`
--
CREATE DATABASE IF NOT EXISTS `teofilo` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `teofilo`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `clientes`
--

CREATE TABLE `clientes` (
  `id` int(11) NOT NULL,
  `nome` varchar(150) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `telefone2` varchar(20) DEFAULT NULL,
  `sexo` varchar(20) DEFAULT NULL,
  `cpf` varchar(45) DEFAULT NULL,
  `rg` varchar(45) DEFAULT NULL,
  `nascimento` date DEFAULT NULL,
  `data_cadastro` date DEFAULT NULL,
  `endereco` varchar(100) DEFAULT NULL,
  `n_casa` varchar(10) DEFAULT NULL,
  `complemento` varchar(100) DEFAULT NULL,
  `valor` double DEFAULT '0',
  `ativo` tinyint(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `conta`
--

CREATE TABLE `conta` (
  `id` int(11) NOT NULL,
  `ID_CLIENTE` int(11) DEFAULT NULL,
  `descricao` varchar(200) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  `valor_ja_pago` double DEFAULT '0',
  `emissao` date DEFAULT NULL,
  `vencimento` date DEFAULT NULL,
  `data_pagamento_final` date DEFAULT NULL,
  `ativo` tinyint(1) DEFAULT NULL,
  `parcelado` tinyint(1) DEFAULT NULL,
  `cartao` tinyint(4) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `conta_sub`
--

CREATE TABLE `conta_sub` (
  `id` int(11) NOT NULL,
  `ID_CONTA` int(11) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  `vencimento` date DEFAULT NULL,
  `data_pago` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `controle_cartao`
--

CREATE TABLE `controle_cartao` (
  `id` int(11) NOT NULL,
  `data` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `documentos`
--

CREATE TABLE `documentos` (
  `id` int(11) NOT NULL,
  `documento` longblob,
  `nome` varchar(200) DEFAULT NULL,
  `ID_CLIENTE` int(4) DEFAULT NULL,
  `modificacao` date DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `ID_TIPO` int(4) DEFAULT NULL,
  `ID_PROCESSO` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `documentos_pessoais`
--

CREATE TABLE `documentos_pessoais` (
  `id` int(11) NOT NULL,
  `documento` longblob,
  `nome` varchar(200) DEFAULT NULL,
  `ID_CLIENTE` int(4) DEFAULT NULL,
  `alteracao` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `processos`
--

CREATE TABLE `processos` (
  `id` int(11) NOT NULL,
  `ID_CLIENTE` int(11) NOT NULL,
  `processo` varchar(200) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipo`
--

CREATE TABLE `tipo` (
  `id` int(11) NOT NULL,
  `descricao` varchar(200) DEFAULT NULL,
  `ID_CLIENTE` int(4) DEFAULT NULL,
  `ID_PROCESSO` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `conta`
--
ALTER TABLE `conta`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `conta_sub`
--
ALTER TABLE `conta_sub`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_ContaS_Conta` (`ID_CONTA`);

--
-- Indexes for table `controle_cartao`
--
ALTER TABLE `controle_cartao`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `documentos`
--
ALTER TABLE `documentos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_documento_tipo` (`ID_TIPO`),
  ADD KEY `fk_documento_processo` (`ID_PROCESSO`),
  ADD KEY `fk_clienteDocumento` (`ID_CLIENTE`);

--
-- Indexes for table `documentos_pessoais`
--
ALTER TABLE `documentos_pessoais`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_clienteDocumentoPessoal` (`ID_CLIENTE`);

--
-- Indexes for table `processos`
--
ALTER TABLE `processos`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tipo`
--
ALTER TABLE `tipo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_tipo_cliente` (`ID_CLIENTE`),
  ADD KEY `fk_tipo_processo` (`ID_PROCESSO`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `conta`
--
ALTER TABLE `conta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `conta_sub`
--
ALTER TABLE `conta_sub`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `controle_cartao`
--
ALTER TABLE `controle_cartao`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `documentos`
--
ALTER TABLE `documentos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `documentos_pessoais`
--
ALTER TABLE `documentos_pessoais`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `processos`
--
ALTER TABLE `processos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tipo`
--
ALTER TABLE `tipo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `conta_sub`
--
ALTER TABLE `conta_sub`
  ADD CONSTRAINT `fk_ContaS_Conta` FOREIGN KEY (`ID_CONTA`) REFERENCES `conta` (`id`);

--
-- Limitadores para a tabela `documentos`
--
ALTER TABLE `documentos`
  ADD CONSTRAINT `fk_clienteDocumento` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `clientes` (`id`),
  ADD CONSTRAINT `fk_documento_processo` FOREIGN KEY (`ID_PROCESSO`) REFERENCES `processos` (`id`),
  ADD CONSTRAINT `fk_documento_tipo` FOREIGN KEY (`ID_TIPO`) REFERENCES `tipo` (`id`);

--
-- Limitadores para a tabela `documentos_pessoais`
--
ALTER TABLE `documentos_pessoais`
  ADD CONSTRAINT `fk_clienteDocumentoPessoal` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `clientes` (`id`);

--
-- Limitadores para a tabela `tipo`
--
ALTER TABLE `tipo`
  ADD CONSTRAINT `fk_tipo_cliente` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `clientes` (`id`),
  ADD CONSTRAINT `fk_tipo_processo` FOREIGN KEY (`ID_PROCESSO`) REFERENCES `processos` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
