import Swal from 'sweetalert2';
import { forkJoin, Observable } from 'rxjs';

interface GuardarListaOptions {
  lista: any[];
  guardar: (elemento: any) => Observable<any>;
  tituloExito: string;
  textoExito: string;
  textoError: string;
}

export function guardarListaEnBloque(options: GuardarListaOptions): void {
  const peticiones = options.lista.map((elemento) => options.guardar(elemento));

  forkJoin(peticiones).subscribe({
    next: () => {
      Swal.fire({
        icon: 'success',
        title: options.tituloExito,
        text: options.textoExito,
        timer: 2000,
        showConfirmButton: false
      });
    },
    error: (err) => {
      console.error(err);
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: options.textoError
      });
    }
  });
}
