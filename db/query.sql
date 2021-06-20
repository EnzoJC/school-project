INSERT INTO roles (`name`) VALUES ('ROLE_ADMIN');
INSERT INTO roles (`name`) VALUES ('ROLE_TEACHER');
INSERT INTO roles (`name`) VALUES ('ROLE_PARENT');
INSERT INTO roles (`name`) VALUES ('ROLE_STUDENT');

INSERT INTO `school_db`.`permissions` (`name`) VALUES ('update');
INSERT INTO `school_db`.`permissions` (`name`) VALUES ('write');
INSERT INTO `school_db`.`permissions` (`name`) VALUES ('read');
INSERT INTO `school_db`.`permissions` (`name`) VALUES ('delete');

INSERT INTO `school_db`.`roles_permissions` (`role_id`, `permission_id`) VALUES (2, 1);
INSERT INTO `school_db`.`roles_permissions` (`role_id`, `permission_id`) VALUES (2, 2);
INSERT INTO `school_db`.`roles_permissions` (`role_id`, `permission_id`) VALUES (2, 3);

INSERT INTO `school_db`.`roles_permissions` (`role_id`, `permission_id`) VALUES (1, 1);
INSERT INTO `school_db`.`roles_permissions` (`role_id`, `permission_id`) VALUES (1, 2);
INSERT INTO `school_db`.`roles_permissions` (`role_id`, `permission_id`) VALUES (1, 3);
INSERT INTO `school_db`.`roles_permissions` (`role_id`, `permission_id`) VALUES (1, 4);

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