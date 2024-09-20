import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private apiUrl = 'http://localhost:8082/api/tasks'; // replace with your API URL

  constructor(private http: HttpClient) { }

  getAllTasks(): Observable<any> {
    return this.http.get(this.apiUrl + '/all');
  }

  getTasksByProjectId(projectId: number): Observable<any> {
    return this.http.get(this.apiUrl + '/project', { params: { projectId } });
  }

  getTaskById(taskId: number): Observable<any> {
    return this.http.get(this.apiUrl + '/' + taskId);
  }

  createTask(task: any): Observable<any> {
    return this.http.post(this.apiUrl + '/create', task);
  }

  updateTask(taskId: number, task: any): Observable<any> {
    return this.http.put(this.apiUrl + '/update', task, { params: { taskId } });
  }

  deleteTask(taskId: number): Observable<any> {
    return this.http.delete(this.apiUrl + '/delete', { params: { taskId } });
  }

}
