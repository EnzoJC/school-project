package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "document_types", indexes = {
        @Index(name = "document_types_ak_1", columnList = "name", unique = true)
})
@Entity
@Getter
@Setter
public class DocumentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_type_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "length", nullable = false)
    private Integer length;
}