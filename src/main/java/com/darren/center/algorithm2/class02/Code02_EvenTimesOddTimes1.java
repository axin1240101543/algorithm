package com.darren.center.algorithm2.class02;

import com.darren.center.algorithm2.class01.LogarithmMachine;

import java.util.*;

/**
 * Author: Darren
 * Date: 2022-02-17 13:53:48
 * Version: 1.0
 * Description:
 * 找到一个数组中出现奇数次的一种数，其他数都是偶数次
 */
public class Code02_EvenTimesOddTimes1 {

    public static void main(String[] args) {
        int maxSize = 100;
        int maxValue = 100;
        int kinds = 10;
        int count = 1000000;
        boolean success = true;
        for (int i = 0; i < count; i++) {
            int[] var1 = randomArray(kinds, maxSize, maxValue);
            if (findOneUnevenTimesNumber(var1) != findOneUnevenTimesNumber2(var1)){
                success = false;
                LogarithmMachine.printArray(var1);
                break;
            }
        }
        System.out.println(success ? "success" : "failure");
    }

    public static int[] randomArray(int kinds, int maxSize, int maxValue){
        List<Integer> list = new ArrayList<>();
        //添加奇数次的数
        int targetNumber = randomNumber(maxValue);
        int unevenCount = 0;
        do {
            unevenCount = randomPositiveNumber(maxSize);
        }while (unevenCount % 2 == 0);
        for (int i = 0; i < unevenCount; i++) {
            list.add(targetNumber);
        }
        //数的种类减去奇数次的数
        kinds--;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < kinds; i++) {
            int otherNumber = 0;
            do {
                otherNumber = randomNumber(maxValue);
            }while (otherNumber  == targetNumber && set.contains(otherNumber));
            //添加偶数次的数
            int evenCount = randomPositiveNumber(maxSize) * 2;
            for (int j = 0; j < evenCount; j++) {
                list.add(otherNumber);
            }
            //避免添加重复的偶数次的数
            set.add(otherNumber);
        }
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public static int randomPositiveNumber(int maxValue){
        return (int)(Math.random() * (maxValue + 1));
    }

    public static int randomNumber(int maxValue){
        return (int)(Math.random() * (maxValue + 1)) - (int)(Math.random() * (maxValue + 1));
    }

    public static int findOneUnevenTimesNumber(int[] arr){
        if (arr == null || arr.length == 0){
            return -999;
        }
        //默认0 0^N 还是N
        int eor = 0;
        for (int i : arr) {
            eor = eor ^ i;
        }
        return eor;
    }

    public static int findOneUnevenTimesNumber2(int[] arr){
        if (arr == null || arr.length == 0){
            return -999;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (map.containsKey(i)){
                map.put(i, map.get(i) + 1);
            }else{
                map.put(i, 1);
            }
        }
        for (Integer key : map.keySet()) {
            if (map.get(key) % 2 != 0){
                return key;
            }
        }
        return -999;
    }
}
