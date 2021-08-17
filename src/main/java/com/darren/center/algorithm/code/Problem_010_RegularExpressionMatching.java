package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-08-16 14:55:09
 * Version: 1.0
 * Description:
 *  给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '' 的正则表达式匹配。
 *
 *
 *    '.' 匹配任意单个字符
 *    '*' 匹配零个或多个前面的那一个元素
 *
 *
 *    所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 *    0 <= s.length <= 20
 *    0 <= p.length <= 30
 *    s 可能为空，且只包含从 a-z 的小写字母。
 *    p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 *    保证每次出现字符  时，前面都匹配到有效的字符
 */
public class Problem_010_RegularExpressionMatching {
    public static void main(String[] args) {
        Solution solution = new Problem_010_RegularExpressionMatching().new Solution();

    }
    class Solution {
        public boolean isMatch(String s, String p) {
            if(s == null || p == null){
                return false;
            }
            char[] str = s.toCharArray();
            char[] pattern = p.toCharArray();
            return isValid(str, pattern) && process(str, pattern, 0, 0);
        }

        public boolean process(char[] str, char[] pattern, int si, int pi){
            //str是""
            if(si == str.length){
                //pi是""
                if(pi == pattern.length){
                    return true;
                }
                //pi没有越界  pi+2往后也要变成"" 才能匹配
                //a*b*c*
                if(pi + 1 < pattern.length && pattern[pi + 1] == '*'){
                    return process(str, pattern, si, pi + 2);
                }
                return false;
            }

            //pi是""
            if(pi == pattern.length){
                //si是""
                return si == str.length;
            }

            //pi + 1 不是*  那么si的位置必须要和pi的位置要匹配
            //pi + 1 越界  那么 pi + 1 肯定不是 *
            if(pi + 1 >= pattern.length || pattern[pi + 1] != '*'){
                //si 和 pi位置匹配上了  且  si + 1 和 pi + 1 位置也要匹配
                return (str[si] == pattern[pi] || pattern[pi] == '.') && process(str, pattern, si + 1, pi + 1);
            }

            //pi + 1 是 *
            //si 和 pi 不匹配  ？* == 要变成0
            if(str[si] != pattern[pi] && pattern[pi] != '.'){
                //pi + 2 往后和 si进行匹配
                return process(str, pattern, si, pi + 2);
            }

            //pi + 1 是 *
            //si 和 pi 匹配
            // str[aaabcd] pattern[a*]/[.*]
            //0 1 2 3四种情况
            //0 a*或者.*变为0个a  pattern可能为aaabcd
            //1 a*或者.*变为1个a  pattern可能为aabcd si+1 pi+2 si++
            //2 a*或者.*变为2个a  pattern可能为abcd si+1 pi+2 si++
            //直到si++ 不是a
            if(process(str, pattern, si, pi + 2)){
                //情况0
                return true;
            }

            while(si < str.length && (str[si] == pattern[pi] || pattern[pi] == '.')){
                //情况 1 2 3 ...  只有 str[si] 不等于 pattern[pi] 那么就跳出循环
                if(process(str, pattern, si + 1, pi + 2)){
                    return true;
                }
                si++;
            }
            return false;
        }

        public boolean isValid(char[] str, char[] pattern){
            for(int i = 0; i < str.length; i++){
                if(str[i] == '.' || str[i] == '*'){
                    return false;
                }
            }
            for(int i = 0; i < pattern.length; i++){
                if(pattern[0] == '*' ||
                        ((i + 1 < pattern.length) && pattern[i] == '*' && pattern[i+1] == '*')){
                    return false;
                }
                // if (pattern[i] == '*' && (i == 0 || pattern[i - 1] == '*')){
                //     return false;
                // }
            }
            return true;
        }
    }
}
