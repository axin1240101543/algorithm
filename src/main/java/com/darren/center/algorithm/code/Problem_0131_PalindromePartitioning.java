package com.darren.center.algorithm.code;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: Darren
 * Date: 2021-10-25 14:03:13
 * Version: 1.0
 * Description:
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 *  回文串 是正着读和反着读都一样的字符串。
 *
 *  示例 1：
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 *
 *  示例 2：
 * 输入：s = "a"
 * 输出：[["a"]]
 */
public class Problem_0131_PalindromePartitioning {

    public static void main(String[] args) {
        Solution solution = new Problem_0131_PalindromePartitioning().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         *
         * @param s
         * @return
         *
         * 思路：
         * 1、每个字符都去拆分
         * 假如字符串为aab
         * 1）拆分为a，并判断a是否是回文，a后面的字符串自己去拆分
         * 后面的ab拆分出来，只能是a单独一个字符，b单独一个字符，然后加上前面的拆分方案，[a,a,b]
         * 2）拆分为aa，并判断ab是否是回文，aa后面的字符串自己去拆分
         * 后面的b只有一个字符，那就加上前面的拆分方案，[aa,b]
         * 总答案为：[a,a,b]、[aa,b]
         *
         * 优化：
         * 判断每个字符串是否为回文，使用动态规划
         * 准备一张二维表
         * 竖为l下标 横为r下标
         * 1）l > r部分无效，也就是左下部分无效
         * 2）右斜对角线 也就是只有一个字符的情况 肯定是回文 填写true
         * 3）右斜第一条对角线 也就是两个字符的情况 判断两个字符是否相等 相等填写true 不相等填写false
         * 4）剩下的所有格子 从最下面的格子 从左往右 依次往上填写
         * 需满足 str[l] == str[r] && str[l+1][r-1] 条件那么填写true 即两端的字符是否相等 然后中间这段是否是回文 依赖[l+1][r-1]位置的答案，也就是每个位置右下角位置的这个格子
         */
        public List<List<String>> partition(String s) {
            // dp[L][R] -> 是不是回文
            boolean[][] dp = getDp(s.toCharArray());
            LinkedList<String> path = new LinkedList<>();
            List<List<String>> ans = new ArrayList<>();
            process(s, 0, dp, path, ans);
            return ans;
        }

        // s 字符串
        // s[0...index-1] 已经做过的决定，放入了path中
        // 在index开始做属于这个位置的决定，
        // index == s.len  path之前做的决定（一种分割方法），放进总答案ans里
        public void process(String s, int index, boolean[][] dp, LinkedList<String> path, List<List<String>> ans){
            if (index == s.length()){
                ans.add(copy(path));
            }else{
                for (int end = index; end < s.length(); end++) {
                    // index..index
                    // index..index+1
                    // index..index+2
                    // ... ...
                    // index..end
                    if (dp[index][end]){
                        path.add(s.substring(index, end + 1));
                        process(s, end + 1, dp, path, ans);
                        path.pollLast();
                    }
                }
            }
        }

        public List<String> copy(LinkedList<String> path){
            List<String> list = new ArrayList<>();
            for (String s : path) {
                list.add(s);
            }
            return list;
        }

        public boolean[][] getDp(char[] str){
            int n = str.length;
            boolean[][] dp = new boolean[n][n];
            for (int i = 0; i < n - 1; i++) {
                dp[i][i] = true;
                //0,1 1,2 2,3 3,4 4,4?
                dp[i][i + 1] = str[i] == str[i + 1];
            }
            dp[n - 1][n - 1] = true;
            //右半部分 l < r
            //row = 0 col = 2
            //   0 1 2 3 4
            // 0 T . ? ? ? 0,2 0,3 0,4
            // 1 * T . ? ? 1,3 1,4
            // 2 * * T . ? 2,4
            // 3 * * * T .
            // 4 * * * * T

            //0,2 1,3 2,4
            //0,3 1,4
            //0,4
            //斜右对角线一次填好 不是从最下一行 从左往右 然后依次往上填写
            for (int j = 2; j < n; j++){
                int row = 0;
                int col = j;
                while (row < n && col < n){
                    if (str[row] == str[col] && dp[row + 1][col - 1]){
                        dp[row][col] = true;
                    }
                    row++;
                    col++;
                }
            }
            return dp;
        }
    }

}
