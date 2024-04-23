
package org.projet.gestion.model;

import java.util.Set;

import jakarta.persistence.*;
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
    
  //obligé de mettre eager pcq sinon les etudiants
  // ont pas le temps de se charger quand tu créé 
  // une classe et que tu veux renvoyer la classe en retour
    @OneToMany(mappedBy = "classe", fetch = FetchType.EAGER)
    private Set<Etudiant> etudiants;
}
