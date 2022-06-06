package com.darren.center.algorithm2.class01;

/**
 * <h3>spring-boot-2020-gitee</h3>
 * <p>判断一个有序数组中一个数是否存在</p>
 *
 * 二分
 *
 * @author : Darren
 * @date : 2022年02月14日 21:00:01
 **/
public class Code04_BSExist {

    public static void main(String[] args) {
        int count = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean success = true;
        for (int i = 0; i < count; i++) {
            int[] var1 = LogarithmMachine.generateRandomArray(maxSize, maxValue);
            int num = LogarithmMachine.generateRandom(100);
            LogarithmMachine.sort(var1);
            if (exist(var1, num) != exist3(var1, num)){
                success = false;
                LogarithmMachine.printArray(var1);
                System.out.println(num);
                break;
            }
        }
        System.out.println(success ? "success" : "failure");
    }

    public static boolean exist(int[] arr, int num){
        if (arr == null || arr.length == 0){
            return false;
        }
        int l = 0;
        int r = arr.length - 1;
        int mid = 0;
        //l < r 至少两个数才进行二分
        //1 <= r 至少一个数才进行二分
        //算法怎么实现，八仙过海各显神通，流程是一样的。
        while (l < r){
            //(l + r) / 2 可能越界，l + r超过int的位数
            mid = l + ((r - l) >> 1);
            if (arr[mid] == num){
                return true;
            }else if (arr[mid] < num){
                l = mid + 1;
            }else if (arr[mid] > num){
                r = mid - 1;
            }
        }
        //最后还剩下两个数 l == r，所以还需要判断一下
        return arr[l] == num;
    }

    public static boolean exist3(int[] arr, int num){
        if (arr == null || arr.length == 0){
            return false;
        }
        int l = 0;
        int r = arr.length - 1;
        int mid = 0;
        //至少一个数才进行二分
        while (l <= r){
            mid = l + ((r - l) >> 1);
            if (arr[mid] == num){
                return true;
            }else if (arr[mid] < num){
                l = mid + 1;
            }else if (arr[mid] > num){
                r = mid - 1;
            }
        }
        return false;
    }

    public static boolean exist2(int[] arr, int num){
        if (arr == null || arr.length == 0){
            return false;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num){
                return true;
            }
        }
        return false;
    }

}

