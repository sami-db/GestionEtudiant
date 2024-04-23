import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../shared/material-module';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Etudiant } from '../../model/etudiant';
import { Classe } from '../../model/classe';
import { ClasseService } from '../../service/classe.service';
import { HttpClientModule } from '@angular/common/http';
import { EtudiantService } from '../../service/etudiant.service';
import { Router } from '@angular/router';
import { ModificationService } from '../../service/modification.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-detail-etudiant',
  standalone: true,
  imports: [MaterialModule, HttpClientModule
  ],
  providers: [ClasseService, EtudiantService],
  templateUrl: './detail-etudiant.component.html',
  styleUrl: './detail-etudiant.component.css'
})
export class DetailEtudiantComponent implements OnInit {
  etudiant!: Etudiant;
  classeList: Classe[] = [];
  photoFile?: File;
  nomPhotoFile = '';
  /**
   * Les forms controles
   */
  etudiantFormGroup!: FormGroup;
  nomCtrlForm!: FormControl;
  prenomCtrlForm!: FormControl;
  private subscriptionEtudiantAModifier!: Subscription;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private classeService: ClasseService,
    private etudiantService: EtudiantService,
    private modificationService: ModificationService
  ) { }

  message !: string;
  ngOnInit(): void {
    this.initEtudiant();
    this.initFormControl();
    this.initClasseList();
  }

  ngOnDestroy() {
    this.subscriptionEtudiantAModifier.unsubscribe();
  }

  /**
   * Cette méthode s'abonner au service de modification pour initialiser l'étudiant à créer ou à modifier.
   */
  private initEtudiant(): void {
    this.subscriptionEtudiantAModifier = this.modificationService.objet$.subscribe(
      (etudiantAModifier: Etudiant) => {
        this.etudiant = etudiantAModifier;
      });
  }

  /**
   * Initialiser des controles sur le formulaire.
   */
  private initFormControl(): void {
    this.nomCtrlForm = this.formBuilder.control(
      this.etudiant.nom,
      [Validators.required, Validators.maxLength(100)]
    );

    this.prenomCtrlForm = this.formBuilder.control(
      this.etudiant.prenom,
      [Validators.required, Validators.maxLength(100)]
    );

    this.etudiantFormGroup = this.formBuilder.group({
      nom: this.nomCtrlForm,
      prenom: this.prenomCtrlForm
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
   * La méthode pour sélectionner un fichier.
   * @param event
   */
  public selectFile(event: any): void {
    const selectedFiles = event.target.files;
    if (selectedFiles) {
      const file: File | null = selectedFiles.item(0);
      if (file) {
        this.photoFile = file;
        const reader = new FileReader();
        reader.onload = (e: any) => {
          this.etudiant.photo = reader.result as string;
        };
        this.nomPhotoFile = this.photoFile.name;
        reader.readAsDataURL(this.photoFile);
      } else {
        this.etudiant.photo = undefined;
      }
    }
  }
  /**
   * Enregistrer l'étudiant.
   */
  public enregistrer(): void {
    this.etudiantService.enregistrerEtudiant(this.etudiant).subscribe({
      next: value => {
        this.router.navigateByUrl('etudiants');
      },
      error: err => console.error(err)
    });
  }

}
