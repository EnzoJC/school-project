package edu.colegiosprisma.school.entity;

import javax.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Entity
@Data
@Table(name="Login")
public class Login implements Serializable {
    private static final long serialVersUID = 1L;

    @Id
    @Column(name="LoginId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long LoginId;
    private String User;
    private String Password;

    @ManyToOne
    @JoinColumn(name="RoleId")
    private Role role;

}
