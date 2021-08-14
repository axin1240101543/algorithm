package com.darren.center.algorithm.code;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2021年08月14日 18:31:59
 **/
public class Problem_007_ReverseInteger {

    public static void main(String[] args) {
        Solution solution = new Problem_007_ReverseInteger().new Solution();
        System.out.println(solution.reverse(-9205));
    }
    class Solution {
        //9205
        public int reverse(int x) {
            boolean negative = ((x >>> 31) & 1) == 1;
            int m = Integer.MIN_VALUE / 10;
            int o = Integer.MIN_VALUE % 10;
            x = negative ? x : -x;
            int result = 0;
            while (x != 0){
                if (result < m || (result == m) && (x % 10 < o)){
                    return 0;
                }
                result = result * 10 + x % 10;
                x /=10;
            }
            return negative ? result : Math.abs(result);
        }
    }

}

