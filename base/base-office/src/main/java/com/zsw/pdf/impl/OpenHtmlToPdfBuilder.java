package com.zsw.pdf.impl;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.zsw.pdf.PdfBuilder;
import lombok.SneakyThrows;

import java.io.OutputStream;

/**
 * @author ZhangShaowei on 2021/11/11 15:25
 */
public class OpenHtmlToPdfBuilder implements PdfBuilder {
    @SneakyThrows
    @Override
    public void htmlToPdf(String html, OutputStream outputStream) {
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.useFastMode();
        builder.withHtmlContent(html, null);
        builder.toStream(outputStream);
        builder.run();
    }
}
