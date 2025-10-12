import { Component, OnInit } from '@angular/core';
import { Usuario } from '../../usuario';
import { UsuarioService } from '../../service/usuarioService';
import Swal from 'sweetalert2';
import { PeriodoService } from '../../service/periodo';
import { AuthService } from '../../service/auth';
import { Router } from '@angular/router';

@Component({
  selector: 'app-usuarios-list',
  standalone: false,
  templateUrl: './usuarios-list.html',
  styleUrls: ['./usuarios-list.css']
})
export class UsuariosList implements OnInit {

  datosEditados = true; 

  periodos: any[] = [];
  periodoSeleccionado: any = null;
  modoEdicionPeriodo: boolean = false;

  filtroEstudiantes: string = '';
  matricula: string = '';
  nombreUsuario: string = '';
  username: string = '';
  password: string = '';
  periodo: string = '';

  modoEdicion: boolean = false;
  usuarios: Usuario[] = [];

  nombreUsuarioLogueado: string = ''; // 👤 Nombre del usuario logueado

  constructor(
    private usuarioService: UsuarioService,
    private periodoService: PeriodoService,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.listUsuarios();
    this.obtenerPeriodos();
    this.nombreUsuarioLogueado = sessionStorage.getItem('nombre') || 'username';

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


  matriculaYaExiste(): boolean {
    return this.usuarios.some(u => u.matricula === this.matricula);
  }

  listUsuarios() {
    this.usuarioService.getUsuarioList().subscribe(
      data => {
        this.usuarios = data;
      },
      error => {
        console.error('Error al obtener usuarios:', error);
      }
    );
  }

  addUsuario() {
    const existe = this.usuarios.some(u => u.matricula === this.matricula);
    if (existe) {
      Swal.fire({
        icon: 'error',
        title: 'Estudiante ya registrado',
        text: `Ya existe un estudiante con la matrícula ${this.matricula}`,
        confirmButtonText: 'Entendido'
      });
      return;
    }

    if (!this.matricula || !this.nombreUsuario || !this.username || !this.username || !this.periodo) {
      Swal.fire({
        icon: 'warning',
        title: 'Campos incompletos',
        text: 'Por favor, completa todos los campos antes de registrar.',
        confirmButtonText: 'Entendido'
      });
      return;
    }

    Swal.fire({
      title: '¿Deseas registrar este estudiante?',
      text: `Se agregará el estudiante con matrícula ${this.matricula}.`,
      icon: 'question',
      showCancelButton: true,
      confirmButtonColor: '#198754',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, registrar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        const nuevoUsuario = new Usuario(
          this.nombreUsuario,
          this.matricula,
          this.username,
          this.password,
          this.periodo
        );

        this.usuarioService.createUsuario(nuevoUsuario).subscribe(
          res => {
            this.listUsuarios();
            this.resetForm();
            Swal.fire({
              icon: 'success',
              title: 'Estudiante agregado',
              text: 'El estudiante se registró correctamente',
              timer: 2000,
              showConfirmButton: false
            });
          },
          error => {
            console.error('Error al registrar usuario:', error);
            Swal.fire({
              icon: 'error',
              title: 'Error al registrar',
              text: 'Hubo un problema al registrar el estudiante',
              confirmButtonText: 'Aceptar'
            });
          }
        );
      }
    });
  }

  usuariosFiltrados(): Usuario[] {
    const filtro = this.filtroEstudiantes.toLowerCase();
    return this.usuarios.filter(us =>
      us.matricula.toLowerCase().includes(filtro) ||
      us.nombreUsuario.toLowerCase().includes(filtro) ||
      us.username.toLowerCase().includes(filtro) ||
      us.password.toLowerCase().includes(filtro) 
    );
  }

  editarUsuario(usuario: Usuario) {
    this.modoEdicion = true;
    this.matricula = usuario.matricula;
    this.nombreUsuario = usuario.nombreUsuario;
    this.username = usuario.username;
    this.password = usuario.password;
  }

  resetForm(): void {
    this.nombreUsuario = '';
    this.matricula = '';
    this.username = '';
    this.password = '';
    this.periodo = '';
    this.modoEdicion = false;
  }

  actualizarUsuario(): void {
    if (!this.matricula || !this.nombreUsuario || !this.username || !this.password || !this.periodo) {
      Swal.fire({
        icon: 'warning',
        title: 'Campos incompletos',
        text: 'Por favor, completa todos los campos antes de actualizar.',
        confirmButtonText: 'Entendido'
      });
      return;
    }

    Swal.fire({
      title: '¿Estás seguro?',
      text: '¿Deseas actualizar los datos de este estudiante?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, actualizar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        const usuarioActualizado = new Usuario(
          this.nombreUsuario,
          this.matricula,
          this.username,
          this.password,
          this.periodo
        );

        this.usuarioService.actualizarUsuario(usuarioActualizado).subscribe(
          res => {
            this.listUsuarios();
            this.resetForm();
            Swal.fire({
              icon: 'success',
              title: 'Estudiante actualizado',
              text: 'El estudiante fue actualizado correctamente',
              timer: 2000,
              showConfirmButton: false
            });
          },
          error => {
            console.error('Error al actualizar usuario:', error);
            Swal.fire({
              icon: 'error',
              title: 'Error al actualizar',
              text: 'Hubo un problema al actualizar el estudiante',
              confirmButtonText: 'Aceptar'
            });
          }
        );
      }
    });
  }

  obtenerPeriodos(): void {
    this.periodoService.getPeriodos().subscribe(
      data => {
        this.periodos = data;
        
      },
      error => {
        console.error('Error al cargar periodos:', error);
      }
    );
  }

  guardarPeriodo() {
    if (!this.periodos) {
      Swal.fire('⚠️ Campo vacío', 'Debes ingresar un período.', 'warning');
      return;
    }

    const periodo = { periodo: this.periodo };

    this.periodoService.crearPeriodo(periodo).subscribe(
      res => {
        Swal.fire('✅ Guardado', 'El período se registró correctamente.', 'success');
        this.periodo = '';
        this.obtenerPeriodos();
      },
      error => {
        Swal.fire('❌ Error', 'No se pudo registrar el período.', 'error');
        console.error(error);
      }
    );
  }

  editarPeriodo(p: any) {
    this.periodoSeleccionado = p;
    this.periodo = p.periodo;
    this.modoEdicionPeriodo = true;
  }

  cancelarEdicionPeriodo() {
    this.periodo = '';
    this.periodoSeleccionado = null;
    this.modoEdicionPeriodo = false;
  }

  actualizarPeriodo() {
    if (!this.periodo.trim()) {
      Swal.fire('Campo vacío', 'Ingresa un nombre de período.', 'warning');
      return;
    }

    Swal.fire({
      title: '¿Actualizar período?',
      text: `Se actualizará a: "${this.periodo}"`,
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: 'Sí, actualizar',
      cancelButtonText: 'Cancelar'
    }).then(result => {
      if (result.isConfirmed && this.periodoSeleccionado) {
        const periodoActualizado = {
          idPeriodo: this.periodoSeleccionado.idPeriodo,
          periodo: this.periodo
        };

        this.periodoService.updatePeriodo(periodoActualizado).subscribe(
          res => {
            Swal.fire('Actualizado', 'El período fue actualizado correctamente.', 'success');
            this.obtenerPeriodos();
            this.cancelarEdicionPeriodo();
          },
          err => {
            console.error(err);
            Swal.fire('Error', 'No se pudo actualizar el período.', 'error');
          }
        );
      }
    });
  }

  
  abrirEstudiantes(): void {
    this.resetForm();
  }

}
