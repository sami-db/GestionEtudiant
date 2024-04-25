import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../shared/material-module';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MatiereService } from '../../service/matiere.service';
import { Router } from '@angular/router';
import { ModificationService } from '../../service/modification.service';
import { Subscription } from 'rxjs';
import { Matiere } from '../../model/matiere';

@Component({
  selector: 'app-detail-matiere',
  standalone: true,
  imports: [MaterialModule, HttpClientModule
  ],
  providers: [MatiereService],
  templateUrl: './detail-matiere.component.html',
  styleUrl: './detail-matiere.component.css'
})
export class DetailMatiereComponent implements OnInit {
  matiere!: Matiere;
  denomination?: String;

  /**
   * Les forms controles
   */
  matiereFormGroup!: FormGroup;
  denominationCtrlForm!: FormControl;
  private subscriptionMatiereAModifier!: Subscription;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private matiereService: MatiereService,
    private modificationService: ModificationService
  ) { }

  message !: string;
  ngOnInit(): void {
    this.initMatiere();
    this.initFormControl();
  }

  ngOnDestroy() {
    this.subscriptionMatiereAModifier.unsubscribe();
  }

  /**
   * Cette méthode s'abonner au service de modification pour initialiser la matière à créer ou à modifier.
   */
  private initMatiere(): void {
    this.subscriptionMatiereAModifier = this.modificationService.objet$.subscribe(
      (matiereAModifier: Matiere) => {
        this.matiere = matiereAModifier;
      });
  }

  /**
   * Initialiser des controles sur le formulaire.
   */
  private initFormControl(): void {
    this.denominationCtrlForm = this.formBuilder.control(
      this.matiere.denomination,
      [Validators.required, Validators.maxLength(100)]
    );

    this.matiereFormGroup = this.formBuilder.group({
      nom: this.denominationCtrlForm
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
   * Enregistrer la matière.
   */
  public enregistrer(): void {
    this.matiereService.enregistrerMatiere(this.matiere).subscribe({
      next: value => {
        this.router.navigateByUrl('matieres');
      },
      error: err => console.error(err)
    });
  }

}
