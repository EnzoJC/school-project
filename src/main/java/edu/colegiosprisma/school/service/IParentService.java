package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Parent;
import edu.colegiosprisma.school.entity.User;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IParentService {
    Parent create(Parent parent);

    Boolean isDuplicate(Parent parent, Model model);

    Boolean isDuplicatePhone(String phone);

    Boolean isDuplicateEmail(String email);

    Boolean isDuplicateDocumentNumber(String documentNumber);

    Parent findByUsername(String username);

    Parent update(Parent newParent, String id);

    Optional<User> findById(String parentId);

    List<Parent> getAll();

    void deleteById(String id);
}
