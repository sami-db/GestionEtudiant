package org.projet.gestion.repository;

import org.projet.gestion.model.Matiere;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatiereRepository extends CrudRepository<Matiere, Long> {

}
