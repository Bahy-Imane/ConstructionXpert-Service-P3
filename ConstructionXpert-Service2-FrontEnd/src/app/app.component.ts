import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import {HomeComponent} from "./component/home/home.component";
import {ProjectListComponent} from "./component/project/project-list/project-list.component";
import {FooterComponent} from "./component/footer/footer.component";
import {HeaderComponent} from "./component/header/header.component";


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FooterComponent, HeaderComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'ConstructionXpert-Service2-FrontEnd';
}
