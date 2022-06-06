package com.darren.center.algorithm2.class03;

import com.darren.center.algorithm2.class01.LogarithmMachine;

/**
 * <h3>algorithm</h3>
 * <p></p>
 * 最简单的递归
 * 获取数组中的最大值
 *
 * @author : Darren
 * @date : 2022年06月02日 07:04:59
 **/
public class Code08_SimpleRecursion {


    public static void main(String[] args) {
        int count = 50;
        int maxSize = 100;
        int maxValue = 100;
        boolean success = true;
        for (int i = 0; i < count; i++) {
            int[] var1 = LogarithmMachine.generateRandomArray(maxSize, maxValue);
            int[] var2 = LogarithmMachine.copyArray(var1);
            int maxNum = findMaxNum(var1, 0, var1.length - 1);
            int maxNum2 = findMaxNum2(var2);
            if (maxNum != maxNum2){
                success = false;
                LogarithmMachine.printArray(var1);
                LogarithmMachine.printArray(var2);
                break;
            }
        }
        System.out.println(success ? "success" : "failure");
    }

    // arr[L..R]范围上求最大值  L ... R   N
    public static int findMaxNum(int[] arr, int left, int right){
        //base case 递归结束条件
        if (left == right){
            return arr[left];
        }
        // L...R 不只一个数
        // mid = (L + R) / 2
        // 中点
        int mid = left + ((right - left) >> 1);
        int leftMax = findMaxNum(arr, left, mid);
        int rightMax = findMaxNum(arr, mid + 1, right);
        return Math.max(leftMax, rightMax);
    }

    public static int findMaxNum2(int[] arr){
        int ans = Integer.MIN_VALUE;
        for (int cur : arr) {
            ans = ans > cur ? ans : cur;
        }
        return ans;
    }

}

