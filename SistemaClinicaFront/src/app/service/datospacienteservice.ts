import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DatosPacientes } from '../datos-pacientes';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class Datospacienteservice {

  private api : string = 'http://localhost:8080/api/pacientes';
  
  constructor(private http:HttpClient){}


  createDatosPaciente(datospacientes : DatosPacientes):Observable<DatosPacientes>{
    return this.http.post<DatosPacientes>(this.api,datospacientes);
  }

  getDatosPaciente(): Observable<DatosPacientes[]>{
    return this.http.get<DatosPacientes[]>(this.api);
  }

  

}
