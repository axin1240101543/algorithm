package com.darren.center.algorithm.code;

import java.util.Arrays;

/**
 * Author: Darren
 * Date: 2021-08-23 15:08:01
 * Version: 1.0
 * Description:
 */
public class Problem_042_TrappingRainWater {

    public static void main(String[] args) {
        Solution solution = new Problem_042_TrappingRainWater().new Solution();
        int[] heigth = {0,1,0,2,1,0,1,3,2,1,2,1};
        int trap = solution.trap(heigth);
        int trap1 = solution.trap1(heigth);
        System.out.println(trap);
        System.out.println(trap1);
    }
    class Solution {

        public int trap1(int[] h){
           int l = 1;
           int r = h.length - 2;
           int lMax = h[0];
           int rMax = h[h.length - 1];
           int res = 0;
           while (l <= r){
               if (lMax <= rMax){
                   //如果左边的最大值 小于 右边的最大值 那么结算h[l]位置的水量
                   //因为对于l位置来说 左边的最大值是固定的  右边的最大值 不管中间的值是大或者小 都不会小于此时右边的最大值 水不会从右边流走
                   //拿左边的最大值 减去 l的高度 那么就得到了l位置的水量
                   res += Math.max(0, lMax - h[l]);
                   lMax = Math.max(lMax, h[l++]);
               }else{
                   //如果左边的最大值 大于 右边的最大值 那么结算h[r]位置的水量
                   //因为对于r位置来说 右边的最大值是固定的  左边的最大值 不管中间的值是大或者小 都不会小于此时左边的最大值 水不会从左边流走
                   //拿右边的最大值 减去 r的高度 那么就得到了r位置的水量
                   res += Math.max(0, rMax - h[r]);
                   rMax = Math.max(rMax, h[r--]);
               }
           }
           return res;
        }

        public int trap(int[] height){
            int[] left = new int[height.length];
            int[] right = new int[height.length];
            int leftMax = 0;
            int rightMax = 0;
            for (int i = 0; i < height.length; i++) {
                if(i == 0 || height[i] > leftMax){
                    left[i] = height[i];
                    leftMax = Math.max(leftMax, height[i]);
                }else{
                    left[i] = leftMax;
                }
            }
            for (int i = height.length - 1; i >= 0; i--) {
                if(i == height.length - 1 || height[i] > rightMax){
                    right[i] = height[i];
                    rightMax = Math.max(rightMax, height[i]);
                }else{
                    right[i] = rightMax;
                }
            }
            //011222233333
            //333333332221
            int res = 0;
            for (int i = 1; i < height.length - 1; i++) {
                int min = Math.min(left[i - 1], right[i + 1]);
                if(min < height[i]){
                    continue;
                }
                res = res + (min - height[i]);
            }
            return res;
        }
    }

}
