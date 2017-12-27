package com.zsw.web;

import com.zsw.conf.FileUploadClinet;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author ZhangShaowei on 2017/6/12 14:23
 */
@RestController
public class UIFileUploadController {

    /** */
    private static final String PATH = "C:\\Users\\ZhangShaowei\\Desktop\\";

    /** */
    @Autowired
    private FileUploadClinet fileUploadClinet;

    /**
     * @param files 文件
     * @return
     */
    @PostMapping("batch/upload")
    public List<String> bacthUpload(@RequestParam("file") final MultipartFile[] files) {
        List<String> result = this.fileUploadClinet.bacthUpload(files, PATH);
//        for (MultipartFile file : files) {
//            result.add(this.fileUploadClinet.singleUpload(file, PATH));
//        }
//        return result;
        return result;
    }

//    @PostMapping("batch/upload2")
//    public String bacthUpload2(HttpServletRequest request) throws IOException {
//
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
//
//        if (multipartResolver.isMultipart(request)) {
//            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//            List<MultipartFile> files = multipartRequest.getFiles("file");
//            for (MultipartFile file : files) {
//                //记录上传过程起始时的时间，用来计算上传时间
//                int pre = (int) System.currentTimeMillis();
//                //取得上传文件
//                if (file != null) {
//                    //取得当前上传文件的文件名称
//                    String myFileName = file.getOriginalFilename();
//                    //如果名称不为"",说明该文件存在，否则说明该文件不存在
//                    if (!"".equals(myFileName.trim())) {
//                        System.out.println(myFileName);
//                        //重命名上传后的文件名?
//                        //定义上传路径
//                        String path = PATH + myFileName;
//                        File localFile = new File(path);
//                        file.transferTo(localFile);
//                    }
//                }
//                //记录上传该文件后的时间
//                int finaltime = (int) System.currentTimeMillis();
//                System.out.println(finaltime - pre);
//            }
//
//        }
//        return "success";
//
//    }

    /**
     * @param multipartFile
     * @return
     */
    @PostMapping("single/upload")
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        return this.fileUploadClinet.singleUpload(multipartFile, PATH);
    }


    /**
     * @param path     文件路径
     * @param filename 文件名
     * @return InputStreamResource
     * @throws IOException e
     */
    @PostMapping("download")
    public ResponseEntity<InputStreamResource> download(
            @RequestParam("path") final String path,
            @RequestParam("filename") final String filename) throws IOException {
//        return this.fileUploadClinet.download(path, filename);
//        Response response = this.fileUploadClinet.download(path, filename);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
        headers.add(
                HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", filename));
        headers.add(HttpHeaders.PRAGMA, "no-cache");
        headers.add(HttpHeaders.EXPIRES, "0");
//        headers.putAll((Map<? extends String, ? extends List<String>>) response.headers());
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(this.fileUploadClinet.download(path, filename).body().asInputStream()));
    }

    /**
     * @param path
     * @param filename
     * @param response
     * @return
     */
    @PostMapping("download1")
    public void download1(
            @RequestParam("path") final String path,
            @RequestParam("filename") final String filename,
            final HttpServletResponse response) {
        File downloadFile = new File(path, filename);
        // set content attributes for the response
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setContentLengthLong(downloadFile.length());

        // set headers for the response
        response.setHeader(
                HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + downloadFile.getName() + "\"");

        try (InputStream myStream = new FileInputStream(downloadFile)) {

            IOUtils.copy(myStream, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    @RequestMapping("test")
    public FileSystemResource down(){
        return new FileSystemResource(new File("G:\\", "slowloris.pl"));
    }

}
