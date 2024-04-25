package org.projet.gestion.service.implement;

import org.projet.gestion.model.Etudiant;
import org.projet.gestion.model.Classe;
import org.projet.gestion.repository.ClasseRepository;
import org.projet.gestion.repository.EtudiantRepository;
import org.projet.gestion.service.interfaces.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtudiantServiceImp implements EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private ClasseRepository classeRepository;

    // Afficher tous les étudiants
    @Override
    public Iterable<Etudiant> afficherEtudiants() {
        return etudiantRepository.findAll();
    }

    // Afficher un étudiant par ID
    @Override
    public Etudiant afficherEtudiant(Long id) {
        return etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etudiant non trouvé avec l'ID : " + id));
    }

    // Afficher les étudiants sans classe
    @Override
    public List<Etudiant> afficherEtudiantsSansClasse() {
        return etudiantRepository.findByClasseIsNull();
    }

    // Créer un nouvel étudiant
    @Override
    public Etudiant creerEtudiant(Etudiant etudiant) {
        if (etudiant.getNom() == null || etudiant.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'étudiant ne peut pas être vide.");
        }

        if (etudiant.getClasse() != null && etudiant.getClasse().getId() != null) {
            Classe classe = classeRepository.findById(etudiant.getClasse().getId())
                    .orElseThrow(() -> new RuntimeException("Classe non trouvée avec l'ID: " + etudiant.getClasse().getId()));
            etudiant.setClasse(classe);
        } else {
            etudiant.setClasse(null);
        }
        return etudiantRepository.save(etudiant);
    }

    // Modifier les détails d'un étudiant
    @Override
    public Etudiant modifierEtudiant(Long id, Etudiant etudiantDetails) {
        return etudiantRepository.findById(id).map(etudiant -> {
            etudiant.setNom(etudiantDetails.getNom());
            etudiant.setPrenom(etudiantDetails.getPrenom());
            etudiant.setPhoto(etudiantDetails.getPhoto());
            etudiant.setClasse(etudiantDetails.getClasse());
            return etudiantRepository.save(etudiant);
        }).orElseThrow(() -> new RuntimeException("Etudiant non trouvé avec id " + id));
    }

    // Supprimer un étudiant par ID
    @Override
    public void supprimerEtudiant(Long id) {
        etudiantRepository.deleteById(id);
    }
}
