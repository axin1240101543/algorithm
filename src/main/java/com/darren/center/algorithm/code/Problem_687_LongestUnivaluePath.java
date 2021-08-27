package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-08-27 09:59:00
 * Version: 1.0
 * Description:
 * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
 * 注意：两个节点之间的路径长度由它们之间的边数表示。
 */
public class Problem_687_LongestUnivaluePath {

    public static void main(String[] args) {
        Solution solution = new Problem_687_LongestUnivaluePath().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 1）与X无关
     * 左树上最大
     * 右树上最大
     * 2）X有关
     * X自己
     * X左树
     * X右树
     * X左右树
     */
    class Solution {
        public int longestUnivaluePath(TreeNode root) {
            if (root == null){
                return 0;
            }
            return process(root).max - 1;
        }

        //假设有n个节点  距离为n  最后返回的时候 max - 1
        public Info process(TreeNode x){
            if (x == null){
                return new Info(0, 0);
            }

            //求出x的len
            TreeNode left = x.left;
            TreeNode right = x.right;

            //假设能拿到左树信息和右树信息
            Info leftInfo = process(left);
            Info rightInfo = process(right);

            //如果只有x一个
            int len = 1;

            //从x出发 如左节点不等于null  且和x的value相等 则往左下走
            if (left != null && left.val == x.val){
                len = leftInfo.len + 1;
            }

            //右节点同理
            //从x出发 求最大值  因为必须x出发，只能走一侧
            if (right != null && right.val == x.val){
                len = Math.max(len, rightInfo.len + 1);
            }

            //求出x的max
            //x有关(len)：x本身 左侧最大值  右侧最大值  x无关：左侧最大值  右侧最大值
            //x无关：左侧最大值  右侧最大值
            int max = Math.max(leftInfo.max, rightInfo.max);
            //求出五种情况的最大值
            max = Math.max(max, len);

            //最后一种情况
            //（不以x出发）左最大长度 + (不以x出发)右最大长度 + 1，即x的value等于left的value等于right的value
            if (left != null && right != null && x.val == left.val && x.val == right.val){
                max = Math.max(max, leftInfo.len + rightInfo.len + 1);
            }
            return new Info(len, max);
        }
    }

    //建设以x为头的树，返回两个信息
    public class Info{
        //在一条路径上：要求每个节点通过且只通过一遍
        public int len;//必须从x出发只能往下走，整棵树的最大合法距离
        public int max;//不要求从x出发，整棵树的最大合法距离

        public Info() {
        }

        public Info(int len, int max) {
            this.len = len;
            this.max = max;
        }
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
