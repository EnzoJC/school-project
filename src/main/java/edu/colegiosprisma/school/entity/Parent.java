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
@PrimaryKeyJoinColumn(name = "parent_id")
public class Parent extends User{
    @Column(name = "occupation", length = 50)
    private String occupation;

    @Size(min = 9, max = 20)
    @NotEmpty
    @Pattern(regexp = "^[0-9]+$", message = "El numero de teléfono solo debe ser números")
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Email(message = "El correo electrónico no es válido")
    @Column(name = "email", nullable = false, length = 20)
    private String email;

    @OneToMany(mappedBy = "parent")
    List<Student> students;
}