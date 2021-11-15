package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
    @Where(clause = "is_active = 1")
    private PaymentType paymentType;

    @ManyToMany
    @JoinTable(
            name = "debts",
            joinColumns = @JoinColumn(name = "payment_id"),
            inverseJoinColumns = @JoinColumn(name = "transaction_id"))
    private Set<Transaction> transactions = new HashSet<>();

    public void addPayment(Transaction transaction) {
        this.transactions.add(transaction);
        transaction.getPayments().add(this);
    }

    public void removePayment(Transaction transaction) {
        this.transactions.remove(transaction);
        transaction.getPayments().remove(this);
    }
}