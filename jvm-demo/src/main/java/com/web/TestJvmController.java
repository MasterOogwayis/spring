package com.web;

import lombok.extern.slf4j.Slf4j;

/**
 * -Xms64m -Xmx64m -Xmn32m -XX:+PrintGCDetails -XX:+UseG1GC -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\Users\ZhangShaowei\Desktop\dump
 * -Xms128m -Xmx128m -Xmn64m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\Users\Administrator\Desktop
 *
 * @author ZhangShaowei on 2019/2/25 9:40
 **/
@Slf4j
//@RequestMapping("test")
//@RestController
public class TestJvmController {

    //    @GetMapping
    public Object test() {
        ProductDto productDto = new ProductDto();
        DetailDto detailDto = new DetailDto();
        detailDto.setId(1L);
        productDto.setNumber("123");
        productDto.setDetailDto(detailDto);
        return productDto;
    }

}
