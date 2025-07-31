// auth.service.ts
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { Auth } from '../models/auth';
import { environment } from '../../../environment/environment';

@Injectable({
  providedIn: 'root',
})
export class AuthService { 
  private baseUrl: string = environment.baseUrl; 
  constructor(private http: HttpClient) {} 



  login(loginDetails: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/auth/login`, loginDetails,{
      headers: { 'Content-Type': 'application/json' } });
  }


  registerStudent(formData: FormData): Observable<any> {
    return this.http.post(`${this.baseUrl}/users/register`, formData);
  } 

   otp(data: { otp: string, email: string }): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/users/validate`, data);
  }

}
