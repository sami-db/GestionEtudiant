package org.projet.gestion.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.*;

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

    // Afficher tous les étudiants avec leurs informations
    @GetMapping("/afficherEtudiants")
    public ResponseEntity<List<EtudiantDTO>> afficherEtudiants() {
        Iterable<Etudiant> etudiants = etudiantService.afficherEtudiants();
        List<EtudiantDTO> etudiantDTOs = new ArrayList<>();

        for (Etudiant etudiant : etudiants) {
            EtudiantDTO etudiantDTO = new EtudiantDTO();
            etudiantDTO.setId(etudiant.getId());
            etudiantDTO.setNom(etudiant.getNom());
            etudiantDTO.setPrenom(etudiant.getPrenom());
            etudiantDTO.setPhoto(etudiant.getPhoto());
            etudiantDTO.setClasseId(etudiant.getClasse() != null ? etudiant.getClasse().getId() : null);
            etudiantDTO.setNomClasse(etudiant.getClasse() != null ? etudiant.getClasse().getDenomination() : null);

            etudiantDTOs.add(etudiantDTO);
        }

        return ResponseEntity.ok(etudiantDTOs);
    }

    // Afficher un étudiant par ID
    @GetMapping("/afficherEtudiant/{id}")
    public Etudiant afficherEtudiant(@PathVariable Long id) {
        return etudiantService.afficherEtudiant(id);
    }

    // Afficher les étudiants n'ayant pas de classe assignée
    @GetMapping("/afficherEtudiantsSansClasse")
    public ResponseEntity<List<Etudiant>> afficherEtudiantsSansClasse() {
        List<Etudiant> etudiants = etudiantService.afficherEtudiantsSansClasse();
        return ResponseEntity.ok(etudiants);
    }

    // Créer un nouvel étudiant
    @PostMapping("/creerEtudiant")
    public ResponseEntity<?> creerEtudiant(@RequestBody EtudiantDTO etudiantDTO) {
        if (etudiantDTO.getNom() == null || etudiantDTO.getPrenom().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Le nom de l'étudiant est obligatoire.");
        }

        Etudiant etudiant = new Etudiant();
        etudiant.setNom(etudiantDTO.getNom());
        etudiant.setPrenom(etudiantDTO.getPrenom());
        etudiant.setPhoto(etudiantDTO.getPhoto());

        if (etudiantDTO.getClasseId() != null) {
            Classe classe = new Classe();
            classe.setId(etudiantDTO.getClasseId());
            etudiant.setClasse(classe);
        }

        Etudiant etudiantCree = etudiantService.creerEtudiant(etudiant);
        return ResponseEntity.ok(etudiantCree);
    }

    // Modifier un étudiant existant par ID
    @PutMapping("/modifierEtudiant/{id}")
    public Etudiant modifierEtudiant(@PathVariable Long id, @RequestBody Etudiant etudiant) {
        return etudiantService.modifierEtudiant(id, etudiant);
    }

    // Supprimer un étudiant par ID
    @DeleteMapping("/supprimerEtudiant")
    public void supprimerEtudiant(@RequestParam Long id) {
        etudiantService.supprimerEtudiant(id);
    }

    // Afficher les notes d'un étudiant par ID d'étudiant
    @GetMapping("/afficherNoteParEtudiant/{etudiantId}")
    public ResponseEntity<List<NoteDTO>> getNotesParEtudiant(@PathVariable Long etudiantId) {
        List<NoteDTO> noteDTOs = noteService.afficherNotesParEtudiant(etudiantId);
        return ResponseEntity.ok(noteDTOs);
    }
}
