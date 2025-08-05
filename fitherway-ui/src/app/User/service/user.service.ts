import { Injectable } from '@angular/core';
import { environment } from '../../../environment/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

 private baseUrl: string = environment.baseUrl ; 
  constructor(private http: HttpClient) {}

  getUserWorkouts(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/workouts/getUserWorkouts`);
  }

}
