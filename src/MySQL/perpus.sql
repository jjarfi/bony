/*
SQLyog Ultimate v11.11 (32 bit)
MySQL - 5.5.42 : Database - perpus
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`perpus` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `perpus`;

/*Table structure for table `buku` */

DROP TABLE IF EXISTS `buku`;

CREATE TABLE `buku` (
  `idbuku` varchar(12) NOT NULL,
  `judul` varchar(100) DEFAULT NULL,
  `idpenerbit` varchar(12) NOT NULL,
  `idpengarang` varchar(12) DEFAULT NULL,
  `tahun` text,
  `jumlah` text,
  `tgl` text,
  PRIMARY KEY (`idbuku`),
  KEY `idpenerbit` (`idpenerbit`),
  KEY `idpengarang` (`idpengarang`),
  CONSTRAINT `buku_ibfk_1` FOREIGN KEY (`idpenerbit`) REFERENCES `penerbit` (`idpenerbit`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `buku_ibfk_2` FOREIGN KEY (`idpengarang`) REFERENCES `pengarang` (`idpengarang`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `buku` */

/*Table structure for table `penerbit` */

DROP TABLE IF EXISTS `penerbit`;

CREATE TABLE `penerbit` (
  `idpenerbit` varchar(12) NOT NULL,
  `nama` varchar(20) DEFAULT NULL,
  `alamat` varchar(30) DEFAULT NULL,
  `nohp` text,
  `email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idpenerbit`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `penerbit` */

/*Table structure for table `pengarang` */

DROP TABLE IF EXISTS `pengarang`;

CREATE TABLE `pengarang` (
  `idpengarang` varchar(12) NOT NULL,
  `nama` varchar(20) DEFAULT NULL,
  `alamat` varchar(30) DEFAULT NULL,
  `nohp` text,
  `email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idpengarang`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `pengarang` */

insert  into `pengarang`(`idpengarang`,`nama`,`alamat`,`nohp`,`email`) values ('P-001','JEREMIA','WAENA','081343439831','JERRY@GMAIL.COM'),('P-002','JARFI','WAENA','081343438931','JARFI@GMAIL.COM');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
