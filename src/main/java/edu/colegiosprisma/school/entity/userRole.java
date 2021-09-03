package edu.colegiosprisma.school.entity;

import javax.persistence.*;

@Entity
public class userRole {
    @EmbeddedId
    userRoleKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    Role role;
}
