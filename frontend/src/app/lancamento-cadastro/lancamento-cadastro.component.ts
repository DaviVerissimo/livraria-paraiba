import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-lancamento-cadastro',
  templateUrl: './lancamento-cadastro.component.html',
  styleUrls: ['./lancamento-cadastro.component.css']
})
export class LancamentoCadastroComponent implements OnInit {

  tipos = [
    {label: 'Pessoa Física', value: 'Pessoa Física'},
    {label: 'Pessoa Jurídica', value:'Pessoa Jurídica'}
  ];

  salvar(form: NgForm){
    console.log(form)
  }

  ngOnInit(): void {
  }

}
