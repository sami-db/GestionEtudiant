package org.projet.gestion.service.interfaces;

import org.projet.gestion.dto.ClasseDTO;
import org.projet.gestion.model.Classe;

public interface ClasseService {
    Iterable<Classe> afficherClasses();
    Classe modifierClasse(Long id, Classe classe);
    void supprimerClasse(Long id);
    Classe creerClasse(ClasseDTO classeDTO);

    //  afficherBulletin()
}
