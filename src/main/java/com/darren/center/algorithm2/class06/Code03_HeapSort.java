package com.darren.center.algorithm2.class06;

import com.darren.center.algorithm2.class01.LogarithmMachine;

/**
 * <h3>algorithm</h3>
 * <p></p>
 * 1、构建大根堆
 * 2、最大值和最后的数交换，heapSize--
 * 3、调整大根堆﻿​
 * 4、重复2和3
 *
 * @author : Darren
 * @date : 2022年06月27日 08:16:29
 **/
public class Code03_HeapSort {

    public static void main(String[] args) {
        int count = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean success = true;
        for (int i = 0; i < count; i++) {
            int[] var1 = LogarithmMachine.generateRandomArray(maxSize, maxValue);
            int[] var2 = LogarithmMachine.copyArray(var1);
            heapSort(var1);
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

    public static void heapSort(int[] arr){
        if (arr == null || arr.length == 0){
            return;
        }

        int heapSize = arr.length;

        // O(N*logN)
        //因为每一层都需要往上看，每一层的数量不断的增加
        /*for (int i = 0; i < arr.length; i++) {
            //不断的堆中添加新的数
            heapInsert(arr, i);
        }*/

        // O(N)
        //因为每一层往下沉，每一层的数量不断的减少，最后一层数量最多的都不需要往下沉
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, heapSize);
        }

        swap(arr, 0, --heapSize);

        // O(N*logN)
        while (heapSize > 0){
            //一个新的数到堆顶了，下沉
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    // 新加进来的数，现在停在了index位置，请依次往上移动，
    // 移动到0位置，或者干不掉自己的父亲了，停！
    private static void heapInsert(int[] arr, int index){
        // [index]    [index-1]/2
        // index == 0
        while (arr[index] > arr[(index - 1) / 2]){
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    // 从index位置，往下看，不断的下沉
    // 停：较大的孩子都不再比index位置的数大；已经没孩子了
    private static void heapify(int[] arr, int index, int heapSize){
        int left = 2 * index + 1;
        // 如果有左孩子，有没有右孩子，可能有可能没有！
        while (left < heapSize){
            // 把较大孩子的下标，给largest（通过heapSize判断是否有右孩子）
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            // 自己和较大的孩子PK
            largest = arr[largest] > arr[index] ? largest : index;
            // 没PK过 break
            if (largest == index){
                break;
            }
            // index和较大孩子，要互换
            swap(arr, index, largest);
            // index来到较大孩子的下标位置
            index = largest;
            // 更新左孩子的下标
            left = 2 * index + 1;
        }
    }

    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}

