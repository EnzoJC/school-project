package edu.colegiosprisma.school.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

@Table(name = "roles", indexes = {
        @Index(name = "roles_ak_1", columnList = "name", unique = true)
})
@Entity
@Getter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id"))
    private Set<Privilege> privileges;
}