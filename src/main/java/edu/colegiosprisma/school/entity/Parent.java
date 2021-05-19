package edu.colegiosprisma.school.entity;

import lombok.Data;
import org.springframework.data.jpa.repository.query.Procedure;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name="parent")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "spInsertParent",
                procedureName = "sp_insert_parent",
                parameters = {
                            @StoredProcedureParameter(name = "sp_given_names", type = String.class, mode = ParameterMode.IN),
                            @StoredProcedureParameter(name = "sp_first_last_name", type = String.class, mode = ParameterMode.IN),
                            @StoredProcedureParameter(name = "sp_second_last_name", type = String.class, mode = ParameterMode.IN),
                            @StoredProcedureParameter(name = "sp_document_type", type = String.class, mode = ParameterMode.IN),
                            @StoredProcedureParameter(name = "sp_document_number", type = String.class, mode = ParameterMode.IN),
                            @StoredProcedureParameter(name = "sp_birth_date", type = Date.class, mode = ParameterMode.IN),
                            @StoredProcedureParameter(name = "sp_address", type = String.class, mode = ParameterMode.IN),
                            @StoredProcedureParameter(name = "sp_gender", type = String.class, mode = ParameterMode.IN),
                            @StoredProcedureParameter(name = "sp_nationality", type = String.class, mode = ParameterMode.IN),
                            @StoredProcedureParameter(name = "sp_phone_number", type = String.class, mode = ParameterMode.IN),
                            @StoredProcedureParameter(name = "sp_email_address", type = String.class, mode = ParameterMode.IN)
                }),
})
public class Parent {
    @Id
    @Column(name="id")
    private String id;
    @Column(name="given_names")
    private String givenNames;
    @Column(name="first_last_name")
    private String firstLastName;
    @Column(name="second_last_name")
    private String secondLastName;
    @Column(name="document_type")
    private String documentType;
    @Column(name="document_number", unique=true)
    private String documentNumber;
    @Temporal(TemporalType.DATE)
    @Column(name="birth_date")
    private Date birthDate;
    @Column(name="address")
    private String address;
    @Column(name="gender")
    private String gender;
    @Column(name="nationality")
    private String nationality;
    @Column(name="phone_number", unique=true)
    private String phoneNumber;
    @Column(name="email_address", unique=true)
    private String emailAddress;
    @Column(name="family_relationship")
    private String familyRelationship;
    @Column(name="status")
    private boolean status;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private User user;
}
