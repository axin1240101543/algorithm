package com.darren.center.algorithm.code;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2021年09月01日 07:59:07
 **/
public class Problem_053_MaximumSubarray {

    public static void main(String[] args) {
        Solution solution = new Problem_053_MaximumSubarray().new Solution();
        int[] arr = {-2,1,-3,4,-1,2,1,-5,4};
        int i = solution.maxSubArray(arr);
        System.out.println(i);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxSubArray(int[] nums) {
            int cur = 0;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                cur = cur + nums[i];
                max = Math.max(max, cur);
                cur = cur < 0 ? 0 : cur;
            }
            return max;
        }
    }

}

