package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * -Xms96m -Xmx96m -Xmn40m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\Users\ZhangShaowei\Desktop\dump -XX:-UseAdaptiveSizePolicy -XX:SurvivorRatio=8 -XX:InitialSurvivorRatio=8
 *
 *  -XX:+UseConcMarkSweepGC
 *
 * @author ZhangShaowei on 2019/3/8 9:57
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class JvmTest {

    public static void main(String[] args) {
        SpringApplication.run(JvmTest.class, args);
    }

}