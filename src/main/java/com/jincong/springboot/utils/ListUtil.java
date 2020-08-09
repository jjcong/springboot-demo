package com.jincong.springboot.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装的List方法
 *
 * @author  j_cong
 * @date    2020/07/01
 * @version V1.0
 */
public class ListUtil {

    /**
     * 将List按照指定数量分组
     * @param srcList  源集合
     * @param size     每组元素个数
     * @param <T>
     * @return  集合分组
     */
    public static <T>List<List<T>> groupBySize(List<T> srcList, int size) {
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
     * @param srcList  源集合
     * @param size     每组元素个数
     * @param <T>
     * @return  集合分组
     */
    public static <T>List<List<T>> averageList(List<T> srcList, int size) {
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

}
