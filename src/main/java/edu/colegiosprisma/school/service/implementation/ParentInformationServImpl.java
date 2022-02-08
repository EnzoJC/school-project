package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Parent;
import edu.colegiosprisma.school.entity.ParentInformation;
import edu.colegiosprisma.school.entity.Role;
import edu.colegiosprisma.school.entity.User;
import edu.colegiosprisma.school.repository.IParentInformationRepository;
import edu.colegiosprisma.school.repository.IParentRepository;
import edu.colegiosprisma.school.repository.IRoleRepository;
import edu.colegiosprisma.school.service.IParentInformationService;
import edu.colegiosprisma.school.util.PasswordUtil;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ParentInformationServImpl implements IParentInformationService {
    private IParentInformationRepository parentInformationRepository;


    @PersistenceContext
    private EntityManager entityManager;

    public ParentInformationServImpl(IParentInformationRepository parentInformationRepository) {
        this.parentInformationRepository = parentInformationRepository;
    }

    @Override
    public ParentInformation create(ParentInformation parent) {
        parentInformationRepository.save(parent);
        return parent;
    }

    @Override
    public ParentInformation update(ParentInformation newParent, String id) {
      return null;
    }

    @Override
    public Optional<ParentInformation> findById(int id) {
        return parentInformationRepository.findById(id);
    }


    @Override
    public Set<ParentInformation> getAll() {
        return new HashSet<>(parentInformationRepository.findAll());
    }

    @Override
    public void deleteById(int id) {
        parentInformationRepository.deleteById(id);
    }
}
