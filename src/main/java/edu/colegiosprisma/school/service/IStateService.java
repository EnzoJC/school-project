package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.State;
import edu.colegiosprisma.school.entity.Student;

import java.util.Set;

public interface IStateService {
    State findById(Integer id);

    Set<State> getAll();

    void deleteById(Integer id);

    State update(State state, Integer id);
}
