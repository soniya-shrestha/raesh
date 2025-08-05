import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environment/environment';
import { NutritionPlan } from '../model/nutrition-plan';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private baseUrl: string = environment.baseUrl;

  constructor(private http: HttpClient) {}

  getUsers() { return this.http.get<any>(`${this.baseUrl}/users`); }
  toggleUserStatus(id: number, active: boolean) {
    return this.http.put(`${this.baseUrl}/users/${id}/status`, null, { params: { active } });
  } 

  addWorkouts(formData: FormData): Observable<any[]> {
      return this.http.post<any[]>(`${this.baseUrl}/workouts/create` ,formData);
  } 

  getAllWorkouts():Observable<any> {
      return this.http.get<any>(`${this.baseUrl}/workouts/getAll`);
  } 

  deleteWorkout(id: number): Observable<any> {
      return this.http.delete<any>(`${this.baseUrl}/workouts/${id}`);
  }  

  getWorkoutById(id: number){} 

  updateWorkout(id: number, formData: FormData):Observable<any> {
      return this.http.put<any>(`${this.baseUrl}/workouts/${id}`, formData);
  }   


   getAll(): Observable<NutritionPlan[]> {
    return this.http.get<NutritionPlan[]>(`${this.baseUrl}/nutrition/getAll`);
  }

  getById(id: number): Observable<NutritionPlan> {
    return this.http.get<NutritionPlan>(`${this.baseUrl}/nutrition/getById/${id}`);
  }

  create(formData: FormData): Observable<NutritionPlan> {
    return this.http.post<NutritionPlan>(`${this.baseUrl}/nutrition/create`, formData);
  }

  update(id: number, formData: FormData): Observable<NutritionPlan> {
    return this.http.put<NutritionPlan>(`${this.baseUrl}/nutrition/${id}`, formData);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/nutrition/${id}`);
  } 

  


}
