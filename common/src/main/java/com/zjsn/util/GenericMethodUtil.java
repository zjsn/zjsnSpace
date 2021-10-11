package com.zjsn.util;

import com.zjsn.domain.user.UserMongoEntity;

import java.util.Arrays;

public class GenericMethodUtil {
    /**
     * 泛型方法
     *
     * @param printArray
     * @param <E>
     */
    public <E> void printArray(E[] printArray) {
        for (E e : printArray) {
            System.out.println(e.toString());
        }
    }

    public static int biSearch(int[] aimArray, int a) {
        // 开始位置
        int zero = 0;
        int listSize = aimArray.length-1;
        int mid;
        while(zero <= listSize) {
            mid = (zero + listSize) / 2; // 去中间位置
            if (aimArray[mid] == a) {// 如果中间位置等于 要的值就返回对应
                return mid + 1;
            } else if (aimArray[mid] > a) {// 如果中间位置大于 要的值的话就 总长度 -1
                listSize = mid -1;
            } else {// 如果中间位置大于 要的值的话就 开始的位置 等于中间位置 + 1
                zero = mid + 1;
            }
        }
        return -1;
    }

    public static int[] ppFun(int[] sourceList, int sortNum) {
        for (int i = 0; i < sortNum; i++) {
            for (int j = 1; j< sortNum - i; j++) {
                if (sourceList[j-1] > sourceList[j]) {
                    int temp;
                    temp = sourceList[j-1];
                    sourceList[j-1] = sourceList[j];
                    sourceList[j] = temp;
                }
            }
        }
        return sourceList;
    }

    public static int[] fastPP(int[] sourceList, int low, int high) {
        int start = low;
        int end = high;
        int key = sourceList[low];
        while (end > start) {
            // 从后往前比较
            while(end > start && sourceList[end] >= key)
                //如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
                end --;
                if (sourceList[end] <= key) {
                    int temp = sourceList[end];
                    sourceList[end] = sourceList[start];
                    sourceList[start] = temp;
                }

            // 从前往后比较
            while(end > start && sourceList[start] <= key)
                start ++;
                //如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
                if (sourceList[start] >= key) {
                    int temp = sourceList[start];
                    sourceList[start] = sourceList[end];
                    sourceList[end] = temp;
                }

            //此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的
            //值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
        }
        if (start > low) {
            fastPP(sourceList, low, start-1); //左边序列。第一个索引位置到关键值索引-1
        }
        if (end < high) {
            fastPP(sourceList, end +1, high); //右边序列。从关键值索引+1 到最后一个
        }
        return sourceList;
    }

    public static void main(String[] args) {
        int[] arr = {1,5,7,3,4,2,6,9,8,5};
        int[] ints = fastPP(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(ints));
    }

    /**
     * 盒子泛型类
     *
     * @param <T> 必须是 UserMongoEntity子类
     */
    public static class Box<T extends UserMongoEntity> {
        private  T t;
        public void add(T t) {
            this.t = t;
        }
        public T getT() {
            return this.t;
        }
    }
}
