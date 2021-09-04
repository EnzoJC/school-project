package edu.colegiosprisma.school.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "schedules")
public class Schedule {


    @Id
    @Column(name = "Schedule_id")
    private int Schedule_id;

    @Column (name = "start_time")
    private Date start_time;


    @Column (name = "end_time")
    private Date end_time;


//falta relacion manytomany

}