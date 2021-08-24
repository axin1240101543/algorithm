package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-08-24 10:17:37
 * Version: 1.0
 * Description:
 */
public class Problem_044_WildcardMatching {
    public static void main(String[] args) {
        Solution solution = new Problem_044_WildcardMatching().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 动态规划 + 枚举情况 -> 斜率优化
         * @param s
         * @param p
         * @return
         */
        public boolean isMatch(String s, String p) {
            char[] str = s.toCharArray();
            char[] pattern = p.toCharArray();
            int n = s.length();
            int m = p.length();
            //si ... n
            //pi ... m
            boolean[][] dp = new boolean[n + 1][m + 1];
            dp[n][m] = true;
            for (int pi = m - 1; pi >=0; pi--){
                dp[n][pi] = pattern[pi] == '*' && dp[n][pi + 1];
            }

            //pi这一列默认为false 除了dp[n][m]位置为true
//            if (pi == pattern.length){
//                return si == str.length;
//            }

            for (int si = n - 1; si >= 0; si--){
                for (int pi = m - 1; pi >= 0; pi--){
                    if (pattern[pi] != '?' && pattern[pi] != '*'){
                        dp[si][pi] = str[si] == pattern[pi] && dp[si + 1][pi + 1];
                        continue;
                    }

                    if (pattern[pi] == '?'){
                        dp[si][pi] = dp[si + 1][pi + 1];
                        continue;
                    }

                    //pi是*
                    dp[si][pi] = dp[si][pi + 1] || dp[si + 1][pi];
                    /*for (int len = 0; len <= str.length - si; len++){
                        if (dp[si + len][pi + 1]){
                            dp[si][pi] = true;
                            break;
                        }
                    }*/
                }
            }
            return dp[0][0];
        }

        /**
         * 动态规划
         * @param s
         * @param p
         * @return
         */
        public boolean isMatch2(String s, String p) {
            char[] str = s.toCharArray();
            char[] pattern = p.toCharArray();
            int n = s.length();
            int m = p.length();
            //si ... n
            //pi ... m
            boolean[][] dp = new boolean[n + 1][m + 1];
            dp[n][m] = true;
            for (int pi = m - 1; pi >=0; pi--){
                dp[n][pi] = pattern[pi] == '*' && dp[n][pi + 1];
            }

            //pi这一列默认为false 除了dp[n][m]位置为true
//            if (pi == pattern.length){
//                return si == str.length;
//            }

            for (int si = n - 1; si >= 0; si--){
                for (int pi = m - 1; pi >= 0; pi--){
                    if (pattern[pi] != '?' && pattern[pi] != '*'){
                        dp[si][pi] = str[si] == pattern[pi] && dp[si + 1][pi + 1];
                        continue;
                    }

                    if (pattern[pi] == '?'){
                        dp[si][pi] = dp[si + 1][pi + 1];
                        continue;
                    }

                    //pi是*
                    for (int len = 0; len <= str.length - si; len++){
                        if (dp[si + len][pi + 1]){
                            dp[si][pi] = true;
                            break;
                        }
                    }
                }
            }
            return dp[0][0];
        }

        /**
         * 暴力递归（会超时）
         * @param s
         * @param p
         * @return
         */
        public boolean isMatch1(String s, String p) {
            char[] str = s.toCharArray();
            char[] pattern = p.toCharArray();
            return process1(str, pattern, 0, 0);
        }

        public boolean process1(char[] str, char[] pattern, int si, int pi){
            if (si == str.length){
                if (pi == pattern.length){
                    return true;
                }
                if (pattern[pi] == '*' && process1(str, pattern, si, pi + 1)){
                    return true;
                }
                return false;
            }

            if (pi == pattern.length){
                return si == str.length;
            }

            if (pattern[pi] != '?' && pattern[pi] != '*'){
                return str[si] == pattern[pi] && process1(str, pattern, si + 1, pi + 1);
            }

            if (pattern[pi] == '?'){
                return process1(str, pattern, si + 1, pi + 1);
            }

            //pi是*
            //si si+1 ... n-1 n
            //7 8 9 10 11
            //7 8 9 10 n
            //11 - 7 = 4(7 8 9 10)
            for (int len = 0; len <= str.length - si; len++){
                if (process1(str, pattern, si + len, pi + 1)){
                    return true;
                }
            }
            return false;
        }
    }
}
