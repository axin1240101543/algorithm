package com.darren.center.algorithm.code;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2021年08月19日 09:33:48
 **/
public class Problem_026_RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        Solution solution = new Problem_026_RemoveDuplicatesFromSortedArray().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int removeDuplicates(int[] nums) {
            if (nums == null || nums.length == 0){
                return 0;
            }
            int index = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[index] != nums[i]){
                    nums[++index] = nums[i];
                }
            }
            return index + 1;
        }
    }

}

