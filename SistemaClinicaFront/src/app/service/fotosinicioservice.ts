import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Fotosinicio } from '../fotosinicio';

@Injectable({
  providedIn: 'root'
})
export class Fotosinicioservice {

  private api = 'http://localhost:8080/api/fotosinicio';


  constructor(private http: HttpClient) { }

  guardarFoto(fotosinico: Fotosinicio): Observable<Fotosinicio> {
    return this.http.post<Fotosinicio>(this.api, fotosinico);
  }

  guardarMultiplesFotos(fotos: Fotosinicio[]): Observable<any> {
  return this.http.post(`${this.api}/multiples`, fotos);
}



  obtenerFotos(): Observable<Fotosinicio[]> {
    return this.http.get<Fotosinicio[]>(this.api);
  }
}
