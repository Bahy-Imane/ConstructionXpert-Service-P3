import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  private apiUrl = 'http://localhost:8081/api/projects'; // adjust the URL to your backend API

  constructor(private http: HttpClient) { }

  getAllProjects(): Observable<any> {
    return this.http.get(`${this.apiUrl}/all`);
  }

  getProjectById(projectId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/${projectId}`);
  }

  addProject(project: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/add-project`, project);
  }

  updateProject(projectId: number, project: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/update-project?projectId=${projectId}`, project);
  }

  deleteProject(projectId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/delete-project?projectId=${projectId}`);
  }

}
