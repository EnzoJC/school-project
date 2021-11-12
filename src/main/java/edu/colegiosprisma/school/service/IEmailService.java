package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Parent;

public interface IEmailService {
    String sendEmail(Parent parent, String templateEngine);
}
