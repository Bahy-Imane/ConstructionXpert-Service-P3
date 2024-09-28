import {Component, OnInit} from '@angular/core';
import {Resource} from "../../../core/model/resource.model";
import {ResourceService} from "../../../core/services/resource-service";
import {MatTable} from "@angular/material/table";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {MatDialog} from "@angular/material/dialog";
import {ResourceFormComponent} from "../resource-form/resource-form.component";
import {FormsModule} from "@angular/forms";
import {MatIcon} from "@angular/material/icon";
import {NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-resource-test',
  standalone: true,
  imports: [
    MatTable,
    MatPaginator,
    FormsModule,
    MatIcon,
    NgIf,
    NgForOf
  ],
  templateUrl: './resource-test.component.html',
  styleUrl: './resource-test.component.css'
})
export class ResourceTestComponent implements OnInit {
  currentSortField: string = 'id';
  currentSortDirection: string = 'asc';
  resource: Resource[]=[];
  totalResource: number = 0;
  pageSize: number = 5;
  currentPage: number = 0;

  constructor(private resourceService: ResourceService, private dialog: MatDialog,) {}

  ngOnInit(): void {
    this.sortedResources('resourceId', 'asc');
    this.paginateResources(this.currentPage, this.pageSize);
  }

  sortedResources(field: string, direction: string): void {
    this.resourceService.getResourcesWithSorting(field, direction).subscribe((data) => {
      this.resource = data;
      this.totalResource = data.length;
    });
  }

  paginateResources(offset: number, pageSize: number): void {
    this.resourceService.getResourcesWithPagination(offset, pageSize).subscribe((data) => {
      this.resource = data;
    });
  }

  getResources():void {
    this.resourceService.getAllResources().subscribe((data)=>{
      this.totalResource = data.length;
    })
  }

  onPageChange(event: PageEvent): void {
    this.currentPage = event.pageIndex;
    this.pageSize = event.pageSize;
    this.paginateResources(this.currentPage, this.pageSize);
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(ResourceFormComponent, {
      width: '250px',
    });

  }

  searchText!: '';

  filteredProjects() {
    if (!this.searchText) {
      return this.resource;
    } else {
      return this.resource.filter(resource =>
        resource.resourceQuantity?.toLowerCase().includes(this.searchText.toLowerCase()) ||
        resource.resourceName?.toLowerCase().includes(this.searchText.toLowerCase())
      );
    }

  }

  sort(field: string) {
    if (this.currentSortField === field) {
      this.currentSortDirection = this.currentSortDirection === 'asc' ? 'desc' : 'asc';
    } else {
      this.currentSortField = field;
      this.currentSortDirection = 'asc';
    }

    this.sortedResources(this.currentSortField, this.currentSortDirection);

  }


}
