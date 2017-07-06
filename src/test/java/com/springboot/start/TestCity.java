package com.springboot.start;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.geom.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.springboot.model.*;
import com.springboot.model.Area;
import com.springboot.service.CityRepository;
import com.springboot.util.FirstLetterUtil;
import net.minidev.json.JSONUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//http://www.stats.gov.cn/tjsj/tjbz/xzqhdm/201703/t20170310_1471429.html
//http://www.ccb.com/cn/OtherResource/bankroll/html/code_help.html

/**
 * Created by yuanwenjie on 2017/5/31.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCity {

    @Autowired
    private CityRepository cityRepository;

    @Before
    public void setup(){
        System.out.println("before---------->");
        System.out.println(cityRepository);
    }


    @Test
    public void saveAreaProvince() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Area> list =  mapper.readValue(new File("C:\\document\\project\\TRS\\springboot-demo\\src\\main\\resources\\areas.json"),new TypeReference<List<Area>>(){});
            if(list.size() >0) {
                for(int i=0;i<list.size();i++) {
                    CityEntity cp = new CityEntity();
                    Long code = Long.parseLong(list.get(i).getCode());
                    cp.setId(code);
                    cp.setName(list.get(i).getName());
                    cp.setpId(Long.parseLong(list.get(i).getParent_code()));
                    cp.setLevel(3);
                    cp.setDisCode(FirstLetterUtil.getFirstLetter(list.get(i).getName()));
                    cp.setCreateTime(new Date());
                    cityRepository.save(cp);
                }
            }
        } catch (IOException e) {
            System.out.println("exception----------"+e);
        }
    }

    @Test
    public void areaToJson() {
        ObjectMapper mapper = new ObjectMapper();
        List<Area> list = new ArrayList<>();
        Area area = new Area();
        area.setName("X");
       // area.setParent_code("000");
        area.setCode("10001");
        list.add(area);
        //Object to JSON in file
       /* try {
            mapper.writeValue(new File("c:\\file.json"), area);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        //Object to JSON in String
        try {
            String jsonInString = mapper.writeValueAsString(list);
            System.out.println(jsonInString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }


}
