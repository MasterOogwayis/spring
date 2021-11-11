package com.zsw.utils;

import com.itextpdf.text.pdf.BaseFont;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

/**
 * @author ZhangShaowei on 2021/11/11 11:06
 */
@Slf4j
public class ITextPdfBuilder {

    @SneakyThrows
    public static void main(String[] args) {
        String path = "C:\\Users\\ZhangShaowei\\Desktop\\3.pdf";
        Path htmlPath = Paths.get(new ClassPathResource("static/content.html").getPath());
        htmlToPdf(Files.readString(htmlPath), path);
    }

    @SneakyThrows
    public static void htmlToPdf(String html, String path) {
        ITextRenderer renderer = new ITextRenderer();

        ITextFontResolver fontResolver = renderer.getFontResolver();
//        fontResolver.addFontDirectory("C:\\Windows\\Fonts", BaseFont.NOT_EMBEDDED);
        fontResolver.addFont("C:\\Windows\\Fonts\\simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        addFonts("C:\\Windows\\Fonts", file -> {
            try {
                fontResolver.addFont(file.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            } catch (Exception e) {
                log.warn("ignored, {}", e.getMessage());
            }
        });

        try (OutputStream os = new FileOutputStream(path)) {
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(os);
        }
    }

    public static void addFonts(String dir, Consumer<File> consumer) {
        File f = new File(dir);
        if (f.isDirectory()) {
            File[] files = f.listFiles((dir1, name) -> {
                String lower = name.toLowerCase();
                return lower.endsWith(".otf") || lower.endsWith(".ttf") || lower.endsWith(".ttc");
            });
            for (File file : files) {
                consumer.accept(file);
            }
        }
    }

}
