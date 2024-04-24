package org.projet.gestion.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClasseDTO {
	private Long id;
    private String denomination;
    private List<Long> EtudiantIds;
    private List<EtudiantDTO> etudiants;
}

