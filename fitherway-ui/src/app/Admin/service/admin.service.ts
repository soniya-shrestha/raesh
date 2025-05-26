import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private baseUrl = '/api/admin';

  constructor(private http: HttpClient) {}

  getUsers() { return this.http.get<any>(`${this.baseUrl}/users`); }
  toggleUserStatus(id: number, active: boolean) {
    return this.http.put(`${this.baseUrl}/users/${id}/status`, null, { params: { active } });
  } 

  getWorkouts(){}
}
