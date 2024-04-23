package org.projet.gestion.controller;

import org.projet.gestion.model.Devoir;
import org.projet.gestion.model.Etudiant;
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

    @PostMapping("/creerDevoir")
    public ResponseEntity<Devoir> creerDevoir(@RequestBody Devoir devoir) {
        Devoir nouveauDevoir = devoirService.creerDevoir(devoir);
        return new ResponseEntity<>(nouveauDevoir, HttpStatus.CREATED);
    }
    @GetMapping("afficherDevoir/{id}")
    public Devoir afficherDevoir(@PathVariable Long id) {
        return devoirService.trouverDevoirParId(id);
    }

    @GetMapping("/afficherTousLesDevoirs")
    public Iterable<Devoir> afficherTousLesDevoirs() {
        return devoirService.listerDevoirs();
    }

    @PutMapping("modifierDevoir/{id}")
    public Devoir modifierDevoir(@PathVariable Long id, @RequestBody Devoir devoir) {
        return devoirService.modifierDevoir(id, devoir);
    }

    @DeleteMapping("supprimerDevoir/{id}")
    public void supprimerDevoir(@PathVariable Long id) {
        devoirService.supprimerDevoir(id);
    }
}
