package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Parent;

public interface IEmailService {
    String send(Parent parent, String templateEngine);
}
