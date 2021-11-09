package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "relationships", indexes = {
        @Index(name = "family_relationships_ak_1", columnList = "name", unique = true)
})
@Entity
@Getter
@Setter
public class Relationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "relationship_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;
}