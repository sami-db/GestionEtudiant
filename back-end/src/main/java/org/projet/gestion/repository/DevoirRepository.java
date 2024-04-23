package org.projet.gestion.repository;

import org.projet.gestion.model.Devoir;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface DevoirRepository extends CrudRepository<Devoir, Long> {
    long countByMatiereId(Long matiereId);
}
