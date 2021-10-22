package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-10-19 11:01:38
 * Version: 1.0
 * Description:
 */
public class Problem_0125_ValidPalindrome {

    public static void main(String[] args) {
        Solution solution = new Problem_0125_ValidPalindrome().new Solution();
        solution.isPalindrome("race a car");
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

//        给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
//
//        说明：本题中，我们将空字符串定义为有效的回文串。
//
//        示例 1:
//        输入: "A man, a plan, a canal: Panama"
//        输出: true
//        解释："amanaplanacanalpanama" 是回文串

        //一句话解释  跳过空格和其他字符 如果数字和数字一样或者字母和字母（忽略大小写）一样 那么就是回文串
        //思路：
        //准备两个指针l r
        //如果l遇到空格和其他字符 l + 1
        //如果r遇到空格其他字符 r - 1
        //如果l和r是数字或者字符 就进行比较
        //怎么比较数字？  怎么比较字母？  怎么比较忽略大小写的字母？
        //a和A的ASCII码之差的绝对值为32  |A:65 - a:97| = 32 |F:70 f:102| = 32
        public boolean isPalindrome(String s) {
            if (s == null || s.length() == 0){
                return false;
            }

            if (s.length() == 1){
                return true;
            }

            char[] strs = s.toCharArray();
            int l = 0;
            int r = strs.length - 1;

            while (l < r){
                ////如果l和r是数字或者字符 就进行比较
                if (isValid(strs[l]) && isValid(strs[r])){
                    if (!equals(strs[l], strs[r])){
                        return false;
                    }
                    l++;
                    r--;
                }else{
                    //如果l遇到空格和其他字符 l + 1
                    //如果r遇到空格其他字符 r - 1
                    l = isValid(strs[l]) ? l : l + 1;
                    r = isValid(strs[r]) ? r : r - 1;
                }
            }
            return true;
        }

        public boolean equals(char c1, char c2){
            if (isNumber(c1) || isNumber(c2)){
                return c1 == c2;
            }
            return (c1 == c2) || Math.abs(c1 - c2) == 32;
        }

        public boolean isNumber(char c){
            if (c >= '0' && c <= '9'){
                return true;
            }
            return false;
        }

        public boolean isLetter(char c){
            if (c >= 'a' && c <= 'z' || (c >= 'A' && c <= 'Z')){
                return true;
            }
            return false;
        }

        public boolean isValid(char c){
            return isLetter(c) || isNumber(c);
        }
    }

}
