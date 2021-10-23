package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "debts")
@Entity
@Getter
@Setter
public class Debt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "debt_id", nullable = false)
    private Integer id;

    @Column(name = "billing_id")
    private Integer billingId;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @ManyToOne(optional = false)
    @JoinColumn(name = "enrollment_id", nullable = false)
    private Enrollment enrollment;

    @Column(name = "payment_status", nullable = false)
    private Boolean paymentStatus = false;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;
}