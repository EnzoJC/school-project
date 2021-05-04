package edu.colegiosprisma.school.entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="Tutor")
public class Tutor {

    @Id
    @Column(name="TutorId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String TutorId;

    @Column(name = "GivenNames", nullable = false, length = 50)
    private String GivenNames;

    @Column(name = "FirstLastName", nullable = false, length = 50)
    private String FirstLastName;

    @Column(name = "SecondLastName", nullable = false, length = 50)
    private String SecondLastName;

    @Column(name = "IdCard", nullable = false)
    private Long IdCard;

    @Column(name = "PhoneNumber", nullable = false)
    private Long PhoneNumber;

    @Column(name = "FamilyRelationship", nullable = false, length = 50)
    private String FamilyRelationship;

    @Column(name = "Status", nullable = false)
    private boolean Status;

    @Column(name = "EmailAddress", nullable = false, length = 50)
    private String EmailAddress;

    @ManyToOne
    @JoinColumn(name="IdentificationId")
    private Identification Identification;

    @OneToOne
    @JoinColumn(name="LoginId")
    private Login Login;

}
