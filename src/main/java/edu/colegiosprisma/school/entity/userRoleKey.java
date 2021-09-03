package edu.colegiosprisma.school.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class userRoleKey implements Serializable {
    @Column(name = "user_id")
    String userId;

    @Column(name = "role_id")
    Long roleId;
}
