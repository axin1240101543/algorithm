package com.darren.center.algorithm.code;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Author: Darren
 * Date: 2021-08-25 10:16:07
 * Version: 1.0
 * Description:
 *
 * public void jump(int[] arr, int n, int start, int end)
 * n：为数组长度
 * start：开始跳的位置  可以向左跳 可以向右跳  不能越界
 * end：要跳到的位置
 * 从start到end最少需要几步？（start和end是从下标1开始）
 */
public class Problem_045_JumpGameIii {
    public static void main(String[] args) {
        Solution solution = new Problem_045_JumpGameIii().new Solution();
        int maxn = 20;
        int maxr = 10;
        int testTimes = 20000000;
        System.out.println("start");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerRandomArray(maxn, maxr);
            int n = arr.length;
            int start = (int) (Math.random() * n + 1);
            int end = (int) (Math.random() * n + 1);
            int ans1 = solution.jump(arr, n, start, end);
            int ans2 = solution.jump2(arr, n, start, end);
//            int ans3 = solution.jump3(arr, n, start, end);
            int ans4 = solution.jump4(arr, n, start, end);
            if (ans1 != ans2 && ans1 != ans4){
                printArray(arr);
                System.out.println("start:" + start);
                System.out.println("end:" + end);
                System.out.println("ans1:" + ans1);
                System.out.println("ans2:" + ans2);
//                System.out.println("ans3:" + ans3);
                System.out.println("ans3:" + ans4);
                break;
            }
        }
        System.out.println("end");
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        /**
         * 动态规划
         * @param nums
         * @param n
         * @param start
         * @param end
         * @return
         */
        public int jump4(int[] nums, int n, int start, int end) {
            int[][] dp = new int[n + 1][n + 1];
            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp[0].length; j++) {
                    dp[i][j] = -2;
                }
            }
            return g2(nums, n, start, end, 0, dp);
        }

        // 一共有N个位置，跳的过程中，如果你又跳回到某个位置，其实这已经说明不是最优步数了
        // 也就是说，如果存在最优的跳法，那么这个最优跳法一定不会大于N-1步
        // 所以，增加了一个参数k，表示已经跳了多少步
        // 整个函数的含义：
        // 一共有1~N个位置，目标是end位置
        // 所有位置能跳的距离都记录在arr中，并且对任意的arr[i] > 0
        // 当前来到的位置是i, 之前已经跳过了k步，
        // 返回最后到达end位置，跳的最少的步数
        // 如果返回-1表示怎么也无法到达
        public int g2(int[] nums, int n, int i, int end, int k, int[][] dp){
            if (i < 1 || i > n || k > n - 1){
                return -1;
            }

            if (dp[i][k] != -2){
                return dp[i][k];
            }

            if (i == end){
                dp[i][k] = k;
                return k;
            }

            int ans1 = g2(nums, n, i - nums[i - 1], end, k + 1, dp);
            int ans2 = g2(nums, n, i + nums[i - 1], end, k + 1, dp);

            int ans = -1;
            if (ans1 != -1 && ans2 != -1){
                ans = Math.min(ans1, ans2);
            }else if (ans1 != -1){
                ans = ans1;
            }else if (ans2 != -1){
                ans = ans2;
            }
            dp[i][k] = ans;
            return ans;
        }

        /**
         *
         * @param nums
         * @param n
         * @param start
         * @param end
         * @return
         *
         * 参数arr、n、end、i，含义从i出发到end要几步
         * 问题：因为有来回跳的问题，递归出现重复值
         * 加参数：int k 表示已经走了几步，假如有10个数，你的步数不可能超过9步（有重复值时，无所谓，当k大于等于n-1时，退出）
         */
        public int jump3(int[] nums, int n, int start, int end) {
            return g(nums, n, start, end, 0);
        }

        public int g(int[] nums, int n, int i, int end, int k){
            if (i < 1){
                return -1;
            }
            if (i > n){
                return -1;
            }
            if (k > n - 1){
                return -1;
            }

            if (i == end){
                return k;
            }

            int left = i - nums[i - 1];
            int right = i + nums[i - 1];

            int ans = -1;
            int ans1 = g(nums, n, left, end, k + 1);
            int ans2 = g(nums, n, right, end, k + 1);
            if (ans1 != -1 && ans2 != -1){
                ans = Math.min(ans1, ans2);
            }else if (ans1 != -1){
                ans = ans1;
            }else if (ans2 != -1){
                ans = ans2;
            }
            return ans;
        }



        public int jump2(int[] nums, int n, int start, int end) {
            boolean[] walk = new boolean[n];
            return f(nums, n, start, end, walk);
        }

        /**
         *
         * @param nums
         * @param n
         * @param i
         * @param end
         * @param walk
         * @return
         * 参数arr、n、end、i，含义从i出发到end要几步
         * 问题：因为有来回跳的问题，递归出现重复值
         * 加参数：boolean[] walk
         * walk[i - 1] == false  i之前没到过
         * walk[i - 1] == true  i之前到过
         */
        public int f(int[] nums, int n, int i, int end, boolean[] walk){
            //越界：
            //i < 1就不行了
            if (i < 1){
                return -1;
            }
            if (i > n){
                return -1;
            }
            if (walk[i - 1]){
                return -1;
            }
            //如果已经到了，返回0步
            if (i == end){
                return 0;
            }
            walk[i - 1] = true;


            int left = i - nums[i - 1];
            int right = i + nums[i - 1];

            int next = -1;
            int ans1 = f(nums, n, left, end, walk);
            int ans2 = f(nums, n, right, end, walk);
            if (ans1 != -1 && ans2 != -1){
                next = Math.min(ans1, ans2);
            }else if (ans1 != -1){
                next = ans1;
            }else if (ans2 != -1){
                next = ans2;
            }

            //恢复现场（只有最优解走过才算走过，否则会出现不是最优解将这个数标记为走过）
            walk[i - 1] = false;

            if (next == -1){
                return -1;
            }
            return next + 1;
        }

        /**
         *
         * @param nums
         * @param n
         * @param start
         * @param end
         * @return
         * start和end下标都是从1开始
         * 宽度优先遍历（时间复杂度：O(N），最优解），要注意越界和重复值
         */
        public int jump(int[] nums, int n, int start, int end) {
            Queue<Integer> queue = new LinkedList<>();
            //Integer表示下标  Integer表示在第几层
            Map<Integer, Integer> levelMap = new HashMap<>();
            queue.add(start);
            levelMap.put(start, 0);
            while (!queue.isEmpty()){
                Integer index = queue.poll();
                Integer level = levelMap.get(index);
                if (index == end){
                    return level;
                }else{
                    //从当前位置往左跳index-1步  start和end下标都是从1开始
                    int left = index - nums[index - 1];
                    //从当前位置往右跳index-1步  start和end下标都是从1开始
                    int right = index + nums[index - 1];
                    if (left > 0 && !levelMap.containsKey(left)){
                        queue.add(left);
                        levelMap.put(left, level + 1);
                    }

                    if (right <= n && !levelMap.containsKey(right)){
                        queue.add(right);
                        levelMap.put(right, level + 1);
                    }
                }
            }
            return -1;
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
