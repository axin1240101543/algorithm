package com.darren.center.algorithm2.class04;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 *  num的右边有多少个数*2后依然小于num
 *
 * 计算过程（先计算，后merge）：
 * 1 4 6 8 9 1 1 2 3 3 4
 * p         r
 * 1：1小于1*2，r不动，1*2 > 1：0个
 * ------
 * 1 4 6 8 9 1 1 2 3 3 4
 *   p           r
 * 4：4大于1*2，r右移，直到右组的某个数*2小于4，2个：1 1
 * ------
 * 1 4 6 8 9 1 1 2 3 3 4
 *     p           r﻿​
 * 6：6大于2*2，r右移，直到右组的某个数*2小于6，3个1 1 2
 * ------
 * 1 4 6 8 9 1 1 2 3 3 4
 *       p             r﻿​
 * 8：8大于3*2，r右移，直到右组的某个数*2小于8，5个1 1 2 3 3
 * ------
 * 1 4 6 8 9 1 1 2 3 3 4
 *         p             r﻿​
 * 9：9大于4*2，r右移，直到右组的某个数*2小于9，6个1 1 2 3 3 4
 * ------
 * 左组越界，总共2 + 3 + 5 + 6 = 16个
 *
 * 总结：
 * 准备一个左指针和一个右指针（到不了的位置，即左闭右开），
 * 左组的每一个值都去判断右组有有多少个数比他小（r - m - 1），因为左右组有序
 * 左组大于右组*2，右指针右移，直到大于等于左组
 * 左组等于右组*2，右指针不动
 * 左组小于右组*2，右指针不动
 *
 * 技巧：
 * r不会退...单调性
 *
 * 模型：
 * 一个数的右边怎么怎么样，就可以考虑merge
 *
 * @author : Darren
 * @date : 2022年06月08日 08:42:00
 **/
public class Code04_BiggerThanRightTwice {

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (biggerThanRightTwice(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }


    public static int biggerThanRightTwice(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int l, int r){
        if (l == r){
            return 0;
        }
        int m = l + ((r - l) >> 1);
        return process(arr, l, m) + process(arr, m + 1, r) + merge(arr, l, m, r);
    }

    // [L....M]   [M+1....R]
    public static int merge(int[] arr, int l, int m, int r){
        int ans = 0;
        // 目前囊括进来的数，是从[M+1, right)
        int right = m + 1;
        for (int i = l; i <= m; i++){
            while (right <= r && arr[i] > (arr[right] * 2)){
                right++;
            }
            ans += right - m - 1;
        }

        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r){
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m){
            help[i++] = arr[p1++];
        }
        while (p2 <= r){
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++){
            arr[l + j] = help[j];
        }
        return ans;
    }

    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > (arr[j] << 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}

