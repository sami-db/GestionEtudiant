package org.projet.gestion.repository;

import org.projet.gestion.model.Classe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasseRepository extends CrudRepository<Classe, Long> {
}
