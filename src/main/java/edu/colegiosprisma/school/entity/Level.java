package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "levels", indexes = {
        @Index(name = "Level_ak_1", columnList = "name", unique = true)
})
@Entity
@Getter
@Setter
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "level_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 10)
    private String name;

    @Column(name = "description", nullable = false, length = 50)
    private String description;
}