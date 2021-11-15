package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "departments")
@Entity
@Getter
@Setter
public class Department {
    @Id
    @Column(name = "department_id", nullable = false, length = 10)
    private String id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;
}