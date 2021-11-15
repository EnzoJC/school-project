package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "blood_types")
@Entity
@Getter
@Setter
public class BloodType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blood_type_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 10)
    private String name;
}