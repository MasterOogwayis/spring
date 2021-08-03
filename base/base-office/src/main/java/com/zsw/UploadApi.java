package com.zsw;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ZhangShaowei on 2021/8/3 11:22
 */
@RequestMapping("excel")
@RestController
public class UploadApi {

    @PostMapping("upload")
    public Object upload(@RequestPart MultipartFile file) {
        return null;
    }

}
