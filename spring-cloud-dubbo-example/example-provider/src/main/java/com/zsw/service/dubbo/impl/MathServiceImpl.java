package com.zsw.service.dubbo.impl;

import com.zsw.service.dubbo.MathService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * @author ZhangShaowei on 2021/8/4 16:45
 */
@Service
@DubboService
public class MathServiceImpl implements MathService {
    @Override
    public int sum(int x, int y) {
        return x + y;
    }
}
