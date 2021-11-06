package com.yypt.algorithm.sort;

import com.yypt.algorithm.utils.SortUtils;

public class SelectionSort {
    public static void sort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int minPos = i;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] < nums[minPos]) {
                    minPos = j;
                }
            }
            SortUtils.swap(nums, i, minPos);
        }
    }
}
