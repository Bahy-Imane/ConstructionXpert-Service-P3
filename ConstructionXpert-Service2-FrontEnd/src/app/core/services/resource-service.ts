import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Resource} from "../model/resource.model";

@Injectable({
  providedIn: 'root'
})
export class ResourceService {
  private apiUrl = 'http://localhost:8083/api/resources';

  constructor(private http: HttpClient) { }

  getAllResources(): Observable<Resource[]> {
    return this.http.get<Resource[]>(`${this.apiUrl}/all`);
  }

  getResourceById(resourceId: number): Observable<Resource> {
    return this.http.get<Resource>(`${this.apiUrl}/${resourceId}`);
  }

  getResourcesByTaskId(taskId: number): Observable<Resource[]> {
    return this.http.get<Resource[]>(`${this.apiUrl}/task?taskId=${taskId}`);
  }

  createResource(resource: Resource): Observable<Resource> {
    return this.http.post<Resource>(`${this.apiUrl}/create`, resource);
  }

  updateResource(resourceId: number, resource: Resource): Observable<Resource> {
    return this.http.put<Resource>(`${this.apiUrl}/update?resourceId=${resourceId}`, resource);
  }

  deleteResource(resourceId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete?resourceId=${resourceId}`);
  }

  getResourcesWithSorting(field: string, direction: string): Observable<Resource[]> {
    return this.http.get<Resource[]>(`${this.apiUrl}/sorting/${field}/${direction}`);
  }

  getResourcesWithPagination(offset: number, pageSize: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/pagination/${offset}/${pageSize}`);
  }

  getResourcesWithSortingAndPagination(field: string, offset: number, pageSize: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/pagination/${offset}/${pageSize}/${field}`);
  }
}
