package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-08-27 10:32:25
 * Version: 1.0
 * Description:
 */
public class Problem_048_RotateImage {
    public static void main(String[] args) {
        Solution solution = new Problem_048_RotateImage().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void rotate(int[][] matrix) {
            int ar = 0;
            int ac = 0;
            int br = matrix.length - 1;
            int bc = matrix[0].length - 1;
            while (ar < br){
                process(matrix, ar++, ac++, br--, bc--);
            }
        }

        //4*4
        //ar ac    0  1  2  3
        //         4        7
        //         8       11
        //        12 13 14 15  br bc
        //第一组 0  3 15 12
        //第二组 1  7 14  8
        //第三组 2 11 13  4
        public void process(int[][] m, int ar, int ac, int br, int bc){
            //bc - ac算出组数
            int tem = 0;
            for (int i = 0; i < bc - ac; i++){
                //ar ac+i 0 1 2
                //ar+i bc 3 7 11
                //br bc-i 15 14 13
                //br-i ac 12 8 4
                tem = m[ar][ac+i];
                //0 应该和 12交换
                m[ar][ac+i] = m[br-i][ac];
                //12 应该和 15交换
                m[br-i][ac] = m[br][bc-i];
                //15 应该和 3交换
                m[br][bc-i] = m[ar+i][bc];
                //3 应该和0交换
                m[ar+i][bc] = tem;
            }
        }
    }
}
