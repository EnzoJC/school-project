//package edu.colegiosprisma.school.service.implementation;
//
//import edu.colegiosprisma.school.entity.Parent;
//import edu.colegiosprisma.school.repository.IParentRepository;
//import edu.colegiosprisma.school.service.IParentService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import javax.persistence.EntityManager;
//import javax.persistence.ParameterMode;
//import javax.persistence.PersistenceContext;
//import javax.persistence.StoredProcedureQuery;
//import java.util.List;
//
//@Service
//public class IParentServImpl implements IParentService {
//    @Autowired
//    private IParentRepository parentRepository;
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public Parent create(Parent parent) {
//        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_generate_id");
//
//        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
//        query.registerStoredProcedureParameter(2, String.class, ParameterMode.OUT);
//
//        query.setParameter(1, "Parent");
//        query.execute();
//
//        String id = (String) query.getSingleResult();
//        System.out.println("ID: " + id);
//
//        parent.setId(id);
//        parent.setType("Parent");
//        parent.setUsername(id);
//        parent.setPassword(new BCryptPasswordEncoder().encode(parent.getDocumentNumber()));
//
//        return parentRepository.save(parent);
//    }
//}
