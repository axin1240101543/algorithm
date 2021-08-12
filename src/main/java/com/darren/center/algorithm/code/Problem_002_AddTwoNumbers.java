package com.darren.center.algorithm.code;

public class Problem_002_AddTwoNumbers {

    public static void main(String[] args) {
        //l1 = [2,4,3], l2 = [5,6,4]
        ListNode l1 = new ListNode();
        ListNode l12 = new ListNode();
        ListNode l13 = new ListNode();

        l1.val = 2;
        l1.next = l12;

        l12.val = 4;
        l12.next = l13;

        l13.val = 3;

        ListNode l2 = new ListNode();
        ListNode l22 = new ListNode();
        ListNode l23 = new ListNode();

        l2.val = 5;
        l2.next = l22;

        l22.val = 6;
        l22.next = l23;

        l23.val = 4;

        addTwoNumbers(l1, l2);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
       if (l1 == null || l2 == null){
           return null;
       }
        ListNode first = l1;
        ListNode second = l2;
        ListNode head = null;
        ListNode pre = null;
        Boolean flag = false;
        while (first != null && second != null){
            int sum = first.val + second.val;
            //下一位如果上一位有进位，那么就++
            if (flag){
                sum++;
                flag = false;
            }
            //当前位如果大于10 那么取余
            if (sum >= 10){
                sum = sum % 10;
                flag = true;
            }

            if (head == null){
                head = new ListNode();
                head.val = sum;
                pre = head;
            }else{
                ListNode cur = new ListNode();
                cur.val = sum;
                pre.next = cur;
                pre = cur;
            }

            first = first.next;
            second = second.next;
        }

        while (first != null){
            int sum = first.val;
            //下一位如果上一位有进位，那么就++
            if (flag){
                sum++;
                flag = false;
            }
            //当前位如果大于10 那么取余
            if (sum >= 10){
                sum = sum % 10;
                flag = true;
            }

            ListNode cur = new ListNode();
            cur.val = sum;
            pre.next = cur;
            pre = cur;

            first = first.next;
        }

        while (second != null){
            int sum = second.val;
            //下一位如果上一位有进位，那么就++
            if (flag){
                sum++;
                flag = false;
            }
            //当前位如果大于10 那么取余
            if (sum >= 10){
                sum = sum % 10;
                flag = true;
            }

            ListNode cur = new ListNode();
            cur.val = sum;
            pre.next = cur;
            pre = cur;

            second = second.next;
        }

        while (flag){
            ListNode cur = new ListNode();
            cur.val = 1;
            pre.next = cur;
            pre = cur;
        }

        return head;
    }

     static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}



