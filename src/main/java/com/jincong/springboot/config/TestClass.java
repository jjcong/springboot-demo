package com.jincong.springboot.config;

import java.util.*;

/**
 * TestClass
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/5/22
 */
public class TestClass {

    public static int duplicate (int[] numbers) {
        int result = -1;
        if(null == numbers || numbers.length == 0) {
            return result;
        }

        for(int i = 0; i<numbers.length; i++) {

            int num = Math.abs(numbers[i]);

            if(numbers[num] >= 0) {
                numbers[num] *= -1;
            } else{
                result = num;
                break;
            }

        }

        return result;

    }

    public static List<Integer> duplicate1 (int[] numbers) {


        List<Integer> result = new ArrayList<>();
        if(null == numbers || numbers.length == 0) {
            return result;
        }
        Set<Integer> set = new HashSet<>();

        Arrays.stream(numbers).forEach(num -> {
            if (!set.add(num)) {
                result.add(num);
            }
        });

        for (int number : numbers) {

        }

        return result;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{2,3,1,0,2,5,3};
        System.out.println(duplicate(arr));
    }
}
