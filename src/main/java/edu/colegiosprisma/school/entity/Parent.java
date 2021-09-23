package edu.colegiosprisma.school.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Table(name = "parents")
@Entity
@PrimaryKeyJoinColumn(name = "parent_id")
public class Parent extends User{
    @ManyToOne
    @JoinColumn(name = "family_relationship_id")
    private FamilyRelationship familyRelationship;

    @OneToMany(mappedBy = "parent")
    List<Student> students;
}