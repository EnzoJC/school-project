package edu.colegiosprisma.school.entity;

import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @Column(name = "id", nullable = false, length = 10)
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="status")
    private boolean status;
    @Column(name="type")
    private String type;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "users_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;
}
