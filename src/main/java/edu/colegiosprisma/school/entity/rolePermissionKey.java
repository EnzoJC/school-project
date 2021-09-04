package edu.colegiosprisma.school.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class rolePermissionKey implements Serializable {

    @Column(name = "role_id")
    int role_id;

    @Column(name = "permission_id")
    int permission_id;






}