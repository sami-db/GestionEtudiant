import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { Note } from '../model/note';

/**
 * Le service pour connecter aux APIs de gestion des classes.
 */
@Injectable({
  providedIn: 'root'
})
export class NoteService {

  constructor(private http: HttpClient) { }

  rechercherNotes(
  ): Observable<Note[]> {
    return this.http.get(environment.afficherNoteParEtudiant) as Observable<Note[]>;
  }
}