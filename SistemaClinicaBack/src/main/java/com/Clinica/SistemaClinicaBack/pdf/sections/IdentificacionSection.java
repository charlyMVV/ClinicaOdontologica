package com.Clinica.SistemaClinicaBack.pdf.sections;

import com.Clinica.SistemaClinicaBack.entity.HistoriaClinica;
import com.Clinica.SistemaClinicaBack.entity.Paciente;
import com.Clinica.SistemaClinicaBack.pdf.PdfCellFactory;
import com.Clinica.SistemaClinicaBack.pdf.PdfSeccion;
import com.Clinica.SistemaClinicaBack.pdf.PdfTableBuilder;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class IdentificacionSection implements PdfSeccion {

    @Override
    public void agregar(Document document, HistoriaClinica hc) throws Exception {
        Paciente p = hc.getPaciente();

        PdfCellFactory.agregarTituloSeccion(document, "IDENTIFICACIÓN DEL PACIENTE");

        PdfPTable tabla = new PdfPTable(4);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[]{22, 28, 22, 28});

        PdfTableBuilder.of(tabla)
                .fila("Nombre",           p.getNombrePaciente(),          "CURP",            p.getCURP())
                .fila("Sexo",             p.getSexo(),                    "Edad",            p.getEdad())
                .fila("Fecha nacimiento", String.valueOf(p.getfechaNacimiento()), "Estado civil", p.getEstadoCivil())
                .fila("Domicilio",        p.getDomicilio(),               "Celular",         p.getTelefonoCelular())
                .fila("Teléfono casa",    p.getTelefonoCasa(),            "Religión",        p.getReligion())
                .fila("Ocupación",        p.getOcupacion(),               "Escolaridad",     p.getEscolaridad())
                .fila("Derechohabiente",  p.getDerechohabiente(),         "Médico familiar", p.getMedicoFamiliar())
                .fila("Número médico",    p.getNumero_medico(),           "Última consulta", p.getUltimaConsulta());

        document.add(tabla);
        document.add(new Paragraph(" "));
    }
}