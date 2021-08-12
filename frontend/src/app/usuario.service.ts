import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import 'rxjs';


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
      .then(response => response.content);
  }

  salvar(usuario: any): Promise<any> {

    return this.http.post<any>(`${this.apiUrl}`, usuario)
      .toPromise()
      .then(response => response.content);
  }
}
