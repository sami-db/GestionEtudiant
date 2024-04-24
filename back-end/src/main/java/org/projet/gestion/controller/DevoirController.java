package org.projet.gestion.controller;

import org.projet.gestion.dto.DevoirDTO;
import org.projet.gestion.model.Devoir;
import org.projet.gestion.service.interfaces.DevoirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Iterable<DevoirDTO> afficherTousLesDevoirs() {
        return devoirService.listerDevoirs();
    }

    @PutMapping("modifierDevoir/{id}")
    public Devoir modifierDevoir(@PathVariable Long id, @RequestBody Devoir devoir) {
        return devoirService.modifierDevoir(id, devoir);
    }

    @DeleteMapping("supprimerDevoir")
    public void supprimerDevoir(@RequestParam Long id) {
        devoirService.supprimerDevoir(id);
    }
}
