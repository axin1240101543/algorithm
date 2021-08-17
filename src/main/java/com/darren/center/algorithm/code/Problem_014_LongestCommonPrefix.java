package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-08-17 15:38:33
 * Version: 1.0
 * Description:
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 *  示例 1：
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 *
 *  示例 2：
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 *
 *  提示：
 *  1 <= strs.length <= 200
 *  0 <= strs[i].length <= 200
 *  strs[i] 仅由小写英文字母组成
 */
public class Problem_014_LongestCommonPrefix {

    public static void main(String[] args) {
        Solution solution = new Problem_014_LongestCommonPrefix().new Solution();
        String[] strs = {"flower","flow","flight"};
        String s = solution.longestCommonPrefix(strs);
        System.out.println(s);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0){
                return "";
            }
            char[] chars = strs[0].toCharArray();
            int min = 200;
            for (String str : strs) {
                char[] tmep = str.toCharArray();
                int index = 0;
                while (index < chars.length && index < tmep.length){
                    if (chars[index] != tmep[index]){
                        break;
                    }
                    index++;
                }
                min = Math.min(min, index);
            }
            if (min == 0){
                return "";
            }
            return strs[0].substring(0, min);
        }
    }

}
