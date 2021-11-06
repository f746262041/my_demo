package com.yypt.algorithm.sort;

import com.yypt.algorithm.utils.SortUtils;

public class BubbleSort {
    public static void sort(int[] nums) {
        System.out.println("Bubble..........");
        for (int i = nums.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    SortUtils.swap(nums, j, j + 1);
                }
            }
        }

    }
}
