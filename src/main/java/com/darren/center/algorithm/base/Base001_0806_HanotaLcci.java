package com.darren.center.algorithm.base;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2021年09月02日 08:17:04
 * 在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只
 * 能放在更大的盘子上面)。移动圆盘时受到以下限制:
 * (1) 每次只能移动一个盘子;
 * (2) 盘子只能从柱子顶端滑出移到下一根柱子;
 * (3) 盘子只能叠在比它大的盘子上。
 *  请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。
 *  你需要原地修改栈。
 *
 *  示例1:
 *   输入：A = [2, 1, 0], B = [], C = []
 *  输出：C = [2, 1, 0]
 *
 *  示例2:
 *   输入：A = [1, 0], B = [], C = []
 *  输出：C = [1, 0]
 *
 *  提示:
 *  A中盘子的数目不大于14个。
 **/
public class Base001_0806_HanotaLcci {
    public static void main(String[] args) {
        Solution solution = new Base001_0806_HanotaLcci().new Solution();
        List<Integer> A = new ArrayList<>();
        A.add(2);
        A.add(1);
        A.add(0);
        List<Integer> B = new ArrayList<>();
        List<Integer> C = new ArrayList<>();
        solution.hanota(A, B, C);
        solution.hanota1(3, "left", "mid", "right");
        solution.hanota2(100);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
            int n = A.size();
            //A表示左边  C表示右边  B表示中间
            process(n, A, C, B);
        }

        //from -> to 我想去的位置
        //other 腾路的地方
        public void process(int n, List<Integer> from, List<Integer> to, List<Integer> other){
            //如果只有一层  直接过去
            if(n == 1){
                to.add(0, from.remove(0));
                return;
            }else{
                //将n - 1 层 从from移动到other
                process(n - 1, from, other, to);
                //将n层从 from直接移动到to
                to.add(0, from.remove(0));
                // 将n-1 层从other移动到to
                process(n - 1, other, to, from);
            }
        }

        public void hanota1(int n, String A, String B, String C) {
            //A表示左边  C表示右边  B表示中间
            process1(n, A, C, B);
        }

        public void process1(int n, String from, String to, String other){
            if(n == 1){
                System.out.println("move 1 from " +  from + " to " + to);
            }else{
                process1(n - 1, from, other, to);
                System.out.println("move 1 from " +  from + " to " + to);
                process1(n - 1, other, to, from);
            }
        }

        //请把1~N层圆盘 从左 -> 右
        public void hanota2(int n) {
            //A表示左边  C表示右边  B表示中间
            left2right(n);
        }


        public void left2right(int n){
            if(n == 1){
                System.out.println("move 1 from left to right" );
                return;
            }
            //n - 1 left -> mid
            left2mid(n - 1);
            //n left -> right
            System.out.println("move 1 from left to right");
            //n - 1 mid -> right
            mid2right(n - 1);
        }

        public void left2mid(int n){
            if(n == 1){
                System.out.println("move 1 from left to mid" );
                return;
            }
            left2right(n - 1);
            System.out.println("move 1 from left to mid");
            right2mid(n - 1);
        }

        public void right2mid(int n){
            if(n == 1){
                System.out.println("move 1 from right to mid" );
                return;
            }
            right2left(n - 1);
            System.out.println("move 1 from right to mid" );
            left2mid(n - 1);
        }

        public void right2left(int n){
            if(n == 1){
                System.out.println("move 1 from right to left" );
                return;
            }
            right2mid(n - 1);
            System.out.println("move 1 from right to left" );
            mid2left(n - 1);
        }

        public void mid2left(int n){
            if(n == 1){
                System.out.println("move 1 from mid to left" );
                return;
            }
            mid2right(n - 1);
            System.out.println("move 1 from mid to left" );
            right2left(n - 1);
        }

        public void mid2right(int n){
            if(n == 1){
                System.out.println("move 1 from mid to right" );
                return;
            }
            mid2left(n - 1);
            System.out.println("move 1 from mid to right" );
            left2right(n - 1);
        }
    }
}

