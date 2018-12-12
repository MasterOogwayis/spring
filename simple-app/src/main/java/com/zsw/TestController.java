package com.zsw;

import com.zsw.base.ui.commons.BaseController;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Test
 *
 * @author ZhangShaowei on 2018/4/8 13:46
 **/
@Getter
@Setter
@ConfigurationProperties(prefix = "zsw.test")
@RestController
public class TestController extends BaseController {

    private List<String> list;

    @GetMapping("test")
    public Object test() {
        return this.list;
    }

//    @PostMapping("send")
//    public Object webhook(HttpServletRequest request) throws IOException {
//        try( BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8")))
//        {
//            String body = this.readStream(br);
//            this.logger.info(body);
//        }
//
//        Map<String, Object> data = new HashMap<>();
//        data.put("errcode", 0);
//        data.put("errmsg", "ok");
//        return data;
//    }



    protected String readStream(BufferedReader br) throws IOException {
        String buffer = null;
        StringBuffer sb = new StringBuffer();
        while ((buffer = br.readLine()) != null) {
            sb.append(buffer);
        }
        return sb.toString();
    }
}
