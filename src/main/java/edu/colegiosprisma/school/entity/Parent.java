package edu.colegiosprisma.school.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = "parents")
@PrimaryKeyJoinColumn(name = "id")
public class Parent extends User{

    @Column(name="family_relationship")
    private String familyRelationship;
}
