package com.zsw.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZhangShaowei on 2021/8/23 10:21
 */
@RestController
public class Json2ObjectApi {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Gson gson;

    @SneakyThrows
    @GetMapping("json")
    public Object json(HttpServletRequest request) {
        return this.objectMapper.writeValueAsString(request.getParameterMap());
    }

    @SneakyThrows
    @GetMapping("gson")
    public Object gson(HttpServletRequest request) {
        return this.gson.toJson(request.getParameterMap());
    }


}
