package com.darren.center.algorithm.code;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Darren
 * Date: 2021-08-18 14:31:31
 * Version: 1.0
 * Description:
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *  有效括号组合需满足：左括号必须以正确的顺序闭合。
 *
 *  示例 1：
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 *
 *  示例 2：
 * 输入：n = 1
 * 输出：["()"]
 *
 *  提示：
 *  1 <= n <= 8
 *
 *  Related Topics 字符串 动态规划 回溯
 */
public class Problem_022_GenerateParentheses {
    public static void main(String[] args) {
        Solution solution = new Problem_022_GenerateParentheses().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> generateParenthesis2(int n) {
            if (n == 0){
                return null;
            }
            List<String> ans = new ArrayList<>();
            char[] path = new char[n << 1];
            int index = 0;
            process2(index, 0, n, path, ans);
            return ans;
        }

        private void process2(int index, int leftMinusRight, int leftNum, char[] path, List<String> ans) {
            if (index == path.length){
                ans.add(String.valueOf(path));
            }else{
                if (leftNum > 0){
                    path[index] = '(';
                    process2(index + 1, leftMinusRight + 1, leftNum - 1, path, ans);
                }
                if (leftMinusRight > 0){
                    path[index] = ')';
                    process2(index + 1, leftMinusRight - 1, leftNum, path, ans);
                }
            }
        }

        public List<String> generateParenthesis(int n) {
            if (n == 0){
                return null;
            }
            List<String> ans = new ArrayList<>();
            char[] path = new char[n << 1];
            int index = 0;
            process(index, path, ans);
            return ans;
        }

        private void process(int index, char[] path, List<String> ans) {
            if (index == path.length){
                if (isValid(path)){
                    ans.add(String.valueOf(path));
                }
            }else{
                path[index] = '(';
                process(index + 1, path, ans);
                path[index] = ')';
                process(index + 1, path, ans);
            }
        }

        private boolean isValid(char[] path) {
            int count = 0;
            for (int i = 0; i < path.length; i++) {
                if (count < 0){
                    return false;
                }
                if ('(' == path[i]){
                    count++;
                }else{
                    count--;
                }
            }
            if (count != 0){
                return false;
            }
            return true;
        }
    }
}
