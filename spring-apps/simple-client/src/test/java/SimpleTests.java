import lombok.Getter;
import lombok.Setter;

/**
 * @author ZhangShaowei on 2020/7/7 13:24
 */
public class SimpleTests {

    public static void main(String[] args) {
        Dto dto = new Dto();


    }


    @Getter
    @Setter
    static class Dto {
        private Long id;
        private String name;
        private String address;
        private Integer age;
        private String email;
    }

}
