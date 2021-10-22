package edu.colegiosprisma.school.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "roles_privileges")
@Entity
public class RolesPrivilege {
    @EmbeddedId
    private RolesPrivilegeId id;

    public RolesPrivilegeId getId() {
        return id;
    }

    public void setId(RolesPrivilegeId id) {
        this.id = id;
    }
}