package edu.colegiosprisma.school.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "students_disabilities")
public class StudentDisability {
    @EmbeddedId
    private StudentDisabilityId id;

    @ManyToOne
    @MapsId("typeDisabilityId")
    private EnrollmentForm enrollmentForm;

    @ManyToOne
    @MapsId("enrollmentFormId")
    private TypeDisability typeDisability;

    @Column(name = "have_disability_certificate", nullable = false)
    private Boolean haveDisabilityCertificate = false;

    @Column(name = "url_certificate", length = 100)
    private String urlCertificate;
}