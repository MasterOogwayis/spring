package com;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


/**
 * test
 *
 * @author ZhangShaowei on 2018/4/3 10:05
 **/
public class StaticTests {

    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();


    private static final String RANDOM_STR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final java.util.Random RANDOM = new java.util.Random();

    public static String getRandomStr() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            sb.append(RANDOM_STR.charAt(RANDOM.nextInt(RANDOM_STR.length())));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {

//        System.err.println(Boolean.valueOf("true"));

        Map<String, String> map = new HashMap<>();
        System.out.println(Base64.encodeBase64String(gson.toJson(map).getBytes()));
        String str = "eyJuaWNrTmFtZSI6Ik1hc3Rlck9vZ3dheSIsInNleCI6MSwiaGVhZEltZ1VybCI6Imh0dHA6Ly90aGlyZHd4LnFsb2dvLmNuL21tb3Blbi92aV8zMi9RMGo0VHdHVGZUSnlPbURWUFNBMHhmMDVNQW5VQ3dCcHFqOU9pYTJ2V3Bob2dsT3hBcWlibDYxU3JsZ1EwOWxyS1dlOHprWnNMazJQQnZuNE55TktzNHJRLzEzMiIsInJlYWxOYW1lIjoi5byg5bCR5LyfIiwibW9iaWxlIjoiMTgzKioqKjA5ODAiLCJpZENhcmQiOiI1MTAxODIqKioqKioqKjcwMzAiLCJiYXNlVXJsIjoiaHR0cDovL3d3dy5zY3lzd3ouY29tL3JlZmMiLCJ0b2tlbiI6IklWenFpa1BMMTZUQXFFYmVWTDl6Rnl2N2pKSGFUMlRWSk83TFVUekY2dCtGNzQrcHNLbXI1YURUSnI4dUlldklHNDRUYW1uK2x2TzlpWExWenZ2WW9RXHUwMDNkXHUwMDNkIn0";
        Map<String, String> data = gson.fromJson(
                new String(Base64.decodeBase64(str)),
                new TypeToken<HashMap<String, String>>() {
                }.getType());
        System.out.println(data);
        data.put("baseUrl", "http://localhost/");
        System.err.println(Base64.encodeBase64String(gson.toJson(data).getBytes()));

//        String path = "{\"nickName\":\"MasterOogway\",\"sex\":1,\"realName\":\"\",\"mobile\":\"183****0980\",\"idCard\":\"\",\"token\":\"ABJSAOlp2wik5AwvBp0hy4rOK0Sk+3a/4G+C4Y/aPBlBdY5t3qV1vVNzPSgk1TZ4msu+vA87pdvegXqwXR/dVQ\\u003d\\u003d\"}";
//        String data = Base64.encodeBase64String(path.getBytes());
//        System.err.println(data);
//        System.out.println(new String(Base64.decodeBase64(data)));

//        Files.createDirectories(Paths.get("/data/asdasdasd/"));
//        Files.copy(
//                Paths.get(path),
//                Paths.get("/data/asdasdasd/3.html"),
//                StandardCopyOption.REPLACE_EXISTING
//        );
    }

    public static byte[] readFileByBytes(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(filePath);
        } else {
            ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
            BufferedInputStream in = null;

            try {
                in = new BufferedInputStream(new FileInputStream(file));
                int bufSize = 1024;
                byte[] buffer = new byte[bufSize];
                boolean var6 = false;

                int len;
                while (-1 != (len = in.read(buffer, 0, bufSize))) {
                    bos.write(buffer, 0, len);
                }

                byte[] var7 = bos.toByteArray();
                return var7;
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException var14) {
                    var14.printStackTrace();
                }

                bos.close();
            }
        }
    }

    @Data
    @AllArgsConstructor
    static class A {
        protected String id;
    }

    @Data
    static class B extends A {
        private String name;

        public B(String id, String name) {
            super(id);
            this.name = name;
        }
    }

    @Data
    static class C {
        private String name;
    }

}
