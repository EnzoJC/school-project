package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Parent;
import edu.colegiosprisma.school.entity.Role;
import edu.colegiosprisma.school.repository.IParentRepository;
import edu.colegiosprisma.school.repository.IRoleRepository;
import edu.colegiosprisma.school.service.IParentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParentServImpl implements IParentService{
    @Autowired
    private IParentRepository parentRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Parent create(Parent parent) {
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
        List<Role> listaRolesParent = new ArrayList<>();
        // Obteniendo el único rol para parent
        Role auxRole = roleRepository.findByName("ROLE_PARENT");
        // Añadiendo el rol a la lista
        listaRolesParent.add(auxRole);
        // Añadiendo la lista de roles al objeto parent
        parent.setRoles(listaRolesParent);

        return parentRepository.save(parent);
    }

    @Override
    public Parent selectByUsername(String username) {
        return (Parent) parentRepository.findByUsername(username);
    }
}
