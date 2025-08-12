import { Injectable } from '@angular/core';
import { environment } from '../../../environment/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

 interface Workout {
  workoutName: string;
  sets: number;
  reps: number;
  equipmentRequired: boolean;
  workoutVideo: string; 
 
}

interface ApiResponse<T> {
  timestamp: string;
  message: string;
  data: T;
  status: string;
}






@Injectable({
  providedIn: 'root'
})
export class UserService {

 private baseUrl: string = environment.baseUrl ; 
  constructor(private http: HttpClient) {}

  // getUserWorkouts(): Observable<any[]> {
  //   return this.http.get<any[]>(`${this.baseUrl}/workouts/getUserWorkouts`);
  // } 

 

// Fix the return type
getUserWorkouts(): Observable<ApiResponse<Workout[]>> {
  return this.http.get<ApiResponse<Workout[]>>(`${this.baseUrl}/workouts/getUserWorkouts`);
} 

markCompleted(id:number, formData: FormData ) :Observable<any>{
  return this.http.post<any>(`${this.baseUrl}/workouts/markCompleted/${id}`, formData);
} 

getAllprogress():Observable<ApiResponse<Workout[]>> {
  return this.http.get<ApiResponse<Workout[]>>(`${this.baseUrl}/workouts/getAllProgress`);
}  

// getUserNutrition(): Observable<any[]> {
//   return this.http.get<any[]>(`${this.baseUrl}/nutrition/user`);
// }  

getUserNutrition(goalType: string, bmiLevel: string): Observable<any[]> {
  const params = { goalType, bmiLevel };
  return this.http.get<any[]>(`${this.baseUrl}/nutrition/user`, { params });
}

getLoggedInUser(): Observable<any> {
  return this.http.get<any>(`${this.baseUrl}/users/getLoggedInUser`);
} 

getUserInfo(): Observable<any> {
  return this.http.get<any>(`${this.baseUrl}/users/getUserInfo`);
} 

}
