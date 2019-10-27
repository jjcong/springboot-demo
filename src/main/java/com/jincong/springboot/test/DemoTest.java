package com.jincong.springboot.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Junit4 单元测试
 *
 * @author  j_cong
 * @date    2019/08/11
 * @version V1.0
 */
public class DemoTest {

    @Test
    public void testString() {
        System.out.println("单元测试");
    }

    @Test
    public void testDebug() {
        List<String> testList = new ArrayList<>();
        testList.add("a");
        testList.add("b");
        testList.add("c");
        testList.add("d");

        testList.forEach(System.out::println);

        Map<Integer, String> testMap = new HashMap<>();
        testMap.put(1, "a");
        testMap.put(2, "b");
        testMap.put(3, "c");
        testMap.put(4, "d");

        for (Map.Entry entry : testMap.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }
}
