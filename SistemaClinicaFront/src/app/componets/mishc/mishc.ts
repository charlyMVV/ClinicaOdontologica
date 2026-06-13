import { Component } from '@angular/core';
import Swal from 'sweetalert2';
import { AuthService } from '../../service/auth';
import { Router } from '@angular/router';
import { HistoriaClinicaService } from '../../service/historiaclinicaservice';

@Component({
  selector: 'app-mishc',
  standalone: false,
  templateUrl: './mishc.html',
  styleUrl: './mishc.css'
})
export class Mishc {

  nombreUsuarioLogueado: string = '';
  datosEditados = false;

  historiasClinicas: any[] = [];
  filtroPacientes: string = '';
  cargandoPacientes = false;

  constructor(
    private authService: AuthService,
    private router: Router,
    private historiaClinicaService: HistoriaClinicaService
  ) { }

  ngOnInit(): void {
    this.nombreUsuarioLogueado = sessionStorage.getItem('nombre') || 'Usuario';
    this.cargarHistoriasClinicas();
  }

  get historiasFiltradas(): any[] {
    const filtro = this.filtroPacientes.trim().toLowerCase();

    if (!filtro) {
      return this.historiasClinicas;
    }

    return this.historiasClinicas.filter((hc) => {
      const nombre = hc.paciente?.nombrePaciente || '';
      const curp = hc.paciente?.curp || hc.paciente?.CURP || '';
      const estatus = hc.estatusHistoriaClinica?.descripcion || '';
      const tipo = hc.tipoHistoriaClinica?.descripcion || '';

      return `${nombre} ${curp} ${estatus} ${tipo}`.toLowerCase().includes(filtro);
    });
  }

  cargarHistoriasClinicas(): void {
    this.cargandoPacientes = true;

    const matricula = sessionStorage.getItem('matricula');

    if (!matricula) {
      this.cargandoPacientes = false;
      Swal.fire('Error', 'No se encontró la matrícula del usuario.', 'error');
      return;
    }

    this.historiaClinicaService.getHistoriasPorUsuario(matricula).subscribe({
      next: (historias) => {
        this.historiasClinicas = historias || [];
        this.cargandoPacientes = false;
      },
      error: (error) => {
        this.cargandoPacientes = false;
        console.error('Error al cargar historias clínicas:', error);
        Swal.fire('Error', 'No se pudieron cargar tus historias clínicas.', 'error');
      }
    });
  }

  continuarHistoria(hc: any): void {
    if (!hc?.idHistoriaClinica) {
      Swal.fire('Error', 'La historia clínica no tiene ID.', 'error');
      return;
    }

    this.datosEditados = false;
    this.router.navigate(['/hc', hc.idHistoriaClinica]);
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
            sessionStorage.clear();
            this.router.navigate(['/']);
          },
          error: () => {
            sessionStorage.clear();
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