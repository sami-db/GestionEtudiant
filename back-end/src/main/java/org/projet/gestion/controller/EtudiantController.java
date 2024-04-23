package org.projet.gestion.controller;

import java.util.List;

import org.projet.gestion.dto.EtudiantDTO;
import org.projet.gestion.dto.NoteDTO;
import org.projet.gestion.model.Classe;
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
<<<<<<< Updated upstream
    
    @GetMapping("/afficherEtudiant/{id}")
    public Etudiant afficherEtudiant(@PathVariable Long id) {
        return etudiantService.afficherEtudiant(id);
    }
    
=======

>>>>>>> Stashed changes
    @PostMapping("/creerEtudiant")
    public ResponseEntity<?> creerEtudiant(@RequestBody EtudiantDTO etudiantDTO) {
        if (etudiantDTO.getNomEtudiant() == null || etudiantDTO.getNomEtudiant().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Le nom de l'étudiant est obligatoire.");
        }

        Etudiant etudiant = new Etudiant();
        etudiant.setNom(etudiantDTO.getNomEtudiant());
        etudiant.setPrenom(etudiantDTO.getPrenomEtudiant());
        etudiant.setPhoto(etudiantDTO.getPhotoEtudiant());

        if (etudiantDTO.getClasseId() != null) {
            Classe classe = new Classe();
            classe.setId(etudiantDTO.getClasseId());
            etudiant.setClasse(classe);
        }

        Etudiant etudiantCree = etudiantService.creerEtudiant(etudiant);
        return ResponseEntity.ok(etudiantCree);
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
