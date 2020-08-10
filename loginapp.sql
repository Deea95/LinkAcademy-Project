-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 14, 2020 at 02:09 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `loginapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `erp`
--

CREATE TABLE `erp` (
  `titular` varchar(255) NOT NULL,
  `nr_ff` int(255) NOT NULL,
  `dataContabila` date NOT NULL,
  `dataRegistru` date NOT NULL,
  `furnizor` varchar(255) NOT NULL,
  `modalitatePlata` varchar(255) NOT NULL,
  `dataScadenta` date NOT NULL,
  `tipFactura` varchar(255) NOT NULL,
  `descriere` varchar(255) NOT NULL,
  `tva` int(255) NOT NULL,
  `reprezentant` varchar(255) NOT NULL,
  `societate` varchar(255) NOT NULL,
  `contract` int(255) NOT NULL,
  `adresa` varchar(255) NOT NULL,
  `contBancar` varchar(255) NOT NULL,
  `valuta` varchar(255) NOT NULL,
  `sumacuTVA` int(255) NOT NULL,
  `campanie` varchar(255) NOT NULL,
  `sumafaraTVA` int(255) NOT NULL,
  `id` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `erp`
--

INSERT INTO `erp` (`titular`, `nr_ff`, `dataContabila`, `dataRegistru`, `furnizor`, `modalitatePlata`, `dataScadenta`, `tipFactura`, `descriere`, `tva`, `reprezentant`, `societate`, `contract`, `adresa`, `contBancar`, `valuta`, `sumacuTVA`, `campanie`, `sumafaraTVA`, `id`) VALUES
('SC.Deea.SRL', 12685, '2020-07-09', '2020-07-09', 'SC.Deea.SRL', 'Card Bancar', '2020-07-30', 'Chirie lunara', 'Chirie luna Iulie 2020, 25.000 RON fara TVA', 4750, 'Popa Andreea', 'SC.LinkAcademy.SA', 630, 'Bucuresti, sector 3', 'RO56RNCB256324848', 'RON', 29750, '2020-07', 25000, 2);

-- --------------------------------------------------------

--
-- Table structure for table `register`
--

CREATE TABLE `register` (
  `id` int(255) NOT NULL,
  `fname` varchar(255) NOT NULL,
  `lname` varchar(255) NOT NULL,
  `uname` varchar(255) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `re_pass` varchar(255) NOT NULL,
  `addr` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `register`
--

INSERT INTO `register` (`id`, `fname`, `lname`, `uname`, `pass`, `re_pass`, `addr`) VALUES
(2, 'Popa', 'Andreea', 'Deea95', '123', '123', 'buc');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `email` text NOT NULL,
  `password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `password`) VALUES
(1, 'deea95.gaming@gmail.com', '123456789');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `erp`
--
ALTER TABLE `erp`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `register`
--
ALTER TABLE `register`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `erp`
--
ALTER TABLE `erp`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
