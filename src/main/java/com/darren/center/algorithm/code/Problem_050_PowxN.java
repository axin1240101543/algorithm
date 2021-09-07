package com.darren.center.algorithm.code;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2021年09月01日 07:39:03
 **/
public class Problem_050_PowxN {

    public static void main(String[] args) {
        Solution solution = new Problem_050_PowxN().new Solution();
        double v = solution.myPow(-1D, -2147483648);
        System.out.println(v);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double myPow(double x, int n) {
            int a = Math.abs(n == Integer.MIN_VALUE ? n + 1 : n);
            double t = x;
            double ans = 1D;
            while(a != 0){
                if((a & 1) == 1){
                    ans = ans * t;
                }
                a = a >> 1;
                t = t * t;
            }

            if (n == Integer.MIN_VALUE){
                ans = ans * x;
            }

            return n < 0 ? (1D / ans) : ans;
        }
    }

}

