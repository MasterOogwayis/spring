package com.zsw.conf;

import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * @author ZhangShaowei on 2017/6/13 14:44
 */
//@Component
public class FileUploadClinetHystrix implements FileUploadClinet {
    @Override
    public List<String> bacthUpload(MultipartFile[] files, String path) {
        return Arrays.asList("失败");
    }

    @Override
    public String singleUpload(MultipartFile multipartFile, String path) {
        return "error";
    }

    @Override
    public feign.Response download(String path, String filename) {
        return null;
    }

}