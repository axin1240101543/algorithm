package com.darren.center.algorithm2023;

import java.util.ArrayList;
import java.util.List;

public class Problem_0015_3Sum {

    public static void main(String[] args) {

    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0){
            return ans;
        }
        for (int i = 0; i < nums.length - 2; i++){
            if (i == 0 || nums[i] != nums[i - 1]){
                List<List<Integer>> lists = twoSum(nums, i + 1, -nums[i]);
                for (List<Integer> list : lists) {
                    list.add(0, nums[i]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    public List<List<Integer>> twoSum(int[] nums, int start, int target){
        List<List<Integer>> ans = new ArrayList<>();
        int l = start;
        int r = nums.length - 1;
        while (l < r){
            int cur = nums[l] + nums[r];
            if (cur < target){
                l++;
            }else if (cur > target){
                r--;
            }else {
                if (l == start || nums[l] != nums[l - 1]){
                    List<Integer> curAns = new ArrayList<>();
                    curAns.add(nums[l]);
                    curAns.add(nums[r]);
                    ans.add(curAns);
                }
                l++;
            }
        }
        return ans;
    }

}
