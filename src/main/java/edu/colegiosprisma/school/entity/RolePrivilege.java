package edu.colegiosprisma.school.entity;


import javax.persistence.*;

@Entity
public class RolePrivilege {

    @EmbeddedId
    RolePrivilegeKey id;

    @ManyToOne
    @MapsId("role_id")
    @JoinColumn(name = "role_id")
    Role role;

    @ManyToOne
    @MapsId("permission_id")
    @JoinColumn(name = "permission_id")
    Privilege privilege;
}