import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import { RouterModule } from '@angular/router';
import {HeaderComponent} from "../header/header.component";
import {NgIf} from "@angular/common";
import {ProjectService} from "../../core/services/project-service";
import {TaskService} from "../../core/services/task-service";
import {ResourceService} from "../../core/services/resource-service";

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [RouterModule, HeaderComponent, NgIf],
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {
  activeSection :string ='users';


  onSelect(section: string) {
    this.activeSection = section;
    console.log('Active Section:', this.activeSection);
  }


  allProjectsNum?: number;
  allTasksNum?: number;
  allResourcesNum?: number;

  constructor(private projectService: ProjectService,
              private taskService :TaskService,
              private resourceService :ResourceService) {}

  ngOnInit(): void {
    this.projectService.getAllProjects().subscribe({
      next: (res) => {
          this.allProjectsNum = res.length;
      }
    });

    // Get total number of failures
    this.taskService.getAllTasks().subscribe({
      next: (res) => {
          this.allTasksNum = res.length;

      }
    });

    this.resourceService.getAllResources().subscribe({
      next: (res) => {

          this.allResourcesNum = res.length;

      }
    });

  }
}
