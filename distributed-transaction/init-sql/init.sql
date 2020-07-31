/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 8.0.20 : Database - master
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`master` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `master`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
                        `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '用户表主键',
                        `user_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
                        `birthday` date DEFAULT NULL COMMENT '出生日期',
                        `age` tinyint DEFAULT NULL COMMENT '年龄',
                        `remark` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
                        `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                        `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户';

/*Data for the table `user` */

insert  into `user`(`id`,`user_name`,`birthday`,`age`,`remark`,`create_time`,`update_time`) values
(1,'blank','1991-01-17',29,'test','2020-07-29 11:38:34','2020-07-29 11:41:51'),
(2,'blank-test','1990-04-27',30,'testttt',NULL,NULL),
(3,'blank','1991-01-17',22,'test dateTime auto fill',NULL,NULL);


CREATE DATABASE /*!32312 IF NOT EXISTS*/`second` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `second`;

/*Table structure for table `user_order` */

DROP TABLE IF EXISTS `user_order`;

CREATE TABLE `user_order` (
                              `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '订单表主键',
                              `user_id` bigint NOT NULL COMMENT '关联用户表ID',
                              `amount` decimal(10,2) DEFAULT NULL COMMENT '金额',
                              `remark` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
                              `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单';

/*Data for the table `user_order` */

insert  into `user_order`(`id`,`user_id`,`amount`,`remark`,`create_time`,`update_time`) values
(1,1,88888.00,'test order','2020-07-29 11:55:39',NULL),
(2,2,6.67,'ttttt','2020-07-29 14:48:20',NULL),
(3,3,7777.00,'dsfsdfds','2020-07-29 14:49:49',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;