package boot_mq_consumer.demo;

import com.atguigu.boot.activemq.BootMQConsumerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@SpringBootTest(classes = BootMQConsumerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
class DemoApplicationTests {
    @Resource

    @Test
    void contextLoads() {
    }

}
