package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-10-11 15:31:14
 * Version: 1.0
 * Description:
 * //给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
 * // 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * // 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
public class Problem_0188_BestTimeToBuyAndSellStockIv {

    public static void main(String[] args) {
        Solution solution = new Problem_0188_BestTimeToBuyAndSellStockIv().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 斜率优化
         * @param k
         * @param prices
         * @return
         */
        public int maxProfit(int k, int[] prices) {
            if (prices == null || prices.length == 0){
                return 0;
            }

            int n = prices.length;
            //过滤：如果k大于N/2，因为上坡的数量不可能超过N/2，那么就是无限次交易 和股票问题2一样
            if (k >= n/2){
                return maxProfit(prices);
            }

            //动态规划 准备一张表 i为位置  j为交易次数  大小为dp[n][k+1]
            //第一行和第一列都是0  没有必要填写
            int[][] dp = new int[k + 1][n];
            //dp[3][1] 3位置  1交易
            //1)3位置不参与交易
            //dp[2][1]
            //2)3位置参与交易
            //dp[3][0] - [3] + [3]
            //dp[2][0] - [2] + [3]
            //dp[1][0] - [1] + [3]
            //dp[0][0] - [0] + [3]
            //把[3]提出来

            //dp[4][1] 4位置  1交易
            //1)4位置不参与交易
            //dp[3][1]
            //2)4位置参与交易
            //dp[4][0] - [4] + [4]
            //dp[3][0] - [3] + [4]
            //dp[2][0] - [2] + [4]
            //dp[1][0] - [1] + [4]
            //dp[0][0] - [0] + [4]
            //把[4]提出来

            //发现line
            //dp[3][0] - [3]
            //dp[2][0] - [2]
            //dp[1][0] - [1]
            //dp[0][0] - [0]
            //可以优化
            int ans = 0;
            for (int j = 1; j <= k; j++) {
                //j 交易 0位置
                int pre = dp[j][0];
                //重复部分
                //减去每个位置单独的那个项 即0位置买入 0位置卖出 剩下的就是重复部分 可以优化的部分
                int best = pre - prices[0];
                for (int i = 1; i < n; i++) {
                    //dp[3][1] 3位置  1交易
                    //2)3位置参与交易
                    //dp[3][0] - [3] + [3]
                    //dp[2][0] - [2] + [3]
                    //dp[1][0] - [1] + [3]
                    //dp[0][0] - [0] + [3]
                    //把[3]提出来
                    //所以j-1
                    pre = dp[j - 1][i];

                    //dp[4][1] 4位置  1交易
                    //1)4位置不参与交易
                    //dp[3][1]
                    //2)4位置参与交易
                    //dp[4][0] - [4] + [4]
                    //dp[3][0] - [3] + [4]
                    //dp[2][0] - [2] + [4]
                    //dp[1][0] - [1] + [4]
                    //dp[0][0] - [0] + [4]
                    //把[4]提出来
                    dp[j][i] = Math.max(dp[j][i-1], best + prices[i]);
                    //计算最新的重复部分
                    //dp[3][0] - [3]
                    //dp[2][0] - [2]
                    //dp[1][0] - [1]
                    //dp[0][0] - [0]
                    //把[3]提出来
                    best = Math.max(best, pre - prices[i]);
                    ans = Math.max(ans, dp[j][i]);
                }
            }
            return ans;
        }

        //思路 动态规划
        //不能超过k次交易，不一定做满k次交易
        //过滤：如果k大于N/2，因为上坡的数量不可能超过N/2，那么就是无限次交易 和股票问题2一样
        //可能性1：i位置不参与交易
        //可能性2：i位置参与最后一次交易卖出时机（贪心得出）
        //两个贪心：
        //1）如果i位置要参与交易，除了最后一次交易外，其他交易不考虑，因为最后一次交易只能在i位置买，在i位置卖，i位置及其往后位置获得收益为0；
        //2）i位置不需要参与买入时机，参加最后一次交易只需要考虑卖出时机，因为参与买入时机，那么卖出时机也是i位置，获得收益为0。
        //可能性1）：最后一次买入时机为i位置 0 ~ i-1（k - 1次交易）-> k - 1  + [i] - [i]
        //可能性2）：最后一次买入事件为i-1位置 0 ~ i-1（k - 1次交易）  -> k - 1 + [i] - [i-1]
        //可能性3）：最后一次买入事件为i-2位置 0 ~ i-1（k - 1次交易）  -> k - 1 + [i] - [i-2]
        //......
        //所有的枚举
        public int maxProfit1(int k, int[] prices) {
            if (prices == null || prices.length == 0){
                return 0;
            }

            int n = prices.length;
            //过滤：如果k大于N/2，因为上坡的数量不可能超过N/2，那么就是无限次交易 和股票问题2一样
            if (k >= n/2){
                return maxProfit(prices);
            }

            //动态规划 准备一张表 i为位置  j为交易次数  大小为dp[n][k+1]
            //第一行和第一列都是0  没有必要填写
            int[][] dp = new int[n][k + 1];
            for (int i = 1; i < n; i++) {
                for (int j = 1; j <= k; j++) {
                    //i位置不参与交易
                    dp[i][j] = dp[i-1][j];
                    //i位置参与交易
                    //第k次交易的买入时机为p  枚举
                    for(int p = i; p >= 0; p--){
                        //可能性1）：最后一次买入时机为p位置 0 ~ i-1（j - 1次交易）-> j - 1  + [i] - [i]
                        //可能性2）：最后一次买入事件为p-1位置 0 ~ i-1（j - 1次交易）  -> j - 1 + [i] - [i-1]
                        //可能性3）：最后一次买入事件为p-2位置 0 ~ i-1（j - 1次交易）  -> j - 1 + [i] - [i-2]
                        dp[i][j] = Math.max(dp[i][j], dp[p][j - 1] - prices[p] + prices[i]);
                    }
                }
            }
            return dp[n - 1][k];
        }

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
