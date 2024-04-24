package org.projet.gestion.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EtudiantDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String photo;
    private Long classeId;
    private String nomClasse;
}