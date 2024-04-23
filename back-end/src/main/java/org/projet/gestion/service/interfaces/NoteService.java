package org.projet.gestion.service.interfaces;

import org.projet.gestion.dto.NoteDTO;
import java.util.List;

public interface NoteService {
    List<NoteDTO> getNotesParEtudiant(Long etudiantId);
    List<NoteDTO> getNotesParClasse(Long classeId);

}