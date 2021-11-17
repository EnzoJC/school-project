package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Department;

import java.util.List;

public interface IDepartmentService {
    List<Department> getAllDepartments();
    Department getDepartment(String id);
}
