-- MySQL dump 10.13  Distrib 5.7.28, for Linux (x86_64)
--
-- Host: 47.106.210.183    Database: attendance
-- ------------------------------------------------------
-- Server version	5.7.25

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
-- Table structure for table `biz_attendance`
--

DROP TABLE IF EXISTS `biz_attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `biz_attendance` (
  `attendance_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `attendance_type` int(11) DEFAULT NULL COMMENT '0：缺席，1:迟到，2：早退',
  `attendance_time` datetime DEFAULT NULL COMMENT '时间',
  `attendance_stu_id` int(11) DEFAULT NULL COMMENT '学生外键',
  `attendance_cas_id` int(11) DEFAULT NULL COMMENT '课程外键',
  `attendance_cls_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`attendance_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `biz_attendance`
--

LOCK TABLES `biz_attendance` WRITE;
/*!40000 ALTER TABLE `biz_attendance` DISABLE KEYS */;
INSERT INTO `biz_attendance` VALUES (6,0,'2019-11-23 18:06:33',11,8,8),(7,1,'2019-11-23 18:06:33',12,8,8),(8,2,'2019-11-23 18:06:33',13,8,8),(9,3,'2019-11-23 18:06:33',14,8,8),(10,4,'2019-11-23 18:06:33',15,8,8),(11,3,'2019-11-23 18:07:43',11,8,8),(12,2,'2019-11-23 18:07:43',13,8,8),(13,3,'2019-11-23 20:27:51',11,8,8),(14,3,'2019-11-23 20:27:51',12,8,8),(15,3,'2019-11-23 20:27:51',13,8,8),(16,3,'2019-11-23 20:27:51',14,8,8),(17,3,'2019-11-23 20:27:51',15,8,8),(18,4,'2019-11-24 17:16:18',11,8,8),(19,4,'2019-11-24 17:16:18',12,8,8),(20,4,'2019-11-24 17:16:18',13,8,8),(21,4,'2019-11-24 17:16:18',14,8,8),(22,4,'2019-11-24 17:16:18',15,8,8);
/*!40000 ALTER TABLE `biz_attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `biz_cou_of_cla`
--

DROP TABLE IF EXISTS `biz_cou_of_cla`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `biz_cou_of_cla` (
  `boco_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) DEFAULT NULL COMMENT '课程id',
  `classes_id` int(11) DEFAULT NULL COMMENT '班级id',
  PRIMARY KEY (`boco_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='中间表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `biz_cou_of_cla`
--

LOCK TABLES `biz_cou_of_cla` WRITE;
/*!40000 ALTER TABLE `biz_cou_of_cla` DISABLE KEYS */;
INSERT INTO `biz_cou_of_cla` VALUES (10,6,4),(11,6,2),(12,6,1),(13,7,1),(14,7,2),(15,8,8),(16,8,1),(17,8,2),(18,8,7),(19,8,9),(20,8,6),(21,5,1),(22,5,2),(23,5,16),(24,5,17),(25,9,1),(26,9,2),(27,9,6),(28,9,7),(29,9,8),(30,9,9),(31,9,16),(32,9,17);
/*!40000 ALTER TABLE `biz_cou_of_cla` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `biz_stu_of_cla`
--

DROP TABLE IF EXISTS `biz_stu_of_cla`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `biz_stu_of_cla` (
  `bsoc_id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_id` int(11) DEFAULT NULL COMMENT '学生id',
  `cla_id` int(11) DEFAULT NULL COMMENT '班级id',
  PRIMARY KEY (`bsoc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='学生班级表：多对多';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `biz_stu_of_cla`
--

LOCK TABLES `biz_stu_of_cla` WRITE;
/*!40000 ALTER TABLE `biz_stu_of_cla` DISABLE KEYS */;
INSERT INTO `biz_stu_of_cla` VALUES (5,10,1),(6,11,8),(7,12,8),(8,13,8),(9,14,8),(10,15,8),(16,11,16),(17,15,16),(18,11,17),(19,12,17),(20,13,17),(21,14,17),(22,15,17);
/*!40000 ALTER TABLE `biz_stu_of_cla` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_admin`
--

DROP TABLE IF EXISTS `sys_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `admin_name` varchar(50) DEFAULT NULL COMMENT '管理员姓名',
  `admin_code` varchar(50) DEFAULT NULL COMMENT '管理员编号',
  `admin_phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `admin_email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_admin`
--

LOCK TABLES `sys_admin` WRITE;
/*!40000 ALTER TABLE `sys_admin` DISABLE KEYS */;
INSERT INTO `sys_admin` VALUES (1,'admin','admin','admin','admin');
/*!40000 ALTER TABLE `sys_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_classes`
--

DROP TABLE IF EXISTS `sys_classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_classes` (
  `classes_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `classes_name` varchar(50) DEFAULT NULL COMMENT '班级名称',
  `classes_code` varchar(50) DEFAULT NULL COMMENT '班级编号',
  PRIMARY KEY (`classes_id`),
  UNIQUE KEY `classes_name` (`classes_name`),
  UNIQUE KEY `classes_code` (`classes_code`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_classes`
--

LOCK TABLES `sys_classes` WRITE;
/*!40000 ALTER TABLE `sys_classes` DISABLE KEYS */;
INSERT INTO `sys_classes` VALUES (1,'软件1班','SF001'),(2,'软件2班','SF002'),(6,'软件3班','SOFT003'),(7,'软件4班','SOFT004'),(8,'软件5班','SOFT005'),(9,'软件6班','SOFT006'),(16,'计算机本科一斑','COM001'),(17,'计算机本科二斑','COM002');
/*!40000 ALTER TABLE `sys_classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_college`
--

DROP TABLE IF EXISTS `sys_college`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_college` (
  `college_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `college_name` varchar(50) DEFAULT NULL COMMENT '学院名称',
  `college_code` varchar(50) DEFAULT NULL COMMENT '学院编号',
  PRIMARY KEY (`college_id`),
  UNIQUE KEY `college_code` (`college_code`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_college`
--

LOCK TABLES `sys_college` WRITE;
/*!40000 ALTER TABLE `sys_college` DISABLE KEYS */;
INSERT INTO `sys_college` VALUES (1,'大数据与软件工程学院','CO001'),(6,'信息与电子工程学院','C0023'),(7,'教师教育学院','TEA001');
/*!40000 ALTER TABLE `sys_college` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_course`
--

DROP TABLE IF EXISTS `sys_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_course` (
  `course_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `course_name` varchar(50) DEFAULT NULL COMMENT '课程名称',
  `course_code` varchar(50) DEFAULT NULL COMMENT '编号',
  `teacher_id` int(11) DEFAULT NULL COMMENT '开课教师',
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_course`
--

LOCK TABLES `sys_course` WRITE;
/*!40000 ALTER TABLE `sys_course` DISABLE KEYS */;
INSERT INTO `sys_course` VALUES (5,'AAA1','AAA1',1),(6,'QQQ','QQQ',1),(7,'JavaWeb','JavaWeb',1),(8,'Kubernetes','C008',1),(9,'以太坊之智能合约','BLOCKCHAIN001',1);
/*!40000 ALTER TABLE `sys_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `icon` varchar(255) NOT NULL,
  `href` varchar(255) NOT NULL,
  `spread` tinyint(1) DEFAULT '0',
  `roleType` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,'学生管理','&#xe630;',' ',0,'1'),(2,'班级管理','&#xe630;',' ',0,'1'),(3,'学院管理','&#xe630;',' ',0,'1'),(4,'课程管理','&#xe630;',' ',0,'1'),(5,'考勤管理','&#xe630;',' ',0,'1'),(6,'教师管理','&#xe630;',' ',0,'1');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu_children`
--

DROP TABLE IF EXISTS `sys_menu_children`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu_children` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `icon` varchar(255) NOT NULL,
  `href` varchar(255) NOT NULL,
  `spread` tinyint(1) DEFAULT '0',
  `fatherMenu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA8243AED1D1BC4B6` (`fatherMenu_id`),
  CONSTRAINT `FKA8243AED1D1BC4B6` FOREIGN KEY (`fatherMenu_id`) REFERENCES `sys_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu_children`
--

LOCK TABLES `sys_menu_children` WRITE;
/*!40000 ALTER TABLE `sys_menu_children` DISABLE KEYS */;
INSERT INTO `sys_menu_children` VALUES (1,'学生列表','&#xe61c;','/Attendance_war/page/system/student/studentList.html',0,1),(2,'班级列表','&#xe61c;','/Attendance_war/page/system/class/classList.html',0,2),(3,'学院列表','&#xe61c;','/Attendance_war/page/system/colleges/collegesList.html',0,3),(4,'课程列表','&#xe61c;','/Attendance_war/page/system/course/courseList.html',0,4),(5,'考勤列表','&#xe61c;','/Attendance_war/page/system/attendance/attendanceList.html',0,5),(6,'课堂考勤','&#xe61c;','/Attendance_war/page/system/attendance/attendance.html',0,5),(7,'教师列表','&#xe61c;','/Attendance_war/page/system/teacher/teacherList.html',0,6);
/*!40000 ALTER TABLE `sys_menu_children` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_student`
--

DROP TABLE IF EXISTS `sys_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_student` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学生id',
  `student_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `student_code` varchar(50) DEFAULT NULL COMMENT '学号',
  `student_phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_student`
--

LOCK TABLES `sys_student` WRITE;
/*!40000 ALTER TABLE `sys_student` DISABLE KEYS */;
INSERT INTO `sys_student` VALUES (11,'麦奇','201700208524','18276297824',4),(12,'伟哥','201700208508','110',5),(13,'杨彪','201700208524','18276427587',6),(14,'里奥','202000208524','13123424945',7),(15,'达芬奇','202001208524','18279389933',8),(16,'麦奇蜀叔','20170020852401','18276292785',2),(17,'阿姆斯特朗','10000000000','13113283933',11);
/*!40000 ALTER TABLE `sys_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_teacher`
--

DROP TABLE IF EXISTS `sys_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_teacher` (
  `teacher_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '教师id',
  `teacher_name` varchar(50) DEFAULT NULL COMMENT '教师姓名',
  `teacher_code` varchar(50) DEFAULT NULL COMMENT '教师编号',
  `teacher_phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `teacher_email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_teacher`
--

LOCK TABLES `sys_teacher` WRITE;
/*!40000 ALTER TABLE `sys_teacher` DISABLE KEYS */;
INSERT INTO `sys_teacher` VALUES (1,'莫智懿','17041','119','mo_fly@qq.com',3),(3,'周星星','9527','13788920488','zhouxingxing@outlook.com',5);
/*!40000 ALTER TABLE `sys_teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `login_account` varchar(50) DEFAULT NULL COMMENT '登入账号',
  `login_password` varchar(50) DEFAULT NULL COMMENT '登入密码',
  `role_type` int(11) DEFAULT NULL COMMENT '用户类型：1:管理员，2：教师，3：学生',
  `user_sex` int(11) DEFAULT NULL COMMENT '性别：0：女，1：男',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'admin','admin',1,1),(2,'20170020852401','123456',3,1),(3,'17041','123456',2,1),(5,'9527','123456',2,1),(6,'201700208524','123456',3,1),(7,'201700208508','123456',3,2),(8,'201700208524','123456',3,1),(9,'202001208524','123456',3,2),(10,'202000208524','123456',3,1),(11,'10000000000','123456',3,1);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-24 17:32:56
