package com.yypt.algorithm.main;

import com.yypt.algorithm.sort.*;
import com.yypt.algorithm.utils.SortUtils;

import java.util.Arrays;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            for (int i = 0; i < n; i++) {
                dataChecker(30);
            }
        }
    }

    static void dataChecker(int n) {
        int[] originArr = SortUtils.generateRandomArray(n);
        int[] copyArr = Arrays.copyOf(originArr, n);
        System.out.println("原始数据:" + Arrays.toString(originArr));
        Arrays.sort(originArr);
        System.out.println("官方排序:" + Arrays.toString(originArr));
        SelectionSort.sort(copyArr);
        System.out.println("我的排序:" + Arrays.toString(copyArr));
        for (int i = 0; i < n; i++) {
            if (originArr[i] != copyArr[i]) {
                System.out.println("错误！");
                return;
            }

        }
        System.out.println("正确");
    }


}
