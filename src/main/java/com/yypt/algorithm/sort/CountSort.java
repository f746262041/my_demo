package com.yypt.algorithm.sort;

import java.util.Arrays;

/**
 * @Auther: IceBear
 * @Date: 2021/11/7 11:37
 * @Description:
 */
public class CountSort {
    public static void main(String[] args) {

    }

    /**
     * @param arr 源数组
     * @return
     */
    public static int[] sort(int[] arr) {
        //创建目标数组
        int[] result = new int[arr.length];

        // 创建计数数组
        int[] count = new int[10];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }
        System.out.println("count数组：" + Arrays.toString(count));
//        for (int i = 0, j = 0; i < count.length; i++) {
//            while (count[i]-- > 0) {
//                result[j++] = i;
//            }
//        }
        //过滤count数组
        for (int i = 1; i < count.length; i++) {
            count[i] = count[i] + count[i - 1];
        }
        System.out.println("count数组优化后：" + Arrays.toString(count));
        for (int i = arr.length - 1; i >= 0; i--) {
            result[--count[arr[i]]] = arr[i];

        }
        System.out.println(Arrays.toString(result));
        return result;
    }
}
