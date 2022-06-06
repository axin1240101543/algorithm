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
 * 迭代版本：
 * 3，2，1，4，6，0，7，5
 * 准备一个变量，步长1 2 4 8 16 ... N-1
 * 步长为1
 * 左组：3
 * 右组：2
 * merge：2，3
 * ------
 * 左组：1
 * 右组：4
 * merge：1，4
 * ------
 * 左组：6
 * 右组：0
 * merge：0，6
 * ------
 * 左组：7
 * 右组：5
 * merge：5，7
 * -----------------
 * 步长为2
 * 左组：2，3
 * 右组：1，4
 * merge：1，2，3，4
 * ------
 * 左组：0，6
 * 右组：5，7
 * merge：0，5，6，7
 * -----------------
 * 步长为4
 * 左组：1，2，3，4
 * 右组：0，5，6，7
 * merge：0，1，2，3，4，5，6，7
 *
 * 左组不够或者没有右组，直接跳过
 *
 * 边界：
 * 1、步长小于N，超过N结束排序
 * 2、L（左组第一个位置）小于N，超过N本次步长结束
 * 3、M（左组最后一个位置）大于等于N，跳过，说明左组不够
 * 4、R（右组最后一个位置） = M + 1 + 步长  或者 N - 1(不够)
 * 5、步长大于N/2，跳出，防止溢出
 * 6、L ... M，M + 1... R﻿​
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
//            sort(var1);
            commonSort(var1);
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

    public static void commonSort(int[] arr){
        if (arr == null || arr.length == 0){
            return;
        }
        //步长
        int stepLength = 1;
        int n = arr.length;
        //步长小于N，超过N结束排序
        while (stepLength < n){
            int l = 0;
            //L（左组第一个位置）小于N，超过N本次步长结束
            while (l < n){
                int m = l + stepLength - 1;
                //M（左组最后一个位置）大于等于N，跳过，说明左组不够
                if (m >= n){
                    break;
                }
                //R（右组最后一个位置） = M + 1 + 步长  或者 N - 1(不够)
                int r = Math.min(n - 1, m + stepLength);
                merge(arr, l, m, r);
                //l来到下一组的位置
                l = r + 1;
            }
            //防止溢出 stepLength * 2 可能超过int的最大值
            if (stepLength > n/2){
                break;
            }
            stepLength <<= 1;
        }
    }

}