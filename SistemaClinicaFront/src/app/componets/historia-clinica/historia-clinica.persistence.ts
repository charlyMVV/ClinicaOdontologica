import Swal from 'sweetalert2';
import { Observable } from 'rxjs';

type Texto = string;

interface GuardarSeccionOptions {
  existe: Observable<boolean>;
  crear: Observable<any>;
  actualizar: Observable<any>;
  tituloExitoCrear: Texto;
  textoExitoCrear: Texto;
  tituloExitoActualizar: Texto;
  textoExitoActualizar: Texto;
  textoErrorCreacion: Texto;
  textoErrorActualizacion: Texto;
  textoErrorVerificacion: Texto;
}

export function guardarSeccionPorExistencia(options: GuardarSeccionOptions): void {
  options.existe.subscribe({
    next: (existe) => {
      const operacion = existe ? options.actualizar : options.crear;
      const tituloExito = existe ? options.tituloExitoActualizar : options.tituloExitoCrear;
      const textoExito = existe ? options.textoExitoActualizar : options.textoExitoCrear;
      const textoError = existe ? options.textoErrorActualizacion : options.textoErrorCreacion;

      operacion.subscribe({
        next: () => {
          Swal.fire({
            icon: 'success',
            title: tituloExito,
            text: textoExito,
            timer: 2000,
            showConfirmButton: false
          });
        },
        error: (err) => {
          console.error(err);
          Swal.fire({
            icon: 'error',
            title: 'Error',
            text: textoError
          });
        }
      });
    },
    error: (err) => {
      console.error(err);
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: options.textoErrorVerificacion
      });
    }
  });
}
