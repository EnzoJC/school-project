package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Enrollment;
import edu.colegiosprisma.school.entity.Role;
import edu.colegiosprisma.school.entity.Student;
import edu.colegiosprisma.school.entity.User;
import edu.colegiosprisma.school.repository.IRoleRepository;
import edu.colegiosprisma.school.repository.IStudentRepository;
import edu.colegiosprisma.school.service.IEnrollmentService;
import edu.colegiosprisma.school.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.*;

@Service
public class StudentServImpl implements IStudentService {
    private final IStudentRepository studentRepository;
    private final IRoleRepository roleRepository;
    private final IEnrollmentService enrollmentService;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public StudentServImpl(IStudentRepository studentRepository, IRoleRepository roleRepository,
                           IEnrollmentService enrollmentService) {
        this.studentRepository = studentRepository;
        this.roleRepository = roleRepository;
        this.enrollmentService = enrollmentService;
    }

    @Override
    public Student create(Student student, Enrollment enrollment) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_generate_id");
        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.OUT);
        query.setParameter(1, "Student");
        query.execute();

        String id = (String) query.getSingleResult();

        student.setId(id);
        student.setType("Student");
        student.setUsername(id);
        student.setPassword(new BCryptPasswordEncoder(4).encode(student.getDocumentNumber()));
        student.setStudentEmail(id + "@colegiosprisma.edu.pe");
        Set<Role> listaRolesStudent = new HashSet<>();
        Role auxRole = roleRepository.findByName("ROLE_STUDENT");
        listaRolesStudent.add(auxRole);
        student.setRoles(listaRolesStudent);

        Student s = studentRepository.save(student);
        enrollmentService.create(enrollment, s);
        return student;
    }

    @Override
    public Optional<User> findById(String studentId) {
        return studentRepository.findById(studentId);
    }

    @Override
    public List<Integer> verifyDuplicate(Student student) {
        User studentWithDocumentNumber = studentRepository.findByDocumentNumber(student.getDocumentNumber());
        List<Integer> lista = new ArrayList<>();

        if (studentWithDocumentNumber != null) {
            System.out.println("Student with document number: " + studentWithDocumentNumber.getDocumentNumber() + " already exists");
            lista.add(1);
        }
        return lista;
    }
}
