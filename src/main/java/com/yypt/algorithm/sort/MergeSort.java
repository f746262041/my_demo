package com.yypt.algorithm.sort;

public class MergeSort {
    public static void sort(int[] arr) {
        System.out.println("Mergesort");
        sort(arr, 0, arr.length - 1);
    }

    static void sort(int[] arr, int left, int right) {
        if (left < right) {
            //分两半
            int mid = (left + right) / 2;
            //排左边
            sort(arr, left, mid);
            //排右边
            sort(arr, mid + 1, right);
            //合并
            merge(arr, left, mid + 1, right);

        }


    }

    public static void merge(int[] arr, int left, int right, int boundary) {
        int mid = right - 1;
        int[] temp = new int[boundary - left + 1];
        int k = 0;
        int i = left;
        int j = right;

        while (i <= mid && j <= boundary) {
            if (arr[i] <= arr[j]) {
                temp[k] = arr[i];
                k++;
                i++;
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= boundary) {
            temp[k++] = arr[j++];
        }

        for (int m = 0; m < temp.length; m++) {
            arr[left + m] = temp[m];
        }

    }

}
