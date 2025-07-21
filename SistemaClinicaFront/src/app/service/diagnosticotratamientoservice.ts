import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Diagnosticotratamiento } from '../diagnosticotratamiento';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class Diagnosticotratamientoservice {

  private api : string = 'http://localhost:8080/api/diagnosticotratamiento';

  constructor(private http:HttpClient) { }

  createDiagnosticoTratamiento(diagnosticotratamiento : Diagnosticotratamiento) : Observable<Diagnosticotratamiento>{
    return this.http.post<Diagnosticotratamiento>(this.api,diagnosticotratamiento);
    
  }

  existenDiagnosticoTratamientoCurp(curp: string): Observable<boolean> {
    return this.http.get<boolean>(`${this.api}/existen/${curp}`);
  }
}
