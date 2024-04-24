import { Component, ViewChild } from '@angular/core';
import {MatPaginator, MatPaginatorIntl} from '@angular/material/paginator';
import { Devoir } from '../../model/devoir';
import { DevoirService } from '../../service/devoir.service';
import {MatTable, MatTableDataSource} from "@angular/material/table";
import {Router, RouterLink} from "@angular/router";
import {MaterialModule} from "../../shared/material-module";
import {HttpClientModule} from "@angular/common/http";

@Component({
  selector: 'app-resultat-devoir',
  imports: [MaterialModule, HttpClientModule, RouterLink],
  providers: [DevoirService],
  styleUrls: ['./resultat-devoir.component.css'],
  templateUrl: './resultat-devoir.component.html',
  standalone: true
})
export class ResultatDevoirComponent {
  displayedColumns: string[] = ['id', 'type', 'date', 'coefficient', 'matiere', 'classe'];
  dataSource = new MatTableDataSource<Devoir>();

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

  constructor(private router: Router, private devoirService: DevoirService) {}

  ngOnInit(): void {
    this.initMatiereList();
  }

  initMatiereList(): void {
    this.devoirService.rechercherDevoirs().subscribe({
      next: value => {
        this.dataSource.data = value;
        this.dataSource.paginator = this.paginator;
      },
      error: err => console.error(err)
    });
  }

  ajouterDevoir(): void {
    this.router.navigateByUrl('detail-devoir');
  }

  modifierDevoir(devoir: Devoir): void {
    this.router.navigateByUrl('detail-devoir');
  }

  supprimerDevoir(id: number): void {
    this.devoirService.supprimerDevoir(id).subscribe({
      next: () => {
        this.dataSource.data = this.dataSource.data.filter(item => item.id !== id);
      },
      error: err => console.error('Erreur de suppression: ', err)
    });
  }
}
