package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "transactions")
@Entity
@Getter
@Setter
public class Transaction {
    @Id
    @Column(name = "transaction_id", nullable = false, length = 12)
    private String id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "enrollment_id", nullable = false)
    private Enrollment enrollment;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "school_ruc", nullable = false)
    private School school;

    @ManyToOne(optional = false)
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    @ManyToMany
    @JoinTable(
            name = "debts",
            joinColumns = @JoinColumn(name = "transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_id"))
    private Set<Payment> payments = new HashSet<>();

    public BigDecimal getTotalPaid(){
        BigDecimal total = BigDecimal.ZERO;
        for (Payment payment : payments) {
            total = total.add(payment.getAmount());
        }
        return total;
    }

    public void addPayment(Payment payment) {
        this.payments.add(payment);
        payment.getTransactions().add(this);
    }

    public void removePayment(Payment payment) {
        this.payments.remove(payment);
        payment.getTransactions().remove(this);
    }
}