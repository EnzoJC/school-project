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
public class ParentServImpl implements IParentService{
    private final IParentRepository parentRepository;
    private final IRoleRepository roleRepository;

    @PersistenceContext // Esto es para que funcione el EntityManager
    // EntityManager es el encargado de gestionar las transacciones
    private EntityManager entityManager;

    public ParentServImpl(IParentRepository parentRepository, IRoleRepository roleRepository) {
        this.parentRepository = parentRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Parent createParent(Parent parent) {
        // Llama a un procedimiento almacenad
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_generate_id");
        // Indicando los tipos de datos de los parametros del procedimiento almacenado
        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.OUT);
        // Enviando el parámetro de entrada al procedimiento almacenado
        query.setParameter(1, "Parent");
        // Ejecutando el procedimiento almacenado
        query.execute();

        // Guardando el resultado del parámetro de salida en una variable
        // Genera un ID con el formato P0-AÑO-0000X (Ejemplo: P020210001)
        String id = (String) query.getSingleResult();

        parent.setId(id); // Asignando el ID al objeto
        parent.setType("Parent"); // Asignando el tipo de usuario al parent
        parent.setUsername(id); // Asignando el username al parent
        // Asignando una contraseña al parent
        parent.setPassword(new BCryptPasswordEncoder().encode(parent.getDocumentNumber()));
        // Creando una lista de roles vacía
        Set<Role> listaRolesParent = new HashSet<>();
        // Obteniendo el único rol para parent
        Role auxRole = roleRepository.findByName("ROLE_PARENT");
        // Añadiendo el rol a la lista
        listaRolesParent.add(auxRole);
        // Añadiendo la lista de roles al objeto parent
        parent.setRoles(listaRolesParent);

        parentRepository.save(parent);
        return parent;
    }

    @Override
    public List<Integer> verifyParentDuplicate(Parent parent) {
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
    public Parent selectByUsername(String username) {
        return (Parent) parentRepository.findByUsername(username);
    }

    /**
     * Se obtienen los valores del objeto Parent por medio de su Id
     * y se combina con un objeto que obtiene los datos del formulario
     */
    @Override
    public Parent update(Parent parent, String id) {
        // p: Objeto con todos los datos del objeto Parent
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
