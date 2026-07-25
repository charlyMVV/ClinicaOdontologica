import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { AuthService } from '../../service/auth';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { DatosPacientes } from '../../datos-pacientes';
import { Datospacienteservice } from '../../service/datospacienteservice';
import { Antecedentes } from '../../service/antecedentes';
import { Nopatologicos } from '../../nopatologicos';
import { NopatologicosService } from '../../service/nopatologicosService';
import { signosvitalesService } from '../../service/signosvitalesService';
import { CabezacuelloService } from '../../service/cabezacuelloservice';
import { Cabezacuello } from '../../cabezacuello';
import { Estomatognaticoservice } from '../../service/estomatognaticoservice';
import { Estomatognatico } from '../../estomatognatico';
import { TejidosblandosService } from '../../service/tejidosblandosservice';
import { Tejidosblandos } from '../../tejidosblandos';
import { Tutorservice } from '../../service/tutorservice';
import { Tutor } from '../../tutor';
import { Diagnosticotratamientoservice } from '../../service/diagnosticotratamientoservice';
import { Diagnosticotratamiento } from '../../diagnosticotratamiento';
import { EvolucionService } from '../../service/evolucionservice';
import { Evolucion } from '../../evolucion';
import { ViewChild, ElementRef, AfterViewInit } from '@angular/core';
import SignaturePad from 'signature_pad';
import { HttpClient } from '@angular/common/http';
import { FirmaService } from '../../service/firmaservice';
import { Firma } from '../../firma';
import { Fotosinicioservice } from '../../service/fotosinicioservice';
import { Fotosinicio } from '../../fotosinicio';
import { HistoriaClinicaService } from '../../service/historiaclinicaservice';
import {
  aplicarAntecedentes as aplicarAntecedentesHistoria,
  aplicarCampos as aplicarCamposHistoria,
  aplicarDatosPaciente as aplicarDatosPacienteHistoria,
  aplicarFirma as aplicarFirmaHistoria,
  aplicarFotos as aplicarFotosHistoria,
  aplicarHistoriaClinica as aplicarHistoriaClinicaHistoria,
  aplicarListaAntecedentes as aplicarListaAntecedentesHistoria,
  normalizarTexto as normalizarTextoHistoria
} from './historia-clinica.mapper';
import {
  obtenerAntecedentesSinDetalle,
  obtenerAntecedentesSinRespuesta,
  esCurpValida,
  esEdadValida,
  esFechaLocalDateValida,
  esTelefonoValido,
  obtenerFaltantesAntecedentesNoPatologicos,
  obtenerFaltantesCabezaCuello,
  obtenerFaltantesDatosPaciente,
  obtenerFaltantesDiagnosticoTratamiento,
  obtenerFaltantesEvolucion,
  obtenerFaltantesFotosInicio,
  obtenerFaltantesHistoriaCompletaFront,
  obtenerFaltantesSignosVitales,
  obtenerFaltantesTejidosBlandos,
  obtenerFaltantesTutor,
  tieneSeleccionEstomatognatico
} from './historia-clinica.validators';
import { guardarSeccionPorExistencia } from './historia-clinica.persistence';
import { guardarListaEnBloque } from './historia-clinica.collections';

@Component({
  selector: 'app-historia-clinica',
  standalone: false,
  templateUrl: './historia-clinica.html',
  styleUrl: './historia-clinica.css'
})


export class HistoriaClinica implements OnInit {

  idHistoriaClinica!: number;

  //Datos personales
  nombrePaciente: string = "";
  curp: string = "";
  sexo: string = "";
  edad: string = "";
  fechaNacimiento: string = "";
  domicilio: string = "";
  telefonoCasa: string = "";
  telefonoCelular: string = "";
  religion: string = "";
  ocupacion: string = "";
  escolaridad: string = "";
  estadoCivil: string = "";
  derechohabiente: string = "";
  medicoFamiliar: string = "";
  numeroMedico: string = "";
  ultimaConsulta: string = "";

  //antecedentes
  descripcionAntecedentes: string = '';
  tipoAntecedentes: string = '';
  detalle: string = '';
  respuesta: string = '';

  //PersonalesNoPatologicos
  frecuenciaLavadoDientes: string = '';
  usaAuxiliaresHigiene: string = '';
  tiposAuxiliaresHigiene: string = '';
  grupoSanguineo: string = '';
  factorRh: string = '';
  cartillaVacunacion: string = '';
  esquemaCompleto: string = '';
  vacunasFaltantes: string = '';
  antecedentesAlergicos: string = '';
  golosinas: string = '';
  cualAlergicos: string = '';
  antibioticos: string = '';
  analgesicos: string = '';
  anestesicos: string = '';
  alimentos: string = '';
  otrasAlergias: string = '';
  tieneAdicciones: string = '';
  tabaco: string = '';
  alcohol: string = '';
  otrasAdicciones: string = '';
  haSidoHospitalizado: string = '';
  fechaHospitalizacion: string = '';
  motivoHospitalizacion: string = '';
  padecimientoActual: string = '';
  haSidoAnestesiado: string = '';
  haRecibidoTransfusion: string = '';
  haRecibidoPerforaciones: string = '';
  consumeMedicamento: string = '';
  embarazo: string = '';
  discapacidad: string = '';
  tieneIntervenciones: string = '';
  parteCuerpo: string = '';

  //signosvitales
  temperatura: string = '';
  frecuenciaRespiratoria: string = '';
  tensionArterial: string = '';
  frecuenciaCardiaca: string = '';
  peso: string = '';
  talla: string = '';

  //cabezacuello
  cabezaCuello = {

    exostosis: false,
    endotosis: false,
    dolicocefalico: false,
    mesocefalico: false,
    branquicefalico: false,
    asimetriaTransversal: false,
    asimetriaLongitudinal: false,
    perfilConcavo: false,
    perfilConvexo: false,
    perfilRecto: false,
    pielNormal: false,
    pielPalida: false,
    pielCianotica: false,
    pielEnrojecida: false,
    musculosHipotonicos: false,
    musculosHipertonicos: false,
    musculosEspasticos: false,
    cadenaGanglionar: false

  }

  //estomatognatico

  estomatognatico = {
    ruidos: false,
    lateralidad: false,
    apertura: false,
    chasquidos: false,
    crepitacion: false,
    dificultadAbrirboca: false,
    dolorAberturaLateralidad: false,
    fatigaDolorMuscular: false,
    disminuicionAbertura: false,
    desviacionAberturaCierre: false,
  }

  // Tejidois blandos

  ganglios: string = '';
  glandulasSalivales: string = '';
  labioExterno: string = '';
  bordeBermellon: string = '';
  labioInterno: string = '';
  comisuras: string = '';
  carrillos: string = '';
  fondoDeSaco: string = '';
  frenillos: string = '';
  lenguaTercioMedio: string = '';
  paladarDuro: string = '';
  paladarBlando: string = '';
  istmoBucofaringe: string = '';
  lenguaDorso: string = '';
  lenguaBordes: string = '';
  lenguaVentral: string = '';
  pisoBoca: string = '';
  dientes: string = '';
  mucosaAlveolar: string = '';
  encia: string = '';

  //tutor
  nombreTutor: string = '';
  edadTutor: string = '';
  domicilioTutor: string = '';
  telefonoCasaTutor: string = '';
  celularTutor: string = '';

  //diagnostico y tratamiento
  interpretacionRx: string = '';
  diagnostico: string = '';
  resumenTratamiento: string = '';

  //evolucion
  fecha: string = '';
  comentarioControl: string = '';

  @ViewChild('canvas') canvasRef!: ElementRef<HTMLCanvasElement>;
  private signaturePad!: SignaturePad;

  firmas: Firma[] = [];

  foto = new Fotosinicio('', '');
  previewUrl: string | ArrayBuffer | null = null;

  listaFotos: Fotosinicio[] = [];
  previews: string[] = [];
  firmaPendiente: string | null = null;

  pacienteGuardado: boolean = false;


  antecedentesHeredofamiliaresList = [
    { descripcionAntecedentes: '¿Padece alguna enfermedad?', respuesta: '', detalle: '', tipoAntecedentes: 'PATOLOGICOS HEREDOFAMILIARES', curp: '' },
    { descripcionAntecedentes: 'Hipertensión', respuesta: '', detalle: '', tipoAntecedentes: 'PATOLOGICOS HEREDOFAMILIARES', curp: '' },
    { descripcionAntecedentes: 'Diabetes', respuesta: '', detalle: '', tipoAntecedentes: 'PATOLOGICOS HEREDOFAMILIARES', curp: '' },
    { descripcionAntecedentes: 'Hemorragias', respuesta: '', detalle: '', tipoAntecedentes: 'PATOLOGICOS HEREDOFAMILIARES', curp: '' },
    { descripcionAntecedentes: 'Cáncer', respuesta: '', detalle: '', tipoAntecedentes: 'PATOLOGICOS HEREDOFAMILIARES', curp: '' },
    { descripcionAntecedentes: 'Infecciones de transmisión sexual', respuesta: '', detalle: '', tipoAntecedentes: 'PATOLOGICOS HEREDOFAMILIARES', curp: '' },
    { descripcionAntecedentes: 'Cardiopatía', respuesta: '', detalle: '', tipoAntecedentes: 'PATOLOGICOS HEREDOFAMILIARES', curp: '' },
    { descripcionAntecedentes: 'Problemas renales', respuesta: '', detalle: '', tipoAntecedentes: 'PATOLOGICOS HEREDOFAMILIARES', curp: '' },
    { descripcionAntecedentes: 'Problemas pulmonares', respuesta: '', detalle: '', tipoAntecedentes: 'PATOLOGICOS HEREDOFAMILIARES', curp: '' }
  ];

  antecedentesPersonales = [
    { descripcionAntecedentes: '¿Padece alguna enfermedad?', respuesta: '', detalle: '', tipoAntecedentes: 'PERSONALES PATOLOGICOS', curp: '' },
    { descripcionAntecedentes: 'Hipertensión', respuesta: '', detalle: '', tipoAntecedentes: 'PERSONALES PATOLOGICOS', curp: '' },
    { descripcionAntecedentes: 'Diabetes', respuesta: '', detalle: '', tipoAntecedentes: 'PERSONALES PATOLOGICOS', curp: '' },
    { descripcionAntecedentes: 'Hemorragias', respuesta: '', detalle: '', tipoAntecedentes: 'PERSONALES PATOLOGICOS', curp: '' },
    { descripcionAntecedentes: 'Cáncer', respuesta: '', detalle: '', tipoAntecedentes: 'PERSONALES PATOLOGICOS', curp: '' },
    { descripcionAntecedentes: 'Infecciones de transmisión sexual', respuesta: '', detalle: '', tipoAntecedentes: 'PERSONALES PATOLOGICOS', curp: '' },
    { descripcionAntecedentes: 'Cardiopatía', respuesta: '', detalle: '', tipoAntecedentes: 'PERSONALES PATOLOGICOS', curp: '' },
    { descripcionAntecedentes: 'Problemas renales', respuesta: '', detalle: '', tipoAntecedentes: 'PERSONALES PATOLOGICOS', curp: '' },
    { descripcionAntecedentes: 'Problemas pulmonares', respuesta: '', detalle: '', tipoAntecedentes: 'PERSONALES PATOLOGICOS', curp: '' }
  ];

  datosEditados = true;
  nombreUsuarioLogueado: string = '';

  fechaMaximaNacimiento: string = new Date().toLocaleDateString('en-CA');
  nombrePacienteLogueado: string = '';

  DatosPacientes: any;

  ngOnInit(): void {
    localStorage.clear();
    this.nombreUsuarioLogueado = sessionStorage.getItem('nombre') || 'Usuario';

    this.cargarFirmas();

    const idHistoriaClinica = Number(
      this.route.snapshot.paramMap.get('idHistoriaClinica')
    );

    if (idHistoriaClinica) {
      this.cargarHistoriaClinicaPorId(idHistoriaClinica);
    }
  }

  get esMayorEdad(): boolean {
    return Number(this.edad) >= 18;
  }

  private calcularEdadDesdeFechaNacimiento(fechaNacimiento: string): string {
    if (!esFechaLocalDateValida(fechaNacimiento)) {
      return '';
    }

    const nacimiento = new Date(`${fechaNacimiento}T00:00:00`);
    const hoy = new Date();
    let edad = hoy.getFullYear() - nacimiento.getFullYear();

    const mesActual = hoy.getMonth();
    const mesNacimiento = nacimiento.getMonth();
    const diaActual = hoy.getDate();
    const diaNacimiento = nacimiento.getDate();

    if (mesActual < mesNacimiento || (mesActual === mesNacimiento && diaActual < diaNacimiento)) {
      edad -= 1;
    }

    return edad >= 0 ? String(edad) : '';
  }

  sincronizarEdadConFechaNacimiento(): void {
    if (!this.fechaNacimiento) {
      this.edad = '';
      return;
    }

    if (!esFechaLocalDateValida(this.fechaNacimiento)) {
      this.fechaNacimiento = '';
      this.edad = '';
      return;
    }

    this.edad = this.calcularEdadDesdeFechaNacimiento(this.fechaNacimiento);
  }

  normalizarFecha(campo: 'fechaNacimiento' | 'fechaHospitalizacion' | 'fecha'): void {
    const valor = this[campo];

    if (!valor) {
      if (campo === 'fechaNacimiento') {
        this.edad = '';
      }
      return;
    }

    if (!esFechaLocalDateValida(valor)) {
      this[campo] = '';

      if (campo === 'fechaNacimiento') {
        this.edad = '';
      }

      return;
    }

    if (campo === 'fechaNacimiento') {
      this.edad = this.calcularEdadDesdeFechaNacimiento(valor);
    }
  }

  cargarHistoriaClinicaPorId(idHistoriaClinica: number): void {
    this.historiaClinicaService.getHistoriaClinicaPorId(idHistoriaClinica).subscribe({
      next: (historia) => {
        this.idHistoriaClinica = historia.idHistoriaClinica;
        this.aplicarHistoriaClinica(historia);
        this.datosEditados = true;
      },
      error: (error) => {
        console.error('Error al cargar la historia clinica:', error);
      }
    });
  }

  constructor(
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private datosService: Datospacienteservice,
    private antecedentesService: Antecedentes,
    private nopatologicosService: NopatologicosService,
    private signosvitalesService: signosvitalesService,
    private cabezacuelloService: CabezacuelloService,
    private estomatognaticoService: Estomatognaticoservice,
    private tejidosblandosService: TejidosblandosService,
    private tutorService: Tutorservice,
    private diagnosticotratamientoService: Diagnosticotratamientoservice,
    private evolucionService: EvolucionService,
    private firmaService: FirmaService,
    private fotosInicioService: Fotosinicioservice,
    private historiaClinicaService: HistoriaClinicaService

  ) { }

  cargarHistoriaClinica(curp: string): void {
    this.historiaClinicaService.getHistoriaClinicaPorCurp(curp).subscribe({
      next: (historia) => {
        this.aplicarHistoriaClinica(historia);
        this.sincronizarEdadConFechaNacimiento();
        this.datosEditados = true;
      },
      error: (error) => {
        console.error('Error al cargar la historia clinica:', error);

        const mensaje = error?.status === 404
          ? 'No existe un paciente registrado con esa CURP.'
          : 'No se pudo cargar la historia clinica del paciente.';

        Swal.fire({
          title: 'Historia no encontrada',
          text: mensaje,
          icon: 'error',
          confirmButtonText: 'OK'
        }).then(() => this.router.navigate(['/mishc']));
      }
    });
  }

  aplicarHistoriaClinica(historia: any): void {
    aplicarHistoriaClinicaHistoria(this, historia);
    this.mostrarFirmaPendiente();
    this.sincronizarEdadConFechaNacimiento();
  }

  aplicarDatosPaciente(paciente: any): void {
    aplicarDatosPacienteHistoria(this, paciente);
  }

  aplicarAntecedentes(antecedentes: any[]): void {
    aplicarAntecedentesHistoria(this, antecedentes);
  }

  aplicarListaAntecedentes(listaFormulario: any[], listaBackend: any[]): void {
    aplicarListaAntecedentesHistoria(listaFormulario, listaBackend, this.curp);
  }

  aplicarCampos(origen: any, campos: string[]): void {
    aplicarCamposHistoria(this, origen, campos);
  }

  aplicarFotos(fotos: any[]): void {
    aplicarFotosHistoria(this, fotos);
  }

  aplicarFirma(firma: any): void {
    aplicarFirmaHistoria(this, firma);
  }

  mostrarFirmaPendiente(): void {
    if (!this.firmaPendiente || !this.signaturePad) {
      return;
    }

    this.signaturePad.clear();
    this.signaturePad.fromDataURL(this.firmaPendiente);
    this.firmaPendiente = null;
  }

  normalizarTexto(texto: string): string {
    return normalizarTextoHistoria(texto);
  }

  soloNumeros(event: KeyboardEvent): void {
    const permitidas = [
      'Backspace', 'Tab', 'ArrowLeft', 'ArrowRight', 'Delete', 'Home', 'End'
    ];

    if (permitidas.includes(event.key) || (event.ctrlKey || event.metaKey)) {
      return;
    }

    if (!/^\d$/.test(event.key)) {
      event.preventDefault();
    }
  }

  normalizarCurpInput(valor: string): string {
    return (valor || '')
      .toUpperCase()
      .replace(/[^A-Z0-9]/g, '')
      .slice(0, 18);
  }

  normalizarTelefonoInput(valor: string): string {
    return (valor || '')
      .replace(/\D/g, '')
      .slice(0, 10);
  }

  normalizarEdadInput(valor: string): string {
    return (valor || '')
      .replace(/\D/g, '')
      .slice(0, 3);
  }

  private validarDatosPaciente(): boolean {
    const faltantes = obtenerFaltantesDatosPaciente(this);
    const erroresFormato: string[] = [];

    if (!esCurpValida(this.curp)) erroresFormato.push('CURP inválida');
    if (!esEdadValida(this.edad)) erroresFormato.push('Edad inválida');
    if (!esFechaLocalDateValida(this.fechaNacimiento)) erroresFormato.push('Fecha de nacimiento inválida');
    if (!esTelefonoValido(this.telefonoCasa)) erroresFormato.push('Teléfono de casa/trabajo debe tener 10 dígitos');
    if (!esTelefonoValido(this.telefonoCelular)) erroresFormato.push('Teléfono celular debe tener 10 dígitos');
    if (this.numeroMedico && !esTelefonoValido(this.numeroMedico)) erroresFormato.push('Teléfono del médico debe tener 10 dígitos');

  if (faltantes.length > 0) {
    Swal.fire({
      icon: 'warning',
      title: 'Campos obligatorios',
      html: `
        Complete los siguientes datos del paciente:
        <br><br>
        ${faltantes.map(c => `• ${c}`).join('<br>')}
      `,
      confirmButtonText: 'Aceptar'
    });

    return false;
  }

  if (erroresFormato.length > 0) {
    Swal.fire({
      icon: 'warning',
      title: 'Formato inválido',
      html: `
        Corrige los siguientes campos:
        <br><br>
        ${erroresFormato.map(c => `• ${c}`).join('<br>')}
      `,
      confirmButtonText: 'Aceptar'
    });

    return false;
  }

  return true;
}


  addDatosPaciente() {
    this.sincronizarEdadConFechaNacimiento();

    if (!this.validarDatosPaciente()) {
    return;
  }
  
    Swal.fire({
      title: '¿Estás seguro?',
      text: 'Revise que todos los datos personales sean correctos.',
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: 'Sí, guardar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {

      if (!result.isConfirmed) {
        return;
      }

      const matricula = sessionStorage.getItem('matricula');

      const datospaciente = new DatosPacientes(
        this.nombrePaciente,
        this.curp,
        this.sexo,
        this.edad,
        this.fechaNacimiento,
        this.domicilio,
        this.telefonoCasa,
        this.telefonoCelular,
        this.religion,
        this.ocupacion,
        this.escolaridad,
        this.estadoCivil,
        this.derechohabiente,
        this.medicoFamiliar,
        this.numeroMedico,
        this.ultimaConsulta
      );

      const payload = {
        paciente: datospaciente,
        matricula: matricula
      };

      this.datosService.existePacientePorCurp(this.curp).subscribe({
        next: (existe) => {

          if (existe) {

            this.datosService.updateDatosPaciente(this.curp, datospaciente).subscribe({
              next: () => {
                this.pacienteGuardado = true;
                Swal.fire({
                  title: '¡Actualizado!',
                  text: 'Los datos del paciente fueron actualizados correctamente.',
                  icon: 'success',
                  confirmButtonText: 'OK'
                });
              },
              error: (err) => {
                console.error(err);
                Swal.fire({
                  title: 'Error',
                  text: 'Ocurrió un error al actualizar el paciente.',
                  icon: 'error',
                  confirmButtonText: 'OK'
                });
              }
            });

          } else {

            this.datosService.createDatosPaciente(payload).subscribe({
              next: () => {
                this.pacienteGuardado = true;
                Swal.fire({
                  title: '¡Guardado!',
                  text: 'Los datos del paciente fueron guardados exitosamente, puedes continuar a la siguiente sección.',
                  icon: 'success',
                  confirmButtonText: 'OK'
                });
              },
              error: (err) => {
                console.error(err);
                Swal.fire({
                  title: 'Error',
                  text: 'Ocurrió un error al guardar el paciente.',
                  icon: 'error',
                  confirmButtonText: 'OK'
                });
              }
            });

          }
        },
        error: (err) => {
          console.error(err);
          Swal.fire({
            title: 'Error',
            text: 'No se pudo verificar la existencia del paciente.',
            icon: 'error',
            confirmButtonText: 'OK'
          });
        }
      });

    });
  }

  listDatosPaciente() {
    this.datosService.getDatosPaciente().subscribe(
      data => {
        this.DatosPacientes = data;
        console.log(data);
      },
      error => {
        console.error('Error al obtener usuarios:', error);
      }
    );
  }

  validarAntecedentes(lista: any[]): boolean {
    const faltanRespuesta = obtenerAntecedentesSinRespuesta(lista);

    if (faltanRespuesta.length > 0) {

      const mensaje = faltanRespuesta
        .map(a => `• ${a.descripcionAntecedentes}`)
        .join('<br>');

      Swal.fire({
        icon: 'warning',
        title: 'Formulario incompleto',
        html: `
        Debe seleccionar una respuesta para los siguientes antecedentes:
        <br><br>
        ${mensaje}
      `,
        confirmButtonText: 'Aceptar'
      });

      return false;
    }

    const faltanDetalle = obtenerAntecedentesSinDetalle(lista);

    if (faltanDetalle.length > 0) {

      const mensaje = faltanDetalle
        .map(a => `• ${a.descripcionAntecedentes}`)
        .join('<br>');

      Swal.fire({
        icon: 'warning',
        title: 'Detalles faltantes',
        html: `
        Debe especificar un detalle para:
        <br><br>
        ${mensaje}
      `,
        confirmButtonText: 'Aceptar'
      });

      return false;
    }

    return true;
  }

  addAntecedentesPersonales() {
    if (!this.curp || this.curp.trim() === '') {
      console.error('No se ha especificado la CURP del paciente.');
      return;
    }

    if (!this.validarAntecedentes(this.antecedentesPersonales)) {
      return;
    }

    guardarListaEnBloque({
      lista: this.antecedentesPersonales.map((antecedente) => ({
        ...antecedente,
        curp: this.curp
      })),
      guardar: (antecedente) => this.antecedentesService.upsertAntecedente(antecedente),
      tituloExito: 'Guardado correctamente',
      textoExito: 'Los antecedentes personales fueron guardados correctamente.',
      textoError: 'Ocurrió un error al guardar la información.'
    });
  }



  addAntecedentesHeredofamiliares() {
    if (!this.curp || this.curp.trim() === '') {
      console.error('No se ha especificado la CURP del paciente.');
      return;
    }

    if (!this.validarAntecedentes(this.antecedentesHeredofamiliaresList)) {
      return;
    }

    guardarListaEnBloque({
      lista: this.antecedentesHeredofamiliaresList.map((antecedente) => ({
        ...antecedente,
        curp: this.curp
      })),
      guardar: (antecedente) => this.antecedentesService.upsertAntecedente(antecedente),
      tituloExito: 'Guardado correctamente',
      textoExito: 'Los antecedentes heredofamiliares fueron guardados correctamente.',
      textoError: 'Ocurrió un error al guardar la información.'
    });
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

  resetForm(): void {
    this.nombrePaciente = "";
    this.curp = "";
    this.sexo = "";
    this.edad = "";
    this.fechaNacimiento = "";
    this.domicilio = "";
    this.telefonoCasa = "";
    this.telefonoCelular = "";
    this.religion = "";
    this.ocupacion = "";
    this.escolaridad = "";
    this.estadoCivil = "";
    this.derechohabiente = "";
    this.medicoFamiliar = "";
    this.numeroMedico = "";
    this.ultimaConsulta = "";
  }


  private validarAntecedentesNoPatologicos(): boolean {
    const faltantes = obtenerFaltantesAntecedentesNoPatologicos(this);

    if (faltantes.length > 0) {
      Swal.fire({
        icon: 'warning',
        title: 'Campos obligatorios',
        html: faltantes.map(x => `• ${x}`).join('<br>')
      });

      return false;
    }

    if (
      this.usaAuxiliaresHigiene === 'Si' &&
      !this.tiposAuxiliaresHigiene?.trim()
    ) {
      Swal.fire({
        icon: 'warning',
        title: 'Información faltante',
        text: 'Debe especificar cuáles auxiliares de higiene utiliza.'
      });
      return false;
    }

    if (
      this.esquemaCompleto === 'No' &&
      !this.vacunasFaltantes?.trim()
    ) {
      Swal.fire({
        icon: 'warning',
        title: 'Información faltante',
        text: 'Debe especificar cuáles vacunas faltan.'
      });
      return false;
    }

    if (
      this.antecedentesAlergicos === 'Si' &&
      !this.cualAlergicos?.trim()
    ) {
      Swal.fire({
        icon: 'warning',
        title: 'Información faltante',
        text: 'Debe especificar las alergias.'
      });
      return false;
    }

    if (
      this.tieneAdicciones === 'Si' &&
      !this.tabaco?.trim() &&
      !this.alcohol?.trim() &&
      !this.otrasAdicciones?.trim()
    ) {
      Swal.fire({
        icon: 'warning',
        title: 'Información faltante',
        text: 'Debe indicar alguna adicción.'
      });
      return false;
    }

    if (
      this.haSidoHospitalizado === 'Si'
    ) {

      if (!this.fechaHospitalizacion) {
        Swal.fire({
          icon: 'warning',
          title: 'Información faltante',
          text: 'Debe indicar la fecha de hospitalización.'
        });
        return false;
      }

      if (!this.motivoHospitalizacion?.trim()) {
        Swal.fire({
          icon: 'warning',
          title: 'Información faltante',
          text: 'Debe indicar el motivo de hospitalización.'
        });
        return false;
      }
    }

    if (
      this.discapacidad === 'Si' &&
      !this.parteCuerpo?.trim()
    ) {
      Swal.fire({
        icon: 'warning',
        title: 'Información faltante',
        text: 'Debe indicar qué parte del cuerpo presenta discapacidad.'
      });
      return false;
    }

    return true;
  }

  addAntecedentesNoPatologicos() {
    if (!this.curp || this.curp.trim() === '') {
      console.error('No se ha especificado la CURP del paciente.');
      return;
    }

    if (!this.validarAntecedentesNoPatologicos()) {
      return;
    }

    const antecedentesNoPatologicos = new Nopatologicos(
      this.curp,
      this.frecuenciaLavadoDientes,
      this.usaAuxiliaresHigiene,
      this.tiposAuxiliaresHigiene,
      this.grupoSanguineo,
      this.factorRh,
      this.cartillaVacunacion,
      this.esquemaCompleto,
      this.vacunasFaltantes,
      this.antecedentesAlergicos,
      this.cualAlergicos,
      this.antibioticos,
      this.analgesicos,
      this.anestesicos,
      this.alimentos,
      this.otrasAlergias,
      this.tieneAdicciones,
      this.golosinas,
      this.tabaco,
      this.alcohol,
      this.otrasAdicciones,
      this.haSidoHospitalizado,
      this.fechaHospitalizacion,
      this.motivoHospitalizacion,
      this.padecimientoActual,
      this.haSidoAnestesiado,
      this.haRecibidoTransfusion,
      this.haRecibidoPerforaciones,
      this.consumeMedicamento,
      this.embarazo,
      this.discapacidad,
      this.tieneIntervenciones,
      this.parteCuerpo
    );

    guardarSeccionPorExistencia({
      existe: this.nopatologicosService.existenAntecedentesPorCurp(this.curp),
      crear: this.nopatologicosService.createAntecedentesnoPatologicos(antecedentesNoPatologicos),
      actualizar: this.nopatologicosService.updateAntecedentesnoPatologicos(this.curp, antecedentesNoPatologicos),
      tituloExitoCrear: 'Guardado correctamente',
      textoExitoCrear: 'Los antecedentes no patológicos fueron guardados correctamente.',
      tituloExitoActualizar: 'Guardado correctamente',
      textoExitoActualizar: 'Los antecedentes no patológicos fueron actualizados correctamente.',
      textoErrorCreacion: 'Ocurrió un error al guardar los antecedentes no patológicos.',
      textoErrorActualizacion: 'Ocurrió un error al actualizar los antecedentes no patológicos.',
      textoErrorVerificacion: 'No fue posible verificar si existen antecedentes no patológicos para este paciente.'
    });
  }

  private validarSignosVitales(): boolean {
    const faltantes = obtenerFaltantesSignosVitales(this);

    if (faltantes.length > 0) {
      Swal.fire({
        icon: 'warning',
        title: 'Campo obligatorio',
        text: `Debe capturar: ${faltantes.join(', ')}`
      });
      return false;
    }

    return true;
  }

  addSignosVitales() {
    if (!this.curp || this.curp.trim() === '') {
      console.error('No se ha especificado la CURP del paciente.');
      return;
    }

    if (!this.validarSignosVitales()) {
      return;
    }

    const signosVitales = {
      temperatura: this.temperatura,
      frecuenciaRespiratoria: this.frecuenciaRespiratoria,
      tensionArterial: this.tensionArterial,
      frecuenciaCardiaca: this.frecuenciaCardiaca,
      peso: this.peso,
      talla: this.talla,
      curp: this.curp
    };

    guardarSeccionPorExistencia({
      existe: this.signosvitalesService.existenSignosVitalesPorCurp(this.curp),
      crear: this.signosvitalesService.createSignosVitales(signosVitales),
      actualizar: this.signosvitalesService.updateSignosVitales(this.curp, signosVitales),
      tituloExitoCrear: 'Guardado',
      textoExitoCrear: 'Los signos vitales fueron guardados correctamente.',
      tituloExitoActualizar: 'Actualizado',
      textoExitoActualizar: 'Los signos vitales fueron actualizados correctamente.',
      textoErrorCreacion: 'Ocurrió un error al guardar los signos vitales.',
      textoErrorActualizacion: 'Ocurrió un error al actualizar los signos vitales.',
      textoErrorVerificacion: 'No fue posible verificar los signos vitales del paciente.'
    });
  }

  private validarCabezaCuello(): boolean {
    const faltantes = obtenerFaltantesCabezaCuello(this);

    if (faltantes.length > 0) {
      Swal.fire({
        icon: 'warning',
        title: 'Datos incompletos',
        html: `
        Debe seleccionar al menos una opción en:
        <br><br>
        ${faltantes.map(x => `• ${x}`).join('<br>')}
      `,
        confirmButtonText: 'Aceptar'
      });

      return false;
    }

    return true;
  }

  addCabezaCuello() {
    if (!this.curp || this.curp.trim() === '') {
      Swal.fire({
        icon: 'warning',
        title: 'CURP faltante',
        text: 'No se ha especificado la CURP del paciente.'
      });
      return;
    }

    if (!this.validarCabezaCuello()) {
      return;
    }

    const cabezaCuello = new Cabezacuello(
      this.cabezaCuello.exostosis,
      this.cabezaCuello.endotosis,
      this.cabezaCuello.dolicocefalico,
      this.cabezaCuello.mesocefalico,
      this.cabezaCuello.branquicefalico,
      this.cabezaCuello.asimetriaTransversal,
      this.cabezaCuello.asimetriaLongitudinal,
      this.cabezaCuello.perfilConcavo,
      this.cabezaCuello.perfilConvexo,
      this.cabezaCuello.perfilRecto,
      this.cabezaCuello.pielNormal,
      this.cabezaCuello.pielPalida,
      this.cabezaCuello.pielCianotica,
      this.cabezaCuello.pielEnrojecida,
      this.cabezaCuello.musculosHipotonicos,
      this.cabezaCuello.musculosHipertonicos,
      this.cabezaCuello.musculosEspasticos,
      this.cabezaCuello.cadenaGanglionar,
      this.curp
    );

    guardarSeccionPorExistencia({
      existe: this.cabezacuelloService.existenCabezaCuelloPorCurp(this.curp),
      crear: this.cabezacuelloService.createExploracionCabezaCuello(cabezaCuello),
      actualizar: this.cabezacuelloService.updateExploracionCabezaCuello(this.curp, cabezaCuello),
      tituloExitoCrear: 'Guardado',
      textoExitoCrear: 'Los datos de cabeza y cuello fueron guardados correctamente.',
      tituloExitoActualizar: 'Actualizado',
      textoExitoActualizar: 'Los datos de cabeza y cuello fueron actualizados correctamente.',
      textoErrorCreacion: 'Ocurrió un error al guardar cabeza y cuello.',
      textoErrorActualizacion: 'Ocurrió un error al actualizar cabeza y cuello.',
      textoErrorVerificacion: 'No se pudo verificar si ya existe cabeza y cuello para este paciente.'
    });
  }

  private validarEstomatognatico(): boolean {
    if (!tieneSeleccionEstomatognatico(this)) {
      Swal.fire({
        icon: 'warning',
        title: 'Datos incompletos',
        text: 'Debe seleccionar al menos una opción en exploración del aparato estomatognático.',
        confirmButtonText: 'Aceptar'
      });

      return false;
    }

    return true;
  }

  addEstomatognatico() {
    if (!this.curp || this.curp.trim() === '') {
      Swal.fire({
        icon: 'warning',
        title: 'CURP faltante',
        text: 'No se ha especificado la CURP del paciente.'
      });
      return;
    }

    const estomatognatico = new Estomatognatico(
      this.estomatognatico.ruidos,
      this.estomatognatico.lateralidad,
      this.estomatognatico.apertura,
      this.estomatognatico.chasquidos,
      this.estomatognatico.crepitacion,
      this.estomatognatico.dificultadAbrirboca,
      this.estomatognatico.dolorAberturaLateralidad,
      this.estomatognatico.fatigaDolorMuscular,
      this.estomatognatico.disminuicionAbertura,
      this.estomatognatico.desviacionAberturaCierre,
      this.curp
    );

    guardarSeccionPorExistencia({
      existe: this.estomatognaticoService.existenEstomatognaticoCurp(this.curp),
      crear: this.estomatognaticoService.createEstomatognatico(estomatognatico),
      actualizar: this.estomatognaticoService.updateEstomatognatico(this.curp, estomatognatico),
      tituloExitoCrear: 'Guardado',
      textoExitoCrear: 'Los datos del sistema estomatognático fueron guardados correctamente.',
      tituloExitoActualizar: 'Actualizado',
      textoExitoActualizar: 'Los datos del sistema estomatognático fueron actualizados correctamente.',
      textoErrorCreacion: 'Ocurrió un error al guardar el sistema estomatognático.',
      textoErrorActualizacion: 'Ocurrió un error al actualizar el sistema estomatognático.',
      textoErrorVerificacion: 'No se pudo verificar si ya existen datos del sistema estomatognático.'
    });
  }

  private validarTejidosBlandos(): boolean {
    const faltantes = obtenerFaltantesTejidosBlandos(this);

    if (faltantes.length > 0) {
      Swal.fire({
        icon: 'warning',
        title: 'Campos obligatorios',
        html: `
        Debe capturar descripción en:
        <br><br>
        ${faltantes.map(c => `• ${c.nombre}`).join('<br>')}
      `,
        confirmButtonText: 'Aceptar'
      });

      return false;
    }

    return true;
  }

  addTejidosBlandos() {
    if (!this.curp || this.curp.trim() === '') {
      Swal.fire({
        icon: 'warning',
        title: 'CURP faltante',
        text: 'No se ha especificado la CURP del paciente.'
      });
      return;
    }

    if (!this.validarTejidosBlandos()) {
      return;
    }

    const tejidosBlandos = new Tejidosblandos(
      this.ganglios,
      this.glandulasSalivales,
      this.labioExterno,
      this.bordeBermellon,
      this.labioInterno,
      this.comisuras,
      this.carrillos,
      this.fondoDeSaco,
      this.frenillos,
      this.lenguaTercioMedio,
      this.paladarDuro,
      this.paladarBlando,
      this.istmoBucofaringe,
      this.lenguaDorso,
      this.lenguaBordes,
      this.lenguaVentral,
      this.pisoBoca,
      this.dientes,
      this.mucosaAlveolar,
      this.encia,
      this.curp
    );

    guardarSeccionPorExistencia({
      existe: this.tejidosblandosService.existenTejidosBlandosPorCurp(this.curp),
      crear: this.tejidosblandosService.createTejidosBlandos(tejidosBlandos),
      actualizar: this.tejidosblandosService.updateTejidosBlandos(this.curp, tejidosBlandos),
      tituloExitoCrear: 'Guardado',
      textoExitoCrear: 'Los datos de tejidos blandos fueron guardados correctamente.',
      tituloExitoActualizar: 'Actualizado',
      textoExitoActualizar: 'Los datos de tejidos blandos fueron actualizados correctamente.',
      textoErrorCreacion: 'Ocurrió un error al guardar tejidos blandos.',
      textoErrorActualizacion: 'Ocurrió un error al actualizar tejidos blandos.',
      textoErrorVerificacion: 'No se pudo verificar si ya existen datos de tejidos blandos.'
    });
  }

  private validarTutor(): boolean {
    const faltantes = obtenerFaltantesTutor(this);
    const erroresFormato: string[] = [];

    if (!esEdadValida(this.edadTutor)) erroresFormato.push('Edad del tutor inválida');
    if (!esTelefonoValido(this.telefonoCasaTutor)) erroresFormato.push('Teléfono de casa o trabajo del tutor debe tener 10 dígitos');
    if (!esTelefonoValido(this.celularTutor)) erroresFormato.push('Teléfono celular del tutor debe tener 10 dígitos');

    if (faltantes.length > 0) {
      Swal.fire({
        icon: 'warning',
        title: 'Campos obligatorios',
        html: `
        Complete los siguientes datos del tutor:
        <br><br>
        ${faltantes.map(c => `• ${c}`).join('<br>')}
      `,
        confirmButtonText: 'Aceptar'
      });

      return false;
    }

    if (erroresFormato.length > 0) {
      Swal.fire({
        icon: 'warning',
        title: 'Formato inválido',
        html: `
        Corrige los siguientes datos del tutor:
        <br><br>
        ${erroresFormato.map(c => `• ${c}`).join('<br>')}
      `,
        confirmButtonText: 'Aceptar'
      });

      return false;
    }

    return true;
  }


  addTutor() {
    if (!this.curp || this.curp.trim() === '') {
      Swal.fire({
        icon: 'warning',
        title: 'CURP faltante',
        text: 'No se ha especificado la CURP del paciente.'
      });
      return;
    }

    if (!this.validarTutor()) {
      return;
    }

    const tutor = new Tutor(
      this.nombreTutor,
      this.edadTutor,
      this.domicilioTutor,
      this.telefonoCasaTutor,
      this.celularTutor,
      this.curp
    );

    guardarSeccionPorExistencia({
      existe: this.tutorService.existeTutorPorCurp(this.curp),
      crear: this.tutorService.createTutor(tutor),
      actualizar: this.tutorService.updateTutor(this.curp, tutor),
      tituloExitoCrear: 'Guardado',
      textoExitoCrear: 'Los datos del tutor fueron guardados correctamente.',
      tituloExitoActualizar: 'Actualizado',
      textoExitoActualizar: 'Los datos del tutor fueron actualizados correctamente.',
      textoErrorCreacion: 'Ocurrió un error al guardar los datos del tutor.',
      textoErrorActualizacion: 'Ocurrió un error al actualizar los datos del tutor.',
      textoErrorVerificacion: 'No se pudo verificar si ya existen datos del tutor.'
    });
  }

  private validarDiagnosticoTratamiento(): boolean {
    const faltantes = obtenerFaltantesDiagnosticoTratamiento(this);

    if (faltantes.length > 0) {
      Swal.fire({
        icon: 'warning',
        title: 'Campos obligatorios',
        html: `
        Complete los siguientes campos:
        <br><br>
        ${faltantes.map(c => `• ${c}`).join('<br>')}
      `,
        confirmButtonText: 'Aceptar'
      });

      return false;
    }

    return true;
  }

  addDiagnosticoTratamiento() {
    if (!this.curp || this.curp.trim() === '') {
      Swal.fire({
        icon: 'warning',
        title: 'CURP faltante',
        text: 'No se ha especificado la CURP del paciente.'
      });
      return;
    }

    if (!this.validarDiagnosticoTratamiento()) {
      return;
    }

    const diagnostico = new Diagnosticotratamiento(
      this.interpretacionRx,
      this.diagnostico,
      this.resumenTratamiento,
      this.curp
    );

    guardarSeccionPorExistencia({
      existe: this.diagnosticotratamientoService.existenDiagnosticoTratamientoCurp(this.curp),
      crear: this.diagnosticotratamientoService.createDiagnosticoTratamiento(diagnostico),
      actualizar: this.diagnosticotratamientoService.updateDiagnosticoTratamiento(this.curp, diagnostico),
      tituloExitoCrear: 'Guardado',
      textoExitoCrear: 'Los datos de diagnóstico y tratamiento fueron guardados correctamente.',
      tituloExitoActualizar: 'Actualizado',
      textoExitoActualizar: 'Los datos de diagnóstico y tratamiento fueron actualizados correctamente.',
      textoErrorCreacion: 'Ocurrió un error al guardar diagnóstico y tratamiento.',
      textoErrorActualizacion: 'Ocurrió un error al actualizar diagnóstico y tratamiento.',
      textoErrorVerificacion: 'No se pudo verificar si ya existen datos de diagnóstico y tratamiento.'
    });
  }

  private validarEvolucion(): boolean {
    const faltantes = obtenerFaltantesEvolucion(this);

    if (faltantes.length > 0) {
      Swal.fire({
        icon: 'warning',
        title: 'Campos obligatorios',
        html: `
        Complete los siguientes campos:
        <br><br>
        ${faltantes.map(c => `• ${c}`).join('<br>')}
      `,
        confirmButtonText: 'Aceptar'
      });

      return false;
    }

    return true;
  }

  addEvolucion() {
    if (!this.curp || this.curp.trim() === '') {
      Swal.fire({
        icon: 'warning',
        title: 'CURP faltante',
        text: 'No se ha especificado la CURP del paciente.'
      });
      return;
    }

    if (!this.validarEvolucion()) {
      return;
    }

    const evolucion = new Evolucion(
      this.fecha,
      this.comentarioControl,
      this.curp
    );

    guardarSeccionPorExistencia({
      existe: this.evolucionService.existenEvolucionPorCurp(this.curp),
      crear: this.evolucionService.createEvolucion(evolucion),
      actualizar: this.evolucionService.updateEvolucion(this.curp, evolucion),
      tituloExitoCrear: 'Guardado',
      textoExitoCrear: 'Los datos de evolución fueron guardados correctamente.',
      tituloExitoActualizar: 'Actualizado',
      textoExitoActualizar: 'Los datos de evolución fueron actualizados correctamente.',
      textoErrorCreacion: 'Ocurrió un error al guardar la evolución.',
      textoErrorActualizacion: 'Ocurrió un error al actualizar la evolución.',
      textoErrorVerificacion: 'No se pudo verificar si ya existen datos de evolución.'
    });
  }

  ngAfterViewInit() {
    this.signaturePad = new SignaturePad(this.canvasRef.nativeElement);
  }

  addFirma() {
    if (this.signaturePad.isEmpty()) {
      console.warn('Firma vacía: Por favor, firma antes de guardar.');
      return;
    }

    if (!this.curp || this.curp.trim() === '') {
      console.error('CURP faltante: Por favor, ingresa la CURP.');
      return;
    }

    const firmaBase64 = this.signaturePad.toDataURL();
    const nuevaFirma = new Firma(firmaBase64, this.curp);

    this.firmaService.guardarFirma(nuevaFirma).subscribe({
      next: () => {
        console.log('Firma guardada correctamente.');
        this.signaturePad.clear();
      },
      error: () => {
        console.error('Error al guardar la firma.');
      }
    });
  }


  limpiarFirma() {
    this.signaturePad.clear();
  }

  cargarFirmas() {
    this.firmaService.obtenerFirmas().subscribe({
      next: (data) => this.firmas = data,
      error: () => alert('No se pudieron cargar las firmas')
    });
  }

  onFileChange(event: any): void {
    const files: FileList = event.target.files;

    this.listaFotos = []; // Limpiar anteriores
    this.previews = [];

    Array.from(files).forEach(file => {
      const reader = new FileReader();
      reader.onload = () => {
        const base64 = reader.result as string;
        this.listaFotos.push(new Fotosinicio(base64, this.curp));
        this.previews.push(base64); // Para mostrar previews opcional
      };
      reader.readAsDataURL(file);
    });
  }

  fotosGuardadas = false;
  private validarFotosInicio(): boolean {
    const faltantes = obtenerFaltantesFotosInicio(this);

    if (faltantes.length > 0) {
      Swal.fire({
        icon: 'warning',
        title: 'Campos obligatorios',
        text: faltantes.join(', ')
      });
      return false;
    }

    return true;
  }

  addFotosInicio(): void {
    if (!this.validarFotosInicio()) {
      return;
    }

    const fotosParaGuardar = this.listaFotos.map(
      f => new Fotosinicio(f.fotos, this.curp)
    );

    this.fotosInicioService.guardarMultiplesFotos(fotosParaGuardar).subscribe({
      next: () => {
        this.fotosGuardadas = true;

        Swal.fire({
          icon: 'success',
          title: 'Guardado',
          text: 'Las fotos del paciente fueron guardadas correctamente.',
          timer: 2000,
          showConfirmButton: false
        });

        this.listaFotos = [];
        this.previews = [];
      },
      error: (err) => {
        console.error(err);

        Swal.fire({
          icon: 'error',
          title: 'Error',
          text: 'Ocurrió un error al guardar las fotos del paciente.'
        });
      }
    });
  }

  validarHistoriaCompletaFront(): string[] {
    return obtenerFaltantesHistoriaCompletaFront(this);
  }

  hayFotos(): boolean {
    return this.fotosGuardadas || this.listaFotos.length > 0 || this.previews.length > 0;
  }

  hayFirma(): boolean {
    if (this.firmaPendiente) {
      return true;
    }

    if (this.signaturePad && !this.signaturePad.isEmpty()) {
      return true;
    }

    return false;
  }

  enviarHC() {
    const faltantes = this.validarHistoriaCompletaFront();

    if (faltantes.length > 0) {
      Swal.fire({
        icon: 'warning',
        title: 'Historia clínica incompleta',
        html: `
      <p>Faltan las siguientes secciones:</p>
      <ul style="text-align:left;">
        ${faltantes.map(f => `<li>${f}</li>`).join('')}
      </ul>
    `
      });
      return;
    }
    if (!this.curp || this.curp.trim() === '') {
      Swal.fire({
        icon: 'error',
        title: 'CURP faltante',
        text: 'Por favor, ingresa la CURP antes de guardar.'
      });
      return;
    }

    if (!this.idHistoriaClinica) {
      Swal.fire({
        icon: 'error',
        title: 'Historia clínica no identificada',
        text: 'No se encontró el ID de la historia clínica.'
      });
      return;
    }

    Swal.fire({
      title: '¿Enviar a revisión?',
      text: 'Se guardarán todos los datos y la historia clínica pasará a revisión.',
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: 'Sí, enviar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        this.addDatosPaciente();
        this.addAntecedentesPersonales();
        this.addAntecedentesHeredofamiliares();
        this.addAntecedentesNoPatologicos();
        this.addSignosVitales();
        this.addCabezaCuello();
        this.addEstomatognatico();
        this.addTejidosBlandos();
        this.addTutor();
        this.addDiagnosticoTratamiento();
        this.addEvolucion();
        this.addFotosInicio();
        this.addFirma();

        this.historiaClinicaService.enviarRevision(this.idHistoriaClinica).subscribe({
          next: () => {
            Swal.fire({
              icon: 'success',
              title: 'Historia clínica enviada',
              text: 'La historia clínica fue enviada a revisión correctamente.'
            });
          },
          error: (err) => {
            const mensaje =
              err?.error?.message ||
              err?.error?.mensaje ||
              'No se pudo enviar la historia clínica a revisión.';

            Swal.fire({
              icon: 'error',
              title: 'No se pudo enviar',
              text: mensaje
            });
          }
        });
      }
    });
  }
}





