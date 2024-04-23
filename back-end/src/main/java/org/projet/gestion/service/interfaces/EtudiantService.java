package org.projet.gestion.service.interfaces;

import org.projet.gestion.model.Etudiant;

public interface EtudiantService {
	Iterable<Etudiant> afficherEtudiants();
	Etudiant creerEtudiant(Etudiant etudiant);
	Etudiant modifierEtudiant(Long id, Etudiant etudiant);
    void supprimerEtudiant(Long id);
}
