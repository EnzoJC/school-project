package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "scholarships")
@Entity
@Getter
@Setter
public class Scholarship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scholarship_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "percentage", nullable = false)
    private Integer percentage;
}