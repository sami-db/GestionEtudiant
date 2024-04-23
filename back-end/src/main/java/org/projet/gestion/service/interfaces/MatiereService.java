package org.projet.gestion.service.interfaces;

import org.projet.gestion.model.Matiere;

public interface MatiereService {
    Matiere creerMatiere(Matiere matiere);

    Matiere modifierMatiere(Long id, Matiere matiereDetails);

    void supprimerMatiere(Long id);


}