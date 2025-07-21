import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { AuthService } from '../../service/auth';
import { Router } from '@angular/router';
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
  fecha :string = '';
  comentarioControl : string = '';




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
  DatosPacientes: any;

  ngOnInit(): void {

    this.nombreUsuarioLogueado = sessionStorage.getItem('nombre') || 'Usuario';

  }



  constructor(
    private authService: AuthService,
    private router: Router,
    private datosService: Datospacienteservice,
    private antecedentesService: Antecedentes,
    private nopatologicosService: NopatologicosService,
    private signosvitalesService: signosvitalesService,
    private cabezacuelloService: CabezacuelloService,
    private estomatognaticoService: Estomatognaticoservice,
    private tejidosblandosService: TejidosblandosService,
    private tutorService: Tutorservice,
    private diagnosticotratamientoService: Diagnosticotratamientoservice,
    private evolucionService: EvolucionService

  ) { }




  addDatosPaciente() {
    Swal.fire({
      title: '¿Estás seguro?',
      text: 'Revise que todos los datos personales sean correctos.',
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: 'Sí, guardar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        let datospaciente = new DatosPacientes(
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

        console.log(datospaciente);

        this.datosService.createDatosPaciente(datospaciente).subscribe({
          next: (res) => {
            Swal.fire({
              title: '¡Guardado!',
              text: 'Los datos del paciente fueron guardados exitosamente, puedes continuar a la siguiente sección',
              icon: 'success',
              confirmButtonText: 'OK'
            });
          },
          error: (err) => {
            console.error(err);

            {
              Swal.fire({
                title: 'Error',
                text: 'Ocurrió un error al guardar el paciente.',
                icon: 'error',
                confirmButtonText: 'OK'
              });
            }
          }
        });
      }
    });
  }

  addAntecedentesPersonales() {
    if (!this.curp || this.curp.trim() === '') {
      Swal.fire('Error', 'No se ha especificado la CURP del paciente.', 'error');
      return;
    }

    // Validar que todas las respuestas estén completas
    const descripcionesFaltantes = this.antecedentesPersonales.filter(
      (antecedente) => !antecedente.respuesta || antecedente.respuesta.trim() === ''
    );

    if (descripcionesFaltantes.length > 0) {
      const listaDescripciones = descripcionesFaltantes
        .map((a) => `- ${a.descripcionAntecedentes}`)
        .join('\n');

      Swal.fire({
        title: 'Faltan datos',
        text: `Debes completar todas las respuestas antes de guardar:\n${listaDescripciones}`,
        icon: 'error',
        confirmButtonText: 'OK'
      });
      return;
    }

    Swal.fire({
      title: '¿Estás seguro?',
      text: 'Se guardarán los antecedentes personales del paciente.',
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: 'Sí, guardar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        const peticiones = this.antecedentesPersonales.map((antecedente) => {
          return this.antecedentesService.createAntecedentesHeredofamiliares({
            ...antecedente,
            curp: this.curp
          });
        });

        forkJoin(peticiones).subscribe({
          next: () => {
            Swal.fire({
              title: '¡Guardado!',
              text: 'Todos los antecedentes personales fueron guardados exitosamente.',
              icon: 'success',
              confirmButtonText: 'OK'
            });
          },
          error: (err) => {
            console.error(err);
            Swal.fire({
              title: 'Error',
              text: 'Ocurrió un error al guardar uno o más antecedentes.',
              icon: 'error',
              confirmButtonText: 'OK'
            });
          }
        });
      }
    });
  }


  addAntecedentesHeredofamiliares() {
    if (!this.curp || this.curp.trim() === '') {
      Swal.fire('Error', 'No se ha especificado la CURP del paciente.', 'error');
      return;
    }

    Swal.fire({
      title: '¿Estás seguro?',
      text: 'Se guardarán los antecedentes heredofamiliares del paciente.',
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: 'Sí, guardar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        const peticiones = this.antecedentesHeredofamiliaresList.map((antecedente) => {
          return this.antecedentesService.createAntecedentesHeredofamiliares({
            ...antecedente,
            curp: this.curp
          });
        });

        forkJoin(peticiones).subscribe({
          next: () => {
            Swal.fire({
              title: '¡Guardado!',
              text: 'Todos los antecedentes heredofamiliares fueron guardados exitosamente.',
              icon: 'success',
              confirmButtonText: 'OK'
            });
          },
          error: (err) => {
            console.error(err);
            Swal.fire({
              title: 'Error',
              text: 'Ocurrió un error al guardar uno o más antecedentes.',
              icon: 'error',
              confirmButtonText: 'OK'
            });
          }
        });
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
      Swal.fire('Error', 'No se ha especificado la CURP del paciente.', 'error');
      return;
    }

    this.nopatologicosService.existenAntecedentesPorCurp(this.curp).subscribe({
      next: (existe) => {
        if (existe) {
          Swal.fire({
            title: 'Atención',
            text: 'Ya existen antecedentes no patológicos registrados con esta CURP.',
            icon: 'warning',
            confirmButtonText: 'OK'
          });
        } else {
          Swal.fire({
            title: '¿Estás seguro?',
            text: 'Revise que todos los antecedentes no patológicos sean correctos.',
            icon: 'question',
            showCancelButton: true,
            confirmButtonText: 'Sí, guardar',
            cancelButtonText: 'Cancelar'
          }).then((result) => {
            if (result.isConfirmed) {
              let antecedentesNoPatologicos = new Nopatologicos(
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

              this.nopatologicosService.createAntecedentesnoPatologicos(antecedentesNoPatologicos).subscribe({
                next: () => {
                  Swal.fire({
                    title: '¡Guardado!',
                    text: 'Los antecedentes no patológicos fueron guardados exitosamente.',
                    icon: 'success',
                    confirmButtonText: 'OK'
                  });
                },
                error: (err) => {
                  console.error(err);
                  Swal.fire({
                    title: 'Error',
                    text: 'Ocurrió un error al guardar los antecedentes no patológicos.',
                    icon: 'error',
                    confirmButtonText: 'OK'
                  });
                }
              });
            }
          });
        }
      },
      error: (err) => {
        console.error(err);
        Swal.fire('Error', 'No se pudo verificar si existen antecedentes.', 'error');
      }
    });
  }

  addSignosVitales() {
    if (!this.curp || this.curp.trim() === '') {
      Swal.fire('Error', 'No se ha especificado la CURP del paciente.', 'error');
      return;
    }

    this.signosvitalesService.existenSignosVitalesPorCurp(this.curp).subscribe({
      next: (existe) => {
        if (existe) {
          Swal.fire({
            title: 'Atención',
            text: 'Ya existen signos vitales registrados con esta CURP.',
            icon: 'warning',
            confirmButtonText: 'OK'
          });
        } else {
          Swal.fire({
            title: '¿Estás seguro?',
            text: 'Se guardarán los signos vitales del paciente.',
            icon: 'question',
            showCancelButton: true,
            confirmButtonText: 'Sí, guardar',
            cancelButtonText: 'Cancelar'
          }).then((result) => {
            if (result.isConfirmed) {
              const signosVitales = {
                temperatura: this.temperatura,
                frecuenciaRespiratoria: this.frecuenciaRespiratoria,
                tensionArterial: this.tensionArterial,
                frecuenciaCardiaca: this.frecuenciaCardiaca,
                peso: this.peso,
                talla: this.talla,
                curp: this.curp
              };

              this.signosvitalesService.createSignosVitales(signosVitales).subscribe({
                next: () => {
                  Swal.fire({
                    title: '¡Guardado!',
                    text: 'Los signos vitales fueron guardados exitosamente.',
                    icon: 'success',
                    confirmButtonText: 'OK'
                  });
                },
                error: (err) => {
                  console.error(err);
                  Swal.fire({
                    title: 'Error',
                    text: 'Ocurrió un error al guardar los signos vitales.',
                    icon: 'error',
                    confirmButtonText: 'OK'
                  });
                }
              });
            }
          });
        }
      },
      error: (err) => {
        console.error(err);
        Swal.fire('Error', 'No se pudo verificar si existen signos vitales.', 'error');
      }
    });
  }


  addCabezaCuello() {
    if (!this.curp || this.curp.trim() === '') {
      Swal.fire('Error', 'No se ha especificado la CURP del paciente.', 'error');
      return;
    }

    this.cabezacuelloService.existenCabezaCuelloPorCurp(this.curp).subscribe({
      next: (existe) => {
        if (existe) {
          Swal.fire({
            icon: 'warning',
            title: 'Atención',
            text: 'Ya existen registros de Cabeza y Cuello para esta CURP.',
          });
        } else {
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

          console.log(cabezaCuello);

          this.cabezacuelloService.createExploracionCabezaCuello(cabezaCuello).subscribe({
            next: () => {
              Swal.fire({
                icon: 'success',
                title: '¡Guardado!',
                text: 'Los datos de Cabeza y Cuello fueron guardados exitosamente.',
              });
            },
            error: (err) => {
              console.error(err);
              Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Ocurrió un error al guardar los datos de Cabeza y Cuello.',
              });
            }
          });
        }
      },
      error: (err) => {
        console.error(err);
        Swal.fire({
          icon: 'error',
          title: 'Error',
          text: 'No se pudo verificar si ya existen registros para la CURP.',
        });
      }
    });
  }


  addEstomatognatico() {
    if (!this.curp || this.curp.trim() === '') {
      Swal.fire('Error', 'No se ha especificado la CURP del paciente.', 'error');
      return;
    }

    this.estomatognaticoService.existenEstomatognaticoCurp(this.curp).subscribe({
      next: (existe) => {
        if (existe) {
          Swal.fire({
            icon: 'warning',
            title: 'Atención',
            text: 'Ya existen registros del sistema estomatognático para esta CURP.',
          });
        } else {
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

          console.log(estomatognatico);

          this.estomatognaticoService.createEstomatognatico(estomatognatico).subscribe({
            next: () => {
              Swal.fire({
                icon: 'success',
                title: '¡Guardado!',
                text: 'Los datos del sistema estomatognático fueron guardados exitosamente.',
              });
            },
            error: (err) => {
              console.error(err);
              Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Ocurrió un error al guardar los datos del sistema estomatognático.',
              });
            }
          });
        }
      },
      error: (err) => {
        console.error(err);
        Swal.fire({
          icon: 'error',
          title: 'Error',
          text: 'No se pudo verificar si ya existen registros del sistema estomatognático para la CURP.',
        });
      }
    });
  }

  addTejidosBlandos() {
    if (!this.curp || this.curp.trim() === '') {
      Swal.fire('Error', 'No se ha especificado la CURP del paciente.', 'error');
      return;
    }

    this.tejidosblandosService.existenTejidosBlandosPorCurp(this.curp).subscribe({
      next: (existe) => {
        if (existe) {
          Swal.fire({
            icon: 'warning',
            title: 'Atención',
            text: 'Ya existen registros de tejidos blandos para esta CURP.',
          });
        } else {
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

          console.log(tejidosBlandos);

          this.tejidosblandosService.createTejidosBlandos(tejidosBlandos).subscribe({
            next: () => {
              Swal.fire({
                icon: 'success',
                title: '¡Guardado!',
                text: 'Los datos de tejidos blandos fueron guardados exitosamente.',
              });
            },
            error: (err) => {
              console.error(err);
              Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Ocurrió un error al guardar los datos de tejidos blandos.',
              });
            }
          });
        }
      },
      error: (err) => {
        console.error(err);
        Swal.fire({
          icon: 'error',
          title: 'Error',
          text: 'No se pudo verificar si ya existen registros de tejidos blandos para la CURP.',
        });
      }
    });
  }

  addTutor() {
    if (!this.curp || this.curp.trim() === '') {
      Swal.fire('Error', 'No se ha especificado la CURP del paciente.', 'error');
      return;
    }


    this.tutorService.existeTutorPorCurp(this.curp).subscribe({
      next: (existe) => {
        if (existe) {
          Swal.fire({
            icon: 'warning',
            title: 'Atención',
            text: 'Ya existen registros de tutor para esta CURP.',
          });
        } else {
          const tutor = new Tutor(
            this.nombreTutor,
            this.edadTutor,
            this.domicilioTutor,
            this.telefonoCasaTutor,
            this.celularTutor,
            this.curp
          );

          this.tutorService.createTutor(tutor).subscribe({
            next: () => {
              Swal.fire({
                icon: 'success',
                title: '¡Guardado!',
                text: 'Los datos del tutor fueron guardados exitosamente.',
              });
            },
            error: (err) => {
              console.error(err);
              Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Ocurrió un error al guardar los datos del tutor.',
              });
            }
          });
        }
      },
      error: (err) => {
        console.error(err);
        Swal.fire({
          icon: 'error',
          title: 'Error',
          text: 'No se pudo verificar si ya existen registros del tutor para la CURP.',
        });
      }
    });
  }


  addDiagnosticoTratamiento() {
    if (!this.curp || this.curp.trim() === '') {
      Swal.fire('Error', 'No se ha especificado la CURP del paciente.', 'error');
      return;
    }


    this.diagnosticotratamientoService.existenDiagnosticoTratamientoCurp(this.curp).subscribe({
      next: (existe) => {
        if (existe) {
          Swal.fire({
            icon: 'warning',
            title: 'Atención',
            text: 'Ya existen registros de diagnostico para esta CURP.',
          });
        } else {
          const diagnostico = new Diagnosticotratamiento(
            this.interpretacionRx,
            this.diagnostico,
            this.resumenTratamiento,
            this.curp
          );

          this.diagnosticotratamientoService.createDiagnosticoTratamiento(diagnostico).subscribe({
            next: () => {
              Swal.fire({
                icon: 'success',
                title: '¡Guardado!',
                text: 'Los datos del tutor fueron guardados exitosamente.',
              });
            },
            error: (err) => {
              console.error(err);
              Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Ocurrió un error al guardar los datos del tutor.',
              });
            }
          });
        }
      },
      error: (err) => {
        console.error(err);
        Swal.fire({
          icon: 'error',
          title: 'Error',
          text: 'No se pudo verificar si ya existen registros del tutor para la CURP.',
        });
      }
    });
  }

addEvolucion() {
    if (!this.curp || this.curp.trim() === '') {
      Swal.fire('Error', 'No se ha especificado la CURP del paciente.', 'error');
      return;
    }


    this.evolucionService.existenEvolucionPorCurp(this.curp).subscribe({
      next: (existe) => {
        if (existe) {
          Swal.fire({
            icon: 'warning',
            title: 'Atención',
            text: 'Ya existen registros de evolucion y diagnostico para esta CURP.',
          });
        } else {
          const evolucion = new Evolucion(
            this.fecha,
            this.comentarioControl,
            this.curp,
            
          );

          this.evolucionService.createEvolucion(evolucion).subscribe({
            next: () => {
              Swal.fire({
                icon: 'success',
                title: '¡Guardado!',
                text: 'Los datos del diagnostico y tratamiento fueron guardados exitosamente.',
              });
            },
            error: (err) => {
              console.error(err);
              Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Ocurrió un error al guardar los datos del diagnostico y tratamiento.',
              });
            }
          });
        }
      },
      error: (err) => {
        console.error(err);
        Swal.fire({
          icon: 'error',
          title: 'Error',
          text: 'No se pudo verificar si ya existen registros del diagnostico y tratamiento para la CURP.',
        });
      }
    });
  }
 

}
