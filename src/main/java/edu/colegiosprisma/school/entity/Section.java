package edu.colegiosprisma.school.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "sections")
public class Section{
    @Id
    @Column(name="section_id")
    private int id;
    @Column(name="name")
    private String name;
/*
    @OneToMany(mappedBy = "section")
    List<Class> classes;*/
}