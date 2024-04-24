import { Routes } from '@angular/router';
import { AccueilComponent } from './component/accueil/accueil.component';
import { ResultatClasseComponent } from './component/resultat-classe/resultat-classe.component';
import { ResultatEtudiantComponent } from './component/resultat-etudiant/resultat-etudiant.component';
import { DetailEtudiantComponent } from './component/detail-etudiant/detail-etudiant.component';
import { DetailClasseComponent } from './component/detail-classe/detail-classe.component';
import { DetailDevoirComponent } from './component/detail-devoir/detail-devoir.component';
import { ResultatMatiereComponent } from './component/resultat-matiere/resultat-matiere.component';
import { ResultatDevoirComponent } from './component/resultat-devoir/resultat-devoir.component';

export const routes: Routes = [
  { path: '', component: AccueilComponent, },
  { path: 'classes', component: ResultatClasseComponent },
  { path: 'etudiants', component: ResultatEtudiantComponent },
  { path: 'matieres', component: ResultatMatiereComponent },
  { path: 'devoirs', component: ResultatDevoirComponent },
  { path: 'detail-classe', component: DetailClasseComponent },
  { path: 'detail-devoir', component: DetailDevoirComponent },
  { path: 'detail-etudiant', component: DetailEtudiantComponent },

];
