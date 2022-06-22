package com.darren.center.algorithm2.class05;

import com.darren.center.algorithm2.class01.LogarithmMachine;

import java.util.Stack;

/**
 * <h3>algorithm</h3>
 * <p></p>
 * 快排 非递归版
 *
 * @author : Darren
 * @date : 2022年06月22日 08:31:59
 **/
public class Code03_QuickSortRecursiveAndUnrecursive {

    public static void main(String[] args) {
        int count = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean success = true;
        for (int i = 0; i < count; i++) {
            int[] var1 = LogarithmMachine.generateRandomArray(maxSize, maxValue);
            int[] var2 = LogarithmMachine.copyArray(var1);
            Code02_PartitionQuickSort.quickSort3(var1);
            quickSort3NotRecursion(var2);
            if (!LogarithmMachine.isEqual(var1, var2)) {
                success = false;
                LogarithmMachine.printArray(var1);
                LogarithmMachine.printArray(var2);
                break;
            }
        }
        System.out.println(success ? "success" : "failure");
    }


    /**
     * 快排3.0 非递归版本
     * 非递归实现：利用自己的栈代替系统栈﻿​
     */
    public static void quickSort3NotRecursion(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        int n = arr.length - 1;
        swap(arr, (int)(Math.random() * n), n);
        int[] range = partition(arr, 0, n);
        Stack<Info> stack = new Stack<>();
        stack.push(new Info(0, range[0] - 1));
        stack.push(new Info(range[0] + 1, n));
        while (!stack.isEmpty()){
            Info info = stack.pop();
            if (info.left < info.right){
                swap(arr, info.left + (int)(Math.random() * (info.right - info.left + 1)), info.right);
                range = partition(arr, info.left, info.right);
                stack.push(new Info(info.left, range[0] - 1));
                stack.push(new Info(range[0] + 1, info.right));
            }
        }
    }

    /**
     *
     * @param arr
     * @param l
     * @param r
     * @return 等于区域的下标
     */
    private static int[] partition(int[] arr, int l, int r){
        if (l == r){
            return new int[]{l, r};
        }
        int leftBoundary = l - 1;
        int rightBoundary = r;
        int index = l;
        while (index < rightBoundary){
            if (arr[index] == arr[r]){
                index++;
            }else if (arr[index] < arr[r]){
                swap(arr, index++, ++leftBoundary);
            }else {
                swap(arr, index, --rightBoundary);
            }
        }
        swap(arr, r, rightBoundary);
        return new int[]{leftBoundary + 1, rightBoundary};
    }

    private static class Info{
        private Integer left;
        private Integer right;

        public Info(Integer left, Integer right) {
            this.left = left;
            this.right = right;
        }

        public Integer getLeft() {
            return left;
        }

        public void setLeft(Integer left) {
            this.left = left;
        }

        public Integer getRight() {
            return right;
        }

        public void setRight(Integer right) {
            this.right = right;
        }
    }

    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

