package org.projet.gestion.controller;

import org.projet.gestion.dto.NoteDTO;
import org.projet.gestion.service.interfaces.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    private NoteService noteService;


    // Créer ou mettre à jour une note
    @PostMapping("/ajouterNote")
    public ResponseEntity<NoteDTO> createOrUpdateNote(@RequestBody NoteDTO noteDTO) {
        NoteDTO savedNote = noteService.createOrUpdateNote(noteDTO.getId(), noteDTO.getEtudiantId(), noteDTO.getPartieDevoirId(), noteDTO.getValeur());
        return ResponseEntity.ok(savedNote);
    }

    // Supprimer une note
    @DeleteMapping("/supprimerNote")
    public ResponseEntity<Void> deleteNote(@RequestParam Long noteId) {
        noteService.deleteNote(noteId);
        return ResponseEntity.ok().build();
    }
}


