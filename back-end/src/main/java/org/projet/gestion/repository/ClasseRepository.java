package org.projet.gestion.repository;

import java.util.List;

import org.projet.gestion.model.Classe;
import org.projet.gestion.model.Etudiant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasseRepository extends CrudRepository<Classe, Long> {
}
