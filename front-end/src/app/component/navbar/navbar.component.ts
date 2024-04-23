import { Component } from '@angular/core';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { NavigationEnd, Router, RouterLink, RouterModule, RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [MatButtonModule, MatMenuModule, RouterLink, RouterOutlet, RouterModule
    , CommonModule /*pour pourvoir utiliser ngclass*/],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  coleurAccueilClassActive: string = '';
  coleurClasseClassActive: string = '';
  coleurEtudiantClassActive: string = '';
  coleurMatiereClassActive: string = '';
  coleurDevoirClassActive: string = '';
  constructor(private router: Router) {
    // Écouter les changements de route
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.updateButtonColor(event.url);
      }
    });
  }

  public afficherMessage(): void {
    alert("Ce sera à vous d'implémenter pour la gestion des devoirs");
  }

  /**
   * Changement de couleur de bouton en fonction de la page en cours.
   * @param url url en cours
   */
  updateButtonColor(url: string): void {
    if (url.includes('classe')) {
      this.coleurAccueilClassActive = 'no-active-button';
      this.coleurClasseClassActive = 'active-button';
      this.coleurEtudiantClassActive = 'no-active-button';
      this.coleurMatiereClassActive = 'no-active-button';
      this.coleurDevoirClassActive = 'no-active-button';
    } else if (url.includes('etudiant')) {
      this.coleurAccueilClassActive = 'no-active-button';
      this.coleurClasseClassActive = 'no-active-button';
      this.coleurEtudiantClassActive = 'active-button';
      this.coleurMatiereClassActive = 'no-active-button';
      this.coleurDevoirClassActive = 'no-active-button';
    } else if (url.includes('matiere')) {
      this.coleurAccueilClassActive = 'no-active-button';
      this.coleurClasseClassActive = 'no-active-button';
      this.coleurEtudiantClassActive = 'no-active-button';
      this.coleurMatiereClassActive = 'active-button';
      this.coleurDevoirClassActive = 'no-active-button';
    } else if (url.includes('devoir')) {
      this.coleurAccueilClassActive = 'no-active-button';
      this.coleurClasseClassActive = 'no-active-button';
      this.coleurEtudiantClassActive = 'no-active-button';
      this.coleurMatiereClassActive = 'no-active-button';
      this.coleurDevoirClassActive = 'active-button';
    } else {
      this.coleurAccueilClassActive = 'active-button';
      this.coleurClasseClassActive = 'no-active-button';
      this.coleurEtudiantClassActive = 'no-active-button';
      this.coleurMatiereClassActive = 'no-active-button';
      this.coleurDevoirClassActive = 'no-active-button';
    }
  }
}
