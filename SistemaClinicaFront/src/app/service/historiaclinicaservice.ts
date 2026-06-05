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
}
