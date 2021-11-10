/*
CREATE DATABASE school_db;
USE school_db;

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
    UNIQUE INDEX days_ak_1 (name),
    CONSTRAINT days_pk PRIMARY KEY (day_id)
);

-- Table: debts
CREATE TABLE debts
(
    debt_id        int         NOT NULL AUTO_INCREMENT,
    payment_id     int         NOT NULL,
    transaction_id varchar(12) NOT NULL,
    CONSTRAINT debts_pk PRIMARY KEY (debt_id)
);

-- Table: departments
CREATE TABLE departments
(
    department_id varchar(10) NOT NULL,
    name          varchar(50) NOT NULL,
    CONSTRAINT departments_pk PRIMARY KEY (department_id)
);

-- Table: districts
CREATE TABLE districts
(
    district_id varchar(10) NOT NULL,
    name        varchar(50) NOT NULL,
    province_id varchar(10) NOT NULL,
    CONSTRAINT districts_pk PRIMARY KEY (district_id)
);

-- Table: document_types
CREATE TABLE document_types
(
    document_type_id int         NOT NULL AUTO_INCREMENT,
    name             varchar(50) NOT NULL,
    length           int         NOT NULL,
    UNIQUE INDEX document_types_ak_1 (name),
    CONSTRAINT document_types_pk PRIMARY KEY (document_type_id)
);

-- Table: enrollments
CREATE TABLE enrollments
(
    enrollment_id  int         NOT NULL AUTO_INCREMENT,
    current_year   boolean     NOT NULL DEFAULT 1,
    state_id       int         NOT NULL,
    scholarship_id int         NULL,
    school_year_id int         NOT NULL,
    class_id       int         NULL,
    grade_id       int         NOT NULL,
    student_id     varchar(10) NOT NULL,
    CONSTRAINT enrollments_pk PRIMARY KEY (enrollment_id)
);

-- Table: enrollments_form
CREATE TABLE enrollments_form
(
    enrollment_form_id    varchar(10)  NOT NULL,
    student_code          varchar(14)  NOT NULL,
    is_registered_birth   boolean      NOT NULL,
    tipe_of_birth_id      int          NOT NULL,
    is_complication_birth boolean      NOT NULL,
    observation_birth     varchar(100) NULL,
    raised_head           int          NULL,
    sit_down              int          NULL,
    crawl                 int          NULL,
    stoop_up              int          NULL,
    walk                  int          NULL,
    sphinter              int          NULL,
    first_words           int          NULL,
    speak_fluently        int          NULL,
    allergy               varchar(50)  NOT NULL,
    blood_type            varchar(20)  NOT NULL,
    mother_tongue         int          NOT NULL,
    second_language       int          NOT NULL,
    district_id           varchar(10)  NOT NULL,
    religion_id           int          NOT NULL,
    UNIQUE INDEX student_code (student_code),
    CONSTRAINT enrollments_form_pk PRIMARY KEY (enrollment_form_id)
);

-- Table: genders
CREATE TABLE genders
(
    gender_id int         NOT NULL AUTO_INCREMENT,
    name      varchar(50) NOT NULL,
    CONSTRAINT genders_pk PRIMARY KEY (gender_id)
);

-- Table: grades
CREATE TABLE grades
(
    grade_id    int         NOT NULL AUTO_INCREMENT,
    `order`     int         NOT NULL,
    name        varchar(50) NOT NULL,
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

-- Table: increment_transaction
CREATE TABLE increment_transaction
(
    increment_transaction_id int NOT NULL AUTO_INCREMENT,
    CONSTRAINT increment_transaction_pk PRIMARY KEY (increment_transaction_id)
);

-- Table: increment_users
CREATE TABLE increment_users
(
    increment_user_id int NOT NULL AUTO_INCREMENT,
    CONSTRAINT increment_users_pk PRIMARY KEY (increment_user_id)
);

-- Table: languages
CREATE TABLE languages
(
    language_id int         NOT NULL AUTO_INCREMENT,
    name        varchar(50) NOT NULL,
    CONSTRAINT languages_pk PRIMARY KEY (language_id)
);

-- Table: levels
CREATE TABLE levels
(
    level_id    int         NOT NULL AUTO_INCREMENT,
    name        varchar(10) NOT NULL,
    description varchar(50) NOT NULL,
    UNIQUE INDEX Level_ak_1 (name),
    CONSTRAINT levels_pk PRIMARY KEY (level_id)
);

-- Table: nationalities
CREATE TABLE nationalities
(
    nationality_id int         NOT NULL AUTO_INCREMENT,
    name           varchar(50) NOT NULL,
    UNIQUE INDEX nationalities_ak_1 (name),
    CONSTRAINT nationalities_pk PRIMARY KEY (nationality_id)
);

-- Table: parent_by_student
CREATE TABLE parent_by_student
(
    enrollment_form_id    varchar(10) NOT NULL,
    parent_information_id int         NOT NULL,
    CONSTRAINT parent_by_student_pk PRIMARY KEY (enrollment_form_id, parent_information_id)
);

-- Table: parents
CREATE TABLE parents
(
    parent_id  varchar(10) NOT NULL,
    occupation varchar(50) NULL,
    phone      varchar(20) NOT NULL,
    email      varchar(20) NOT NULL,
    UNIQUE INDEX phone_ak_p (phone),
    UNIQUE INDEX email_ak_p (email),
    CONSTRAINT parents_pk PRIMARY KEY (parent_id)
);

-- Table: parents_information
CREATE TABLE parents_information
(
    parent_information_id int         NOT NULL AUTO_INCREMENT,
    given_names           varchar(50) NOT NULL,
    first_last_name       varchar(50) NOT NULL,
    second_last_name      varchar(50) NOT NULL,
    is_alive              boolean     NOT NULL,
    birth_date            date        NOT NULL,
    education_degree      varchar(50) NOT NULL,
    occupation            varchar(50) NOT NULL,
    religion              varchar(50) NOT NULL,
    live_with_student     boolean     NOT NULL,
    document_type_id      int         NOT NULL,
    document_number       varchar(20) NOT NULL,
    UNIQUE INDEX document_number_ak_2 (document_number),
    CONSTRAINT parents_information_pk PRIMARY KEY (parent_information_id)
);

-- Table: payment_discounts
CREATE TABLE payment_discounts
(
    payment_discount_id int       NOT NULL AUTO_INCREMENT,
    percentage          int       NOT NULL,
    date_created        timestamp NOT NULL,
    valid_from          timestamp NOT NULL,
    valid_until         timestamp NOT NULL,
    payment_id          int       NOT NULL,
    CONSTRAINT payment_discounts_pk PRIMARY KEY (payment_discount_id)
);

-- Table: payment_types
CREATE TABLE payment_types
(
    payment_type_id int         NOT NULL AUTO_INCREMENT,
    name            varchar(30) NOT NULL,
    UNIQUE INDEX name_ak_1 (name),
    CONSTRAINT payment_types_pk PRIMARY KEY (payment_type_id)
);

-- Table: payments
CREATE TABLE payments
(
    payment_id      int            NOT NULL AUTO_INCREMENT,
    description     varchar(50)    NOT NULL,
    amount          decimal(10, 2) NOT NULL,
    start_date      date           NULL,
    expiration_date date           NULL,
    is_active       boolean        NOT NULL DEFAULT 1,
    payment_type_id int            NOT NULL,
    CONSTRAINT payments_pk PRIMARY KEY (payment_id)
);

-- Table: privileges
CREATE TABLE privileges
(
    privilege_id int         NOT NULL AUTO_INCREMENT,
    name         varchar(50) NOT NULL,
    UNIQUE INDEX privileges_ak_1 (name),
    CONSTRAINT privileges_pk PRIMARY KEY (privilege_id)
);

-- Table: provinces
CREATE TABLE provinces
(
    province_id   varchar(10) NOT NULL,
    name          varchar(50) NOT NULL,
    department_id varchar(10) NOT NULL,
    CONSTRAINT provinces_pk PRIMARY KEY (province_id)
);

-- Table: relationships
CREATE TABLE relationships
(
    relationship_id int         NOT NULL AUTO_INCREMENT,
    name            varchar(50) NOT NULL,
    UNIQUE INDEX family_relationships_ak_1 (name),
    CONSTRAINT relationships_pk PRIMARY KEY (relationship_id)
);

-- Table: religions
CREATE TABLE religions
(
    religion_id int         NOT NULL AUTO_INCREMENT,
    name        varchar(50) NOT NULL,
    CONSTRAINT religions_pk PRIMARY KEY (religion_id)
);

-- Table: roles
CREATE TABLE roles
(
    role_id int         NOT NULL AUTO_INCREMENT,
    name    varchar(50) NOT NULL,
    UNIQUE INDEX roles_ak_1 (name),
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
    start_time  date NOT NULL,
    end_time    date NOT NULL,
    day_id      int  NOT NULL,
    CONSTRAINT schedules_pk PRIMARY KEY (schedule_id)
);

-- Table: scholarships
CREATE TABLE scholarships
(
    scholarship_id int         NOT NULL AUTO_INCREMENT,
    name           varchar(50) NOT NULL,
    percentage     int         NOT NULL,
    CONSTRAINT scholarships_pk PRIMARY KEY (scholarship_id)
);

-- Table: school
CREATE TABLE school
(
    ruc     varchar(11) NOT NULL,
    name    varchar(50) NOT NULL,
    address varchar(50) NOT NULL,
    CONSTRAINT school_pk PRIMARY KEY (ruc)
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

-- Table: states
CREATE TABLE states
(
    state_id int         NOT NULL AUTO_INCREMENT,
    name     varchar(50) NOT NULL,
    CONSTRAINT states_pk PRIMARY KEY (state_id)
);

-- Table: students
CREATE TABLE students
(
    student_id      varchar(10) NOT NULL,
    student_email   varchar(50) NOT NULL,
    parent_id       varchar(10) NOT NULL,
    relationship_id int         NULL,
    last_grade_id   int         NULL,
    UNIQUE INDEX students_ak_1 (student_email),
    CONSTRAINT students_pk PRIMARY KEY (student_id)
);

-- Table: students_disabilities
CREATE TABLE students_disabilities
(
    type_disabiltiy_id          int         NOT NULL,
    enrollment_form_id          varchar(10) NOT NULL,
    have_disability_certificate boolean     NOT NULL,
    order_sibling               boolean     NOT NULL,
    CONSTRAINT students_disabilities_pk PRIMARY KEY (type_disabiltiy_id, enrollment_form_id)
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
    phone               varchar(20) NOT NULL,
    email               varchar(20) NOT NULL,
    UNIQUE INDEX phone_ak_t (institutional_email, phone),
    UNIQUE INDEX email_ak_t (email),
    CONSTRAINT teachers_pk PRIMARY KEY (teacher_id)
);

-- Table: transactions
CREATE TABLE transactions
(
    transaction_id  varchar(12)  NOT NULL,
    issue_date      date         NULL,
    expiration_date date         NULL,
    payment_date    date         NULL,
    description     varchar(100) NULL,
    school_ruc      varchar(11)  NOT NULL,
    state_id        int          NOT NULL,
    enrollment_id   int          NOT NULL,
    CONSTRAINT transactions_pk PRIMARY KEY (transaction_id)
);

-- Table: types_disabilities
CREATE TABLE types_disabilities
(
    type_disabilty_id int         NOT NULL AUTO_INCREMENT,
    name              varchar(50) NOT NULL,
    CONSTRAINT types_disabilities_pk PRIMARY KEY (type_disabilty_id)
);

-- Table: types_of_birth
CREATE TABLE types_of_birth
(
    tipe_of_birth_id int         NOT NULL AUTO_INCREMENT,
    name             varchar(50) NOT NULL,
    CONSTRAINT types_of_birth_pk PRIMARY KEY (tipe_of_birth_id)
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
    username         varchar(10)  NULL,
    password         varchar(100) NULL,
    type             varchar(50)  NOT NULL,
    is_active        boolean      NOT NULL DEFAULT 1,
    UNIQUE INDEX document_number_ak_u (document_number),
    UNIQUE INDEX username_ak_u (username),
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

-- Reference: debts_payments (table: debts)
ALTER TABLE debts
    ADD CONSTRAINT debts_payments FOREIGN KEY debts_payments (payment_id)
        REFERENCES payments (payment_id);

-- Reference: debts_transactions (table: debts)
ALTER TABLE debts
    ADD CONSTRAINT debts_transactions FOREIGN KEY debts_transactions (transaction_id)
        REFERENCES transactions (transaction_id);

-- Reference: districts_provinces (table: districts)
ALTER TABLE districts
    ADD CONSTRAINT districts_provinces FOREIGN KEY districts_provinces (province_id)
        REFERENCES provinces (province_id);

-- Reference: ef_first_language (table: enrollments_form)
ALTER TABLE enrollments_form
    ADD CONSTRAINT ef_first_language FOREIGN KEY ef_first_language (second_language)
        REFERENCES languages (language_id);

-- Reference: ef_second_language (table: enrollments_form)
ALTER TABLE enrollments_form
    ADD CONSTRAINT ef_second_language FOREIGN KEY ef_second_language (mother_tongue)
        REFERENCES languages (language_id);

-- Reference: enrollments_classes (table: enrollments)
ALTER TABLE enrollments
    ADD CONSTRAINT enrollments_classes FOREIGN KEY enrollments_classes (class_id)
        REFERENCES classes (class_id);

-- Reference: enrollments_form_districts (table: enrollments_form)
ALTER TABLE enrollments_form
    ADD CONSTRAINT enrollments_form_districts FOREIGN KEY enrollments_form_districts (district_id)
        REFERENCES districts (district_id);

-- Reference: enrollments_form_religions (table: enrollments_form)
ALTER TABLE enrollments_form
    ADD CONSTRAINT enrollments_form_religions FOREIGN KEY enrollments_form_religions (religion_id)
        REFERENCES religions (religion_id);

-- Reference: enrollments_form_students (table: enrollments_form)
ALTER TABLE enrollments_form
    ADD CONSTRAINT enrollments_form_students FOREIGN KEY enrollments_form_students (enrollment_form_id)
        REFERENCES students (student_id);

-- Reference: enrollments_form_type_of_birth (table: enrollments_form)
ALTER TABLE enrollments_form
    ADD CONSTRAINT enrollments_form_type_of_birth FOREIGN KEY enrollments_form_type_of_birth (tipe_of_birth_id)
        REFERENCES types_of_birth (tipe_of_birth_id);

-- Reference: enrollments_grades (table: enrollments)
ALTER TABLE enrollments
    ADD CONSTRAINT enrollments_grades FOREIGN KEY enrollments_grades (grade_id)
        REFERENCES grades (grade_id);

-- Reference: enrollments_scholarships (table: enrollments)
ALTER TABLE enrollments
    ADD CONSTRAINT enrollments_scholarships FOREIGN KEY enrollments_scholarships (scholarship_id)
        REFERENCES scholarships (scholarship_id);

-- Reference: enrollments_school_years (table: enrollments)
ALTER TABLE enrollments
    ADD CONSTRAINT enrollments_school_years FOREIGN KEY enrollments_school_years (school_year_id)
        REFERENCES school_years (school_year_id);

-- Reference: enrollments_states (table: enrollments)
ALTER TABLE enrollments
    ADD CONSTRAINT enrollments_states FOREIGN KEY enrollments_states (state_id)
        REFERENCES states (state_id);

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

-- Reference: parent_by_student_ef (table: parent_by_student)
ALTER TABLE parent_by_student
    ADD CONSTRAINT parent_by_student_ef FOREIGN KEY parent_by_student_ef (enrollment_form_id)
        REFERENCES enrollments_form (enrollment_form_id);

-- Reference: parent_by_student_pi (table: parent_by_student)
ALTER TABLE parent_by_student
    ADD CONSTRAINT parent_by_student_pi FOREIGN KEY parent_by_student_pi (parent_information_id)
        REFERENCES parents_information (parent_information_id);

-- Reference: parent_user (table: parents)
ALTER TABLE parents
    ADD CONSTRAINT parent_user FOREIGN KEY parent_user (parent_id)
        REFERENCES users (user_id);

-- Reference: payment_discounts_payments (table: payment_discounts)
ALTER TABLE payment_discounts
    ADD CONSTRAINT payment_discounts_payments FOREIGN KEY payment_discounts_payments (payment_id)
        REFERENCES payments (payment_id);

-- Reference: payments_payment_types (table: payments)
ALTER TABLE payments
    ADD CONSTRAINT payments_payment_types FOREIGN KEY payments_payment_types (payment_type_id)
        REFERENCES payment_types (payment_type_id);

-- Reference: pi_document_types (table: parents_information)
ALTER TABLE parents_information
    ADD CONSTRAINT pi_document_types FOREIGN KEY pi_document_types (document_type_id)
        REFERENCES document_types (document_type_id);

-- Reference: provinces_departments (table: provinces)
ALTER TABLE provinces
    ADD CONSTRAINT provinces_departments FOREIGN KEY provinces_departments (department_id)
        REFERENCES departments (department_id);

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

-- Reference: sd_td (table: students_disabilities)
ALTER TABLE students_disabilities
    ADD CONSTRAINT sd_td FOREIGN KEY sd_td (type_disabiltiy_id)
        REFERENCES types_disabilities (type_disabilty_id);

-- Reference: sds_ef (table: students_disabilities)
ALTER TABLE students_disabilities
    ADD CONSTRAINT sds_ef FOREIGN KEY sds_ef (enrollment_form_id)
        REFERENCES enrollments_form (enrollment_form_id);

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

-- Reference: students_grades (table: students)
ALTER TABLE students
    ADD CONSTRAINT students_grades FOREIGN KEY students_grades (last_grade_id)
        REFERENCES grades (grade_id);

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

-- Reference: transactions_enrollments (table: transactions)
ALTER TABLE transactions
    ADD CONSTRAINT transactions_enrollments FOREIGN KEY transactions_enrollments (enrollment_id)
        REFERENCES enrollments (enrollment_id);

-- Reference: transactions_school (table: transactions)
ALTER TABLE transactions
    ADD CONSTRAINT transactions_school FOREIGN KEY transactions_school (school_ruc)
        REFERENCES school (ruc);

-- Reference: transactions_states (table: transactions)
ALTER TABLE transactions
    ADD CONSTRAINT transactions_states FOREIGN KEY transactions_states (state_id)
        REFERENCES states (state_id);

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

-- Reference: users_genders (table: users)
ALTER TABLE users
    ADD CONSTRAINT users_genders FOREIGN KEY users_genders (gender_id)
        REFERENCES genders (gender_id);

-- Reference: users_nationality (table: users)
ALTER TABLE users
    ADD CONSTRAINT users_nationality FOREIGN KEY users_nationality (nationality_id)
        REFERENCES nationalities (nationality_id);

-- End of file.

-- ----------------------------------------------------------------------------------------------------------------------
-- Poblado de tablas

INSERT INTO roles (`name`) VALUES ('ROLE_ADMIN'); -- ID = 1
INSERT INTO roles (`name`) VALUES ('ROLE_TEACHER'); -- ID = 2
INSERT INTO roles (`name`) VALUES ('ROLE_PARENT'); -- ID = 3
INSERT INTO roles (`name`) VALUES ('ROLE_STUDENT'); -- ID = 4

INSERT INTO `privileges` (`name`) VALUES ('UPDATE_PRIVILEGE'); -- ID = 1
INSERT INTO `privileges` (`name`) VALUES ('WRITE_PRIVILEGE'); -- ID = 2
INSERT INTO `privileges` (`name`) VALUES ('READ_PRIVILEGE'); -- ID = 3
INSERT INTO `privileges` (`name`) VALUES ('DELETE_PRIVILEGE'); -- ID = 4

INSERT INTO `document_types` (name, length) VALUES ('DNI', 8);
INSERT INTO `document_types` (name, length) VALUES ('Carné de Extranjería', 12);
INSERT INTO `document_types` (name, length) VALUES ('Pasaporte', 12);
INSERT INTO `document_types` (name, length) VALUES ('Partida de Nacimiento', 15);

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

INSERT INTO `relationships`(`name`) VALUES ('Padre');
INSERT INTO `relationships`(`name`) VALUES ('Madre');
INSERT INTO `relationships`(`name`) VALUES ('Tio');
INSERT INTO `relationships`(`name`) VALUES ('Tia');
INSERT INTO `relationships`(`name`) VALUES ('Abuelo');
INSERT INTO `relationships`(`name`) VALUES ('Abuela');

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

INSERT INTO `payment_types` (`name`) VALUES ('Mensualidad de Marzo');
INSERT INTO `payment_types` (`name`) VALUES ('Mensualidad de Abril');
INSERT INTO `payment_types` (`name`) VALUES ('Mensualidad de Mayo');
INSERT INTO `payment_types` (`name`) VALUES ('Mensualidad de Junio');
INSERT INTO `payment_types` (`name`) VALUES ('Mensualidad de Julio');
INSERT INTO `payment_types` (`name`) VALUES ('Mensualidad de Agosto');
INSERT INTO `payment_types` (`name`) VALUES ('Mensualidad de Septiembre');
INSERT INTO `payment_types` (`name`) VALUES ('Mensualidad de Octubre');
INSERT INTO `payment_types` (`name`) VALUES ('Mensualidad de Noviembre');
INSERT INTO `payment_types` (`name`) VALUES ('Mensualidad de Diciembre');
INSERT INTO `payment_types` (`name`) VALUES ('Matrícula');
INSERT INTO `payment_types` (`name`) VALUES ('Gasto administrativo');
INSERT INTO `payment_types` (`name`) VALUES ('Multa');

INSERT INTO `payments` (`description`, `amount`, `start_date`, `expiration_date`, `payment_type_id`) VALUES ('Mensualidad de Marzo', 250, '2021-03-20', '2021-03-31', 1);
INSERT INTO `payments` (`description`, `amount`, `start_date`, `expiration_date`, `payment_type_id`) VALUES ('Mensualidad de Abril', 250, '2021-04-20', '2021-04-30', 2);
INSERT INTO `payments` (`description`, `amount`, `start_date`, `expiration_date`, `payment_type_id`) VALUES ('Mensualidad de Mayo', 250, '2021-05-20', '2021-05-31', 3);
INSERT INTO `payments` (`description`, `amount`, `start_date`, `expiration_date`, `payment_type_id`) VALUES ('Mensualidad de Junio', 250, '2021-06-20', '2021-06-30', 4);
INSERT INTO `payments` (`description`, `amount`, `start_date`, `expiration_date`, `payment_type_id`) VALUES ('Mensualidad de Julio', 250, '2021-07-20', '2021-07-31', 5);
INSERT INTO `payments` (`description`, `amount`, `start_date`, `expiration_date`, `payment_type_id`) VALUES ('Mensualidad de Agosto', 250, '2021-08-20', '2021-08-31', 6);
INSERT INTO `payments` (`description`, `amount`, `start_date`, `expiration_date`, `payment_type_id`) VALUES ('Mensualidad de Septiembre', 250, '2021-09-20', '2021-09-30', 7);
INSERT INTO `payments` (`description`, `amount`, `start_date`, `expiration_date`, `payment_type_id`) VALUES ('Mensualidad de Octubre', 250, '2021-10-20', '2021-10-31', 8);
INSERT INTO `payments` (`description`, `amount`, `start_date`, `expiration_date`, `payment_type_id`) VALUES ('Mensualidad de Noviembre', 250, '2021-11-20', '2021-11-30', 9);
INSERT INTO `payments` (`description`, `amount`, `start_date`, `expiration_date`, `payment_type_id`) VALUES ('Mensualidad de Diciembre', 150, '2021-12-05', '2021-12-20', 10);
INSERT INTO `payments` (`description`, `amount`, `payment_type_id`) VALUES ('Matrícula 2021', 200, 11);

insert into `states` (`name`) values ('En espera');
insert into `states` (`name`) values ('Pre-inscrito');
insert into `states` (`name`) values ('Pendiente de validación');
insert into `states` (`name`) values ('Matriculado');
insert into `states` (`name`) values ('Pendiente de pago');
insert into `states` (`name`) values ('Atrasado');
insert into `states` (`name`) values ('Cancelado');

insert into school (ruc, name, address) values ('20600093470', 'Colegios Prisma' ,'San Martin De Porres');

-- ----------------------------------------------------------------------------------------------------
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

DROP PROCEDURE IF EXISTS sp_generate_trans_id;
DELIMITER $$
CREATE PROCEDURE sp_generate_trans_id(OUT sp_id varchar(12))
BEGIN
    START TRANSACTION;
    INSERT INTO increment_students VALUES (NULL);
    SET sp_id = CONCAT('BV01-', LPAD(LAST_INSERT_ID(), 6, '0'));
    select sp_id;
    COMMIT;
END $$
DELIMITER ;

*/

-- CALL sp_generate_id("Parent", @var);
-- select @var;