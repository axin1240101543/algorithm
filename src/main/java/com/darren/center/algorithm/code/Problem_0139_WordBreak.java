package com.darren.center.algorithm.code;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Darren
 * Date: 2021-11-15 09:40:59
 * Version: 1.0
 * Description:
 * 139、根据字典中的单词去划分给定字符串，能true，否false。
 * 思路：根据前缀划分
 * 例如：字典表[a、aa、aab、b]
 * 字符串：aaaabaab
 * 1）a + (aaabaab) -> 甲种方法
 * 2）aa + (aabaab) -> 乙种方法
 * 3）aaa ✖
 * 升级版 能够被分解的方法数，当方法数为0则false，否则true。
 * 暴力递归 -> 动态规划（O（N^3）） -> 前缀树优化（O（N^2））
 * 为什么是O（N^3）？
 * 因为当使用hash表存储时，当样本量比较大，达到一定的规模，例如一百万长的字符串，这么就需要遍历字符串，然后求出hashcode，才能在hash表中查找，此时的
 * 时间复杂度不再是O（1），而是O（N）。
 * 前缀树：
 * 如果前缀树（根据字典表构建）后面没路了，给定字符串后面的剩余字符就不用再尝试了。
 */
public class Problem_0139_WordBreak {

    public static void main(String[] args) {
        Solution solution = new Problem_0139_WordBreak().new Solution();
        String s = "aaaabaab";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("ac");
        wordDict.add("aa");
        wordDict.add("aab");
        wordDict.add("b");
        boolean res = solution.wordBreak3(s, wordDict);
        System.out.println(res);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 暴力递归
         * @param s
         * @param wordDict
         * @return
         */
        public boolean wordBreak(String s, List<String> wordDict) {
            int ans = process(s, 0, wordDict);
            return ans != 0;
        }

        //s[0 ... index - 1] 已经分解过了
        //s[index ...] 这一段的方法数返回
        public int process(String s, int index, List<String> wordDict){
            if (index == s.length()){
                return 1;
            }
            int ways = 0;
            //index ... index
            //index ... index + 1
            //index ... index + 2
            //index ... end
            for(int end = index; end < s.length(); end++){
                //s[index ... end]
                String pre = s.substring(index, end + 1);
                if (wordDict.contains(pre)){
                    ways += process(s, end + 1, wordDict);
                }
            }
            return ways;
        }


        /**
         * 动态规划
         * 时间复杂度为O（N^3）
         * @param s
         * @param wordDict
         * @return
         */
        public boolean wordBreak2(String s, List<String> wordDict) {
            int n = s.length();
            int[] dp = new int[n + 1];
            dp[n] = 1;
            for (int index = n - 1; index >= 0; index--) {
                int ways = 0;
                for(int end = index; end < s.length(); end++){
                    //s[index ... end]
                    String pre = s.substring(index, end + 1);
                    if (wordDict.contains(pre)){
                        ways += dp[end + 1];
                    }
                }
                dp[index] = ways;
            }

            return dp[0] != 0;
        }

        public class Node{
            public boolean end;
            public Node[] nexts;

            public Node() {
                this.end = false;
                //从头节点出发准备26条路，因为每个字符串都可能以26个字母的任意一个开始
                this.nexts = new Node[26];
            }
        }

        /**
         * 前缀树优化，用前缀树代替hash表来查找这个划分的新字符串是否在字典表中
         * 时间复杂度为O（N^2）
         * String pre = s.substring(index, end + 1);
         * if (wordDict.contains(pre)){
         *      ways += dp[end + 1];
         * }
         * @param s
         * @param wordDict
         * @return
         */
        public boolean wordBreak3(String s, List<String> wordDict) {
            Node root = new Node();
            //字典表[ac、aa、aab、b]
            //1）初始化root节点，即根节点，准备好从root开发的26条路
            //2）字典中的每个字符串都从root节点出发
            //3）遍历每个字符串 拿到每个字符 然后每个字符的ASCII减去'a'字符的ASCII就是数组下标
            // 'a' - 'a' = 0
            // 'c' - 'a' = 2
            // ...
            //4）node不断的跳到下一个节点，然后将每个节点串起来，node里面是node，最后的位置end变为true，表示字符串已经结束
            //例如：
            //ac这个字符串，a这个字符的下标是0，路如果存在那么复用，不存在创建，然后node来到0这个下标，0这个下标的next node为null
            //c字符字符的下标是2,路如果存在那么复用，不存在创建，然后node来到2这个下标，2这个下标的next node为null
            for (String str : wordDict) {
                char[] chars = str.toCharArray();
                Node node = root;
                int index = 0;
                for (int i = 0; i < chars.length; i++) {
                    index = chars[i] - 'a';
                    //如果不存在，那么创建新的路，如果存在就复用，例如ac，aa这两个字符串，aa可以复用ac中的a的路
                    if (node.nexts[index] == null){
                        node.nexts[index] = new Node();
                    }
                    node = node.nexts[index];
                }
                node.end = true;
            }

            char[] chars = s.toCharArray();
            int n = s.length();
            int[] dp = new int[n + 1];
            dp[n] = 1;
            for (int index = n - 1; index >= 0; index--) {
//                int ways = 0;
                Node cur = root;
                //index ... index
                //index ... index + 1
                //index ... index + 2
                //index ... end
                for(int end = index; end < s.length(); end++){
                    //s[index ... end]
                    /*String pre = s.substring(index, end + 1);
                    if (wordDict.contains(pre)){
                        ways += dp[end + 1];
                    }*/
                    //通过每个字符去前缀树中查找节点
                    cur = cur.nexts[chars[end] - 'a'];
                    //如果这条路不存在，那么就直接返回，因为后面的字符不需要尝试了
                    if (cur == null){
                        break;
                    }
                    //判断这个前缀是否存在  既然它这个end是true 说明以这个字符结尾的字符串肯定是存在的
                    if (cur.end){
                        dp[index] += dp[end + 1];
                    }
                }
//                dp[index] = ways;
            }
            return dp[0] != 0;
        }
    }

}
