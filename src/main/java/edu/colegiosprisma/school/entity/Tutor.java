package edu.colegiosprisma.school.entity;

import javax.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Entity
@Data
@Table(name="Tutor")
public class Tutor implements Serializable {
    private static final long serialVersUID = 1L;

    @Id
    @Column(name="TutorId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String TutorId;

    private String GivenNames;
    private String FirstLastName;
    private String SecondLastName;
    private Long IdCard;
    private Long PhoneNumber;
    private String FamilyRelationship;
    private boolean Status;
    private String EmailAddress;

    @ManyToOne
    @JoinColumn(name="IdentificationId")
    private Identification identification;

    @OneToOne
    @JoinColumn(name="LoginId")
    private Login login;

}
