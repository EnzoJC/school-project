package edu.colegiosprisma.school.repository;
import edu.colegiosprisma.school.entity.Login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoginRepository  extends JpaRepository<Login, Long>{
    public Login findByUser(String user);
    public Login registrar(Login login);
}
