package edu.colegiosprisma.school.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "classrooms")
public class Classroom{
    @Id
    @Column(name="classrooms_id")
    private int id;
    @Column(name = "capacity")
    private int capacity;
    @Column(name = "name")
    private String name;
/*
    @OneToMany(mappedBy = "classroom")
    List<Class> classes;*/
}