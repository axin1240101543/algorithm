package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-09-23 15:40:31
 * Version: 1.0
 * Description:
 */
public class Problem_076_MinimumWindowSubstring {

    public static void main(String[] args) {
        Solution solution = new Problem_076_MinimumWindowSubstring().new Solution();
        String res = solution.minWindow("ADOBECODEBANC", "ABC");
        System.out.println(res);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String minWindow(String s, String t) {
            char[] str = s.toCharArray();
            char[] target = t.toCharArray();

            //准备一张欠债表
            int[] map = new int[256];
            for (char c : target) {
                map[c]++;
            }

            //欠债的字符数
            int all = target.length;
            int l = 0;
            int r = 0;

            int min = -1;
            int ll = -1;
            int rr = -1;
            while (r < str.length){
                //不管是欠债的还是不欠债的都--
                //不欠债的都变成了负数
                //欠债的都会变成0
                map[str[r]]--;

                //如果当前字符在欠债表中大于等于0 说明这个目标字符在欠债
                if (map[str[r]] >= 0){
                    all--;
                }

                //不欠债了
                if (all == 0){
                    //l来到左边第一个有效位置 大于等于0的字符才是目标字符
                    while (map[str[l]] < 0){
                        map[str[l]]++;
                        l++;
                    }
                    //抓取答案
                    if (min == -1 || min > (r - l)){
                        min = r - l + 1;
                        ll = l;
                        rr = r;
                    }
                    //l向右移
                    map[str[l]]++;
                    l++;
                    //又开始欠债
                    all++;
                }
                r++;
            }
            return min == -1 ? "" : s.substring(ll, rr + 1);
        }
    }

}
