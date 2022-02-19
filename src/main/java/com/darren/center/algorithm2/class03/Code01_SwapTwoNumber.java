package com.darren.center.algorithm2.class03;

import com.darren.center.algorithm2.class02.LogarithmMachine;

/**
 * <h3>spring-boot-2020-gitee</h3>
 * <p>不使用额外变量交换两个数</p>
 *
 * @author : Darren
 * @date : 2022年02月15日 21:10:39
 **/
public class Code01_SwapTwoNumber {

    public static void main(String[] args) {
        int a = 1;
        int b = 2;

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a + " " + b);

        int[] arr = {1, 2, 3};
        a = 0;
        b = 1;
        swap(arr, a, b);
        LogarithmMachine.printArray(arr);

        //避免交换同一块内存区域的两个值，否则报错，得到的结果是0
        //一定要保证调用的方法传入的是两个不同的位置
        a = 0;
        b = 0;
        swap(arr, a, b);
        LogarithmMachine.printArray(arr);
    }

    public static void swap(int[] arr, int i, int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

}

