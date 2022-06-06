package com.darren.center.algorithm2.class03;

/**
 * <h3>algorithm</h3>
 * <p></p>
 * 把给定值都删除
 *
 * 入参：Node h，int num
 * 删除所有的num
 * 返回值：Node
 *
 * 注意：如果开头就是num，返回新Head
 *
 * 1、来到第一个不需要删除的位置
 * 情况一：head = null  head != null
 * 2、pre记录上一个有效节点，发现cur等于num，跳过cur，pre的next指针指向cur下一个节点
 * 3、不等于num时，pre来到cur（有效节点）
 * 4、返回head
 *
 * 对数器：
 * 容器实现、LinkedList
 *
 * @author : Darren
 * @date : 2022年05月27日 07:45:35
 **/
public class Code02_DeleteGivenValue {

    // for test
    public static void main(String[] args) {
        int len = 50;
        int value = 100;
        int testTime = 100000;
        System.out.println("test begin!");
        for (int i = 0; i < testTime; i++) {
            //生成一个随机单向链表
            Node node = generateRandomLinkedList(len, value);
            Node node1 = node;
            int num = (int)(Math.random() * (value + 1));
            Node head1 = deleteNum(node, num);
            Node head2 = removeValue(node1, num);
            if (!checkNodeIsEqual(head1, head2)) {
                System.out.println("Oops1!");
            }

        }
        System.out.println("test finish!");

    }

    public static Node deleteNum(Node head, int num){
        //head来到第一个不需要删除的位置
        while (head != null){
            if (head.value != num){
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur != null){
            if (cur.value == num){
                //如果当前值等于num 那么pre的next指向cur的next，即相等就刷新pre的next指针
                pre.next = cur.next;
            }else{
                //如果当前值不等于num，那么pre来到cur位置
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }


    // head = removeValue(head, 2);
    public static Node removeValue(Node head, int num) {
        // head来到第一个不需要删的位置
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }
        // 1 ) head == null
        // 2 ) head != null
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
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

    public static boolean checkNodeIsEqual(Node head1, Node head2){
        if (head1 == null || head2 == null){
            return head1 == head2;
        }
        while (head1 != null && head2 != null){
            if (head1.value == head2.value){
                head1 = head1.next;
                head2 = head2.next;
            }else{
                break;
            }
        }
        if (head1 == null && head2 == null){
            return true;
        }
        return false;
    }
}

