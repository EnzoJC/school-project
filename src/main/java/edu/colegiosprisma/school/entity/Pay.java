package edu.colegiosprisma.school.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "pays")
public class Pay {
    @Id
    @Column(name = "pay_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "expiration_date")
    private Date expirationDate;
}
