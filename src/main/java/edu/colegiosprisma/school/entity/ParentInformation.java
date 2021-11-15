package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "parents_information", indexes = {
        @Index(name = "document_number_ak_2", columnList = "document_number", unique = true)
})
@Entity
@Getter
@Setter
public class ParentInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parent_information_id", nullable = false)
    private Integer id;

    @Column(name = "given_names", nullable = false, length = 50)
    private String givenNames;

    @Column(name = "first_last_name", nullable = false, length = 50)
    private String firstLastName;

    @Column(name = "second_last_name", nullable = false, length = 50)
    private String secondLastName;

    @Column(name = "is_alive", nullable = false)
    private Boolean isAlive = false;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "live_with_student", nullable = false)
    private Boolean liveWithStudent = false;

    @ManyToOne(optional = false)
    @JoinColumn(name = "document_type_id", nullable = false)
    private DocumentType documentType;

    @Column(name = "document_number", nullable = false, length = 20)
    private String documentNumber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "education_degree_id", nullable = false)
    private EducationDegree educationDegree;

    @ManyToOne(optional = false)
    @JoinColumn(name = "occupation_id", nullable = false)
    private Occupation occupation;

    @ManyToOne(optional = false)
    @JoinColumn(name = "religions_religion_id", nullable = false)
    private Religion religions;
}