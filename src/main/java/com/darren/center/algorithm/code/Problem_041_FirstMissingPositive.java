package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-08-23 14:39:44
 * Version: 1.0
 * Description:
 */
public class Problem_041_FirstMissingPositive {

    public static void main(String[] args) {
        Solution solution = new Problem_041_FirstMissingPositive().new Solution();
        int[] nums = {2,1};
        int i = solution.firstMissingPositive(nums);
        System.out.println(i);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int firstMissingPositive(int[] nums) {
            int l = 0;
            int r = nums.length;
            while (l < r){
                if(nums[l] == l + 1){
                    l++;
                }else if(nums[l] > r || nums[l] <= l || nums[nums[l] - 1] == nums[l]){
                    swap(nums, l, --r);
                }else{
                    swap(nums, l, nums[l] - 1);
                }
            }
            return l + 1;
        }

        public void swap(int[] arr, int i, int j){
            int tem = arr[i];
            arr[i] = arr[j];
            arr[j] = tem;
        }
    }

}
