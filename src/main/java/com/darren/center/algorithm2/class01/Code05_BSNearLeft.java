package com.darren.center.algorithm2.class01;

/**
 * <h3>spring-boot-2020-gitee</h3>
 * <p>找到一个有序数组中>=一个数最左的位置</p>
 *
 * 二分
 *
 * 1 2 3 3 3 4 8 9 10
 *     2
 * 此时>=3的最左的位置就是2位置
 *
 * @author : Darren
 * @date : 2022年02月14日 21:16:56
 **/
public class Code05_BSNearLeft {

    public static void main(String[] args) {
        int count = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean success = true;
        for (int i = 0; i < count; i++) {
            int[] var1 = LogarithmMachine.generateRandomArray(maxSize, maxValue);
            int num = LogarithmMachine.generateRandom(100);
            LogarithmMachine.sort(var1);
            if (nearLeft(var1, num) != nearLeft1(var1, num)){
                success = false;
                LogarithmMachine.printArray(var1);
                System.out.println(num);
                break;
            }
        }
        System.out.println(success ? "success" : "failure");
    }

    public static int nearLeft(int[] arr, int num){
        if (arr == null || arr.length == 0){
            return -1;
        }
        int l = 0;
        int r = arr.length - 1;
        int mid = 0;
        int index = -1;
        while (l <= r){
            mid = l + ((r - l) >> 1);
            if (arr[mid] >= num){
                //记录大于等于num的位置，直到下一次有满足条件的值刷新位置
                index = mid;
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        return index;
    }

    public static int nearLeft1(int[] arr, int num){
        if (arr == null || arr.length == 0){
            return -1;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= num){
                return i;
            }
        }
        return -1;
    }

}

