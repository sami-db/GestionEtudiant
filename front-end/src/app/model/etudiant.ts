import { Classe } from './classe';
export interface Etudiant {
  id?: number;
  nom?: string;
  prenom?: string;
  photo?: string;
  classeId?: number;
  nomClasse?: string;
}
