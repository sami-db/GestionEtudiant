package org.projet.gestion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "note")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant de la note

    @Column(name = "valeur")
    private float valeur; // Valeur de la note

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etudiant_id")
    @JsonIgnore // Ignorer la propriété "etudiant" lors de la sérialisation JSON
    private Etudiant etudiant; // Étudiant associé à cette note

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partiedevoir_id")
    private PartieDevoir partieDevoir; // Partie du devoir associée à cette note
}
