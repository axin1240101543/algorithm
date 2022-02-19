package com.darren.center.algorithm2.class03;

/**
 * Author: Darren
 * Date: 2022-02-17 15:56:54
 * Version: 1.0
 * Description:
 * 提取一个数二进制最右边的1
 */
public class Code03_ExtractRightOne {

    public static void main(String[] args) {
        //00001010
        int a = 10;
        //11110101 + 1 = 11110110 & 00001010(a) = 00000010 = 2
        int b = a & (~a + 1);
        System.out.println(b);

        int c = a & (-a);
        System.out.println(c);
    }
}
