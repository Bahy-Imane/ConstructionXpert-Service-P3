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
    return this.http.get<Resource[]>(this.apiUrl + '/all');
  }

  getResourceByTaskId(taskId: number): Observable<Resource[]> {
    return this.http.get<Resource[]>(this.apiUrl + '/task', { params: { taskId } });

  }
  getResourceById(resourceId: number): Observable<any> {
    return this.http.get<any>(this.apiUrl + '/' + resourceId  );
  }

  createResource(resource: Resource): Observable<Resource> {
    return this.http.post<Resource>(this.apiUrl + '/create', resource);
  }

  updateResource(resourceId: number, resource: Resource): Observable<Resource> {
    return this.http.put<Resource>(this.apiUrl + '/update', resource, { params: { resourceId } });
  }

  deleteResource(resourceId: number): Observable<any> {
    return this.http.delete(this.apiUrl + '/delete', { params: { resourceId } });
  }
}
