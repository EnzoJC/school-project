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

    @ManyToOne(optional = false)
    @JoinColumn(name = "school_year_id", nullable = false)
    private SchoolYear schoolYear;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "enrollment_status", nullable = false)
    private Boolean enrollmentStatus = false;

    @ManyToOne(optional = false)
    @JoinColumn(name = "grade_id", nullable = false)
    private Grade grade;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class class_;

    @Column(name = "current_year", nullable = false)
    private Boolean currentYear = false;
}