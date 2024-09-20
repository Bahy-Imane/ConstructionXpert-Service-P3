import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {ResourceService} from "../../../core/services/resource-service";
import {ActivatedRoute, Router} from "@angular/router";
import {TaskService} from "../../../core/services/task-service";
import {Task} from "../../../core/model/task.model";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-resource-form',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NgForOf
  ],
  templateUrl: './resource-form.component.html',
  styleUrl: './resource-form.component.css'
})
export class ResourceFormComponent implements OnInit{
  resourceForm !:FormGroup
  resourceId:number |null = null
  tasks:Task[]=[]

  constructor(private resourceService:ResourceService,
              private fb:FormBuilder,
              private route:ActivatedRoute,
              private router:Router,
              private taskService:TaskService) {
    this.resourceForm = this.fb.group({
      resourceName :['',[Validators.required]],
      resourceType :['',[Validators.required]],
      resourceQuantity :['',[Validators.required]],
      resourceProvider :['',[Validators.required]],
      taskId :['',[Validators.required]],
    })
  }

  ngOnInit(): void {
    this.resourceId = this.route.snapshot.params['id']
    if(this.resourceId){
      this.resourceService.getResourceById(this.resourceId).subscribe((resource)=>{
        this.resourceForm.patchValue({
          resourceName:resource.resourceName,
          resourceType:resource.resourceType,
          resourceQuantity:resource.resourceQuantity,
          resourceProvider:resource.resourceProvider,
          taskId:resource.taskId,
        })
      })
    }
    this.taskService.getAllTasks().subscribe((data)=>{
      this.tasks=data
    })
  }

  onSubmit():void{
    if (this.resourceForm.valid){
      const resource=this.resourceForm.value
      if (this.resourceId){
        this.resourceService.updateResource(this.resourceId,resource).subscribe(()=>{
          alert("Resource Edited success")
          this.router.navigate(['/resources'])
        })
      }else {
        this.resourceService.createResource(resource).subscribe(()=>{
          alert("Resource add success")
          this.router.navigate(['/resources'])
        })
      }
    }
  }

}
