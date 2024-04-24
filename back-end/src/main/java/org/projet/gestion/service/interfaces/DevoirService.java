package org.projet.gestion.service.interfaces;

import java.util.List;

import org.projet.gestion.dto.DevoirDTO;
import org.projet.gestion.model.Devoir;

public interface DevoirService {
    Devoir creerDevoir(Devoir devoir);
    Devoir trouverDevoirParId(Long id);
    Iterable<DevoirDTO> listerDevoirs();
    Devoir modifierDevoir(Long id, Devoir devoir);
    void supprimerDevoir(Long id);
}
