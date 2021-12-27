package com.zsw;

import lombok.Cleanup;
import lombok.SneakyThrows;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author ZhangShaowei on 2021/9/23 13:50
 */
public class StaticTests {

    @SneakyThrows
    public static void main(String[] args) {
//        TestThisService service = new JdkProxy().getInstance(new TestThisService());
//        System.out.println(AnnotationUtils.isCandidateClass(service.getClass(), Wrapped.class));


        URI uri = URI.create("http://localhost:18080//hello").normalize();

        URL url = uri.toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        @Cleanup InputStream in = connection.getInputStream();

        byte[] data = new byte[in.available()];

        IOUtils.readFully(connection.getInputStream(), data);

        System.out.println(new String(data, StandardCharsets.UTF_8));


    }


}
