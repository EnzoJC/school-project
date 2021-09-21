package edu.colegiosprisma.school.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

// Permite indicar que la clase está correlacionada con una tabla de la base datos
@Entity
// Permite que se generen los Getter y Setter automáticamente
@Getter
@Setter
// Permite especificar los detalles de la tabla, en este caso su nombre
@Table(name = "users")
// Define la estrategia de herencia que se utilizará para una jerarquía de clases de entidad
// En este caso se usa JOINED para que tabla se asigne a una clase
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    // Permite especificar la PK de la entidad
    @Id
    // Permite definir aspectos de la columna
    @Column(name = "user_id", nullable = false, length = 10)
    private String id;
    @Column(name = "given_names")
    private String givenNames;
    @Column(name = "first_last_name")
    private String firstLastName;
    @Column(name = "second_last_name")
    private String secondLastName;
    @Column(name = "document_type")
    private String documentType;
    @Column(name = "document_number", unique = true)
    private String documentNumber;
    // Permite mapear las fechas de la base de datos de forma simple,
    // en este caso solo se tomará la fecha (sin hora)
    @Temporal(TemporalType.DATE)
    // Indica como se debe formatear la fecha
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth_date")
    private Date birthDate;
    @Column(name = "address")
    private String address;
    @Column(name = "gender")
    private String gender;
    @Column(name = "nationality")
    private String nationality;
    @Column(name = "phone", unique = true)
    private String phoneNumber;
    @Column(name = "email", unique = true)
    private String emailAddress;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "is_active")
    private boolean status;
    @Column(name = "type")
    private String type;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    List<Role> roles;
}
