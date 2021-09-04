package edu.colegiosprisma.school.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "days")
public class Day{
    @Id
    @Column(name="day_id")
    private int id;
    @Column(name="name")
    private String name;
/*
    @OneToMany(mappedBy = "day")
    List<Schecule> schedules;*/
}