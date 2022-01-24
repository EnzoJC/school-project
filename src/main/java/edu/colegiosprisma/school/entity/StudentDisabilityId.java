package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class StudentDisabilityId implements Serializable {
    private static final long serialVersionUID = -3839315610188303341L;
    @Column(name = "type_disability_id", nullable = false)
    private Integer typeDisabilityId;
    @Column(name = "enrollment_form_id", nullable = false, length = 10)
    private String enrollmentFormId;
}