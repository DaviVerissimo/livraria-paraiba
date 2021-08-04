import { UsuarioService } from './../usuario.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.css']
})
export class ClienteComponent implements OnInit {

  usuarios = [];

  constructor(private usuarioService: UsuarioService) { }
  ngOnInit(): void {
    this.listAll();
  }
  listAll(){
    this.usuarioService.listAll().then(usuarios =>
      this.usuarios = usuarios);

    }
}
