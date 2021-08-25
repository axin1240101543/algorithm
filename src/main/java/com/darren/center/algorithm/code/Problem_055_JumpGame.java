package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-08-25 10:16:33
 * Version: 1.0
 * Description:
 */
public class Problem_055_JumpGame {
    public static void main(String[] args) {
        Solution solution = new Problem_055_JumpGame().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean canJump(int[] nums) {
            if(nums == null || nums.length < 2){
                return true;
            }
            //往右到达的最大边界
            int max = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (max >= nums.length - 1){
                    return true;
                }
                //看下i是否能来到  max是否小于i
                if (max < i){
                    return false;
                }
                max = Math.max(max, i + nums[i]);
            }
            return true;
        }
    }
}
