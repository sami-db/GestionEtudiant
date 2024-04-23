package org.projet.gestion.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EtudiantDTO {
    private Long id;
    private String nomEtudiant;
    private String prenomEtudiant;
    private String photoEtudiant;
    private Long classeId;
}