package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-08-23 13:54:36
 * Version: 1.0
 * Description:
 */
public class Problem_037_SudokuSolver {
    public static void main(String[] args) {
        Solution solution = new Problem_037_SudokuSolver().new Solution();
    }
    class Solution {
        public void solveSudoku(char[][] board) {
            boolean[][] row = new boolean[9][10];
            boolean[][] col = new boolean[9][10];
            boolean[][] bucket = new boolean[9][10];
            initMaps(board, row, col, bucket);
            process(board, 0, 0, row, col, bucket);
        }

        public boolean process(char[][] board, int i, int j, boolean[][] row, boolean[][] col, boolean[][] bucket){
            if(i == 9){
                return true;
            }
            int nexti = j != 8 ? i : i + 1;
            int nextj = j != 8 ? j + 1 : 0;
            if(board[i][j] != '.'){
                return process(board, nexti, nextj, row, col, bucket);
            }else{
                int bid = 3 * (i / 3) + (j / 3);
                for (int num = 1; num <= 9; num++){
                    if(!row[i][num] && !col[j][num] && !bucket[bid][num]){
                        row[i][num] = true;
                        col[j][num] = true;
                        bucket[bid][num] = true;
                        board[i][j] = (char) (num + '0');
                        if(process(board, nexti, nextj, row, col, bucket)){
                            return true;
                        }
                        row[i][num] = false;
                        col[j][num] = false;
                        bucket[bid][num] = false;
                        board[i][j] = '.';
                    }
                }
            }
            return false;
        }

        public void initMaps(char[][] board, boolean[][] row, boolean[][] col, boolean[][] bucket){
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    int bid = 3 * (i / 3) + (j / 3);
                    if (board[i][j] != '.'){
                        int num = board[i][j] - '0';
                        row[i][num] = true;
                        col[j][num] = true;
                        bucket[bid][num] = true;
                    }
                }
            }
        }
    }
}
