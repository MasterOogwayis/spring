package com.zsw;

import com.alibaba.excel.EasyExcel;
import com.zsw.converter.LocalDateTimeConverter;
import com.zsw.pojo.IndexOrNameData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author ZhangShaowei on 2021/8/3 11:22
 */
@RequestMapping("excel")
@RestController
public class ExcelApi {

    @PostMapping("upload")
    public Object upload(@RequestPart MultipartFile file) {
        return null;
    }


    @GetMapping("download")
    public void download(HttpServletResponse response) {
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("测试", UTF_8).replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            EasyExcel.write(outputStream, IndexOrNameData.class)
                    .registerConverter(new LocalDateTimeConverter())
                    .sheet("模板")
                    .doWrite(data());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<IndexOrNameData> data() {
        List<IndexOrNameData> list = new ArrayList<>();
        list.add(new IndexOrNameData(1d, "title", LocalDateTime.now()));
        list.add(new IndexOrNameData(2d, "name", LocalDateTime.now().plusDays(1)));
        list.add(new IndexOrNameData(3d, "age", LocalDateTime.now().plusDays(2)));
        list.add(new IndexOrNameData(4d, "address", LocalDateTime.now().plusDays(3)));
        return list;
    }

}
