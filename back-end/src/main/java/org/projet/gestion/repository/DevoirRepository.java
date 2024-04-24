package org.projet.gestion.repository;

import org.projet.gestion.model.Devoir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface DevoirRepository extends JpaRepository<Devoir, Long> {
    long countByMatiereId(Long matiereId);
}
