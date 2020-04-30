package configuration;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.context.support.StandardServletEnvironment;

/**
 * @author ZhangShaowei
 */
public class GenericConfiguration {

    public static void main(String[] args) {

        testApacheCommonConfiguration();
        testEnvironment();

    }

    private static void println(Object object) {
        System.out.println(object);
    }


    public static void testBase() {
        println(System.getProperty("user.home"));

        println(System.getProperty("user.age", "0"));
        // 将 System Properties 转换为 Integer 类型
        println(Integer.getInteger("user.age", 0));
        println(Boolean.getBoolean("user.male"));
    }


    public static void testApacheCommonConfiguration() {
        Configuration configuration = new CompositeConfiguration();
        System.out.println(configuration.getString("user.male"));;
    }


    public static void testEnvironment() {
        Environment environment = new StandardServletEnvironment();
        System.err.println(environment.getProperty("user.male"));
    }

}
