package org.projet.gestion.repository;

import java.util.List;

import org.projet.gestion.model.Etudiant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EtudiantRepository extends CrudRepository<Etudiant, Long> {
    List<Etudiant> findByClasse_Id(Long id);
}
