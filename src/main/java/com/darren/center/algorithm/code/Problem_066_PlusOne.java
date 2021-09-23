package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-09-23 18:03:06
 * Version: 1.0
 * Description:
 */
public class Problem_066_PlusOne {
    public static void main(String[] args) {
        Solution solution = new Problem_066_PlusOne().new Solution();
    }

    class Solution {
        public int[] plusOne(int[] digits) {
            if (digits == null || digits.length == 0){
                return null;
            }

            for (int i = digits.length - 1; i >= 0; i--) {
                if (digits[i] < 9){
                    digits[i]++;
                    return digits;
                }else{
                    digits[i] = 0;
                }
            }

            //9 + 1 = 10
            //99 + 1 = 100
            int[] ans = new int[digits.length + 1];
            ans[0] = 1;
            return ans;
        }

        public int[] plusOne1(int[] digits) {
            if (digits == null || digits.length == 0){
                return null;
            }

            boolean flag = false;
            for (int i = digits.length - 1; i >= 0; i--) {
                if (digits[i] < 9){
                    digits[i]++;
                    return digits;
                }else{
                    flag = true;
                    digits[i] = 0;
                }
            }

            //9 + 1 = 10
            //99 + 1 = 100
            int[] ans = new int[digits.length + 1];
            if (flag){
                ans[0] = 1;
            }
            return ans;
        }
    }

}
