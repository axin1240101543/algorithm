package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-10-19 10:31:01
 * Version: 1.0
 * Description:
 */
public class Problem_0124_BinaryTreeMaximumPathSum {

    public static void main(String[] args) {
        Solution solution = new Problem_0124_BinaryTreeMaximumPathSum().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不
    //一定经过根节点。
    //
    // 路径和 是路径中各节点值的总和。
    //
    // 给你一个二叉树的根节点 root ，返回其 最大路径和 。
    //
    // 示例 1：
    //
    //
    //输入：root = [1,2,3]
    //输出：6
    //解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
    class Solution {
        //可能性分析
        //1、与x无关
        //那么math（leftMax和rightMax）
        //2、与x有关
        //1）只有x
        //2）x往左边扎 必须从x出发
        //3）x往右边扎 必须从x出发
        //4）左边扎最大 + x + 右边扎最大
        public int maxPathSum(TreeNode root) {
            if(root == null){
                return 0;
            }

            return process(root).max;
        }

        public class Info{
            //最大值
            private int max;
            //必须从头出发的最大值
            private int headMax;

            public Info(int max, int headMax) {
                this.max = max;
                this.headMax = headMax;
            }
        }

        public Info process(TreeNode node){
            if (node == null){
                return null;
            }

            Info leftInfo = process(node.left);
            Info rightInfo = process(node.right);

            //可能性1 左树最大值
            int p1 = Integer.MIN_VALUE;
            if (leftInfo != null){
                p1 = leftInfo.max;
            }

            //可能性2 右树最大值
            int p2 = Integer.MIN_VALUE;
            if (rightInfo != null){
                p2 = rightInfo.max;
            }

            //可能性3 只有x自己
            int p3 = node.val;

            //可能性4 x + 从x出发的左树最大值
            int p4 = Integer.MIN_VALUE;
            if (leftInfo != null){
                p4 = leftInfo.headMax + node.val;
            }

            //可能性5 x + 从x出发的右树最大值
            int p5 = Integer.MIN_VALUE;
            if (rightInfo != null){
                p5 = rightInfo.headMax + node.val;
            }

            //可能性6 从x出发的左树最大值 + x + 从x出发的右树最大值，也就是经过x
            int p6 = Integer.MIN_VALUE;
            if (leftInfo != null && rightInfo != null){
                p6 = leftInfo.headMax + node.val + rightInfo.headMax;
            }

            //六种可能性 取最大值
            int max = Math.max(p1, Math.max(p2, Math.max(p3, Math.max(p4, Math.max(p5, p6)))));
            //三种可能性 只有x自己  从x出发的左树最大值（包含x）  从x出发的右树最大值（包含x）
            int headMax = Math.max(p3, Math.max(p4, p5));
            return new Info(max, headMax);
        }
    }

}
