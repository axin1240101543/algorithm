package com.darren.center.algorithm.code;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: Darren
 * Date: 2021-09-17 14:46:34
 * Version: 1.0
 * Description:
 */
public class Problem_0102_BinaryTreeLevelOrderTraversal {

    public static void main(String[] args) {
        Solution solution = new Problem_0102_BinaryTreeLevelOrderTraversal().new Solution();
        TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        List<List<Integer>> lists = solution.levelOrder(root);
        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.println(integer + " ");
            }
            System.out.println();
        }
    }

    //给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
    // 示例：
    //二叉树：[3,9,20,null,null,15,7],
    //    3
    //   / \
    //  9  20
    //    /  \
    //   15   7
    //[
    //  [3],
    //  [9,20],
    //  [15,7]
    //]
    class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> ans = new ArrayList<>();
            if (root == null){
                return ans;
            }
            //双端队列 Deque
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()){
                int size = queue.size();
                List<Integer> cur = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    //从头弹
                    TreeNode node = queue.pollFirst();
                    cur.add(node.val);
                    if (node.left != null){
                        //从尾巴进
                        queue.offerLast(node.left);
                    }
                    if (node.right != null){
                        //从尾巴进
                        queue.offerLast(node.right);
                    }
                }
                ans.add(cur);
            }
            return ans;
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
