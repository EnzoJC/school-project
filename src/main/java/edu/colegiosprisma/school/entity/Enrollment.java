package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "enrollments")
@Entity
@Getter
@Setter
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_id", nullable = false)
    private Integer id;

    @Column(name = "current_year", nullable = false)
    private Boolean currentYear = false;

    @ManyToOne(optional = false)
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    @ManyToOne
    @JoinColumn(name = "scholarship_id")
    private Scholarship scholarship;

    @ManyToOne(optional = false)
    @JoinColumn(name = "school_year_id", nullable = false)
    private SchoolYear schoolYear;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class clase;

    @ManyToOne(optional = false)
    @JoinColumn(name = "grade_id", nullable = false)
    private Grade grade;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
}