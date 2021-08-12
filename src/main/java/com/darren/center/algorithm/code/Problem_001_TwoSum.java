package com.darren.center.algorithm.code;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem_001_TwoSum {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 7, 15};
        int target = 14;
        int[] t = t(nums, target);
        Arrays.stream(t).forEach(v -> System.out.println(v));
    }

    private static int[] t(int[] nums, int target) {
        if (nums == null || nums.length <= 1){
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if (map.containsKey(target - nums[i])){
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{0,0};
    }

}
