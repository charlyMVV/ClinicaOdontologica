import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cabezacuello } from '../cabezacuello';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CabezacuelloService {

  private api : string = 'http://localhost:8080/api/cabezacuello';

  constructor( private http:HttpClient) { }

  createExploracionCabezaCuello(cabezacuello: Cabezacuello) : Observable<Cabezacuello>{
    return this.http.post<Cabezacuello>(this.api,cabezacuello);
  }

  
  existenCabezaCuelloPorCurp(curp: string): Observable<boolean> {
    return this.http.get<boolean>(`${this.api}/existen/${curp}`);
  }
}
