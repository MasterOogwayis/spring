package com.zsw.pdf.impl;

import com.itextpdf.text.pdf.BaseFont;
import com.zsw.pdf.PdfBuilder;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.OutputStream;
import java.util.function.Consumer;

/**
 * @author ZhangShaowei on 2021/11/11 15:01
 */
@Slf4j
public class FlyingSaucerPdfBuilder implements PdfBuilder {
    @Override
    public void htmlToPdf(String html, OutputStream outputStream) {

        Document document = Jsoup.parse(html, "UTF-8");
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);

        ITextRenderer renderer = new ITextRenderer();
        ITextFontResolver fontResolver = renderer.getFontResolver();
        addFont("C:\\Windows\\Fonts", file -> {
            try {
                fontResolver.addFont(file.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            } catch (Exception e) {
                log.warn("error: {}", e.getMessage());
            }
        });

        SharedContext sharedContext = renderer.getSharedContext();
        sharedContext.setPrint(true);
        sharedContext.setInteractive(false);
        renderer.setDocumentFromString(document.html());
        renderer.layout();
        renderer.createPDF(outputStream);
    }

    private void addFont(String dir, Consumer<File> consumer) {
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
