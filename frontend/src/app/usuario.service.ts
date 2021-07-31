import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  readonly apiUrl: string;

  constructor(private http: HttpClient) {
    this.apiUrl = 'http://localhost:8080/cliente';
  }
  listAll(): Promise<any> {
    return this.http.get<any>(`${this.apiUrl}`)
      .toPromise()
      .then(response => response.json().content);
  }


}
