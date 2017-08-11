package com.springboot.start;

import com.springboot.SpringbootDemoApplication;
import com.springboot.model.Reservation;
import com.springboot.util.JacksonUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootDemoApplication.class)
@WebAppConfiguration
//@ContextConfiguration(classes = WebConfig.class)
//@WebAppConfiguration("src/test/webapp")
//@WebAppConfiguration("classpath:test-web-resources")
public class SpringbootDemoApplicationTests {
    private MediaType contentType = new MediaType("application",
            "json", Charset.forName("UTF-8"));
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    /**
     * 测试加载上下文
     */
    @Test
    public void contextLoads() {
    }

    @Before
    public void setup() {
        System.out.println("-------------------begin------------------------------------------");
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testMock() {
        System.out.println(this.mockMvc);
    }

    @Test
    public void testReadReservation() throws Exception {
        String urlTemplate = "reservation/name/jlong";
        mockMvc.perform(get(urlTemplate))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(content().json("{\"id\":8,\"reservationName\":\"jlong\"}"));
    }

    @Test
    public void testReadReservationJsonPath() throws Exception {
        String urlTemplate = "reservation/name/jlong";
        mockMvc.perform(get(urlTemplate))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is("8")))
                .andExpect(jsonPath("$.reservationName", is("jlong")));
    }

    @Test
    public void testReadReservationJsonPathSize() throws Exception {
        String urlTemplate = "reservation/name/jlong";
        mockMvc.perform(get(urlTemplate))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].id", is("8")))
                .andExpect(jsonPath("$[1].reservationName", is("jlong")));
    }

    @Test
    public void testSaveReservation() throws Exception {
        Reservation reservation = new Reservation(null,"TestSave111");
        String reservationJson = JacksonUtil.Obj2Json(reservation);
        this.mockMvc.perform(post("reservation/save")
                .contentType(contentType)
                .content(reservationJson))
                .andExpect(status().isOk());
    }

    @After
    public void tearDown() {
        System.out.println("-------------------end-------------------------------------");
    }
}