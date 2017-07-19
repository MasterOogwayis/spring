package com.web;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ZhangShaowei on 2017/6/12 14:23
 */
@RestController
@RequestMapping("file")
public class ApiFileUploadController {


    /**
     * @param files 文件
     * @return
     */
    @PostMapping("batch/upload")
    @Deprecated
    public List<String> bacthUpload(@RequestPart("files") MultipartFile[] files, @RequestParam("path") String path) {
        String fileName = null;
        List<String> list = new ArrayList<>();
        if (!ObjectUtils.isEmpty(files)) {

            try {
                for (MultipartFile file : files) {
                    fileName = file.getOriginalFilename();
                    file.transferTo(new File(path + fileName));
                    list.add(fileName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        } else {
            return Arrays.asList("faild for not found!");
        }
    }


    /**
     * @param file
     * @return
     */
    @PostMapping(value = "single/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String singleUpload(@RequestPart("file") MultipartFile file, @RequestParam("path") String path) {
        if (file != null && !file.isEmpty()) {
//            try (
//                    BufferedOutputStream out = new BufferedOutputStream(
//                            new FileOutputStream(new File(PATH + file.getOriginalFilename())));
//            ) {
//                out.write(file.getBytes());
//            } catch (Exception e) {
//                e.printStackTrace();
//                return e.getMessage();
//            }
            try {
                Files.createDirectories(Paths.get(path));
//                Files.copy(
//                        file.getInputStream(),
//                        Paths.get(path + file.getOriginalFilename()),
//                        StandardCopyOption.REPLACE_EXISTING);
                file.transferTo(new File(path, file.getOriginalFilename()));
            } catch (Exception e) {
                e.printStackTrace();
                return e.getMessage();
            }
            return "success:" + file.getOriginalFilename() ;
        }
        return "file not found!";

    }


    @Bean
    MultipartConfigElement createMultipartConfigElement() {
        MultipartConfigFactory mcf = new MultipartConfigFactory();
        /**
         * 设置最大上传文件的大小，默认是1MB
         */
        mcf.setMaxFileSize("50MB");
        return mcf.createMultipartConfig();
    }

}
