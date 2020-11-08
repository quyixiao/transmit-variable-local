package com.test;

import java.util.HashMap;
import java.util.Map;

public class TestMap {

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<String,Object>();
        System.out.println(map.isEmpty());
        map.put("1",1);
        System.out.println(map.isEmpty());

    }
}
