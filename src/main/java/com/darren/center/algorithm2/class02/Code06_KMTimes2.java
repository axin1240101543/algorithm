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
 * @date : 2022年02月17日 21:17:11
 * 一个数组的一种数出现k次，其他数都是m次，找到出现k次的这种数
 * 限制：k < m, m > 1
 * 返回出现k次的这种数，如果没有出现返回-1
 **/
public class Code06_KMTimes2 {

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
            int ans1 = findKNumber(var1, k, m);
            int ans2 = findKNumber2(var1, k, m);
            if (ans1 != ans2){
                success = false;
                LogarithmMachine.printArray(var1);
                System.out.println(k);
                System.out.println(m);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println(success ? "success" : "failure");
    }

    public static int[] randomArray(int maxKinds, int maxValue, int k, int m){
        int targetNumber = randomNumber(maxValue);
        //0.5的概率出现真命天子
        //[0~m-2] -> [0~ m-1] 保证k < m
        int times = Math.random() < 0.5 ? k : ((int)(Math.random() * (m - 1)) + 1);
        int kinds = randomPositiveNumberContain0(maxKinds) + 2;
        int size = times + (kinds - 1) * m;
        int[] arr = new int[size];
        int index = 0;
        for(; index < times; index++){
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
     * @param arr
     * @param k
     * @param m
     * @return
     */
    public static int findKNumber(int[] arr, int k, int m){
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
            if ((tmp[i] % m) == 0){
                continue;
            }
            if ((tmp[i] % m) == k){
                //然后将第i位的1或进ans
                ans |= (1 << i);
            }else {
                return -1;
            }
        }
        //arr中出现了0 但是还是要用ans收集答案 所以处理0的个数等于k的情况
        if (ans == 0){
            int count = 0;
            for (int num : arr) {
                if (num == 0){
                    count++;
                }
            }
            if (count != k){
                return -1;
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

