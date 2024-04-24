package org.projet.gestion.service.implement;

import java.util.List;
import java.util.Set;

import org.projet.gestion.dto.ClasseDTO;
import org.projet.gestion.model.Classe;
import org.projet.gestion.model.Devoir;
import org.projet.gestion.model.Etudiant;
import org.projet.gestion.repository.ClasseRepository;
import org.projet.gestion.repository.EtudiantRepository;
import org.projet.gestion.service.interfaces.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ClasseServiceImp implements ClasseService {

	private ClasseRepository classeRepository;
    private EtudiantRepository etudiantRepository;
    
	@Autowired
    public ClasseServiceImp(ClasseRepository classeRepository, EtudiantRepository etudiantRepository) {
        this.classeRepository = classeRepository;
        this.etudiantRepository = etudiantRepository;
    }

    @Override
    public Iterable<Classe> afficherClasses() {
        return classeRepository.findAll();
    }
    
    @Override
    public Classe afficherClasse(Long id) {
        return classeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classe non trouvée avec l'ID : " + id));
    }

    @Transactional
    @Override
    public Classe creerClasse(ClasseDTO classeDTO) {
        Classe classe = new Classe();
        classe.setDenomination(classeDTO.getDenomination());
        Classe classeCree = classeRepository.save(classe);

        if (classeDTO.getEtudiantIds() != null && !classeDTO.getEtudiantIds().isEmpty()) {
            for (Long etudiantId : classeDTO.getEtudiantIds()) {
                Etudiant etudiant = etudiantRepository.findById(etudiantId)
                                        .orElseThrow(() -> new RuntimeException("Etudiant " + etudiantId + " non trouvé"));
                etudiant.setClasse(classeCree);
                etudiantRepository.save(etudiant);
            }
        }

        return classeRepository.findById(classeCree.getId())
                .orElseThrow(() -> new RuntimeException("Classe " + classeCree.getId() + " non trouvée"));
    }

    @Override
    public Classe modifierClasse(Long id, Classe classeDetails) {
        return classeRepository.findById(id).map(classe -> {
            classe.setDenomination(classeDetails.getDenomination());
            return classeRepository.save(classe);
        }).orElseThrow(() -> new RuntimeException("Classe non trouvée avec id " + id));
    }

    @Override
    public void supprimerClasse(Long classeId) {
        Classe classe = classeRepository.findById(classeId)
                .orElseThrow(() -> new EntityNotFoundException("Classe non trouvée avec l'ID : " + classeId));

        Set<Etudiant> etudiants = classe.getEtudiants();
        for (Etudiant etudiant : etudiants) {
            etudiant.setClasse(null);
            etudiantRepository.save(etudiant);
        }
        classeRepository.delete(classe);
    }
}




















