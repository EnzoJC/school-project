package edu.colegiosprisma.school.entity;

import javax.persistence.*;

@Entity
public class gradeCourse {
    @EmbeddedId
    gradeCourseKey id;

    @ManyToOne
    @MapsId("gradeId")
    @JoinColumn(name = "grade_id")
    Grade grade;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    Course course;
    //faltan las relaciones manytomany
}
