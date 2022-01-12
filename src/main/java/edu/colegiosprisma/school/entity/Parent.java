package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Table(name = "parents", indexes = {
        @Index(name = "phone_ak_p", columnList = "phone", unique = true),
        @Index(name = "email_ak_p", columnList = "email", unique = true)
})
@Entity
@Getter
@Setter
// @PrimaryKeyJoinColumn permite que el id del padre sea el id del parent
@PrimaryKeyJoinColumn(name = "parent_id")
public class Parent extends User {
    @OneToMany(mappedBy = "parent")
    List<Student> students;

    @Column(name = "occupation", length = 50)
    private String occupation;

    @Size(min = 9, max = 9, message = "El número de celular solo debe tener 9 dígitos ")
    @NotEmpty
    @Pattern(regexp = "^[0-9]{9}$", message = "El numero de teléfono solo debe ser números")
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Email(message = "El correo electrónico no es válido")
    @NotEmpty
    @Column(name = "email", nullable = false, length = 50)
    private String email;
}