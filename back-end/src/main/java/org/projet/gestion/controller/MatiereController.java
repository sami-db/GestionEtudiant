package org.projet.gestion.controller;

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

    // Afficher toutes les matières
    @GetMapping("/afficherMatieres")
    public Iterable<Matiere> afficherMatieres() {
        return matiereService.afficherMatieres();
    }

    // Afficher une matière par ID
    @GetMapping("/afficherMatiere/{id}")
    public Matiere afficherEtudiant(@PathVariable Long id) {
        return matiereService.afficherMatiere(id);
    }

    // Ajouter une nouvelle matière
    @PostMapping("/ajouterMatiere")
    public Matiere ajouterMatiere(@RequestBody Matiere matiere) {
        return matiereService.creerMatiere(matiere);
    }

    // Modifier une matière existante par ID
    @PutMapping("/modifierMatiere/{id}")
    public Matiere modifierMatiere(@PathVariable Long id, @RequestBody Matiere matiere) {
        return matiereService.modifierMatiere(id, matiere);
    }

    // Supprimer une matière par ID
    @DeleteMapping("/supprimerMatiere")
    public void supprimerMatiere(@RequestParam Long id) {
        matiereService.supprimerMatiere(id);
    }
}
