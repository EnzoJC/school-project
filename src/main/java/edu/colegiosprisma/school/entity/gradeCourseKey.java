package edu.colegiosprisma.school.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class gradeCourseKey implements Serializable {
    @Column(name = "grade_id")
    int gradeId;
    @Column(name = "course_id")
    int courseId;
}
