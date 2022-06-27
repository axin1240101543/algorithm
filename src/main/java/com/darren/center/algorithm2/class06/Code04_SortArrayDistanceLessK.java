package com.darren.center.algorithm2.class06;

import com.darren.center.algorithm2.class01.LogarithmMachine;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 * 一个无序数组，有个规律，排序过程中，每个数移动的距离不超过k，怎么排？
 *
 * 准备一个小根堆，堆的大小是k + 1，每次弹一个加一个，弹出时就是最小值﻿​
 * 没有的加，全部弹完
 *
 * 时间复杂度:O(N * logK)
 * 额外空间复杂度O（k + 1）
 *
 * @author : Darren
 * @date : 2022年06月27日 08:40:40
 **/
public class Code04_SortArrayDistanceLessK {

    // for test
    public static void main(String[] args) {
        System.out.println("test begin");
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int k = (int) (Math.random() * maxSize) + 1;
            int[] arr = randomArrayNoMoveMoreK(maxSize, maxValue, k);
            int[] arr1 = LogarithmMachine.copyArray(arr);
            int[] arr2 = LogarithmMachine.copyArray(arr);
            sortedArrDistanceLessK(arr1, k);
            sortedArrDistanceLessK1(arr2, k);
            if (!LogarithmMachine.isEqual(arr1, arr2)) {
                succeed = false;
                System.out.println("K : " + k);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

    public static void sortedArrDistanceLessK(int[] arr, int k) {
        if (k == 0){
            return;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        //先创建k个数大小的堆 0 ... k-1
        for (; index <= Math.min(arr.length - 1, k - 1); index++){
            heap.add(arr[index]);
        }

        int i = 0;
        for (; index < arr.length; index++) {
            // 保证堆的规模是k + 1
            heap.add(arr[index]);
            arr[i++] = heap.poll();
        }

        //没有的加，全部弹完
        while (!heap.isEmpty()){
            arr[i++] = heap.poll();
        }
    }


    public static void sortedArrDistanceLessK1(int[] arr, int k) {
        if (k == 0) {
            return;
        }
        // 默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        // 0...K-1
        for (; index <= Math.min(arr.length - 1, k - 1); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length; i++, index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }

    public static int[] randomArrayNoMoveMoreK(int maxSize, int maxValue, int K) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        // 先排个序
        Arrays.sort(arr);
        // 然后开始随意交换，但是保证每个数距离不超过K
        // swap[i] == true, 表示i位置已经参与过交换
        // swap[i] == false, 表示i位置没有参与过交换
        boolean[] isSwap = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int j = Math.min(i + (int) (Math.random() * (K + 1)), arr.length - 1);
            if (!isSwap[i] && !isSwap[j]) {
                isSwap[i] = true;
                isSwap[j] = true;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        return arr;
    }
}

