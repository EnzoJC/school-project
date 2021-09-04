package edu.colegiosprisma.school.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "enrollments")
public class Enrollment{
    @Id
    @Column(name="enrollment_id")
    private int id;
/*
    @ManyToOne
    @JoinColumn(name = "school_year_id")
    SchoolYear schoolYear;

    @ManyToOne
    @JoinColumn(name = "student_id")
    Student student;*/

    @Column(name = "enrollment_status")
    private boolean enrollmentStatus;
/*
    @ManyToOne
    @JoinColumn(name = "grade_id")
    Grade grade;

    @ManyToOne
    @JoinColumn(name = "class_id")
    Class class;*/

}