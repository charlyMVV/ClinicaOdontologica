package com.Clinica.SistemaClinicaBack.pdf;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import org.springframework.stereotype.Component;

@Component
public class PdfFuenteLoader {

    private final Font checkFont;

    public PdfFuenteLoader() {
        this.checkFont = cargarFuenteCheck();
    }

    public Font getCheckFont() {
        return checkFont;
    }

    private final Font CHECK_FONT = cargarFuenteCheck();

    private Font cargarFuenteCheck() {
        try {
            BaseFont bf = BaseFont.createFont(
                    "C:/Windows/Fonts/seguisym.ttf",
                    BaseFont.IDENTITY_H,
                    BaseFont.EMBEDDED
            );

            return new Font(bf, 10, Font.BOLD);

        } catch (Exception e) {
            e.printStackTrace();
            return new Font(Font.HELVETICA, 10, Font.BOLD);
        }
    }
}