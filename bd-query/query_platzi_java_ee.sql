En terminal...

mysql -u root -p  # Solicita la contraseña de usuario root.
mysql>
	CREATE DATABASE platzi_java_ee;  -- Crear nueva BD.
	CREATE USER platziprofesores@localhost IDENTIFIED BY 'PlatziProfesores';  -- Crear el usuario user_wp con contraseña PlatziProfesores.
	GRANT ALL PRIVILEGES ON platzi_java_ee.* TO platziprofesores@localhost;  -- Dar acceso al usuario de la nueva BD creada.
	FLUSH PRIVILEGES;  -- Informar de los nuevos privilegios establecidos.
	exit 


En un archivo query.sql para ejecutar en MySQL Workbench...

CREATE SCHEMA `platzi_java_ee` DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
-- ALTER SCHEMA `platzi_java_ee` DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;

CREATE TABLE `platzi_java_ee`.`teacher` (
  `id_teacher` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) CHARACTER SET 'utf8' NOT NULL,
  `avatar` VARCHAR(250) CHARACTER SET 'utf8' NULL,
  PRIMARY KEY (`id_teacher`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `platzi_java_ee`.`course` (
  `id_course` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) CHARACTER SET 'utf8' NOT NULL,
  `themes` TEXT CHARACTER SET 'utf8' NULL,
  `project` VARCHAR(250) CHARACTER SET 'utf8' NULL,
  `id_teacher` INT NOT NULL,
  PRIMARY KEY (`id_course`),
  INDEX `fk_course__teacher_idx` (`id_teacher`),
  CONSTRAINT `fk_course__teacher`
    FOREIGN KEY (`id_teacher`)
    REFERENCES `platzi_java_ee`.`teacher` (`id_teacher`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `platzi_java_ee`.`social_media` (
  `id_social_media` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) CHARACTER SET 'utf8' NOT NULL,
  `icon` VARCHAR(250) CHARACTER SET 'utf8' NULL,
  PRIMARY KEY (`id_social_media`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `platzi_java_ee`.`teacher_social_media` (
  `id_teacher_social_media` INT NOT NULL AUTO_INCREMENT,
  `id_teacher` INT NOT NULL,
  `id_social_media` INT NOT NULL,
  `nickname` VARCHAR(250) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`id_teacher_social_media`),
  INDEX `fk_teacher_social_media__teacher_idx` (`id_teacher`),
  INDEX `fk_teacher_social_media__social_media_idx` (`id_social_media`),
  CONSTRAINT `fk_teacher_social_media__teacher`
    FOREIGN KEY (`id_teacher`)
    REFERENCES `platzi_java_ee`.`teacher` (`id_teacher`),
  CONSTRAINT `fk_teacher_social_media__social_media`
    FOREIGN KEY (`id_social_media`)
    REFERENCES `platzi_java_ee`.`social_media` (`id_social_media`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data
INSERT INTO platzi_java_ee.teacher VALUES (1,'Anahi Salgado','Avatar'),(2,'Freddy Vega','Avatar'),(3,'Leonidas Esteban','Avatar');
INSERT INTO platzi_java_ee.course VALUES (1, 'Java Avanzado', 'Tema 1', 'Rest API', 1),(2, 'Curso Definitivo de Android', '* Qué es Android', 'Platzigram', 1);
INSERT INTO platzi_java_ee.social_media VALUES (1,'twitter','avatar'),(3,'facebook','avatar');
INSERT INTO platzi_java_ee.teacher_social_media VALUES (1,1,1,'@anncode'),(2,1,3,'Anncode');

-- Consultar...
SELECT * FROM platzi_java_ee.teacher;
SELECT * FROM platzi_java_ee.course;
SELECT * FROM platzi_java_ee.social_media;
SELECT * FROM platzi_java_ee.teacher_social_media;

DELETE FROM platzi_java_ee.teacher_social_media WHERE id_teacher_social_media = 6;

UPDATE platzi_java_ee.teacher SET id_teacher = 3 WHERE name = 'Leonidas Esteban';