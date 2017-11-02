CREATE TABLE `portal`.`BookMeetingRoom` (
 `bookMeetingRoomId` int(11) NOT NULL AUTO_INCREMENT,
 `beginTime` datetime DEFAULT NULL,
 `createdDate` datetime DEFAULT NULL,
 `emailId` varchar(255) DEFAULT NULL,
 `endTime` datetime DEFAULT NULL,
 `headCount` int(11) NOT NULL,
 `meetingTitle` varchar(255) DEFAULT NULL,
 `bookingDate` datetime DEFAULT NULL,
 `locationId` int(11) DEFAULT NULL,
 `meetingRoomId` int(11) DEFAULT NULL,
 PRIMARY KEY (`bookMeetingRoomId`),
 KEY `FKhtnirl4jflypf78vrgm52irdd` (`locationId`),
 KEY `FKn9sa8x9lgfwuuutx6cvbfqhln` (`meetingRoomId`),
 CONSTRAINT `FKhtnirl4jflypf78vrgm52irdd` FOREIGN KEY (`locationId`) REFERENCES `Location` (`locationId`),
 CONSTRAINT `FKn9sa8x9lgfwuuutx6cvbfqhln` FOREIGN KEY (`meetingRoomId`) REFERENCES `MeetingRoom` (`meetingRoomId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;



CREATE TABLE `portal`.`MeetingRoom`  (
 `meetingRoomId` int(11) NOT NULL AUTO_INCREMENT,
 `description` varchar(255) DEFAULT NULL,
 `location` int(11) NOT NULL,
`beginTime` datetime DEFAULT NULL,
 `endTime` datetime DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
 PRIMARY KEY (`meetingRoomId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;



CREATE TABLE  `portal`.`Location` (
 `locationId` int(11) NOT NULL AUTO_INCREMENT,
 `locationName` varchar(255) DEFAULT NULL,
 PRIMARY KEY (`locationId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

