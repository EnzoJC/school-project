package edu.colegiosprisma.school.entity;

import edu.colegiosprisma.school.validation.IdentityCard;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Table(name = "users", indexes = {
        @Index(name = "document_number_ak_u", columnList = "document_number", unique = true),
        @Index(name = "username_ak_u", columnList = "username", unique = true)
})
@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@IdentityCard(message = "Revise bien su número de documento de identidad", fieldType = "documentType", fieldLength = "documentNumber")
public class User {
    private static final String REGEX_FOR_NAMES = "^[a-zA-ZÀ-ÿ\\u00f1\\u00d1]{2,25}(\\s{1}[a-zA-ZÀ-ÿ\\u00f1\\u00d1]{2,25})?$";
    @Id
    @Column(name = "user_id", nullable = false, length = 10)
    private String id;

    @Size(min = 2, message = "{validation.user.name.size.short}")
    @Size(max = 50, message = "{validation.user.name.size.long}")
    @NotEmpty(message = "El nombre es obligatorio")
    @Pattern(regexp = REGEX_FOR_NAMES, message = "El nombre solo puede contener letras")
    @Column(name = "given_names", nullable = false, length = 50)
    private String givenNames;

    @Size(min = 2, max = 50, message = "El primer apellido es muy corto")
    @NotEmpty(message = "El primer apellido es obligatorio")
    @Pattern(regexp = REGEX_FOR_NAMES, message = "El primer apellido solo puede contener letras")
    @Column(name = "first_last_name", nullable = false, length = 50)
    private String firstLastName;

    @Size(min = 2, max = 50, message = "El segundo apellido es muy corto")
    @NotEmpty(message = "El segundo apellido es obligatorio")
    @Pattern(regexp = REGEX_FOR_NAMES, message = "El segundo apellido solo puede contener letras")
    @Column(name = "second_last_name", nullable = false, length = 50)
    private String secondLastName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "document_type_id", nullable = false)
    private DocumentType documentType;

    @NotEmpty(message = "El número de documento es obligatorio")
    @Pattern(regexp = "^[0-9]+$", message = "El número de documento debe contener solo números")
    @Column(name = "document_number", nullable = false, length = 20)
    private String documentNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth_date", nullable = false)
    protected LocalDate birthDate;

    @Column(name = "address", nullable = false, length = 50)
    // @Address
    private String address;

    @ManyToOne(optional = false)
    @JoinColumn(name = "gender_id", nullable = false)
    private Gender gender;

    @ManyToOne(optional = false)
    @JoinColumn(name = "nationality_id", nullable = false)
    private Nationality nationality;

    @Column(name = "username", length = 10)
    private String username;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Column(name = "is_active", nullable = false, columnDefinition = "boolean DEFAULT true")
    private Boolean isActive = false;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}