import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StorageService } from '../../../auth/services/storage/storage.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class AdminService {

  constructor(private http: HttpClient) { }

  getUsers(): Observable<any> {
    return this.http.get('http://localhost:8080/api/admin/users', httpOptions);
  }

  postTask(taskDto: any): Observable<any> {
    return this.http.post('http://localhost:8080/api/admin/task', taskDto, httpOptions);
  }

  searchTasks(title: string): Observable<any> {
    return this.http.get('http://localhost:8080/api/admin/tasks/search/' + title, httpOptions);
  }

  deleteTask(id: number): Observable<any> {
    return this.http.delete('http://localhost:8080/api/admin/task/' + id, httpOptions);
  }

  updateTask(id: number, taskDTO: any): Observable<any> {
    return this.http.put('http://localhost:8080/api/admin/task/' + id, taskDTO, httpOptions);
  }

  getTaskById(id: number): Observable<any> {
    return this.http.get('http://localhost:8080/api/admin/task/' + id, httpOptions);
  }

  getCommentsById(id: number): Observable<any> {
    return this.http.get('http://localhost:8080/api/admin/task/' + id + '/comments', httpOptions);
  }

  createComment(taskId: number, content: string): Observable<any> {
    const params_ = {
      taskId: taskId,
      postedBy:StorageService.getUserId() ?? ''
    };
    return this.http.post('http://localhost:8080/api/admin/task/comment', content, {
      params: params_,
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${StorageService.getToken()}`
      }),
      withCredentials: true
    });
  }


  getTasks(): Observable<any> {
    console.log('Current Token:', StorageService.getToken());
    console.log('httpOptions:', httpOptions);
    return this.http.get('http://localhost:8080/api/admin/tasks', httpOptions);
  }


}
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': `Bearer ${StorageService.getToken()}`
  }),
  withCredentials: true
};
