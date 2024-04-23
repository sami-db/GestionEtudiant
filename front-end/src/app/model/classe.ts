import { Etudiant } from "./etudiant";

export interface Classe {
  id?: number;
  nom?: string;
  etudiants?: Etudiant[];
}
