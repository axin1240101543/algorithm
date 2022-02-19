package com.darren.center.algorithm2.class02;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>spring-boot-2020-gitee</h3>
 * <p>找到一个不相等且无序数组中的局部最小值</p>
 *
 * 二分
 * 结论：二分不一定有序，只要能排他性的砍一半儿就能二分
 *
 * @author : Darren
 * @date : 2022年02月15日 20:10:09
 **/
public class Code07_BSLessIndex {

    public static void main(String[] args) {
        int count = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean success = true;
        for (int i = 0; i < count; i++) {
            int[] var1 = LogarithmMachine.generateRandomArray(maxSize, maxValue);
            List<Integer> list = new ArrayList<>();
            for (int v : var1) {
                if (!list.contains(v)){
                    list.add(v);
                }
            }
            int[] var2 = new int[list.size()];
            for (int j = 0; j < list.size(); j++) {
                var2[j] = list.get(j);
            }
            int num = LogarithmMachine.generateRandom(100);
            LogarithmMachine.sort(var2);
            if (getLessIndex(var2) != getLessIndex2(var2)){
                success = false;
                LogarithmMachine.printArray(var2);
                System.out.println(num);
                break;
            }
        }
        System.out.println(success ? "success" : "failure");
    }

    public static int getLessIndex(int[] arr){
        if (arr == null || arr.length == 0){
            return -1;
        }
        if (arr.length == 1 || arr[0] < arr[1]){
            return 0;
        }
        if (arr.length == 2 && arr[0] == arr[1]){
            return 1;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]){
            return arr.length - 1;
        }
        int l = 1;
        int r = arr.length - 2;
        int mid = 0;
        int result = -1;
        while (l <= r){
            mid = l + ((r - l) >> 1);
            if (arr[mid] > arr[mid - 1]){
                r = mid - 1;
            }else if (arr[mid] > arr[mid + 1]){
                l = mid + 1;
            }else {
                result = mid;
                break;
            }
        }
        return result;
    }

    public static int getLessIndex2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1; // no exist
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int left = 1;
        int right = arr.length - 2;
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (arr[mid] > arr[mid - 1]) {
                right = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }

}

