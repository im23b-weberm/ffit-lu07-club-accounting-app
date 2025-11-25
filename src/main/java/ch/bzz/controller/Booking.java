package ch.bzz.controller;

import jakarta.persistence.*;      // pour les annotations JPA
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;    // PK

    @Column(name = "booking_number", nullable = false)
    private Integer bookingNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "text", length = 255)
    private String text;

    // FK vers Account (débit)
    @ManyToOne(optional = false)
    @JoinColumn(name = "debit_account", nullable = false)
    private Account debitAccount;

    // FK vers Account (crédit)
    @ManyToOne(optional = false)
    @JoinColumn(name = "credit_account", nullable = false)
    private Account creditAccount;

    @Column(name = "amount", nullable = false)
    private float amount;

    // FK vers Project
    @ManyToOne(optional = false)
    @JoinColumn(name = "project_name", nullable = false)
    private Project project;
}
