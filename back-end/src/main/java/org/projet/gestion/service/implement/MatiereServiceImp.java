package org.projet.gestion.service.implement;

import org.projet.gestion.model.Etudiant;
import org.projet.gestion.model.Matiere;
import org.projet.gestion.repository.DevoirRepository;
import org.projet.gestion.repository.MatiereRepository;
import org.projet.gestion.service.interfaces.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MatiereServiceImp implements MatiereService {

    @Autowired
    private MatiereRepository matiereRepository;
    @Autowired
    private DevoirRepository devoirRepository;

    // Afficher toutes les matières
    @Override
    public Iterable<Matiere> afficherMatieres() {
        return matiereRepository.findAll();
    }

    // Afficher une matière par ID
    @Override
    public Matiere afficherMatiere(Long id) {
        return matiereRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matiere non trouvée avec l'ID : " + id));
    }

    // Créer une nouvelle matière
    @Override
    public Matiere creerMatiere(Matiere matiere) {
        return matiereRepository.save(matiere);
    }

    // Modifier les détails d'une matière
    @Override
    public Matiere modifierMatiere(Long id, Matiere matiereDetails) {
        return matiereRepository.findById(id).map(matiere -> {
            matiere.setDenomination(matiereDetails.getDenomination());
            return matiereRepository.save(matiere);
        }).orElseThrow(() -> new RuntimeException("Matiere non trouvée avec id : " + id));
    }

    // Supprimer une matière par ID
    @Override
    public void supprimerMatiere(Long id) {
        long count = devoirRepository.countByMatiereId(id);
        if (count == 0) {
            matiereRepository.deleteById(id);
        } else {
            throw new RuntimeException("Matiere est utilisée dans un ou plusieurs devoirs et ne peut pas être supprimée");
        }
    }

}
