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

        /**
         * 枚举行为优化后的尝试函数，改成动态规划的解
         * 如果str长度为N，pattern长度为M，最终时间复杂度为O(N*M)
         * @param str
         * @param pattern
         * @return
         */
        public boolean isMatch4(String str, String pattern) {
            if (str == null || pattern == null) {
                return false;
            }
            char[] s = str.toCharArray();
            char[] p = pattern.toCharArray();
            if (!isValid(s, p)) {
                return false;
            }
            int N = s.length;
            int M = p.length;
            boolean[][] dp = new boolean[N + 1][M + 1];
            dp[N][M] = true;
            for (int j = M - 1; j >= 0; j--) {
                dp[N][j] = (j + 1 < M && p[j + 1] == '*') && dp[N][j + 2];
            }
            // dp[0..N-2][M-1]都等于false，只有dp[N-1][M-1]需要讨论
            if (N > 0 && M > 0) {
                dp[N - 1][M - 1] = (s[N - 1] == p[M - 1] || p[M - 1] == '.');
            }
            for (int i = N - 1; i >= 0; i--) {
                for (int j = M - 2; j >= 0; j--) {
                    if (p[j + 1] != '*') {
                        dp[i][j] = ((s[i] == p[j]) || (p[j] == '.')) && dp[i + 1][j + 1];
                    } else {
                        if ((s[i] == p[j] || p[j] == '.') && dp[i + 1][j]) {
                            dp[i][j] = true;
                        } else {
                            dp[i][j] = dp[i][j + 2];
                        }
                    }
                }
            }
            return dp[0][0];
        }

        /**
         * 枚举行为优化
         * @param s
         * @param p
         * @return
         */
        public boolean isMatch3(String s, String p) {
            if (s == null || p == null) {
                return false;
            }
            char[] str = s.toCharArray();
            char[] pattern = p.toCharArray();
            return isValid(str, pattern) && process3(str, pattern, 0, 0);
        }

        // 举例说明枚举行为优化
        // 求状态(si = 3, pi = 7)时，假设状况如下
        // str : a a a b ...
        // si  : 3 4 5 6 ...
        // pat : a * ? ...
        // pi  : 7 8 9 ...
        // 状态(si = 3, pi = 7)会依赖：
        //   状态(si = 3, pi = 9)
        //   状态(si = 4, pi = 9)
        //   状态(si = 5, pi = 9)
        //   状态(si = 6, pi = 9)
        //
        // 求状态(si = 2, pi = 7)时，假设状况如下
        // str : a a a a b ...
        // si  : 2 3 4 5 6 ...
        // pat : a * ? ...
        // pi  : 7 8 9 ...
        // 状态(si = 2, pi = 7)会依赖：
        //   状态(si = 2, pi = 9)
        //   状态(si = 3, pi = 9)
        //   状态(si = 4, pi = 9)
        //   状态(si = 5, pi = 9)
        //   状态(si = 6, pi = 9)
        //
        // 注意看状态(si = 2, pi = 7)依赖的后4个，其实就是状态(si = 3, pi = 7)
        // 所以状态(si = 2, pi = 7)的依赖可以化简为：
        //   状态(si = 2, pi = 9)
        //   状态(si = 3, pi = 7)
        // 这样枚举行为就被化简成了有限两个状态，详细情况看代码
        public boolean process3(char[] str, char[] pattern, int si, int pi) {
            if (si == str.length && pi == pattern.length) {
                return true;
            }
            if (si == str.length) {
                return (pi + 1 < pattern.length && pattern[pi + 1] == '*') && process3(str, pattern, si, pi + 2);
            }
            if (pi == pattern.length) {
                return false;
            }
            if (pi + 1 >= pattern.length || pattern[pi + 1] != '*') {
                return ((str[si] == pattern[pi]) || (pattern[pi] == '.')) && process3(str, pattern, si + 1, pi + 1);
            }
            // 此处为枚举行为优化，含义看函数注释
            if ((str[si] == pattern[pi] || pattern[pi] == '.') && process3(str, pattern, si + 1, pi)) {
                return true;
            }
            return process3(str, pattern, si, pi + 2);
        }

        /**
         * 动态规划
         * @param s
         * @param p
         * @return
         */
        public boolean isMatch2(String s, String p) {
            if(s == null || p == null){
                return false;
            }
            char[] str = s.toCharArray();
            char[] pattern = p.toCharArray();
            int[][] dp = new int[s.length() + 1][p.length() + 1];
            for (int si = 0; si <= str.length; si++) {
                for (int pi = 0; pi <= pattern.length; pi++) {
                    dp[si][pi] = -1;
                }
            }
            return isValid(str, pattern) && process2(str, pattern, 0, 0, dp);
        }

        public boolean process2(char[] str, char[] pattern, int si, int pi, int[][] dp){

            if (dp[si][pi] != -1){
                return dp[si][pi] == 1;
            }

            //str是""
            if(si == str.length){
                //pi是""
                if(pi == pattern.length){
                    dp[si][pi] = 1;
                    return true;
                }
                //pi没有越界  pi+2往后也要变成"" 才能匹配
                //a*b*c*
                if(pi + 1 < pattern.length && pattern[pi + 1] == '*'){
                    boolean ans = process2(str, pattern, si, pi + 2, dp);
                    dp[si][pi] = ans ? 1 : 0;
                    return ans;
                }
                dp[si][pi] = 0;
                return false;
            }

            //pi是""
            if(pi == pattern.length){
                //si是""
                boolean ans = si == str.length;
                dp[si][pi] = ans ? 1 : 0;
                return ans;
            }

            //pi + 1 不是*  那么si的位置必须要和pi的位置要匹配
            //pi + 1 越界  那么 pi + 1 肯定不是 *
            if(pi + 1 >= pattern.length || pattern[pi + 1] != '*'){
                //si 和 pi位置匹配上了  且  si + 1 和 pi + 1 位置也要匹配
                boolean ans = (str[si] == pattern[pi] || pattern[pi] == '.') && process2(str, pattern, si + 1, pi + 1, dp);
                dp[si][pi] = ans ? 1 : 0;
                return ans;
            }

            //pi + 1 是 *
            //si 和 pi 不匹配  ？* == 要变成0
            if(str[si] != pattern[pi] && pattern[pi] != '.'){
                //pi + 2 往后和 si进行匹配
                boolean ans = process2(str, pattern, si, pi + 2, dp);
                dp[si][pi] = ans ? 1 : 0;
                return ans;
            }

            //pi + 1 是 *
            //si 和 pi 匹配
            // str[aaabcd] pattern[a*]/[.*]
            //0 1 2 3四种情况
            //0 a*或者.*变为0个a  pattern可能为aaabcd
            //1 a*或者.*变为1个a  pattern可能为aabcd si+1 pi+2 si++
            //2 a*或者.*变为2个a  pattern可能为abcd si+1 pi+2 si++
            //直到si++ 不是a
            if(process2(str, pattern, si, pi + 2, dp)){
                //情况0
                dp[si][pi] = 1;
                return true;
            }

            while(si < str.length && (str[si] == pattern[pi] || pattern[pi] == '.')){
                //情况 1 2 3 ...  只有 str[si] 不等于 pattern[pi] 那么就跳出循环
                if(process2(str, pattern, si + 1, pi + 2, dp)){
                    dp[si][pi] = 1;
                    return true;
                }
                si++;
            }
            dp[si][pi] = 0;
            return false;
        }

        /**
         * 暴力递归
         * @param s
         * @param p
         * @return
         */
        public boolean isMatch1(String s, String p) {
            if(s == null || p == null){
                return false;
            }
            char[] str = s.toCharArray();
            char[] pattern = p.toCharArray();
            return isValid(str, pattern) && process1(str, pattern, 0, 0);
        }

        public boolean process1(char[] str, char[] pattern, int si, int pi){
            //str是""
            if(si == str.length){
                //pi是""
                if(pi == pattern.length){
                    return true;
                }
                //pi没有越界  pi+2往后也要变成"" 才能匹配
                //a*b*c*
                if(pi + 1 < pattern.length && pattern[pi + 1] == '*'){
                    return process1(str, pattern, si, pi + 2);
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
                return (str[si] == pattern[pi] || pattern[pi] == '.') && process1(str, pattern, si + 1, pi + 1);
            }

            //pi + 1 是 *
            //si 和 pi 不匹配  ？* == 要变成0
            if(str[si] != pattern[pi] && pattern[pi] != '.'){
                //pi + 2 往后和 si进行匹配
                return process1(str, pattern, si, pi + 2);
            }

            //pi + 1 是 *
            //si 和 pi 匹配
            // str[aaabcd] pattern[a*]/[.*]
            //0 1 2 3四种情况
            //0 a*或者.*变为0个a  pattern可能为aaabcd
            //1 a*或者.*变为1个a  pattern可能为aabcd si+1 pi+2 si++
            //2 a*或者.*变为2个a  pattern可能为abcd si+1 pi+2 si++
            //直到si++ 不是a
            if(process1(str, pattern, si, pi + 2)){
                //情况0
                return true;
            }

            while(si < str.length && (str[si] == pattern[pi] || pattern[pi] == '.')){
                //情况 1 2 3 ...  只有 str[si] 不等于 pattern[pi] 那么就跳出循环
                if(process1(str, pattern, si + 1, pi + 2)){
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
