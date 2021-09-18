package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-09-18 14:00:27
 * Version: 1.0
 * Description:
 */
public class Problem_0104_MaximumDepthOfBinaryTree {

    public static void main(String[] args) {
        Solution solution = new Problem_0104_MaximumDepthOfBinaryTree().new Solution();
        TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        int res = solution.maxDepth(root);
        System.out.println(res);
    }

    //给定二叉树 [3,9,20,null,null,15,7]，
    //     3
    //   / \
    //  9  20
    //    /  \
    //   15   7
    // 返回它的最大深度 3 。
    class Solution {
        public int maxDepth(TreeNode root) {
            if (root == null){
                return 0;
            }
            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
