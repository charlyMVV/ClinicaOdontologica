package com.Clinica.SistemaClinicaBack.pdf.sections;

import com.Clinica.SistemaClinicaBack.entity.*;
import com.Clinica.SistemaClinicaBack.pdf.PdfCellFactory;
import com.Clinica.SistemaClinicaBack.pdf.PdfFuenteLoader;
import com.Clinica.SistemaClinicaBack.pdf.PdfSeccion;
import com.Clinica.SistemaClinicaBack.pdf.PdfTableBuilder;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
@RequiredArgsConstructor
public class ExploracionSection implements PdfSeccion {

    private final PdfFuenteLoader fuenteLoader;

    @Override
    public void agregar(Document document, HistoriaClinica hc) throws Exception {
        agregarSignosVitales(document, hc.getSignosVitales());
        agregarCabezaCuello(document, hc.getCabezaCuello());
        agregarEstomatognatico(document, hc.getExploracionEstomatognatico());
        agregarTejidosBlandos(document, hc.getTejidosBlandos());
    }

    // ── Signos vitales ───────────────────────────────────────────────────────

    private void agregarSignosVitales(Document document, SignosVitales sv) throws Exception {
        PdfCellFactory.agregarTituloSeccion(document, "SIGNOS VITALES");

        if (sv == null) {
            document.add(new Paragraph("No hay signos vitales registrados."));
            return;
        }

        PdfPTable tabla = new PdfPTable(4);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[]{25, 25, 25, 25});

        PdfTableBuilder.of(tabla)
                .fila("Temperatura",    PdfCellFactory.sanitizar(sv.getTemperatura()) + " °C",
                        "Frec. respiratoria", PdfCellFactory.sanitizar(sv.getFrecuenciaRespiratoria()) + " rpm")
                .fila("Tensión arterial", sv.getTensionArterial(),
                        "Frec. cardiaca", PdfCellFactory.sanitizar(sv.getFrecuenciaCardiaca()) + " lpm")
                .fila("Peso", PdfCellFactory.sanitizar(sv.getPeso()) + " kg",
                        "Talla", PdfCellFactory.sanitizar(sv.getTalla()) + " cm");

        document.add(tabla);
        document.add(new Paragraph(" "));
    }

    // ── Cabeza y cuello ──────────────────────────────────────────────────────

    private void agregarCabezaCuello(Document document, CabezaCuello cc) throws Exception {
        PdfCellFactory.agregarTituloSeccion(document, "EXPLORACIÓN DE CABEZA Y CUELLO");

        if (cc == null) {
            document.add(new Paragraph("No hay exploración de cabeza y cuello registrada."));
            return;
        }

        PdfPTable tabla = new PdfPTable(4);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[]{42, 8, 42, 8});
        tabla.setSpacingBefore(8f);
        tabla.setSpacingAfter(10f);

        var f = fuenteLoader.getCheckFont();
        PdfTableBuilder.of(tabla)
                .subtitulo("CABEZA", 4)
                .filaCheck("EXOSTOSIS", cc.isExostosis(), "ENDOSTOSIS", cc.isEndotosis(), f)

                .subtitulo("CRÁNEO", 4)
                .filaCheck("DOLICOCEFÁLICO", cc.isDolicocefalico(), "MESOCEFÁLICO", cc.isMesocefalico(), f)
                .filaCheck("BRAQUICEFÁLICO", cc.isBranquicefalico(), "", false, f)

                .subtitulo("CARA - ASIMETRÍAS", 4)
                .filaCheck("TRANSVERSALES", cc.isAsimetriaTransversal(), "LONGITUDINALES", cc.isAsimetriaLongitudinal(), f)

                .subtitulo("PERFIL", 4)
                .filaCheck("CÓNCAVO", cc.isPerfilConcavo(), "CONVEXO", cc.isPerfilConvexo(), f)
                .filaCheck("RECTO", cc.isPerfilRecto(), "", false, f)

                .subtitulo("PIEL", 4)
                .filaCheck("NORMAL", cc.isPielNormal(), "PÁLIDA", cc.isPielPalida(), f)
                .filaCheck("CIANÓTICA", cc.isPielCianotica(), "ENROJECIDA", cc.isPielEnrojecida(), f)

                .subtitulo("MÚSCULOS", 4)
                .filaCheck("HIPOTÓNICOS", cc.isMusculosHipotonicos(), "HIPERTÓNICOS", cc.isMusculosHipertonicos(), f)
                .filaCheck("ESPÁSTICOS", cc.isMusculosEspasticos(), "", false, f)

                .subtitulo("CUELLO", 4)
                .filaCheck("SE PALPA LA CADENA GANGLIONAR", cc.isCadenaGanglionar(), "", false, f);

        document.add(tabla);
    }

    // ── Estomatognático ──────────────────────────────────────────────────────

    private void agregarEstomatognatico(Document document, ExploracionEstomatognatico est) throws Exception {
        PdfCellFactory.agregarTituloSeccion(document, "EXPLORACIÓN DEL APARATO ESTOMATOGNÁTICO");

        if (est == null) {
            document.add(new Paragraph("No hay exploración del aparato estomatognático registrada."));
            return;
        }

        PdfPTable tabla = new PdfPTable(4);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[]{42, 8, 42, 8});
        tabla.setSpacingBefore(8f);
        tabla.setSpacingAfter(10f);

        var f = fuenteLoader.getCheckFont();
        PdfTableBuilder.of(tabla)
                .subtitulo("Articulación Temporomandibular (ATM)", 4)
                .filaCheck("Ruidos",                       est.isRuidos(),                    "Chasquidos",                  est.isChasquidos(), f)
                .filaCheck("Crepitación",                  est.isCrepitacion(),               "Fatiga o dolor muscular",     est.isFatigaDolorMuscular(), f)
                .filaCheck("Dolor abertura/lateralidad",   est.isDolorAberturaLateralidad(),  "Apertura",                    est.isApertura(), f)
                .filaCheck("Lateralidad",                  est.isLateralidad(),               "Dificultad para abrir boca",  est.isDificultadAbrirboca(), f)
                .filaCheck("Disminución de abertura",      est.isDisminuicionAbertura(),      "Desviación apertura/cierre",  est.isDesviacionAberturaCierre(), f);

        document.add(tabla);
    }

    // ── Tejidos blandos ──────────────────────────────────────────────────────

    private void agregarTejidosBlandos(Document document, TejidosBlandos tb) throws Exception {
        PdfCellFactory.agregarTituloSeccion(document, "TEJIDOS BLANDOS");

        if (tb == null) {
            document.add(new Paragraph("No hay exploración de tejidos blandos registrada."));
            return;
        }

        PdfPTable tabla = new PdfPTable(2);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[]{35, 65});
        tabla.setSpacingBefore(8f);
        tabla.setSpacingAfter(10f);

        PdfTableBuilder.of(tabla)
                .filaTejido("GANGLIOS",                tb.getGanglios())
                .filaTejido("GLÁNDULAS SALIVALES",     tb.getGlandulasSalivales())
                .filaTejido("LABIO EXTERNO",           tb.getLabioExterno())
                .filaTejido("BORDE BERMELLÓN",         tb.getBordeBermellon())
                .filaTejido("LABIO INTERNO",           tb.getLabioInterno())
                .filaTejido("COMISURAS",               tb.getComisuras())
                .filaTejido("CARRILLOS",               tb.getCarrillos())
                .filaTejido("FONDO DE SACO",           tb.getFondoDeSaco())
                .filaTejido("FRENILLOS",               tb.getFrenillos())
                .filaTejido("LENGUA TERCIO MEDIO",     tb.getLenguaTercioMedio())
                .filaTejido("PALADAR DURO",            tb.getPaladarDuro())
                .filaTejido("PALADAR BLANDO",          tb.getPaladarBlando())
                .filaTejido("ISTMO BUCOFARÍNGEO",      tb.getIstmoBucofaringe())
                .filaTejido("LENGUA DORSO",            tb.getLenguaDorso())
                .filaTejido("LENGUA BORDES",           tb.getLenguaBordes())
                .filaTejido("LENGUA VENTRAL",          tb.getLenguaVentral())
                .filaTejido("PISO DE LA BOCA",         tb.getPisoBoca())
                .filaTejido("DIENTES",                 tb.getDientes())
                .filaTejido("MUCOSA BORDE ALVEOLAR",   tb.getMucosaAlveolar())
                .filaTejido("ENCÍA",                   tb.getEncia());

        document.add(tabla);
        document.add(new Paragraph(" "));
    }
}