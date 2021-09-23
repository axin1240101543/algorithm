package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-09-23 17:18:29
 * Version: 1.0
 * Description:
 */
public class Problem_070_ClimbingStairs {

    public static void main(String[] args) {
        Solution solution = new Problem_070_ClimbingStairs().new Solution();
    }
    class Solution {
        public int climbStairs(int n) {
            int[] dp = new int[n + 1];
            if (n >= 1){
                dp[0] = 1;
            }

            if (n >= 2){
                dp[1] = 2;
            }

            for (int i = 2; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }

            return dp[n - 1];
        }
    }

}
