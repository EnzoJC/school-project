package edu.colegiosprisma.school.entity;

import javax.persistence.*;

@Table(name = "classes")
@Entity
// @Entity es para indicar que esta clase es una entidad
public class Class {
    // @Id es para indicar que es la llave primaria
    @Id
    // @GeneratedValue es para indicar que es una llave primaria autoincrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column es para indicar que es una columna de la tabla
    @Column(name = "class_id", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}