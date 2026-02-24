import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing-module'; 
import { App } from './app';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { UsuariosList } from './components/usuarios-administrador/usuarios-list';
import { UsuarioAdd } from './components/usuario-add/usuario-add';
import { LoginComponent } from './components/login/login.component';
import { Menu } from './components/menu/menu.component';
import { HistoriaClinica } from './components/historia-clinica/historia-clinica';
import { Mishc } from './components/mishc/mishc';





@NgModule({
  declarations: [
    App,
    UsuariosList,
    UsuarioAdd,
    LoginComponent,
    Menu,
    HistoriaClinica,
    Mishc,
    
    
    
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule      
  ],
  providers: [
    provideBrowserGlobalErrorListeners()
  ],
  bootstrap: [App]
})
export class AppModule { }
