package com.jincong.springboot.test.guava;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * GuavaTest
 * 列表中包含几个重复key的value，可以put进入不同value但是相同的key，后面放入的值不会覆盖前面的值
 * 类似Map<Integer, List<String>> 结构
 * 或者使用java8提供的group by功能
 * @author j_cong
 * @version V1.0
 * @date 2020/12/19
 */
public class GuavaTest {


    public static void main(String[] args) {
        Multimap<String,String> multimap = ArrayListMultimap.create();

        multimap.put("lower", "a");
        multimap.put("lower", "b");
        multimap.put("lower", "c");

        multimap.put("upper", "A");
        multimap.put("upper", "B");

        List<String> lowerList = (List<String>)multimap.get("lower");
        //输出key为lower的list集合
        System.out.println("输出key为lower的list集合=========");
        System.out.println(lowerList.toString());
        lowerList.add("f");
        System.out.println(lowerList.toString());


        Map<String, Collection<String>> map = multimap.asMap();
        System.out.println("把Multimap转为一个map============");
        for (Map.Entry<String,  Collection<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            Collection<String> value =  multimap.get(key);
            System.out.println(key + ":" + value);
        }

        System.out.println("获得所有Multimap的key值==========");
        Set<String> keys =  multimap.keySet();
        for(String key:keys){
            System.out.println(key);
        }

        System.out.println("输出Multimap所有的value值========");
        Collection<String> values = multimap.values();
        System.out.println(values);
    }


}
