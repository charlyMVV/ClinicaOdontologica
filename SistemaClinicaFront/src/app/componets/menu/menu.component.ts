import { Component } from '@angular/core';
import { AuthService } from '../../service/auth';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { HistoriaClinicaService } from '../../service/historiaclinicaservice';



@Component({
  selector: 'app-menu',
  standalone: false,
  templateUrl: './menu.html',
  styleUrl: './menu.css'
})
export class Menu {

  nombreUsuarioLogueado: string = '';
AuthService: any;

clinicaSeleccionada: any = null;


  constructor(public authService: AuthService, private router: Router,private historiaClinicaService: HistoriaClinicaService) { }

  ngOnInit(): void {
    this.nombreUsuarioLogueado = sessionStorage.getItem('nombre') || 'Usuario';

    const clinicaGuardada = sessionStorage.getItem('clinicaSeleccionada');

    if (clinicaGuardada) {
      this.clinicaSeleccionada = JSON.parse(clinicaGuardada);
    } else {
      this.seleccionarClinica();
    }
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
            sessionStorage.removeItem('clinicaSeleccionada');
            sessionStorage.removeItem('idClinica');
            sessionStorage.removeItem('nombreClinica');
            sessionStorage.removeItem('responsableClinica');
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

  seleccionarClinica(): void {
  this.historiaClinicaService.listarClinicas().subscribe({
    next: async (clinicas) => {

      if (!clinicas || clinicas.length === 0) {
        Swal.fire({
          icon: 'warning',
          title: 'Sin clínicas registradas',
          text: 'Primero debe registrar al menos una clínica desde el panel de administración.'
        });
        return;
      }

      const opciones: any = {};

      clinicas.forEach((clinica) => {
        opciones[clinica.idClinica] = clinica.nombreClinica;
      });

      const { value: idClinica } = await Swal.fire({
        title: 'Selecciona la clínica',
        input: 'select',
        inputOptions: opciones,
        inputPlaceholder: 'Seleccione una clínica',
        allowOutsideClick: false,
        allowEscapeKey: false,
        confirmButtonText: 'Continuar',
        inputValidator: (value) => {
          if (!value) {
            return 'Debe seleccionar una clínica.';
          }
          return null;
        }
      });

      if (idClinica) {
        const clinica = clinicas.find(c => c.idClinica == idClinica);

        this.clinicaSeleccionada = clinica;

        sessionStorage.setItem('idClinica', String(clinica.idClinica));
        sessionStorage.setItem('nombreClinica', clinica.nombreClinica);
        sessionStorage.setItem('responsableClinica', clinica.responsableClinica);
        sessionStorage.setItem('clinicaSeleccionada', JSON.stringify(clinica));
      }
    },
    error: (err) => {
      console.error(err);

      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'No se pudieron cargar las clínicas.'
      });
    }
  });
}


}
