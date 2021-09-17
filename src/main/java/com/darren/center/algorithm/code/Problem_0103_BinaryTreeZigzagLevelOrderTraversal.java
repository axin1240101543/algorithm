package com.darren.center.algorithm.code;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: Darren
 * Date: 2021-09-17 15:17:11
 * Version: 1.0
 * Description:
 */
public class Problem_0103_BinaryTreeZigzagLevelOrderTraversal {

    public static void main(String[] args) {
        Solution solution = new Problem_0103_BinaryTreeZigzagLevelOrderTraversal().new Solution();
        TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        List<List<Integer>> lists = solution.zigzagLevelOrder(root);
        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.println(integer + " ");
            }
            System.out.println();
        }
    }

    //给定二叉树 [3,9,20,null,null,15,7],
    //    3
    //   / \
    //  9  20
    //    /  \
    //   15   7
    // 返回锯齿形层序遍历如下：
    //[
    //  [3],
    //  [20,9],
    //  [15,7]
    //]
    class Solution {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> ans = new ArrayList<>();
            if (root == null){
                return ans;
            }

            LinkedList<TreeNode> deque = new LinkedList<>();
            deque.add(root);
            //是否从左往右 否则 从右往左
            boolean isHead = true;

            while (!deque.isEmpty()){
                int size = deque.size();
                List<Integer> cur = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    if (isHead){
                        //从左往右 从头弹  从尾进
                        TreeNode c = deque.pollFirst();
                        cur.add(c.val);
                        if (c.left != null){
                            deque.offerLast(c.left);
                        }
                        if (c.right != null){
                            deque.offerLast(c.right);
                        }
                    }else{
                        //从右往左 从尾弹 从头进 先加右 后加左
                        TreeNode c = deque.pollLast();
                        cur.add(c.val);
                        if (c.right != null){
                            deque.offerFirst(c.right);
                        }
                        if (c.left != null){
                            deque.offerFirst(c.left);
                        }
                    }
                }
                ans.add(cur);
                isHead = !isHead;
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
