package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-11-15 15:30:37
 * Version: 1.0
 * Description:
 * 141、链表是有环还是无环的，无环返回null，有环返回第一个入环节点。
 * 思路：
 * 快慢指针
 * 如快指针提前走到了null，那么直接返回null，无环。
 * 1）如果快慢指针相遇，那么快指针回到开头，慢指针不动；
 * 2）快慢指针，从开始的快2慢1变成快1慢1继续走；
 * 3）当再次相遇，那么相遇的那个节点就是入环节点。
 */
public class Problem_0141_LinkedListCycle {

    public static void main(String[] args) {
        Solution solution = new Problem_0141_LinkedListCycle().new Solution();
    }

    class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
    }

    public class Solution {
        public boolean hasCycle(ListNode head) {
            return getFirstLoopNode(head) != null;
        }

        public ListNode getFirstLoopNode(ListNode head){
            if (head == null || head.next == null || head.next.next == null){
                return null;
            }
            ListNode slow = head.next;
            ListNode fast = head.next.next;
            while (fast != slow){
                if (fast.next == null || fast.next.next == null){
                    return null;
                }
                slow = slow.next;
                fast = fast.next.next;
            }
            fast = head;
            while (fast != slow){
                slow = slow.next;
                fast = fast.next;
            }
            return fast;
        }
    }

}
