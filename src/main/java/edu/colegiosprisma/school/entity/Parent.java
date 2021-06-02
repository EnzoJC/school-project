package edu.colegiosprisma.school.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name="parents")
@DiscriminatorValue(value="Parent")
public class Parent extends User{
    @Id
    @Column(name="id")
    private String id;
    @Column(name="family_relationship")
    private String familyRelationship;
}
