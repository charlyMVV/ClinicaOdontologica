import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DatosPacientes } from '../datos-pacientes';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class Datospacienteservice {

  private api: string = 'http://localhost:8080/api/pacientes';

  constructor(private http: HttpClient) { }

    createDatosPaciente(payload: any): Observable<any> {
      return this.http.post<any>(this.api, payload);
    }

  getDatosPaciente(): Observable<DatosPacientes[]> {
    return this.http.get<DatosPacientes[]>(this.api);
  }

  existePacientePorCurp(curp: string): Observable<boolean> {
    return this.http.get<boolean>(`${this.api}/existen/${curp}`);
  }

  updateDatosPaciente(curp: string, datosPaciente: DatosPacientes): Observable<DatosPacientes> {
    return this.http.put<DatosPacientes>(`${this.api}/curp/${curp}`, datosPaciente);
  }


}
