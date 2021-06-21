package edu.colegiosprisma.school.repository;
import edu.colegiosprisma.school.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

//@NoRepositoryBean
@Repository
public interface IUserRepository extends JpaRepository<User, String>{
    User findByUsername(String username);
}
