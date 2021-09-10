package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "users_roles")
public class UserRole {
    @EmbeddedId
    UserRoleKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    Role role;
}
