import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { Devoir } from '../model/devoir';

@Injectable({
  providedIn: 'root'
})
export class DevoirService {

  constructor(private http: HttpClient) { }

  rechercherDevoirs(): Observable<Devoir[]> {
    return this.http.get(environment.rechercherDevoirs) as Observable<Devoir[]>;
  }

  supprimerDevoir(id: number): Observable<HttpResponse<string>> {
    let paramList: HttpParams = new HttpParams();
    paramList = paramList.set('id', id.toString());
    return this.http.delete(environment.supprimerDevoir, {
      params: paramList,
      observe: 'response'
    }) as Observable<HttpResponse<string>>;
  }

  enregistrerDevoir(devoir: Devoir): Observable<Devoir> {
    return this.http.post(environment.enregistrerDevoir, devoir) as Observable<Devoir>;
  }
}
