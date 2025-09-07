import { Component } from '@angular/core';
import { AuthService } from '../../service/auth';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';



@Component({
  selector: 'app-menu',
  standalone: false,
  templateUrl: './menu.html',
  styleUrl: './menu.css'
})
export class Menu {

  nombreUsuarioLogueado: string = '';
AuthService: any;


  constructor(public authService: AuthService, private router: Router) { }

  ngOnInit(): void {

    this.nombreUsuarioLogueado = sessionStorage.getItem('nombre') || 'Usuario';
  }

  AdministradorSumbit(){
    this.router.navigate(['administrador']);
  }

  MenuSubmitHc() {
    this.router.navigate(['/hc']);
  }
  
  MenuSubmitMisHc(){
    this.router.navigate(['/mishc']);
  }

  logout(): void {
    Swal.fire({
      title: '¿Cerrar sesión?',
      text: 'Tu sesión se cerrará y volverás al inicio de sesión.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí, cerrar sesión',
      cancelButtonText: 'Cancelar',
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6'
    }).then((result) => {
      if (result.isConfirmed) {
        this.authService.logout().subscribe({
          next: () => {
            sessionStorage.removeItem('usuario');
            this.router.navigate(['/']);
          },
          error: () => {
            sessionStorage.removeItem('usuario');
            this.router.navigate(['/']);
          }
        });
      }
    });
  }


}
