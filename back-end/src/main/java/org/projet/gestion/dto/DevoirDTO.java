package org.projet.gestion.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DevoirDTO {
	private Long id;
    private String type;
    private LocalDate date;
    private int coefficient;
    private String nomClasse;
    private String nomMatiere;
}
