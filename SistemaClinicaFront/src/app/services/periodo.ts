import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PeriodoService {
  private baseURL = 'http://localhost:8080/api/periodo';

  constructor(private http: HttpClient) {}

  getPeriodos(): Observable<any[]> {
    return this.http.get<any[]>(this.baseURL);
  }

  crearPeriodo(periodo: any): Observable<any> {
  return this.http.post<any>(this.baseURL, periodo);
}

updatePeriodo(periodo: any): Observable<any> {
  return this.http.put(this.baseURL, periodo); // <-- sin /id
}


}
