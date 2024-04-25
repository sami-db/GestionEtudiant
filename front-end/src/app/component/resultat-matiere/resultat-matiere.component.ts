import { Component, ViewChild } from '@angular/core';
import {MatPaginator, MatPaginatorIntl} from '@angular/material/paginator';
import { Matiere } from '../../model/matiere';
import { MatiereService } from '../../service/matiere.service';
import {MatTable, MatTableDataSource} from "@angular/material/table";
import {Router, RouterLink} from "@angular/router";
import {MaterialModule} from "../../shared/material-module";
import {HttpClientModule} from "@angular/common/http";
import { ModificationService } from '../../service/modification.service';


@Component({
  selector: 'app-resultat-matiere',
  imports: [MaterialModule, HttpClientModule, RouterLink],
  providers: [MatiereService],
  styleUrls: ['./resultat-matiere.component.css'],
  templateUrl: './resultat-matiere.component.html',
  standalone: true
})
export class ResultatMatiereComponent {
  displayedColumns: string[] = ['id', 'nom'];
  dataSource = new MatTableDataSource<Matiere>();

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
    private matiereService: MatiereService, 
    private modificationService: ModificationService
  ) {}

  ngOnInit(): void {
    this.initMatiereList();
  }

  initMatiereList(): void {
    this.matiereService.rechercherMatieres().subscribe({
      next: value => {
        this.dataSource.data = value;
        this.dataSource.paginator = this.paginator;
      },
      error: err => console.error(err)
    });
  }

  ajouterMatiere(): void {
    this.modificationService.envoyerObjetACreerOuModifier({});
    this.router.navigateByUrl('detail-matiere');
  }

  modifierMatiere(matiere: Matiere): void {
    this.modificationService.envoyerObjetACreerOuModifier(matiere);
    this.router.navigateByUrl('detail-matiere');
  }

  supprimerMatiere(id: number): void {
    this.matiereService.supprimerMatiere(id).subscribe({
      next: () => {
        this.dataSource.data = this.dataSource.data.filter(item => item.id !== id);
      },
      error: err => console.error('Erreur de suppression: ', err)
    });
  }
}
