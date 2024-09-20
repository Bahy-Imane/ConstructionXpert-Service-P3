import {Component, OnInit} from '@angular/core';
import {Task} from "../../../core/model/task.model";
import {TaskService} from "../../../core/services/task-service";
import {Router} from "@angular/router";
import {NgFor, NgForOf} from "@angular/common";

@Component({
  selector: 'app-taske-list',
  standalone: true,
  imports: [
    NgFor
  ],
  templateUrl: './taske-list.component.html',
  styleUrl: './taske-list.component.css'
})
export class TaskeListComponent implements OnInit{
  tasks:Task[]=[]

  constructor(private taskService : TaskService,private router:Router) {
  }

  ngOnInit(): void {
    this.taskService.getAllTasks().subscribe((data)=>{
      this.tasks=data
    })
  }

  addTask():void{
    this.router.navigate(['/addTask'])
  }

  editTask(id:number):void{
    this.router.navigate(['/editTask/'+id])
  }

  delete(id:number):void{
    this.taskService.deleteTask(id).subscribe(()=>{
      this.tasks= this.tasks.filter(t =>t.taskId!==id)
    })
  }

}
