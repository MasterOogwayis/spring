package com.zsw.client;

import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * @author zsw on 2017/6/13 14:44
 */
//@Component
public class FileUploadClinetHystrix implements com.zsw.client.FileUploadClinet {

    @Override
    public List<String> upload(MultipartFile[] files, String path) {
        return null;
    }
}
