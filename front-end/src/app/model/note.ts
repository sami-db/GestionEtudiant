import { Etudiant } from "./etudiant";
import { Devoir } from "./devoir";


export interface Note {
  id?: number;
  valeur?: number;
  etudiants?: Etudiant[];
  devoirs?: Devoir[];
}
