INSERT INTO IncrementStudent VALUES (NULL);
SET sp_id = CONCAT('ST2021', LPAD(LAST_INSERT_ID(), 4, '0'));
SET sp_student_email = CONCAT(sp_id, '@colegiosprisma.edu');
/*------------------------------------------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS sp_generate_id;
DELIMITER $$
CREATE PROCEDURE sp_generate_id (IN sp_type varchar(50), OUT sp_id varchar(10))
BEGIN

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
		BEGIN
		GET DIAGNOSTICS CONDITION 1 @sqlstate = RETURNED_SQLSTATE, 
		 @errno = MYSQL_ERRNO, @text = MESSAGE_TEXT;
		SET @full_error = CONCAT("ERROR ", @errno, " (", @sqlstate, "): ", @text);
        ROLLBACK;
		SELECT @full_error;
		END;
	DECLARE EXIT HANDLER FOR SQLWARNING
		BEGIN
		GET DIAGNOSTICS CONDITION 1 @sqlstate = RETURNED_SQLSTATE, 
		 @errno = MYSQL_ERRNO, @text = MESSAGE_TEXT;
		SET @full_error = CONCAT("ERROR ", @errno, " (", @sqlstate, "): ", @text);
        ROLLBACK;
		SELECT @full_error;
		END;

	START TRANSACTION;
		IF sp_type = 'Student' THEN
			INSERT INTO increment_students VALUES (NULL);
			SET sp_id = CONCAT('S02021', LPAD(LAST_INSERT_ID(), 4, '0'));
            select sp_id;
		ELSEIF sp_type = 'Parent' THEN
			INSERT INTO increment_parents VALUES (NULL);
			SET sp_id = CONCAT('P02021', LPAD(LAST_INSERT_ID(), 4, '0'));
            select sp_id;
		ELSEIF sp_type = 'Teacher' THEN
			INSERT INTO increment_teachers VALUES (NULL);
			SET sp_id = CONCAT('T02021', LPAD(LAST_INSERT_ID(), 4, '0'));
            select sp_id;
		END IF;
	COMMIT;
END $$
DELIMITER ;

CALL sp_generate_id("Parent", @var);
select @var
/*------------------------------------------------------------------------------------------------------*/
DELIMITER $$
CREATE TRIGGER tg_user_insert
BEFORE INSERT ON `users`
FOR EACH ROW
BEGIN
	IF NEW.type = 'Student' THEN
		INSERT INTO increment_students VALUES (NULL);
		SET NEW.id = CONCAT('S02021', LPAD(LAST_INSERT_ID(), 4, '0'));
        SET NEW.username = CONCAT('S02021', LPAD(LAST_INSERT_ID(), 4, '0'));
    ELSEIF NEW.type = 'Parent' THEN
		INSERT INTO increment_parents VALUES (NULL);
		SET NEW.id = CONCAT('P02021', LPAD(LAST_INSERT_ID(), 4, '0'));
        SET NEW.username = CONCAT('P02021', LPAD(LAST_INSERT_ID(), 4, '0'));
    ELSEIF NEW.type = 'Teacher' THEN
		INSERT INTO increment_teachers VALUES (NULL);
        SET NEW.id = CONCAT('T02021', LPAD(LAST_INSERT_ID(), 4, '0'));
        SET NEW.username = CONCAT('T02021', LPAD(LAST_INSERT_ID(), 4, '0'));
    END IF;
END$$
DELIMITER ;

DROP TRIGGER IF EXISTS tg_user_insert;
/*------------------------------------------------------------------------------------------------------*/
DELIMITER $$
CREATE TRIGGER tg_role_insert
AFTER INSERT ON `users`
FOR EACH ROW
BEGIN
	IF NEW.type = 'Student' THEN
		INSERT INTO `users_roles` (`role_id`, `users_id`) VALUES (4, NEW.username);
    ELSEIF NEW.type = 'Parent' THEN
		INSERT INTO `users_roles` (`role_id`, `users_id`) VALUES (3, NEW.username);
    ELSEIF NEW.type = 'Teacher' THEN
		INSERT INTO `users_roles` (`role_id`, `users_id`) VALUES (2, NEW.username);
    END IF;
END$$
DELIMITER ;

DROP TRIGGER IF EXISTS tg_role_insert;
/*------------------------------------------------------------------------------------------------------*/
INSERT INTO `school_db`.`users`
(`id`,`given_names`,`first_last_name`,`second_last_name`,`document_type`,`document_number`,`birth_date`,`address`,`gender`,
`nationality`,`phone_number`,`email_address`,`type`,`password`)
VALUES
('1', 'Enzo Joao', 'Carrion', 'Escobar', 'DNI', '76360860', '2000-08-30', 'Lima', 'Masculino', 'Perú', '946653562', 'enz10.cr@gmail.com', 'Student',
'76360860');

INSERT INTO `school_db`.`users`
(`given_names`,`first_last_name`,`second_last_name`,`document_type`,`document_number`,`birth_date`,`address`,`gender`,
`nationality`,`phone_number`,`email_address`,`type`,`password`)
VALUES
('Andrea Amparo', 'Laureano', 'Cayetano', 'DNI', '73472267', '2000-06-15', 'Lima', 'Femenino', 'Perú', '96227666', 'andrea@gmail.com', 'Parent',
'73472267');

INSERT INTO `school_db`.`users`
(`given_names`,`first_last_name`,`second_last_name`,`document_type`,`document_number`,`birth_date`,`address`,`gender`,
`nationality`,`phone_number`,`email_address`,`type`,`password`)
VALUES
('Patrick Nick', 'Tirado', 'Suarez', 'DNI', '72145103', '2000-01-22', 'Lima', 'Masculino', 'Perú', '954554807', 'patrick@gmail.com', 'Teacher',
'72145103');
/*------------------------------------------------------------------------------------------------------*/
DELIMITER $$
CREATE TRIGGER tg_student_insert
BEFORE INSERT ON `students`
FOR EACH ROW
BEGIN
	SET NEW.student_email = CONCAT(NEW.id, '@colegiosprisma.edu');
END$$
DELIMITER ;

DROP TRIGGER IF EXISTS tg_student_insert;

INSERT INTO `school_db`.`students` (`id`,`parent_id`) VALUES ('S020210001', 'P020210001');

/*------------------------------------------------------------------------------------------------------*/
INSERT INTO `school_db`.`parents` (`id`) VALUES ('P020210001');
/*------------------------------------------------------------------------------------------------------*/
DELIMITER $$
CREATE TRIGGER tg_teacher_insert
BEFORE INSERT ON `teachers`
FOR EACH ROW
BEGIN
	SET NEW.institutional_email = CONCAT(NEW.id, '@colegiosprisma.edu');
END$$
DELIMITER ;

DROP TRIGGER IF EXISTS tg_teacher_insert;

INSERT INTO `school_db`.`teachers` (`id`) VALUES ('T020210001');

/*------------------------------------------------------------------------------------------------------*/





INSERT INTO `school_db`.`users`
(`id`,
`given_names`,
`first_last_name`,
`second_last_name`,
`document_type`,
`document_number`,
`birth_date`,
`address`,
`gender`,
`nationality`,
`phone_number`,
`email_address`,
`type`,
`username`,
`password`,
`status`)
VALUES
(<{id: }>,
<{given_names: }>,
<{first_last_name: }>,
<{second_last_name: }>,
<{document_type: }>,
<{document_number: }>,
<{birth_date: }>,
<{address: }>,
<{gender: }>,
<{nationality: }>,
<{phone_number: }>,
<{email_address: }>,
<{type: }>,
<{username: }>,
<{password: }>,
<{status: 1}>);












