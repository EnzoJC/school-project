package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "privileges", indexes = {
        @Index(name = "privileges_ak_1", columnList = "name", unique = true)
})
@Entity
@Getter
@Setter
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "privilege_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;
}