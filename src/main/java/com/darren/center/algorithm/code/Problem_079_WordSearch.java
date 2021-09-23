package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-09-23 14:26:43
 * Version: 1.0
 * Description:
 */
public class Problem_079_WordSearch {

    public static void main(String[] args) {
        Solution solution = new Problem_079_WordSearch().new Solution();
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        System.out.println(solution.exist(board, "SEE"));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean exist(char[][] board, String word) {
            char[] w = word.toCharArray();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (process(board, i, j, w, 0)){
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean process(char[][] board, int i, int j, char[] w, int k){
            if (k == w.length){
                return true;
            }

            if (i < 0 || i == board.length || j < 0 || j == board[0].length){
                return false;
            }

            if (board[i][j] != w[k]){
                return false;
            }

            char tem = board[i][j];
            board[i][j] = '0';
            boolean ans = process(board, i + 1, j, w, k + 1)
                    || process(board, i - 1, j, w, k + 1)
                    || process(board, i, j + 1, w, k + 1)
                    || process(board, i, j - 1, w, k + 1);
            board[i][j] = tem;
            return ans;
        }
    }

}
