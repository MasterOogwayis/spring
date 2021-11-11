package com.zsw;


import com.zsw.pdf.PdfBuilder;
import com.zsw.pdf.impl.FlyingSaucerPdfBuilder;
import com.zsw.pdf.impl.XhtmlPdfBuilder;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author ZhangShaowei on 2021/11/11 10:15
 */
@Slf4j
public class PdfTests {

    @SneakyThrows
    public static void main(String[] args) {
        String path = "C:\\Users\\ZhangShaowei\\Desktop";
        File htmlFile = new ClassPathResource("static/content.html").getFile();
        String html = Files.readString(htmlFile.toPath());

        @Cleanup FileOutputStream os1 = new FileOutputStream(Paths.get(path, "合同1.pdf").toFile());
        test(new XhtmlPdfBuilder(), html, os1);

        @Cleanup FileOutputStream os2 = new FileOutputStream(Paths.get(path, "合同2.pdf").toFile());
        test(new FlyingSaucerPdfBuilder(), html, os2);

//        @Cleanup FileOutputStream os3 = new FileOutputStream(Paths.get(path, "合同3.pdf").toFile());
//        test(new OpenHtmlToPdfBuilder(), html, os3);

    }

    @SneakyThrows
    public static void test(PdfBuilder builder, String html, OutputStream os) {
        long time = System.currentTimeMillis();
        builder.htmlToPdf(html, os);
        log.info("{}, 耗时: {}ms", builder.getClass(), System.currentTimeMillis() - time);
    }

}
