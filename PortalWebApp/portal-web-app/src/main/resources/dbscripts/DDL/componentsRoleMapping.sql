CREATE TABLE portal.Components(
component_id int NOT NULL,
component_name varchar(22) NOT NULL, 
PRIMARY KEY (component_id)
);

INSERT INTO `portal`.`Components` (`component_id`,`component_name`) VALUES ('1', "Questions");
INSERT INTO `portal`.`Components` (`component_id`,`component_name`) VALUES ('2', "Blog");
INSERT INTO `portal`.`Components` (`component_id`,`component_name`) VALUES ('3', "Trainings");
INSERT INTO `portal`.`Components` (`component_id`,`component_name`) VALUES ('4', "Configurations");
INSERT INTO `portal`.`Components` (`component_id`,`component_name`) VALUES ('5', "Meetings Room");


CREATE TABLE portal.Components_Role_Mapping(
comp_role_map_id int NOT NULL auto_increment,
component_id int,
roleId int, 
is_comp_accessible boolean NOT NULL, 
PRIMARY KEY (comp_role_map_id),
KEY `fk_component_id_components_access` (`component_id`),
KEY `fk_roleId_components_access` (`roleId`),
CONSTRAINT `fk_component_id_components_access` FOREIGN KEY (`component_id`) REFERENCES `Components` (`component_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
CONSTRAINT `fk_roleId_components_access` FOREIGN KEY (`roleId`) REFERENCES `UserRole` (`roleId`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

COMMIT;
