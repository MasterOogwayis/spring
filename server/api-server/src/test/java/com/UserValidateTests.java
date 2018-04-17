package com;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Tests
 *
 * @author ZhangShaowei on 2018/4/12 14:07
 **/
public class UserValidateTests {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(UserValidateTests.class);

    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    private static final String PATH = "C:\\Users\\ZhangShaowei\\Desktop\\xxx.txt";
    private static final String PATH_DETAILS = "C:\\Users\\ZhangShaowei\\Desktop\\openids_details.txt";

    private static final String URI = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";

    private static final String ACCESS_TOKEN = "8_6G0QlhuATgihn9LB2HJKDt3waS-t3VBRnvchsokBLN0bkxUWDU1S3Yfnj2-5LKlA9ua8SPItetQ95FxP4RTayU7sfqVCgh-qiaYDtcaQg5uDx6xsQxIful9cMPM6Niqvsua3jQcIXLLeb5cJAICbAEARZT";

    private static final RestTemplate restTemplate = new RestTemplate();

    static {
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
    }

    public static void main(String[] args) throws IOException {

        List<String> openIds = Files.readAllLines(Paths.get(PATH), Charset.forName("utf-8"));

//        logger.info("openIds: {}", openIds);

        Map<String, String> sourceData = openIds.stream()
                .collect(Collectors.toMap(str -> str.split("\\|")[0], str -> str.split("\\|")[1]));

        List<UserParams> userList = sourceData.keySet().stream()
                .map(openId -> new UserParams(openId, "zh_CN"))
                .collect(Collectors.toList());

        List<UserParams> params = new ArrayList<>();
        List<WxMpUser> totals = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            params.add(userList.get(i));
            if ((i + 1) % 100 == 0 || (i + 1) == userList.size()) {
                Map<String, List<UserParams>> map = new HashMap<>();
                map.put("user_list", params);
                String result = restTemplate.postForObject(
                        URI.replace("ACCESS_TOKEN", ACCESS_TOKEN),
                        gson.toJson(map),
                        String.class);
//                logger.info("result: {}  size: {}", result, params.size());
                JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
                List<WxMpUser> users = gson.fromJson(jsonObject.get("user_info_list"), new TypeToken<List<WxMpUser>>() {
                }.getType());
                totals.addAll(users);
                params = new ArrayList<>();
            }
        }

        Path details = Paths.get(PATH_DETAILS);

        if (!Files.exists(details)) {
            Files.createFile(details);
        }
        totals.sort(Comparator.comparing(WxMpUser::getSubscribe));
        totals.forEach(user -> {
            user.setOpenid(user.getOpenid() + "|" + sourceData.get(user.getOpenid()));
        });


        logger.info("size: {}", totals.size());
        Files.write(details, totals.stream().map(WxMpUser::toString).collect(Collectors.toList()), Charset.forName("utf-8"));
    }


    static class UserParams {
        private String openid;
        private String lang;

        /**  */
        public String getOpenid() {
            return openid;
        }

        /**  */
        public void setOpenid(String openid) {
            this.openid = openid;
        }

        /**  */
        public String getLang() {
            return lang;
        }

        /**  */
        public void setLang(String lang) {
            this.lang = lang;
        }

        public UserParams(String openid, String lang) {
            this.openid = openid;
            this.lang = lang;
        }

        @Override
        public String toString() {
            return "UserParams{" +
                    "openid='" + openid + '\'' +
                    '}';
        }
    }

    static class WxMpUser implements Serializable {
        private static final long serialVersionUID = 5788154322646488738L;

        private Integer subscribe;
        private String openid;
        private String nickname;
        /**
         * 性别描述信息：男、女、未知等.
         */
        private String sexDesc;
        /**
         * 性别表示：1，2等数字.
         */
        private Integer sex;
        private String language;
        private String city;
        private String province;
        private String country;
        private String headImgUrl;
        private Long subscribe_time;

        private Integer qr_scene;

        private String qr_scene_str;

        /**  */
        public Integer getSubscribe() {
            return subscribe;
        }

        /**  */
        public void setSubscribe(Integer subscribe) {
            this.subscribe = subscribe;
        }

        /**  */
        /**  */
        public String getOpenid() {
            return openid;
        }

        /**  */
        public void setOpenid(String openid) {
            this.openid = openid;
        }

        /**  */
        public String getNickname() {
            return nickname;
        }

        /**  */
        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        /**  */
        public String getSexDesc() {
            return sexDesc;
        }

        /**  */
        public void setSexDesc(String sexDesc) {
            this.sexDesc = sexDesc;
        }

        /**  */
        public Integer getSex() {
            return sex;
        }

        /**  */
        public void setSex(Integer sex) {
            this.sex = sex;
        }

        /**  */
        public String getLanguage() {
            return language;
        }

        /**  */
        public void setLanguage(String language) {
            this.language = language;
        }

        /**  */
        public String getCity() {
            return city;
        }

        /**  */
        public void setCity(String city) {
            this.city = city;
        }

        /**  */
        public String getProvince() {
            return province;
        }

        /**  */
        public void setProvince(String province) {
            this.province = province;
        }

        /**  */
        public String getCountry() {
            return country;
        }

        /**  */
        public void setCountry(String country) {
            this.country = country;
        }

        /**  */
        public String getHeadImgUrl() {
            return headImgUrl;
        }

        /**  */
        public void setHeadImgUrl(String headImgUrl) {
            this.headImgUrl = headImgUrl;
        }

        /**  */
        public Long getSubscribe_time() {
            return subscribe_time;
        }

        /**  */
        public void setSubscribe_time(Long subscribe_time) {
            this.subscribe_time = subscribe_time;
        }

        /**  */
        public Integer getQr_scene() {
            return qr_scene;
        }

        /**  */
        public void setQr_scene(Integer qr_scene) {
            this.qr_scene = qr_scene;
        }

        /**  */
        public String getQr_scene_str() {
            return qr_scene_str;
        }

        /**  */
        public void setQr_scene_str(String qr_scene_str) {
            this.qr_scene_str = qr_scene_str;
        }

        @Override
        public String toString() {
            return openid + " " + subscribe + " "
                    + (Objects.nonNull(subscribe_time) ? dateTimeFormat(subscribe_time) : subscribe_time)
                    + " " + qr_scene + " " + qr_scene_str + " " + nickname;
        }
    }

    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String dateTimeFormat(Long time) {
        return LocalDateTime.ofEpochSecond(
                time,
                0,
                ZoneOffset.ofHours(8)).format(TIMESTAMP_FORMAT);
    }

}
