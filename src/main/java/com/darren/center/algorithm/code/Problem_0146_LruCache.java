package com.darren.center.algorithm.code;

import java.util.HashMap;

/**
 * Author: Darren
 * Date: 2021-11-16 10:33:45
 * Version: 1.0
 * Description:
 * 146、RLU缓存，Least Recently Used（最近最少使用）
 * 运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 *  LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 *  int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 *  void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上
 * 限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 *  进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 * 思路：
 * 使用一个结构体，结构体最多只能放k条记录
 * 例如：放3条记录
 * abc 7  时间点1
 * bcd 14 时间点2
 * de 23 时间点3
 *
 * 1）put操作和get操作都需要更新时间点
 * 假如此时 get abc，那么需要更新时间，时间点为4
 * 2）当put操作发生时，发现结构体的size大于k，那么移除时间点最早的记录。
 * 假如此时结构体已经有了3条记录，再放入eee 16 时间点5，那么移除bcd 14（因为abc 时间已经更新为4），因为他时间点是最早的
 *
 *  结构体对外提供get、put方法，事件复杂度为O（1），结构设计为hash表 + 双向链表，java已有实现LinkedHashMap（不能用，哈哈哈）
 *  将时间最早的记录放在链表的头部，每次添加新记录都从尾巴添加，当容量不足时，移除头部即可，当发生更新或者获取操作时，将这个记录节点分离出来重新添加到尾巴上
 *
 * 流程：
 * 1）、准备双向链表的节点
 * 2）、准备一个双线链表，提供三个方法
 * 方法一：addNode，添加节点，当发生添加操作时调用
 * 判断链表是否为空，如不为空则直接将head和tail指向新加节点，否则添加到尾巴
 * 方法二：moveNodeToTail，移动某个节点到尾巴，当发生更新或者获取操作时调用
 * 判断链表是否为空，如为空则直接返回
 * 判断node是否为tail，那么直接返回
 * 判断node是否为head，那么需要换头，然后让node成为新的tail
 * 此时node为中间位置，那么分离node，调整前后节点指针，然后让node成为新的tail
 * 方法三：removeHead，移除head，当capacity满了时调用，移除时间最早的节点
 * 判断head是否等于tail，那么此时只有一个节点，返回并将head和tail指向null
 * 如有多个节点，那么换头，让head的下一个节点成为新的head
 * 3）、准备一个结构体，结构体由一个map和一个双向链表组成，提供两个方法
 * 方法一：get方法
 * 判断map中是否存在这个key，如存在那么返回并调用链表的moveNodeToTail，更新时间
 * 方法二：put方法
 * 判断map中是否存在这个key，如果存在那么从map中取出，并更新为新的value，然后调用链表的moveNodeToTail，更新时间
 * 如果map中不存在，那么判断当前容量是否不足，如不足那么调用链表的removeHead，然后移除map中的元素，最后再添加到map中，并调用链表的addNode添加新节点
 */
public class Problem_0146_LruCache {

    public static void main(String[] args) {
        LRUCache solution = new Problem_0146_LruCache().new LRUCache(5);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class LRUCache {

        private MyCache<Integer, Integer> cache;

        public LRUCache(int capacity) {
            cache = new MyCache<Integer, Integer>(capacity);
        }

        public int get(int key) {
            Integer ans = cache.get(key);
            return ans == null ? -1 : ans;
        }

        public void put(int key, int value) {
            cache.put(key, value);
        }

        public class MyCache<K, V>{
            private HashMap<K, Node<K,V>> map;
            private NodeDoubleLinkedList<K, V> linkedList;
            private final int capacity;

            public MyCache(int capacity) {
                if (capacity < 1){
                    throw new RuntimeException("should be more than 0");
                }
                this.map = new HashMap<>();
                this.linkedList = new NodeDoubleLinkedList<>();
                this.capacity = capacity;
            }

            public V get(K key){
                if (map.containsKey(key)){
                    Node<K, V> node = map.get(key);
                    //更新时间
                    linkedList.moveNodeToTail(node);
                    return node.value;
                }
                return null;
            }

            public void put(K key, V value){
                if (map.containsKey(key)){
                    Node<K, V> node = map.get(key);
                    //更新值
                    node.value = value;
                    //更新时间
                    linkedList.moveNodeToTail(node);
                }else{
                    //此时结构体满了，移除链表头并移除hash表中的值
                    if (map.size() == capacity){
                        removeMostUnusedCache();
                    }
                    //添加
                    Node<K, V> node = new Node<>(key, value);
                    map.put(key, node);
                    linkedList.addNode(node);
                }
            }

            /**
             * 此时结构体满了，移除链表头并移除hash表中的值
             */
            private void removeMostUnusedCache(){
                Node<K, V> node = linkedList.removeHead();
                map.remove(node.key);
            }

        }

        /**
         * 双向链表
         * @param <K>
         * @param <V>
         */
        public class NodeDoubleLinkedList<K, V>{
            private Node<K, V> head;
            private Node<K, V> tail;

            public NodeDoubleLinkedList() {
                head = null;
                tail = null;
            }

            /**
             * 添加新节点到尾巴
             * @param node
             */
            public void addNode(Node<K, V> node){
                if (null == node){
                    return;
                }
                if (head == null){
                    //初始化链表
                    head = node;
                    tail = node;
                }else{
                    tail.next = node;
                    node.last = tail;
                    tail = node;
                }
            }

            /**
             * 分离指定节点到尾巴上
             * @param node
             */
            public void moveNodeToTail(Node<K, V> node){
                if (null == node){
                    return;
                }
                //node是尾巴
                if (node == tail){
                    return;
                }
                //node是head
                if (node == head){
                    //换头
                    head = node.next;
                    node.next.last = null;
                }else{
                    //分离
                    node.last.next = node.next;
                    node.next.last = node.last;
                }
                //换尾
                tail.next = node;
                node.last = tail;
                tail = node;
            }

            /**
             * 移除头结点
             * @return
             */
            public Node<K,V> removeHead(){
                if (null == head){
                    return null;
                }
                Node<K, V> node = head;
                if (head == tail){
                    //当链表只有一个节点
                    head = null;
                    tail = null;
                }else{
                    //换头
                    head = head.next;
                    node.next = null;
                    head.last = null;
                }
                return node;
            }
        }


        /**
         * 链表中的节点
         * @param <K>
         * @param <V>
         */
        public class Node<K, V>{
            public K key;
            public V value;
            public Node<K, V> last;
            public Node<K, V> next;

            public Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }
    }

}
