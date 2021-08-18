package com.darren.center.algorithm.code;

/**
 * Author: Darren
 * Date: 2021-08-18 11:34:05
 * Version: 1.0
 * Description:
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 *  示例 1：
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 *
 *  示例 2：
 * 输入：l1 = [], l2 = []
 * 输出：[]
 *
 *  示例 3：
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 *
 *  提示：
 *  两个链表的节点数目范围是 [0, 50]
 *  -100 <= Node.val <= 100
 *  l1 和 l2 均按 非递减顺序 排列
 *
 *  Related Topics 递归 链表
 */
public class Problem_021_MergeTwoSortedLists {

    public static void main(String[] args) {
        Solution solution = new Problem_021_MergeTwoSortedLists().new Solution();
        //l1 = [1,2,4], l2 = [1,3,4]
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        System.out.println(solution.mergeTwoLists(l1, l2));
    }

    public static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null || l2 == null){
                return l1 == null ? l2 : l1;
            }
            ListNode head = l1.val <= l2.val ? l1 : l2;
            ListNode pre = head;
            ListNode cur1 = pre.next;
            ListNode cur2 = head == l1 ? l2 : l1;
            while (cur1 != null && cur2 != null){
                if (cur1.val <= cur2.val){
                    pre.next = cur1;
                    cur1 = cur1.next;
                }else{
                    pre.next = cur2;
                    cur2 = cur2.next;
                }
                pre = pre.next;
            }
            pre.next = cur1 == null ? cur2 : cur1;
            return head;
        }
    }

}
