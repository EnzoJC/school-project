package edu.colegiosprisma.school.repository;
import edu.colegiosprisma.school.entity.Login;
import edu.colegiosprisma.school.entity.Tutor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITutorRepository extends JpaRepository<Tutor, Long> {
    public Tutor findByLogin(Login login);
}
