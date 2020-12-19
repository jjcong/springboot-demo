package com.jincong.springboot.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 封装的List方法
 *
 * @author j_cong
 * @version V1.0
 * @date 2020/07/01
 */
public class ListUtil {

    /**
     * 将List按照指定数量分组
     *
     * @param srcList 源集合
     * @param size    每组元素个数
     * @param <T>
     * @return 集合分组
     */
    public static <T> List<List<T>> groupBySize(List<T> srcList, int size) {
        if (null == srcList || srcList.size() == 0 || size <= 0) {
            return null;
        }

        List<List<T>> result = new ArrayList<>();
        int remainder = srcList.size() % size;
        int group = srcList.size() / size;

        for (int i = 0; i < group; i++) {
            result.add(srcList.subList(i * size, (i + 1) * size));
        }
        if (remainder > 0) {
            result.add(srcList.subList(group * size, group * size + remainder));
        }

        return result;
    }


    /**
     * 将List按照指定数量分组
     *
     * @param srcList 源集合
     * @param size    每组元素个数
     * @param <T>
     * @return 集合分组
     */
    public static <T> List<List<T>> averageList(List<T> srcList, int size) {
        if (null == srcList || srcList.size() == 0 || size <= 0) {
            return null;
        }

        List<List<T>> result = new ArrayList<>();
        int remainder = srcList.size() % size;
        int group = srcList.size() / size;
        int offset = 0;

        for (int i = 0; i < size; i++) {
            List<T> tempList = null;
            if (remainder > 0) {
                tempList = srcList.subList(i * group + offset, (i + 1) * group + offset + 1);
            } else {
                tempList = srcList.subList(i * group + offset, (i + 1) * group + offset);
            }
            result.add(tempList);
        }

        return result;
    }




    /**
     * 判断对象是否为null（包括属性全为null的场景）
     * @param obj 代判断的对象
     * @return true or false
     */
    public static boolean isAllFieldsNull(Object obj){
        // 得到类对象
        Class stuCla = (Class) obj.getClass();
        //得到属性集合
        Field[] fs = stuCla.getDeclaredFields();
        boolean flag = true;
        //遍历属性
        for (Field f : fs) {

            try {
                // 设置属性是可以访问的(私有的也可以)
                f.setAccessible(true);
                Object val = f.get(obj);
                if(val!=null) {//只要有1个属性不为空,那么就不是所有的属性值都为空
                    flag = false;
                    break;
                }
            } catch (Exception e) {
                return flag;
            }

        }
        return flag;
    }




    /**
     * 每个长 SQL 插入的行数，可以根据数据库性能调整
     */
    private final static int SIZE = 1000;

    /**
     * 如果需要调整并发数目，修改下面方法的第二个参数即可
     */
    static {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "4");
    }

    /**
     * 插入方法
     *
     * @param list     插入数据集合
     * @param consumer 消费型方法，直接使用 mapper::method 方法引用的方式
     * @param <T>      插入的数据类型
     */
    public static <T> void insertData(List<T> list, Consumer<List<T>> consumer) {

        if (list == null || list.size() < 1) {
            return;
        }

        List<List<T>> streamList = new ArrayList<>();

        for (int i = 0; i < list.size(); i += SIZE) {
            int j = Math.min((i + SIZE), list.size());
            List<T> subList = list.subList(i, j);
            streamList.add(subList);
        }
        // 并行流使用的并发数是 CPU 核心数，不能局部更改。全局更改影响较大，斟酌
        streamList.parallelStream().forEach(consumer);
    }

}
