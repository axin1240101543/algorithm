package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-10-25 10:28:21
 * Version: 1.0
 * Description:
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充
 *
 *  示例 1：
 * 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O",
 * "X","X"]]
 * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都
 * 会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 */
public class Problem_0130_SurroundedRegions {

    public static void main(String[] args) {
        Solution solution = new Problem_0130_SurroundedRegions().new Solution();
        //XXXX
        //XOOX
        //XXOX
        //XOXX
        char[][] board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O', 'X','X'}};
        solution.solve(board);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：
         * 1、先去找到那些位置要修改，那些不需要修改，当碰到边界时，与碰到边界的这个O相连的O都不需要修改，只有O的上下左右都被X包围的时候才需要修改
         * 2、然后根据修改的信息去修改相应的位置
         * 注：为什么不边递归边修改？
         * 因为当你某个点碰到边界后，它自己不用修改，和它相连的这个点会也不用修改，但相连的这个点的上下左右的点是不会回来到这个相连的点的，则他们就不会知道他们是否修改
         * @param board
         */
        public void solve(char[][] board) {
            //判断这个片区是否需要修改 实际上就是判断这个片区所有的O有没有碰边界
            boolean[] flag = new boolean[1];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 'O'){
                        //上来先变成true  如果发现碰壁 那么就变成false
                        flag[0] = true;
                        can(board, i, j, flag);
                        //标记当前片区是 修改还是不修改
                        board[i][j] = flag[0] ? 'T' : 'F';
                    }
                }
            }

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    char c = board[i][j];
                    //进入片区
                    if (c == 'T' || c == 'F'){
                        //将标记的位置修改为. 此时当前片区所有位置都是.
                        board[i][j] = '.';
                        change(board, i, j, c);
                    }
                }
            }
        }

        /**
         *
         * @param board
         * @param i
         * @param j
         * @param c 当前片区是修改还是不修改
         */
        public void change(char[][] board, int i, int j, char c){
            if (i < 0 || i == board.length || j < 0 || j == board[0].length){
                return;
            }
            if (board[i][j] == '.'){
                board[i][j] = c == 'T' ? 'X' : 'O';
                change(board, i + 1, j, c);
                change(board, i - 1, j, c);
                change(board, i, j + 1, c);
                change(board, i, j - 1, c);
            }
        }

        /**
         *
         * @param board
         * @param i
         * @param j
         * @param flag 当前片区是修改还是不修改
         */
        public void can(char[][] board, int i, int j, boolean[] flag){
            if (i < 0 || i == board.length || j < 0 || j == board[0].length){
                flag[0] = false;
                return;
            }
            if (board[i][j] == 'O'){
                board[i][j] = '.';
                can(board, i + 1, j, flag);
                can(board, i - 1, j, flag);
                can(board, i, j + 1, flag);
                can(board, i, j - 1, flag);
            }
        }
    }

}
