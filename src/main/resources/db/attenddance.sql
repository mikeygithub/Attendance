-- MySQL dump 10.13  Distrib 5.7.27, for Linux (x86_64)
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
-- Table structure for table `bus_attendance`
--

DROP TABLE IF EXISTS `bus_attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bus_attendance` (
  `attendance_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `attendance_type` int(11) DEFAULT NULL COMMENT '0：缺席，1:迟到，2：早退',
  `attendance_time` datetime DEFAULT NULL COMMENT '时间',
  `attendance_stu_id` int(11) DEFAULT NULL COMMENT '学生外键',
  `attendance_cas_id` int(11) DEFAULT NULL COMMENT '课程外键',
  PRIMARY KEY (`attendance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_attendance`
--

LOCK TABLES `bus_attendance` WRITE;
/*!40000 ALTER TABLE `bus_attendance` DISABLE KEYS */;
/*!40000 ALTER TABLE `bus_attendance` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_admin`
--

LOCK TABLES `sys_admin` WRITE;
/*!40000 ALTER TABLE `sys_admin` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_classes`
--

LOCK TABLES `sys_classes` WRITE;
/*!40000 ALTER TABLE `sys_classes` DISABLE KEYS */;
INSERT INTO `sys_classes` VALUES (1,'软件1班','SF001'),(2,'软件2班','SF002');
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_college`
--

LOCK TABLES `sys_college` WRITE;
/*!40000 ALTER TABLE `sys_college` DISABLE KEYS */;
INSERT INTO `sys_college` VALUES (1,'大数据与软件工程学院','CO001');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_course`
--

LOCK TABLES `sys_course` WRITE;
/*!40000 ALTER TABLE `sys_course` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,'学生管理','&#xe630;',' ',0,'1'),(2,'班级管理','&#xe630;',' ',0,'1'),(3,'学院管理','&#xe630;',' ',0,'1'),(4,'宿舍楼管理','&#xe630;',' ',0,'1'),(5,'宿舍管理','&#xe630;',' ',0,'1'),(6,'宿管员管理','&#xe630;',' ',0,'1'),(7,'辅导员管理','&#xe630;',' ',0,'1'),(8,'晚归记录管理','&#xe630;',' ',0,'1'),(9,'晚归记录管理','&#xe630;',' ',0,'2'),(10,'晚归记录管理','&#xe630;',' ',0,'3'),(11,'学生管理','&#xe630;',' ',0,'2');
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu_children`
--

LOCK TABLES `sys_menu_children` WRITE;
/*!40000 ALTER TABLE `sys_menu_children` DISABLE KEYS */;
INSERT INTO `sys_menu_children` VALUES (1,'学生列表','&#xe61c;','/Attendance_war/page/system/student/studentList.html',0,1),(2,'班级列表','&#xe61c;','/Attendance_war/page/system/class/classList.html',0,2),(3,'学院列表','&#xe61c;','/Attendance_war/page/system/colleges/collegesList.html',0,3),(4,'宿舍列表','&#xe61c;','/Attendance_war/page/system/dorm/dormList.html',0,4),(5,'宿舍楼管理','&#xe61c;','/Attendance_war/page/system/dormitory/dormitoryList.html',0,5),(6,'宿管员列表','&#xe61c;','/Attendance_war/page/system/houseparent/houseparentList.html',0,6),(7,'辅导员列表','&#xe61c;','/Attendance_war/page/system/instructor/instructorList.html',0,7),(8,'记录列表','&#xe61c;','/Attendance_war/page/system/latereturn/latereturnList.html',0,8),(9,'记录列表','&#xe61c;','/Attendance_war/page/system/latereturn/latereturnList.html',0,9),(10,'记录列表','&#xe61c;','/Attendance_war/page/system/latereturn/latereturnList.html',0,10),(11,'学生列表','&#xe61c;','/Attendance_war/page/system/student/studentList.html',0,11);
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
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_student`
--

LOCK TABLES `sys_student` WRITE;
/*!40000 ALTER TABLE `sys_student` DISABLE KEYS */;
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
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_teacher`
--

LOCK TABLES `sys_teacher` WRITE;
/*!40000 ALTER TABLE `sys_teacher` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
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

-- Dump completed on 2019-11-20 11:25:32
