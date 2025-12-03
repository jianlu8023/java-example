-- --------------------------------------------------------
-- 主机:                           fc00::64
-- 服务器版本:                        8.0.31 - MySQL Community Server - GPL
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 导出 basic 的数据库结构
DROP DATABASE IF EXISTS `basic`;
CREATE DATABASE IF NOT EXISTS `basic` /*!40100 DEFAULT CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `basic`;

-- 导出  表 basic.file 结构
DROP TABLE IF EXISTS `file`;
CREATE TABLE IF NOT EXISTS `file` (
  `uid` bigint NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `fileName` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件名称',
  `fileDescribe` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件描述',
  `fileSize` bigint NOT NULL COMMENT '文件大小',
  `fileUploadTime` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件上传时间',
  `fileType` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件类型，文件后缀',
  `isArchive` tinyint NOT NULL DEFAULT '0' COMMENT '是否被归档',
  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '是否被删除',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='文件';

-- 数据导出被取消选择。

-- 导出  表 basic.user 结构
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `uid` bigint NOT NULL AUTO_INCREMENT,
  `userName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `userNick` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
  `userAge` int NOT NULL COMMENT '用户年龄',
  `userEmail` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户emial',
  `userGender` tinyint NOT NULL COMMENT '用户性别',
  `userId` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户身份证号',
  `createTime` datetime NOT NULL  COMMENT '用户创建时间',
  `updateTime` datetime NOT NULL COMMENT '更新信息时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户信息表';

-- 数据导出被取消选择。

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
