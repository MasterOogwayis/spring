import com.UIFileApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ZhangShaowei on 2017/7/28 11:32
 */
@SpringBootTest(classes = UIFileApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestApplication {


    /**
     *
     */
    @Autowired
    private TestRestTemplate restTemplate;

    /**
     *
     */
    @Test
    public void test() {
        this.restTemplate.getForEntity(
                "/{username}/vehicle", String.class, "Phil");
    }


}
