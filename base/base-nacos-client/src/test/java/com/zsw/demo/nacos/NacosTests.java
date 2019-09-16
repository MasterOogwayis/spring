package com.zsw.demo.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author Administrator on 2019/8/4 19:51
 **/
@Slf4j
public class NacosTests {

    @SneakyThrows
    public static void main(String[] args) {
        String serverAddress = "localhost";
        String dataId = "SpringCloud";
        String group = "core-server";

        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.SERVER_ADDR, serverAddress);
        ConfigService configService = NacosFactory.createConfigService(properties);

        String config = configService.getConfig(dataId, group, 5000);

        log.info("config: {}", config);

        configService.addListener(dataId, group, new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String s) {
                log.warn("updatet config: {}", s);
            }
        });

        System.in.read();


    }

}
