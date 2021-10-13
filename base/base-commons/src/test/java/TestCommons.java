import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zsw.pojo.ApiResponse;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

/**
 * @author ZhangShaowei on 2018/11/20 11:18
 **/
@Slf4j
public class TestCommons {

    private static final Gson gson = new GsonBuilder().create();
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        //忽略没有的属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //允许没有双引号
        mapper.getDeserializationConfig().with(JsonReadFeature.ALLOW_UNQUOTED_FIELD_NAMES);
        //允许转义字符
        mapper.getDeserializationConfig().with(JsonReadFeature.ALLOW_UNQUOTED_FIELD_NAMES);
        //允许单引号
        mapper.getDeserializationConfig().with(JsonReadFeature.ALLOW_SINGLE_QUOTES);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    @SneakyThrows
    public static void main(String[] args) {
        String json = "{\"code\":0, \"data\":{\"name\":\"zsw\",\"address\":\"Earth\"}}";

        ApiResponse<A> aa = fromJson(json, A.class);
        ApiResponse<B> ab = fromJson(json, B.class);

        log.info("a = {}", gson.toJson(aa));
        log.info("b = {}", gson.toJson(ab));

        ApiResponse<A> aaa = mapper.readValue(json, type(A.class));
        ApiResponse<A> bbb = mapper.readValue(json, type(B.class));

        log.info("end ...");

    }


    public static <T> ApiResponse<T> fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[]{clazz};
            }

            @Override
            public Type getRawType() {
                return ApiResponse.class;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        });
    }

    public static <T> JavaType type(Class<T> clazz) {
        return mapper.getTypeFactory().constructParametricType(ApiResponse.class, clazz);
    }

    @Data
    @ToString
    static class A {
        private String name;
    }

    @Data
    @ToString
    static class B {
        private String address;
    }


}
