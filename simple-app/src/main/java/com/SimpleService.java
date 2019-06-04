package com;

import com.zsw.http.backup.RequestHttp;
import com.zsw.http.backup.okhttp.OkHttpRequest;
import org.springframework.stereotype.Service;

/**
 * @author ZhangShaowei on 2019/5/28 17:03
 **/
@Service("simpleService")
public class SimpleService extends OkHttpRequest implements RequestHttp {
}
