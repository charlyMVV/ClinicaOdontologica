import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from '../models/usuario';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth';
  private usuario: Usuario | null = null;

  constructor(private http: HttpClient) { }




  login(username: string, password: string) {
  return this.http.post<{ token: string }>(
    'http://localhost:8080/auth/login',
    { username, password },
    { withCredentials: true }
  );
}

  
  logout() {
    return this.http.get(`${this.apiUrl}/logout`, {
      responseType: 'text',
      withCredentials: true
    });
  }

  isAdmin(): boolean {
    const roles = sessionStorage.getItem('role');
    return roles === 'ADMIN';
  }


}