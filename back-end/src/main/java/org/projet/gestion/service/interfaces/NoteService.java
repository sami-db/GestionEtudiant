package org.projet.gestion.service.interfaces;

import org.projet.gestion.dto.NoteDTO;
import java.util.List;

public interface NoteService {
    List<NoteDTO> afficherNotesParEtudiant(Long etudiantId);
    List<NoteDTO> afficherNotesParClasse(Long classeId);
    NoteDTO creerNote(Long noteId, Long etudiantId, Long partieDevoirId, float valeur);
    void supprimerNote(Long noteId);
}