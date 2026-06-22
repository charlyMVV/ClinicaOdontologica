import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HistoriaClinicaService {

  private api = 'http://localhost:8080/api/historia-clinica';

  constructor(private http: HttpClient) { }

  getHistoriaClinicaPorCurp(curp: string): Observable<any> {
    return this.http.get<any>(`${this.api}/${curp}`);
  }

  getHistoriasPorEstatus(estatus: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.api}/estatus/${estatus}`);
  }

  getTodasHistorias(): Observable<any[]> {
    return this.http.get<any[]>(`${this.api}/todas`);
  }

  getHistoriasPorUsuario(matricula: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.api}/usuario/${matricula}`);
  }
  getHistoriaClinicaPorId(idHistoriaClinica: number): Observable<any> {
    return this.http.get<any>(`${this.api}/id/${idHistoriaClinica}`);
  }

  enviarRevision(idHistoriaClinica: number): Observable<any> {
    return this.http.put<any>(`${this.api}/${idHistoriaClinica}/enviar-revision`, {});
  }

  aprobar(idHistoriaClinica: number): Observable<any> {
    return this.http.put<any>(`${this.api}/${idHistoriaClinica}/aprobar`, {});
  }

  rechazar(idHistoriaClinica: number): Observable<any> {
    return this.http.put<any>(`${this.api}/${idHistoriaClinica}/rechazar`, {});
  }

  crearNuevaHistoriaClinica(payload: any): Observable<any> {
    return this.http.post<any>(`${this.api}/nueva`, payload);
  }

  obtenerPdf(idHistoriaClinica: number): Observable<Blob> { 
    return this.http.get(`${this.api}/${idHistoriaClinica}/pdf`, {responseType: 'blob'});
  }
}
