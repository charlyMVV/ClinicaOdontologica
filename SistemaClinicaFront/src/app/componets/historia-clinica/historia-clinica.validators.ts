type RegistroLibre = any;

interface HistoriaClinicaValidationState extends RegistroLibre {
  antecedentesHeredofamiliaresList: RegistroLibre[];
  antecedentesPersonales: RegistroLibre[];
  cabezaCuello: RegistroLibre;
  estomatognatico: RegistroLibre;
  listaFotos: RegistroLibre[];
  previews: string[];
  fotosGuardadas: boolean;
  firmaPendiente: string | null;
  signaturePad?: {
    isEmpty(): boolean;
  };
}

function campoVacio(valor: any): boolean {
  return valor === undefined || valor === null || valor.toString().trim() === '';
}

export function obtenerFaltantesDatosPaciente(state: RegistroLibre): string[] {
  const faltantes: string[] = [];

  if (campoVacio(state.nombrePaciente)) faltantes.push('Nombre completo del paciente');
  if (campoVacio(state.curp)) faltantes.push('CURP');
  if (campoVacio(state.sexo)) faltantes.push('Sexo');
  if (campoVacio(state.edad)) faltantes.push('Edad');
  if (campoVacio(state.fechaNacimiento)) faltantes.push('Fecha de nacimiento');
  if (campoVacio(state.domicilio)) faltantes.push('Domicilio completo');
  if (campoVacio(state.telefonoCasa)) faltantes.push('Teléfono de casa/trabajo');
  if (campoVacio(state.telefonoCelular)) faltantes.push('Teléfono celular');
  if (campoVacio(state.religion)) faltantes.push('Religión');
  if (campoVacio(state.ocupacion)) faltantes.push('Ocupación');
  if (campoVacio(state.escolaridad)) faltantes.push('Escolaridad');
  if (campoVacio(state.estadoCivil)) faltantes.push('Estado civil');
  if (campoVacio(state.derechohabiente)) faltantes.push('Derechohabiente');
  if (campoVacio(state.medicoFamiliar)) faltantes.push('Nombre del médico familiar');
  if (campoVacio(state.ultimaConsulta)) faltantes.push('Fecha y motivo de la última consulta');

  return faltantes;
}

export function esCurpValida(curp: string): boolean {
  if (!curp) {
    return false;
  }

  const curpNormalizada = curp.toUpperCase().trim();
  return /^[A-Z]{4}\d{6}[HM][A-Z]{5}[A-Z0-9]\d$/.test(curpNormalizada);
}

export function esTelefonoValido(telefono: string): boolean {
  return /^\d{10}$/.test((telefono || '').replace(/\D/g, ''));
}

export function esEdadValida(edad: string | number): boolean {
  if (edad === null || edad === undefined || String(edad).trim() === '') {
    return false;
  }

  const valor = Number(String(edad).trim());
  return Number.isInteger(valor) && valor >= 0 && valor <= 120;
}

export function esFechaLocalDateValida(fecha: string): boolean {
  if (!fecha || typeof fecha !== 'string') {
    return false;
  }

  if (!/^\d{4}-\d{2}-\d{2}$/.test(fecha)) {
    return false;
  }

  const parsed = new Date(`${fecha}T00:00:00`);
  if (Number.isNaN(parsed.getTime())) {
    return false;
  }

  const [year, month, day] = fecha.split('-').map(Number);
  return (
    parsed.getFullYear() === year &&
    parsed.getMonth() + 1 === month &&
    parsed.getDate() === day
  );
}

export function obtenerAntecedentesSinRespuesta(lista: RegistroLibre[]): any[] {
  return lista.filter((antecedente) => campoVacio(antecedente.respuesta));
}

export function obtenerAntecedentesSinDetalle(lista: RegistroLibre[]): any[] {
  return lista.filter(
    (antecedente) =>
      antecedente.respuesta === 'Sí' &&
      campoVacio(antecedente.detalle)
  );
}

export function obtenerFaltantesAntecedentesNoPatologicos(state: RegistroLibre): string[] {
  const faltantes: string[] = [];

  if (campoVacio(state.frecuenciaLavadoDientes)) faltantes.push('Frecuencia de lavado de dientes');
  if (campoVacio(state.usaAuxiliaresHigiene)) faltantes.push('Uso de auxiliares de higiene');
  if (campoVacio(state.grupoSanguineo)) faltantes.push('Grupo sanguíneo');
  if (campoVacio(state.factorRh)) faltantes.push('Factor RH');
  if (campoVacio(state.cartillaVacunacion)) faltantes.push('Cartilla de vacunación');
  if (campoVacio(state.esquemaCompleto)) faltantes.push('Esquema completo');
  if (campoVacio(state.antecedentesAlergicos)) faltantes.push('Antecedentes alérgicos');
  if (campoVacio(state.golosinas)) faltantes.push('Consumo de golosinas');
  if (campoVacio(state.tieneAdicciones)) faltantes.push('Adicciones');
  if (campoVacio(state.haSidoHospitalizado)) faltantes.push('Hospitalización');
  if (campoVacio(state.haSidoAnestesiado)) faltantes.push('Anestesia');
  if (campoVacio(state.haRecibidoTransfusion)) faltantes.push('Transfusiones');
  if (campoVacio(state.haRecibidoPerforaciones)) faltantes.push('Tatuajes o perforaciones');
  if (campoVacio(state.tieneIntervenciones)) faltantes.push('Intervenciones quirúrgicas');
  if (campoVacio(state.consumeMedicamento)) faltantes.push('Consume medicamento');
  if (campoVacio(state.discapacidad)) faltantes.push('Discapacidad');
  if (campoVacio(state.embarazo)) faltantes.push('Embarazo');

  return faltantes;
}

export function obtenerFaltantesSignosVitales(state: RegistroLibre): string[] {
  const campos = [
    { valor: state.temperatura, nombre: 'Temperatura' },
    { valor: state.frecuenciaRespiratoria, nombre: 'Frecuencia respiratoria' },
    { valor: state.tensionArterial, nombre: 'Tensión arterial' },
    { valor: state.frecuenciaCardiaca, nombre: 'Frecuencia cardíaca' },
    { valor: state.peso, nombre: 'Peso' },
    { valor: state.talla, nombre: 'Talla' }
  ];

  return campos.filter((campo) => campoVacio(campo.valor)).map((campo) => campo.nombre);
}

export function obtenerFaltantesCabezaCuello(state: RegistroLibre): string[] {
  const faltantes: string[] = [];

  const cabezaSeleccionada = state.cabezaCuello?.exostosis || state.cabezaCuello?.endotosis;
  const craneoSeleccionado =
    state.cabezaCuello?.dolicocefalico ||
    state.cabezaCuello?.mesocefalico ||
    state.cabezaCuello?.branquicefalico;
  const caraSeleccionada =
    state.cabezaCuello?.asimetriaTransversal ||
    state.cabezaCuello?.asimetriaLongitudinal;
  const perfilSeleccionado =
    state.cabezaCuello?.perfilConcavo ||
    state.cabezaCuello?.perfilConvexo ||
    state.cabezaCuello?.perfilRecto;
  const pielSeleccionada =
    state.cabezaCuello?.pielNormal ||
    state.cabezaCuello?.pielPalida ||
    state.cabezaCuello?.pielCianotica ||
    state.cabezaCuello?.pielEnrojecida;
  const musculosSeleccionado =
    state.cabezaCuello?.musculosHipotonicos ||
    state.cabezaCuello?.musculosHipertonicos ||
    state.cabezaCuello?.musculosEspasticos;

  if (!cabezaSeleccionada) faltantes.push('Cabeza');
  if (!craneoSeleccionado) faltantes.push('Cráneo');
  if (!caraSeleccionada) faltantes.push('Cara / Asimetrías');
  if (!perfilSeleccionado) faltantes.push('Perfil');
  if (!pielSeleccionada) faltantes.push('Piel');
  if (!musculosSeleccionado) faltantes.push('Músculos');

  return faltantes;
}

export function tieneSeleccionEstomatognatico(state: RegistroLibre): boolean {
  return !!(
    state.estomatognatico?.ruidos ||
    state.estomatognatico?.lateralidad ||
    state.estomatognatico?.apertura ||
    state.estomatognatico?.chasquidos ||
    state.estomatognatico?.crepitacion ||
    state.estomatognatico?.dificultadAbrirboca ||
    state.estomatognatico?.dolorAberturaLateralidad ||
    state.estomatognatico?.fatigaDolorMuscular ||
    state.estomatognatico?.disminuicionAbertura ||
    state.estomatognatico?.desviacionAberturaCierre
  );
}

export function obtenerFaltantesTejidosBlandos(state: RegistroLibre): Array<{ nombre: string }> {
  const campos = [
    { valor: state.ganglios, nombre: 'Ganglios' },
    { valor: state.glandulasSalivales, nombre: 'Glándulas salivales' },
    { valor: state.labioExterno, nombre: 'Labio externo' },
    { valor: state.bordeBermellon, nombre: 'Borde bermellón' },
    { valor: state.labioInterno, nombre: 'Labio interno' },
    { valor: state.comisuras, nombre: 'Comisuras' },
    { valor: state.carrillos, nombre: 'Carrillos' },
    { valor: state.fondoDeSaco, nombre: 'Fondo de saco' },
    { valor: state.frenillos, nombre: 'Frenillos' },
    { valor: state.lenguaTercioMedio, nombre: 'Lengua tercio medio' },
    { valor: state.paladarDuro, nombre: 'Paladar duro' },
    { valor: state.paladarBlando, nombre: 'Paladar blando' },
    { valor: state.istmoBucofaringe, nombre: 'Istmo bucofaríngeo' },
    { valor: state.lenguaDorso, nombre: 'Lengua dorso' },
    { valor: state.lenguaBordes, nombre: 'Lengua bordes' },
    { valor: state.lenguaVentral, nombre: 'Lengua ventral' },
    { valor: state.pisoBoca, nombre: 'Piso de la boca' },
    { valor: state.dientes, nombre: 'Dientes' },
    { valor: state.mucosaAlveolar, nombre: 'Mucosa del borde alveolar' },
    { valor: state.encia, nombre: 'Encía' }
  ];

  return campos.filter((campo) => campoVacio(campo.valor)).map((campo) => ({ nombre: campo.nombre }));
}

export function obtenerFaltantesTutor(state: RegistroLibre): string[] {
  const faltantes: string[] = [];

  if (campoVacio(state.nombreTutor)) faltantes.push('Nombre del tutor');
  if (campoVacio(state.edadTutor)) faltantes.push('Edad del tutor');
  if (campoVacio(state.domicilioTutor)) faltantes.push('Domicilio completo');
  if (campoVacio(state.telefonoCasaTutor)) faltantes.push('Teléfono de casa o trabajo');
  if (campoVacio(state.celularTutor)) faltantes.push('Teléfono celular');

  return faltantes;
}

export function obtenerFaltantesDiagnosticoTratamiento(state: RegistroLibre): string[] {
  const faltantes: string[] = [];

  if (campoVacio(state.interpretacionRx)) faltantes.push('Interpretación RX');
  if (campoVacio(state.diagnostico)) faltantes.push('Diagnóstico');
  if (campoVacio(state.resumenTratamiento)) faltantes.push('Resumen del tratamiento');

  return faltantes;
}

export function obtenerFaltantesEvolucion(state: RegistroLibre): string[] {
  const faltantes: string[] = [];

  if (campoVacio(state.fecha)) faltantes.push('Fecha');
  if (campoVacio(state.comentarioControl)) faltantes.push('Evolución del paciente');

  return faltantes;
}

export function obtenerFaltantesFotosInicio(state: RegistroLibre): string[] {
  const faltantes: string[] = [];

  if (campoVacio(state.curp)) faltantes.push('CURP');
  if (!state.listaFotos || state.listaFotos.length === 0) faltantes.push('Fotografías');

  return faltantes;
}

function tieneFotos(state: any): boolean {
  return !!(state.fotosGuardadas || state.listaFotos?.length > 0 || state.previews?.length > 0);
}

function tieneFirma(state: any): boolean {
  if (state.firmaPendiente) {
    return true;
  }

  if (state.signaturePad && !state.signaturePad.isEmpty()) {
    return true;
  }

  return false;
}

export function obtenerFaltantesHistoriaCompletaFront(state: any): string[] {
  const faltantes: string[] = [];

  if (campoVacio(state.curp)) {
    faltantes.push('Datos del paciente');
  }

  if (!state.antecedentesPersonales?.length || !state.antecedentesHeredofamiliaresList?.length) {
    faltantes.push('Antecedentes personales y heredofamiliares');
  }

  if (campoVacio(state.frecuenciaLavadoDientes) || campoVacio(state.grupoSanguineo) || campoVacio(state.factorRh)) {
    faltantes.push('Antecedentes no patológicos');
  }

  if (
    campoVacio(state.temperatura) ||
    campoVacio(state.frecuenciaRespiratoria) ||
    campoVacio(state.tensionArterial) ||
    campoVacio(state.frecuenciaCardiaca) ||
    campoVacio(state.peso) ||
    campoVacio(state.talla)
  ) {
    faltantes.push('Signos vitales');
  }

  const cabezaCuello = obtenerFaltantesCabezaCuello(state);
  if (cabezaCuello.length > 0) {
    faltantes.push('Cabeza y cuello');
  }

  if (!state.ganglios || !state.glandulasSalivales) {
    faltantes.push('Tejidos blandos');
  }

  if (campoVacio(state.diagnostico) || campoVacio(state.resumenTratamiento)) {
    faltantes.push('Diagnóstico y tratamiento');
  }

  if (!tieneFotos(state)) {
    faltantes.push('Fotografías');
  }

  if (!tieneFirma(state)) {
    faltantes.push('Firma');
  }

  return faltantes;
}




