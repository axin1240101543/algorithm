package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-08-17 17:54:29
 * Version: 1.0
 * Description:
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *  进阶：你能尝试使用一趟扫描实现吗？
 *
 *  示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 *  示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 *
 *  示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *
 *  提示：
 *  链表中结点的数目为 sz
 *  1 <= sz <= 30
 *  0 <= Node.val <= 100
 *  1 <= n <= sz
 */
public class Problem_019_RemoveNthNodeFromEndOfList {

    public static void main(String[] args) {
        Solution solution = new Problem_019_RemoveNthNodeFromEndOfList().new Solution();
    }

     public class ListNode {
          int val;
         ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            if (head == null){
                return null;
            }
            ListNode cur = head;
            ListNode pre = null;
            while (cur != null){
                //n每次递减
                n--;
                if (n == -1){
                    pre = head;
                }
                //pre开始跟着cur移动，pre和cur始终保持n+1个距离
                if (n < -1){
                    pre = pre.next;
                }
                cur = cur.next;
            }
            //说明head不够n个节点
            if (n > 0){
                return head;
            }
            //说明head刚好n个节点
            if (pre == null){
                return head.next;
            }
            //pre的下一个就是要删除的节点
            pre.next = pre.next.next;
            return head;
        }
    }
}
