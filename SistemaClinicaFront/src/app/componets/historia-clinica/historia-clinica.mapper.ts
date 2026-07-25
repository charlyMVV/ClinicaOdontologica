import { Fotosinicio } from '../../fotosinicio';

type RegistroLibre = any;

interface HistoriaClinicaMappingState extends RegistroLibre {
  curp: string;
  pacienteGuardado: boolean;
  cabezaCuello: RegistroLibre;
  estomatognatico: RegistroLibre;
  antecedentesHeredofamiliaresList: RegistroLibre[];
  antecedentesPersonales: RegistroLibre[];
  listaFotos: Fotosinicio[];
  previews: string[];
  firmaPendiente: string | null;
}

const CAMPOS_DATOS_PACIENTE = [
  'nombrePaciente', 'curp', 'sexo', 'edad', 'fechaNacimiento', 'domicilio', 'telefonoCasa',
  'telefonoCelular', 'religion', 'ocupacion', 'escolaridad', 'estadoCivil', 'derechohabiente',
  'medicoFamiliar', 'numeroMedico', 'ultimaConsulta'
];

const CAMPOS_ANTECEDENTES_NO_PATOLOGICOS = [
  'frecuenciaLavadoDientes', 'usaAuxiliaresHigiene', 'tiposAuxiliaresHigiene', 'grupoSanguineo',
  'factorRh', 'cartillaVacunacion', 'esquemaCompleto', 'vacunasFaltantes', 'antecedentesAlergicos',
  'golosinas', 'cualAlergicos', 'antibioticos', 'analgesicos', 'anestesicos', 'alimentos',
  'otrasAlergias', 'tieneAdicciones', 'tabaco', 'alcohol', 'otrasAdicciones', 'haSidoHospitalizado',
  'fechaHospitalizacion', 'motivoHospitalizacion', 'padecimientoActual', 'haSidoAnestesiado',
  'haRecibidoTransfusion', 'haRecibidoPerforaciones', 'consumeMedicamento', 'embarazo',
  'discapacidad', 'tieneIntervenciones', 'parteCuerpo'
];

const CAMPOS_SIGNOS_VITALES = [
  'temperatura', 'frecuenciaRespiratoria', 'tensionArterial', 'frecuenciaCardiaca', 'peso', 'talla'
];

const CAMPOS_TEJIDOS_BLANDOS = [
  'ganglios', 'glandulasSalivales', 'labioExterno', 'bordeBermellon', 'labioInterno', 'comisuras',
  'carrillos', 'fondoDeSaco', 'frenillos', 'lenguaTercioMedio', 'paladarDuro', 'paladarBlando',
  'istmoBucofaringe', 'lenguaDorso', 'lenguaBordes', 'lenguaVentral', 'pisoBoca', 'dientes',
  'mucosaAlveolar', 'encia'
];

const CAMPOS_TUTOR = [
  'nombreTutor', 'edadTutor', 'domicilioTutor', 'telefonoCasaTutor', 'celularTutor'
];

const CAMPOS_DIAGNOSTICO_TRATAMIENTO = [
  'interpretacionRx', 'diagnostico', 'resumenTratamiento'
];

const CAMPOS_EVOLUCION = ['fecha', 'comentarioControl'];

export function normalizarTexto(texto: string): string {
  return (texto || '')
    .normalize('NFD')
    .replace(/[\u0300-\u036f]/g, '')
    .toLowerCase()
    .trim();
}

export function aplicarCampos(
  destino: RegistroLibre,
  origen: RegistroLibre | null | undefined,
  campos: string[]
): void {
  if (!origen) {
    return;
  }

  campos.forEach((campo) => {
    if (origen[campo] !== undefined && origen[campo] !== null) {
      destino[campo] = origen[campo];
    }
  });
}

export function aplicarListaAntecedentes(
  listaFormulario: RegistroLibre[],
  listaBackend: RegistroLibre[],
  curp: string
): void {
  listaFormulario.forEach((antecedenteFormulario, index) => {
    const descripcion = normalizarTexto(antecedenteFormulario.descripcionAntecedentes);
    const antecedenteBackend = listaBackend.find((antecedente) =>
      normalizarTexto(antecedente?.descripcionAntecedentes) === descripcion
    ) || listaBackend[index];

    if (antecedenteBackend) {
      antecedenteFormulario.respuesta = antecedenteBackend.respuesta || '';
      antecedenteFormulario.detalle = antecedenteBackend.detalle || '';
      antecedenteFormulario.curp = antecedenteBackend.curp || curp;
    }
  });
}

export function aplicarDatosPaciente(
  state: any,
  paciente: RegistroLibre | null | undefined
): void {
  if (!paciente) {
    return;
  }

  aplicarCampos(state, paciente, CAMPOS_DATOS_PACIENTE);
}

export function aplicarAntecedentes(
  state: any,
  antecedentes: RegistroLibre[] | null | undefined
): void {
  const listaAntecedentes = antecedentes || [];

  const heredofamiliares = listaAntecedentes.filter((antecedente) =>
    normalizarTexto(antecedente?.tipoAntecedentes).includes('heredofamiliares')
  );

  const personales = listaAntecedentes.filter((antecedente) =>
    normalizarTexto(antecedente?.tipoAntecedentes).includes('personales patologicos')
  );

  aplicarListaAntecedentes(state.antecedentesHeredofamiliaresList, heredofamiliares, state.curp);
  aplicarListaAntecedentes(state.antecedentesPersonales, personales, state.curp);
}

export function aplicarFotos(
  state: any,
  fotos: RegistroLibre[] | null | undefined
): void {
  const listaFotos = (fotos || []).map((foto) => new Fotosinicio(foto.fotos || '', foto.curp || state.curp));

  state.listaFotos = listaFotos;
  state.previews = listaFotos.map((foto) => foto.fotos).filter((foto) => !!foto);
}

export function aplicarFirma(
  state: any,
  firma: RegistroLibre | null | undefined
): void {
  if (!firma?.firma) {
    return;
  }

  state.firmaPendiente = firma.firma;
}

export function aplicarHistoriaClinica(
  state: any,
  historia: RegistroLibre | null | undefined
): void {
  state.curp = historia?.curp || '';
  state.pacienteGuardado = !!state.curp;

  aplicarDatosPaciente(state, historia?.paciente);
  aplicarAntecedentes(state, historia?.antecedentes || []);
  aplicarCampos(state, historia?.antecedentesNoPatologicos, CAMPOS_ANTECEDENTES_NO_PATOLOGICOS);
  aplicarCampos(state, historia?.signosVitales, CAMPOS_SIGNOS_VITALES);

  if (historia?.cabezaCuello) {
    state.cabezaCuello = {
      ...state.cabezaCuello,
      ...historia.cabezaCuello
    };
  }

  if (historia?.estomatognatico) {
    state.estomatognatico = {
      ...state.estomatognatico,
      ...historia.estomatognatico
    };
  }

  aplicarCampos(state, historia?.tejidosBlandos, CAMPOS_TEJIDOS_BLANDOS);
  aplicarCampos(state, historia?.tutor, CAMPOS_TUTOR);
  aplicarCampos(state, historia?.diagnosticoTratamiento, CAMPOS_DIAGNOSTICO_TRATAMIENTO);

  const evoluciones = historia?.evolucionPaciente || [];
  if (evoluciones.length > 0) {
    const ultimaEvolucion = evoluciones[evoluciones.length - 1];
    aplicarCampos(state, ultimaEvolucion, CAMPOS_EVOLUCION);
  }

  aplicarFotos(state, historia?.fotosInicio || []);
  aplicarFirma(state, historia?.firma);
}

