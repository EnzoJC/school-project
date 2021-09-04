package edu.colegiosprisma.school.entity;


import javax.persistence.*;

@Entity
public class rolePermission{

    @EmbeddedId
    rolePermissionKey id;

    @ManyToOne
    @MapsId("role_id")
    @JoinColumn(name = "role_id")
    Role role;

    @ManyToOne
    @MapsId("permission_id")
    @JoinColumn(name = "permission_id")
    Permission permission;
}