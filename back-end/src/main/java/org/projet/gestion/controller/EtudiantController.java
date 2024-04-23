package org.projet.gestion.controller;

import java.util.List;

import org.projet.gestion.dto.NoteDTO;
import org.projet.gestion.model.Etudiant;
import org.projet.gestion.model.Note;
import org.projet.gestion.service.interfaces.EtudiantService;
import org.projet.gestion.service.interfaces.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EtudiantController {

    private final EtudiantService etudiantService;
    private final NoteService noteService;

    @Autowired
    public EtudiantController(EtudiantService etudiantService, NoteService noteService) {
        this.etudiantService = etudiantService;
        this.noteService = noteService;
    }
    
    @GetMapping("/afficherEtudiants")
    public Iterable<Etudiant> afficherEtudiants() {
        return etudiantService.afficherEtudiants();
    }
    
    @GetMapping("/afficherEtudiant/{id}")
    public Etudiant afficherEtudiant(@PathVariable Long id) {
        return etudiantService.afficherEtudiant(id);
    }
    
    @PostMapping("/creerEtudiant")
    public Etudiant creerEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.creerEtudiant(etudiant);
    }

    @PutMapping("/modifierEtudiant/{id}")
    public Etudiant modifierEtudiant(@PathVariable Long id, @RequestBody Etudiant etudiant) {
        return etudiantService.modifierEtudiant(id, etudiant);
    }

    @DeleteMapping("/supprimerEtudiant/{id}")
    public void supprimerEtudiant(@PathVariable Long id) {
    	etudiantService.supprimerEtudiant(id);
    }
    
    @GetMapping("/afficherNoteParEtudiant/{etudiantId}")
    public ResponseEntity<List<NoteDTO>> getNotesParEtudiant(@PathVariable Long etudiantId) {
        List<NoteDTO> noteDTOs = noteService.getNotesParEtudiant(etudiantId);
        return ResponseEntity.ok(noteDTOs);
    }
    
    
}
