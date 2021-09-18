package com.darren.center.algorithm.code;

import java.util.Stack;

/**
 * Author: Darren
 * Date: 2021-09-17 15:50:53
 * Version: 1.0
 * Description:
 */
public class Problem_084_LargestRectangleInHistogram {

    public static void main(String[] args) {
        Solution solution = new Problem_084_LargestRectangleInHistogram().new Solution();
        int[] h = {2,1,5,6,2,3};
        int res = solution.process(h);
        System.out.println(res);
    }

    //输入：heights = [2,1,5,6,2,3]
    //输出：10
    //解释：最大的矩形为图中红色区域，面积为 10
    class Solution {
        public int largestRectangleArea(int[] heights) {
            return process(heights);
        }

        //计算当前位置最优的解 当前位置 左边比你小的 就无法将他计算进来 右边比你小的也无法将他计算进来
        //[2,1,5,6,2,3]
        //假如当前位置是h[4] = 2, 那么左边能计算的位置是 h[2] = 5, 右边能计算的位置是 h[5] = 3， 此时的答案是5 6 2 3四个数（6 - 1 - 1），也就是 4*2 = 8
        //最优解 是 h[2] = 5, 左边的位置是 h[2] = 5, 右边的位置是 h[3] = 6， 此时的答案是5 6 两个数（4 - 1 - 1），也就是 2*5= 10

        //左边的操作可以使用一个栈 只能大压小 要计算的位置弹出之后，栈顶的元素的位置就是左边无法到达的位置
        public int process(int[] h) {
            //存下标
            Stack<Integer> stack = new Stack<>();
            int res = 0;
            for (int i = 0; i < h.length; i++) {
                //如果等于的时候也弹出，将无法计算出此时topIndex的最优解 但是此时topIndex的最优解不会错过 因为以后的这个相等的值会计算出之前的相等的值的最优解 可能性舍弃
                while (!stack.isEmpty() && h[i] <= h[stack.peek()]){
                    //此时计算topIndex位置的答案
                    int topIndex = stack.pop();
                    int curTopIndex = stack.isEmpty() ? -1 : stack.peek();
                    //计算有效范围
                    int validArea = i - curTopIndex - 1;
                    res = Math.max(res, h[topIndex] * validArea);
                }
                stack.push(i);
            }

            //123456 没有机会弹出
            while (!stack.isEmpty()){
                //此时计算topIndex位置的答案
                int topIndex = stack.pop();
                int curTopIndex = stack.isEmpty() ? -1 : stack.peek();
                //计算有效范围
                int validArea = h.length - curTopIndex - 1;
                res = Math.max(res, h[topIndex] * validArea);
            }

            return res;
        }
    }
}
