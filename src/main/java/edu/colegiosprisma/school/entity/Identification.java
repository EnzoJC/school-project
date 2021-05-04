package edu.colegiosprisma.school.entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="Identification")
public class Identification {

    @Id
    @Column(name="IdentificationId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdentificationId;

    @Column(name = "Type", nullable = false, length = 50)
    private String Type;

}
