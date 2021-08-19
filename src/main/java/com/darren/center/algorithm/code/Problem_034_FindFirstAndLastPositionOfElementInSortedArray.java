package com.darren.center.algorithm.code;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2021年08月19日 21:02:17
 **/
public class Problem_034_FindFirstAndLastPositionOfElementInSortedArray {

    public static void main(String[] args) {
        Solution solution = new Problem_034_FindFirstAndLastPositionOfElementInSortedArray().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] searchRange(int[] nums, int target) {
            if (nums == null || nums.length == 0){
                return new int[]{-1, -1};
            }
            int first = first(nums, target);
            int second = second(nums, target);
            return new int[]{first, second};
        }

        public int first(int[] nums, int target){
            int l = 0;
            int r = nums.length - 1;
            int m = 0;
            int ans = -1;
            while (l <= r){
                m = (l + r) / 2;
                if (target < nums[m]){
                    r = m - 1;
                }else if (target > nums[m]){
                    l = m + 1;
                }else{
                    ans = m;
                    r = m - 1;
                }
            }
            return ans;
        }

        public int second(int[] nums, int target){
            int l = 0;
            int r = nums.length - 1;
            int m = 0;
            int ans = -1;
            while (l <= r){
                m = (l + r) / 2;
                if (target < nums[m]){
                    r = m - 1;
                }else if (target > nums[m]){
                    l = m + 1;
                }else{
                    ans = m;
                    l = m + 1;
                }
            }
            return ans;
        }
    }

}

