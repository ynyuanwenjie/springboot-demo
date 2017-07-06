package com.springboot.model;

/**
 * Created by yuanwenjie on 2017/6/1.
 */
public class Area {

    private String code;
    private String name;

    private String parent_code;

    public Area() {
    }

    public Area(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent_code() {
        return parent_code;
    }

    public void setParent_code(String parent_code) {
        this.parent_code = parent_code;
    }

    @Override
    public String toString() {
        return "Area{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", parent_code='" + parent_code + '\'' +
                '}';
    }
}
