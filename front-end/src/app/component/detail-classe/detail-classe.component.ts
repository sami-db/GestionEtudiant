import { Component, OnInit, ViewChild } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ClasseService } from '../../service/classe.service';
import { EtudiantService } from '../../service/etudiant.service';
import { Etudiant } from '../../model/etudiant';
import { Classe } from '../../model/classe';
import { MaterialModule } from '../../shared/material-module';
import { HttpClientModule } from '@angular/common/http';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { SelectionModel } from '@angular/cdk/collections';
import { ModificationService } from '../../service/modification.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-detail-classe',
  standalone: true,
  imports: [MaterialModule],
  providers: [ClasseService, EtudiantService],
  templateUrl: './detail-classe.component.html',
  styleUrl: './detail-classe.component.css'
})
export class DetailClasseComponent implements OnInit {
  classe!: Classe;
  classeFormGroup!: FormGroup;
  nomCtrlForm!: FormControl;
  displayedColumns: string[] = ['id', 'nom', 'prenom'];
  dataSourceEtudiants = new MatTableDataSource<Etudiant>();
  @ViewChild(MatPaginator) paginator: MatPaginator | null = null;
  selection = new SelectionModel<Etudiant>(true, []);
  private subscriptionClasseAModifier!: Subscription;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private classeService: ClasseService,
    private etudiantService: EtudiantService,
    private modificationService: ModificationService
  ) { }

  ngOnInit(): void {
    this.initLaClasse();
    this.initEtudiants();
    this.initFormControl();
  }

  ngOnDestroy() {
    this.subscriptionClasseAModifier.unsubscribe();
  }

  /**
   * Cette méthode s'abonner au service de modification pour initialiser la classe.
   */
  private initLaClasse(): void {
    this.subscriptionClasseAModifier = this.modificationService.objet$.subscribe(
      (classeAModifier: Classe) => {
        this.classe = classeAModifier;
      });
  }

  /**
   * Initialiser des controles sur le formulaire.
   */
  private initFormControl(): void {
    this.nomCtrlForm = this.formBuilder.control(
      this.classe.denomination,
      [Validators.required, Validators.maxLength(100)]
    );
    this.classeFormGroup = this.formBuilder.group({
      nom: this.nomCtrlForm
    });
  }
  /**
   * Si tous les checkbox sont sélectionnés.
   * @returns
   */
  isTousSelectionnes(): boolean {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSourceEtudiants.data.length;
    return numSelected === numRows;
  }

  /**
   * action du bouton de "Sélectionner/déselectionner" tous les étudiants
   */
  selectionnerOuDeselectionnerTous(): void {
    this.isTousSelectionnes() ?
      this.selection.clear() :
      this.dataSourceEtudiants.data.forEach(row => this.selection.select(row));
  }

  /**
   * Enregistrer la classe.
   */
  enregistrer(): void {
    if (this.classeFormGroup.valid) {
      this.classe.etudiants = this.selection.selected;
      this.classeService.enregistrerClasse(this.classe).subscribe({
        next: value => {
          this.router.navigateByUrl('classes');
        },
        error: err => console.error(err)
      });
    }
  }
  /**
   * Récupérer le message d'erreur en fonction de validateur.
   * @param ctrl le formcontrol
   * @returns
   */
  getMessageErreur(ctrl: AbstractControl): string {
    if (!ctrl) {
      return "";
    }
    if (ctrl.hasError('required')) {
      return 'Champs obligatoire';
    } else if (ctrl.hasError('maxlength')) {
      return (
        'Champs de caractère maximum ' +
        ctrl.getError('maxlength').requiredLength
      );
    }
    return "";
  }

  /**
   * Initialiser la liste des étudiants de la classe ou à ajouter dans la classe.
   */
  private initEtudiants(): void {
    this.dataSourceEtudiants.data = this.classe.etudiants ? this.classe.etudiants : [];
    // Sélectionner tous les étudiants de la classe, en cas de modification
    this.dataSourceEtudiants.data.forEach(row => this.selection.select(row));

    this.etudiantService.rechercherEtudiantsDisponibles().subscribe({
      next: value => this.dataSourceEtudiants.data = [...this.dataSourceEtudiants.data, ...value],
      error: err => console.error(err)
    });
    this.dataSourceEtudiants.paginator = this.paginator;
  }

}
