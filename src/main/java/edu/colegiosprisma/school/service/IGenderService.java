package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Gender;
import org.springframework.stereotype.Component;

import java.util.List;

public interface IGenderService {
    List<Gender> getAllGenders();
}
