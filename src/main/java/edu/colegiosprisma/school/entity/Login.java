package edu.colegiosprisma.school.entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="Login")
public class Login {

    @Id
    @Column(name="LoginId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long LoginId;

    @Column(name = "User", nullable = false, length = 50)
    private String User;

    @Column(name = "Password", nullable = false, length = 50)
    private String Password;

    @Column(name = "Group", nullable = false, length = 50)
    private String Group;

    @Column(name = "RoleId", nullable = false)
    private Long RoleId;

}
