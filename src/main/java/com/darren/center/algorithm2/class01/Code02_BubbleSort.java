package com.darren.center.algorithm2.class01;

/**
 * <h3>spring-boot-2020-gitee</h3>
 * <p>m冒泡排序</p>
 * 一句话说清楚：
 * 从0位置开始，比较相邻两个位置的数，谁大谁往右交换，直到n-1位置，此时n-1位置搞定，依次搞定n-2位置，n-3位置……
 * 即
 * 1、相邻两个比较，谁大谁往右交换
 * 2、搞定最后一个数
 *
 * 稳定 时间复杂度：O（N^2）
 * @author : Darren
 * @date : 2022年02月13日 11:35:23
 **/
public class Code02_BubbleSort {

    public static void main(String[] args) {
        int count = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean success = true;
        for (int i = 0; i < count; i++) {
            int[] var1 = LogarithmMachine.generateRandomArray(maxSize, maxValue);
            int[] var2 = LogarithmMachine.copyArray(var1);
            bubbleSort(var1);
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

    public static void bubbleSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        //每次搞定end位置的数 保证end ~ n-1有序
        for (int end = arr.length - 1; end >= 0; end--){
            for (int i = 0; i < end; i++){
                //相邻两个比较，谁大谁往右交换
                if (arr[i] > arr[i+1]){
                    swap(arr, i, i + 1);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j){
        int x = arr[i];
        arr[i] = arr[j];
        arr[j] = x;
    }

}

