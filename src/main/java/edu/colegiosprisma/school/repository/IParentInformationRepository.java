package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.ParentInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IParentInformationRepository extends JpaRepository<ParentInformation, Integer> {
}
