import { Component } from '@angular/core';
import { MaterialModule } from '../../shared/material-module';

@Component({
  selector: 'app-accueil',
  standalone: true,
  imports: [MaterialModule],
  templateUrl: './accueil.component.html',
  styleUrl: './accueil.component.css'
})
export class AccueilComponent {

}
