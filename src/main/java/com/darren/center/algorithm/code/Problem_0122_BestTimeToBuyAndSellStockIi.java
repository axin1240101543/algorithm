package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-10-11 10:34:56
 * Version: 1.0
 * Description:
 * //给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
 * // 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * // 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
public class Problem_0122_BestTimeToBuyAndSellStockIi {

    public static void main(String[] args) {
        Solution solution = new Problem_0122_BestTimeToBuyAndSellStockIi().new Solution();
    }

    class Solution {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0){
                return 0;
            }

            int ans = 0;
            for (int i = 1; i < prices.length; i++) {
                //上坡就加起来 否则就加0
                ans += Math.max(0, prices[i] - prices[i - 1]);
            }
            return ans;
        }
    }

}
