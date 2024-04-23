package org.projet.gestion.repository;

import org.projet.gestion.model.Etudiant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EtudiantRepository extends CrudRepository<Etudiant, Long> {
}
