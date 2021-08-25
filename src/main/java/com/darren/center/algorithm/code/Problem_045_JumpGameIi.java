package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-08-25 10:16:07
 * Version: 1.0
 * Description:
 */
public class Problem_045_JumpGameIi {
    public static void main(String[] args) {
        Solution solution = new Problem_045_JumpGameIi().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int jump(int[] nums) {
            if (nums == null || nums.length == 0){
                return 0;
            }

            //step 当前最少几步能到i位置
            //cur  判断当前是否能到，不能到将next更新为next step+1
            //next 当前能跳的最远的值 如果当前的值比next的值大那么更新
            int step = 0;
            int cur = 0;
            int next = nums[0];

            for (int i = 1; i < nums.length; i++) {
                if (next >= nums.length - 1){
                    return step + 1;
                }

                if (cur < i){
                    step++;
                    cur = next;
                }

                next = Math.max(next, i + nums[i]);
            }

            return step;
        }
    }
}
