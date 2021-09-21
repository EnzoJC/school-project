package edu.colegiosprisma.school.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "privileges")
public class Privilege {
    @Id
    @Column(name = "privilege_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int permission_id;

    @Column(name = "name")
    private String name;

}