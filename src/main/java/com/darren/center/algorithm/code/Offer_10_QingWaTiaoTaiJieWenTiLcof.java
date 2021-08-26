package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-08-16 10:21:11
 * Version: 1.0
 * Description:
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * 输入：n = 0
 * 输出：1
 * 0 <= n <= 100
 */
public class Offer_10_QingWaTiaoTaiJieWenTiLcof {

    public static void main(String[] args) {
        Solution solution = new Offer_10_QingWaTiaoTaiJieWenTiLcof().new Solution();
    }
    class Solution {

        /**
         * 用f(1)表示跳到第一个台阶的路径数
         * 用f(2)表示跳到第二个台阶的路径数
         * 用f(3)表示跳到第三个台阶的路径数
         * 用f(4)表示跳到第四个台阶的路径数
         * 用f(n)表示跳到第n个台阶的路径数
         *
         * 当有0个台阶时，有一种方式（算法上的规定）
         * 当有1个台阶时，有一种方式
         * 当有2个台阶时，有两种方式
         * 当有3个台阶时，有三种方式  f(1)+f(2)=1+2=3
         * 这个时候，你站在第一个台阶上到第三个台阶有两种情况，
         * 方法一：从第一个台阶跳两步到达。有f(1)种路径。
         * 方法二：从第二个台阶跳一步到达。有f(2)种路径。
         * 当有4个台阶时，有5种方式  f(2)+f(3)=3+2=5
         * 这个时候，你站在第一个台阶上到第三个台阶有两种情况，
         * 方法一：从第二个台阶跳两步到达。有f(2)种路径。
         * 方法二：从第三个台阶跳一步到达。有f(3)种路径。
         * @param n
         * @return
         */
        public int numWays(int n) {
            if(n <= 1){
                return 1;
            }
            int[] nums = new int[n + 1];
            nums[0] = 1;//台阶为0
            nums[1] = 1;//台阶为1
            for(int i = 2; i < n+1; i++) {
                nums[i] = (nums[i - 1] + nums[i - 2]) % 1000000007;
            }
            return nums[n];
        }

    }

}
