-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 26, 2016 at 07:37 AM
-- Server version: 10.1.10-MariaDB
-- PHP Version: 5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `member`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `ID` int(11) NOT NULL,
  `Username` varchar(30) NOT NULL,
  `Password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`ID`, `Username`, `Password`) VALUES
(1, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `calon`
--

CREATE TABLE `calon` (
  `id` int(11) NOT NULL,
  `nama1` varchar(30) NOT NULL,
  `nama2` varchar(30) DEFAULT NULL,
  `nama_lengkap1` varchar(50) DEFAULT NULL,
  `nama_lengkap2` varchar(50) DEFAULT NULL,
  `foto` varchar(30) NOT NULL,
  `profil` longtext,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `suara`
--

CREATE TABLE `suara` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `id_tps` varchar(30) NOT NULL,
  `calon1` int(11) DEFAULT NULL,
  `calon2` int(11) DEFAULT NULL,
  `calon3` int(11) DEFAULT NULL,
  `sah` int(11) DEFAULT NULL,
  `rusak` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL,
  `verified` int(3) DEFAULT NULL,
  `valcode` varchar(4) NOT NULL,
  `flag` int(3) DEFAULT NULL,
  `over` int(3) DEFAULT NULL,
  `koreksi` int(11) NOT NULL,
  `manual` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tps`
--

CREATE TABLE `tps` (
  `id` int(11) NOT NULL,
  `kd_wilayah` varchar(8) DEFAULT NULL,
  `no_tps` int(11) DEFAULT NULL,
  `laki-laki` int(11) DEFAULT NULL,
  `perempuan` int(11) NOT NULL,
  `jumlah` int(11) DEFAULT NULL,
  `cakupan` varchar(200) DEFAULT NULL,
  `alamat` varchar(200) DEFAULT NULL,
  `keterangan` varchar(200) DEFAULT NULL,
  `petugas` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `flag` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(30) NOT NULL,
  `Username` varchar(30) NOT NULL,
  `no_hp` varchar(12) DEFAULT NULL,
  `kd_wilayah` varchar(8) DEFAULT NULL,
  `email` varchar(320) NOT NULL,
  `password` varchar(30) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `eksekutif` int(11) DEFAULT NULL,
  `cretaed_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_at` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `delete_at` datetime DEFAULT NULL,
  `remember_token` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `wilayah`
--

CREATE TABLE `wilayah` (
  `id` int(11) NOT NULL,
  `kode_wilayah` varchar(30) DEFAULT NULL,
  `nama` varchar(30) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `level_wilayah` int(11) DEFAULT NULL,
  `mst_kode_wilayah` varchar(8) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `wilayah`
--

INSERT INTO `wilayah` (`id`, `kode_wilayah`, `nama`, `url`, `level_wilayah`, `mst_kode_wilayah`, `sort`) VALUES
(1, '036300', 'Kota Semarang', 'Kota_Semarang', 2, '030000', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Username` (`Username`),
  ADD KEY `Password` (`Password`),
  ADD KEY `Password_2` (`Password`),
  ADD KEY `Username_2` (`Username`);

--
-- Indexes for table `calon`
--
ALTER TABLE `calon`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `suara`
--
ALTER TABLE `suara`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tps`
--
ALTER TABLE `tps`
  ADD PRIMARY KEY (`id`),
  ADD KEY `kd_wilayah` (`kd_wilayah`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `Username` (`Username`),
  ADD KEY `no_hp` (`no_hp`);

--
-- Indexes for table `wilayah`
--
ALTER TABLE `wilayah`
  ADD PRIMARY KEY (`id`),
  ADD KEY `kode_wilayah` (`kode_wilayah`),
  ADD KEY `nama` (`nama`),
  ADD KEY `level_wilayah` (`level_wilayah`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `calon`
--
ALTER TABLE `calon`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `suara`
--
ALTER TABLE `suara`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tps`
--
ALTER TABLE `tps`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `wilayah`
--
ALTER TABLE `wilayah`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
