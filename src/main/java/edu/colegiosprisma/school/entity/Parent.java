package edu.colegiosprisma.school.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "parents")
@PrimaryKeyJoinColumn(name = "parent_id")
public class Parent extends User{

    @Column(name="family_relationship")
    private String familyRelationship;

    @OneToMany(mappedBy = "parent")
    List<Student> students;
}
