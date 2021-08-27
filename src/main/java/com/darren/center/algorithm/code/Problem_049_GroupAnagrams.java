package com.darren.center.algorithm.code;

import java.util.*;

/**
 * Author: Darren
 * Date: 2021-08-27 11:32:38
 * Version: 1.0
 * Description:
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母都恰好只用一次。
 *
 *  示例 1:
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 */
public class Problem_049_GroupAnagrams {
    public static void main(String[] args) {
        Solution solution = new Problem_049_GroupAnagrams().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //种类一样、且只出现一次
        //对每个字符串排序，然后添加到容器中，如果是容器中没有的，新建一个子容器，否则添加到同一个子容器中
        //Map<String, List<String>>
        public List<List<String>> groupAnagrams(String[] strs) {
            List<List<String>> ans = new ArrayList<>();
            if (strs == null || strs.length == 0){
                return ans;
            }

            Map<String, List<String>> map = new HashMap<>();
            for (String str : strs) {
                char[] chars = str.toCharArray();
                Arrays.sort(chars);
                String key = String.valueOf(chars);
                if (!map.containsKey(key)){
                    map.put(key, new ArrayList<>());
                }
                map.get(key).add(str);
            }

            for (List<String> str: map.values()){
                ans.add(str);
            }
            return ans;
        }
    }
}
