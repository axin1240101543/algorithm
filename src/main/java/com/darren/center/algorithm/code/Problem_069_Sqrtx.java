package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-09-23 17:43:35
 * Version: 1.0
 * Description:
 */
public class Problem_069_Sqrtx {

    public static void main(String[] args) {
        Solution solution = new Problem_069_Sqrtx().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int mySqrt(int x) {
            long l = 1;
            long r = x;
            long m = 0;
            long ans = 0;
            while (l <= r){
                m = (l + r) >> 1;
                if (m * m <= x){
                    ans = m;
                    l = m + 1;
                }else{
                    r = m - 1;
                }
            }
            return (int) ans;
        }
    }

}
