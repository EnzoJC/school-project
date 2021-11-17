package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Department;
import edu.colegiosprisma.school.repository.IDepartmentRepository;
import edu.colegiosprisma.school.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServImpl implements IDepartmentService {
    @Autowired
    private IDepartmentRepository departmentRepository;
    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartment(String id) {
        return departmentRepository.findById(id).get();
    }
}
