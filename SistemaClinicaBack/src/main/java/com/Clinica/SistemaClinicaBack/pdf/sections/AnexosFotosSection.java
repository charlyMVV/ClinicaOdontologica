package com.Clinica.SistemaClinicaBack.pdf.sections;

import com.Clinica.SistemaClinicaBack.entity.FotosInicio;
import com.Clinica.SistemaClinicaBack.entity.HistoriaClinica;
import com.Clinica.SistemaClinicaBack.pdf.PdfSeccion;
import com.Clinica.SistemaClinicaBack.repository.FotosInicioRepository;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.List;

@Component
@Order(9)
@RequiredArgsConstructor
public class AnexosFotosSection implements PdfSeccion {

    private final FotosInicioRepository fotosInicioRepository;

    @Override
    public void agregar(Document document, HistoriaClinica hc) throws Exception {
        List<FotosInicio> fotos =
                fotosInicioRepository.findByHistoriaClinica_IdHistoriaClinica(hc.getIdHistoriaClinica());

        if (fotos == null || fotos.isEmpty()) return;

        document.newPage();

        Paragraph titulo = new Paragraph("ANEXOS FOTOGRÁFICOS",
                new Font(Font.HELVETICA, 12, Font.BOLD));
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);
        document.add(new Paragraph(" "));

        PdfPTable tabla = new PdfPTable(2);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[]{50, 50});

        for (FotosInicio foto : fotos) {
            if (foto.getFotos() == null || foto.getFotos().trim().isEmpty()) continue;

            try {
                Image imagen = base64AImagen(foto.getFotos());
                imagen.scaleToFit(230, 180);
                imagen.setAlignment(Element.ALIGN_CENTER);

                PdfPCell celda = new PdfPCell(imagen, true);
                celda.setPadding(8);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tabla.addCell(celda);

            } catch (Exception e) {
                PdfPCell error = new PdfPCell(new Phrase("No se pudo cargar una imagen."));
                error.setPadding(8);
                tabla.addCell(error);
            }
        }

        if (tabla.size() > 0) {
            document.add(tabla);
        }
    }

    private Image base64AImagen(String base64) throws Exception {
        String data = base64.contains(",")
                ? base64.substring(base64.indexOf(",") + 1)
                : base64;
        return Image.getInstance(Base64.getDecoder().decode(data));
    }
}