import { Component } from '@angular/core';
import {MatCardModule} from "@angular/material/card";
import {MatFormField, MatFormFieldModule} from "@angular/material/form-field";
import {MatInput, MatInputModule} from "@angular/material/input";
import {FormsModule} from "@angular/forms";
import {MatSelectModule} from "@angular/material/select";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [MatCardModule,MatFormFieldModule, MatSelectModule, MatInputModule, FormsModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
