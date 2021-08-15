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
            int left = 0;
            int right = height.length - 1;
            while (left < right){
                max = Math.max(max, Math.min(height[left], height[right]) * (right - left));
                if (height[left] > height[right]){
                    right--;
                }else{
                    left++;
                }
            }
            return max;
        }
    }

}

