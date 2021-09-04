package edu.colegiosprisma.school.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "levels")
public class Level {
    @Id
    @Column(name = "level_id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "level")
    List<Grade> grades;
}
