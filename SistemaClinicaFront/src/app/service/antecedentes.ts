import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AntecedentesHeredofamiliares } from '../antecedentesheredofamiliares';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class Antecedentes {

  private api : string = 'http://localhost:8080/api/antecedentes';


  constructor(private http:HttpClient) { }

  createAntecedentesHeredofamiliares(antecedentesheredofamiliares : AntecedentesHeredofamiliares):Observable<AntecedentesHeredofamiliares>{
    return this.http.post<AntecedentesHeredofamiliares>(this.api,antecedentesheredofamiliares);
  }

  
  existenAntecedentesPorCurp(curp: string): Observable<boolean> {
  return this.http.get<boolean>(`${this.api}/existen/${curp}`);
  }

}
