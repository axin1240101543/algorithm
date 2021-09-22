package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-09-18 16:41:32
 * Version: 1.0
 * Description:
 */
public class Problem_0116_PopulatingNextRightPointersInEachNode {

    public static void main(String[] args) {
        Solution solution = new Problem_0116_PopulatingNextRightPointersInEachNode().new Solution();
        Node root = new Node(1, new Node(2, new Node(4), new Node(5), null), new Node(3, new Node(6), new Node(7), null), null);
        Node connect = solution.connect(root);
        System.out.println(connect);
    }

    // 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
    // 初始状态下，所有 next 指针都被设置为 NULL。
    class Solution {
        public Node connect(Node root) {
            if (root == null){
                return null;
            }

            MyQueue myQueue = new MyQueue();
            myQueue.offer(root);
            while (!myQueue.isEmpty()){
                int size = myQueue.size;
                Node pre = null;
                for (int i = 0; i < size; i++) {
                    //从头部弹
                    Node cur = myQueue.poll();
                    //加入尾部
                    if (cur.left != null){
                        myQueue.offer(cur.left);
                    }
                    if (cur.right != null){
                        myQueue.offer(cur.right);
                    }
                    if (pre != null){
                        pre.next = cur;
                    }
                    pre = cur;
                }
            }
            return root;
        }

        class MyQueue{
            public Node head;
            public Node tail;
            public int size;

            public MyQueue() {
                this.head = null;
                this.tail = null;
                this.size = 0;
            }

            //add 从尾部加
            public void offer(Node node){
                size++;
                if (head == null){
                    head = node;
                    tail = node;
                }else {
                    tail.next = node;
                    tail = node;
                }
            }

            //remove 从头部弹
            public Node poll(){
                size--;
                Node ans = head;
                head = head.next;
                ans.next = null;
                return ans;
            }

            //isEmpty
            public boolean isEmpty(){
                return size == 0;
            }
        }
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

}
