package com.darren.center.algorithm.code;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Author: Darren
 * Date: 2021-10-19 14:11:33
 * Version: 1.0
 * Description:
 * 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列：
 *  序列中第一个单词是 beginWord 。
 *  序列中最后一个单词是 endWord 。
 *  每次转换只能改变一个字母。
 *  转换过程中的中间单词必须是字典 wordList 中的单词。
 *
 *  给你两个单词 beginWord 和 endWord 和一个字典 wordList ，找到从 beginWord 到 endWord 的 最短转换序列 中
 * 的 单词数目 。如果不存在这样的转换序列，返回 0。
 *
 *  示例 1：
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot",
 * "log","cog"]
 * 输出：5
 * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
 *
 *  示例 2：
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot",
 * "log"]
 * 输出：0
 * 解释：endWord "cog" 不在字典中，所以无法进行转换。
 */
public class Problem_0127_WordLadder {

    public static void main(String[] args) {
        Solution solution = new Problem_0127_WordLadder().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //宽度优先遍历，开始的这个单词的每个字符能到哪些单词？
        //思路：假如开始的这个单词是abc，下一层的单词跟abc只有一个字符不一样，那么他就是下一层的单词，然后下一层的单词再去推下一层跟他只有一个字符不一样的单词，直到出现结束单词，那么层数就是最短距离。
        //常规思路：把N长度的列表中每个单词遍历一遍，判断每个单词是不是和abc只差一个
        //时间复杂度：O（N）* k（单词长度）
        //时间复杂度优化：
        //1、把字典全部放入hashset
        //2、把abc进行分解，每次改变一个字符都判断是否在hashset里面
        //例如：abc，只改变a第一个字符，从b到z，跳过a，下一次值改变b，从a到z，跳过b
        //如果单词长度为4，每个字符查询25次（26个字符，减去本身），那么就是4*25，即O（k），25是常数项，hashset查询常规来说时间复杂度是O（1），
        //但是样本的规模不可忽略的时候，也就是当样本为一个长度为k的string去hashcode查询的时候，去计算string的hashcode，需要遍历string，然后再去查找，时间复杂度为O（k），所以整体的时间复杂度为O（k^2）。
        //此时就是O（N）* k和O（k^2）的比较，单词不长则O（k^2）比较好，否则O（N）* k比较好。
        //3、贪心优化：从start走到end和end走到start是一样的，所以判断当前层的单词数量如果少，那么更换start和end的位置，永远小追大。
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            Set<String> wordLists = new HashSet<>(wordList);
            if (!wordLists.contains(endWord)){
                return 0;
            }

            Set<String> begin = new HashSet();
            Set<String> end = new HashSet();
            begin.add(beginWord);
            end.add(endWord);

            //宽度优先遍历
            Set access = new HashSet();

            for(int len = 2; !begin.isEmpty(); len++){
                //下一层
                Set<String> next = new HashSet();
                for (String w : begin) {
                    //遍历当前单词的每个字符
                    for (int j = 0; j < w.length(); j++){
                        //每个字符开始变换 从a - z，除了它本身
                        char[] chars = w.toCharArray();
                        for (char c = 'a'; c <= 'z'; c++){
                            //除了它本身
                            if (w.charAt(j) != c){
                                //将当前位置j变换为c
                                chars[j] = c;
                                String newNext = String.valueOf(chars);
                                //如果end列表中包含了这个新的单词，那么说明找到了，因为一开始我们就添加了endWord这个单词
                                if (end.contains(newNext)){
                                    return len;
                                }

                                //不重复添加相同的单词，因为abc -> cbc -> abc
                                //字典中必须包含这个新单词
                                if (!access.contains(newNext) && wordLists.contains(newNext)){
                                    next.add(newNext);
                                    access.add(newNext);
                                }
                            }
                        }
                    }
                }
                //贪心：永远小追大
                begin = (next.size() < end.size()) ? next : end;
                //如果begin等于next 说明从next比end小 begin不用变 否则必end大  end变成next  start变成end
                end = (begin == next) ? end : next;
            }
            return 0;
        }
    }

}
