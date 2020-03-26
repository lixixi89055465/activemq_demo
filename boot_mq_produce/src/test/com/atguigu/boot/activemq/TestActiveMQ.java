package com.atguigu.boot.activemq;

import com.atguigu.boot.activemq.produce.Queue_Produce;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

/**
 * @author lixiang
 * @date 2020-03-26 09:41
 */
@SpringBootTest(classes = BootMqProduceApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestActiveMQ {
    @Resource
    private Queue_Produce queue_produce;

    @Test
    public void testSend() {
        queue_produce.produceMsg();
    }

}
