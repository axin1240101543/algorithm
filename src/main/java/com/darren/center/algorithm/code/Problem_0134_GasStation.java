package com.darren.center.algorithm.code;

import java.util.LinkedList;

/**
 * Author: Darren
 * Date: 2021-10-25 16:12:41
 * Version: 1.0
 * Description:
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 *
 * 说明:
 * 如果题目有解，该答案即为唯一答案。
 * 输入数组均为非空数组，且长度相同。
 * 输入数组中的元素均为非负数。
 *
 * 示例 1:
 *
 * 输入:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 *         0 1 2 3 4
 *
 * 输出: 3
 *
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 *
 * 示例 2:
 * 输入:
 * gas  = [2,3,4]
 * cost = [3,4,3]
 *
 * 输出: -1
 *
 * 解释:
 * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 * 因此，无论怎样，你都不可能绕环路行驶一周。
 */
public class Problem_0134_GasStation {

    public static void main(String[] args) {
        Solution solution = new Problem_0134_GasStation().new Solution();
        int[] gas = {9,2,1,4,2,6};
        int[] cost = {3,3,3,0,4,10};
        int res = solution.canCompleteCircuit(gas, cost);
        System.out.println(res);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         *
         * @param gas
         * @param cost
         * @return
         * gas  = [3,2,1,4]
         * cost = [2,2,2,2]
         *         0 1 2 3
         * 思路：
         * 加油站所拥有的油 减去 去下一个加油站的距离 得到剩余多少油的一个数组，即纯能值数组
         * arr =  [1,0,-1,2]
         *         0 1  2 3
         * 从每个位置转一圈，回到这个位置的累加值大于等于0，那么这个点就是一个有效点
         * 例如：
         * 0这个位置，1 + 0 + -1 + 2 > 0,是一个有效点
         * 1这个位置，0 + -1 < 0, 不是一个有效点
         * 2这个位置，-1 < 0,不是一个有效点
         * 3这个位置，2 + 1 + 0 + -1 > 0，是一个有效点
         * 时间复杂度为O（N^2）
         *
         * gas = [9,2,1,4,2,6]
         * cost = [3,3,3,0,4,10]
         * 纯能值数组：[6,-1,-2,4,-2,-4]
         *            0  1  2 3  4  5
         * 加工一个一倍累加和数组
         * arr1 = [6,5,3,7,5,1]
         * 加工一个两倍累加和数组
         * arr2 = [6,5,3,7,5,1,7,6,4,8,6,2]
         *         0 1 2 3 4 5 6 7 8 9 10 11
         *
         * 0是否是有效点的范围是0 ~ 5 [6,5,3,7,5,1]，最小值 >= 0 那么就是有效点
         * 1是否是有效点的范围是1 ~ 6 [5,3,7,5,1,7] - 前面一个数6，得到[-1,-3,1,-1,-5,1] == 1位置的累加和[-1,-3,1,-1,-5,1]，最小值 < 0， 那么不是有效点
         * 2是否是有效点的范围是2 ~ 7 [3,7,5,1,7,6] - 前面一个数5, 得到[-2,2,0,-4,2,1] == 2位置的累加和[-2,2,0,-4,2,1]，最小值 < 0，那么不是有效点
         *
         * 注：
         * 窗口最小值的计算（进阶第一节）
         * 准备一个双向链表 保证值从小到大
         * 1 2 3 4 5
         * 0 1 2 3 4
         * l = 0 r = 0
         * r可以不断向右移动，窗口扩大，然后这个数从尾巴进 进的时候 将队列中大于等当前数的数全部从尾部弹出
         * 当l向右移动时，窗口缩小，l不能超过r
         * 链表从左侧弹出的第一个值就是当前窗口最小值
         * 时间复杂度为O（N）
         */
        public int canCompleteCircuit(int[] gas, int[] cost) {
            boolean[] good = goodArray(gas, cost);
            for (int i = 0; i < gas.length; i++) {
                if (good[i]){
                    return i;
                }
            }
            return -1;
        }

        public boolean[] goodArray(int[] g, int[] c){
            int n = g.length;
            int m = n * 2;
            int[] arr = new int[m];
            //生成两倍纯能值数组
            //纯能值数组：[6,-1,-2,4,-2,-4]
            //[6,-1,-2,4,-2,-4,6,-1,-2,4,-2,-4]
            for (int i = 0; i < n; i++) {
                arr[i] = g[i] - c[i];
                arr[i + n] = g[i] - c[i];
            }
            //生成两倍累加值数组
            //[6,5,3,7,5,1,7,6,4,8,6,2]
            for (int i = 1; i < m; i++) {
                arr[i] += arr[i - 1];
            }

            //窗口内最小值 初始化窗口 即0位置的窗口 队列的头部元素为最小值的下标
            LinkedList<Integer> deque = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                while (!deque.isEmpty() && arr[deque.peekLast()] >= arr[i]){
                    deque.pollLast();
                }
                //存入下标
                deque.addLast(i);
            }


            //offset就是窗口的前一个值 每次窗口要减去他 然后再判断是否大于等于0
            //i ... j就是窗口范围（i[0 ... n] j[n ... m - 1]）
            boolean[] ans = new boolean[n];
            for (int offset = 0, i = 0, j = n; j < m; offset = arr[i++], j++){
                //最小值 - 窗口前一个值 判断是否 大于等于 0
                if (arr[deque.peekFirst()] - offset >= 0){
                    ans[i] = true;
                }
                //始终维护一个i...j的窗口 此时窗口是[1 ... 7]
                //1 2 3 4 5 6 7
                //i           j
                //如果最小值在1
                //那么此时i == 最小值的下标（队列的头部元素），下一个窗口是[2 ... 8]，那么最小值过期，弹出
                //如果最小值在3
                //那么此时i != 最小值的下标（队列的头部元素），下一个窗口是[2 ... 8]，此时最小值还在窗口内，不应该过期。
                if (deque.peekFirst() == i){
                    deque.pollFirst();
                }
                //不断的向右扩窗口
                while (!deque.isEmpty() && arr[deque.peekLast()] >= arr[j]){
                    deque.pollLast();
                }
                //存入下标
                deque.addLast(j);
            }
            return ans;
        }
    }

}
