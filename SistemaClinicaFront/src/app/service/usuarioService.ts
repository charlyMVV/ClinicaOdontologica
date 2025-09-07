import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from '../usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private apiPeriodo = 'http://localhost:8080/api/periodo';
  private api: string = "http://localhost:8080/api/usuarios";


  constructor(private http: HttpClient) { }

  getUsuarioList(): Observable<Usuario[]> {

    return this.http.get<Usuario[]>(this.api);
  }

  createUsuario(usuario: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(this.api, usuario);
  }

  actualizarUsuario(usuario: Usuario): Observable<Usuario> {
    return this.http.put<Usuario>(`${this.api}`, usuario);
  }

  updatePeriodo(periodo: any): Observable<any> {
    return this.http.put(`${this.apiPeriodo}/${periodo.idPeriodo}`, periodo);
  }





}
