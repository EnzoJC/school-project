CREATE DATABASE school_db;

-- tables
-- Table: classes
CREATE TABLE classes
(
    class_id     int         NOT NULL AUTO_INCREMENT,
    section_id   int         NOT NULL,
    grade_id     int         NOT NULL,
    tutor_id     varchar(10) NOT NULL,
    classroom_id int         NOT NULL,
    CONSTRAINT classes_pk PRIMARY KEY (class_id)
);

-- Table: classrooms
CREATE TABLE classrooms
(
    classroom_id int         NOT NULL AUTO_INCREMENT,
    capacity     int         NOT NULL,
    name         varchar(50) NOT NULL,
    CONSTRAINT classrooms_pk PRIMARY KEY (classroom_id)
);

-- Table: courses
CREATE TABLE courses
(
    course_id      int         NOT NULL AUTO_INCREMENT,
    name           varchar(50) NOT NULL,
    academic_hours int         NOT NULL,
    CONSTRAINT courses_pk PRIMARY KEY (course_id)
);

-- Table: days
CREATE TABLE days
(
    day_id int         NOT NULL AUTO_INCREMENT,
    name   varchar(12) NOT NULL,
    UNIQUE INDEX name_ak_1 (name),
    CONSTRAINT days_pk PRIMARY KEY (day_id)
);

-- Table: document_types
CREATE TABLE document_types
(
    document_type_id int         NOT NULL AUTO_INCREMENT,
    name             varchar(50) NOT NULL,
    UNIQUE INDEX name_ak_1 (name),
    CONSTRAINT document_types_pk PRIMARY KEY (document_type_id)
);

-- Table: enrollments
CREATE TABLE enrollments
(
    enrollment_id     int         NOT NULL AUTO_INCREMENT,
    school_year_id    int         NOT NULL,
    student_id        varchar(10) NOT NULL,
    enrollment_status boolean     NOT NULL,
    grade_id          int         NOT NULL,
    class_id          int         NULL,
    CONSTRAINT enrollments_pk PRIMARY KEY (enrollment_id)
);

-- Table: genders
CREATE TABLE genders
(
    gender_id int         NOT NULL AUTO_INCREMENT,
    name      varchar(50) NOT NULL,
    UNIQUE INDEX name_ak_1 (name),
    CONSTRAINT genders_pk PRIMARY KEY (gender_id)
);

-- Table: grades
CREATE TABLE grades
(
    grade_id    int         NOT NULL AUTO_INCREMENT,
    `order`     int         NOT NULL,
    name        varchar(20) NOT NULL,
    description varchar(50) NOT NULL,
    level_id    int         NOT NULL,
    UNIQUE INDEX grades_ak_1 (`order`),
    CONSTRAINT grades_pk PRIMARY KEY (grade_id)
);

-- Table: grades_courses
CREATE TABLE grades_courses
(
    grade_id  int NOT NULL,
    course_id int NOT NULL,
    CONSTRAINT grades_courses_pk PRIMARY KEY (grade_id, course_id)
);

-- Table: increment_parents
CREATE TABLE increment_parents
(
    increment_parent_id int NOT NULL AUTO_INCREMENT,
    CONSTRAINT increment_parents_pk PRIMARY KEY (increment_parent_id)
);

-- Table: increment_students
CREATE TABLE increment_students
(
    increment_student_id int NOT NULL AUTO_INCREMENT,
    CONSTRAINT increment_students_pk PRIMARY KEY (increment_student_id)
);

-- Table: increment_teachers
CREATE TABLE increment_teachers
(
    increment_teacher_id int NOT NULL AUTO_INCREMENT,
    CONSTRAINT increment_teachers_pk PRIMARY KEY (increment_teacher_id)
);

-- Table: increment_users
CREATE TABLE increment_users
(
    increment_user_id int NOT NULL AUTO_INCREMENT,
    CONSTRAINT increment_users_pk PRIMARY KEY (increment_user_id)
);

-- Table: levels
CREATE TABLE levels
(
    level_id    int         NOT NULL AUTO_INCREMENT,
    name        varchar(10) NOT NULL,
    description varchar(50) NOT NULL,
    UNIQUE INDEX name_ak_1 (name),
    CONSTRAINT levels_pk PRIMARY KEY (level_id)
);

-- Table: nationalities
CREATE TABLE nationalities
(
    nationality_id int         NOT NULL AUTO_INCREMENT,
    name           varchar(50) NOT NULL,
    UNIQUE INDEX name_ak_1 (name),
    CONSTRAINT nationalities_pk PRIMARY KEY (nationality_id)
);

-- Table: parents
CREATE TABLE parents
(
    parent_id  varchar(10) NOT NULL,
    occupation varchar(50) NULL,
    CONSTRAINT parents_pk PRIMARY KEY (parent_id)
);

-- Table: payments_details
CREATE TABLE payments_details
(
    payment_detail_id int     NOT NULL AUTO_INCREMENT,
    billing_id        int     NULL,
    pay_date          date    NULL,
    pay_id            int     NOT NULL,
    enrollment_id     int     NOT NULL,
    payment_status        boolean NOT NULL,
    expiration_date   date    NULL,
    CONSTRAINT payments_details_pk PRIMARY KEY (payment_detail_id)
);

-- Table: pays
CREATE TABLE pays
(
    pay_id          int            NOT NULL AUTO_INCREMENT,
    description     varchar(50)    NOT NULL,
    amount          decimal(10, 2) NOT NULL,
    start_date      date           NULL,
    expiration_date date           NULL,
    CONSTRAINT pays_pk PRIMARY KEY (pay_id)
);

-- Table: privileges
CREATE TABLE privileges
(
    privilege_id int         NOT NULL AUTO_INCREMENT,
    name         varchar(50) NOT NULL,
    UNIQUE INDEX name_ak_1 (name),
    CONSTRAINT privileges_pk PRIMARY KEY (privilege_id)
);

-- Table: relationships
CREATE TABLE relationships
(
    relationship_id int         NOT NULL AUTO_INCREMENT,
    name            varchar(50) NOT NULL,
    UNIQUE INDEX name_ak_1 (name),
    CONSTRAINT relationships_pk PRIMARY KEY (relationship_id)
);

-- Table: roles
CREATE TABLE roles
(
    role_id int         NOT NULL AUTO_INCREMENT,
    name    varchar(50) NOT NULL,
    UNIQUE INDEX name_ak_1 (name),
    CONSTRAINT roles_pk PRIMARY KEY (role_id)
);

-- Table: roles_privileges
CREATE TABLE roles_privileges
(
    role_id      int NOT NULL,
    privilege_id int NOT NULL,
    CONSTRAINT roles_privileges_pk PRIMARY KEY (role_id, privilege_id)
);

-- Table: schedules
CREATE TABLE schedules
(
    schedule_id int  NOT NULL AUTO_INCREMENT,
    start_time  time NOT NULL,
    end_time    time NOT NULL,
    day_id      int  NOT NULL,
    CONSTRAINT schedules_pk PRIMARY KEY (schedule_id)
);

-- Table: school_years
CREATE TABLE school_years
(
    school_year_id int  NOT NULL AUTO_INCREMENT,
    year           int  NOT NULL,
    start_date     date NOT NULL,
    finish_date    date NOT NULL,
    CONSTRAINT school_years_pk PRIMARY KEY (school_year_id)
);

-- Table: sections
CREATE TABLE sections
(
    section_id int         NOT NULL AUTO_INCREMENT,
    name       varchar(50) NOT NULL,
    CONSTRAINT sections_pk PRIMARY KEY (section_id)
);

-- Table: sessions
CREATE TABLE sessions
(
    session_id int         NOT NULL AUTO_INCREMENT,
    class_id   int         NOT NULL,
    course_id  int         NOT NULL,
    teacher_id varchar(10) NOT NULL,
    name       varchar(50) NOT NULL,
    CONSTRAINT sessions_pk PRIMARY KEY (session_id)
);

-- Table: sessions_schedules
CREATE TABLE sessions_schedules
(
    schedule_id int NOT NULL,
    session_id  int NOT NULL,
    CONSTRAINT sessions_schedules_pk PRIMARY KEY (schedule_id, session_id)
);

-- Table: students
CREATE TABLE students
(
    student_id      varchar(10) NOT NULL,
    student_email   varchar(50) NOT NULL,
    parent_id       varchar(10) NOT NULL,
    relationship_id int         NOT NULL,
    UNIQUE INDEX student_email_ak_1 (student_email),
    CONSTRAINT students_pk PRIMARY KEY (student_id)
);

-- Table: teacher_courses
CREATE TABLE teacher_courses
(
    teacher_id varchar(10) NOT NULL,
    course_id  int         NOT NULL,
    CONSTRAINT teacher_courses_pk PRIMARY KEY (teacher_id, course_id)
);

-- Table: teachers
CREATE TABLE teachers
(
    teacher_id          varchar(10) NOT NULL,
    institutional_email varchar(50) NOT NULL,
    UNIQUE INDEX inst_email_ak_1 (institutional_email),
    CONSTRAINT teachers_pk PRIMARY KEY (teacher_id)
);

-- Table: users
CREATE TABLE users
(
    user_id          varchar(10)  NOT NULL,
    given_names      varchar(50)  NOT NULL,
    first_last_name  varchar(50)  NOT NULL,
    second_last_name varchar(50)  NOT NULL,
    document_type_id int          NOT NULL,
    document_number  varchar(20)  NOT NULL,
    birth_date       date         NOT NULL,
    address          varchar(50)  NOT NULL,
    gender_id        int          NOT NULL,
    nationality_id   int          NOT NULL,
    phone            varchar(20)  NULL,
    email            varchar(50)  NULL,
    username         varchar(10)  NOT NULL,
    password         varchar(100) NOT NULL,
    type             varchar(50)  NOT NULL,
    is_active        boolean      NOT NULL DEFAULT 1,
    UNIQUE INDEX document_number_ak_1 (document_number),
    UNIQUE INDEX phone_ak_2 (phone),
    UNIQUE INDEX email_ak_3 (email),
    UNIQUE INDEX username_ak_4 (username),
    CONSTRAINT users_pk PRIMARY KEY (user_id)
);

-- Table: users_roles
CREATE TABLE users_roles
(
    role_id int         NOT NULL,
    user_id varchar(10) NOT NULL,
    CONSTRAINT users_roles_pk PRIMARY KEY (role_id, user_id)
);

-- foreign keys
-- Reference: class_teacher (table: classes)
ALTER TABLE classes
    ADD CONSTRAINT class_teacher FOREIGN KEY class_teacher (tutor_id)
        REFERENCES teachers (teacher_id);

-- Reference: classes_classroom (table: classes)
ALTER TABLE classes
    ADD CONSTRAINT classes_classroom FOREIGN KEY classes_classroom (classroom_id)
        REFERENCES classrooms (classroom_id);

-- Reference: classes_grades (table: classes)
ALTER TABLE classes
    ADD CONSTRAINT classes_grades FOREIGN KEY classes_grades (grade_id)
        REFERENCES grades (grade_id);

-- Reference: classes_section (table: classes)
ALTER TABLE classes
    ADD CONSTRAINT classes_section FOREIGN KEY classes_section (section_id)
        REFERENCES sections (section_id);

-- Reference: enrollments_class (table: enrollments)
ALTER TABLE enrollments
    ADD CONSTRAINT enrollments_class FOREIGN KEY enrollments_class (class_id)
        REFERENCES classes (class_id);

-- Reference: enrollments_grade (table: enrollments)
ALTER TABLE enrollments
    ADD CONSTRAINT enrollments_grade FOREIGN KEY enrollments_grade (grade_id)
        REFERENCES grades (grade_id);

-- Reference: enrollments_school_years (table: enrollments)
ALTER TABLE enrollments
    ADD CONSTRAINT enrollments_school_years FOREIGN KEY enrollments_school_years (school_year_id)
        REFERENCES school_years (school_year_id);

-- Reference: enrollments_students (table: enrollments)
ALTER TABLE enrollments
    ADD CONSTRAINT enrollments_students FOREIGN KEY enrollments_students (student_id)
        REFERENCES students (student_id);

-- Reference: gc_course (table: grades_courses)
ALTER TABLE grades_courses
    ADD CONSTRAINT gc_course FOREIGN KEY gc_course (course_id)
        REFERENCES courses (course_id);

-- Reference: gc_grade (table: grades_courses)
ALTER TABLE grades_courses
    ADD CONSTRAINT gc_grade FOREIGN KEY gc_grade (grade_id)
        REFERENCES grades (grade_id);

-- Reference: grades_levels (table: grades)
ALTER TABLE grades
    ADD CONSTRAINT grades_levels FOREIGN KEY grades_levels (level_id)
        REFERENCES levels (level_id);

-- Reference: parent_user (table: parents)
ALTER TABLE parents
    ADD CONSTRAINT parent_user FOREIGN KEY parent_user (parent_id)
        REFERENCES users (user_id);

-- Reference: payments_details_enrollment (table: payments_details)
ALTER TABLE payments_details
    ADD CONSTRAINT payments_details_enrollment FOREIGN KEY payments_details_enrollment (enrollment_id)
        REFERENCES enrollments (enrollment_id);

-- Reference: payments_details_pay (table: payments_details)
ALTER TABLE payments_details
    ADD CONSTRAINT payments_details_pay FOREIGN KEY payments_details_pay (pay_id)
        REFERENCES pays (pay_id);

-- Reference: rp_privilege (table: roles_privileges)
ALTER TABLE roles_privileges
    ADD CONSTRAINT rp_privilege FOREIGN KEY rp_privilege (privilege_id)
        REFERENCES privileges (privilege_id);

-- Reference: rp_role (table: roles_privileges)
ALTER TABLE roles_privileges
    ADD CONSTRAINT rp_role FOREIGN KEY rp_role (role_id)
        REFERENCES roles (role_id);

-- Reference: sc_schedule (table: sessions_schedules)
ALTER TABLE sessions_schedules
    ADD CONSTRAINT sc_schedule FOREIGN KEY sc_schedule (schedule_id)
        REFERENCES schedules (schedule_id);

-- Reference: sc_session (table: sessions_schedules)
ALTER TABLE sessions_schedules
    ADD CONSTRAINT sc_session FOREIGN KEY sc_session (session_id)
        REFERENCES sessions (session_id);

-- Reference: schedules_day (table: schedules)
ALTER TABLE schedules
    ADD CONSTRAINT schedules_day FOREIGN KEY schedules_day (day_id)
        REFERENCES days (day_id);

-- Reference: sessions_class (table: sessions)
ALTER TABLE sessions
    ADD CONSTRAINT sessions_class FOREIGN KEY sessions_class (class_id)
        REFERENCES classes (class_id);

-- Reference: sessions_course (table: sessions)
ALTER TABLE sessions
    ADD CONSTRAINT sessions_course FOREIGN KEY sessions_course (course_id)
        REFERENCES courses (course_id);

-- Reference: sessions_teacher (table: sessions)
ALTER TABLE sessions
    ADD CONSTRAINT sessions_teacher FOREIGN KEY sessions_teacher (teacher_id)
        REFERENCES teachers (teacher_id);

-- Reference: student_user (table: students)
ALTER TABLE students
    ADD CONSTRAINT student_user FOREIGN KEY student_user (student_id)
        REFERENCES users (user_id);

-- Reference: students_parent (table: students)
ALTER TABLE students
    ADD CONSTRAINT students_parent FOREIGN KEY students_parent (parent_id)
        REFERENCES parents (parent_id);

-- Reference: students_relationships (table: students)
ALTER TABLE students
    ADD CONSTRAINT students_relationships FOREIGN KEY students_relationships (relationship_id)
        REFERENCES relationships (relationship_id);

-- Reference: tc_course (table: teacher_courses)
ALTER TABLE teacher_courses
    ADD CONSTRAINT tc_course FOREIGN KEY tc_course (course_id)
        REFERENCES courses (course_id);

-- Reference: tc_teacher (table: teacher_courses)
ALTER TABLE teacher_courses
    ADD CONSTRAINT tc_teacher FOREIGN KEY tc_teacher (teacher_id)
        REFERENCES teachers (teacher_id);

-- Reference: teacher_user (table: teachers)
ALTER TABLE teachers
    ADD CONSTRAINT teacher_user FOREIGN KEY teacher_user (teacher_id)
        REFERENCES users (user_id);

-- Reference: ur_role (table: users_roles)
ALTER TABLE users_roles
    ADD CONSTRAINT ur_role FOREIGN KEY ur_role (role_id)
        REFERENCES roles (role_id);

-- Reference: ur_user (table: users_roles)
ALTER TABLE users_roles
    ADD CONSTRAINT ur_user FOREIGN KEY ur_user (user_id)
        REFERENCES users (user_id);

-- Reference: users_document_type (table: users)
ALTER TABLE users
    ADD CONSTRAINT users_document_type FOREIGN KEY users_document_type (document_type_id)
        REFERENCES document_types (document_type_id);

-- Reference: users_gender (table: users)
ALTER TABLE users
    ADD CONSTRAINT users_gender FOREIGN KEY users_gender (gender_id)
        REFERENCES genders (gender_id);

-- Reference: users_nationality (table: users)
ALTER TABLE users
    ADD CONSTRAINT users_nationality FOREIGN KEY users_nationality (nationality_id)
        REFERENCES nationalities (nationality_id);

-- End of file.

/*------------------------------------------------------------------------------------------------------*/
-- Creación de Queries

INSERT INTO roles (`name`) VALUES ('ROLE_ADMIN'); -- ID = 1
INSERT INTO roles (`name`) VALUES ('ROLE_TEACHER'); -- ID = 2
INSERT INTO roles (`name`) VALUES ('ROLE_PARENT'); -- ID = 3
INSERT INTO roles (`name`) VALUES ('ROLE_STUDENT'); -- ID = 4

INSERT INTO `privileges` (`name`) VALUES ('UPDATE_PRIVILEGE'); -- ID = 1
INSERT INTO `privileges` (`name`) VALUES ('WRITE_PRIVILEGE'); -- ID = 2
INSERT INTO `privileges` (`name`) VALUES ('READ_PRIVILEGE'); -- ID = 3
INSERT INTO `privileges` (`name`) VALUES ('DELETE_PRIVILEGE'); -- ID = 4

INSERT INTO `document_types` (name) VALUES ('DNI');
INSERT INTO `document_types` (name) VALUES ('Carné de Extranjería');
INSERT INTO `document_types` (name) VALUES ('Pasaporte');
INSERT INTO `document_types` (name) VALUES ('Libreta Militar');
INSERT INTO `document_types` (name) VALUES ('Partida de Nacimiento');

INSERT INTO `nationalities` (name) VALUES ('Argentina');
INSERT INTO `nationalities` (name) VALUES ('Boliviana');
INSERT INTO `nationalities` (name) VALUES ('Brasileña');
INSERT INTO `nationalities` (name) VALUES ('Chilena');
INSERT INTO `nationalities` (name) VALUES ('Colombiana');
INSERT INTO `nationalities` (name) VALUES ('Ecuatoriana');
INSERT INTO `nationalities` (name) VALUES ('Guyanes');
INSERT INTO `nationalities` (name) VALUES ('Paraguaya');
INSERT INTO `nationalities` (name) VALUES ('Peruana');
INSERT INTO `nationalities` (name) VALUES ('Uruguaya');
INSERT INTO `nationalities` (name) VALUES ('Venezolana');

INSERT INTO `roles_privileges` (`role_id`, `privilege_id`) VALUES (2, 1);
INSERT INTO `roles_privileges` (`role_id`, `privilege_id`) VALUES (2, 2);
INSERT INTO `roles_privileges` (`role_id`, `privilege_id`) VALUES (2, 3);

INSERT INTO `roles_privileges` (`role_id`, `privilege_id`) VALUES (1, 1);
INSERT INTO `roles_privileges` (`role_id`, `privilege_id`) VALUES (1, 2);
INSERT INTO `roles_privileges` (`role_id`, `privilege_id`) VALUES (1, 3);
INSERT INTO `roles_privileges` (`role_id`, `privilege_id`) VALUES (1, 4);

INSERT INTO `roles_privileges` (`role_id`, `privilege_id`) VALUES (3, 1);
INSERT INTO `roles_privileges` (`role_id`, `privilege_id`) VALUES (3, 2);
INSERT INTO `roles_privileges` (`role_id`, `privilege_id`) VALUES (3, 3);

INSERT INTO `roles_privileges` (`role_id`, `privilege_id`) VALUES (4, 1);
INSERT INTO `roles_privileges` (`role_id`, `privilege_id`) VALUES (4, 2);
INSERT INTO `roles_privileges` (`role_id`, `privilege_id`) VALUES (4, 3);

INSERT INTO `genders` (`name`) VALUES ('Masculino');
INSERT INTO `genders` (`name`) VALUES ('Femenino');

INSERT INTO `relationships`(`name`) VALUES ('Hijo(a)');
INSERT INTO `relationships`(`name`) VALUES ('Sobrino(a)');
INSERT INTO `relationships`(`name`) VALUES ('Primo(a)');
INSERT INTO `relationships`(`name`) VALUES ('Nieto(a)');
INSERT INTO `relationships`(`name`) VALUES ('Apoderado(a)');
INSERT INTO `relationships`(`name`) VALUES ('Ahijado(a)');

INSERT INTO `school_years` (`year`, `start_date`, `finish_date`) VALUES (2019, '2019-03-01', '2019-12-15');
INSERT INTO `school_years` (`year`, `start_date`, `finish_date`) VALUES (2020, '2020-03-01', '2020-12-15');
INSERT INTO `school_years` (`year`, `start_date`, `finish_date`) VALUES (2021, '2021-03-01', '2021-12-15');

INSERT INTO `levels`(`name`, `description`) VALUES ('Inicial', 'Inicial');
INSERT INTO `levels`(`name`, `description`) VALUES ('Primaria', 'Primaria');
INSERT INTO `levels`(`name`, `description`) VALUES ('Secundaria', 'Secundaria');

INSERT INTO `grades` (`order`, `name`, `description`, `level_id`) VALUES (01, '3 Años', '3 años de inicial', 1);
INSERT INTO `grades` (`order`, `name`, `description`, `level_id`) VALUES (02, '4 años', '4 años de inicial', 1);
INSERT INTO `grades` (`order`, `name`, `description`, `level_id`) VALUES (03, '5 años', '5 años de inicial', 1);
INSERT INTO `grades` (`order`, `name`, `description`, `level_id`) VALUES (04, '1° Primaria', 'Primer grado de primaria', 2);
INSERT INTO `grades` (`order`, `name`, `description`, `level_id`) VALUES (05, '2° Primaria', 'Segundo grado de primaria', 2);
INSERT INTO `grades` (`order`, `name`, `description`, `level_id`) VALUES (06, '3° Primaria', 'Tercer grado de primaria', 2);
INSERT INTO `grades` (`order`, `name`, `description`, `level_id`) VALUES (07, '4° Primaria', 'Cuarto grado de primaria', 2);
INSERT INTO `grades` (`order`, `name`, `description`, `level_id`) VALUES (08, '5° Primaria', 'Quinto grado de primaria', 2);
INSERT INTO `grades` (`order`, `name`, `description`, `level_id`) VALUES (09, '6° Primaria', 'Sexto grado de primaria', 2);
INSERT INTO `grades` (`order`, `name`, `description`, `level_id`) VALUES (10, '1° Secundaria', 'Primer año de secundaria', 3);
INSERT INTO `grades` (`order`, `name`, `description`, `level_id`) VALUES (11, '2° Secundaria', 'Segundo año de secundaria', 3);
INSERT INTO `grades` (`order`, `name`, `description`, `level_id`) VALUES (12, '3° Secundaria', 'Tercer año de secundaria', 3);
INSERT INTO `grades` (`order`, `name`, `description`, `level_id`) VALUES (13, '4° Secundaria', 'Cuarto año de secundaria', 3);
INSERT INTO `grades` (`order`, `name`, `description`, `level_id`) VALUES (14, '5° Secundaria', 'Quinto año de secundaria', 3);

INSERT INTO `pays` (`description`, `amount`, `start_date`, `expiration_date`) VALUES ('Mensualidad de Marzo', 250, '2021-03-20', '2021-03-31');
INSERT INTO `pays` (`description`, `amount`, `start_date`, `expiration_date`) VALUES ('Mensualidad de Abril', 250, '2021-04-20', '2021-04-30');
INSERT INTO `pays` (`description`, `amount`, `start_date`, `expiration_date`) VALUES ('Mensualidad de Mayo', 250, '2021-05-20', '2021-05-31');
INSERT INTO `pays` (`description`, `amount`, `start_date`, `expiration_date`) VALUES ('Mensualidad de Junio', 250, '2021-06-20', '2021-06-30');
INSERT INTO `pays` (`description`, `amount`, `start_date`, `expiration_date`) VALUES ('Mensualidad de Julio', 250, '2021-07-20', '2021-07-31');
INSERT INTO `pays` (`description`, `amount`, `start_date`, `expiration_date`) VALUES ('Mensualidad de Agosto', 250, '2021-08-20', '2021-08-31');
INSERT INTO `pays` (`description`, `amount`, `start_date`, `expiration_date`) VALUES ('Mensualidad de Septiembre', 250, '2021-09-20', '2021-09-30');
INSERT INTO `pays` (`description`, `amount`, `start_date`, `expiration_date`) VALUES ('Mensualidad de Octubre', 250, '2021-10-20', '2021-10-31');
INSERT INTO `pays` (`description`, `amount`, `start_date`, `expiration_date`) VALUES ('Mensualidad de Noviembre', 250, '2021-11-20', '2021-11-30');
INSERT INTO `pays` (`description`, `amount`, `start_date`, `expiration_date`) VALUES ('Mensualidad de Diciembre', 150, '2021-12-05', '2021-12-20');
INSERT INTO `pays` (`description`, `amount`) VALUES ('Matrícula ', 200);

/*------------------------------------------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS sp_generate_id;
DELIMITER $$
CREATE PROCEDURE sp_generate_id(IN sp_type varchar(50), OUT sp_id varchar(10))
BEGIN
    START TRANSACTION;
    IF sp_type = 'Student' THEN
        INSERT INTO increment_students VALUES (NULL);
        SET sp_id = CONCAT('S0', YEAR(CURDATE()), LPAD(LAST_INSERT_ID(), 4, '0'));
        select sp_id;
    ELSEIF sp_type = 'Parent' THEN
        INSERT INTO increment_parents VALUES (NULL);
        SET sp_id = CONCAT('P0', YEAR(CURDATE()), LPAD(LAST_INSERT_ID(), 4, '0'));
        select sp_id;
    ELSEIF sp_type = 'Teacher' THEN
        INSERT INTO increment_teachers VALUES (NULL);
        SET sp_id = CONCAT('T0', YEAR(CURDATE()), LPAD(LAST_INSERT_ID(), 4, '0'));
        select sp_id;
    ELSEIF sp_type = 'Admin' THEN
        INSERT INTO increment_users VALUES (NULL);
        SET sp_id = CONCAT('U0', YEAR(CURDATE()), LPAD(LAST_INSERT_ID(), 4, '0'));
        select sp_id;
    END IF;
    COMMIT;
END $$
DELIMITER ;

-- CALL sp_generate_id("Parent", @var);
-- select @var;