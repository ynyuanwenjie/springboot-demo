package com.springboot.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/10.
 */
public class JacksonUtil {
    public static <T> T Json2Obj(String json, TypeReference<T> tr) {
        T obj = null;
        // write your code here
        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
        try {
            obj = mapper.readValue(json, tr);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }
    @SuppressWarnings({ "unchecked" })
    public static Map<String, Object> Json2Map(String json) {
        Map<String, Object> result = null;
        // write your code here
        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
        try {
            result = mapper.readValue(json, Map.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * Object to JSON in String
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    public static String Obj2Json(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(obj);
            return json;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
    /**
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static String keyVal2Json(Object... objs) {
        try {
            if(objs.length%2==0){
                ObjectMapper objectMapper = new ObjectMapper();
                Map map = new HashMap();
                for(int i=0;i<objs.length;i=i+2){
                    map.put(objs[i], objs[i+1]);
                }
                String json = objectMapper.writeValueAsString(map);
                return json;
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return "";
    }
    /**
     *
     * @return
     */
    public static String array2Json(String... params ){
        if(params.length%2 == 0){
            Map<String, Object> map = new HashMap<String, Object>(params.length/2);
            for(int i=0;i<params.length;i++){
                if(i%2==0)
                    map.put(params[i], params[i+1]);
            }
            return JacksonUtil.Obj2Json(map);
        }
        return null;
    }
}
