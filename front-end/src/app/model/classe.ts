import { Etudiant } from "./etudiant";

export interface Classe {
  id?: number;
  denomination?: string;
  etudiants?: Etudiant[];
}
