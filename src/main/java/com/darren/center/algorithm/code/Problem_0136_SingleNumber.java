package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-11-04 10:50:54
 * Version: 1.0
 * Description:
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 *  说明：
 *  你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 *  示例 1:
 *  输入: [2,2,1]
 * 输出: 1
 *
 *
 *  示例 2:
 *  输入: [4,1,2,1,2]
 * 输出: 4
 */
public class Problem_0136_SingleNumber {

    public static void main(String[] args) {
        Solution solution = new Problem_0136_SingleNumber().new Solution();
    }
    class Solution {
        public int singleNumber(int[] nums) {
            if (nums == null || nums.length == 0){
                return 0;
            }
            int ans = 0;
            for(int num : nums){
                ans ^= num;
            }
            return ans;
        }

        public int singleNumber1(int[] nums) {
            if (nums == null || nums.length == 0){
                return 0;
            }
            int ans = 0;
            for (int i = 0; i < nums.length; i++) {
                ans = ans ^ nums[i];
            }
            return ans;
        }
    }

}
