package org.projet.gestion.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "partiedevoir")
public class PartieDevoir {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant de la partie du devoir

    @Column(name = "points")
    private float points; // Nombre de points pour cette partie du devoir

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "devoir_id")
    private Devoir devoir; // Devoir associé à cette partie du devoir

    @OneToMany(mappedBy = "partieDevoir", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Note> notes = new HashSet<>(); // Notes associées à cette partie du devoir
}
