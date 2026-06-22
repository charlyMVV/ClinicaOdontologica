package com.Clinica.SistemaClinicaBack.pdf.sections;

import com.Clinica.SistemaClinicaBack.entity.Antecedentes;
import com.Clinica.SistemaClinicaBack.entity.AntecedentesNoPatologicos;
import com.Clinica.SistemaClinicaBack.entity.HistoriaClinica;
import com.Clinica.SistemaClinicaBack.pdf.PdfCellFactory;
import com.Clinica.SistemaClinicaBack.pdf.PdfSeccion;
import com.Clinica.SistemaClinicaBack.pdf.PdfTableBuilder;
import com.Clinica.SistemaClinicaBack.repository.AntecedentesRepository;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Order(3)
@RequiredArgsConstructor
public class AntecedentesSection implements PdfSeccion {

    private final AntecedentesRepository antecedentesRepository;

    @Override
    public void agregar(Document document, HistoriaClinica hc) throws Exception {
        List<Antecedentes> antecedentes =
                antecedentesRepository.findByHistoriaClinica_IdHistoriaClinica(hc.getIdHistoriaClinica());

        agregarTablaPatologicos(document, antecedentes,
                "ANTECEDENTES PATOLÓGICOS HEREDO FAMILIARES",
                "PATOLOGICOS HEREDOFAMILIARES");

        document.add(new Paragraph(" "));

        agregarTablaPatologicos(document, antecedentes,
                "ANTECEDENTES PERSONALES PATOLÓGICOS",
                "PERSONALES PATOLOGICOS");

        document.add(new Paragraph(" "));

        agregarNoPatologicos(document, hc.getAntecedentesNoPatologicos());
    }

    // ── Patológicos ──────────────────────────────────────────────────────────

    private void agregarTablaPatologicos(Document document,
                                         List<Antecedentes> antecedentes,
                                         String titulo,
                                         String tipo) throws Exception {
        PdfCellFactory.agregarTituloSeccion(document, titulo);

        PdfPTable tabla = new PdfPTable(4);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[]{45, 12, 12, 31});
        tabla.setSpacingAfter(10);

        PdfTableBuilder.of(tabla)
                .encabezados("Antecedente", "Sí", "No", "Detalle");

        antecedentes.stream()
                .filter(a -> tipo.equalsIgnoreCase(a.getTipoAntecedentes()))
                .forEach(a -> {
                    PdfCellFactory.agregarTexto(tabla, PdfCellFactory.sanitizar(a.getDescripcionAntecedentes()), Element.ALIGN_LEFT);
                    PdfCellFactory.agregarTexto(tabla, PdfCellFactory.esSi(a.getRespuesta()) ? "X" : "", Element.ALIGN_CENTER);
                    PdfCellFactory.agregarTexto(tabla, PdfCellFactory.esNo(a.getRespuesta())  ? "X" : "", Element.ALIGN_CENTER);
                    PdfCellFactory.agregarTexto(tabla, PdfCellFactory.sanitizar(a.getDetalle()), Element.ALIGN_LEFT);
                });

        document.add(tabla);
    }

    // ── No Patológicos ───────────────────────────────────────────────────────

    private void agregarNoPatologicos(Document document, AntecedentesNoPatologicos anp) throws Exception {
        PdfCellFactory.agregarTituloSeccion(document, "ANTECEDENTES PERSONALES NO PATOLÓGICOS");

        if (anp == null) {
            document.add(new Paragraph("No hay antecedentes personales no patológicos registrados."));
            return;
        }

        PdfPTable tabla = new PdfPTable(4);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[]{25, 25, 25, 25});
        tabla.setSpacingBefore(8f);
        tabla.setSpacingAfter(10f);

        PdfTableBuilder b = PdfTableBuilder.of(tabla);

        b.subtitulo("Higiene bucal", 4)
                .fila("Frecuencia de lavado", anp.getFrecuenciaLavadoDientes(), "Usa auxiliares", anp.getUsaAuxiliaresHigiene())
                .filaCompleta("Tipos de auxiliares", anp.getTiposAuxiliaresHigiene())
                .fila("Consume golosinas", anp.getGolosinas(), "Padecimiento actual", anp.getPadecimientoActual());

        b.subtitulo("Grupo sanguíneo y vacunación", 4)
                .fila("Grupo sanguíneo", anp.getGrupoSanguineo(), "Factor RH", anp.getFactorRh())
                .fila("Cartilla vacunación", anp.getCartillaVacunacion(), "Esquema completo", anp.getEsquemaCompleto())
                .filaCompleta("Vacunas faltantes", anp.getVacunasFaltantes());

        b.subtitulo("Antecedentes alérgicos", 4)
                .fila("Tiene alergias", anp.getAntecedentesAlergicos(), "Cuál alergia", anp.getCualAlergicos())
                .fila("Antibióticos", anp.getAntibioticos(), "Analgésicos", anp.getAnalgesicos())
                .fila("Anestésicos", anp.getAnestesicos(), "Alimentos", anp.getAlimentos())
                .filaCompleta("Otras alergias", anp.getOtrasAlergias());

        b.subtitulo("Adicciones", 4)
                .fila("Tiene adicciones", anp.getTieneAdicciones(), "Tabaco", anp.getTabaco())
                .fila("Alcohol", anp.getAlcohol(), "Otras adicciones", anp.getOtrasAdicciones());

        b.subtitulo("Hospitalización y procedimientos", 4)
                .fila("Ha sido hospitalizado", anp.getHaSidoHospitalizado(), "Fecha hospitalización", fecha(anp.getFechaHospitalizacion()))
                .filaCompleta("Motivo hospitalización", anp.getMotivoHospitalizacion())
                .fila("Ha sido anestesiado", anp.getHaSidoAnestesiado(), "Ha recibido transfusión", anp.getHaRecibidoTransfusion())
                .fila("Tatuajes/perforaciones", anp.getHaRecibidoPerforaciones(), "Consume medicamento", anp.getConsumeMedicamento())
                .fila("Embarazo", anp.getEmbarazo(), "Discapacidad", anp.getDiscapacidad())
                .fila("Intervenciones quirúrgicas", anp.getTieneIntervenciones(), "Parte del cuerpo", anp.getParteCuerpo());

        document.add(tabla);
    }

    private String fecha(LocalDate fecha) {
        if (fecha == null) return "";
        return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}