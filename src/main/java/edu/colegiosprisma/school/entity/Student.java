package edu.colegiosprisma.school.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "id")
public class Student extends User{

    @Column(name="student_email")
    private String studentEmail;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    Parent parent;
}
