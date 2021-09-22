package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "family_relationships", indexes = {
        @Index(name = "name_ak_1", columnList = "name", unique = true)
})
@Entity
public class FamilyRelationship {
    @Id
    @Column(name = "family_relationship_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;
}