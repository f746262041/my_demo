package com.yypt.algorithm.sort;

import com.yypt.algorithm.utils.SortUtils;

public class ShellSort {
    public static void sort(int[] nums) {
        System.out.println("shellSort....");
        int h = 1;
        while ((3 * h + 1) < nums.length) {
            h = 3 * h + 1;
        }

        for (int gap = h; gap > 0; gap = (gap - 1) / 3) {
            for (int i = gap; i < nums.length; i++) {
                for (int j = i; j > gap - 1; j -= gap) {
                    if (nums[j] < nums[j - gap]) {
                        SortUtils.swap(nums, j, j - gap);
                    }
                }
            }

        }

    }

}
