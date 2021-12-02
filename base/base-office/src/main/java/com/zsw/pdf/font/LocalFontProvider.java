package com.zsw.pdf.font;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author ZhangShaowei on 2021/12/2 9:50
 */
@Slf4j
public class LocalFontProvider extends XMLWorkerFontProvider {
    @Override
    @SneakyThrows
    public Font getFont(final String fontname, final String encoding,
                        final boolean embedded, final float size, final int style,
                        final BaseColor color) {
        Font font = super.getFont(fontname, encoding, embedded, size, style, color);
        if (Objects.isNull(font.getBaseFont())) {
            font = this.defaultFont(fontname, encoding, embedded, size, style, color);
            log.info("fontName: {}", fontname);
        }
        return font;
    }

    @SneakyThrows
    private Font defaultFont(final String fontname, final String encoding,
                             final boolean embedded, final float size, final int style,
                             final BaseColor color) {
        BaseFont bf = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", embedded);
        Font font = new Font(bf, size, style, color);
        font.setColor(color);
        return font;
    }
}