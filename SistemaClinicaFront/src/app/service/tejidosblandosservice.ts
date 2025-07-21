import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Tejidosblandos } from '../tejidosblandos';

@Injectable({
  providedIn: 'root'
})
export class TejidosblandosService {

  private api : string = 'http://localhost:8080/api/tejidosblandos';

  constructor(private http:HttpClient) { }

  createTejidosBlandos(tejidosblandos: Tejidosblandos) : Observable<Tejidosblandos>{
    return this.http.post<Tejidosblandos>(this.api,tejidosblandos)
  }

  existenTejidosBlandosPorCurp(curp: string): Observable<boolean> {
    return this.http.get<boolean>(`${this.api}/existen/${curp}`);
}
}
