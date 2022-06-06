package com.darren.center.algorithm2.class01;

/**
 * <h3>spring-boot-2020-gitee</h3>
 * <p>插入排序</p>
 * 一句话说清楚：
 * 当前位置的数往前看，大则交换，交换后继续往前看，大则交换，直到不比他大或者0位置越界，搞定当前数左边的范围内有序
 * 即
 * 1、往前看，大则交换，直到不比他大或者0位置越界
 * 2、搞定当前数左边的范围
 *
 * 举例：手里拿了一堆牌，新抽了一张牌插入到合适的位置
 *
 * 和冒泡区别：冒泡的流程不受数据状况影响，即数据状况好是O（N^2），差也是O（N^2），但插入排序可以做的最好的情况是O（N），最差的情况是O（N^2）。
 *
 * 稳定 时间复杂度：O（N^2）
 * @author : Darren
 * @date : 2022年02月13日 11:54:53
 **/
public class Code03_InsertionSort {

    public static void main(String[] args) {
        int count = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean success = true;
        for (int i = 0; i < count; i++) {
            int[] var1 = LogarithmMachine.generateRandomArray(maxSize, maxValue);
            int[] var2 = LogarithmMachine.copyArray(var1);
            insertionSort(var1);
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

    public static void insertionSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        //保证
        //0 ~ 0 范围有序（默认就搞定）
        //0 ~ 1 范围有序
        //0 ~ 2 范围有序
        //……
        //0 ~ n - 1 范围有序
        for(int i = 1; i < arr.length; i++){
            //j + 1是当前数，j是前面一个数，两个相邻的数比较，谁小谁往左，直到j<0越界
            for (int j = i - 1; j >=0 && arr[j] > arr[j + 1]; j--){
                swap(arr, j, j + 1);
            }
        }
    }

    public static void swap(int[] arr, int i, int j){
        int x = arr[i];
        arr[i] = arr[j];
        arr[j] = x;
    }

}

