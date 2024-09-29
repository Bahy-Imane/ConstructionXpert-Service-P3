import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs";
import {Task} from "../model/task.model";
import {Page} from "../model/page.mpdel";

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private apiUrl = 'http://localhost:8888/TASKS-MANAGER/api/tasks'; // replace with your API URL

  constructor(private http: HttpClient) {}

  getAllTasks(): Observable<Task[]> {
    return this.http.get<Task[]>(`${this.apiUrl}/all`);
  }

  getTasksByProjectId(projectId: number): Observable<Task[]> {
    return this.http.get<Task[]>(`${this.apiUrl}/project?projectId=${projectId}`);
  }

  getTaskById(taskId: number): Observable<Task> {
    return this.http.get<Task>(`${this.apiUrl}/${taskId}`);
  }

  createTask(task: Task): Observable<Task> {
    return this.http.post<Task>(`${this.apiUrl}/create`, task);
  }

  updateTask(taskId: number, task: Task): Observable<Task> {
    return this.http.put<Task>(`${this.apiUrl}/update?taskId=${taskId}`, task);
  }

  deleteTask(taskId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete?taskId=${taskId}`);
  }

  getTasksWithSorting(field: string): Observable<Task[]> {
    return this.http.get<Task[]>(`${this.apiUrl}/sorting/${field}`);
  }

  getTasksWithPagination(offset: number, pageSize: number): Observable<Page<Task>> {
    return this.http.get<Page<Task>>(`${this.apiUrl}/pagination/${offset}/${pageSize}`);
  }

  getTasksWithPaginationAndSorting(offset: number, pageSize: number, field: string): Observable<Page<Task>> {
    return this.http.get<Page<Task>>(`${this.apiUrl}/paginationAndSorting/${offset}/${pageSize}/${field}`);
  }
}
