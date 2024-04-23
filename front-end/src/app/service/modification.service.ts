import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

/**
 * Le service utilise BehaviorSubject pour émettre des événements dans le cas de création ou modification d'un objet.
 */
@Injectable({
  providedIn: 'root'
})
export class ModificationService {
  private objetInitiale = {};
  private objetAModifier = new BehaviorSubject<any>(this.objetInitiale);
  objet$ = this.objetAModifier.asObservable();

  envoyerObjetACreerOuModifier(objetAModifier: any) {
    this.objetAModifier.next(objetAModifier);
  }

  reinitialiserObjetAModifier() {
    this.objetAModifier.next(this.objetInitiale);
  }
}
