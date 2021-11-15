package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "occupations")
@Entity
@Getter
@Setter
public class Occupation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "occupation_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;
}