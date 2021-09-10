package edu.colegiosprisma.school.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    @Column(name="role_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(name="name")
    private String Name;

//    @ManyToMany
//    Set<User> users;
}