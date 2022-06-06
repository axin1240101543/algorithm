package com.darren.center.algorithm2.class02;

import com.darren.center.algorithm2.class01.LogarithmMachine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2022年02月17日 20:36:56
 * 一个数组的一种数出现k次（findKNumber方法只是将不是m次的数找出来），其他数都是m次，找到出现k次的这种数
 * 限制：k < m, m > 1
 **/
public class Code05_KMTimes {

    public static void main(String[] args) {
        int maxKinds = 10;
        int maxValue = 100;
        int count = 1000000;
        int max = 10;
        boolean success = true;
        for(int i = 0; i < count; i++){
            // 1 ~ 10
            int a = randomPositiveNumber(max);
            int b = randomPositiveNumber(max);
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            if (k == m){
                m++;
            }
            int[] var1 = randomArray(maxKinds, maxValue, k, m);
            int ans1 = findKNumber(var1, m);
            int ans2 = findKNumber2(var1, k, m);
            if (ans1 != ans2){
                success = false;
                LogarithmMachine.printArray(var1);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println(success ? "success" : "failure");
    }

    public static int[] randomArray(int maxKinds, int maxValue, int k, int m){
        int targetNumber = randomNumber(maxValue);
        int kinds = randomPositiveNumberContain0(maxKinds) + 2;
        int size = k + (kinds - 1) * m;
        int[] arr = new int[size];
        int index = 0;
        for(; index < k; index++){
            arr[index] = targetNumber;
        }
        kinds--;
        Set<Integer> set = new HashSet<>();
        set.add(targetNumber);
        while (kinds != 0){
            int otherNumber = 0;
            do {
                otherNumber = randomNumber(maxValue);
            }while (set.contains(otherNumber));
            set.add(otherNumber);
            for(int i = 0; i < m; i++){
                arr[index++] = otherNumber;
            }
            kinds--;
        }
        for (int i = 0; i < arr.length; i++){
            //i位置的数和j位置的数交换打乱这个数组
            int j = (int)(Math.random() * arr.length);
            swap(arr, i, j);
        }
        return arr;
    }

    public static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 包含0
     * @param maxValue
     * @return
     */
    public static int randomPositiveNumberContain0(int maxValue){
        return (int)(Math.random() * (maxValue + 1));
    }

    /**
     * 不包含0
     * @param maxValue
     * @return
     */
    public static int randomPositiveNumber(int maxValue){
        return (int)(Math.random() * maxValue) + 1;
    }

    public static int randomNumber(int maxValue){
        return (int)(Math.random() * (maxValue + 1)) - (int)(Math.random() * (maxValue + 1));
    }

    /**
     * 时间复杂度：O（N）
     * 这个实现只是将不是m次的数返回，并不是返回k次的数，返回k次的数@com.darren.center.algorithm2.class03.Code06_KMTimes2
     * @param arr
     * @param m
     * @return
     */
    public static int findKNumber(int[] arr, int m){
        if (arr == null || arr.length == 0){
            return -1;
        }
        //将每个数的二进制累加到这个32位的tmp数组中，因为int最大32位
        //tmp的0位就是二进制从右往左第0位 tmp的1位就是二进制从右往左第1位 ……
        int[] tmp = new int[32];
        for(int num : arr){
            for (int i = 0; i <= 31; i++){
                //如果说这个数向右移动i位 & 1 不等于0 那么说明这个数的第i位是1
            /*if (((num >> i) & 1) != 0){
                tmp[i]++;
            }*/
                tmp[i] += ((num >> i) & 1);
            }
        }
        //用于设置答案
        int ans = 0;
        for (int i = 0; i <= 31; i++){
            //出现m次的数是1 这个位置肯定是m的倍数  模上m那么就会变成0 如果k次的数在这个位置上也是1 那么就不会等于0
            if ((tmp[i] % m) != 0){
                //然后将第i位的1或进ans
                ans |= (1 << i);
            }
        }
        return ans;
    }

    /**
     * 利用hash表实现
     * @param arr
     * @param k
     * @param m
     * @return
     */
    public static int findKNumber2(int[] arr, int k, int m){
        if (arr == null || arr.length == 0){
            return -1;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < arr.length; i++){
            if (map.containsKey(arr[i])){
                map.put(arr[i], map.get(arr[i]) + 1);
            }else {
                map.put(arr[i], 1);
            }
        }
        for(Integer key: map.keySet()){
            if (map.get(key) == k){
                return key;
            }
        }
        return -1;
    }

}

