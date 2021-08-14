package com.darren.center.algorithm.code;

import java.util.HashMap;
import java.util.Map;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2021年08月14日 10:39:06
 **/
public class Problem_003_LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        Solution solution = new Problem_003_LongestSubstringWithoutRepeatingCharacters().new Solution();
        int result = solution.lengthOfLongestSubstring("abcabcbb");
        System.out.println(result);
    }
    class Solution {

        public int lengthOfLongestSubstring2(String s) {
            if(s == null || s.equals("")){
                return 0;
            }
            char[] str = s.toCharArray();
            Map<Character, Integer> map = new HashMap<>();
            //表示当前字符往前推 推不动的位置  初始值为-1  0位置推不动的位置为-1
            int start = -1;
            //当前字符的位置
            int end = 0;
            //结果值
            int result = 0;
            for(int i =0; i < str.length; i ++){
                end = i;
                //如果当前字符在之前出现过，那么就更新start，否则不更新
                //为什么map需要判断是否存在，因为不存在取出来的值为null，比较不了大小
                if(map.containsKey(str[i])){
                    //当前值出现的下标和推不动的位置比较
                    //1、如果比推不动的位置小，则取推不动的位置
                    //2、如果比推不动的位置大，则取当前值曾经出现的位置
                    start = Math.max(start, map.get(str[i]));
                }
                //计算当前字符和推不动的位置的距离
                int curChar = end - start;
                //比较当前结果和当前位置往前推的距离的大小
                result = Math.max(result, curChar);
                //将当前位置存入map中
                map.put(str[i], end);
            }
            return result;
        }

        public int lengthOfLongestSubstring(String s) {
            if(s == null || s.equals("")){
                return 0;
            }
            //key 表示字符   value 表示 这个字符出现的位置
            int[] map = new int[256];
            for(int i = 0; i < map.length; i++){
                map[i] = -1;
            }
            //表示当前字符往前推 推不动的位置  初始值为-1  0位置推不动的位置为-1
            int pre = -1;
            //结果值
            int result = 0;
            char[] str = s.toCharArray();
            for(int i = 0; i < str.length; i++){
                //判断当前值是否在map中出现过，出现过就取出下标
                int preIndex = map[str[i]];
                //当前值出现的下标和推不动的位置比较
                //1、如果比推不动的位置小，则取推不动的位置
                //2、如果比推不动的位置大，则取当前值曾经出现的位置
                pre = Math.max(pre, preIndex);
                //比较当前结果和当前位置往前推的距离的大小
                result = Math.max(result, i - pre);
                //将当前位置存入map中
                map[str[i]] = i;
            }
            return result;
        }
    }

}

