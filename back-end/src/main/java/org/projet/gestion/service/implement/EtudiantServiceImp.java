package org.projet.gestion.service.implement;

import org.projet.gestion.model.Etudiant;
import org.projet.gestion.repository.EtudiantRepository;
import org.projet.gestion.service.interfaces.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EtudiantServiceImp implements EtudiantService {
	
	@Autowired
    private EtudiantRepository etudiantRepository;

    @Override
    public Iterable<Etudiant> afficherEtudiants() {
        return etudiantRepository.findAll();
    }
    
    @Override
    public Etudiant afficherEtudiant(Long id) {
        return etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etudiant non trouvé avec l'ID : " + id));
    }

    
    @Override
    public Etudiant creerEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    @Override
    public Etudiant modifierEtudiant(Long id, Etudiant etudiantDetails) {
        return etudiantRepository.findById(id).map(etudiant -> {
            etudiant.setNom(etudiantDetails.getNom());
            etudiant.setPrenom(etudiantDetails.getPrenom());
            etudiant.setPhoto(etudiantDetails.getPhoto());
            etudiant.setClasse(etudiantDetails.getClasse());
            return etudiantRepository.save(etudiant);
        }).orElseThrow(() -> new RuntimeException("Etudiant non trouvée avec id " + id));
    }

    @Override
    public void supprimerEtudiant(Long id) {
        etudiantRepository.deleteById(id);
    }
}
