package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Department;
import edu.colegiosprisma.school.entity.State;

import java.util.List;
import java.util.Set;

public interface IDepartmentService {
    List<Department> getAll();

    Department findById(String id);

    void deleteById(String id);

    Department update(Department department, String id);
}
