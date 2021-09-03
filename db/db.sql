-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2021-09-03 04:25:07.285

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
    CONSTRAINT days_pk PRIMARY KEY (day_id)
);

-- Table: enrollments
CREATE TABLE enrollments
(
    enrollment_id     int         NOT NULL AUTO_INCREMENT,
    school_year_id    int         NOT NULL,
    student_id        varchar(10) NOT NULL,
    enrollment_status boolean     NOT NULL,
    grade_id          int         NOT NULL,
    class_id          int NULL,
    CONSTRAINT enrollments_pk PRIMARY KEY (enrollment_id)
);

-- Table: grades
CREATE TABLE grades
(
    grade_id    int         NOT NULL AUTO_INCREMENT,
    `order`     int         NOT NULL,
    name        varchar(10) NOT NULL,
    description varchar(50) NOT NULL,
    level_id    int         NOT NULL,
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
    UNIQUE INDEX Level_ak_1 (name),
    CONSTRAINT levels_pk PRIMARY KEY (level_id)
);

-- Table: parents
CREATE TABLE parents
(
    parent_id           varchar(10) NOT NULL,
    family_relationship varchar(50) NULL,
    CONSTRAINT parents_pk PRIMARY KEY (parent_id)
);

-- Table: payments_details
CREATE TABLE payments_details
(
    payment_detail_id int  NOT NULL AUTO_INCREMENT,
    billing_id        int  NOT NULL,
    pay_date          date NOT NULL,
    pay_id            int  NOT NULL,
    enrollment_id     int  NOT NULL,
    CONSTRAINT payments_details_pk PRIMARY KEY (payment_detail_id)
);

-- Table: pays
CREATE TABLE pays
(
    pay_id          int            NOT NULL AUTO_INCREMENT,
    description     varchar(50)    NOT NULL,
    amount          decimal(10, 2) NOT NULL,
    start_date      date           NOT NULL,
    expiration_date date           NOT NULL,
    CONSTRAINT pays_pk PRIMARY KEY (pay_id)
);

-- Table: permissions
CREATE TABLE permissions
(
    permission_id int         NOT NULL AUTO_INCREMENT,
    name          varchar(50) NOT NULL,
    CONSTRAINT permissions_pk PRIMARY KEY (permission_id)
);

-- Table: roles
CREATE TABLE roles
(
    role_id int         NOT NULL AUTO_INCREMENT,
    name    varchar(50) NOT NULL,
    UNIQUE INDEX Role_ak_1 (name),
    CONSTRAINT roles_pk PRIMARY KEY (role_id)
);

-- Table: roles_permissions
CREATE TABLE roles_permissions
(
    role_id       int NOT NULL,
    permission_id int NOT NULL,
    CONSTRAINT roles_permissions_pk PRIMARY KEY (role_id, permission_id)
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
) COMMENT 'Curso de una clase dictada por un profesor a un determinado horario';

-- Table: sessions_schedules
CREATE TABLE sessions_schedules
(
    class_course_id int NOT NULL,
    schedule_id     int NOT NULL,
    CONSTRAINT sessions_schedules_pk PRIMARY KEY (class_course_id, schedule_id)
);

-- Table: students
CREATE TABLE students
(
    student_id    varchar(10) NOT NULL,
    student_email varchar(50) NOT NULL,
    parent_id     varchar(10) NOT NULL,
    UNIQUE INDEX Student_ak_1 (student_email),
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
    UNIQUE INDEX Teacher_ak_1 (institutional_email),
    CONSTRAINT teachers_pk PRIMARY KEY (teacher_id)
);

-- Table: users
CREATE TABLE users
(
    user_id          varchar(10)  NOT NULL,
    given_names      varchar(50)  NOT NULL,
    first_last_name  varchar(50)  NOT NULL,
    second_last_name varchar(50)  NOT NULL,
    document_type    varchar(50)  NOT NULL,
    document_number  varchar(20)  NOT NULL,
    birth_date       date         NOT NULL,
    address          varchar(50)  NOT NULL,
    gender           varchar(50)  NOT NULL,
    nationality      varchar(50) NULL,
    phone            varchar(20) NULL,
    email            varchar(50) NULL,
    username         varchar(10)  NOT NULL,
    password         varchar(100) NOT NULL,
    type             varchar(50)  NOT NULL,
    is_active        boolean      NOT NULL DEFAULT 1,
    UNIQUE INDEX Tutor_ak_1 (document_number,email,phone),
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
-- Reference: Session_teachers (table: sessions)
ALTER TABLE sessions
    ADD CONSTRAINT Session_teachers FOREIGN KEY Session_teachers (teacher_id)
    REFERENCES teachers (teacher_id);

-- Reference: classes_classrooms (table: classes)
ALTER TABLE classes
    ADD CONSTRAINT classes_classrooms FOREIGN KEY classes_classrooms (classroom_id)
    REFERENCES classrooms (classroom_id);

-- Reference: classes_course_classes (table: sessions)
ALTER TABLE sessions
    ADD CONSTRAINT classes_course_classes FOREIGN KEY classes_course_classes (class_id)
    REFERENCES classes (class_id);

-- Reference: classes_courses_courses (table: sessions)
ALTER TABLE sessions
    ADD CONSTRAINT classes_courses_courses FOREIGN KEY classes_courses_courses (course_id)
    REFERENCES courses (course_id);

-- Reference: classes_grades (table: classes)
ALTER TABLE classes
    ADD CONSTRAINT classes_grades FOREIGN KEY classes_grades (grade_id)
    REFERENCES grades (grade_id);

-- Reference: classes_sections (table: classes)
ALTER TABLE classes
    ADD CONSTRAINT classes_sections FOREIGN KEY classes_sections (section_id)
    REFERENCES sections (section_id);

-- Reference: classes_teachers (table: classes)
ALTER TABLE classes
    ADD CONSTRAINT classes_teachers FOREIGN KEY classes_teachers (tutor_id)
    REFERENCES teachers (teacher_id);

-- Reference: enrollments_classes (table: enrollments)
ALTER TABLE enrollments
    ADD CONSTRAINT enrollments_classes FOREIGN KEY enrollments_classes (class_id)
    REFERENCES classes (class_id);

-- Reference: enrollments_grades (table: enrollments)
ALTER TABLE enrollments
    ADD CONSTRAINT enrollments_grades FOREIGN KEY enrollments_grades (grade_id)
    REFERENCES grades (grade_id);

-- Reference: enrollments_school_years (table: enrollments)
ALTER TABLE enrollments
    ADD CONSTRAINT enrollments_school_years FOREIGN KEY enrollments_school_years (school_year_id)
    REFERENCES school_years (school_year_id);

-- Reference: enrollments_students (table: enrollments)
ALTER TABLE enrollments
    ADD CONSTRAINT enrollments_students FOREIGN KEY enrollments_students (student_id)
    REFERENCES students (student_id);

-- Reference: grades_courses_courses (table: grades_courses)
ALTER TABLE grades_courses
    ADD CONSTRAINT grades_courses_courses FOREIGN KEY grades_courses_courses (course_id)
    REFERENCES courses (course_id);

-- Reference: grades_courses_grades (table: grades_courses)
ALTER TABLE grades_courses
    ADD CONSTRAINT grades_courses_grades FOREIGN KEY grades_courses_grades (grade_id)
    REFERENCES grades (grade_id);

-- Reference: grades_levels (table: grades)
ALTER TABLE grades
    ADD CONSTRAINT grades_levels FOREIGN KEY grades_levels (level_id)
    REFERENCES levels (level_id);

-- Reference: parents_users (table: parents)
ALTER TABLE parents
    ADD CONSTRAINT parents_users FOREIGN KEY parents_users (parent_id)
    REFERENCES users (user_id);

-- Reference: payments_details_enrollments (table: payments_details)
ALTER TABLE payments_details
    ADD CONSTRAINT payments_details_enrollments FOREIGN KEY payments_details_enrollments (enrollment_id)
    REFERENCES enrollments (enrollment_id);

-- Reference: payments_details_pays (table: payments_details)
ALTER TABLE payments_details
    ADD CONSTRAINT payments_details_pays FOREIGN KEY payments_details_pays (pay_id)
    REFERENCES pays (pay_id);

-- Reference: roles_permissions_permissions (table: roles_permissions)
ALTER TABLE roles_permissions
    ADD CONSTRAINT roles_permissions_permissions FOREIGN KEY roles_permissions_permissions (permission_id)
    REFERENCES permissions (permission_id);

-- Reference: roles_permissions_roles (table: roles_permissions)
ALTER TABLE roles_permissions
    ADD CONSTRAINT roles_permissions_roles FOREIGN KEY roles_permissions_roles (role_id)
    REFERENCES roles (role_id);

-- Reference: schedule_classes_course_classes_course (table: sessions_schedules)
ALTER TABLE sessions_schedules
    ADD CONSTRAINT schedule_classes_course_classes_course FOREIGN KEY schedule_classes_course_classes_course (class_course_id)
    REFERENCES sessions (session_id);

-- Reference: schedule_classes_course_schedule (table: sessions_schedules)
ALTER TABLE sessions_schedules
    ADD CONSTRAINT schedule_classes_course_schedule FOREIGN KEY schedule_classes_course_schedule (schedule_id)
    REFERENCES schedules (schedule_id);

-- Reference: schedule_day (table: schedules)
ALTER TABLE schedules
    ADD CONSTRAINT schedule_day FOREIGN KEY schedule_day (day_id)
    REFERENCES days (day_id);

-- Reference: students_parents (table: students)
ALTER TABLE students
    ADD CONSTRAINT students_parents FOREIGN KEY students_parents (parent_id)
    REFERENCES parents (parent_id);

-- Reference: students_users (table: students)
ALTER TABLE students
    ADD CONSTRAINT students_users FOREIGN KEY students_users (student_id)
    REFERENCES users (user_id);

-- Reference: teacher_courses_courses (table: teacher_courses)
ALTER TABLE teacher_courses
    ADD CONSTRAINT teacher_courses_courses FOREIGN KEY teacher_courses_courses (course_id)
    REFERENCES courses (course_id);

-- Reference: teacher_courses_teachers (table: teacher_courses)
ALTER TABLE teacher_courses
    ADD CONSTRAINT teacher_courses_teachers FOREIGN KEY teacher_courses_teachers (teacher_id)
    REFERENCES teachers (teacher_id);

-- Reference: teachers_users (table: teachers)
ALTER TABLE teachers
    ADD CONSTRAINT teachers_users FOREIGN KEY teachers_users (teacher_id)
    REFERENCES users (user_id);

-- Reference: users_roles_roles (table: users_roles)
ALTER TABLE users_roles
    ADD CONSTRAINT users_roles_roles FOREIGN KEY users_roles_roles (role_id)
    REFERENCES roles (role_id);

-- Reference: users_roles_users (table: users_roles)
ALTER TABLE users_roles
    ADD CONSTRAINT users_roles_users FOREIGN KEY users_roles_users (user_id)
    REFERENCES users (user_id);

-- End of file.

