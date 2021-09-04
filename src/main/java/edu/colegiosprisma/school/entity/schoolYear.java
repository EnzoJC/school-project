package edu.colegiosprisma.school.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "schoolyears")

public class schoolYear {


    @Id
    @Column(name = "school_year_id")
    private int school_year_id;

    @Column(name = "year")
    private int year;

    @Column(name = "start_date")
    private Date start_date;

    @Column(name = "finish_date")
    private Date finish_date;



}