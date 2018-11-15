package com.graduation.design.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.*;

/**
 * @author xuweizhi
 */
@Component
@ConfigurationProperties(prefix = "users")
public class Users {

    private String name;

    private Integer[] arrays;

    private Integer age;

    private List<String> list1 = new ArrayList<>();

    private List<Map<String, String>> list2 = new ArrayList<>();

    private Map<String, String> map1 = new HashMap<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer[] getArrays() {
        return arrays;
    }

    public void setArrays(Integer[] arrays) {
        this.arrays = arrays;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getList1() {
        return list1;
    }

    public void setList1(List<String> list1) {
        this.list1 = list1;
    }

    public List<Map<String, String>> getList2() {
        return list2;
    }

    public void setList2(List<Map<String, String>> list2) {
        this.list2 = list2;
    }

    public Map<String, String> getMap1() {
        return map1;
    }

    public void setMap1(Map<String, String> map1) {
        this.map1 = map1;
    }

    @Override
    public String toString() {
        return "Users{" +
                "name='" + name + '\'' +
                ", arrays=" + Arrays.toString(arrays) +
                ", age=" + age +
                ", list1=" + list1 +
                ", list2=" + list2 +
                ", map1=" + map1 +
                '}';
    }

}
