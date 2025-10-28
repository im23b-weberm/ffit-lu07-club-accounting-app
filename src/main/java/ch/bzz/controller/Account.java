package ch.bzz.controller;

import jakarta.persistence.*;     // pour les annotations JPA
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity                             // indique que c’est une entité JPA
@Table(name = "account")           // nom de la table dans la DB
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-incrément
    @Column(name = "id")
    private Long id;

    @Column(name = "account_number", nullable = false, unique = true)
    private Long accountNumber;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    // FK vers Project
    @ManyToOne(optional = false)
    @JoinColumn(name = "project_name", nullable = false)
    private Project project;
}
