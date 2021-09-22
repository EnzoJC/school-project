package edu.colegiosprisma.school.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "courses")
public class Course {
    @Id
    @Column(name = "course_id")
    private  Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "academic_hours")
    private int academicHours;
    @ManyToMany
    Set<Grade> grades;
}
