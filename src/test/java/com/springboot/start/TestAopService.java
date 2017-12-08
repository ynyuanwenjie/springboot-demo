package com.springboot.start;

import com.springboot.SpringbootDemoApplication;
import com.springboot.service.MessageCommunicator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootDemoApplication.class)
@WebAppConfiguration
public class TestAopService {
    @Autowired
    private MessageCommunicator messageCommunicator;


    @Before
    public void setup(){
    }
    @Test
    public void test01() {
        messageCommunicator.deliver("Wanna learn AspectJ?");
    }

}
