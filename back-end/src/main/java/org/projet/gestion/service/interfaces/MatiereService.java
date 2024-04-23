package org.projet.gestion.service.interfaces;

import org.projet.gestion.model.Matiere;

public interface MatiereService {
	
	Iterable<Matiere> afficherMatieres();
	Matiere afficherMatiere(Long id);
    Matiere creerMatiere(Matiere matiere);
    Matiere modifierMatiere(Long id, Matiere matiereDetails);
    void supprimerMatiere(Long id);


}