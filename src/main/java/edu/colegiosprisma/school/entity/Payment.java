package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "payments")
@Entity
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false)
    private Integer id;

    @Column(name = "description", nullable = false, length = 50)
    private String description;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @ManyToOne(optional = false)
    @JoinColumn(name = "payment_type_id", nullable = false)
    private PaymentType paymentType;
}