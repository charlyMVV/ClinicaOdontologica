import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { AuthService } from '../../service/auth';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { DatosPacientes } from '../../datos-pacientes';
import { Datospacienteservice } from '../../service/datospacienteservice';
import { forkJoin } from 'rxjs';
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






@Component({
  selector: 'app-historia-clinica',
  standalone: false,
  templateUrl: './historia-clinica.html',
  styleUrl: './historia-clinica.css'
})


export class HistoriaClinica implements OnInit {


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
  nombrePacienteLogueado: string = '';

  DatosPacientes: any;

  ngOnInit(): void {
    localStorage.clear();
    this.nombreUsuarioLogueado = sessionStorage.getItem('nombre') || 'Usuario';


    this.cargarFirmas();

    const curpRuta = this.route.snapshot.paramMap.get('curp');
    if (curpRuta) {
      this.cargarHistoriaClinica(curpRuta);
    }


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
    this.curp = historia?.curp || '';
    this.aplicarDatosPaciente(historia?.paciente);
    this.aplicarAntecedentes(historia?.antecedentes || []);
    this.aplicarCampos(historia?.antecedentesNoPatologicos, [
      'frecuenciaLavadoDientes', 'usaAuxiliaresHigiene', 'tiposAuxiliaresHigiene', 'grupoSanguineo',
      'factorRh', 'cartillaVacunacion', 'esquemaCompleto', 'vacunasFaltantes', 'antecedentesAlergicos',
      'golosinas', 'cualAlergicos', 'antibioticos', 'analgesicos', 'anestesicos', 'alimentos',
      'otrasAlergias', 'tieneAdicciones', 'tabaco', 'alcohol', 'otrasAdicciones', 'haSidoHospitalizado',
      'fechaHospitalizacion', 'motivoHospitalizacion', 'padecimientoActual', 'haSidoAnestesiado',
      'haRecibidoTransfusion', 'haRecibidoPerforaciones', 'consumeMedicamento', 'embarazo',
      'discapacidad', 'tieneIntervenciones', 'parteCuerpo'
    ]);
    this.aplicarCampos(historia?.signosVitales, [
      'temperatura', 'frecuenciaRespiratoria', 'tensionArterial', 'frecuenciaCardiaca', 'peso', 'talla'
    ]);

    if (historia?.cabezaCuello) {
      Object.assign(this.cabezaCuello, historia.cabezaCuello);
    }

    if (historia?.estomatognatico) {
      Object.assign(this.estomatognatico, historia.estomatognatico);
    }

    this.aplicarCampos(historia?.tejidosBlandos, [
      'ganglios', 'glandulasSalivales', 'labioExterno', 'bordeBermellon', 'labioInterno', 'comisuras',
      'carrillos', 'fondoDeSaco', 'frenillos', 'lenguaTercioMedio', 'paladarDuro', 'paladarBlando',
      'istmoBucofaringe', 'lenguaDorso', 'lenguaBordes', 'lenguaVentral', 'pisoBoca', 'dientes',
      'mucosaAlveolar', 'encia'
    ]);
    this.aplicarCampos(historia?.tutor, [
      'nombreTutor', 'edadTutor', 'domicilioTutor', 'telefonoCasaTutor', 'celularTutor'
    ]);
    this.aplicarCampos(historia?.diagnosticoTratamiento, [
      'interpretacionRx', 'diagnostico', 'resumenTratamiento'
    ]);
    this.aplicarCampos(historia?.evolucionPaciente, ['fecha', 'comentarioControl']);
    this.aplicarFotos(historia?.fotosInicio || []);
    this.aplicarFirma(historia?.firma);
  }

  aplicarDatosPaciente(paciente: any): void {
    if (!paciente) {
      return;
    }

    this.aplicarCampos(paciente, [
      'nombrePaciente', 'curp', 'sexo', 'edad', 'fechaNacimiento', 'domicilio', 'telefonoCasa',
      'telefonoCelular', 'religion', 'ocupacion', 'escolaridad', 'estadoCivil', 'derechohabiente',
      'medicoFamiliar', 'numeroMedico', 'ultimaConsulta'
    ]);
  }

  aplicarAntecedentes(antecedentes: any[]): void {
    const heredofamiliares = antecedentes.filter((antecedente) =>
      this.normalizarTexto(antecedente?.tipoAntecedentes).includes('heredofamiliares')
    );
    const personales = antecedentes.filter((antecedente) =>
      this.normalizarTexto(antecedente?.tipoAntecedentes).includes('personales patologicos')
    );

    this.aplicarListaAntecedentes(this.antecedentesHeredofamiliaresList, heredofamiliares);
    this.aplicarListaAntecedentes(this.antecedentesPersonales, personales);
  }

  aplicarListaAntecedentes(listaFormulario: any[], listaBackend: any[]): void {
    listaFormulario.forEach((antecedenteFormulario, index) => {
      const descripcion = this.normalizarTexto(antecedenteFormulario.descripcionAntecedentes);
      const antecedenteBackend = listaBackend.find((antecedente) =>
        this.normalizarTexto(antecedente?.descripcionAntecedentes) === descripcion
      ) || listaBackend[index];

      if (antecedenteBackend) {
        antecedenteFormulario.respuesta = antecedenteBackend.respuesta || '';
        antecedenteFormulario.detalle = antecedenteBackend.detalle || '';
        antecedenteFormulario.curp = antecedenteBackend.curp || this.curp;
      }
    });
  }

  aplicarCampos(origen: any, campos: string[]): void {
    if (!origen) {
      return;
    }

    campos.forEach((campo) => {
      if (origen[campo] !== undefined && origen[campo] !== null) {
        (this as any)[campo] = origen[campo];
      }
    });
  }

  aplicarFotos(fotos: any[]): void {
    this.listaFotos = fotos.map((foto) => new Fotosinicio(foto.fotos || '', foto.curp || this.curp));
    this.previews = this.listaFotos.map((foto) => foto.fotos).filter((foto) => !!foto);
  }

  aplicarFirma(firma: any): void {
    if (!firma?.firma) {
      return;
    }

    this.firmaPendiente = firma.firma;
    this.mostrarFirmaPendiente();
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
    return (texto || '')
      .normalize('NFD')
      .replace(/[\u0300-\u036f]/g, '')
      .toLowerCase()
      .trim();
  }




  addDatosPaciente() {
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

    this.datosService.existePacientePorCurp(this.curp).subscribe({
      next: (existe) => {

        if (existe) {

          this.datosService.updateDatosPaciente(this.curp, datospaciente).subscribe({
            next: () => {
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

          this.datosService.createDatosPaciente(datospaciente).subscribe({
            next: () => {
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

  addAntecedentesPersonales() {
  if (!this.curp || this.curp.trim() === '') {
    console.error('No se ha especificado la CURP del paciente.');
    return;
  }

  const descripcionesFaltantes = this.antecedentesPersonales.filter(
    (antecedente) => !antecedente.respuesta || antecedente.respuesta.trim() === ''
  );

  if (descripcionesFaltantes.length > 0) {
    const listaDescripciones = descripcionesFaltantes
      .map((a) => `- ${a.descripcionAntecedentes}`)
      .join('\n');

    console.warn('Faltan respuestas en:', listaDescripciones);
    return;
  }

  const peticiones = this.antecedentesPersonales.map((antecedente) => {
    return this.antecedentesService.upsertAntecedente({
      ...antecedente,
      curp: this.curp
    });
  });

  forkJoin(peticiones).subscribe({
    next: () => {
      console.log('Antecedentes personales guardados/actualizados correctamente.');
    },
    error: (err) => {
      console.error('Error al guardar/actualizar antecedentes personales:', err);
    }
  });
}



 addAntecedentesHeredofamiliares() {
  if (!this.curp || this.curp.trim() === '') {
    console.error('No se ha especificado la CURP del paciente.');
    return;
  }

  const peticiones = this.antecedentesHeredofamiliaresList.map((antecedente) => {
    return this.antecedentesService.upsertAntecedente({
      ...antecedente,
      curp: this.curp
    });
  });

  forkJoin(peticiones).subscribe({
    next: () => {
      console.log('Antecedentes heredofamiliares guardados/actualizados correctamente.');
    },
    error: (err) => {
      console.error('Error al guardar/actualizar antecedentes heredofamiliares:', err);
    }
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

  addAntecedentesNoPatologicos() {
  if (!this.curp || this.curp.trim() === '') {
    console.error('No se ha especificado la CURP del paciente.');
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

  this.nopatologicosService.existenAntecedentesPorCurp(this.curp).subscribe({
    next: (existe) => {
      if (existe) {
        this.nopatologicosService
          .updateAntecedentesnoPatologicos(this.curp, antecedentesNoPatologicos)
          .subscribe({
            next: () => {
              console.log('Antecedentes no patológicos actualizados correctamente.');
            },
            error: (err) => {
              console.error('Error al actualizar antecedentes no patológicos:', err);
            }
          });
      } else {
        this.nopatologicosService
          .createAntecedentesnoPatologicos(antecedentesNoPatologicos)
          .subscribe({
            next: () => {
              console.log('Antecedentes no patológicos guardados correctamente.');
            },
            error: (err) => {
              console.error('Error al guardar antecedentes no patológicos:', err);
            }
          });
      }
    },
    error: (err) => {
      console.error('Error al verificar existencia de antecedentes no patológicos:', err);
    }
  });
}

addSignosVitales() {
  if (!this.curp || this.curp.trim() === '') {
    console.error('No se ha especificado la CURP del paciente.');
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

  this.signosvitalesService.existenSignosVitalesPorCurp(this.curp).subscribe({
    next: (existe) => {
      if (existe) {
        this.signosvitalesService
          .updateSignosVitales(this.curp, signosVitales)
          .subscribe({
            next: () => {
              console.log('Signos vitales actualizados correctamente.');
            },
            error: (err) => {
              console.error('Error al actualizar signos vitales:', err);
            }
          });
      } else {
        this.signosvitalesService
          .createSignosVitales(signosVitales)
          .subscribe({
            next: () => {
              console.log('Signos vitales guardados correctamente.');
            },
            error: (err) => {
              console.error('Error al guardar signos vitales:', err);
            }
          });
      }
    },
    error: (err) => {
      console.error('Error al verificar existencia de signos vitales:', err);
    }
  });
}


  addCabezaCuello() {
  if (!this.curp || this.curp.trim() === '') {
    console.error('No se ha especificado la CURP del paciente.');
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

  this.cabezacuelloService.existenCabezaCuelloPorCurp(this.curp).subscribe({
    next: (existe) => {
      if (existe) {
        this.cabezacuelloService
          .updateExploracionCabezaCuello(this.curp, cabezaCuello)
          .subscribe({
            next: () => {
              console.log('Datos de Cabeza y Cuello actualizados exitosamente.');
            },
            error: (err) => {
              console.error('Error al actualizar datos de Cabeza y Cuello:', err);
            }
          });
      } else {
        this.cabezacuelloService
          .createExploracionCabezaCuello(cabezaCuello)
          .subscribe({
            next: () => {
              console.log('Datos de Cabeza y Cuello guardados exitosamente.');
            },
            error: (err) => {
              console.error('Error al guardar datos de Cabeza y Cuello:', err);
            }
          });
      }
    },
    error: (err) => {
      console.error('No se pudo verificar si ya existen registros para la CURP:', err);
    }
  });
}

  addEstomatognatico() {
  if (!this.curp || this.curp.trim() === '') {
    console.error('No se ha especificado la CURP del paciente.');
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

  this.estomatognaticoService.existenEstomatognaticoCurp(this.curp).subscribe({
    next: (existe) => {

      if (existe) {

        this.estomatognaticoService
          .updateEstomatognatico(this.curp, estomatognatico)
          .subscribe({
            next: () => {
              console.log('Datos del sistema estomatognático actualizados exitosamente.');
            },
            error: (err) => {
              console.error('Error al actualizar datos del sistema estomatognático:', err);
            }
          });

      } else {

        this.estomatognaticoService
          .createEstomatognatico(estomatognatico)
          .subscribe({
            next: () => {
              console.log('Datos del sistema estomatognático guardados exitosamente.');
            },
            error: (err) => {
              console.error('Error al guardar datos del sistema estomatognático:', err);
            }
          });

      }
    },
    error: (err) => {
      console.error(
        'No se pudo verificar si ya existen registros del sistema estomatognático para la CURP:',
        err
      );
    }
  });
}




  addTejidosBlandos() {
  if (!this.curp || this.curp.trim() === '') {
    console.error('No se ha especificado la CURP del paciente.');
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

  this.tejidosblandosService.existenTejidosBlandosPorCurp(this.curp).subscribe({
    next: (existe) => {
      if (existe) {
        this.tejidosblandosService.updateTejidosBlandos(this.curp, tejidosBlandos).subscribe({
          next: () => {
            console.log('Datos de tejidos blandos actualizados exitosamente.');
          },
          error: (err) => {
            console.error('Error al actualizar datos de tejidos blandos:', err);
          }
        });
      } else {
        this.tejidosblandosService.createTejidosBlandos(tejidosBlandos).subscribe({
          next: () => {
            console.log('Datos de tejidos blandos guardados exitosamente.');
          },
          error: (err) => {
            console.error('Error al guardar datos de tejidos blandos:', err);
          }
        });
      }
    },
    error: (err) => {
      console.error('No se pudo verificar si ya existen registros de tejidos blandos para la CURP:', err);
    }
  });
}

  addTutor() {
  if (!this.curp || this.curp.trim() === '') {
    console.error('No se ha especificado la CURP del paciente.');
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

  this.tutorService.existeTutorPorCurp(this.curp).subscribe({
    next: (existe) => {

      if (existe) {

        this.tutorService.updateTutor(this.curp, tutor).subscribe({
          next: () => {
            console.log('Datos del tutor actualizados exitosamente.');
          },
          error: (err) => {
            console.error('Error al actualizar datos del tutor:', err);
          }
        });

      } else {

        this.tutorService.createTutor(tutor).subscribe({
          next: () => {
            console.log('Datos del tutor guardados exitosamente.');
          },
          error: (err) => {
            console.error('Error al guardar datos del tutor:', err);
          }
        });

      }
    },
    error: (err) => {
      console.error(
        'No se pudo verificar si ya existen registros del tutor para la CURP:',
        err
      );
    }
  });
}


  addDiagnosticoTratamiento() {
  if (!this.curp || this.curp.trim() === '') {
    console.error('No se ha especificado la CURP del paciente.');
    return;
  }

  const diagnostico = new Diagnosticotratamiento(
    this.interpretacionRx,
    this.diagnostico,
    this.resumenTratamiento,
    this.curp
  );

  this.diagnosticotratamientoService.existenDiagnosticoTratamientoCurp(this.curp).subscribe({
    next: (existe) => {
      if (existe) {
        this.diagnosticotratamientoService
          .updateDiagnosticoTratamiento(this.curp, diagnostico)
          .subscribe({
            next: () => {
              console.log('Datos de diagnóstico y tratamiento actualizados exitosamente.');
            },
            error: (err) => {
              console.error('Error al actualizar diagnóstico y tratamiento:', err);
            }
          });
      } else {
        this.diagnosticotratamientoService
          .createDiagnosticoTratamiento(diagnostico)
          .subscribe({
            next: () => {
              console.log('Datos de diagnóstico y tratamiento guardados exitosamente.');
            },
            error: (err) => {
              console.error('Error al guardar diagnóstico y tratamiento:', err);
            }
          });
      }
    },
    error: (err) => {
      console.error('Error al verificar registros de diagnóstico para la CURP:', err);
    }
  });
}

  addEvolucion() {
  if (!this.curp || this.curp.trim() === '') {
    console.error('No se ha especificado la CURP del paciente.');
    return;
  }

  const evolucion = new Evolucion(
    this.fecha,
    this.comentarioControl,
    this.curp
  );

  this.evolucionService.existenEvolucionPorCurp(this.curp).subscribe({
    next: (existe) => {

      if (existe) {

        this.evolucionService.updateEvolucion(this.curp, evolucion).subscribe({
          next: () => {
            console.log('Datos de evolución actualizados exitosamente.');
          },
          error: (err) => {
            console.error('Error al actualizar evolución:', err);
          }
        });

      } else {

        this.evolucionService.createEvolucion(evolucion).subscribe({
          next: () => {
            console.log('Datos de evolución guardados exitosamente.');
          },
          error: (err) => {
            console.error('Error al guardar evolución:', err);
          }
        });

      }
    },
    error: (err) => {
      console.error('Error al verificar registros de evolución para la CURP:', err);
    }
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

  addFotosInicio(): void {
    if (!this.curp || this.listaFotos.length === 0) {
      return;
    }

    // Asignar CURP a cada imagen
    this.listaFotos = this.listaFotos.map(f => new Fotosinicio(f.fotos, this.curp));

    console.log('Enviando fotos:', this.listaFotos);

    this.fotosInicioService.guardarMultiplesFotos(this.listaFotos).subscribe({
      next: (res: any) => {
        this.listaFotos = [];
        this.previews = [];
        this.curp = '';
      },
      error: (err) => {
        console.error('Error al guardar imágenes', err);
      }
    });
  }

  enviarHC() {
    if (!this.curp || this.curp.trim() === '') {
      Swal.fire({
        icon: 'error',
        title: 'CURP faltante',
        text: 'Por favor, ingresa la CURP antes de guardar.'
      });
      return;
    }

    Swal.fire({
      title: '¿Estás seguro?',
      text: 'Se guardarán todos los datos del expediente clínico.',
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: 'Sí, guardar',
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

        Swal.fire({
          icon: 'success',
          title: 'Expediente guardado',
          text: 'Todos los datos han sido enviados correctamente.'
        });
      }
    });
  }


}
