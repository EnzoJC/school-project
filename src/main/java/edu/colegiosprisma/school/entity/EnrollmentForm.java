package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "enrollments_form", indexes = {
        @Index(name = "student_code", columnList = "student_code", unique = true)
})
public class EnrollmentForm {
    @Id
    @Column(name = "enrollment_form_id", nullable = false, length = 10)
    private String id;

    @Column(name = "student_code", nullable = false, length = 14)
    private String studentCode;

    @Column(name = "is_registered_birth", nullable = false)
    private Boolean isRegisteredBirth = false;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipe_of_birth_id", nullable = false)
    private TypeBirth tipeOfBirth;

    @Column(name = "is_complication_birth", nullable = false)
    private Boolean isComplicationBirth = false;

    @Column(name = "observation_birth", length = 100)
    private String observationBirth;

    @Column(name = "siblings", nullable = false)
    private Integer siblings;

    @Column(name = "order_sibling", nullable = false)
    private Integer orderSibling;

    @Column(name = "raised_head")
    private Integer raisedHead;

    @Column(name = "sit_down")
    private Integer sitDown;

    @Column(name = "crawl")
    private Integer crawl;

    @Column(name = "stoop_up")
    private Integer stoopUp;

    @Column(name = "walk")
    private Integer walk;

    @Column(name = "sphinter")
    private Integer sphinter;

    @Column(name = "first_words")
    private Integer firstWords;

    @Column(name = "speak_fluently")
    private Integer speakFluently;

    @Column(name = "allergy", nullable = false, length = 50)
    private String allergy;

    @ManyToOne(optional = false)
    @JoinColumn(name = "mother_tongue", nullable = false)
    private Language motherTongue;

    @ManyToOne(optional = false)
    @JoinColumn(name = "second_language", nullable = false)
    private Language secondLanguage;

    @ManyToOne(optional = false)
    @JoinColumn(name = "district_id", nullable = false)
    private District district;

    @ManyToOne(optional = false)
    @JoinColumn(name = "religion_id", nullable = false)
    private Religion religion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "blood_type_id", nullable = false)
    private BloodType bloodType;

    @ManyToMany
    @JoinTable(name = "parent_by_student",
            joinColumns = @JoinColumn(name = "enrollment_form_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_information_id"))
    private Set<ParentInformation> parents = new LinkedHashSet<>();

    @OneToMany(mappedBy = "enrollmentForm")
    private Set<StudentDisability> disabilities = new LinkedHashSet<>();

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "student_user_id")
    @MapsId
    private Student student;

}