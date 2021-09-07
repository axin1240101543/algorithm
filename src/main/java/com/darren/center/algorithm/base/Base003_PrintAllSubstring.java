package com.darren.center.algorithm.base;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2021年09月02日 21:41:01
 *
 * 打印一个字符串的所有子串
 **/
public class Base003_PrintAllSubstring {

    public static void main(String[] args) {
        String s = "abcd";
        substring(s);
    }

    public static void substring(String s){
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            for (int j = i + 1; j <= str.length; j++) {
                System.out.println(s.substring(i, j));
            }
        }
    }
}

