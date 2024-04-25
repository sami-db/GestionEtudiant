import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator, MatPaginatorIntl } from '@angular/material/paginator';
import { HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';
import { Note } from '../../model/note';
import { NoteService } from '../../service/note.service';
import { MaterialModule } from '../../shared/material-module';
import { MAT_TOOLTIP_DEFAULT_OPTIONS, MatTooltipDefaultOptions } from '@angular/material/tooltip';

export const myCustomTooltipDefaults: MatTooltipDefaultOptions = {
  showDelay: 1000,
  hideDelay: 1000,
  touchendHideDelay: 1000,
};

@Component({
  selector: 'app-resultat-accueil',
  standalone: true,
  imports: [MaterialModule, HttpClientModule],
  providers: [NoteService, { provide: MAT_TOOLTIP_DEFAULT_OPTIONS, useValue: myCustomTooltipDefaults }],
  templateUrl: './detail-accueil.component.html',
  styleUrls: ['./detail-accueil.component.css'] // Correction from 'styleUrl' to 'styleUrls' which should be an array
})
export class DetailAccueilComponent implements OnInit {
  displayedColumns: string[] = ['id', 'valeur', 'etudiants', 'devoirs'];
  dataSource = new MatTableDataSource<Note>();

  @ViewChild(MatPaginator) set paginator(pager: MatPaginator) {
    if (pager) {
      this.dataSource.paginator = pager;
      this.dataSource.paginator._intl = new MatPaginatorIntl();
      this.dataSource.paginator._intl.itemsPerPageLabel = `Total : ${this.dataSource.data.length} - Éléments par page`;
      this.dataSource.paginator._intl.getRangeLabel = function (
        page,
        pageSize,
        length
      ) {
        if (length === 0) {
          return 'Page 1 sur 1';
        }
        const amountPages = Math.ceil(length / pageSize);
        return `Page ${page + 1} sur ${amountPages}`;
      };
    }
  }

  constructor(
    private router: Router,
    private noteService: NoteService // Inject the NoteService
  ) { }

  ngOnInit(): void {
    this.initNoteList();
  }

  /**
   * Initialiser la liste des notes pour un étudiant donné
   */
  initNoteList(): void {
    this.noteService.rechercherNotes().subscribe({
      next: (notes: Note[]) => this.dataSource.data = notes,
      error: (err: any) => console.error('Erreur lors de la récupération des notes', err)
    });
    this.dataSource.paginator = this.paginator;
  }
}
