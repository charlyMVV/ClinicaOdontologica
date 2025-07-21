import { Injectable } from '@angular/core';
import { Tutor } from '../tutor';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class Tutorservice {

  private api : string = 'http://localhost:8080/api/tutor';

  constructor(private http:HttpClient) { }

  createTutor(tutor :  Tutor) : Observable<Tutor>{
    return this.http.post<Tutor>(this.api,tutor);
  }

  existeTutorPorCurp(curp : string):Observable<boolean>{
    return this.http.get<boolean>(`${this.api}/existen/${curp}`);
  }
}
