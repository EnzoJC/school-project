package edu.colegiosprisma.school.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table (name = "grades")
public class Grade {
    @Id
    @Column(name = "grade_id")
    private int grade_id;           //no lo c rick
    @Column(name = "order")
    private int order;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "level_id")
    Level level;
    @ManyToMany
    Set<Course> courses;
}
