package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.Student;

public interface IStudentRepository extends IUserRepository{
    Student findByDocumentNumber(String documentNumber);
}
