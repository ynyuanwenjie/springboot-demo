package com.springboot.start;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class SpringbootDemoApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Before
	public void setup() {
		System.out.println("-------------------begin---------------------");
	}
	@Test
	public void test(){

	}

	@After
	public void tearDown() {
		System.out.println("-------------------end---------------------");
	}
}