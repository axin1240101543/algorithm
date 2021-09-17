package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-09-17 14:07:15
 * Version: 1.0
 * Description:
 */
public class Problem_0101_SymmetricTree {

    public static void main(String[] args) {
        Solution solution = new Problem_0101_SymmetricTree().new Solution();
    }

    //给定一个二叉树，检查它是否是镜像对称的。
    // 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
    //原树和翻面树应该是一样的
    class Solution {
        public boolean isSymmetric(TreeNode root) {
            return process(root, root);
        }

        public boolean process(TreeNode t1, TreeNode t2){
            if (t1 == null && t2 == null){
                return true;
            }

            if (t1 != null && t2 != null){
                return t1.val == t2.val && process(t1.left, t2.right) && process(t1.right, t2.left);
            }

            //一棵树为空 一棵树不为空返回false
            return false;
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
