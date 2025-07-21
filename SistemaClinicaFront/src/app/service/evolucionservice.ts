import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Evolucion } from '../evolucion';

@Injectable({
  providedIn: 'root'
})
export class EvolucionService {

  private api : string = 'http://localhost:8080/api/evolucionpaciente';

  constructor(private http:HttpClient) { }

  createEvolucion(evolucion : Evolucion): Observable<Evolucion>{
    return this.http.post<Evolucion>(this.api,evolucion);
  }

  existenEvolucionPorCurp(curp: string): Observable<boolean> {
    return this.http.get<boolean>(`${this.api}/existen/${curp}`);
  }

}
