package com.darren.center.algorithm2.ext;

import com.darren.center.algorithm2.class02.LogarithmMachine;

import java.util.HashSet;
import java.util.Set;

/**
 * Author: Darren
 * Date: 2022-02-17 10:10:32
 * Version: 1.0
 * Description:
 * 数组去重
 */
public class ArrayNoRepeat {

    public static void main(String[] args) {
        int maxSize = 100;
        int maxValue = 100;
        int count = 100000;
        boolean success = true;
        for (int i = 0; i < count; i++) {
            int[] var1 = LogarithmMachine.generateRandomArray(maxSize, maxValue);
            int[] var11 = arrNoRepeat(var1);
            int[] var22 = arrNoRepeat2(var1);
            LogarithmMachine.sort(var11);
            LogarithmMachine.sort(var22);
            if (!LogarithmMachine.isEqual(var11, var22)){
                success = false;
                LogarithmMachine.printArray(var1);
                LogarithmMachine.printArray(var11);
                LogarithmMachine.printArray(var22);
                break;
            }
        }
        System.out.println(success ? "success" : "failure");
    }

    public static int[] arrNoRepeat(int[] arr){
        if (arr == null || arr.length == 0) {
            return null;
        }
        if (arr.length == 1){
            return arr;
        }
        boolean flag = true;
        //额外空间
        int[] temp = new int[arr.length];
        int size = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                //i位置的数和后面每个数比较，发现重复了就跳过i位置
                if (arr[i] == arr[j]){
                    flag = false;
                    break;
                }
            }
            //i位置的数不重复 将下标记录下来
            if (flag){
                temp[size++] = i;
            }
            //不管上一个数的结果是什么，恢复现场
            flag = true;
        }
        int[] result = new int[size];
        for (int i = 0; i < result.length; i++) {
            result[i] = arr[temp[i]];
        }
        return result;
    }

    public static int[] arrNoRepeat2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        if (arr.length == 1){
            return arr;
        }
        Set<Integer> set = new HashSet<>();
        for (int i : arr) {
            set.add(i);
        }
        int[] result = new int[set.size()];
        int i = 0;
        for (Integer integer : set) {
            result[i++] = integer;
        }
        return result;
    }
}
