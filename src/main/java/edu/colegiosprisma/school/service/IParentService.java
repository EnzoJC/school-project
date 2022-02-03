package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Parent;
import org.springframework.ui.Model;

public interface IParentService {
    Parent create(Parent parent);

    Boolean isDuplicate(Parent parent, Model model);

    Boolean isDuplicatePhone(String phone);

    Boolean isDuplicateEmail(String email);

    Boolean isDuplicateDocumentNumber(String documentNumber);

    Parent findByUsername(String username);

    Parent update(Parent parent, String id);
}
