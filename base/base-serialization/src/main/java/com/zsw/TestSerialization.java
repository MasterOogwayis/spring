package com.zsw;

import com.zsw.seri.*;
import com.zsw.socket.User;
import lombok.SneakyThrows;

/**
 * @author ZhangShaowei on 2019/6/4 11:21
 **/
public class TestSerialization {


    @SneakyThrows
    public static void main(String[] args) {
        User user = User.builder().name("zsw").age(2).build();

        ISerializer javaSerializer = new JavaSerializer();
        ISerializer fastjsonSerializer = new FastJsonSerializer();
        ISerializer hessianSerializer = new HessianSerializer();
        ISerializer xStreamSerializer = new XStreamSerializer();
        ISerializer protostuffSerializer = new ProtostuffSerializer();

        // java
        byte[] javaData = javaSerializer.serialize(user);
        System.err.println("java - length: " + javaData.length);
        StringBuilder sb = new StringBuilder("java: ");
        for (byte b : javaData) {
            sb.append(b).append(" ");
        }
        System.out.println(sb);

        // fastjson
        byte[] fastjsonData = fastjsonSerializer.serialize(user);
        System.err.println("fastjson - length: " + fastjsonData.length);
        sb = new StringBuilder("fastjson: ");
        for (byte b : fastjsonData) {
            sb.append(b).append(" ");
        }
        System.out.println(sb);

        // hessian
        byte[] hessianData = hessianSerializer.serialize(user);
        System.err.println("hessian - length: " + hessianData.length);
        sb = new StringBuilder("hessian: ");
        for (byte b : hessianData) {
            sb.append(b).append(" ");
        }
        System.out.println(sb);

        // xStream
        byte[] xstreamData = xStreamSerializer.serialize(user);
        System.err.println("xstream - length: " + xstreamData.length);
        sb = new StringBuilder("xstream: ");
        for (byte b : xstreamData) {
            sb.append(b).append(" ");
        }
        System.out.println(sb);

        // protostuff
        byte[] protostuffData = protostuffSerializer.serialize(user);
        System.err.println("protostuff - length: " + protostuffData.length);
        sb = new StringBuilder("protostuff: ");
        for (byte b : protostuffData) {
            sb.append(b).append(" ");
        }
        System.out.println(sb);


        User deserialize = javaSerializer.deserialize(javaData);

        System.out.println(deserialize);


    }

}
