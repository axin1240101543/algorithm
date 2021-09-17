package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-09-17 10:49:05
 * Version: 1.0
 * Description:
 */
public class Problem_088_MergeSortedArray {

    public static void main(String[] args) {
        Solution solution = new Problem_088_MergeSortedArray().new Solution();
    }

    class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            if (n == 0) {
                return;
            }
            if (m == 0){
                for (int i = 0; i < nums1.length; i++) {
                    nums1[i] = nums2[i];
                }
                return;
            }

            int r = nums1.length;
            while(m > 0 && n > 0){
                if (nums1[m - 1] >= nums2[n - 1]){
                    nums1[--r] = nums1[m - 1];
                    m--;
                }else{
                    nums1[--r] = nums2[n - 1];
                    n--;
                }
            }

            while (n > 0){
                nums1[--r] = nums2[n - 1];
                n--;
            }
        }
    }

}
