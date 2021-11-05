package com.darren.center.algorithm.code;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Darren
 * Date: 2021-11-04 11:01:32
 * Version: 1.0
 * Description:
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 *  构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random
 * 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。
 *
 *  例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random
 * --> y 。
 *
 *  返回复制链表的头节点。
 *
 *  用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 *
 *  val：一个表示 Node.val 的整数。
 *  random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为 null 。
 *
 *  你的代码 只 接受原链表的头节点 head 作为传入参数。
 */
public class Problem_0138_CopyListWithRandomPointer {

    public static void main(String[] args) {
        Solution solution = new Problem_0138_CopyListWithRandomPointer().new Solution();
    }

    class Solution {
        /**
         * 单链表加上一个随机指针后，然后复制这个链表
         * 思路：
         * 1、遍历老链表，使用哈希表记录，key为老节点，value为新节点（此时未设置next和random指针）；
         * 2、遍历map，例如a的新节点为a'，a'的next指针应该指向a的next指针指向的下一个节点b的新节点b'，通过map可以查询到b'；
         * 3、同理，random指针一样可以通过步骤2的方式设置；
         * 4、最后返回新链表的head节点。
         * 思路2：
         * 1、遍历老链表，将每个节点的下一个变成它的新节点，即任何一个老节点，它的下一个节点一定在它的下一个（此时未设置next和random指针）；
         * 2、设置新节点的random指针，遍历老链表，每次拿一对，例如a和a'，a'的random指针可以通过a的random指针查到它自己所
         * 指向的节点c，然后这个节点的下一个c'就是a'的random所指向的节点c'。
         * 3、最后next方向上分离老链表和新链表。
         * @param head
         * @return
         */
        public Node copyRandomList(Node head) {
            if (head == null){
                return null;
            }

            Node cur = head;
            Node next = null;
            while (cur != null){
                //原节点的next
                next = cur.next;
                //新节点
                cur.next = new Node(cur.val);
                //新节点的next
                cur.next.next = next;
                cur = next;
            }

            cur = head;
            Node copy = null;
            while (cur != null){
                //原节点的next
                next = cur.next.next;
                //新节点
                copy = cur.next;
                //新节点的random指针
                copy.random = cur.random != null ? cur.random.next : null;
                cur = next;
            }
            Node ans = head.next;
            cur = head;
            while (cur != null){
                //原节点的next
                next = cur.next.next;
                //新节点
                copy = cur.next;
                cur.next = next;
                copy.next = next != null ? next.next : null;
                cur = next;
            }
            return ans;
        }
    }

    public Node copyRandomList(Node head) {

        if (head == null){
            return null;
        }

        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        Node next = null;
        while (cur != null){
            next = cur.next;
            //保存老节点和新节点的映射关系
            map.put(cur, new Node(cur.val));
            cur = next;
        }

        cur = head;
        Node copy = null;
        while (cur != null){
            next = cur.next;
            //拿到新节点
            copy = map.get(cur);
            //新节点的next就是在map中通过老节点的next查询出来的新节点
            copy.next = map.get(cur.next);
            //新节点的random就是在map中通过老节点的random查询出来的新节点
            copy.random = map.get(cur.random);
            cur = next;
        }

        return map.get(head);
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
