package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-08-23 10:55:43
 * Version: 1.0
 * Description:
 */
public class Problem_036_ValidSudoku {

    public static void main(String[] args) {
        Solution solution = new Problem_036_ValidSudoku().new Solution();
        char[][] board = {
            {'5','3','.','.','7','.','.','.','.'}
            ,{'6','.','.','1','9','5','.','.','.'}
            ,{'.','9','8','.','.','.','.','6','.'}
            ,{'8','.','.','.','6','.','.','.','3'}
            ,{'4','.','.','8','.','3','.','.','1'}
            ,{'7','.','.','.','2','.','.','.','6'}
            ,{'.','6','.','.','.','.','2','8','.'}
            ,{'.','.','.','4','1','9','.','.','5'}
            ,{'.','.','.','.','8','.','.','7','9'}
        };

        boolean validSudoku = solution.isValidSudoku(board);
        System.out.println(validSudoku);

    }

    class Solution {
        public boolean isValidSudoku(char[][] board) {
            boolean[][] row = new boolean[9][10];
            boolean[][] col = new boolean[9][10];
            boolean[][] bucket = new boolean[9][10];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    int bid = 3 * (i / 3) + (j / 3);
                    System.out.println("i:" + i + " j:" + j  + " bid:" + bid);
                    if (board[i][j] != '.'){
                        int num = board[i][j] - '0';
                        if(row[i][num] || col[j][num] || bucket[bid][num]){
                            return false;
                        }
                        row[i][num] = true;
                        col[j][num] = true;
                        bucket[bid][num] = true;
                    }
                }
            }
            return true;
        }
    }

}
