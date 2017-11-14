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

select * from portal.Components;

select * from portal.Components_Role_Mapping;

select * from portal.userRole;

INSERT INTO `portal`.`Components_Role_Mapping` (`comp_role_map_id`, `component_id`, `roleId`, `is_comp_accessible`) VALUES ('1', '1', '5', '1');
INSERT INTO `portal`.`Components_Role_Mapping` (`comp_role_map_id`, `component_id`, `roleId`, `is_comp_accessible`) VALUES ('2', '1', '6', '1');
INSERT INTO `portal`.`Components_Role_Mapping` (`comp_role_map_id`, `component_id`, `roleId`, `is_comp_accessible`) VALUES ('3', '2', '5', '1');
INSERT INTO `portal`.`Components_Role_Mapping` (`comp_role_map_id`, `component_id`, `roleId`, `is_comp_accessible`) VALUES ('4', '2', '6', '1');
INSERT INTO `portal`.`Components_Role_Mapping` (`comp_role_map_id`, `component_id`, `roleId`, `is_comp_accessible`) VALUES ('5', '3', '5', '1');
INSERT INTO `portal`.`Components_Role_Mapping` (`comp_role_map_id`, `component_id`, `roleId`, `is_comp_accessible`) VALUES ('6', '3', '6', '1');
INSERT INTO `portal`.`Components_Role_Mapping` (`comp_role_map_id`, `component_id`, `roleId`, `is_comp_accessible`) VALUES ('7', '4', '5', '1');
INSERT INTO `portal`.`Components_Role_Mapping` (`comp_role_map_id`, `component_id`, `roleId`, `is_comp_accessible`) VALUES ('8', '4', '6', '0');
INSERT INTO `portal`.`Components_Role_Mapping` (`comp_role_map_id`, `component_id`, `roleId`, `is_comp_accessible`) VALUES ('9', '5', '5', '1');
INSERT INTO `portal`.`Components_Role_Mapping` (`comp_role_map_id`, `component_id`, `roleId`, `is_comp_accessible`) VALUES ('10', '5', '6', '1');


COMMIT;
