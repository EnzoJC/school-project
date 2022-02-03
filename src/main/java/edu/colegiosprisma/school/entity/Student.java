package edu.colegiosprisma.school.entity;

import edu.colegiosprisma.school.entity.validation.RangeAge;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Table(name = "students", indexes = {
        @Index(name = "students_ak_1", columnList = "student_email", unique = true)
})
@Entity
@Getter
@Setter
// @PrimaryKeyJoinColumn permite que el id de la clase padre sea el id del student
@PrimaryKeyJoinColumn(name = "student_id")
@RangeAge(min = 5, max = 18, fieldDate = "birthDate", message = "Debe ser menor de 18 años")
public class Student extends User {
    @Column(name = "student_email", nullable = false, length = 50)
    private String studentEmail;

    @ManyToOne(optional = false)
    @JoinColumn(name = "parent_id", nullable = false)
    private Parent parent;

    @ManyToOne
    @JoinColumn(name = "relationship_id")
    private Relationship relationship;

    @ManyToOne
    @JoinColumn(name = "last_grade_id")
    private Grade lastGrade;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    @Where(clause = "current_year = true")
    private List<Enrollment> enrollments;
}