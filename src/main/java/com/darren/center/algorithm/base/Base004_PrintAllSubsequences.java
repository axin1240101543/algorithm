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
 * 打印一个字符串的全部子序列
 * 每个字符选择要或者不要 依次展开
 **/
public class Base004_PrintAllSubsequences {

    public static void main(String[] args) {
        String s = "abc";
        char[] str = s.toCharArray();
        List<String> ans = new ArrayList<>();
        String path = "";
        subSequences(str, 0, ans, path);
        ans.forEach(System.out::println);

        String s1 = "aaa";
        char[] str1 = s1.toCharArray();
        List<String> ans1 = new ArrayList<>();
        Set<String> set = new HashSet<>();
        subSequences1(str1, 0, set, path);
        set.forEach(v -> ans1.add(v));
        ans1.forEach(System.out::println);
    }


    public static void subSequences(char[] str, int index, List<String> ans, String path){
        if (index == str.length){
            ans.add(path);
            return;
        }
        String no = path;
        subSequences(str, index + 1, ans, no);
        String yes = path + String.valueOf(str[index]);
        subSequences(str, index + 1, ans, yes);
    }

    /**
     * 打印一个字符串的全部子序列，要求不出现重复字面值的子序列
     * @param str
     * @param index
     * @param ans
     * @param path
     */
    public static void subSequences1(char[] str, int index, Set<String> ans, String path){
        if (index == str.length){
            ans.add(path);
            return;
        }
        String no = path;
        subSequences1(str, index + 1, ans, no);
        String yes = path + String.valueOf(str[index]);
        subSequences1(str, index + 1, ans, yes);
    }
}

