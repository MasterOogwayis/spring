package com.map.gaode;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author ZhangShaowei on 2020/7/28 14:10
 */
@Slf4j
public class GaodeServiceApi {

    private static final RestTemplate restTemplate = new RestTemplate();

    private static final String key = "80f287a56964b0b3f6a62db5ad4b6049";

    private static final String privateKey = "10f66d815e5f082b0de83c79679bb022";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 驾车路线
     */
    @Test
    public void testDriving() {
        String uri = "https://restapi.amap.com/v3/direction/driving";
        Map<String, String> map = new TreeMap<>();
        map.put("key", key);
        map.put("origin", "104.07136,30.572175");
        map.put("destination", "103.073274,30.027273");
        sign(map);
        log.debug("request params :{}", map);

        String body = restTemplate.getForObject(uri + "?" + collectToString(map), String.class);
        log.debug("response: {}", body);
    }

    /**
     * 公交
     */
    @Test
    public void testDirectionTransitIntegrated() {
        String uri = "https://restapi.amap.com/v3/direction/transit/integrated";
        Map<String, String> map = new TreeMap<>();
        map.put("key", key);
        map.put("origin", "104.07136,30.572175");
        map.put("destination", "103.073274,30.027273");
        map.put("city", "成都市");
        map.put("cityd", "雅安市");
//        map.put("extensions", "base");
//        map.put("strategy", "0");
//        map.put("nightflag", "0");
//        map.put("date", LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE));
//        map.put("time", "");
        map.put("output", "JSON");
//        map.put("callback", "");
        sign(map);
        log.debug("request params :{}", map);

        String body = restTemplate.getForObject(uri + "?" + collectToString(map), String.class);
        log.debug("response: {}", body);
    }


    private Map<String, String> sign(Map<String, String> map) {
        String collect = collectToString(map) + privateKey;
        map.put("sig", DigestUtils.md5Hex(collect));
        return map;
    }

    private String collectToString(Map<String, String> map) {
        return map.entrySet().stream()
                .map(entity -> entity.getKey() + "=" + entity.getValue())
                .collect(Collectors.joining("&"));
    }

}
