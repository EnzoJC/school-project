package edu.colegiosprisma.school.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "pays")
@Entity
@Getter
@Setter
public class Pay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_id", nullable = false)
    private Integer id;

    @Column(name = "description", nullable = false, length = 50)
    private String description;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "start_date")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "expiration_date")
    private Date expirationDate;
}