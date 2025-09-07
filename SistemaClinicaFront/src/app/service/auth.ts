import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from '../usuario';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth';
  private usuario: Usuario | null = null;

  constructor(private http: HttpClient) { }




  login(usuario: string, contrasena: string, roles: string) {
    return this.http.post<{ mensaje: string, nombre: string, roles: string }>(
      'http://localhost:8080/api/auth/login',
      { usuario, contrasena, roles },
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
    const roles = sessionStorage.getItem('roles');
    return roles === 'administrador';
  }


}