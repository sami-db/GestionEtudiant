package org.projet.gestion.service.interfaces;

import org.projet.gestion.model.Devoir;

public interface DevoirService {
    Devoir creerDevoir(Devoir devoir);
    Devoir trouverDevoirParId(Long id);
    Iterable<Devoir> listerDevoirs();
    Devoir modifierDevoir(Long id, Devoir devoir);
    void supprimerDevoir(Long id);
}
