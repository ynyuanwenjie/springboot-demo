package com.springboot.start;

import com.springboot.SpringbootDemoApplication;
import com.springboot.annotation.Log;
import com.springboot.service.AopService;
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
    private AopService aopService;

    @Test
    public void test01() {
        //aopService.findByCustName("baoliping");
        testAop();
    }

    @Log
    public void testAop() {
        System.out.println("test aop");
    }
}
