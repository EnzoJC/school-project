package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Parent;
import edu.colegiosprisma.school.entity.Role;
import edu.colegiosprisma.school.entity.User;
import edu.colegiosprisma.school.repository.IParentRepository;
import edu.colegiosprisma.school.repository.IRoleRepository;
import edu.colegiosprisma.school.service.IParentService;
import edu.colegiosprisma.school.util.PasswordUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
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
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("sp_generate_id")
                .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.OUT)
                .setParameter(1, "PARENT");

        query.execute();

        String id = (String) query.getSingleResult();

        parent.setId(id);
        parent.setType("Parent");
        parent.setUsername(id);
        parent.setPassword(PasswordUtil.encode(parent.getDocumentNumber()));
        Set<Role> listaRolesParent = Set.of(roleRepository.findByName("ROLE_PARENT"));
        parent.setRoles(listaRolesParent);

        parentRepository.save(parent);
        return parent;
    }

    @Override
    public Boolean isDuplicate(Parent parent, Model model) {
        boolean flag = false;
        if (isDuplicatePhone(parent.getPhone())) {
            model.addAttribute("duplicatePhone", "El número de teléfono ya existe");
            flag = true;
        }
        if (isDuplicateEmail(parent.getEmail())) {
            model.addAttribute("duplicateEmail", "El correo ya existe");
            flag = true;
        }
        if (isDuplicateDocumentNumber(parent.getDocumentNumber())) {
            model.addAttribute("duplicateDocumentNumber", "El número de documento ya existe");
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean isDuplicatePhone(String phone) {
        return parentRepository.findByPhone(phone) != null;
    }

    @Override
    public Boolean isDuplicateEmail(String email) {
        return parentRepository.findByEmail(email) != null;
    }

    @Override
    public Boolean isDuplicateDocumentNumber(String documentNumber) {
        return parentRepository.findByDocumentNumber(documentNumber) != null;
    }

    @Override
    public Parent findByUsername(String username) {
        return (Parent) parentRepository.findByUsername(username);
    }

    @Override
    public Parent update(Parent newParent, String id) {
        Parent parenFromDB = (Parent) parentRepository.findByUsername(id);
        parenFromDB.setGivenNames(newParent.getGivenNames());
        parenFromDB.setFirstLastName(newParent.getFirstLastName());
        parenFromDB.setSecondLastName(newParent.getSecondLastName());
        parenFromDB.setPhone(newParent.getPhone());
        parenFromDB.setEmail(newParent.getEmail());
        parenFromDB.setAddress(newParent.getAddress());
        return parentRepository.save(parenFromDB);
    }

    @Override
    public Optional<User> findById(String parentId) {
        return parentRepository.findById(parentId);
    }


    @Override
    public Set<User> getAll() {
        return new HashSet<>(parentRepository.findAll());
    }

    @Override
    public void deleteById(String id) {
        parentRepository.deleteById(id);
    }
}
