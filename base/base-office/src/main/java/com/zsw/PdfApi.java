package com.zsw;

import com.zsw.pdf.impl.XhtmlPdfBuilder;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author ZhangShaowei on 2021/11/29 16:13
 */
@RequestMapping("pdf")
@RestController
public class PdfApi {

    @GetMapping("download")
    public ResponseEntity<byte[]> download() throws IOException {
        ClassPathResource resource = new ClassPathResource("docs/content.html");
        InputStream inputStream = resource.getInputStream();
        String html = IOUtils.toString(inputStream, UTF_8);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        new XhtmlPdfBuilder().htmlToPdf(html, outputStream);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=\"1.pdf\"");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(outputStream.size())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(outputStream.toByteArray());
    }

}
