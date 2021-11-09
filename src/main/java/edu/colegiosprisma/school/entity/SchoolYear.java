package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "school_years")
@Entity
@Getter
@Setter
public class SchoolYear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_year_id", nullable = false)
    private Integer id;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "finish_date", nullable = false)
    private LocalDate finishDate;
}