package org.projet.gestion.controller;

import java.util.List;

import org.projet.gestion.dto.ClasseDTO;
import org.projet.gestion.dto.NoteDTO;
import org.projet.gestion.model.Classe;
import org.projet.gestion.service.interfaces.ClasseService;
import org.projet.gestion.service.interfaces.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ClasseController {
    private final ClasseService classeService;
    private final NoteService noteService;

    @Autowired
    public ClasseController(ClasseService classeService, NoteService noteService) {
        this.classeService = classeService;
        this.noteService = noteService;
    }

    // Récupérer toutes les classes
    @GetMapping("/afficherClasses")
    public Iterable<ClasseDTO> afficherClasses() {
        return classeService.afficherClasses();
    }

    // Récupérer une classe par ID
    @GetMapping("/afficherClasse/{id}")
    public Classe afficherClasse(@PathVariable Long id) {
        return classeService.afficherClasse(id);
    }

    // Récupérer les notes d'une classe par ID de classe
    @GetMapping("/afficherNoteParClasse/{id}")
    public ResponseEntity<List<NoteDTO>> getNotesParClasse(@PathVariable Long id) {
        List<NoteDTO> notes = noteService.afficherNotesParClasse(id);
        return ResponseEntity.ok(notes);
    }

    // Créer une nouvelle classe
    @PostMapping("/creerClasse")
    public ResponseEntity<Classe> createClasse(@RequestBody ClasseDTO classeDTO) {
        Classe classeCreee = classeService.creerClasse(classeDTO);
        return ResponseEntity.ok(classeCreee);
    }

    // Modifier une classe existante par ID
    @PutMapping("/modifierClasse/{id}")
    public Classe modifierClasse(@PathVariable Long id, @RequestBody Classe classe) {
        return classeService.modifierClasse(id, classe);
    }

    // Supprimer une classe par ID
    @DeleteMapping("/supprimerClasse")
    public void supprimerClasse(@RequestParam Long id) {
        classeService.supprimerClasse(id);
    }
}
