package com.yypt.algorithm.sort;

import java.util.Arrays;

/**
 * @Auther: IceBear
 * @Date: 2021/11/7 14:28
 * @Description:
 */
public class RadixSort {
    public static void main(String[] args) {
        System.out.println(Math.pow(2, 10));
    }


    public static void sort(int[] arr) {
        int[] temp = new int[arr.length];
        int[] count = new int[10];
        int max = findMax(arr);
        int maxDigitCapacity = getMaxDigitCapacity(max);

        for (int i = 0; i < maxDigitCapacity; i++) {
            int division = (int) Math.pow(10, i);
            for (int j = 0; j < arr.length; j++) {
                int num = arr[j] / division % 10;
                count[num]++;

            }
            System.out.println(Arrays.toString(count));
            for (int j = 1; j < count.length; j++) {
                count[j] = count[j] + count[j - 1];
            }
            System.out.println(Arrays.toString(count));

            for (int j = arr.length - 1; j >= 0; j--) {
                int num = arr[j] / division % 10;
                temp[--count[num]] = arr[j];
            }
            System.arraycopy(temp, 0, arr, 0, arr.length);


            Arrays.fill(count, 0);

        }

    }

    public static int getMaxDigitCapacity(int num) {
        int n = 1;
        int k = 0;
        while (num / n % 10 > 0) {
            n *= 10;
            k++;
        }
        return k;
    }

    ;

    public static int findMax(int[] arr) {
        int max = arr[0];
        for (int val : arr) {
            if (max < val) {
                max = val;
            }
        }
        return max;
    }
}
