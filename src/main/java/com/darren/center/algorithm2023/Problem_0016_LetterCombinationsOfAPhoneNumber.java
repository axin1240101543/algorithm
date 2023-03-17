package com.darren.center.algorithm2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_0016_LetterCombinationsOfAPhoneNumber {

    public static void main(String[] args) {

    }

    public char[][] map = new char[][]{
            {},
            {},
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y','z'}
    };

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.length() == 0){
            return ans;
        }
        char[] chars = digits.toCharArray();
        char[] path = new char[chars.length];
        process(chars, 0, path, ans);
        return ans;
    }

    public void process(char[] chars, int index, char[] path, List<String> ans){
        if (index == chars.length){
            ans.add(String.valueOf(path));
        }else {
            char digit = chars[index];
            char[] curArr = map[digit - '0'];
            for (char aChar : curArr) {
                path[index] = aChar;
                process(chars, index + 1, path, ans);
            }
        }
    }

}
