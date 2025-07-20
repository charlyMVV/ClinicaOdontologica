import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Signosvitales } from '../signosvitales';

@Injectable({
  providedIn: 'root'
})
export class signosvitalesService {

  private api : string = 'http://localhost:8080/api/signosvitales'

  constructor(private http:HttpClient) { }

  createSignosVitales(signosvitales: Signosvitales) : Observable<Signosvitales>{
    return this.http.post<Signosvitales>(this.api,signosvitales);
  }

  existenSignosVitalesPorCurp(curp: string): Observable<boolean> {
    return this.http.get<boolean>(`${this.api}/existen/${curp}`);
  }
}
