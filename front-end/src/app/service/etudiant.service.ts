import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Etudiant } from '../model/etudiant';
import { environment } from '../environments/environment';


/**
 * Le service pour connecter aux APIs de gestion des classes.
 */
@Injectable({
  providedIn: 'root'
})
export class EtudiantService {

  constructor(private http: HttpClient) { }

  rechercherEtudiants(
  ): Observable<Etudiant[]> {
    return this.http.get(environment.rechercherEtudiants) as Observable<Etudiant[]>;
  }

  rechercherEtudiantsDisponibles(
  ): Observable<Etudiant[]> {
    return this.http.get(environment.rechercherEtudiantsDisponibles) as Observable<Etudiant[]>;
  }

  enregistrerEtudiant(etudiant: Etudiant
  ): Observable<Etudiant> {
    return this.http.post(environment.enregistrerEtudiant, etudiant) as Observable<Etudiant>;
  }

  supprimerEtudiant(etudiantId: number
  ): Observable<HttpResponse<string>> {
    let paramList: HttpParams = new HttpParams();
    paramList = paramList.set('id', etudiantId);
    return this.http.delete(environment.supprimerEtudiant, {
      params: paramList,
    }) as Observable<HttpResponse<string>>;
  }
}
