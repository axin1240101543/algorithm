package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-09-17 10:58:20
 * Version: 1.0
 * Description:
 */
public class Problem_091_DecodeWays {

    public static void main(String[] args) {
        Solution solution = new Problem_091_DecodeWays().new Solution();
        String s = "226";
        int res = solution.numDecodings3(s);
        System.out.println(res);
    }
    class Solution {
        public int numDecodings(String s) {
            return process(s.toCharArray(), 0);
        }

        public int numDecodings1(String s) {
            int n = s.length();
            char[] str = s.toCharArray();
            //0 ... n
            int[] dp = new int[n + 1];
            dp[n] = 1;

            for (int i = str.length - 1; i >= 0; i--) {
                if (str[i] == '0'){
                    dp[i] = 0;
                    continue;
                }
                dp[i] = dp[i + 1];
                if (i + 1 == str.length){
                    continue;
                }

                int num = (str[i] - '0') * 10 + (str[i + 1] - '0');
                if (num <= 26){
                    dp[i] += dp[i + 2];
                }
            }

            return dp[0];
        }

        public int process(char[] str, int index){
            if (index == str.length){
                return 1;
            }

            if (str[index] == '0'){
                return 0;
            }

            int ways = process(str, index + 1);

            if (index + 1 == str.length){
                return ways;
            }

            int num = (str[index] - '0') * 10 + (str[index + 1] - '0');
            if (num <= 26){
                ways += process(str, index + 2);
            }
            return ways;
        }


        public int numDecodings2(String s) {
            return process1(s.toCharArray(), 0);
        }

        public int numDecodings3(String s) {
            int n = s.length();
            char[] str = s.toCharArray();
            //0 ... n
            int[] dp = new int[n + 1];
            dp[n] = 1;

            for (int i = str.length - 1; i >= 0; i--) {
                if (str[i] == '0'){
                    dp[i] = 0;
                    continue;
                }

                if (str[i] == '1'){
                    dp[i] = dp[i + 1];
                    if (i + 1 < str.length){
                        dp[i] += dp[i + 2];
                    }
                    continue;
                }

                if (str[i] == '2'){
                    dp[i] = dp[i + 1];
                    if (i + 1 < str.length && (str[i + 1] >= '0' && str[i + 1] <= '6')){
                        dp[i] += dp[i + 2];
                    }
                    continue;
                }

                dp[i] = dp[i + 1];
            }
            return dp[0];
        }

        public int process1(char[] str, int index){
            if (index == str.length){
                return 1;
            }

            if (str[index] == '0'){
                return 0;
            }

            if (str[index] == '1'){
                int ways = process1(str, index + 1);
                if (index + 1 < str.length){
                    ways += process1(str, index + 2);
                }
                return ways;
            }

            if (str[index] == '2'){
                int ways = process1(str, index + 1);
                if (index + 1 < str.length && (str[index + 1] >= '0' && str[index + 1] <= '6')){
                    ways += process1(str, index + 2);
                }
                return ways;
            }

            return process1(str, index + 1);
        }
    }

}
