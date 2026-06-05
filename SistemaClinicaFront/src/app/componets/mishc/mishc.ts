import { Component } from '@angular/core';
import Swal from 'sweetalert2';
import { AuthService } from '../../service/auth';
import { Router } from '@angular/router';
import { DatosPacientes } from '../../datos-pacientes';
import { Datospacienteservice } from '../../service/datospacienteservice';

@Component({
  selector: 'app-mishc',
  standalone: false,
  templateUrl: './mishc.html',
  styleUrl: './mishc.css'
})
export class Mishc {

  nombreUsuarioLogueado: string = '';
  datosEditados = false;
  pacientes: DatosPacientes[] = [];
  filtroPacientes: string = '';
  cargandoPacientes = false;

  constructor(
    private authService: AuthService,
    private router: Router,
    private datosPacienteService: Datospacienteservice
  ) { }

  ngOnInit(): void {

    this.nombreUsuarioLogueado = sessionStorage.getItem('nombre') || 'Usuario';
    this.cargarPacientes();
  }

  get pacientesFiltrados(): DatosPacientes[] {
    const filtro = this.filtroPacientes.trim().toLowerCase();

    if (!filtro) {
      return this.pacientes;
    }

    return this.pacientes.filter((paciente) => {
      const nombre = paciente.nombrePaciente || '';
      const curp = paciente.curp || '';

      return `${nombre} ${curp}`.toLowerCase().includes(filtro);
    });
  }

  cargarPacientes(): void {
    this.cargandoPacientes = true;

    this.datosPacienteService.getDatosPaciente().subscribe({
      next: (pacientes) => {
        this.pacientes = pacientes || [];
        this.cargandoPacientes = false;
      },
      error: (error) => {
        this.cargandoPacientes = false;
        console.error('Error al cargar pacientes:', error);
        Swal.fire({
          title: 'Error',
          text: 'No se pudo cargar la lista de pacientes.',
          icon: 'error',
          confirmButtonText: 'OK'
        });
      }
    });
  }

  continuarHistoria(paciente: DatosPacientes): void {
    if (!paciente.curp) {
      Swal.fire({
        title: 'CURP faltante',
        text: 'Este paciente no tiene CURP registrada.',
        icon: 'warning',
        confirmButtonText: 'OK'
      });
      return;
    }

    this.datosEditados = false;
    this.router.navigate(['/hc', paciente.curp]);
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

  volverAlMenu() {
    this.router.navigate(['menu']);
  }

  puedeSalir(): Promise<boolean> {
    if (!this.datosEditados) {
      return Promise.resolve(true);
    }

    return Swal.fire({
      title: '¿Estás seguro?',
      text: 'Si regresas al menú, podrías perder tus avances actuales.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6',
      confirmButtonText: 'Sí, volver',
      cancelButtonText: 'Cancelar'
    }).then((result) => result.isConfirmed);
  }

}
