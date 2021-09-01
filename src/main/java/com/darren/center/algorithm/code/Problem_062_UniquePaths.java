package com.darren.center.algorithm.code;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2021年09月01日 19:34:20
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 **/
public class Problem_062_UniquePaths {


    public static void main(String[] args) {
        Solution solution = new Problem_062_UniquePaths().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 排列组合  https://baike.baidu.com/item/%E6%8E%92%E5%88%97%E7%BB%84%E5%90%88/706498?fr=aladdin
         * 组合的定义：从n个不同元素中，任取m(m≤n）个元素并成一组，叫做从n个不同元素中取出m个元素的一个组合；
         * 从n个不同元素中取出m(m≤n）个元素的所有组合的个数，叫做从n个不同元素中取出m个元素的组合数。用符号 C(n,m) 表示。
         * C（n,m）= n! / (m!)*(n!-m!)
         * C(9, 5)  从左上角到右下角需要9步  不管你怎么走 都需要向下走五步
         * C(9, 5) -> 9! / 5!*4! ->  9*8*7*6*5 / 5*4*3*2*1 = 126
         * @param m
         * @param n
         * @return
         */
        public int uniquePaths(int m, int n) {

            int part = n - 1;
            //总步数
            int all = m + n - 2;
            //C(9, 5)
            //part = 9 - 5 = 4
            //all = 9
            //a -> 5 6 7 8 9 -> part + 1 -> 5步
            //b -> 1 2 3 4 5 -> all - part -> 4步
            long a = 1L;
            long b = 1L;
            for(int i = part + 1, j = 1; i <= all || j <= all - part; i++, j++){
                a = a * i;
                b = b * j;
                long gcd = gcd(a, b);
                a = a / gcd;
                b = b / gcd;
            }
            return (int) a;
        }

        /**
         * 求最大公约数 https://baike.baidu.com/item/%E6%9C%80%E5%A4%A7%E5%85%AC%E7%BA%A6%E6%95%B0/869308?fr=aladdin
         * 辗转相除法：辗转相除法是求两个自然数的最大公约数的一种方法，也叫欧几里德算法。
         * 调用的时候，请保证初次调用时，m和n都不为0
         * @param m
         * @param n
         * @return
         */
        public long gcd(long m, long n){
            return n == 0 ? m : gcd(n, m % n);
        }
    }

}

