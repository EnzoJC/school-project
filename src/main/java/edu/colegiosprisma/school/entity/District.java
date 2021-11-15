package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "districts")
@Entity
@Getter
@Setter
public class District {
    @Id
    @Column(name = "district_id", nullable = false, length = 10)
    private String id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "province_id", nullable = false)
    private Province province;
}