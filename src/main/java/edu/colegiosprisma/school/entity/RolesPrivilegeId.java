package edu.colegiosprisma.school.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RolesPrivilegeId implements Serializable {
    private static final long serialVersionUID = 5145599829364448276L;
    @Column(name = "role_id", nullable = false)
    private Integer roleId;
    @Column(name = "privilege_id", nullable = false)
    private Integer privilegeId;

    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(privilegeId, roleId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RolesPrivilegeId entity = (RolesPrivilegeId) o;
        return Objects.equals(this.privilegeId, entity.privilegeId) &&
                Objects.equals(this.roleId, entity.roleId);
    }
}