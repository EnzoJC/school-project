package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "religions")
@Entity
@Getter
@Setter
public class Religion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "religion_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;
}