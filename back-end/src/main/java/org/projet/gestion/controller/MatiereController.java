package org.projet.gestion.controller;

import org.projet.gestion.model.Classe;
import org.projet.gestion.model.Etudiant;
import org.projet.gestion.model.Matiere;
import org.projet.gestion.service.interfaces.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MatiereController {

    @Autowired
    private MatiereService matiereService;
    
    
    @GetMapping("/afficherMatieres")
    public Iterable<Matiere> afficherMatieres() {
        return matiereService.afficherMatieres();
    }
    
    @GetMapping("/afficherMatiere/{id}")
    public Matiere afficherEtudiant(@PathVariable Long id) {
        return matiereService.afficherMatiere(id);
    }
    
    @PostMapping("/ajouterMatiere")
    public Matiere ajouterMatiere(@RequestBody Matiere matiere) {
        return matiereService.creerMatiere(matiere);
    }

    @PutMapping("/modifierMatiere/{id}")
    public Matiere modifierMatiere(@PathVariable Long id, @RequestBody Matiere matiere) {
        return matiereService.modifierMatiere(id, matiere);
    }

    @DeleteMapping("/supprimerMatiere/{id}")
    public ResponseEntity<?> supprimerMatiere(@PathVariable Long id) {
        try {
            matiereService.supprimerMatiere(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
