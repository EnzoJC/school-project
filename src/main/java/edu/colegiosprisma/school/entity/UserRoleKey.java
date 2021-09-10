package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class UserRoleKey implements Serializable {
    @Column(name = "user_id")
    String userId;

    @Column(name = "role_id")
    Long roleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRoleKey that = (UserRoleKey) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        return roleId != null ? roleId.equals(that.roleId) : that.roleId == null;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        return result;
    }
}
