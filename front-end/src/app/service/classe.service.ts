import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { Classe } from '../model/classe';

/**
 * Le service pour connecter aux APIs de gestion des classes.
 */
@Injectable({
  providedIn: 'root'
})
export class ClasseService {

  constructor(private http: HttpClient) { }

  rechercherClasses(
  ): Observable<Classe[]> {
    return this.http.get(environment.rechercherClasses) as Observable<Classe[]>;
  }

  supprimerClasse(classeId: number
  ): Observable<HttpResponse<string>> {
    let paramList: HttpParams = new HttpParams();
    paramList = paramList.set('id', classeId);
    return this.http.delete(environment.supprimerClasse, {
      params: paramList,
    }) as Observable<HttpResponse<string>>;
  }


  enregistrerClasse(classe: Classe
  ): Observable<Classe> {
    return this.http.post(environment.enregistrerClasse, classe) as Observable<Classe>;
  }
}
