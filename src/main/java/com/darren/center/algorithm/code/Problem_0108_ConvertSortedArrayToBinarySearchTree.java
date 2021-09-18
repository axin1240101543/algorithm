package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-09-18 16:04:39
 * Version: 1.0
 * Description:
 */
public class Problem_0108_ConvertSortedArrayToBinarySearchTree {

    public static void main(String[] args) {
        Solution solution = new Problem_0108_ConvertSortedArrayToBinarySearchTree().new Solution();
    }

    //给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
    // 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
    class Solution {
        public TreeNode sortedArrayToBST(int[] nums) {
            return process(nums, 0, nums.length - 1);
        }

        //输入：nums = [-10,-3,0,5,9]
        //输出：[0,-3,9,-10,null,5]
        //解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
        public TreeNode process(int[] nums, int l, int r){
            if (l > r){
                return null;
            }

            if (l == r){
                return new TreeNode(nums[l]);
            }

            int m = (l + r) >> 1;
            TreeNode head = new TreeNode(nums[m]);
            head.left = process(nums, l, m - 1);
            head.right = process(nums, m + 1, r);
            return head;
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
