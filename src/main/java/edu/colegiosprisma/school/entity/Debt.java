package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "debts")
@Entity
@Getter
@Setter
public class Debt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "debt_id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @ManyToOne(optional = false)
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;
}