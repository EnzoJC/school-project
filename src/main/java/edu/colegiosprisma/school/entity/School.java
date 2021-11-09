package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "school")
@Entity
@Getter
@Setter
public class School {
    @Id
    @Column(name = "ruc", nullable = false, length = 11)
    private String ruc;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "address", nullable = false, length = 50)
    private String address;
}