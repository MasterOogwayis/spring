package com.web;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.util.List;

/**
 * @author ZhangShaowei on 2017/6/12 14:23
 */
@RestController
public class FileUploadController {

    private static final String PATH = "C:\\Users\\ZhangShaowei\\Desktop\\";

    @PostMapping("single/upload")
    public String upload(HttpServletRequest request) throws Exception {
        Part part = request.getPart("fileUpload");
        try (
                InputStream input = part.getInputStream();
                OutputStream output = new FileOutputStream(PATH + part.getSubmittedFileName());

        ) {
            IOUtils.copy(input, output);

        }

        return "success";
    }


    /**
     * @return
     */
    @PostMapping("batch/upload")
    public String bacthUpload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        List<MultipartFile> files = multipartRequest.getFiles("file");

        String fileName = null;
        String msg = "";
        if (!CollectionUtils.isEmpty(files)) {
            for (int i = 0; i < files.size(); i++) {
                try {
                    fileName = files.get(i).getOriginalFilename();
                    byte[] bytes = files.get(i).getBytes();
                    BufferedOutputStream buffStream =
                            new BufferedOutputStream(new FileOutputStream(new File(PATH + fileName)));
                    buffStream.write(bytes);
                    buffStream.close();
                    msg += "You have successfully uploaded " + fileName + "<br/>";
                } catch (Exception e) {
                    return "You failed to upload " + fileName + ": " + e.getMessage() + "<br/>";
                }
            }
            return msg;
        } else {
            return "Unable to upload. File is empty.";
        }
    }

    @PostMapping("batch/upload1")
    public String bacthUpload1(@RequestParam("file") MultipartFile[] files) {
        String fileName = null;
        String msg = "";
        if (!ObjectUtils.isEmpty(files)) {
            for (int i = 0; i < files.length; i++) {
                try {
                    fileName = files[i].getOriginalFilename();
                    byte[] bytes = files[i].getBytes();
                    BufferedOutputStream buffStream =
                            new BufferedOutputStream(new FileOutputStream(new File(PATH + fileName)));
                    buffStream.write(bytes);
                    buffStream.close();
                    msg += "You have successfully uploaded " + fileName + "<br/>";
                } catch (Exception e) {
                    return "You failed to upload " + fileName + ": " + e.getMessage() + "<br/>";
                }
            }
            return msg;
        } else {
            return "Unable to upload. File is empty.";
        }
    }

    @PostMapping("batch/upload2")
    public String bacthUpload2(HttpServletRequest request) throws IOException {

        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> files = multipartRequest.getFiles("file");
            for (MultipartFile file : files) {
                //记录上传过程起始时的时间，用来计算上传时间
                int pre = (int) System.currentTimeMillis();
                //取得上传文件
                if (file != null) {
                    //取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    //如果名称不为"",说明该文件存在，否则说明该文件不存在
                    if (!"".equals(myFileName.trim())) {
                        System.out.println(myFileName);
                        //重命名上传后的文件名?
                        //定义上传路径
                        String path = PATH + myFileName;
                        File localFile = new File(path);
                        file.transferTo(localFile);
                    }
                }
                //记录上传该文件后的时间
                int finaltime = (int) System.currentTimeMillis();
                System.out.println(finaltime - pre);
            }

        }
        return "success";

    }

    @PostMapping("uploadFile")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file) {
        if (!file.isEmpty()) {
            try (
                    BufferedOutputStream out = new BufferedOutputStream(
                            new FileOutputStream(
                                    new File(PATH + file.getOriginalFilename())));
            ) {
                out.write(file.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
                return e.getMessage();
            }
            return "success";
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
