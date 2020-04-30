package configuration;

import java.io.IOException;
import java.util.Properties;

public class PropertiesDemo {

    public static void main(String[] args) throws IOException {

        Properties properties = new Properties();
        properties.setProperty("name", "zsw");
        properties.setProperty("age", "30");

        properties.storeToXML(System.out, "This is a comment", "UTF-8");

    }
}
