CREATE SCHEMA `school` DEFAULT CHARACTER SET utf8mb4 ;

DROP TABLE if exists `school`.`s_homework`;
CREATE TABLE `school`.`s_homework` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(20) NULL,
  `content` TEXT NULL,
  `create_time` datetime NOT NULL,
  `deadline`  datetime NULL,
  PRIMARY KEY (`id`));


DROP TABLE if exists `school`.`s_student`;
CREATE TABLE `school`.`s_student` (
  `id` BIGINT NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`));


DROP TABLE if exists `school`.`s_student_homework`;
CREATE TABLE `school`.`s_student_homework` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `student_id` BIGINT NOT NULL,
  `homework_id` BIGINT NOT NULL,
  `homework_title` VARCHAR(45) NOT NULL,
  `homework_content` TEXT NULL,
  `submit_time` datetime NOT NULL,
  PRIMARY KEY (`id`));

INSERT INTO `school`.`s_student_homework` (`id`, `student_id`, `homework_id`, `homework_title`, `homework_content`, `submit_time`) VALUES ('1', '1', '1', '作业', '完成如下SQL语句练习', '2020-03-03 12:00:00');

