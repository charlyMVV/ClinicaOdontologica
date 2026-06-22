package com.Clinica.SistemaClinicaBack.pdf.sections;

import com.Clinica.SistemaClinicaBack.entity.Firma;
import com.Clinica.SistemaClinicaBack.entity.HistoriaClinica;
import com.Clinica.SistemaClinicaBack.pdf.PdfCellFactory;
import com.Clinica.SistemaClinicaBack.pdf.PdfSeccion;
import com.Clinica.SistemaClinicaBack.repository.FirmaRepository;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.awt.Color;
import java.util.Base64;
import java.util.Optional;

@Component
@Order(7)
@RequiredArgsConstructor
public class AutorizacionSection implements PdfSeccion {

    private static final Color GRIS_BORDE = new Color(180, 180, 180);

    private final FirmaRepository firmaRepository;

    @Override
    public void agregar(Document document, HistoriaClinica hc) throws Exception {
        Font normal = new Font(Font.HELVETICA, 10);

        PdfCellFactory.agregarTituloSeccion(document, "AUTORIZACIÓN");

        String nombrePaciente = hc.getPaciente() != null
                ? PdfCellFactory.sanitizar(hc.getPaciente().getNombrePaciente())
                : "";
        String curp = hc.getPaciente() != null ? hc.getPaciente().getCURP() : null;

        Paragraph texto = new Paragraph(
                "Por la presente autorizo a " + nombrePaciente
                        + " y/o asociados a realizar el tratamiento informado en la presente Historia Clínica. "
                        + "He sido informado sobre la naturaleza y finalidad del tratamiento, sus posibles riesgos, "
                        + "complicaciones y alternativas terapéuticas. Asimismo, autorizo la realización de procedimientos "
                        + "complementarios que el profesional considere necesarios, incluyendo anestesia, estudios radiográficos "
                        + "y demás métodos auxiliares de diagnóstico.", normal);
        texto.setAlignment(Element.ALIGN_JUSTIFIED);
        document.add(texto);

        Paragraph menor = new Paragraph(
                "En caso de tratarse de un menor de edad, la autorización deberá ser otorgada por el padre, madre o tutor responsable.",
                normal);
        menor.setAlignment(Element.ALIGN_JUSTIFIED);
        menor.setSpacingBefore(10f);
        document.add(menor);

        // Firma digital si existe
        if (curp != null) {
            Optional<Firma> firmaOpt = firmaRepository.findFirstByCurp(curp);
            if (firmaOpt.isPresent() && tieneContenido(firmaOpt.get().getFirma())) {
                String base64 = limpiarBase64(firmaOpt.get().getFirma());
                byte[] bytes = Base64.getDecoder().decode(base64);
                Image imagenFirma = Image.getInstance(bytes);
                imagenFirma.scaleToFit(180, 70);
                imagenFirma.setAlignment(Element.ALIGN_CENTER);
                document.add(imagenFirma);
            }
        }

        // Línea de firma
        PdfPTable firmaTabla = new PdfPTable(1);
        firmaTabla.setWidthPercentage(60);
        firmaTabla.setHorizontalAlignment(Element.ALIGN_CENTER);
        PdfPCell linea = new PdfPCell(new Phrase(" "));
        linea.setBorder(Rectangle.TOP);
        linea.setHorizontalAlignment(Element.ALIGN_CENTER);
        linea.setPaddingTop(10);
        linea.setBorderColor(GRIS_BORDE);
        firmaTabla.addCell(linea);
        document.add(firmaTabla);

        Paragraph nombre = new Paragraph(nombrePaciente, normal);
        nombre.setAlignment(Element.ALIGN_CENTER);
        document.add(nombre);

        Paragraph autoriza = new Paragraph("Nombre completo y firma del paciente o tutor", normal);
        autoriza.setAlignment(Element.ALIGN_CENTER);
        document.add(autoriza);

        document.add(new Paragraph(" "));
    }

    private boolean tieneContenido(String s) {
        return s != null && !s.trim().isEmpty();
    }

    private String limpiarBase64(String base64) {
        return base64.contains(",") ? base64.substring(base64.indexOf(",") + 1) : base64;
    }
}