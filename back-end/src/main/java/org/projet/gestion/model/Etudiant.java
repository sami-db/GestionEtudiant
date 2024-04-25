package org.projet.gestion.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "etudiant")
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "photo")
    private String photo;

    @ManyToOne
    @JsonIgnore // Ignorer la propriété "classe" lors de la sérialisation JSON
    @JoinColumn(name = "classe_id")
    @JsonIgnoreProperties({"etudiants"}) // Ignorer la propriété "etudiants" de la classe lors de la sérialisation JSON pour éviter la récursivité
    private Classe classe; // Classe associée à cet étudiant

    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("etudiant") // Ignorer la propriété "etudiant" lors de la sérialisation JSON pour éviter la récursivité
    private Set<Note> notes = new HashSet<>(); // Notes associées à cet étudiant
}
