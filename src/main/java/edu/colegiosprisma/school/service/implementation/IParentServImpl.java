package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Parent;
import edu.colegiosprisma.school.service.IParentService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.Date;

@Service
public class IParentServImpl implements IParentService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void guardar(Parent parent){

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("spInsertParent");

        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(6, Date.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(7, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(8, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(9, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(10, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(11, String.class, ParameterMode.IN);

        query.setParameter(1, parent.getGivenNames());
        query.setParameter(2, parent.getFirstLastName());
        query.setParameter(3, parent.getSecondLastName());
        query.setParameter(4, parent.getDocumentType());
        query.setParameter(5, parent.getDocumentNumber());
        query.setParameter(6, parent.getBirthDate());
        query.setParameter(7, parent.getAddress());
        query.setParameter(8, parent.getGender());
        query.setParameter(9, parent.getNationality());
        query.setParameter(10, parent.getPhoneNumber());
        query.setParameter(11, parent.getEmailAddress());

        query.execute();

    }
}
