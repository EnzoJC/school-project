package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Table(name = "payments")
@Entity
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_detail_id", nullable = false)
    private Integer id;

    @Column(name = "billing_id")
    private Integer billingId;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "pay_date")
    private Date payDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pay_id", nullable = false)
    private Pay pay;

    @ManyToOne(optional = false)
    @JoinColumn(name = "enrollment_id", nullable = false)
    private Enrollment enrollment;

    @Column(name = "payment_status")
    private Boolean paymentStatus;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "expiration_date")
    private Date expirationDate;
}