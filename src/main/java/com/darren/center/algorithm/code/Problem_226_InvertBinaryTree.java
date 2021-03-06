package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-08-16 10:21:11
 * Version: 1.0
 * Description:
 * 翻转一棵二叉树
 */
public class Problem_226_InvertBinaryTree {

    public static void main(String[] args) {
        Solution solution = new Problem_226_InvertBinaryTree().new Solution();
    }


    class Solution {
        public TreeNode invertTree(TreeNode root) {
            return new TreeNode();
        }
    }

    public TreeNode invertTree(TreeNode root) {
        if (root != null){
            invert(root);
        }
        return root;
    }

    public void invert(TreeNode root){
        TreeNode left = root.left;
        if (root.left != null){
            invert(root.left);
        }
        TreeNode right = root.right;
        if (root.right != null){
            invert(root.right);
        }
        root.left = right;
        root.right = left;
    }

    public class TreeNode {
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
