package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.sleuth.stream.SleuthStreamAutoConfiguration;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;
import zipkin.server.EnableZipkinServer;

@EnableZipkinStreamServer
@EnableDiscoveryClient
@SpringBootApplication(exclude = SleuthStreamAutoConfiguration.class)
public class ServerZipkinApplication {

    /**
     * main
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ServerZipkinApplication.class, args);
    }
}
