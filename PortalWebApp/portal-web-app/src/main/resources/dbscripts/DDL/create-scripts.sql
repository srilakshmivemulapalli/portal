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
  `description` varchar(200) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `emailid` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`questionId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=big5;


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
