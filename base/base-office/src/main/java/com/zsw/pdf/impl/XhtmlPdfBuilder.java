package com.zsw.pdf.impl;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseField;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RadioCheckField;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.zsw.pdf.PdfBuilder;
import com.zsw.pdf.font.LocalFontProvider;
import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author ZhangShaowei on 2021/11/11 8:58
 */
public class XhtmlPdfBuilder implements PdfBuilder {


    @Override
    @SneakyThrows
    public void htmlToPdf(String html, OutputStream os) {

        // 创建一个文档
        Document document = new Document(PageSize.A4);
        // pdf输出流
        PdfWriter pdfWriter = PdfWriter.getInstance(document, os);
        document.open();

        PdfFormField radioGroup = PdfFormField.createRadioButton(pdfWriter, false);
        radioGroup.setFieldName("numbers");
        for (int i = 0; i < 3; i++) {
            Rectangle rect = new Rectangle(130 + (40 * i), 430, 160 + (40 * i), 455);
            this.addRadioButtonKid(pdfWriter, radioGroup, rect, String.valueOf(i));
        }
        pdfWriter.addAnnotation(radioGroup);
        // 如果 html 有效内容为空，document.close()会直接报错
        // this will do the trick
        document.add(new Chunk(""));
        XMLWorkerHelper.getInstance().parseXHtml(
                pdfWriter,
                document,
                new ByteArrayInputStream(html.getBytes(StandardCharsets.UTF_8)),
                StandardCharsets.UTF_8,
                new LocalFontProvider()
        );

        document.close();
        pdfWriter.close();
    }

    @SneakyThrows
    private void addRadioButtonKid(PdfWriter writer, PdfFormField radio, Rectangle rect, String onValue) {
        RadioCheckField bt = new RadioCheckField(writer, rect, null, onValue);
        bt.setBorderWidth(BaseField.BORDER_WIDTH_THICK);
        bt.setBorderColor(BaseColor.BLACK);
        bt.setBackgroundColor(BaseColor.WHITE);
        bt.setCheckType(RadioCheckField.TYPE_CROSS);
        bt.setChecked(false);
        PdfFormField ck = bt.getCheckField();
        ck.setPlaceInPage(1);
        radio.addKid(ck);
    }


}
