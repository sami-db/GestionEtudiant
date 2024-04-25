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
    private Long id;

    @Column(name = "points")
    private float points;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "devoir_id")
    private Devoir devoir;

    @OneToMany(mappedBy = "partieDevoir", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Note> notes = new HashSet<>();
}
