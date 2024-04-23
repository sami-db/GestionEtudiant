package org.projet.gestion.model;

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

    @ManyToOne
    @JoinColumn(name = "devoir_id")
    private Devoir devoir;
}
