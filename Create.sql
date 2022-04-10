-- ----------------------------
-- Table structure for User
-- ----------------------------
DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
  `uid` int NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `profile` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;


-- ----------------------------
-- Table structure for Questions
-- ----------------------------
DROP TABLE IF EXISTS `Questions`;
CREATE TABLE `Questions` (
  `ques_id` int NOT NULL,
  `uid` int DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `ques_body` varchar(255) DEFAULT NULL,
  `isSolved` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ques_id`),
  KEY `uid_ques` (`uid`),
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
-- Table structure for Topic
-- ----------------------------
DROP TABLE IF EXISTS `Topic`;
CREATE TABLE `Topic` (
  `topic_id` int NOT NULL,
  `topic_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;


-- ----------------------------
-- Table structure for QuestionsBelong
-- ----------------------------
DROP TABLE IF EXISTS `QuestionsBelong`;
CREATE TABLE `QuestionsBelong` (
  `ques_id` int NOT NULL,
  `topic_id` int NOT NULL,
  PRIMARY KEY (`ques_id`,`topic_id`),
  KEY `topic_id` (`topic_id`),
  CONSTRAINT `ques_id2` FOREIGN KEY (`ques_id`) REFERENCES `Questions` (`ques_id`),
  CONSTRAINT `topic_id` FOREIGN KEY (`topic_id`) REFERENCES `Topic` (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
