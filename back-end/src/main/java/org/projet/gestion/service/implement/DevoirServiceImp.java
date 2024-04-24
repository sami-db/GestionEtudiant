package org.projet.gestion.service.implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.projet.gestion.dto.DevoirDTO;
import org.projet.gestion.model.Devoir;
import org.projet.gestion.model.PartieDevoir;
import org.projet.gestion.repository.DevoirRepository;
import org.projet.gestion.repository.PartieDevoirRepository;
import org.projet.gestion.service.interfaces.DevoirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevoirServiceImp implements DevoirService {

    @Autowired
    private DevoirRepository devoirRepository;

    @Autowired
    private PartieDevoirRepository partieDevoirRepository;
    @Override
    public Devoir creerDevoir(Devoir devoir) {

        Devoir savedDevoir = devoirRepository.save(devoir);

        if (devoir.getPointsDesParties() != null) {
            for (Float points : devoir.getPointsDesParties()) {
                PartieDevoir partieDevoir = new PartieDevoir();
                partieDevoir.setPoints(points);
                partieDevoir.setDevoir(savedDevoir);
                partieDevoirRepository.save(partieDevoir);
            }
        }

        return savedDevoir;
    }

    @Override
    public Devoir trouverDevoirParId(Long id) {
        Optional<Devoir> devoir = devoirRepository.findById(id);
        return devoir.orElseThrow(() -> new RuntimeException("Devoir non trouvé avec id : " + id));
    }

    @Override
    public Iterable<DevoirDTO> listerDevoirs() {
        return devoirRepository.findAll().stream()
                .map(this::convertToDevoirDTO)
                .collect(Collectors.toList());
    }

    private DevoirDTO convertToDevoirDTO(Devoir devoir) {
        DevoirDTO dto = new DevoirDTO();
        dto.setId(devoir.getId());
        dto.setType(devoir.getType());
        dto.setDate(devoir.getDate());
        dto.setCoefficient(devoir.getCoefficient());
        if (devoir.getMatiere() != null) {
            dto.setNomMatiere(devoir.getMatiere().getDenomination());
        }
        if (devoir.getClasse() != null) {
            dto.setNomClasse(devoir.getClasse().getDenomination());
        }
        return dto;
    }
    
    
    @Override
    public Devoir modifierDevoir(Long id, Devoir devoirDetails) {
        return devoirRepository.findById(id).map(devoir -> {
            devoir.setType(devoirDetails.getType());
            devoir.setDate(devoirDetails.getDate());
            devoir.setCoefficient(devoirDetails.getCoefficient());
            return devoirRepository.save(devoir);
        }).orElseThrow(() -> new RuntimeException("Devoir non trouvé avec id : " + id));
    }

    @Override
    public void supprimerDevoir(Long id) {
        devoirRepository.deleteById(id);
    }
}
