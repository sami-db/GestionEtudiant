package org.projet.gestion.controller;

import org.projet.gestion.dto.ClasseDTO;
import org.projet.gestion.model.Classe;
import org.projet.gestion.service.interfaces.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ClasseController {
    @Autowired
    private ClasseService classeService;

    @GetMapping("/afficherClasse")
    public Iterable<Classe> afficherClasses() {
        return classeService.afficherClasses();
    }

    //@GetMapping("/afficherBulletin")
    //public Iterable<Classe> afficherBulletin() {
    //    return classeService.afficherBulletin();
    //}

    @PostMapping("/creerClasse")
    public ResponseEntity<Classe> createClasse(@RequestBody ClasseDTO classeDTO) {
        Classe classeCreee = classeService.creerClasse(classeDTO);
        return ResponseEntity.ok(classeCreee);
    }

    @PutMapping("/modifierClasse/{id}")
    public Classe modifierClasse(@PathVariable Long id, @RequestBody Classe classe) {
        return classeService.modifierClasse(id, classe);
    }


    @DeleteMapping("/supprimerClasse/{id}")
    public void supprimerClasse(@PathVariable Long id) {
        classeService.supprimerClasse(id);
    }
}
