package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Parent;
import edu.colegiosprisma.school.entity.ParentInformation;
import edu.colegiosprisma.school.entity.User;

import java.util.Optional;
import java.util.Set;

public interface IParentInformationService {
    ParentInformation create(ParentInformation parent);

    ParentInformation update(ParentInformation newParent, String id);

    Optional<ParentInformation> findById(int id);

    Set<ParentInformation> getAll();

    void deleteById(int id);
}
