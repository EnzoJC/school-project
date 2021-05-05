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
    private String Type;

}
