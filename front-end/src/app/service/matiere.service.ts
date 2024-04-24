import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { Matiere } from '../model/matiere';

@Injectable({
  providedIn: 'root'
})
export class MatiereService {

  constructor(private http: HttpClient) { }

  rechercherMatieres(): Observable<Matiere[]> {
    return this.http.get(environment.rechercherMatieres) as Observable<Matiere[]>;
  }

  supprimerMatiere(id: number): Observable<HttpResponse<string>> {
    let paramList: HttpParams = new HttpParams();
    paramList = paramList.set('id', id.toString());
    return this.http.delete(environment.supprimerMatiere, {
      params: paramList,
      observe: 'response'
    }) as Observable<HttpResponse<string>>;
  }

  enregistrerMatiere(matiere: Matiere): Observable<Matiere> {
    return this.http.post(environment.enregistrerMatiere, matiere) as Observable<Matiere>;
  }
}
