import {Component, OnInit} from '@angular/core';
import {Resource} from "../../../core/model/resource.model";
import {ResourceService} from "../../../core/services/resource-service";
import {Router} from "@angular/router";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-resource-list',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './resource-list.component.html',
  styleUrl: './resource-list.component.css'
})
export class ResourceListComponent implements OnInit{
  resources :Resource[]=[]

  constructor(private resourceService:ResourceService,
              private router:Router) {
  }

  ngOnInit(): void {
    this.resourceService.getAllResources().subscribe(data=>{
      this.resources = data
    })
  }

  addResource():void{
    this.router.navigate(['/addResource'])
  }
  editeResource(id:number):void{
    this.router.navigate(['/editResource/'+id])
  }

  delete(id:number):void{
    this.resourceService.deleteResource(id).subscribe(()=>{
      this.resources= this.resources.filter(r => r.resourceId!==id)
    })

  }


}
