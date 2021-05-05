package edu.colegiosprisma.school.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "role")
public class Role implements Serializable {
    private static final long serialVersUID = 1L;

    @Id
    @Column(name="RoleId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long RoleId;
    private String Name;
}
