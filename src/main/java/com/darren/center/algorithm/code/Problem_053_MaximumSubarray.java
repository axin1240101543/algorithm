package com.darren.center.algorithm.code;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2021年09月01日 07:59:07
 **/
public class Problem_053_MaximumSubarray {

    public static void main(String[] args) {
        Solution solution = new Problem_053_MaximumSubarray().new Solution();
        int maxn = 20;
        int maxr = 10;
        int testTimes = 20000000;
        System.out.println("start");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerRandomArray(maxn, maxr);
            int ans1 = solution.maxSubArray(arr);
            int ans2 = solution.maxSubArray2(arr);
            if (ans1 != ans2){
                printArray(arr);
                System.out.println("ans1:" + ans1);
                System.out.println("ans2:" + ans2);
                break;
            }
        }
        System.out.println("end");
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxSubArray(int[] nums) {
            int cur = 0;
            int max = nums[0];
            for (int i = 0; i < nums.length; i++) {
                cur = cur + nums[i];
                max = Math.max(max, cur);
                cur = cur < 0 ? 0 : cur;
            }
            return max;
        }

        public int maxSubArray2(int[] nums) {
            int n = nums.length;
            int[] dp = new int[n];
            dp[0] = nums[0];
            int max = nums[0];
            for (int i = 1; i < nums.length; i++) {
                int p1 = nums[i];
                int p2 = nums[i] + dp[i - 1];
                dp[i] = Math.max(p1, p2);
                max = Math.max(max, dp[i]);
            }
            return max;
        }
    }

    public static int[] gerRandomArray(int n, int r){
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * r);
        }
        return arr;
    }

    public static void printArray(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}

