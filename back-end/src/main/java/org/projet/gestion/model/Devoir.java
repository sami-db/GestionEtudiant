package org.projet.gestion.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "devoir")
public class Devoir {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "coefficient")
    private int coefficient;

    @ManyToOne
    @JoinColumn(name = "matiere_id")
    private Matiere matiere;
    
    @ManyToOne
    @JoinColumn(name = "classe_id")
    private Classe classe;

    @Transient
    private List<Float> pointsDesParties;


}
