package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<Debt> debts;
}