package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-09-23 16:41:58
 * Version: 1.0
 * Description:
 */
public class Problem_073_SetMatrixZeroes {

    public static void main(String[] args) {
        Solution solution = new Problem_073_SetMatrixZeroes().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void setZeroes1(int[][] matrix) {
            boolean col = false;

            //将每一行和列的信息记录在第一行和第一列
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (matrix[i][j] == 0) {
                        //记录列的信息
                        matrix[i][0] = 0;

                        //记录行的信息
                        //第0列的时候 标记变0
                        if (j == 0){
                            col = true;
                        }else{
                            matrix[0][j] = 0;
                        }
                    }
                }
            }

            for (int i = matrix.length - 1; i >= 0; i--) {
                for (int j = 1; j < matrix[0].length; j++) {
                    if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                        matrix[i][j] = 0;
                    }
                }
            }

            if (col) {
                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][0] = 0;
                }
            }
        }

        public void setZeroes(int[][] matrix) {
            boolean row = false;
            //列是变动的  第一行
            for (int i = 0; i < matrix[0].length; i++) {
                if (matrix[0][i] == 0) {
                    row = true;
                    break;
                }
            }

            boolean col = false;
            //行是变动的  第一列
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][0] == 0) {
                    col = true;
                    break;
                }
            }

            //将每一行和列的信息记录在第一行和第一列
            for (int i = 1; i < matrix.length; i++) {
                for (int j = 1; j < matrix[0].length; j++) {
                    if (matrix[i][j] == 0) {
                        matrix[0][j] = 0;
                        matrix[i][0] = 0;
                    }
                }
            }

            for (int i = 1; i < matrix.length; i++) {
                for (int j = 1; j < matrix[0].length; j++) {
                    if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                        matrix[i][j] = 0;
                    }
                }
            }

            if (row) {
                for (int i = 0; i < matrix[0].length; i++) {
                    matrix[0][i] = 0;
                }
            }

            if (col) {
                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][0] = 0;
                }
            }
        }
    }

}
