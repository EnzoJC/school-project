package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.*;
import edu.colegiosprisma.school.repository.IParentRepository;
import edu.colegiosprisma.school.repository.IRoleRepository;
import edu.colegiosprisma.school.repository.IStudentRepository;
import edu.colegiosprisma.school.service.IEnrollmentService;
import edu.colegiosprisma.school.service.IStudentService;
import edu.colegiosprisma.school.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentServImpl implements IStudentService {
    private final IStudentRepository studentRepository;
    private final IRoleRepository roleRepository;
    private final IEnrollmentService enrollmentService;
    private final IParentRepository parentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public StudentServImpl(IStudentRepository studentRepository, IRoleRepository roleRepository,
                           IEnrollmentService enrollmentService, IParentRepository parentRepository) {
        this.studentRepository = studentRepository;
        this.roleRepository = roleRepository;
        this.enrollmentService = enrollmentService;
        this.parentRepository = parentRepository;
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
        student.setPassword(PasswordUtil.encode(student.getDocumentNumber()));
        student.setStudentEmail(id + "@colegiosprisma.edu.pe");
        Set<Role> listaRolesStudent = Set.of(roleRepository.findByName("ROLE_STUDENT"));
        student.setRoles(listaRolesStudent);
        student.setParent(getParentLogged());
        Student s = studentRepository.save(student);
        enrollmentService.create(enrollment, s);
        return student;
    }

    @Override
    public Optional<User> findById(String studentId) {
        return studentRepository.findById(studentId);
    }

    @Override
    public Student findByUsername(String user) {
        return (Student) studentRepository.findByUsername(user);
    }

    @Override
    public Set<User> getAll() {
        return new LinkedHashSet<>(studentRepository.findAll());
    }

    @Override
    public void deleteById(String id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student update(Student student, String id) {
        return null;
    }

    @Override
    public Boolean isDuplicateDocumentNumber(String documentNumber) {
        return studentRepository.findByDocumentNumber(documentNumber) != null;
    }

    @Override
    public Boolean isDuplicate(Student student, Model model) {
        if (isDuplicateDocumentNumber(student.getDocumentNumber())) {
            model.addAttribute("duplicateDocumentNumber", "El número de documento ya existe");
            return true;
        }
        return false;
    }

    @Override
    public Parent getParentLogged() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return (Parent) parentRepository.findByUsername(userDetails.getUsername());
    }
}
