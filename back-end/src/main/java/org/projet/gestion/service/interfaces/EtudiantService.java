package org.projet.gestion.service.interfaces;

import java.util.List;

import org.projet.gestion.model.Etudiant;

public interface EtudiantService {
	Iterable<Etudiant> afficherEtudiants();
	Etudiant afficherEtudiant(Long id);
	List<Etudiant> afficherEtudiantsSansClasse();
	Etudiant creerEtudiant(Etudiant etudiant);
	Etudiant modifierEtudiant(Long id, Etudiant etudiant);
    void supprimerEtudiant(Long id);
}
