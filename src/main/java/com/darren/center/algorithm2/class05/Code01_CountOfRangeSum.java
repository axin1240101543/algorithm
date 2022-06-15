package com.darren.center.algorithm2.class05;

import com.darren.center.algorithm2.class01.LogarithmMachine;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 * 给定一个数组arr，两个整数lower和upper，
 * 返回arr中有多少个子数组的累加和在[lower,upper]范围上
 * Leetcode题目：https://leetcode.cn/problems/count-of-range-sum/
 *
 * 思路1：
 * 求从i ... j范围的累加和
 * 前缀和数组：
 * arr[i，j] = arr[0，j] - arr[0, i - 1]
 * 提前生成辅助数组，遍历一遍即可
 *
 * 1、-1、-2、3   [-1,1]
 * 0   1   2  3
 * 枚举每个位置的子数组（连续）
 * 0-0 [1]  √
 * 0-1 [1,-1]  1- 1 = 0 √
 * 0-2 [1,-1,-2]  1-1-2 = -2 ×
 * 0-3 [1,-1,-2,3] √
 * 1-1 ...
 * 1-2 ...
 * 1-3 ...
 * 2-2 ...
 * 2-3 ...
 * 3-3 ...
 * 等差数列，时间复杂度为O（N^2）
 * 每个数获取i ... j的累加和，采用遍历的方式，总的时间复杂度O（N^3）
 * 如果使用前缀和数组，优化成O（N^2）
 *
 * 思路2：
 * 2.1、以某个数结尾的子数组有几个？
 * 以0位置结尾的 0-0 有a个
 * 以1位置结尾的 1-1 0-1 有b个
 * 以2位置结尾的 2-2 1-2 0-2 有c个
 * 以3位置结尾的 3-3 2-3 1-3 0-3 有d个
 * ...
 * 把a + b + c + d ... 所有加起来就是答案﻿​
 *
 * 举例：
 * 以17位置结尾：
 * [lower,upper] = [10,40]
 * 0-0 0
 * 0-1 40
 * 0-2 70
 * ...
 * 0-16 60
 * 0-17 前缀和 100
 *
 * 如果：
 * 100 - 0 -> 1-17的前缀和
 * 100 - 40 -> 2-17的前缀和
 * 那么：
 * a - b = c(原始范围) -> a - c = b(现在的范围)
 * 即100 - b = [10,40] -> b = 100 - [10,40] = [60, 90]
 * 如果前缀和在[60, 90]范围内，那么从i到j的前缀和肯定在原始范围内
 * 即0-1的前缀和40不在[60, 90]范围内，那么1-17不在[10,40]范围内，100 - 40 = 60
 * 即0-2的前缀和70在[60, 90]范围内，那么2-17在[10,40]范围内，100 - 70 = 30
 * ...
 *
 * 结论：
 * 求以x位置结尾的子数组的前缀和落在[lower,upper]内，就是求以x位置结尾的子数组的前缀和落在[x-upper,x-lower]内，不要原数组了
 *
 * merge：
 * 求右组中每个数，在左组中有多少个数达标（落在[lower,upper]内）。
 *
 * 左组的窗口是不回退，所以时间复杂度为O（N），整体的时间复杂度为O（N * logN）。
 *
 * @author : Darren
 * @date : 2022年06月15日 07:44:06
 **/
public class Code01_CountOfRangeSum {

    public static void main(String[] args) {
        int count = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean success = true;
        for (int i = 0; i < count; i++) {
            int[] var1 = LogarithmMachine.generateRandomArray(maxSize, maxValue);
            int[] var2 = LogarithmMachine.copyArray(var1);
            int lower = LogarithmMachine.generateRandom(maxValue);
            int upper = LogarithmMachine.generateRandom(maxValue);
            lower = Math.min(lower, upper);
            upper = Math.max(lower, upper);
            int ans2 = countRangeSum(var1, lower, upper);
            int ans1 = countRangeSum1(var1, lower, upper);
            if (ans1 != ans2){
                success = false;
                LogarithmMachine.printArray(var1);
                LogarithmMachine.printArray(var2);
                System.out.println("lower:" + lower + " upper:" + upper);
                break;
            }
        }
        System.out.println(success ? "success" : "failure");
    }

    public static int countRangeSum(int[] nums, int lower, int upper){
        if (nums == null || nums.length == 0){
            return 0;
        }
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++){
            sum[i] = sum[i - 1] + nums[i];
        }
        return process1(sum, 0, sum.length - 1, lower, upper);
    }

    public static int process1(long[] sum, int l, int r, int lower, int upper){
        if (l == r){
            if (sum[l] >= lower && sum[l] <= upper){
                return 1;
            }else {
                return 0;
            }
        }
        int mid = l + ((r - l) >> 1);
        return process1(sum, l, mid, lower, upper)
                + process1(sum, mid + 1, r, lower, upper)+
                + marge(sum, l, mid, r, lower, upper);
    }

    public static int marge(long[] sum, int l, int mid, int r, int lower, int upper) {
        //计算过程
        int wondowL = l;
        int wondowR = l;
        int ans = 0;
        for (int i = mid + 1; i <= r; i++){
            //新目标
            long targetL = sum[i] - upper;
            long targetR = sum[i] - lower;
            //[wondowL,
            while (wondowL <= mid && sum[wondowL] < targetL){
                wondowL++;
            }
            //[wondowL,wondowR) 左闭右开
            while (wondowR <= mid && sum[wondowR] <= targetR){
                wondowR++;
            }
            ans += wondowR - wondowL;
        }

        //marge过程
        long[] help = new long[r - l + 1];
        int i = 0;
        int left = l;
        int right = mid + 1;
        while (left <= mid && right <= r){
            help[i++] = sum[left] <= sum[right] ? sum[left++] : sum[right++];
        }
        while (left <= mid){
            help[i++] = sum[left++];
        }
        while (right <= r){
            help[i++] = sum[right++];
        }
        for (int j = 0; j < help.length; j++){
            sum[l + j] = help[j];
        }
        return ans;
    }

    public static int countRangeSum1(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        return process(sum, 0, sum.length - 1, lower, upper);
    }

    public static int process(long[] sum, int L, int R, int lower, int upper) {
        if (L == R) {
            return sum[L] >= lower && sum[L] <= upper ? 1 : 0;
        }
        int M = L + ((R - L) >> 1);
        return process(sum, L, M, lower, upper) + process(sum, M + 1, R, lower, upper)
                + merge(sum, L, M, R, lower, upper);
    }

    public static int merge(long[] arr, int L, int M, int R, int lower, int upper) {
        int ans = 0;
        int windowL = L;
        int windowR = L;
        // [windowL, windowR)
        for (int i = M + 1; i <= R; i++) {
            long min = arr[i] - upper;
            long max = arr[i] - lower;
            while (windowR <= M && arr[windowR] <= max) {
                windowR++;
            }
            while (windowL <= M && arr[windowL] < min) {
                windowL++;
            }
            ans += windowR - windowL;
        }
        long[] help = new long[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }

}

