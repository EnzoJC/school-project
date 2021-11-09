package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "payment_discounts")
@Entity
@Getter
@Setter
public class PaymentDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_discount_id", nullable = false)
    private Integer id;

    @Column(name = "percentage", nullable = false)
    private Integer percentage;

    @Column(name = "date_created", nullable = false)
    private Instant dateCreated;

    @Column(name = "valid_from", nullable = false)
    private Instant validFrom;

    @Column(name = "valid_until", nullable = false)
    private Instant validUntil;

    @ManyToOne(optional = false)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;
}