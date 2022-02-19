package com.darren.center.algorithm2.class03;

import com.darren.center.algorithm2.class02.LogarithmMachine;

import java.util.*;

/**
 * Author: Darren
 * Date: 2022-02-17 15:58:42
 * Version: 1.0
 * Description:
 * 找到一个数组中出现奇数次的两种数，其他数都是偶数次
 */
public class Code04_EvenTimesOddTimes2 {

    public static void main(String[] args) {
        int maxSize = 100;
        int maxValue = 100;
        int kinds = 10;
        int count = 1000000;
        boolean success = true;
        for (int i = 0; i < count; i++) {
            int[] var1 = randomArray(kinds, maxSize, maxValue);
            int[] var2 = findTwoUnevenTimesNumber(var1);
            int[] var3 = findTwoUnevenTimesNumber2(var1);
            LogarithmMachine.sort(var2);
            LogarithmMachine.sort(var3);
            if (!LogarithmMachine.isEqual(var2, var3)){
                success = false;
                LogarithmMachine.printArray(var1);
                LogarithmMachine.printArray(var2);
                LogarithmMachine.printArray(var3);
                break;
            }
        }
        System.out.println(success ? "success" : "failure");
    }

    public static int[] randomArray(int kinds, int maxSize, int maxValue){
        List<Integer> list = new ArrayList<>();
        //添加奇数次的数
        int targetNumber1 = randomNumber(maxValue);
        int targetNumber2 = randomNumber(maxValue);
        int unevenCount1 = 0;
        int unevenCount2 = 0;
        do {
            unevenCount1 = randomPositiveNumber(maxSize);
        }while (unevenCount1 % 2 == 0);
        do {
            unevenCount2 = randomPositiveNumber(maxSize);
        }while (unevenCount2 % 2 == 0);
        for (int i = 0; i < unevenCount1; i++) {
            list.add(targetNumber1);
        }
        for (int i = 0; i < unevenCount2; i++) {
            list.add(targetNumber2);
        }
        //数的种类减去奇数次的数
        kinds -= 2;
        Set<Integer> set = new HashSet<>();
        set.add(targetNumber1);
        set.add(targetNumber2);
        for (int i = 0; i < kinds; i++) {
            int otherNumber = 0;
            do {
                otherNumber = randomNumber(maxValue);
            }while (set.contains(otherNumber));
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

    public static int[] findTwoUnevenTimesNumber(int[] arr){
        if (arr == null || arr.length == 0){
            return null;
        }
        //默认0 0^N 还是N
        //eor = a ^ b
        int eor = 0;
        for (int i : arr) {
            eor = eor ^ i;
        }
        //提取eor最右的1（随便哪个1，这里固定最右边的1），然后这个1位置，a和b肯定有一个是1，另外一个是0，所以异或出来是1
        int rightOne = eor & (-eor);
        int eor2 = 0;
        for (int i = 0; i < arr.length; i++) {
            //将所有这个最右边是1的数筛选出来，不管是奇数次的还是偶数次的，只会筛选一个出现奇数次的数出来，因为另外一个出现奇数次的数这个位置是0，最后将筛选出来的所有数进行异或，得到了其中一个出现奇数次的数
            if ((arr[i] & rightOne) != 0){
                eor2 = eor2 ^ arr[i];
            }
        }
        //另外一个数 用a^b^eor2，就得到了另外一个数，相同的消掉
        return new int[]{eor2, eor ^ eor2};
    }

    public static int[] findTwoUnevenTimesNumber2(int[] arr){
        if (arr == null || arr.length == 0){
            return null;
        }
        //计算词频
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (map.containsKey(i)){
                map.put(i, map.get(i) + 1);
            }else{
                map.put(i, 1);
            }
        }
        List<Integer> list = new ArrayList<>();
        for (Integer key : map.keySet()) {
            //将词频不是偶数的添加到list中
            if (map.get(key) % 2 != 0){
                list.add(key);
            }
        }
        int[] result = new int[2];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
