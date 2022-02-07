package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Department;
import edu.colegiosprisma.school.repository.IDepartmentRepository;
import edu.colegiosprisma.school.service.IDepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServImpl implements IDepartmentService {
    private final IDepartmentRepository departmentRepository;

    public DepartmentServImpl(IDepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findById(String id) {
        if (departmentRepository.findById(id).isPresent()) {
            return departmentRepository.findById(id).get();
        }
        return new Department();
    }

    @Override
    public void deleteById(String id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Department update(Department department, String id) {
        return null;
    }
}
