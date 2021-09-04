package edu.colegiosprisma.school.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "classes")
public class Class{
    @Id
    @Column(name="class_id")
    private int id;
/*
    @ManyToOne
    @JoinColumn(name = "section_id")
    Section section; ANDREA

    @ManyToOne
    @JoinColumn(name = "grade_id")
    Grade grade; IO

    @OneToOne(mappedBy = "teacher")
    private Teacher teacher; FALTA

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    Classroom classroom;*/

}