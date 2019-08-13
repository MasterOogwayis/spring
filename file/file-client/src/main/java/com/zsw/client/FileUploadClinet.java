package com.zsw.client;
/**
 * @author ZhangShaowei on 2017/6/13 14:43
 */

import com.zsw.config.FileConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Feign interface
 *
 * @author ZhangShaowei on 2017/6/13 14:43
 **/
@FeignClient(value = "${zsw.client.servername.file}", configuration = FileConfiguration.MultipartSupportConfig.class)
public interface FileUploadClinet {


    /**
     * @param files files
     * @param path  path
     * @return names
     */
    @PostMapping(value = "file/batch/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    List<String> upload(@RequestPart MultipartFile[] files, @RequestParam("path") String path);

}
