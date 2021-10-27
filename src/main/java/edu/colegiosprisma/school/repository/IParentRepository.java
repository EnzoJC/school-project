package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.Parent;
import edu.colegiosprisma.school.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IParentRepository extends IUserRepository{
    Parent findByDocumentNumber(String documentNumber);
}
