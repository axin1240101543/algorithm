package com.darren.center.algorithm.code;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: Darren
 * Date: 2021-09-23 14:36:21
 * Version: 1.0
 * Description:
 */
public class Problem_078_Subsets {

    public static void main(String[] args) {
        Solution solution = new Problem_078_Subsets().new Solution();
        int[] nums = {1,2,3};
        List<List<Integer>> subsets = solution.subsets(nums);
        for (List<Integer> subset : subsets) {
            for (Integer integer : subset) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> ans = new ArrayList<>();
            LinkedList<Integer> path = new LinkedList<>();
            process(nums, 0, path, ans);
            return ans;
        }

        public void process(int[] nums, int index, LinkedList<Integer> path, List<List<Integer>> ans){
            if (index == nums.length){
                List<Integer> cur = new ArrayList<>();
                for (Integer integer : path) {
                    cur.add(integer);
                }
                ans.add(cur);
            }else{
                process(nums, index + 1, path, ans);
                path.addLast(nums[index]);
                process(nums, index + 1, path, ans);
                path.removeLast();
            }
        }
    }
}
