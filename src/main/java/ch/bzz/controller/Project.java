package ch.bzz.controller;

import jakarta.persistence.*;      // pour les annotations JPA
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity                             // indique que cette classe est une entité JPA
@Table(name = "project")           // nom de la table dans la base
public class Project {

    @Id
    @Column(name = "project_name", nullable = false, unique = true, length = 100)
    private String projectName;     // PK

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;
}
