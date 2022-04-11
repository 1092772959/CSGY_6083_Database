DROP TABLE IF EXISTS `Like`;
DROP TABLE IF EXISTS `Answers`;
DROP TABLE IF EXISTS `Questions`;
DROP TABLE IF EXISTS `User`;
DROP TABLE IF EXISTS `Topic`;

-- Table structure for User
-- ----------------------------
CREATE TABLE `User` (
  `uid` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `profile` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL
);

-- ----------------------------
-- Table structure for Topic
-- ----------------------------
CREATE TABLE `Topic` (
  `topic_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `topic_name` varchar(255) DEFAULT NULL,
  `parent_id` int DEFAULT NULL,
  CONSTRAINT `parent_id` FOREIGN KEY (`parent_id`) REFERENCES `Topic` (`topic_id`)
);

-- ----------------------------
-- Table structure for Questions
-- ----------------------------
CREATE TABLE `Questions` (
  `ques_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `uid` int DEFAULT NULL,
  `topic_id` int DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `ques_body` varchar(255) DEFAULT NULL,
  `isSolved` tinyint(1) DEFAULT NULL,
  CONSTRAINT `topic_id` FOREIGN KEY (`topic_id`) REFERENCES `Topic` (`topic_id`),
  CONSTRAINT `uid_ques` FOREIGN KEY (`uid`) REFERENCES `User` (`uid`)
);

-- ----------------------------
-- Table structure for Answers
-- ----------------------------
CREATE TABLE `Answers` (
  `ans_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `uid` int DEFAULT NULL,
  `ques_id` int DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `ans_body` varchar(255) DEFAULT NULL,
  `thumb_ups` int DEFAULT NULL,
  `isBest` tinyint(1) DEFAULT NULL,
  CONSTRAINT `ques_id` FOREIGN KEY (`ques_id`) REFERENCES `Questions` (`ques_id`),
  CONSTRAINT `uid_ans` FOREIGN KEY (`uid`) REFERENCES `User` (`uid`) 
);

-- ----------------------------
-- Table structure for Like
-- ----------------------------
CREATE TABLE `Like` (
  `uid` int NOT NULL,
  `ans_id` int NOT NULL,
  PRIMARY KEY (`uid`,`ans_id`),
  CONSTRAINT `ans_id` FOREIGN KEY (`ans_id`) REFERENCES `Answers` (`ans_id`),
  CONSTRAINT `uid` FOREIGN KEY (`uid`) REFERENCES `User` (`uid`)
);

SET FOREIGN_KEY_CHECKS = 1;