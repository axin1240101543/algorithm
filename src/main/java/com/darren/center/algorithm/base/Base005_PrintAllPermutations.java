package com.darren.center.algorithm.base;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2021年09月02日 21:41:01
 * 打印一个字符的全排列  类似leetcode 46
 **/
public class Base005_PrintAllPermutations {

    public static void main(String[] args) {
        String s = "abc";
        char[] str = s.toCharArray();
        List<String> ans = new ArrayList<>();
        process(str, 0, ans);
        ans.forEach(System.out::println);

        System.out.println("~~~~~~~~~~~~~~~~");

        String s1 = "aaa";
        char[] str1 = s1.toCharArray();
        List<String> ans1 = new ArrayList<>();
        process1(str1, 0, ans1);
        ans1.forEach(System.out::println);

        System.out.println("~~~~~~~~~~~~~~~~");

        String s2 = "eee";
        char[] str2 = s2.toCharArray();
        List<String> ans2 = new ArrayList<>();
        process2(str2, 0, ans2);
        ans1.forEach(System.out::println);

        System.out.println("~~~~~~~~~~~~~~~~");

        //0 -> 48
        //97 - 48 = 49 ?
        char a = 'a';
        int b = a - '0';
        System.out.println(b);
        char c = (char) (b + '0');
        System.out.println(c);

        //ASCII 97 ~ 122 (a -> z)
        char d = 'b';
        //0 ~ 25
        System.out.println(d - 'a');
    }

    public static void process(char[] str, int index, List<String> ans){
        if (index == str.length){
            ans.add(String.valueOf(str));
            return;
        }
        for (int i = index; i < str.length; i++) {
            swap(str, index, i);
            process(str, index + 1, ans);
            swap(str, index, i);
        }
    }

    public static void process1(char[] str, int index, List<String> ans){
        if (index == str.length){
            ans.add(String.valueOf(str));
            return;
        }
        Set<Character> set = new HashSet<>();
        for (int i = index; i < str.length; i++) {
            //剪枝
            //如果str[i]已经来到了index位置过，那么就不再往下展开
            if (!set.contains(str[i])){
                set.add(str[i]);
                swap(str, index, i);
                process1(str, index + 1, ans);
                swap(str, index, i);
            }
        }
    }

    public static void process2(char[] str, int index, List<String> ans){
        if (index == str.length){
            ans.add(String.valueOf(str));
            return;
        }
        boolean[] flag = new boolean[26]; //0~25个位置 -> 26个字母
        for (int i = index; i < str.length; i++) {
            //剪枝
            //如果str[i]已经来到了index位置过，那么就不再往下展开
            if (!flag[str[i] - 'a']){
                flag[str[i] - 'a'] = true;
                swap(str, index, i);
                process2(str, index + 1, ans);
                swap(str, index, i);
            }
        }
    }

    public static void swap(char[] str, int i, int j){
        char tem = str[i];
        str[i] = str[j];
        str[j] = tem;
    }
}

