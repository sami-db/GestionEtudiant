package org.projet.gestion.controller;

import org.projet.gestion.dto.DevoirDTO;
import org.projet.gestion.model.Devoir;
import org.projet.gestion.service.interfaces.DevoirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DevoirController {

    @Autowired
    private DevoirService devoirService;

    // Créer un nouveau devoir
    @PostMapping("/creerDevoir")
    public ResponseEntity<Devoir> creerDevoir(@RequestBody Devoir devoir) {
        Devoir nouveauDevoir = devoirService.creerDevoir(devoir);
        return new ResponseEntity<>(nouveauDevoir, HttpStatus.CREATED);
    }

    // Afficher un devoir par ID
    @GetMapping("afficherDevoir/{id}")
    public Devoir afficherDevoir(@PathVariable Long id) {
        return devoirService.trouverDevoirParId(id);
    }

    // Afficher tous les devoirs
    @GetMapping("/afficherTousLesDevoirs")
    public Iterable<DevoirDTO> afficherTousLesDevoirs() {
        return devoirService.listerDevoirs();
    }

    // Modifier un devoir existant par ID
    @PutMapping("modifierDevoir/{id}")
    public Devoir modifierDevoir(@PathVariable Long id, @RequestBody Devoir devoir) {
        return devoirService.modifierDevoir(id, devoir);
    }

    // Supprimer un devoir par ID
    @DeleteMapping("supprimerDevoir")
    public void supprimerDevoir(@RequestParam Long id) {
        devoirService.supprimerDevoir(id);
    }
}
