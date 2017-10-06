CREATE TABLE `portal`.`UserRole` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(100) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=big5;

CREATE TABLE `portal`.`User` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `emailid` varchar(45) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `loginDate` datetime DEFAULT NULL,
  `activeStatus` varchar(45)  DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  KEY `fk_roleId_user_idx` (`roleId`),
  CONSTRAINT `fk_roleId_user` FOREIGN KEY (`roleId`) REFERENCES `UserRole` (`roleId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=big5;

CREATE TABLE `portal`.`Categories` (
  `categoryId` int(11) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(100) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  PRIMARY KEY (`categoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=big5;

CREATE TABLE `Questionaries` (
  `questionId` int(11) NOT NULL AUTO_INCREMENT,
  `categoryId` int(11) DEFAULT NULL,
  `question` varchar(100) DEFAULT NULL,
  `description` varchar(5000) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `emailid` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`questionId`),
  KEY `fk_categories_id_idx` (`categoryId`),
  CONSTRAINT `fk_categories_id` FOREIGN KEY (`categoryId`) REFERENCES `Categories` (`categoryId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=big5;



CREATE TABLE `QuestionReplies` (
  `replyId` int(11) NOT NULL AUTO_INCREMENT,
  `questId` int(11) DEFAULT NULL,
  `replyDescription` varchar(5000) DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `emailid` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`replyId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=big5;




CREATE TABLE `portal`.`Blogs` (
  `blogsId` int(11) NOT NULL AUTO_INCREMENT,
  `categoryId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `description` blob,
  `path` varchar(145) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  PRIMARY KEY (`blogsId`),
  KEY `fk_categoryid_blogs_idx` (`categoryId`),
  CONSTRAINT `fk_categoryid_blogs` FOREIGN KEY (`categoryId`) REFERENCES `Categories` (`categoryId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=big5;

ALTER TABLE `portal`.`User` 
ADD COLUMN `createDate` DATETIME NULL DEFAULT NULL AFTER `roleId`;

ALTER TABLE `portal`.`User` 
ADD COLUMN `image` VARCHAR(200) NULL DEFAULT NULL AFTER `createDate`;

CREATE TABLE `portal`.`Trainings` (
  `trainingId` int(11) NOT NULL AUTO_INCREMENT,
  `trainingTitle` varchar(100) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `trainerName` varchar(100) DEFAULT NULL,
  `trainingDate` datetime DEFAULT NULL,
  `trainingStatus` varchar(100) DEFAULT NULL,
  `trainingType` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`trainingId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=big5;

CREATE TABLE `portal`.`QuestionComment` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT,
  `questionsId` int(11) DEFAULT NULL,
  `commentdescription` varchar(1000) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `emailId` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`commentId`),
  KEY `fk_questionId_QuestionComment_idx` (`questionId`),
  CONSTRAINT `fk_questionId_QuestionComment` FOREIGN KEY (`questionId`) REFERENCES `Questionaries` (`questionId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=big5;


CREATE TABLE `portal`.`QuestionReplyComment` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT,
  `repliesId` int(11) DEFAULT NULL,
  `commentdescription` varchar(1000) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `emailId` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`commentId`),
  KEY `fk_replyId_QuestionReplyComment_idx` (`replyId`),
  CONSTRAINT `fk_replyId_QuestionReplyComment` FOREIGN KEY (`replyId`) REFERENCES `QuestionReplies` (`replyId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=big5;

CREATE TABLE `portal`.`Course` (
  `courseId` int(11) NOT NULL AUTO_INCREMENT,
  `courseName` varchar(100) DEFAULT NULL,
  `trainingid` int(11) DEFAULT NULL,
  PRIMARY KEY (`courseId`),
  KEY `fk_trainingid_course_idx` (`trainingid`),
  CONSTRAINT `fk_trainingid_course` FOREIGN KEY (`trainingid`) REFERENCES `Trainings` (`trainingid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=big5;

CREATE TABLE `portal`.`TrainingRequest` (
  `trainingRequestId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `courseId` int(11) DEFAULT NULL,  
  `description` varchar(100) DEFAULT NULL,
  `requestedDate` datetime DEFAULT NULL,
   PRIMARY KEY (`trainingRequestId`),
  KEY `fk_user_id_idx` (`userId`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`userId`)   REFERENCES `User` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  KEY `fk_course_id_idx` (`courseId`),
  CONSTRAINT `fk_course_id` FOREIGN KEY (`courseId`) REFERENCES `Course` (`courseId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=big5;

CREATE TABLE `portal`.`TrainingFeedBack` (
  `trainingFeedBackId` int(11) NOT NULL AUTO_INCREMENT,
  `trainingId` int(11) DEFAULT NULL, 
  `feedback` varchar(200) DEFAULT NULL,
  `rating` varchar(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  PRIMARY KEY (`trainingFeedBackId`),
  KEY `fk_trainings_id_idx` (`trainingId`),
  CONSTRAINT `fk_trainings_id` FOREIGN KEY (`trainingId`)   REFERENCES `Trainings` (`trainingId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=big5;
