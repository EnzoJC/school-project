package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStateRepository extends JpaRepository<State, Integer> {
}