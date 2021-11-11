package com.zsw.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author ZhangShaowei on 2021/11/11 8:58
 */
public class XhtmlPdfBuilder {


    public static void main(String[] args) {


    }

    public static void htmlToPdf(String html, OutputStream os) {
        // 创建一个文档
        Document document = new Document(PageSize.A4);
        // pdf输出流
        PdfWriter pdfWriter;
        try {
            pdfWriter = PdfWriter.getInstance(document, os);
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        document.open();

        try {
            XMLWorkerHelper.getInstance().parseXHtml(
                    pdfWriter,
                    document,
                    new ByteArrayInputStream(html.getBytes(StandardCharsets.UTF_8)),
                    StandardCharsets.UTF_8
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        document.close();
        pdfWriter.close();
    }


}
