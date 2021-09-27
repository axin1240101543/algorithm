package com.darren.center.algorithm.ext;

/**
 * Author: Darren
 * Date: 2021-09-27 16:53:20
 * Version: 1.0
 * Description:
 */
public class ArrayMax {

    public static void main(String[] args) {
        int[] arr = {4,5,6,4,7,6,9,1,5,4,9,8};
        int res = process(arr, 0, arr.length - 1);
        System.out.println(res);
    }

    //二分法
    //左边去找最大值  右边去找最大值 返回最大值
    public static int process(int[] arr, int l, int r){
        if (l == r){
            return arr[l];
        }

        int m = l + ((r - l) >> 1);
        int lMax = process(arr, l, m);
        int rMax = process(arr, m + 1, r);
        return Math.max(lMax, rMax);
    }

}
