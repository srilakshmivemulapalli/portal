
ALTER TABLE `portal`.`Blogs` 
DROP FOREIGN KEY `fk_categoryid_blogs`;

ALTER TABLE `portal`.`Blogs` 
DROP COLUMN `categoryId`,
DROP INDEX `fk_categoryid_blogs_idx` ;

ALTER TABLE `portal`.`Blogs` 
ADD COLUMN `userMailId` VARCHAR(45) NOT NULL AFTER `createdDate`;

ALTER TABLE `portal`.`Blogs` 
ADD COLUMN `title` VARCHAR(45) NOT NULL AFTER `blogsId`;