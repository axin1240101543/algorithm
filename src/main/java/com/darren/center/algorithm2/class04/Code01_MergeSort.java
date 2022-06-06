package com.darren.center.algorithm2.class04;

import com.darren.center.algorithm2.class01.LogarithmMachine;

/**
 * <h3>algorithm</h3>
 * <p>归并排序</p>
 *
 * 时间复杂度O（N * logN）
 *
 * 原理：
 * 1、求中点M
 * 2、左部分有序f(arr, l, m)
 * 3、右部分有序f(arr, m + 1, r)
 * 4、mergin，合并左部分和右部分
 * T(N) = 2 * T(N / 2) + O(N)
 * Master公式：
 * log(b,a) == d
 * log(2,2) == 1
 *  O（N * logN）﻿
 *
 * mergin过程（O（N），指针没有回退）
 * 3，4，2，6，0，1
 * 左组 2 3 4（有序）
 * 右组 0 1 6（有序）
 * 准备一个辅助数组
 * 左指针2位置 右指针0位置
 * 谁小拷贝谁
 * 最后将辅助数据里面的0，1，2，3，4，6拷贝回原数组
 *
 * 2，3，6，1
 * 左组 2（有序）
 * 右组 3（有序）
 * 准备一个辅助数组
 * 左指针2位置 右指针3位置
 * 谁小拷贝谁
 * 最后将辅助数据里面的2，3拷贝回原数组
 *  ------
 * 左组 6（有序）
 * 右组 1（有序）
 * 准备一个辅助数组
 * 左指针6位置 右指针1位置
 * 谁小拷贝谁
 * 最后将辅助数据里面的1，6拷贝回原数组
 *  ------
 * 左组 2 3（有序）
 * 右组 1 6（有序）
 * 准备一个辅助数组
 * 左指针2位置 右指针3位置
 * 谁小拷贝谁
 * 最后将辅助数据拷贝回原数组﻿​
 *
 * 左组和右组必有一个越界，将剩下的数拷贝到辅助数组
 *
 * 为什么归并排序比选择排序、冒泡排序、插入排序好？
 * 因为他们这些排序大量浪费比较行为
 *
 * @author : Darren
 * @date : 2022年06月06日 08:37:46
 **/
public class Code01_MergeSort {

    public static void main(String[] args) {
        int count = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean success = true;
        for (int i = 0; i < count; i++) {
            int[] var1 = LogarithmMachine.generateRandomArray(maxSize, maxValue);
            int[] var2 = LogarithmMachine.copyArray(var1);
            sort(var1);
            LogarithmMachine.sort(var2);
            if (!LogarithmMachine.isEqual(var1, var2)){
                success = false;
                LogarithmMachine.printArray(var1);
                LogarithmMachine.printArray(var2);
                break;
            }
        }
        System.out.println(success ? "success" : "failure");
    }


    public static void sort(int[] arr){
        if (arr == null || arr.length == 0){
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int l, int r){
        if (l == r){
            return;
        }
        int m = l + ((r - l) >> 1);
        process(arr, l, m);
        process(arr, m + 1, r);
        merge(arr, l, m, r);
    }

    public static void merge(int[] arr, int l, int m, int r){
        //准备一个辅助数组
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r){
            //谁小拷贝谁
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        //左组和右组必有一个越界，将剩下的数拷贝到辅助数组
        while (p1 <= m){
            help[i++] = arr[p1++];
        }
        while (p2 <= r){
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++){
            arr[l + j] = help[j];
        }
    }

}