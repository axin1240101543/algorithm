package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-10-11 11:26:33
 * Version: 1.0
 * Description:
 * //给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * // 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * // 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
public class Problem_0123_BestTimeToBuyAndSellStockIii {

    public static void main(String[] args) {
        Solution solution = new Problem_0123_BestTimeToBuyAndSellStockIii().new Solution();
    }

    class Solution {
        //思路：需要在i位置做第二次交易，需要什么信息？
        //i位置两次交易做完，i位置是第二次卖出的最佳时机。
        //信息：得到0 ~ i-1范围上 第一次交易后最佳的买入时机，（第一次交易收益 - 第二次买入） + i位置的值（提前减去成本）
        //i位置上的可能性：1）第二次买入的位置不是i  2）第二次买入的位置是i
        public int maxProfit(int[] prices) {
            int min = prices[0];
            int firstAns = 0;
            int firstAnsMinusCost = -prices[0];
            int ans = 0;
            for (int i = 0; i < prices.length; i++) {
                //计算以i位置卖出的最大收益
                ans = Math.max(ans, firstAnsMinusCost + prices[i]);

                //更新第一次买的最大收益
                min = Math.min(min, prices[i]);
                firstAns = Math.max(firstAns, prices[i] - min);

                //第二次买入的位置不是i 那么firstAnsMinusCost就是他自己
                //第二次买入的是位置是i 那么需要减去i位置的成本
                firstAnsMinusCost = Math.max(firstAnsMinusCost, firstAns - prices[i]);
            }
            return ans;
        }
    }

}
