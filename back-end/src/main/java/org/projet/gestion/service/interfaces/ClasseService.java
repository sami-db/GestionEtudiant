package org.projet.gestion.service.interfaces;

import org.projet.gestion.dto.ClasseDTO;
import org.projet.gestion.model.Classe;
import org.projet.gestion.model.Matiere;

public interface ClasseService {
    Iterable<ClasseDTO> afficherClasses();
	Classe afficherClasse(Long id);
    Classe modifierClasse(Long id, Classe classe);
    void supprimerClasse(Long id);
    Classe creerClasse(ClasseDTO classeDTO);

    //  afficherBulletin()
}
