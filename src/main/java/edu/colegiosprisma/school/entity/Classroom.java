package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "classrooms")
@Entity
@Getter
@Setter
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classroom_id", nullable = false)
    private Integer id;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "name", nullable = false, length = 50)
    private String name;
}