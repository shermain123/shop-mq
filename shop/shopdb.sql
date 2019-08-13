/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.40 : Database - shopdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`shopdb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `shopdb`;

/*Table structure for table `good` */

DROP TABLE IF EXISTS `good`;

CREATE TABLE `good` (
  `id` varchar(50) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `descs` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `good` */

insert  into `good`(`id`,`name`,`descs`) values ('1.0','test1','2.0'),('2.0','test2','3.0'),('3.0','test3','3.0');

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(8) unsigned NOT NULL,
  `pro_name` varchar(50) NOT NULL COMMENT '商品名称',
  `pro_qty` int(8) NOT NULL COMMENT '数量',
  `pro_type` varchar(8) NOT NULL COMMENT '类型 ',
  `pro_state` varchar(1) NOT NULL COMMENT '状态（0启用,1停用）',
  `price` double DEFAULT NULL COMMENT '价格',
  `ioc` varchar(100) DEFAULT NULL COMMENT '图片',
  `cr_date` date DEFAULT NULL COMMENT '上价日期',
  `up_date` date DEFAULT NULL COMMENT '更新日期',
  `desc` varchar(200) DEFAULT NULL COMMENT '描述',
  `discount` double DEFAULT NULL COMMENT '折扣',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `product` */

insert  into `product`(`id`,`pro_name`,`pro_qty`,`pro_type`,`pro_state`,`price`,`ioc`,`cr_date`,`up_date`,`desc`,`discount`) values (1,'test1',2,'1','1',18.5,'','2018-05-31','2018-05-31','aaaaa',1),(2,'test2',3,'1','1',12.5,NULL,'2018-06-08','2018-06-08','bbbb',2),(3,'test3',3,'1','1',85.5,NULL,'2018-06-08','2018-06-08','cccc',1),(4,'test4',6,'1','1',45.5,NULL,'2018-06-15','2018-06-15','ccccc',2);

/*Table structure for table `product_copy` */

DROP TABLE IF EXISTS `product_copy`;

CREATE TABLE `product_copy` (
  `id` int(8) unsigned NOT NULL,
  `pro_name` varchar(50) NOT NULL COMMENT '商品名称',
  `pro_qty` int(8) NOT NULL COMMENT '数量',
  `pro_type` varchar(8) NOT NULL COMMENT '类型 ',
  `pro_state` varchar(1) NOT NULL COMMENT '状态（0启用,1停用）',
  `price` double DEFAULT NULL COMMENT '价格',
  `ioc` varchar(100) DEFAULT NULL COMMENT '图片',
  `cr_date` date DEFAULT NULL COMMENT '上价日期',
  `up_date` date DEFAULT NULL COMMENT '更新日期',
  `desc` varchar(200) DEFAULT NULL COMMENT '描述',
  `discount` double DEFAULT NULL COMMENT '折扣',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `product_copy` */

insert  into `product_copy`(`id`,`pro_name`,`pro_qty`,`pro_type`,`pro_state`,`price`,`ioc`,`cr_date`,`up_date`,`desc`,`discount`) values (1,'test1',2,'1','1',18.5,'','2018-05-31','2018-05-31','aaaaa',1),(2,'test2',3,'1','1',12.5,NULL,'2018-06-08','2018-06-08','bbbb',2),(3,'test3',3,'1','1',85.5,NULL,'2018-06-08','2018-06-08','cccc',1),(4,'test4',6,'1','1',45.5,NULL,'2018-06-15','2018-06-15','ccccc',2);

/*Table structure for table `tb_test` */

DROP TABLE IF EXISTS `tb_test`;

CREATE TABLE `tb_test` (
  `var1` varchar(255) DEFAULT NULL,
  `var2` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_test` */

insert  into `tb_test`(`var1`,`var2`) values ('ad','算算'),('sd','ds');

/*Table structure for table `tb_var` */

DROP TABLE IF EXISTS `tb_var`;

CREATE TABLE `tb_var` (
  `var1` varchar(255) DEFAULT NULL,
  `var2` varchar(255) DEFAULT NULL,
  `var3` varchar(255) DEFAULT NULL,
  `var4` varchar(255) DEFAULT NULL,
  `var5` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_var` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
