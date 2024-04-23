package org.projet.gestion.repository;

import org.projet.gestion.model.PartieDevoir;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartieDevoirRepository extends CrudRepository<PartieDevoir, Long> {

}
