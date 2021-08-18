package com.darren.center.algorithm.code;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Author: Darren
 * Date: 2021-08-18 15:31:27
 * Version: 1.0
 * Description:
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 *  请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 *  示例 1：
 *
 *  输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 *
 *  示例 2：
 *  输入：lists = []
 * 输出：[]
 *
 *  示例 3：
 *  输入：lists = [[]]
 * 输出：[]
 *
 *  提示：
 *  k == lists.length
 *  0 <= k <= 10^4
 *  0 <= lists[i].length <= 500
 *  -10^4 <= lists[i][j] <= 10^4
 *  lists[i] 按 升序 排列
 *  lists[i].length 的总和不超过 10^4
 */
public class Problem_023_MergeKSortedLists {

    public static void main(String[] args) {
        Solution solution = new Problem_023_MergeKSortedLists().new Solution();
        //lists = [[1,4,5],[1,3,4],[2,6]]
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode l3 = new ListNode(2, new ListNode(6));
        ListNode[] lists = {l1, l2, l3};
        solution.mergeKLists(lists);
    }
          public static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static class ListNodeComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    }

    class Solution {

        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0){
                return null;
            }

            PriorityQueue<ListNode> heap = new PriorityQueue<>(new ListNodeComparator());
            for (ListNode head : lists) {
                if (head != null){
                    heap.offer(head);
                }
            }

            if (heap.isEmpty()) {
                return null;
            }

            ListNode head = heap.poll();
            ListNode pre = head;
            if (pre.next != null){
                heap.offer(pre.next);
            }
            while (!heap.isEmpty()){
                ListNode cur = heap.poll();
                pre.next = cur;
                pre = cur;
                if (cur.next != null){
                    heap.offer(cur.next);
                }
            }
            return head;
        }
    }
}
