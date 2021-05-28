package edu.colegiosprisma.school.entity;

import javax.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@Table(name="users")
public class User implements Serializable {
    private static final long serialVersUID = 1L;

    @Id
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "username"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "id")
    private Parent parent;
}
