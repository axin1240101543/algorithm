package com.darren.center.algorithm2.class05;

import com.darren.center.algorithm2.class01.LogarithmMachine;

/**
 * <h3>algorithm</h3>
 * <p></p>
 * 快排
 *
 * @author : Darren
 * @date : 2022年06月22日 07:46:58
 **/
public class Code02_PartitionQuickSort {

    public static void main(String[] args) {
        int count = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean success = true;
        for (int i = 0; i < count; i++) {
            int[] var1 = LogarithmMachine.generateRandomArray(maxSize, maxValue);
            int[] var2 = LogarithmMachine.copyArray(var1);
            int[] var3 = LogarithmMachine.copyArray(var1);
            quickSort1(var1);
            quickSort2(var2);
            quickSort3(var3);
            if (!LogarithmMachine.isEqual(var1, var2) || !LogarithmMachine.isEqual(var1, var3)) {
                success = false;
                LogarithmMachine.printArray(var1);
                LogarithmMachine.printArray(var2);
                LogarithmMachine.printArray(var3);
                break;
            }
        }
        System.out.println(success ? "success" : "failure");
    }

    /**
     * 快排1.0
     * 不断的玩荷兰国旗问题
     * 1、选择最右边的数作为指定值num；
     * 2、将小于等于num的放左边，大于num的放右边；
     * 3、此时num不用动了，它已经放到了它在有序数组中应该在的位置；
     * 4、num左边递归玩1，2，3（此时的划分值变成了新的范围内最右的值）
     * 5、num右边递归玩1，2，3（此时的划分值变成了新的范围内最右的值）
     * 6、整体有序﻿​
     *
     * 每次搞定一个数
     *
     * 时间复杂度O（N^2），最差情况：数组本身是有序的。
     * @param arr
     */
    public static void quickSort1(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    /**
     * 快排2.0
     * 不断的玩荷兰国旗问题
     * 1、选择最右边的数作为指定值num；
     * 2、将小于num的放左边，等于num的放中间，大于num的放右边；
     * 3、此时等于的num不用动了，它已经放到了它在有序数组中应该在的位置；
     * 4、左边小于num的数递归玩1，2，3（此时的划分值变成了新的范围内最右的值）
     * 5、右边大于num的数递归玩1，2，3（此时的划分值变成了新的范围内最右的值）
     * 6、整体有序 ﻿​
     *
     * 优势：可以搞定等于num的一批数
     *
     * 时间复杂度O（N^2），最差情况：数组本身是有序的。
     * @param arr
     */
    public static void quickSort2(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    /**
     * 快排3.0
     * 和2.0的区别就是随机选择一个数交换到指定值num
     *
     * 不断的玩荷兰国旗问题
     * 0、随机将一个数交换到最右边；
     * 1、选择最右边的数作为指定值num；
     * 2、将小于num的放左边，等于num的放中间，大于num的放右边；
     * 3、此时等于的num不用动了，它已经放到了它在有序数组中应该在的位置；
     * 4、左边小于num的数递归玩1，2，3（随机将一个数交换到最右边，此时的划分值变成了新的范围内最右的值）
     * 5、右边大于num的数递归玩1，2，3（随机将一个数交换到最右边，此时的划分值变成了新的范围内最右的值）
     * 6、整体有序 ﻿​
     *
     * 优势：可以搞定等于num的一批数
     *
     * T（N） = 2 * T（N / 2） + O（N）
     *
     * 最好情况：当随机到中间N/2，时间复杂度靠近O（N * logN）；
     * 最差情况：当随机到最左边或者最右边，时间复杂度靠近O（N^2）
     * 每种情况占1/N，概率事件。
     * 所有情况累加，数学证明时间复杂度O（N * logN）。
     *
     * 额外空间复杂度：
     * 最差情况：记住每个点，O（N）
     * 最好情况：记住每个点，下次partition释放，每次直接到中点，O（logN）
     * 额外空间复杂度累加：O（logN）
     *
     * @param arr
     */
    public static void quickSort3(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    private static void process3(int[] arr, int l, int r){
        if (l >= r){
            return;
        }
        //随机一个位置交换到r位置
        // l = 3 r = 5
        // l + 0 ... 2
        // 3 4 5
        swap(arr,  l + (int)(Math.random() * (r - l + 1)), r);
        int[] range = partition2(arr, l, r);
        process2(arr, l, range[0] - 1);
        process2(arr, range[1] + 1, r);
    }

    private static void process2(int[] arr, int l, int r){
        if (l >= r){
            return;
        }
        int[] range = partition2(arr, l, r);
        process2(arr, l, range[0] - 1);
        process2(arr, range[1] + 1, r);
    }

    /**
     *
     * @param arr
     * @param l
     * @param r
     * @return 等于区域的下标
     */
    private static int[] partition2(int[] arr, int l, int r){
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

    public static void process1(int[] arr, int l, int r){
        if (l >= r){
            return;
        }
        int m = partition1(arr, l, r);
        process1(arr, l, m - 1);
        process1(arr, m + 1, r);
    }

    private static int partition1(int[] arr, int l, int r){
        if (l == r){
            return l;
        }
        int leftBoundary = l - 1;
        int index = l;
        while (index < r){
            if (arr[index] <= arr[r]){
                swap(arr, index, ++leftBoundary);
            }
            index++;
        }
        swap(arr, r, ++leftBoundary);
        return leftBoundary;
    }

    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}

