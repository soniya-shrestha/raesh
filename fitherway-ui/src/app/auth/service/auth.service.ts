// auth.service.ts
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { Auth } from '../models/auth';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<Auth> {
    return this.http.post<Auth>('/api/auth/login', { email, password }).pipe(
      tap((response: Auth) => {
        localStorage.setItem('access_token', response.token);
      })
    );
  }

  register(data: any): Observable<Auth> {
    return this.http.post<Auth>('/api/auth/register', data).pipe(
      tap((response: Auth) => {
        localStorage.setItem('access_token', response.token);
      })
    );
  }
}
