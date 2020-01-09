package com.jincong.springboot.test;

import org.junit.Test;

import java.util.*;

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

        System.out.println(testList);

        testList.forEach(System.out::println);

        Map<Integer, String> testMap = new HashMap<>(16);
        testMap.put(1, "a");
        testMap.put(2, "b");
        testMap.put(3, "c");
        testMap.put(4, "d");
        System.out.println(testMap);

        for (Map.Entry entry : testMap.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

    @Test
    public void printArr() {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i;
        }
        Arrays.stream(arr).forEach(System.out::println);

        System.out.println(Arrays.toString(arr));
    }
}
