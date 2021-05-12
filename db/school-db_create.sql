-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2021-05-12 06:59:13.834

-- tables
-- Table: classes
CREATE TABLE classes (
    id int NOT NULL AUTO_INCREMENT,
    classroom_type varchar(50) NOT NULL,
    start_time time NOT NULL,
    end_time time NOT NULL,
    section_id int NOT NULL,
    classroom_id int NOT NULL,
    grade_id int NOT NULL,
    CONSTRAINT classes_pk PRIMARY KEY (id)
);

-- Table: classrooms
CREATE TABLE classrooms (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    CONSTRAINT classrooms_pk PRIMARY KEY (id)
);

-- Table: courses
CREATE TABLE courses (
    id int NOT NULL AUTO_INCREMENT,
    description varchar(50) NOT NULL,
    CONSTRAINT courses_pk PRIMARY KEY (id)
);

-- Table: enrollments
CREATE TABLE enrollments (
    id int NOT NULL AUTO_INCREMENT,
    school_year_id int NOT NULL,
    student_id varchar(10) NOT NULL,
    class_id int NOT NULL,
    enrollment_status boolean NOT NULL,
    CONSTRAINT enrollments_pk PRIMARY KEY (id)
);

-- Table: grades
CREATE TABLE grades (
    id int NOT NULL AUTO_INCREMENT,
    `order` int NOT NULL,
    name varchar(10) NOT NULL,
    description varchar(50) NOT NULL,
    level_id int NOT NULL,
    CONSTRAINT grades_pk PRIMARY KEY (id)
);

-- Table: grades_courses
CREATE TABLE grades_courses (
    grade_course_id int NOT NULL AUTO_INCREMENT,
    course_id int NOT NULL,
    grade_id int NOT NULL,
    teacher_id varchar(10) NOT NULL,
    CONSTRAINT grades_courses_pk PRIMARY KEY (grade_course_id)
);

-- Table: increment_parents
CREATE TABLE increment_parents (
    id int NOT NULL AUTO_INCREMENT,
    CONSTRAINT increment_parents_pk PRIMARY KEY (id)
);

-- Table: increment_students
CREATE TABLE increment_students (
    id int NOT NULL AUTO_INCREMENT,
    CONSTRAINT increment_students_pk PRIMARY KEY (id)
);

-- Table: increment_teachers
CREATE TABLE increment_teachers (
    id int NOT NULL AUTO_INCREMENT,
    CONSTRAINT increment_teachers_pk PRIMARY KEY (id)
);

-- Table: levels
CREATE TABLE levels (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(10) NOT NULL,
    description varchar(50) NOT NULL,
    UNIQUE INDEX Level_ak_1 (name),
    CONSTRAINT levels_pk PRIMARY KEY (id)
);

-- Table: parents
CREATE TABLE parents (
    id varchar(10) NOT NULL,
    given_names varchar(50) NOT NULL,
    first_last_name varchar(50) NOT NULL,
    second_last_name varchar(50) NOT NULL,
    document_type varchar(50) NOT NULL,
    document_number varchar(20) NOT NULL,
    birth_date date NOT NULL,
    address varchar(50) NOT NULL,
    gender varchar(50) NOT NULL,
    nationality varchar(50) NULL,
    phone_number varchar(20) NOT NULL,
    email_address varchar(50) NOT NULL,
    family_relationship varchar(50) NULL,
    status boolean NOT NULL DEFAULT 1,
    UNIQUE INDEX Tutor_ak_1 (document_number,email_address,phone_number),
    CONSTRAINT parents_pk PRIMARY KEY (id)
);

-- Table: payments_details
CREATE TABLE payments_details (
    id int NOT NULL AUTO_INCREMENT,
    billing_id int NOT NULL,
    pay_date date NOT NULL,
    pay_id int NOT NULL,
    enrollment_id int NOT NULL,
    CONSTRAINT payments_details_pk PRIMARY KEY (id)
);

-- Table: pays
CREATE TABLE pays (
    id int NOT NULL AUTO_INCREMENT,
    description varchar(50) NOT NULL,
    amount double(4,2) NOT NULL,
    start_date date NOT NULL,
    expiration_date date NOT NULL,
    CONSTRAINT pays_pk PRIMARY KEY (id)
);

-- Table: permissions
CREATE TABLE permissions (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    CONSTRAINT permissions_pk PRIMARY KEY (id)
);

-- Table: roles
CREATE TABLE roles (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    UNIQUE INDEX Role_ak_1 (name),
    CONSTRAINT roles_pk PRIMARY KEY (id)
);

-- Table: roles_permissions
CREATE TABLE roles_permissions (
    id int NOT NULL AUTO_INCREMENT,
    role_id int NOT NULL,
    permission_id int NOT NULL,
    CONSTRAINT roles_permissions_pk PRIMARY KEY (id)
);

-- Table: school_years
CREATE TABLE school_years (
    id int NOT NULL AUTO_INCREMENT,
    year int NOT NULL,
    start_date date NOT NULL,
    finish_date date NOT NULL,
    CONSTRAINT school_years_pk PRIMARY KEY (id)
);

-- Table: sections
CREATE TABLE sections (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    descripcion varchar(50) NOT NULL,
    grade_id int NOT NULL,
    CONSTRAINT sections_pk PRIMARY KEY (id)
);

-- Table: students
CREATE TABLE students (
    id varchar(10) NOT NULL,
    given_names varchar(50) NOT NULL,
    first_last_name varchar(50) NOT NULL,
    second_last_name varchar(50) NOT NULL,
    document_type varchar(50) NOT NULL,
    document_number varchar(20) NOT NULL,
    birth_date date NOT NULL,
    campus varchar(50) NULL,
    address varchar(50) NOT NULL,
    gender varchar(50) NOT NULL,
    nationality varchar(50) NOT NULL,
    student_email varchar(50) NOT NULL,
    status boolean NOT NULL DEFAULT 1,
    parent_id varchar(10) NOT NULL,
    UNIQUE INDEX Student_ak_1 (student_email,document_number),
    CONSTRAINT students_pk PRIMARY KEY (id)
);

-- Table: teachers
CREATE TABLE teachers (
    id varchar(10) NOT NULL,
    given_names varchar(50) NOT NULL,
    first_last_name varchar(50) NOT NULL,
    second_last_name varchar(50) NOT NULL,
    document_type varchar(50) NOT NULL,
    document_number varchar(20) NOT NULL,
    birth_date date NOT NULL,
    address varchar(50) NOT NULL,
    gender varchar(50) NOT NULL,
    nationality varchar(50) NOT NULL,
    phone_number varchar(20) NOT NULL,
    email_address varchar(50) NOT NULL,
    institutional_email varchar(50) NOT NULL,
    status boolean NOT NULL DEFAULT 1,
    UNIQUE INDEX Teacher_ak_1 (phone_number,institutional_email,document_number),
    CONSTRAINT teachers_pk PRIMARY KEY (id)
);

-- Table: users
CREATE TABLE users (
    id varchar(10) NOT NULL,
    username varchar(50) NOT NULL,
    password varchar(100) NOT NULL,
    status boolean NOT NULL DEFAULT 1,
    UNIQUE INDEX User_ak_1 (username),
    CONSTRAINT users_pk PRIMARY KEY (id)
);

-- Table: users_roles
CREATE TABLE users_roles (
    id int NOT NULL AUTO_INCREMENT,
    user_id varchar(10) NOT NULL,
    role_id int NOT NULL,
    CONSTRAINT users_roles_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: classes_classrooms (table: classes)
ALTER TABLE classes ADD CONSTRAINT classes_classrooms FOREIGN KEY classes_classrooms (classroom_id)
    REFERENCES classrooms (id);

-- Reference: classes_grades (table: classes)
ALTER TABLE classes ADD CONSTRAINT classes_grades FOREIGN KEY classes_grades (grade_id)
    REFERENCES grades (id);

-- Reference: classes_sections (table: classes)
ALTER TABLE classes ADD CONSTRAINT classes_sections FOREIGN KEY classes_sections (section_id)
    REFERENCES sections (id);

-- Reference: enrollments_classes (table: enrollments)
ALTER TABLE enrollments ADD CONSTRAINT enrollments_classes FOREIGN KEY enrollments_classes (class_id)
    REFERENCES classes (id);

-- Reference: enrollments_school_years (table: enrollments)
ALTER TABLE enrollments ADD CONSTRAINT enrollments_school_years FOREIGN KEY enrollments_school_years (school_year_id)
    REFERENCES school_years (id);

-- Reference: enrollments_students (table: enrollments)
ALTER TABLE enrollments ADD CONSTRAINT enrollments_students FOREIGN KEY enrollments_students (student_id)
    REFERENCES students (id);

-- Reference: grades_courses_courses (table: grades_courses)
ALTER TABLE grades_courses ADD CONSTRAINT grades_courses_courses FOREIGN KEY grades_courses_courses (course_id)
    REFERENCES courses (id);

-- Reference: grades_courses_grades (table: grades_courses)
ALTER TABLE grades_courses ADD CONSTRAINT grades_courses_grades FOREIGN KEY grades_courses_grades (grade_id)
    REFERENCES grades (id);

-- Reference: grades_courses_teachers (table: grades_courses)
ALTER TABLE grades_courses ADD CONSTRAINT grades_courses_teachers FOREIGN KEY grades_courses_teachers (teacher_id)
    REFERENCES teachers (id);

-- Reference: grades_levels (table: grades)
ALTER TABLE grades ADD CONSTRAINT grades_levels FOREIGN KEY grades_levels (level_id)
    REFERENCES levels (id);

-- Reference: parents_users (table: parents)
ALTER TABLE parents ADD CONSTRAINT parents_users FOREIGN KEY parents_users (id)
    REFERENCES users (id);

-- Reference: payments_details_enrollments (table: payments_details)
ALTER TABLE payments_details ADD CONSTRAINT payments_details_enrollments FOREIGN KEY payments_details_enrollments (enrollment_id)
    REFERENCES enrollments (id);

-- Reference: payments_details_pays (table: payments_details)
ALTER TABLE payments_details ADD CONSTRAINT payments_details_pays FOREIGN KEY payments_details_pays (pay_id)
    REFERENCES pays (id);

-- Reference: roles_permissions_permissions (table: roles_permissions)
ALTER TABLE roles_permissions ADD CONSTRAINT roles_permissions_permissions FOREIGN KEY roles_permissions_permissions (permission_id)
    REFERENCES permissions (id);

-- Reference: roles_permissions_roles (table: roles_permissions)
ALTER TABLE roles_permissions ADD CONSTRAINT roles_permissions_roles FOREIGN KEY roles_permissions_roles (role_id)
    REFERENCES roles (id);

-- Reference: sections_grades (table: sections)
ALTER TABLE sections ADD CONSTRAINT sections_grades FOREIGN KEY sections_grades (grade_id)
    REFERENCES grades (id);

-- Reference: students_parents (table: students)
ALTER TABLE students ADD CONSTRAINT students_parents FOREIGN KEY students_parents (parent_id)
    REFERENCES parents (id);

-- Reference: students_users (table: students)
ALTER TABLE students ADD CONSTRAINT students_users FOREIGN KEY students_users (id)
    REFERENCES users (id);

-- Reference: teachers_users (table: teachers)
ALTER TABLE teachers ADD CONSTRAINT teachers_users FOREIGN KEY teachers_users (id)
    REFERENCES users (id);

-- Reference: users_roles_roles (table: users_roles)
ALTER TABLE users_roles ADD CONSTRAINT users_roles_roles FOREIGN KEY users_roles_roles (role_id)
    REFERENCES roles (id);

-- Reference: users_roles_users (table: users_roles)
ALTER TABLE users_roles ADD CONSTRAINT users_roles_users FOREIGN KEY users_roles_users (user_id)
    REFERENCES users (id);

-- End of file.

