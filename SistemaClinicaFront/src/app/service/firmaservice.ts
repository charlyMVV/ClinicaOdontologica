import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Firma } from '../firma';

@Injectable({
  providedIn: 'root'
})
export class FirmaService {

  private apiUrl = 'http://localhost:8080/api/firmas';

  constructor(private http: HttpClient) {}

  // Guardar una firma
  guardarFirma(firma: Firma): Observable<Firma> {
    return this.http.post<Firma>(this.apiUrl, firma);
  }

  // Obtener todas las firmas
  obtenerFirmas(): Observable<Firma[]> {
    return this.http.get<Firma[]>(this.apiUrl);
  }

  // Obtener firma por ID
  obtenerFirmaPorId(id: number): Observable<Firma> {
    return this.http.get<Firma>(`${this.apiUrl}/${id}`);
  }

  // Eliminar firma por ID
  eliminarFirma(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
