package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "students_disabilities")
@Getter
@Setter
public class StudentDisability {
    @EmbeddedId
    private StudentDisabilityId id;

    @ManyToOne
    @MapsId("typeDisabiltiyId")
    private TypeDisability typeDisability;

    @ManyToOne
    @MapsId("enrollmentFormId")
    private EnrollmentsForm enrollmentsForm;


    @Column(name = "have_disability_certificate", nullable = false)
    private Boolean haveDisabilityCertificate = false;

    @Column(name = "url_certificate", length = 100)
    private String urlCertificate;
}