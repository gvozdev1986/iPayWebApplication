-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               5.7.10-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for ipaywebapplication
CREATE DATABASE IF NOT EXISTS `ipaywebapplication` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ipaywebapplication`;

-- Dumping structure for table ipaywebapplication.bankaccount
CREATE TABLE IF NOT EXISTS `bankaccount` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `CreditCard` int(11) DEFAULT NULL,
  `StatusBankAccount` tinyint(4) DEFAULT NULL,
  `BalanceBankAccount` double DEFAULT NULL,
  `Available` tinyint(4) DEFAULT NULL,
  `NameAccount` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_bankaccount_creditcard` (`CreditCard`),
  CONSTRAINT `FK_bankaccount_creditcard` FOREIGN KEY (`CreditCard`) REFERENCES `creditcard` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table ipaywebapplication.creditcard
CREATE TABLE IF NOT EXISTS `creditcard` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Client` int(11) DEFAULT NULL,
  `CardNumber` varchar(50) DEFAULT NULL,
  `CardFirstName` varchar(50) DEFAULT NULL,
  `CardLastName` varchar(50) DEFAULT NULL,
  `ValidDate` varchar(50) DEFAULT NULL,
  `TypeCard` varchar(50) DEFAULT NULL,
  `VerifyCode` varchar(50) DEFAULT NULL,
  `Block` tinyint(4) DEFAULT NULL,
  `Available` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `CardNumber` (`CardNumber`),
  KEY `FK_creditcard_client` (`Client`),
  CONSTRAINT `FK_creditcard_client` FOREIGN KEY (`Client`) REFERENCES `usr` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=313 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table ipaywebapplication.mailsettings
CREATE TABLE IF NOT EXISTS `mailsettings` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `MailLogin` varchar(50) DEFAULT NULL,
  `MailPswd` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table ipaywebapplication.messages
CREATE TABLE IF NOT EXISTS `messages` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `NameContact` varchar(50) NOT NULL,
  `Date` date NOT NULL,
  `Time` time NOT NULL,
  `EmailContact` varchar(50) NOT NULL,
  `PhoneContact` varchar(50) NOT NULL,
  `MessageContact` varchar(1500) NOT NULL,
  `CheckRead` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table ipaywebapplication.payment
CREATE TABLE IF NOT EXISTS `payment` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `DatePayment` date NOT NULL,
  `TimePayment` time NOT NULL,
  `DescriptionPayment` varchar(1500) NOT NULL DEFAULT '0',
  `PaymentData` int(11) NOT NULL DEFAULT '0',
  `AmountPayment` double NOT NULL DEFAULT '0',
  `CreditCard` int(11) NOT NULL DEFAULT '0',
  `Available` tinyint(4) NOT NULL DEFAULT '0',
  `OrderNo` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_payment_paymentdata` (`PaymentData`),
  KEY `FK_payment_creditcard` (`CreditCard`),
  CONSTRAINT `FK_payment_creditcard` FOREIGN KEY (`CreditCard`) REFERENCES `creditcard` (`Id`),
  CONSTRAINT `FK_payment_paymentdata` FOREIGN KEY (`PaymentData`) REFERENCES `paymentdata` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=271 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table ipaywebapplication.paymentdata
CREATE TABLE IF NOT EXISTS `paymentdata` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `PaymentDataCode` varchar(250) NOT NULL DEFAULT '0',
  `PaymentDataName` varchar(250) NOT NULL DEFAULT '0',
  `PaymentDataGroup` varchar(250) NOT NULL DEFAULT '0',
  `PaymentDataDescription` varchar(1500) NOT NULL DEFAULT '0',
  `Available` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table ipaywebapplication.usr
CREATE TABLE IF NOT EXISTS `usr` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Login` varchar(50) NOT NULL DEFAULT '0',
  `Password` varchar(50) NOT NULL DEFAULT '0',
  `FirstName` varchar(50) NOT NULL DEFAULT '0',
  `LastName` varchar(50) NOT NULL DEFAULT '0',
  `Patronymic` varchar(50) NOT NULL DEFAULT '0',
  `PhoneHome` varchar(50) NOT NULL DEFAULT '0',
  `DateBirth` date NOT NULL,
  `PhoneMobile` varchar(50) NOT NULL DEFAULT '0',
  `Address` varchar(1500) NOT NULL DEFAULT '0',
  `Email` varchar(150) NOT NULL DEFAULT '0',
  `Available` tinyint(4) NOT NULL DEFAULT '0',
  `isAdmin` tinyint(4) DEFAULT NULL,
  `RegCode` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Login` (`Login`)
) ENGINE=InnoDB AUTO_INCREMENT=986 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
