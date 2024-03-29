package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "grades", indexes = {
        @Index(name = "grades_ak_1", columnList = "order", unique = true)
})
@Entity
@Getter
@Setter
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id", nullable = false)
    private Integer id;

    @Column(name = "`order`", nullable = false)
    private Integer order;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", nullable = false, length = 50)
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "level_id", nullable = false)
    private Level level;
}