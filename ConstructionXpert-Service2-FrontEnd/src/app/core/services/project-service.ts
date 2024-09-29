import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import {HttpClient} from "@angular/common/http";
import {Project} from "../model/project.model";
import {Page} from "../model/page.mpdel";

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  private apiUrl = 'http://localhost:8888/PROJECTS-MANAGER/api/projects'; // adjust the URL to your backend API

  constructor(private http: HttpClient) {}

  // Récupérer tous les projets
  getAllProjects(): Observable<Project[]> {
    return this.http.get<Project[]>(`${this.apiUrl}/all`);
  }

  // Récupérer un projet par ID
  getProjectById(projectId: number): Observable<Project> {
    return this.http.get<Project>(`${this.apiUrl}/${projectId}`);
  }

  // Ajouter un nouveau projet
  addProject(project: Project): Observable<Project> {
    return this.http.post<Project>(`${this.apiUrl}/add-project`, project);
  }

  // Mettre à jour un projet
  updateProject(projectId: number, project: Project): Observable<Project> {
    return this.http.put<Project>(`${this.apiUrl}/update-project?projectId=${projectId}`, project);
  }

  // Supprimer un projet
  deleteProject(projectId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete-project?projectId=${projectId}`);
  }

  // Récupérer les projets triés par champ
  getProjectsWithSorting(field: string): Observable<Project[]> {
    return this.http.get<Project[]>(`${this.apiUrl}/sorting/${field}`);
  }

  // Pagination simple
  getProjectsWithPagination(offset: number, pageSize: number): Observable<Page<Project>> {
    return this.http.get<Page<Project>>(`${this.apiUrl}/pagination/${offset}/${pageSize}`);
  }

  // Pagination avec tri
  getProjectsWithPaginationAndSorting(offset: number, pageSize: number, field: string): Observable<Page<Project>> {
    return this.http.get<Page<Project>>(`${this.apiUrl}/paginationAndSorting/${offset}/${pageSize}/${field}`);
  }
}
