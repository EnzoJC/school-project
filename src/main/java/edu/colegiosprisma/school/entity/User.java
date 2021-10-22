package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

// Permite que se generen los Getter y Setter automáticamente
@Getter
@Setter
// Permite especificar los detalles de la tabla, en este caso su nombre y sus campos unicos
@Table(name = "users", indexes = {
        @Index(name = "email_ak_3", columnList = "email", unique = true),
        @Index(name = "document_number_ak_1", columnList = "document_number", unique = true),
        @Index(name = "username_ak_4", columnList = "username", unique = true),
        @Index(name = "phone_ak_2", columnList = "phone", unique = true)
})
// Permite indicar que la clase está correlacionada con una tabla de la base datos
@Entity
// Define la estrategia de herencia que se utilizará para una jerarquía de clases de entidad
// En este caso se usa JOINED para que tabla se asigne a una clase
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    // Permite especificar la PK de la entidad
    @Id
    // Permite definir aspectos de la columna
    @Column(name = "user_id", nullable = false, length = 10)
    private String id;

    @Column(name = "given_names", nullable = false, length = 50)
    private String givenNames;

    @Column(name = "first_last_name", nullable = false, length = 50)
    private String firstLastName;

    @Column(name = "second_last_name", nullable = false, length = 50)
    private String secondLastName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "document_type_id", nullable = false)
    private DocumentType documentType;

    @Column(name = "document_number", nullable = false, length = 20)
    private String documentNumber;

    // Permite mapear las fechas de la base de datos de forma simple,
    // en este caso solo se tomará la fecha (sin hora)
    @Temporal(TemporalType.DATE)
    // Indica como se debe formatear la fecha
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @ManyToOne(optional = false)
    @JoinColumn(name = "gender_id", nullable = false)
    private Gender gender;

    @ManyToOne(optional = false)
    @JoinColumn(name = "nationality_id", nullable = false)
    private Nationality nationality;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "username", nullable = false, length = 10)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Column(name = "is_active", nullable = false, columnDefinition = "boolean DEFAULT true")
    private Boolean isActive = true;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    List<Role> roles;

    public int getAge() {
        LocalDate localDate = Instant.ofEpochMilli(birthDate.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        int year  = localDate.getYear();
        int month = localDate.getMonthValue();
        int day   = localDate.getDayOfMonth();
        return Period.between(LocalDate.of(year, month, day), LocalDate.now()).getYears();
    }

}