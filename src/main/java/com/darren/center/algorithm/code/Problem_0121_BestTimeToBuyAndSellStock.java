package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-09-22 11:34:12
 * Version: 1.0
 * Description:
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 */
public class Problem_0121_BestTimeToBuyAndSellStock {

    public static void main(String[] args) {
        Solution solution = new Problem_0121_BestTimeToBuyAndSellStock().new Solution();
        int[] prices = {7,1,5,3,6,4};
        int res = solution.maxProfit(prices);
        System.out.println(res);
    }


    //输入：[7,1,5,3,6,4]
    //输出：5
    //解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
    //注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
    class Solution {
        //每次以i位置去结算，0 ~ i-1位置上的最小值，i位置的值减去最小值，就是此时i位置的最大利润，每个位置都求。
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0){
                return 0;
            }
            int min = prices[0];
            int res = 0;
            for (int i = 1; i < prices.length; i++) {
                min = Math.min(min, prices[i - 1]);
                res = Math.max(res, prices[i] - min);
            }
            return res;
        }
    }
}
