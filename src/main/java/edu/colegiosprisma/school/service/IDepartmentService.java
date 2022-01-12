package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Department;

import java.util.List;

public interface IDepartmentService {
    List<Department> getAll();

    Department findById(String id);
}
