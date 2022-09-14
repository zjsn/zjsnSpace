package com.zjsn.user.util;

import java.util.Arrays;

public class BubbleSort {
    private int[] arr;

    public BubbleSort(int[] arr) {
        this.arr = arr;
    }

    public int[] sortFun() {
        for (int i = 0 ; i < arr.length - 1 ; i++) {
            boolean isSorted = false;
            for (int j = 0 ; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j , j + 1);
                    isSorted = true;
                }
            }
            if (!isSorted) {
                break;
            }
        }
        return this.arr;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
         BubbleSort bubbleSort = new BubbleSort(new int[]{1, 2, 5, 7, 8, 9, 4, 2, 5, 7, 9});
        System.out.println(Arrays.toString(bubbleSort.sortFun()));
    }
}
