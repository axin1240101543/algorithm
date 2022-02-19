package com.darren.center.algorithm2.class02;

/**
 * <h3>spring-boot-2020-gitee</h3>
 * <p>找到一个有序数组中<=一个数最右的位置</p>
 *
 * 二分
 *
 * 1 2 3 3 4 8 9 9 9 10
 *                 8
 * 此时<=9的最右的位置就是8位置
 *
 * @author : Darren
 * @date : 2022年02月15日 20:17:15
 **/
public class Code06_BSNearRight {

    public static void main(String[] args) {
        int count = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean success = true;
        for (int i = 0; i < count; i++) {
            int[] var1 = LogarithmMachine.generateRandomArray(maxSize, maxValue);
            int num = LogarithmMachine.generateRandom(100);
            LogarithmMachine.sort(var1);
            if (nearRight(var1, num) != nearRight2(var1, num)){
                success = false;
                LogarithmMachine.printArray(var1);
                System.out.println(num);
                break;
            }
        }
        System.out.println(success ? "success" : "failure");
    }

    public static int nearRight(int[] arr, int num){
        if (arr == null || arr.length == 0){
            return -1;
        }
        int l = 0;
        int r = arr.length - 1;
        int mid = 0;
        int index = -1;
        while (l <= r){
            mid = l + ((r - l) >> 1);
            if (arr[mid] <= num){
                index = mid;
                l = mid + 1;
            }else {
                r = mid - 1;
            }
        }
        return index;
    }

    public static int nearRight2(int[] arr, int num){
        if (arr == null || arr.length == 0){
            return -1;
        }
        for (int i = arr.length - 1; i >= 0; i--){
            if (arr[i] <= num){
                return i;
            }
        }
        return -1;
    }

}

