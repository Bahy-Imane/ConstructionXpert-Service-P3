import {Component, OnInit} from '@angular/core';
import {Project} from "../../../core/model/project.model";
import {ProjectService} from "../../../core/services/project-service";
import {Router} from "@angular/router";
import {DatePipe, NgForOf} from "@angular/common";

@Component({
  selector: 'app-project-list',
  standalone: true,
  imports: [
    DatePipe,
    NgForOf
  ],
  templateUrl: './project-list.component.html',
  styleUrl: './project-list.component.css'
})
export class ProjectListComponent implements OnInit{

  projects : Project[]=[]

  constructor(private projectService:ProjectService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.projectService.getAllProjects().subscribe((data)=>{
      this.projects=data
    })
  }

  addProject():void{
    this.router.navigate(['/add'])
  }
  editProject(id:number) {
    this.router.navigate(['/edit/'+id])

  }

  deleteProject(id:number) {
    this.projectService.deleteProject(id).subscribe(()=>{
      this.projects = this.projects.filter(p=>p.projectId !==id)
    })

  }
}
