package org.projet.gestion.service.implement;

import org.projet.gestion.dto.NoteDTO;
import org.projet.gestion.model.Devoir;
import org.projet.gestion.model.Etudiant;
import org.projet.gestion.model.Matiere;
import org.projet.gestion.model.Note;
import org.projet.gestion.model.PartieDevoir;
import org.projet.gestion.repository.EtudiantRepository;
import org.projet.gestion.repository.NoteRepository;
import org.projet.gestion.repository.PartieDevoirRepository;
import org.projet.gestion.service.interfaces.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImp implements NoteService {
    
    private final NoteRepository noteRepository;
    private final EtudiantRepository etudiantRepository;
    private final PartieDevoirRepository partieDevoirRepository;

    @Autowired
    public NoteServiceImp(NoteRepository noteRepository, EtudiantRepository etudiantRepository, PartieDevoirRepository partieDevoirRepository) {
        this.noteRepository = noteRepository;
        this.etudiantRepository = etudiantRepository;
        this.partieDevoirRepository = partieDevoirRepository;
    }
    
    @Override
    public List<NoteDTO> getNotesParEtudiant(Long etudiantId) {
        return noteRepository.findByEtudiantId(etudiantId).stream()
                              .map(this::convertToNoteDTO)
                              .collect(Collectors.toList());
    }
    
    @Override
    public List<NoteDTO> getNotesParClasse(Long classeId) {
        List<Etudiant> etudiants = etudiantRepository.findByClasse_Id(classeId);
        List<NoteDTO> notesDTO = new ArrayList<>();
        for (Etudiant etudiant : etudiants) {
            List<Note> notes = noteRepository.findByEtudiantId(etudiant.getId());
            for (Note note : notes) {
                NoteDTO dto = convertToNoteDTO(note);
                notesDTO.add(dto);
            }
        }
        
        return notesDTO;
    }

    private NoteDTO convertToNoteDTO(Note note) {
        NoteDTO dto = new NoteDTO();
        dto.setId(note.getId());
        dto.setEtudiantId(note.getEtudiant() != null ? note.getEtudiant().getId() : null);
        dto.setValeur(note.getValeur());

        PartieDevoir partieDevoir = note.getPartieDevoir();
        if (partieDevoir != null) {
            dto.setPartieDevoirId(partieDevoir.getId());

            Devoir devoir = partieDevoir.getDevoir();
            if (devoir != null) {
                Matiere matiere = devoir.getMatiere();
                if (matiere != null) {
                    dto.setDenomination(matiere.getDenomination());
                }
            }
        }

        Etudiant etudiant = note.getEtudiant();
        if (etudiant != null) {
            dto.setNomEtudiant(etudiant.getNom());
            dto.setPrenomEtudiant(etudiant.getPrenom());
        }

        return dto;
    }


    @Override
    public NoteDTO createOrUpdateNote(Long noteId, Long etudiantId, Long partieDevoirId, float valeur) {
        Note note;
        if (noteId == null) { // Création d'une nouvelle note
            note = new Note();
        } else { // Mise à jour d'une note existante
            note = noteRepository.findById(noteId).orElseThrow(() -> new RuntimeException("Note not found"));
        }

        // Récupération et liaison de l'étudiant et de la partie de devoir
        Etudiant etudiant = etudiantRepository.findById(etudiantId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        PartieDevoir partieDevoir = partieDevoirRepository.findById(partieDevoirId)
                .orElseThrow(() -> new RuntimeException("Partie devoir not found"));

        // Vérification que la note n'est pas supérieure aux points possibles pour la partie du devoir
        if (valeur > partieDevoir.getPoints()) {
            throw new IllegalArgumentException("La valeur de la note ne peut pas être supérieure aux points de la partie du devoir");
        }

        note.setEtudiant(etudiant);
        note.setPartieDevoir(partieDevoir);
        note.setValeur(valeur);
        note = noteRepository.save(note);

        NoteDTO dto = convertToNoteDTO(note);

        return dto;
    }


    @Override
    public void deleteNote(Long noteId) {
        noteRepository.deleteById(noteId);
    }

}