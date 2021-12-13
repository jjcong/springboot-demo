package com.jincong.springboot.utils;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author j_cong
 * @version V1.0
 * @date 2021/12/13
 */
@Slf4j
public class PartitionUtil {


    /**
     * 默认每个分组的元素个数
     */
    private static final int DEFAULT_PARTITION_SIZE = 500;


    /**
     * 分批访问接口，聚合结果返回
     *
     * @param partitionSize 分组中的元素个数
     * @param allList       所有的列表
     * @param function      子访问入口
     * @return 聚合结果
     */
    public static <T, R> List<R> partitionQuery(int partitionSize, List<T> allList, Function<List<T>, List<R>> function) {
        if (CollectionUtil.isEmpty(allList)) {
            log.warn("all data is empty");
            return Collections.emptyList();
        }

        List<List<T>> partitionList = Lists.partition(allList, partitionSize);
        List<R> result = Lists.newArrayList();

        for (List<T> partition : partitionList) {
            List<R> apply = function.apply(partition);
            if (CollectionUtil.isNotEmpty(apply)) {
                result.addAll(apply);
            }

        }
        return result;
    }

    public static <T, R> List<R> partitionQuery(List<T> allList, Function<List<T>, List<R>> function) {
        return PartitionUtil.partitionQuery(DEFAULT_PARTITION_SIZE, allList, function);
    }


    /**
     * 分批处理接口
     *
     * @param partitionSize 分组中的元素个数
     * @param allList       所有的列表
     * @param consumer      子访问入口
     */
    public static <T> void partitionHandle(int partitionSize, List<T> allList, Consumer<List<T>> consumer) {
        if (CollectionUtil.isEmpty(allList)) {
            log.warn("all data is empty");
            return;
        }
        Lists.partition(allList, partitionSize).forEach(consumer);
    }


    public static <T> void partitionHandle(List<T> allList, Consumer<List<T>> consumer) {
        PartitionUtil.partitionHandle(DEFAULT_PARTITION_SIZE, allList, consumer);
    }
}
