package com.darren.center.algorithm.code;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Darren
 * Date: 2021-09-18 14:05:34
 * Version: 1.0
 * Description:
 */
public class Problem_0105_ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public static void main(String[] args) {
        Solution solution = new Problem_0105_ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
    }

    //给定一棵树的前序遍历 preorder 与中序遍历 inorder。请构造二叉树并返回其根节点。
    //Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
    //Output: [3,9,20,null,null,15,7]
    class Solution {


        public TreeNode buildTree1(int[] preorder, int[] inorder) {
            //直接将数字在in数组中的位置缓存下来
            Map<Integer, Integer> map = new HashMap<>(inorder.length);
            for (int i = 0; i < inorder.length; i++) {
                map.put(inorder[i], i);
            }
            return process1(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
        }

        public TreeNode process1(int[] pre, int l1, int r1, int[] in, int l2, int r2, Map<Integer, Integer> map){
            if (l1 > r1){
                return null;
            }

            //只有一个数了
            TreeNode head = new TreeNode(pre[l1]);
            if (l1 == r1){
                return head;
            }

            //[3,9,20,15,7], inorder = [9,3,15,20,7]
            //左树的范围 l1+1 ... l1 + (index - l2)
            //index - l2 可以得到左树有几个节点 l1往后数几个节点就是pre数组中的左树的范围
            head.left = process1(pre, l1 + 1, l1 + map.get(pre[l1]) - l2, in, l2, map.get(pre[l1]) - 1, map);
            //右树直接加1 即可
            head.right = process1(pre, l1 + map.get(pre[l1]) - l2 + 1, r1, in, map.get(pre[l1]) + 1, r2, map);
            return head;
        }

        //先序 中左右
        //中序 左中右
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            return process(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        }

        public TreeNode process(int[] pre, int l1, int r1, int[] in, int l2, int r2){
            if (l1 > r1){
                return null;
            }

            //只有一个数了
            TreeNode head = new TreeNode(pre[l1]);
            if (l1 == r1){
                return head;
            }

            //得到头在in数组中的位置
            int index = 0;
            for (; index < in.length; index++) {
                if (pre[l1] == in[index]){
                    break;
                }
            }

            //[3,9,20,15,7], inorder = [9,3,15,20,7]
            //左树的范围 l1+1 ... l1 + (index - l2)
            //index - l2 可以得到左树有几个节点 l1往后数几个节点就是pre数组中的左树的范围
            head.left = process(pre, l1 + 1, l1 + index - l2, in, l2, index - 1);
            //右树直接加1 即可
            head.right = process(pre, l1 + index - l2 + 1, r1, in, index + 1, r2);
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
