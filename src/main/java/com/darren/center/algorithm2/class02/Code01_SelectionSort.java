package com.darren.center.algorithm2.class02;

/**
 * <h3>spring-boot-2020-gitee</h3>
 * <p>选择排序</p>
 * 一句话说清楚：
 * 从0~n-1范围内找到最小值，放到0位置，从1~n-1范围内找到最小值，放到1位置，……
 * 即
 * 1、找到最小值
 * 2、交换到当前的位置
 *
 * 不稳定 时间复杂度：O（N^2）
 * @author : Darren
 * @date : 2022年02月13日 10:37:02
 **/
public class Code01_SelectionSort {


    public static void main(String[] args) {
        int count = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean success = true;
        for (int i = 0; i < count; i++) {
            int[] var1 = LogarithmMachine.generateRandomArray(maxSize, maxValue);
            int[] var2 = LogarithmMachine.copyArray(var1);
            selectedSort(var1);
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

    public static void selectedSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        //每次搞定i位置的数 从左往右 保证0 ~ i有序
        for(int i = 0; i < arr.length; i++){
            int minIndex = i;
            //从i ~ n-1范围内找到最小值的下标
            for (int j = i; j < arr.length; j++){
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    public static void selectedSort1(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        //为什么最后一个数不用再判断，因为执行到最后一个数，此时i已经来到了n-1位置，n-1 ~ n-1位置不需要找最小值
        for(int i = 0; i < arr.length - 1; i++){
            int minIndex = i;
            for (int j = i; j < arr.length; j++){
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    public static void selectedSort2(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        for(int i = 0; i < arr.length - 1; i++){
            int minIndex = i;
            //什么j要从i+1开始，因为范围是i~n-1，i位置不需要和i位置判断，当minIndex和i+1~n-1范围内每个值判断之后就找到了最小值，然后给到i位置，i++
            for (int j = i + 1; j < arr.length; j++){
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    public static void swap(int[] arr, int i, int j){
        int x = arr[i];
        arr[i] = arr[j];
        arr[j] = x;
    }

}

