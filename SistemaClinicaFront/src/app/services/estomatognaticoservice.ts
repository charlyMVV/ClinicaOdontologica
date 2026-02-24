import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Estomatognatico } from '../models/estomatognatico';
import { Observable } from 'rxjs';
import { Cabezacuello } from '../models/cabezacuello';

@Injectable({
  providedIn: 'root'
})
export class Estomatognaticoservice {
  private api : string = 'http://localhost:8080/api/estomatognatico'

  constructor(private http:HttpClient) { }

  createEstomatognatico(estomatognatico : Estomatognatico) : Observable<Estomatognatico>{
    return this.http.post<Estomatognatico>(this.api,estomatognatico);

  }

  existenEstomatognaticoCurp(curp: string): Observable<boolean> {
    return this.http.get<boolean>(`${this.api}/existen/${curp}`);
  }

}
