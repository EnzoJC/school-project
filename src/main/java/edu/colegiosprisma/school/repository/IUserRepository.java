package edu.colegiosprisma.school.repository;
import edu.colegiosprisma.school.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, String>{
    public User findByUsername(String username);
}
