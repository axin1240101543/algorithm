package com.darren.center.algorithm.code;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2021年08月15日 13:04:40
 **/
public class Problem_011_ContainerWithMostWater {

    public static void main(String[] args) {
        Solution solution = new Problem_011_ContainerWithMostWater().new Solution();
    }
    class Solution {
        public int maxArea(int[] height) {
            int max = 0;
            int l = 0;
            int r = height.length - 1;
            while (l < r){
                int min = Math.min(height[l], height[r]);
                int curMax = min * (r - l);
                max = Math.max(max, curMax);
                if (height[l] > height[r]){
                    r--;
                }else{
                    l++;
                }
            }
            return max;
        }
    }

}

