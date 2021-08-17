package com.darren.center.algorithm.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Darren
 * Date: 2021-08-17 16:10:38
 * Version: 1.0
 * Description:
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
 * 复的三元组。
 *
 *  注意：答案中不可以包含重复的三元组。
 *
 *  示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 *
 *
 *  示例 2：
 * 输入：nums = []
 * 输出：[]
 *
 *
 *  示例 3：
 * 输入：nums = [0]
 * 输出：[]
 *
 *  提示：
 *  0 <= nums.length <= 3000
 *  -105 <= nums[i] <= 105
 */
public class Problem_015_ThreeSum {

    public static void main(String[] args) {
        Solution solution = new Problem_015_ThreeSum().new Solution();
        int[] nums = {-1,0,1,2,-1,-4};
        solution.threeSum(nums);
//        List<List<Integer>> lists = solution.twoSum(nums, 0, 0);
        System.out.println();
    }
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> ans = new ArrayList<>();
            if (nums == null || nums.length == 0){
                return ans;
            }
            Arrays.sort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (i == 0 || nums[i] != nums[i-1]){
                    List<List<Integer>> cur = twoSum(nums, i + 1, -nums[i]);
                    for (List<Integer> list : cur) {
                        list.add(0, nums[i]);
                        ans.add(list);
                    }
                }
            }
            return ans;
        }

        //nums是有序的
        public List<List<Integer>> twoSum(int[] nums, int begin, int target){
            int l = begin;
            int r = nums.length - 1;
            List<List<Integer>> ans = new ArrayList<>();
            while (l < r){
                if (nums[l] + nums[r] > target){
                    r--;
                }else if (nums[l] + nums[r] < target){
                    l++;
                }else {
                    if (l == begin || nums[l] != nums[l - 1]){
                        List<Integer> cur = new ArrayList<>();
                        cur.add(nums[l]);
                        cur.add(nums[r]);
                        ans.add(cur);
                    }
                    l++;
                }
            }
            return ans;
        }
    }

}
