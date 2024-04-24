import { Classe } from "./classe";
import { Matiere } from "./matiere";

export interface Devoir {
  id?: number;
  type?: string;
  date?: Date | null;
  coefficient?: number;
  matiere?: Matiere;
  classe?: Classe;
}
