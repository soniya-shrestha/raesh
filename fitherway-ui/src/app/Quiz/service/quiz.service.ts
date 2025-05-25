import { Injectable } from '@angular/core';
import { environment } from '../../../environment/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class QuizService {

 private baseUrl: string = environment.baseUrl+'/quiz'; 
  constructor(private http: HttpClient) {}

  getGoals(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/goals`);
  }

  getExperience(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/experience`);
  }

  getAges(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/ages`);
  }

  getShapes(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/shapes`);
  }

  getDreams(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/dreams`);
  }

  getExercises(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/exercises`);
  }

  getBestShapes(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/bestshapes`);
  }

  getPreferDays(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/preferdays`);
  }

  getPreferTimes(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/prefertimes`);
  }

  getPreferDayTimes(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/preferdaytimes`);
  }

  getEquipments(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/equipments`);
  }

  submitQuizResponse(data: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/submit`, data);
  }

  calculateBMI(data: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/calculate-bmi`, data);
  }

}
