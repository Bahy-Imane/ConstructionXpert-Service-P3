import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {ProjectService} from "../../../core/services/project-service";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-project-form',
  standalone: true,
  imports: [
    NgIf,
    ReactiveFormsModule
  ],
  templateUrl: './project-form.component.html',
  styleUrl: './project-form.component.css'
})
export class ProjectFormComponent implements OnInit{
  projectForm !: FormGroup;
  projectId: number| null = null

  constructor(private fb:FormBuilder,
              private route:ActivatedRoute,
              private router:Router,
              private projectService:ProjectService) {
    this.projectForm = this.fb.group({
      projectName: ['', [Validators.required]],
      projectDescription: ['', [Validators.required]],
      projectStartDate: ['', [Validators.required]],
      projectEndDate: ['', [Validators.required]],

    })
  }

  onSubmit():void{
    if (this.projectForm.valid){
      const project= this.projectForm.value
      if (this.projectId){
        this.projectService.updateProject(this.projectId,project).subscribe(()=>{
          alert("edit success")
          this.router.navigate(['/projects'])
        })
      }else {
        this.projectService.addProject(project).subscribe(()=>{
          alert("add success")
          this.router.navigate(['/projects'])

        })
      }
    }

  }

  ngOnInit(): void {
    this.projectId=this.route.snapshot.params['id']
    if(this.projectId){
      this.loadProject(this.projectId)
      console.log(this.loadProject(this.projectId))
    }
  }

  loadProject(projectId: number) {
    this.projectService.getProjectById(projectId).subscribe((project)=>{
     this.projectForm.patchValue({
       projectName:project.projectName,
       projectDescription:project.projectDescription,
       projectStartDate:project.projectStartDate,
       projectEndDate:project.projectEndDate
     })
    })

  }
}
