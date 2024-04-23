package org.projet.gestion.service.implement;

import org.projet.gestion.dto.NoteDTO;
import org.projet.gestion.model.Etudiant;
import org.projet.gestion.model.Note;
import org.projet.gestion.repository.NoteRepository;
import org.projet.gestion.service.interfaces.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImp implements NoteService {
    private final NoteRepository noteRepository;

    @Autowired
    public NoteServiceImp(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public List<NoteDTO> getNotesParEtudiant(Long etudiantId) {
        return noteRepository.findByEtudiantId(etudiantId).stream()
                              .map(this::convertToNoteDTO)
                              .collect(Collectors.toList());
    }

    private NoteDTO convertToNoteDTO(Note note) {
        NoteDTO dto = new NoteDTO();
        dto.setId(note.getId());
        dto.setEtudiantId(note.getEtudiant().getId());
        dto.setPartieDevoirId(note.getPartieDevoir().getId());
        dto.setValeur(note.getValeur());
        dto.setDenomination(note.getPartieDevoir().getDevoir().getMatiere().getDenomination());

        Etudiant etudiant = note.getEtudiant();
        if (etudiant != null) {
            dto.setNomEtudiant(etudiant.getNom());
            dto.setPrenomEtudiant(etudiant.getPrenom());
        }

        return dto;
    }

}