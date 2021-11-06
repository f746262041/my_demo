package com.yypt.algorithm.sort;

import com.yypt.algorithm.utils.SortUtils;

public class InsertSort {
    public static void sort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0; j--) {
                if (nums[j] < nums[j - 1]) {
                    SortUtils.swap(nums, j, j - 1);
                }
            }
        }
    }
}
