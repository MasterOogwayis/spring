package com.zsw;

import com.zsw.base.ui.commons.BaseController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Test
 *
 * @author ZhangShaowei on 2018/4/8 13:46
 **/
@RestController
public class TestController extends BaseController {

    @PostMapping("send")
    public Object webhook(HttpServletRequest request) throws IOException {
        try( BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8")))
        {
            String body = this.readStream(br);
            this.logger.info(body);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("errcode", 0);
        data.put("errmsg", "ok");
        return data;

    }



    protected String readStream(BufferedReader br) throws IOException {
        String buffer = null;
        StringBuffer sb = new StringBuffer();
        while ((buffer = br.readLine()) != null) {
            sb.append(buffer);
        }
        return sb.toString();
    }
}
