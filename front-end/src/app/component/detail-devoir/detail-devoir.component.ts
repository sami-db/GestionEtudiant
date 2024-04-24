import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../shared/material-module';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Matiere } from '../../model/matiere';
import { Devoir } from '../../model/devoir';
import { Classe } from '../../model/classe';
import { ClasseService } from '../../service/classe.service';
import { MatiereService } from '../../service/matiere.service';
import { HttpClientModule } from '@angular/common/http';
import { DevoirService } from '../../service/devoir.service';
import { Router } from '@angular/router';
import { ModificationService } from '../../service/modification.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-detail-devoir',
  standalone: true,
  imports: [MaterialModule, HttpClientModule
  ],
  providers: [DevoirService, ClasseService, MatiereService],
  templateUrl: './detail-devoir.component.html',
  styleUrl: './detail-devoir.component.css'
})
export class DetailDevoirComponent implements OnInit {
  devoir!: Devoir;
  type?: string;
  coefficient?: number;
  matiereList: Matiere[] = [];
  classeList: Classe[] = [];
  /**
   * Les forms controles
   */
  DevoirFormGroup!: FormGroup;
  typeCtrlForm!: FormControl;
  dateCtrlForm!: FormControl;
  coefficientCtrlForm!: FormControl;
  private subscriptionDevoirAModifier!: Subscription;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private devoirService: DevoirService,
    private matiereService: MatiereService,
    private classeService: ClasseService,
    private modificationService: ModificationService
  ) { }

  message !: string;
  ngOnInit(): void {
    this.initDevoir();
    this.initFormControl();
    this.initClasseList();
    this.initMatiereList();
  }

  ngOnDestroy() {
    this.subscriptionDevoirAModifier.unsubscribe();
  }

  /**
   * Cette méthode s'abonner au service de modification pour initialiser l'étudiant à créer ou à modifier.
   */
  private initDevoir(): void {
    this.subscriptionDevoirAModifier = this.modificationService.objet$.subscribe(
      (devoirAModifier: Devoir) => {
        this.devoir = devoirAModifier;
      });
  }

  /**
   * Initialiser des controles sur le formulaire.
   */
  private initFormControl(): void {
    this.typeCtrlForm = this.formBuilder.control(
      this.devoir.type,
      [Validators.required, Validators.maxLength(100)]
    );

    this.dateCtrlForm = this.formBuilder.control(
      this.devoir.date,
      [Validators.required, Validators.maxLength(100)]
    );

    this.DevoirFormGroup = this.formBuilder.group({
      type: this.typeCtrlForm,
      date: this.dateCtrlForm
    });
  }

  /**
   * Initialiser la liste des classes pour pouvoir affecter à l'étudiant.
   */
  private initClasseList(): void {
    this.classeService.rechercherClasses().subscribe({
      next: value => this.classeList = value,
      error: err => console.error(err)
    });
  }

    /**
   * Initialiser la liste des matières pour pouvoir affecter à l'étudiant.
   */
    private initMatiereList(): void {
      this.matiereService.rechercherMatieres().subscribe({
        next: value => this.matiereList = value,
        error: err => console.error(err)
      });
    }
  /**
   * Récupérer le message d'erreur en fonction de validateur.
   * @param ctrl le formcontrol
   * @returns
   */
  public getMessageErreur(ctrl: AbstractControl) {
    if (!ctrl) {
      return;
    }
    if (ctrl.hasError('required')) {
      return 'Champs obligatoire';
    } else if (ctrl.hasError('maxlength')) {
      return (
        'Champs de caractère maximum ' +
        ctrl.getError('maxlength').requiredLength
      );
    }
    return;
  }

  /**
   * REset la date 
   */

  clearDate() {
    this.devoir.date = null; 
  }
  
  public enregistrer(): void {
    this.devoirService.enregistrerDevoir(this.devoir).subscribe({
      next: value => {
        this.router.navigateByUrl('devoirs');
      },
      error: err => console.error(err)
    });
  }

}