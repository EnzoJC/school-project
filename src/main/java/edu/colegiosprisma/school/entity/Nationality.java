package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "nationalities", indexes = {
        @Index(name = "nationalities_ak_1", columnList = "name", unique = true)
})
@Entity
@Getter
@Setter
public class Nationality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nationality_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;
}