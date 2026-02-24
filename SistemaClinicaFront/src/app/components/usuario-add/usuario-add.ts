import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-usuario-add',
  standalone: false,
  templateUrl: './usuario-add.html',
  styleUrl: './usuario-add.css'
})
export class UsuarioAdd implements OnInit{

  matricula : String = '';
  nombreUsuario : String = '';
  usuario : String = '';
  contrasena : String = '';
  periodo : String = ''

  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

}
