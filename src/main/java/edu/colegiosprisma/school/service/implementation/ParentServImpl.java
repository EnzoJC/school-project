package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Parent;
import edu.colegiosprisma.school.entity.Role;
import edu.colegiosprisma.school.entity.User;
import edu.colegiosprisma.school.repository.IParentRepository;
import edu.colegiosprisma.school.repository.IRoleRepository;
import edu.colegiosprisma.school.service.IParentService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ParentServImpl implements IParentService {
    private final IParentRepository parentRepository;
    private final IRoleRepository roleRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public ParentServImpl(IParentRepository parentRepository, IRoleRepository roleRepository) {
        this.parentRepository = parentRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Parent create(Parent parent) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_generate_id");
        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.OUT);
        query.setParameter(1, "Parent");
        query.execute();
        String id = (String) query.getSingleResult();

        parent.setId(id);
        parent.setType("Parent");
        parent.setUsername(id);
        parent.setPassword(new BCryptPasswordEncoder().encode(parent.getDocumentNumber()));
        Set<Role> listaRolesParent = new HashSet<>();
        Role auxRole = roleRepository.findByName("ROLE_PARENT");
        listaRolesParent.add(auxRole);
        parent.setRoles(listaRolesParent);

        parentRepository.save(parent);
        return parent;
    }

    @Override
    public List<Integer> verifyDuplicate(Parent parent) {
        User parentWithDocumentNumber = parentRepository.findByDocumentNumber(parent.getDocumentNumber());
        Parent parentWithEmail = parentRepository.findByEmail(parent.getEmail());
        Parent parentWithPhone = parentRepository.findByPhone(parent.getPhone());

        List<Integer> lista = new ArrayList<>();

        if (parentWithDocumentNumber != null) {
            lista.add(1);
        }
        if (parentWithEmail != null) {
            lista.add(2);
        }
        if (parentWithPhone != null) {
            lista.add(3);
        }
        return lista;
    }

    @Override
    public Parent findByUsername(String username) {
        return (Parent) parentRepository.findByUsername(username);
    }

    @Override
    public Parent update(Parent parent, String id) {
        Parent p = (Parent) parentRepository.findByUsername(id);
        p.setGivenNames(parent.getGivenNames());
        p.setFirstLastName(parent.getFirstLastName());
        p.setSecondLastName(parent.getSecondLastName());
        p.setPhone(parent.getPhone());
        p.setEmail(parent.getEmail());
        p.setAddress(parent.getAddress());
        return parentRepository.save(p);
    }
}
