import { Component } from '@angular/core';
import { AuthService } from '../../service/auth';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { jwtDecode } from 'jwt-decode'; // ✅ Importación correcta

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  standalone: false
})
export class LoginComponent {
  username = '';
  password = '';

  constructor(private authService: AuthService, private router: Router) {}

  loginSubmit() {
    this.authService.login(this.username, this.password).subscribe({
      next: (resp) => {
        if (resp.token) {
          sessionStorage.setItem('token', resp.token);

          const decoded: any = jwtDecode(resp.token);

          sessionStorage.setItem('username', decoded.nombreUsuario);
          sessionStorage.setItem('role', decoded.role);

          this.router.navigate(['/menu']);
        }
      },
      error: (err) => {
        if (err.status === 401) {
          Swal.fire({
            icon: 'error',
            title: 'Credenciales incorrectas',
            text: 'El usuario o la contraseña no son válidos',
          });
        } else {
          Swal.fire({
            icon: 'error',
            title: 'Error del servidor',
            text: 'No se pudo procesar la solicitud',
          });
        }
      }
    });
  }
}