package org.projet.gestion.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteDTO {
    private Long id;
    private Long etudiantId;
    private Long partieDevoirId;
    private float valeur;
    private String nomEtudiant;
    private String prenomEtudiant;
    private String denomination;
}