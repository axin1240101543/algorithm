package com.darren.center.algorithm.code;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Darren
 * Date: 2021-08-17 17:14:28
 * Version: 1.0
 * Description:
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 *  给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *  示例 1：
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 *
 *  示例 2：
 * 输入：digits = ""
 * 输出：[]
 *
 *  示例 3：
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 *
 *  提示：
 *  0 <= digits.length <= 4
 *  digits[i] 是范围 ['2', '9'] 的一个数字。
 */
public class Problem_017_LetterCombinationsOfAPhoneNumber {

    public static void main(String[] args) {
        Solution solution = new Problem_017_LetterCombinationsOfAPhoneNumber().new Solution();
        List<String> strings = solution.letterCombinations("23");
        strings.forEach(System.out::println);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public char[][] a = {{}, {},
                {'a', 'b', 'c'},
                {'d', 'e', 'f'},
                {'g', 'h', 'i'},
                {'j', 'k', 'l'},
                {'m', 'n', 'o'},
                {'p', 'q', 'r', 's'},
                {'t', 'u', 'v'},
                {'w', 'x', 'y', 'z'}
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
                char[] cur = a[digit - '0'];
                for (char c : cur) {
                    path[index] = c;
                    process(chars, index + 1, path, ans);
                }
            }
        }
    }

}
