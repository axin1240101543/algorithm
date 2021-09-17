package com.darren.center.algorithm.code;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Darren
 * Date: 2021-09-17 11:24:12
 * Version: 1.0
 * Description:
 */
public class Problem_094_BinaryTreeInorderTraversal {

    public static void main(String[] args) {
        Solution solution = new Problem_094_BinaryTreeInorderTraversal().new Solution();
        TreeNode root = new TreeNode(1, null, new TreeNode(2, new TreeNode(3 ), null));
        solution.inorderTraversal1(root);

        System.out.println();

        List<Integer> list = solution.inorderTraversal(root);
        list.forEach(System.out::println);
    }

    //输入：root = [1,null,2,3]
    //输出：[1,3,2]
    class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> ans = new ArrayList<>();
            process(root, ans);
            return ans;
        }

        public void process(TreeNode root, List<Integer> ans){
            if (root == null){
                return;
            }
            process(root.left, ans);
            ans.add(root.val);
            process(root.right, ans);
        }

        public void inorderTraversal1(TreeNode root) {
            if (root == null){
                return;
            }
            inorderTraversal1(root.left);
            System.out.println(root.val);
            inorderTraversal1(root.right);
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
