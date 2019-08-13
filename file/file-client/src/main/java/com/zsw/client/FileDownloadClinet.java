package com.zsw.client;
/**
 * @author ZhangShaowei on 2017/6/13 14:43
 */

import com.zsw.config.FileConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Feign interface
 *
 * @author ZhangShaowei on 2017/6/13 14:43
 **/
@FeignClient(value = "${zsw.client.servername.file}", configuration = FileConfiguration.ClientConfiguration.class)
public interface FileDownloadClinet {


    /**
     * byte[]  it may cause OutOfMemoryError,
     *
     * @param path     path
     * @param filename filename
     * @return InputStreamResource
     */
    @RequestMapping(value = "file/download", method = RequestMethod.POST)
    feign.Response download(
            @RequestParam("path") String path,
            @RequestParam("filename") String filename);



    @RequestMapping(value = "file/down", method = RequestMethod.POST)
    MultipartFile[] down(@RequestParam("path") String path, @RequestParam("filename") String filename);

}
