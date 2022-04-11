-- ----------------------------
-- Table structure for User
-- ----------------------------
DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
  `uid` int NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `profile` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;


-- ----------------------------
-- Table structure for Topic
-- ----------------------------
DROP TABLE IF EXISTS `Topic`;
CREATE TABLE `Topic` (
  `topic_id` int NOT NULL,
  `topic_name` varchar(255) DEFAULT NULL,
  `parent_id` int DEFAULT NULL,
  PRIMARY KEY (`topic_id`),
  KEY `parent_id` (`parent_id`),
  CONSTRAINT `parent_id` FOREIGN KEY (`parent_id`) REFERENCES `Topic` (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;


-- ----------------------------
-- Table structure for Questions
-- ----------------------------
DROP TABLE IF EXISTS `Questions`;
CREATE TABLE `Questions` (
  `ques_id` int NOT NULL,
  `uid` int DEFAULT NULL,
  `topic_id` int DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `ques_body` varchar(255) DEFAULT NULL,
  `isSolved` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ques_id`),
  KEY `uid_ques` (`uid`),
  KEY `topic_id` (`topic_id`),
  CONSTRAINT `topic_id` FOREIGN KEY (`topic_id`) REFERENCES `Topic` (`topic_id`),
  CONSTRAINT `uid_ques` FOREIGN KEY (`uid`) REFERENCES `User` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;


-- ----------------------------
-- Table structure for Answers
-- ----------------------------
DROP TABLE IF EXISTS `Answers`;
CREATE TABLE `Answers` (
  `ans_id` int NOT NULL,
  `uid` int DEFAULT NULL,
  `ques_id` int DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `ans_body` varchar(255) DEFAULT NULL,
  `thumb_ups` int DEFAULT NULL,
  `isBest` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ans_id`),
  KEY `uid_ans` (`uid`),
  KEY `ques_id` (`ques_id`),
  CONSTRAINT `ques_id` FOREIGN KEY (`ques_id`) REFERENCES `Questions` (`ques_id`),
  CONSTRAINT `uid_ans` FOREIGN KEY (`uid`) REFERENCES `User` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;


-- ----------------------------
-- Table structure for Like
-- ----------------------------
DROP TABLE IF EXISTS `Like`;
CREATE TABLE `Like` (
  `uid` int NOT NULL,
  `ans_id` int NOT NULL,
  PRIMARY KEY (`uid`,`ans_id`),
  KEY `ans_id` (`ans_id`),
  CONSTRAINT `ans_id` FOREIGN KEY (`ans_id`) REFERENCES `Answers` (`ans_id`),
  CONSTRAINT `uid` FOREIGN KEY (`uid`) REFERENCES `User` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;