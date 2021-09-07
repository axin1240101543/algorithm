package com.darren.center.algorithm.code;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2021年09月01日 07:59:07
 * 	// 给定一个数组arr，在不能取相邻数的情况下，返回所有组合中的最大累加和
 * 	// 思路：
 * 	// 定义dp[i] : 表示arr[0...i]范围上，在不能取相邻数的情况下，返回所有组合中的最大累加和
 * 	// 在arr[0...i]范围上，在不能取相邻数的情况下，得到的最大累加和，可能性分类：
 * 	// 可能性 1) 选出的组合，不包含arr[i]。那么dp[i] = dp[i-1]
 * 	// 比如，arr[0...i] = {3,4,-4}，最大累加和是不包含i位置数的时候
 * 	//
 * 	// 可能性 2) 选出的组合，只包含arr[i]。那么dp[i] = arr[i]
 * 	// 比如，arr[0...i] = {-3,-4,4}，最大累加和是只包含i位置数的时候
 * 	//
 * 	// 可能性 3) 选出的组合，包含arr[i], 且包含arr[0...i-2]范围上的累加和。那么dp[i] = arr[i] + dp[i-2]
 * 	// 比如，arr[0...i] = {3,1,4}，最大累加和是3和4组成的7，因为相邻不能选，所以i-1位置的数要跳过
 * 	//
 * 	// 综上所述：dp[i] = Max { dp[i-1], arr[i] , arr[i] + dp[i-2] }
 **/
public class Problem_053_MaximumSubarray_Ii {

    public static void main(String[] args) {
        Solution solution = new Problem_053_MaximumSubarray_Ii().new Solution();
        int maxn = 20;
        int maxr = 10;
        int testTimes = 200000;
        System.out.println("start");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerRandomArray(maxn, maxr);
            int ans1 = solution.maxSubArray(arr);
            int ans2 = solution.maxSum(arr);
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
            if (nums == null || nums.length == 0){
                return 0;
            }

            if (nums.length == 1){
                return nums[0];
            }

            if (nums.length == 2){
                return Math.max(nums[0], nums[1]);
            }

            int n = nums.length;
            int[] dp = new int[n];
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);
            for (int i = 2; i < n; i++) {
                //三种可能性pk出最大值
                dp[i] = Math.max(Math.max(nums[i], dp[i-1]), nums[i] + dp[i - 2]);
            }
            return dp[n-1];
        }

        public int maxSum(int[] arr) {
            if (arr == null || arr.length == 0) {
                return 0;
            }
            int N = arr.length;
            if (N == 1) {
                return arr[0];
            }
            if (N == 2) {
                return Math.max(arr[0], arr[1]);
            }
            int[] dp = new int[N];
            dp[0] = arr[0];
            dp[1] = Math.max(arr[0], arr[1]);
            for (int i = 2; i < N; i++) {
                dp[i] = Math.max(Math.max(dp[i - 1], arr[i]), arr[i] + dp[i - 2]);
            }
            return dp[N - 1];
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

