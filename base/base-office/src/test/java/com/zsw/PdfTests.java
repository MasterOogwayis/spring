package com.zsw;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Paths;

/**
 * @author ZhangShaowei on 2021/11/11 10:15
 */
public class PdfTests {

    public static void main(String[] args) {
        System.out.println(Paths.get("C:\\Windows\\Fonts\\simsun.ttc").toFile().exists());
        File[] files = new File("C:\\Windows\\Fonts").listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                String lower = name.toLowerCase();
                return lower.endsWith(".otf") || lower.endsWith(".ttf") || lower.endsWith(".ttc");
            }
        });
        for (File file : files) {
            System.out.println(file.getAbsolutePath());
        }
    }

}
