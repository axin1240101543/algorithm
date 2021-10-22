package com.darren.center.algorithm.code;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Darren
 * Date: 2021-10-20 09:57:56
 * Version: 1.0
 * Description:
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *  请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 *  示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 *  示例 2：
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 */
public class Problem_0128_LongestConsecutiveSequence {

    public static void main(String[] args) {
        Solution solution = new Problem_0128_LongestConsecutiveSequence().new Solution();
//        int[] nums = {100,1,200,3,4,0,2};
//        int[] nums = {100,4,200,1,3,2};
        int[] nums = {0,3,7,2,5,8,4,6,0,1};
        int i = solution.longestConsecutive1(nums);
        System.out.println(i);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 一张表实现
         * @param nums
         * @return
         * 准备一张hash表，key为当前数字，value为这个数字的长度
         * 当来到这个数字时，看这个数字前一个数的长度为多少，如果存在，那么拿到这个长度preLen；
         * 同理，拿到这个数字后一个数的长度为多少，如果存在，那么拿到这个长度posLen；
         * 然后加上这个数本身的长度，那么就得到了此时的最大长度preLen + 1 + posLen，此时抓一下答案。
         * 最后去更新当前数字往前走的那个数字的长度和当前数字往后走的那个数字的长度。
         */
        public int longestConsecutive(int[] nums) {
            if (nums == null || nums.length == 0){
                return 0;
            }
            if (nums.length == 1){
                return 1;
            }
            //key 这个数字  value 这个数字的长度
            Map<Integer, Integer> map = new HashMap<>();
            int len = 0;
            for (int num : nums) {
                //避免重复的num参与计算
                if (!map.containsKey(num)){
                    map.put(num, 1);
                    //x + ...
                    //x后面的长度
                    int posLen = map.get(num + 1) == null ? 0 : map.get(num + 1);
                    //... + x
                    //x前面的长度
                    int preLen = map.get(num - 1) == null ? 0 : map.get(num - 1);
                    //不管前面的长度和后面的长度有没有 加起来
                    int all = preLen + 1 + posLen;
                    //只更新两端的数据 不管中间的数据是否正确
                    //更新头的位置的长度
                    map.put(num - preLen, all);
                    //更新尾的位置的长度
                    map.put(num + posLen, all);
                    //抓一下最大的长度
                    len = Math.max(len, all);
                }
            }
            return len;
        }

        /**
         * 两张表实现
         * @param nums
         * @return
         * 流程（当前数 -> 头  或者  尾 + 当前数）：
         * 例如：100 1 200 3 4 0 2
         * 100 ：head 100、1  tail 100、1
         * head中查询有没有100+1 101开头的数 没有 跳过
         * tail中查询有没有100-1 99结尾的数 没有 跳过
         *
         * head
         * 100,1
         *
         * tail
         * 100,1
         *
         * 1 ：head 1、1 tail 1、1
         * head中查询有没有1+1 1开头的数 没有 跳过
         * tail中查询有没有1-1 0结尾的数 没有 跳过
         *
         * head
         * 100,1
         * 1,1
         *
         * tail
         * 100,1
         * 1,1
         *
         * 200 : head 200、1 tail 200、1
         * head中查询有没有200+1 201开头的数 没有 跳过
         * tail中查询有没有200-1 199结尾的数 没有 跳过
         *
         * head
         * 100,1
         * 1,1
         * 200,1
         *
         * tail
         * 100,1
         * 1,1
         * 200,1
         *
         * 3 : head 3、1 tail 3、1
         * head中查询有没有3+1 4开头的数 没有 跳过
         * tail中查询有没有3-1 2结尾的数 没有 跳过
         *
         * head
         * 100,1
         * 1,1
         * 200,1
         * 3,1
         *
         * tail
         * 100,1
         * 1,1
         * 200,1
         * 3,1
         *
         * 4 ：head 4、1 tail 4、1
         * head中查询有没有4+1 5开头的 没有 跳过
         * tail中查询有没有4-1 3结尾的 有 合并
         * 3、1 4、1 合并
         * tail中删除以3结尾的记录3、1
         * head中删除以4开头的记录4、1
         * 修改head中3的记录为3、2 -> 3-4
         * 修改tail中4的记录为4、2 -> 3-4
         *
         * head
         * 100,1
         * 1,1
         * 200,1
         * 3,1 -> 合并3、2
         * 4,1 删除
         *
         * tail
         * 100,1
         * 1,1
         * 200,1
         * 3,1 (删除)
         * 4,1 -> 合并4、2
         *
         * 0 ：head 0 1 tail 0 1
         * head中查询有没有0+1 1开头的 有 合并
         * tail中查询有没有0-1 -1结尾的 没有 跳过
         * 0、1 1、1 合并
         * tail中删除以0结尾的记录0，1
         * head中删除以1开头的记录1，1
         * 修改head中0的记录为0、2 -> 0-1
         * 修改tail中1的记录为1、2 -> 0-1
         *
         * head
         * 100,1
         * 1,1 (删除)
         * 200,1
         * 3,2
         * 0,1 -> 合并0,2
         *
         * tail
         * 100,1
         * 1,1 -> 合并1,2
         * 200,1
         * 4,2
         * 0,1 删除
         *
         * 2 ：head 2 1 tail 2 1
         * head中查询有没有2+1 3开头的 有 合并
         * tail中查询有没有2-1 1结尾的 有 合并
         * 2、1 3、2 合并
         * tail中删除2结尾的记录
         * head中删除3开头的记录
         * 修改head中2的记录为2、3 -> 2-4
         * 修改tail中4的记录为4、3 -> 2-4
         *
         * head
         * 100,1
         * 200,1
         * 3,2 (删除)
         * 0,2
         * 2,1 -> 合并 2，3
         *
         * tail
         * 100,1
         * 1,2
         * 200,1
         * 4,2 -> 合并 4、3
         * 2,1 删除
         *
         * 1、2 2、3
         * tail中删除1结尾的数
         * head中删除2开头的数
         * 修改head中0的记录为0、5 -> 0 - 4
         * 修改tail中4的记录为4、5 -> 0 - 4
         *
         * head
         * 100,1
         * 200,1
         * 0,2 -> 合并 0,5
         * 2,3 删除
         *
         * tail
         * 100,1
         * 1,2 (删除)
         * 200,1
         * 4,3 -> 合并 4,5
         */
        public int longestConsecutive1(int[] nums) {
            if (nums == null || nums.length == 0){
                return 0;
            }

            if (nums.length == 1){
                return 1;
            }

            //头的连续区间的大小  尾的连续区间大小
            //key 表示这个数字 value 表示个数
            HashMap<Integer, Integer> head = new HashMap<>();
            HashMap<Integer, Integer> tail = new HashMap<>();
            int len = 0;
            for (int num:nums) {
                if (!head.containsKey(num) && !tail.containsKey(num)){
                    head.put(num, 1);
                    tail.put(num, 1);
                    int posLen = head.get(num + 1) == null ? 0 : head.get(num + 1);
                    int preLen = tail.get(num - 1) == null ? 0 : tail.get(num - 1);
                    if (posLen != 0 && preLen != 0){
                        len = Math.max(len, preLen + 1 + posLen);
                        head.put(num - posLen, len);
                        head.remove(num);

                        tail.put(num + posLen, len);
                        tail.remove(num);
                        continue;
                    }
                    if (posLen != 0){
                        len = Math.max(len, posLen + 1);
                        head.put(num, posLen + 1);
                        head.remove(num + 1);

                        tail.put(num + posLen, posLen + 1);
                        tail.remove(num);
                        continue;
                    }
                    if (preLen != 0){
                        len = Math.max(len, preLen + 1);
                        tail.put(num, preLen + 1);
                        tail.remove(num - 1);

                        head.put(num - preLen, preLen + 1);
                        head.remove(num);
                        continue;
                    }
                }
            }
            return len;
        }
    }

}
