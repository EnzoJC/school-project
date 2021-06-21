package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Parent;
import org.springframework.stereotype.Component;

@Component
public interface IParentService {
    Parent create(Parent parent);
}
