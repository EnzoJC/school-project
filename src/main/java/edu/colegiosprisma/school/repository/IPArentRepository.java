package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface IPArentRepository extends JpaRepository<Parent, String> {
    public void guardar(Parent parent);

}
