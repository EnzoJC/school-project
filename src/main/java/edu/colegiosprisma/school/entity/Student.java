package edu.colegiosprisma.school.entity;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "student_id")
public class Student extends User{

    @Column(name="student_email")
    private String studentEmail;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    Parent parent;

    @ManyToOne
    @JoinColumn(name = "relationship_id")
    private Relationship relationship;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    @Where(clause = "current_year = true")
    private List<Enrollment> enrollments;
}
