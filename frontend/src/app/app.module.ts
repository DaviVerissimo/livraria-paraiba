import { UsuarioService } from './usuario.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { ButtonModule } from 'primeng/button';
import {SelectButtonModule} from 'primeng/selectbutton';
import { InputMaskModule } from 'primeng/inputmask';
import {TableModule} from 'primeng/table';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LancamentoCadastroComponent } from './lancamento-cadastro/lancamento-cadastro.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FormsModule } from '@angular/forms';
import { FooterComponent } from './footer/footer.component';
import { ClienteComponent } from './cliente/cliente.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    LancamentoCadastroComponent,
    NavbarComponent,
    FooterComponent,
    ClienteComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    InputTextModule,
    PasswordModule,
    FormsModule,
    ButtonModule,
    SelectButtonModule,
    InputMaskModule,
    TableModule,
    HttpClientModule
  ],
  providers: [UsuarioService],
  bootstrap: [AppComponent]
})
export class AppModule { }
