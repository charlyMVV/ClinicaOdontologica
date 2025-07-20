import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Nopatologicos } from '../nopatologicos';



@Injectable({
  providedIn: 'root'
})
export class NopatologicosService {

  private api: string = 'http://localhost:8080/api/antecedentesnopatologicos';


  constructor(private http: HttpClient) { }

  createAntecedentesnoPatologicos(nopatologicos: Nopatologicos): Observable<Nopatologicos> {
    return this.http.post<Nopatologicos>(this.api, nopatologicos);
  }

  existenAntecedentesPorCurp(curp: string): Observable<boolean> {
    return this.http.get<boolean>(`${this.api}/existen/${curp}`);
  }
}
