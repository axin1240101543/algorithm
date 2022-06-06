package com.darren.center.algorithm2.class03;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>algorithm</h3>
 * <p></p>
 * 单链表、双向链表反转
 *
 * 单链表：
 * 方法一：改变指针往前指，第一个节点指向null，返回最后一个节点
 * 1、next指向下一个节点
 * 2、操作当前节点，当前节点的next指向pre（默认是null）
 * 3、pre指向当前节点
 * 4、当前节点来到下一个节点
 *
 * 方法二：数组放入单链表，然后反向遍历，返回n-1
 *
 * 双链表：
 * 多一步操作  last指针指向下一个节点
 *
 * @author : Darren
 * @date : 2022年05月27日 07:43:55
 **/
public class Code01_ReverseList {

    // for test
    public static void main(String[] args) {
        int len = 50;
        int value = 100;
        int testTime = 100000;
        System.out.println("test begin!");
        for (int i = 0; i < testTime; i++) {
            //生成一个随机单向链表
            Node node1 = generateRandomLinkedList(len, value);
            //将单向链表加入list
            List<Integer> list1 = getLinkedListOriginOrder(node1);
            //反转单向链表
            node1 = reverseNode(node1);
            //反向遍历判断反转后的链表和list中每个值是否相等
            if (!checkLinkedListReverse(list1, node1)) {
                System.out.println("Oops1!");
            }

            //生成一个随机单向链表
            Node node2 = generateRandomLinkedList(len, value);
            //将单向链表加入list
            List<Integer> list2 = getLinkedListOriginOrder(node2);
            //反转单向链表（list实现）
            node2 = testReverseLinkedList(node2);
            //反向遍历判断反转后的链表和list中每个值是否相等
            if (!checkLinkedListReverse(list2, node2)) {
                System.out.println("Oops2!");
            }

            //生成一个随机双向链表
            DoubleNode node3 = generateRandomDoubleList(len, value);
            //将双向链表加入list
            List<Integer> list3 = getDoubleListOriginOrder(node3);
            //反转双向链表
            node3 = reverseDoubleNode(node3);
            //反向遍历判断反转后的链表和list中每个值是否相等
            if (!checkDoubleListReverse(list3, node3)) {
                System.out.println("Oops3!");
            }

            //生成一个随机双向链表
            DoubleNode node4 = generateRandomDoubleList(len, value);
            //将双向链表加入list
            List<Integer> list4 = getDoubleListOriginOrder(node4);
            //反转双向链表
            node4 = reverseDoubleNode(node4);
            //反向遍历判断反转后的链表和list中每个值是否相等
            if (!checkDoubleListReverse(list4, node4)) {
                System.out.println("Oops4!");
            }

        }
        System.out.println("test finish!");

    }

    public static Node reverseNode(Node head){
        if (head == null){
            return null;
        }
        Node pre = null;
        Node next = null;
        while (head != null){
            //记录下一个节点
            next = head.next;
            //改变当前节点的next
            head.next = pre;
            //上一个节点来到当前节点
            pre = head;
            //当前节点来到下一个节点
            head = next;
        }
        return pre;
    }

    public static Node testReverseLinkedList(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        list.get(0).next = null;
        int N = list.size();
        for (int i = 1; i < N; i++) {
            list.get(i).next = list.get(i - 1);
        }
        return list.get(N - 1);
    }

    public static DoubleNode reverseDoubleNode(DoubleNode head){
        if (head == null){
            return null;
        }
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null){
            //记录下一个节点
            next = head.next;
            //改变当前节点的next
            head.next = pre;
            //改变当前节点的last（多一行）
            head.last = next;
            //上一个节点来到当前节点
            pre = head;
            //当前节点来到下一个节点
            head = next;
        }
        return pre;
    }

    // for test
    public static Node generateRandomLinkedList(int len, int value) {
        int size = (int) (Math.random() * (len + 1));
        if (size == 0) {
            return null;
        }
        size--;
        Node head = new Node((int) (Math.random() * (value + 1)));
        Node pre = head;
        while (size != 0) {
            Node cur = new Node((int) (Math.random() * (value + 1)));
            pre.next = cur;
            pre = cur;
            size--;
        }
        return head;
    }

    // for test
    public static DoubleNode generateRandomDoubleList(int len, int value) {
        //0 ~ len
        int size = (int) (Math.random() * (len + 1));
        if (size == 0) {
            return null;
        }
        size--;
        DoubleNode head = new DoubleNode((int) (Math.random() * (value + 1)));
        DoubleNode pre = head;
        while (size != 0) {
            DoubleNode cur = new DoubleNode((int) (Math.random() * (value + 1)));
            pre.next = cur;
            cur.last = pre;
            pre = cur;
            size--;
        }
        return head;
    }

    // for test
    public static List<Integer> getLinkedListOriginOrder(Node head) {
        List<Integer> ans = new ArrayList<>();
        while (head != null) {
            ans.add(head.value);
            head = head.next;
        }
        return ans;
    }

    // for test
    public static boolean checkLinkedListReverse(List<Integer> origin, Node head) {
        for (int i = origin.size() - 1; i >= 0; i--) {
            if (!origin.get(i).equals(head.value)) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    // for test
    public static List<Integer> getDoubleListOriginOrder(DoubleNode head) {
        List<Integer> ans = new ArrayList<>();
        while (head != null) {
            ans.add(head.value);
            head = head.next;
        }
        return ans;
    }

    // for test
    public static boolean checkDoubleListReverse(List<Integer> origin, DoubleNode head) {
        DoubleNode end = null;
        for (int i = origin.size() - 1; i >= 0; i--) {
            if (!origin.get(i).equals(head.value)) {
                return false;
            }
            end = head;
            head = head.next;
        }
        for (int i = 0; i < origin.size(); i++) {
            if (!origin.get(i).equals(end.value)) {
                return false;
            }
            end = end.last;
        }
        return true;
    }
}

