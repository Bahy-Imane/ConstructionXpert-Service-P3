import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {TaskService} from "../../../core/services/task-service";
import {ActivatedRoute, Router} from "@angular/router";
import {NgForOf, NgIf} from "@angular/common";
import {Project} from "../../../core/model/project.model";
import {ProjectService} from "../../../core/services/project-service";

@Component({
  selector: 'app-taske-form',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NgIf,
    NgForOf
  ],
  templateUrl: './taske-form.component.html',
  styleUrl: './taske-form.component.css'
})
export class TaskeFormComponent implements OnInit{
  taskForm!:FormGroup
  taskId:number|null=null
  projects : Project[]=[]

  constructor(private taskService:TaskService,
              private projectService:ProjectService,
              private route:ActivatedRoute,
              private fb :FormBuilder,
              private router:Router) {
    this.taskForm= this.fb.group({
      taskTitle : ['',[Validators.required]],
      taskDescription : ['',[Validators.required]],
      taskStatus : ['',[Validators.required]],
      projectId : ['',[Validators.required]],
    })
  }

  ngOnInit(): void {
    this.taskId = this.route.snapshot.params['id']
    if(this.taskId){
     this.taskService.getTaskById(this.taskId).subscribe((task)=>{
       this.taskForm.patchValue({
         taskTitle : task.taskTitle,
           taskDescription :task.taskDescription,
         taskStatus : task.taskStatus,
         projectId:task.projectId
     })
     })
    };

    this.projectService.getAllProjects().subscribe((data)=>{
      this.projects=data
    })
  }

  onSubmit():void{
    if (this.taskForm.valid){
      const task= this.taskForm.value
      if (this.taskId){
        this.taskService.updateTask(this.taskId, task).subscribe(()=>{
          alert("task edited success")
          this.router.navigate(['/tasks'])
        })
      }else {
        this.taskService.createTask(task).subscribe(()=>{
          alert("task add success")
          this.router.navigate(['/tasks'])

        })
      }
    }
  }

}
