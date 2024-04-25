package org.projet.gestion.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "classe")
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "denomination")
    private String denomination;

    // Les étudiants associés à cette classe
    @OneToMany(mappedBy = "classe", fetch = FetchType.EAGER)
    private Set<Etudiant> etudiants;

    // Les devoirs associés à cette classe
    @JsonIgnoreProperties("classe") // Ignorer la propriété "classe" lors de la sérialisation JSON pour éviter la récursivité
    @OneToMany(mappedBy = "classe", cascade = CascadeType.ALL)
    private Set<Devoir> devoirs = new HashSet<>();
}
