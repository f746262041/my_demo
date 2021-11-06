package com.yypt.algorithm.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SortUtils {
    public static void main(String[] args) {
        int[] arr = generateRandomArray(10);
        System.out.println(Arrays.toString(arr));
    }

    public static void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    public static int[] generateRandomArray(int n) {
        int[] ints = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            ints[i] = random.nextInt(n);
        }
        return ints;
    }
}
