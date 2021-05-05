-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2021-05-03 05:09:18.814

-- tables
-- Table: Class
CREATE TABLE Class (
    ClassId int NOT NULL AUTO_INCREMENT,
    ClassroomType varchar(50) NOT NULL,
    StartTime time NOT NULL,
    EndTime time NOT NULL,
    SectionId int NOT NULL,
    ClassroomId int NOT NULL,
    GradeId int NOT NULL,
    CONSTRAINT Class_pk PRIMARY KEY (ClassId)
);

-- Table: Classroom
CREATE TABLE Classroom (
    ClassroomId int NOT NULL AUTO_INCREMENT,
    Name varchar(50) NOT NULL,
    CONSTRAINT Classroom_pk PRIMARY KEY (ClassroomId)
);

-- Table: Course
CREATE TABLE Course (
    CourseId int NOT NULL AUTO_INCREMENT,
    Description varchar(50) NOT NULL,
    CONSTRAINT Course_pk PRIMARY KEY (CourseId)
);

-- Table: Enrollment
CREATE TABLE Enrollment (
    EnrollmentId int NOT NULL AUTO_INCREMENT,
    SchoolYearId int NOT NULL,
    StudentId varchar(10) NOT NULL,
    ClassId int NOT NULL,
    EnrollmentStatus boolean NOT NULL,
    CONSTRAINT Enrollment_pk PRIMARY KEY (EnrollmentId)
);

-- Table: Grade
CREATE TABLE Grade (
    GradeId int NOT NULL AUTO_INCREMENT,
    `Order` int NOT NULL,
    Name varchar(10) NOT NULL,
    Description varchar(50) NOT NULL,
    LevelId int NOT NULL,
    CONSTRAINT Grade_pk PRIMARY KEY (GradeId)
);

-- Table: GradeCourse
CREATE TABLE GradeCourse (
    GradeCourseId int NOT NULL AUTO_INCREMENT,
    CourseId int NOT NULL,
    GradeId int NOT NULL,
    TeacherId varchar(10) NOT NULL,
    CONSTRAINT GradeCourse_pk PRIMARY KEY (GradeCourseId)
);

-- Table: Identification
CREATE TABLE Identification (
    IdentificationId int NOT NULL AUTO_INCREMENT,
    Type varchar(50) NOT NULL,
    CONSTRAINT Identification_pk PRIMARY KEY (IdentificationId)
);

-- Table: IncrementStudent
CREATE TABLE IncrementStudent (
    id int NOT NULL AUTO_INCREMENT,
    CONSTRAINT IncrementStudent_pk PRIMARY KEY (id)
);

-- Table: IncrementTeacher
CREATE TABLE IncrementTeacher (
    id int NOT NULL AUTO_INCREMENT,
    CONSTRAINT IncrementTeacher_pk PRIMARY KEY (id)
);

-- Table: IncrementTutor
CREATE TABLE IncrementTutor (
    id int NOT NULL AUTO_INCREMENT,
    CONSTRAINT IncrementTutor_pk PRIMARY KEY (id)
);

-- Table: Level
CREATE TABLE Level (
    LevelId int NOT NULL AUTO_INCREMENT,
    Name varchar(10) NOT NULL,
    Description varchar(50) NOT NULL,
    CONSTRAINT Level_pk PRIMARY KEY (LevelId)
);

-- Table: Login
CREATE TABLE Login (
    LoginId int NOT NULL AUTO_INCREMENT,
    User varchar(50) NOT NULL,
    Password varchar(50) NOT NULL,
    RoleId int NOT NULL,
    CONSTRAINT Login_pk PRIMARY KEY (LoginId)
);

-- Table: Pay
CREATE TABLE Pay (
    PayId int NOT NULL AUTO_INCREMENT,
    Description varchar(50) NOT NULL,
    Amount double(4,2) NOT NULL,
    StartDate date NOT NULL,
    ExpirationDate date NOT NULL,
    CONSTRAINT Pay_pk PRIMARY KEY (PayId)
);

-- Table: PaymentDetails
CREATE TABLE PaymentDetails (
    PaymentDetailsId int NOT NULL AUTO_INCREMENT,
    BilligId int NOT NULL,
    PayDate date NOT NULL,
    Pay_PayId int NOT NULL,
    EnrollmentId int NOT NULL,
    CONSTRAINT PaymentDetails_pk PRIMARY KEY (PaymentDetailsId)
);

-- Table: Role
CREATE TABLE Role (
    RoleId int NOT NULL AUTO_INCREMENT,
    Name int NOT NULL,
    CONSTRAINT Role_pk PRIMARY KEY (RoleId)
);

-- Table: SchoolYear
CREATE TABLE SchoolYear (
    SchoolYearId int NOT NULL AUTO_INCREMENT,
    Year int NOT NULL,
    StartDate date NOT NULL,
    FinishDate date NOT NULL,
    CONSTRAINT SchoolYear_pk PRIMARY KEY (SchoolYearId)
);

-- Table: Section
CREATE TABLE Section (
    SectionID int NOT NULL AUTO_INCREMENT,
    Name varchar(50) NOT NULL,
    Descripcion varchar(50) NOT NULL,
    GradeId int NOT NULL,
    CONSTRAINT Section_pk PRIMARY KEY (SectionID)
);

-- Table: Student
CREATE TABLE Student (
    StudentId varchar(10) NOT NULL,
    GivenNames varchar(50) NOT NULL,
    FirstLastName varchar(50) NOT NULL,
    SecondLastName varchar(50) NOT NULL,
    BirthDate date NOT NULL,
    Campus varchar(50) NOT NULL,
    Address varchar(50) NOT NULL,
    Gender varchar(50) NOT NULL,
    Nationality varchar(50) NOT NULL,
    Status boolean NOT NULL,
    StudentEmail varchar(50) NOT NULL,
    IdentificationId int NOT NULL,
    TutorId varchar(10) NOT NULL,
    LoginId int NOT NULL,
    CONSTRAINT Student_pk PRIMARY KEY (StudentId)
);

ALTER TABLE Student AUTO_INCREMENT = 20210;

DELIMITER $$
CREATE TRIGGER tg_studentId
BEFORE INSERT ON Student
FOR EACH ROW
BEGIN
  INSERT INTO increment VALUES (NULL);
  SET NEW.StudentId = CONCAT('ST2021', LPAD(LAST_INSERT_ID(), 4, '0'));
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER tg_emailStudent
BEFORE INSERT ON Student
FOR EACH ROW
BEGIN
 SET NEW.StudentEmail = CONCAT(NEW.StudentId, '@colegiosprisma.edu');
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER tg_studentLogin
BEFORE INSERT ON Student
FOR EACH ROW
BEGIN
  INSERT INTO `Login` (`User`, `Password`, `Group`) VALUES (New.StudentId, New.FirstLastName, 'Estudiantes');
  SET NEW.LoginId = LAST_INSERT_ID();
END$$
DELIMITER ;

-- Table: Teacher
CREATE TABLE Teacher (
    TeacherId varchar(10) NOT NULL,
    GivenNames varchar(50) NOT NULL,
    FirstLastName varchar(50) NOT NULL,
    SecondLastName varchar(50) NOT NULL,
    PhoneNumber int NOT NULL,
    Status boolean NOT NULL,
    Address varchar(50) NOT NULL,
    EmailAddress varchar(50) NOT NULL,
    TeacherEmail varchar(50) NOT NULL,
    IdentificationId int NOT NULL,
    LoginId int NOT NULL,
    CONSTRAINT Teacher_pk PRIMARY KEY (TeacherId)
);

ALTER TABLE Teacher AUTO_INCREMENT = 20210;

DELIMITER $$
CREATE TRIGGER tg_teacherId
BEFORE INSERT ON Teacher
FOR EACH ROW
BEGIN
  INSERT INTO increment VALUES (NULL);
  SET NEW.TeacherId = CONCAT('PR2021', LPAD(LAST_INSERT_ID(), 4, '0'));
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER tg_emailTeacher
BEFORE INSERT ON Teacher
FOR EACH ROW
BEGIN
 SET NEW.TeacherEmail = CONCAT(NEW.TeacherId, '@colegiosprisma.edu');
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER tg_teacherLogin
BEFORE INSERT ON Teacher
FOR EACH ROW
BEGIN
  INSERT INTO `Login` (`User`, `Password`, `Group`) VALUES (New.TeacherId, New.FirstLastName, 'Docentes');
  SET NEW.LoginId = LAST_INSERT_ID();
END$$
DELIMITER ;

-- Table: Tutor
CREATE TABLE Tutor (
    TutorId varchar(10) NOT NULL,
    GivenNames varchar(50) NOT NULL,
    FirstLastName varchar(50) NOT NULL,
    SecondLastName varchar(50) NOT NULL,
    IdCard int NOT NULL,
    PhoneNumber int NOT NULL,
    FamilyRelationship varchar(50) NOT NULL,
    Status boolean NOT NULL,
    EmailAddress varchar(50) NOT NULL,
    IdentificationId int NOT NULL,
    LoginId int NOT NULL,
    CONSTRAINT Tutor_pk PRIMARY KEY (TutorId)
);

ALTER TABLE Tutor AUTO_INCREMENT = 20210;

DELIMITER $$
CREATE TRIGGER tg_tutorId
BEFORE INSERT ON Tutor
FOR EACH ROW
BEGIN
  INSERT INTO increment VALUES (NULL);
  SET NEW.TutorId = CONCAT('TU2021', LPAD(LAST_INSERT_ID(), 4, '0'));
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER tg_tutorLogin
BEFORE INSERT ON Tutor
FOR EACH ROW
BEGIN
  INSERT INTO `Login` (`User`, `Password`, `Group`) VALUES (New.TutorId, New.FirstLastName, 'Apoderados');
  SET NEW.LoginId = LAST_INSERT_ID();
END$$
DELIMITER ;

-- foreign keys
-- Reference: Class_Classroom (table: Class)
ALTER TABLE Class ADD CONSTRAINT Class_Classroom FOREIGN KEY Class_Classroom (ClassroomId)
    REFERENCES Classroom (ClassroomId);

-- Reference: Class_Grade (table: Class)
ALTER TABLE Class ADD CONSTRAINT Class_Grade FOREIGN KEY Class_Grade (GradeId)
    REFERENCES Grade (GradeId);

-- Reference: Class_Section (table: Class)
ALTER TABLE Class ADD CONSTRAINT Class_Section FOREIGN KEY Class_Section (SectionId)
    REFERENCES Section (SectionID);

-- Reference: Enrollment_Class (table: Enrollment)
ALTER TABLE Enrollment ADD CONSTRAINT Enrollment_Class FOREIGN KEY Enrollment_Class (ClassId)
    REFERENCES Class (ClassId);

-- Reference: Enrollment_SchoolYear (table: Enrollment)
ALTER TABLE Enrollment ADD CONSTRAINT Enrollment_SchoolYear FOREIGN KEY Enrollment_SchoolYear (SchoolYearId)
    REFERENCES SchoolYear (SchoolYearId);

-- Reference: Enrollment_Student (table: Enrollment)
ALTER TABLE Enrollment ADD CONSTRAINT Enrollment_Student FOREIGN KEY Enrollment_Student (StudentId)
    REFERENCES Student (StudentId);

-- Reference: GradeCourse_Course (table: GradeCourse)
ALTER TABLE GradeCourse ADD CONSTRAINT GradeCourse_Course FOREIGN KEY GradeCourse_Course (CourseId)
    REFERENCES Course (CourseId);

-- Reference: GradeCourse_Grade (table: GradeCourse)
ALTER TABLE GradeCourse ADD CONSTRAINT GradeCourse_Grade FOREIGN KEY GradeCourse_Grade (GradeId)
    REFERENCES Grade (GradeId);

-- Reference: GradeCourse_Teacher (table: GradeCourse)
ALTER TABLE GradeCourse ADD CONSTRAINT GradeCourse_Teacher FOREIGN KEY GradeCourse_Teacher (TeacherId)
    REFERENCES Teacher (TeacherId);

-- Reference: Grade_Level (table: Grade)
ALTER TABLE Grade ADD CONSTRAINT Grade_Level FOREIGN KEY Grade_Level (LevelId)
    REFERENCES Level (LevelId);

-- Reference: Login_Role (table: Login)
ALTER TABLE Login ADD CONSTRAINT Login_Role FOREIGN KEY Login_Role (RoleId)
    REFERENCES Role (RoleId);

-- Reference: PaymentDetails_Enrollment (table: PaymentDetails)
ALTER TABLE PaymentDetails ADD CONSTRAINT PaymentDetails_Enrollment FOREIGN KEY PaymentDetails_Enrollment (EnrollmentId)
    REFERENCES Enrollment (EnrollmentId);

-- Reference: PaymentDetails_Pay (table: PaymentDetails)
ALTER TABLE PaymentDetails ADD CONSTRAINT PaymentDetails_Pay FOREIGN KEY PaymentDetails_Pay (Pay_PayId)
    REFERENCES Pay (PayId);

-- Reference: Section_Grade (table: Section)
ALTER TABLE Section ADD CONSTRAINT Section_Grade FOREIGN KEY Section_Grade (GradeId)
    REFERENCES Grade (GradeId);

-- Reference: Student_Identification (table: Student)
ALTER TABLE Student ADD CONSTRAINT Student_Identification FOREIGN KEY Student_Identification (IdentificationId)
    REFERENCES Identification (IdentificationId);

-- Reference: Student_Login (table: Student)
ALTER TABLE Student ADD CONSTRAINT Student_Login FOREIGN KEY Student_Login (LoginId)
    REFERENCES Login (LoginId);

-- Reference: Student_Tutor (table: Student)
ALTER TABLE Student ADD CONSTRAINT Student_Tutor FOREIGN KEY Student_Tutor (TutorId)
    REFERENCES Tutor (TutorId);

-- Reference: Teacher_Identification (table: Teacher)
ALTER TABLE Teacher ADD CONSTRAINT Teacher_Identification FOREIGN KEY Teacher_Identification (IdentificationId)
    REFERENCES Identification (IdentificationId);

-- Reference: Teacher_Login (table: Teacher)
ALTER TABLE Teacher ADD CONSTRAINT Teacher_Login FOREIGN KEY Teacher_Login (LoginId)
    REFERENCES Login (LoginId);

-- Reference: Tutor_Identification (table: Tutor)
ALTER TABLE Tutor ADD CONSTRAINT Tutor_Identification FOREIGN KEY Tutor_Identification (IdentificationId)
    REFERENCES Identification (IdentificationId);

-- Reference: Tutor_Login (table: Tutor)
ALTER TABLE Tutor ADD CONSTRAINT Tutor_Login FOREIGN KEY Tutor_Login (LoginId)
    REFERENCES Login (LoginId);

-- End of file.

