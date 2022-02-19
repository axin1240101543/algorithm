package com.darren.center.algorithm2.class02;

import java.util.Arrays;

/**
 * <h3>spring-boot-2020-gitee</h3>
 * <p>对数器</p>
 *
 * @author : Darren
 * @date : 2022年02月13日 10:39:40
 **/
public class LogarithmMachine {

    /**
     * 生成一个随机数组
     * @param maxSize
     * @param maxValue
     * @return
     */
    public static int[] generateRandomArray(int maxSize, int maxValue){
        // Math.random() -> [0,1) 所有的小数，等概率返回一个
        // Math.random() * N -> [0,N) 所有小数，等概率返回一个
        // (int)(Math.random() * N) -> [0,N-1] 所有的整数，等概率返回一个
        int[] arrays = new int[(int) ((maxSize+1) * Math.random())];
        for (int i = 0; i < arrays.length; i++) {
            arrays[i] = (int) ((maxValue+1) * Math.random()) - (int)(maxValue * Math.random());
        }
        return arrays;
    }

    /**
     * 生成一个随机数
     * @param maxValue
     * @return
     */
    public static int generateRandom(int maxValue){
        return (int) ((maxValue+1) * Math.random()) - (int)(maxValue * Math.random());
    }

    /**
     * java自带排序
     * @param arrays
     */
    public static void sort(int[] arrays){
        if (arrays == null || arrays.length < 2){
            return;
        }
        Arrays.sort(arrays);
    }

    /**
     * java自带复制数组
     * @param arrays
     * @return
     */
    public static int[] copyArray(int[] arrays){
        return Arrays.copyOf(arrays, arrays.length);
    }

    /**
     * 判断两个数组是否相等
     * @param var1
     * @param var2
     * @return
     */
    public static boolean isEqual(int[] var1, int[] var2){
        if ((var1 == null && var2 != null) || (var1 != null && var2 == null)){
            return false;
        }
        if(var1 == null && var2 == null){
            return true;
        }
        if (var1.length != var2.length){
            return false;
        }
        for (int i = 0; i < var1.length; i++) {
            if (var1[i] != var2[i]){
                return false;
            }
        }
        return true;
    }

    /**
     * 打印数组
     * @param arrays
     */
    public static void printArray(int[] arrays){
        if (arrays == null){
            return;
        }
        for (int i = 0; i < arrays.length; i++) {
            System.out.print(arrays[i] + " ");
        }
        System.out.println();
    }
}

