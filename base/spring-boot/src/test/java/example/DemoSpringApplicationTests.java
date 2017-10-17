package example;

import com.boot.DemoSpringBootApplication;
import com.boot.persistence.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author ZhangShaowei on 2017/4/24 13:35
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = DemoSpringBootApplication.class)
public class DemoSpringApplicationTests {

    @Autowired
    private UserRepository userRepository;

//    @Test
    public void contextLoads() {
        System.err.println(this.userRepository.findByName("admin"));
    }

}
