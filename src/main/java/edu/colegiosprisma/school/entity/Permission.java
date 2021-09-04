package edu.colegiosprisma.school.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table (name = "permissions")
public class Permission {
    @Id
    @Column (name = "permission_id")
    private int permission_id;

    @Column (name = "name")
    private String name;

}