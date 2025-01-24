import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { StorageService } from '../../../auth/services/storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http: HttpClient) { }

  getTasksByUserId(): Observable<any> {
    const userId = StorageService.getUserId();
    if (!userId) {
      console.error('User ID is not available');
      return throwError(() => new Error('User ID is not set.'));
    }
    return this.http.get(`http://localhost:8080/api/employee/tasks/${userId}`, httpOptions);
  }

  getTaskById(id: number): Observable<any> {
    return this.http.get('http://localhost:8080/api/employee/task/' + id, httpOptions);
  }

  getCommentsById(id: number): Observable<any> {
    return this.http.get('http://localhost:8080/api/employee/task/' + id + '/comments', httpOptions);
  }

  createComment(taskId: number, content: string): Observable<any> {
    const params_ = {
      taskId: taskId,
      postedBy:StorageService.getUserId() ?? ''
    };
    return this.http.post('http://localhost:8080/api/employee/task/comment', content, {
      params: params_,
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${StorageService.getToken()}`
      }),
      withCredentials: true
    });
  }

  updateTask(id: number, status:string): Observable<any> {
    return this.http.get('http://localhost:8080/api/employee/task/' + id + '/' + status, httpOptions);
  }
}

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': `Bearer ${StorageService.getToken()}`
  }),
  withCredentials: true
};