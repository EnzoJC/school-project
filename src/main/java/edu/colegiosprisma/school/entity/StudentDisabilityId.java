package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class StudentDisabilityId implements Serializable {
    private static final long serialVersionUID = -3839315610188303341L;
    @Column(name = "type_disabiltiy_id", nullable = false)
    private Integer typeDisabiltiyId;
    @Column(name = "enrollment_form_id", nullable = false, length = 10)
    private String enrollmentFormId;

    @Override
    public int hashCode() {
        return Objects.hash(enrollmentFormId, typeDisabiltiyId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StudentDisabilityId entity = (StudentDisabilityId) o;
        return Objects.equals(this.enrollmentFormId, entity.enrollmentFormId) &&
                Objects.equals(this.typeDisabiltiyId, entity.typeDisabiltiyId);
    }
}