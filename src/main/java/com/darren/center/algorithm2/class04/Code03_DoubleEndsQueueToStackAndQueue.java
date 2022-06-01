package com.darren.center.algorithm2.class04;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * <h3>algorithm</h3>
 * <p></p>
 * 双链表实现队列和栈
 *
 * @author : DarrenReentrantLock
 * @date : 2022年05月27日 08:25:09
 **/
public class Code03_DoubleEndsQueueToStackAndQueue {

    public static void main(String[] args) {
        int oneTestDataNum = 100;
        int value = 10000;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            MyStack<Integer> myStack = new MyStack<>();
            MyQueue<Integer> myQueue = new MyQueue<>();
            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTestDataNum; j++) {
                int nums = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myStack.push(nums);
                    stack.push(nums);
                } else {
                    if (Math.random() < 0.5) {
                        myStack.push(nums);
                        stack.push(nums);
                    } else {
                        if (!isEqual(myStack.pop(), stack.pop())) {
                            System.out.println("oops!");
                        }
                    }
                }
                int numq = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myQueue.add(numq);
                    queue.offer(numq);
                } else {
                    if (Math.random() < 0.5) {
                        myQueue.add(numq);
                        queue.offer(numq);
                    } else {
                        if (!isEqual(myQueue.poll(), queue.poll())) {
                            System.out.println("oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }

    public static class DoubleNode<T> {

        public T value;

        public DoubleNode<T> last;

        public DoubleNode<T> next;

        public DoubleNode(T value) {
            this.value = value;
        }
    }

  public static class DoubleEndQueue<T>{
      DoubleNode<T> head;

      DoubleNode<T> tail;

      public void addHead(T value){
          DoubleNode<T> cur = new DoubleNode<T>(value);
          if (head == null){
              head = cur;
              tail = cur;
          }else{
              head.last = cur;
              cur.next = head;
              head = cur;
          }
      }

      public void addTail(T value){
          DoubleNode<T> cur = new DoubleNode<T>(value);
          if (tail == null){
              head = cur;
              tail = cur;
          }else{
              tail.next = cur;
              cur.last = tail;
              tail = cur;
          }
      }

      public T popHead(){
         if (head == null){
             return null;
         }
         DoubleNode<T> cur = head;
         if (head == tail){
             head = null;
             tail = null;
         }else{
             head = head.next;
             head.last = null;
             cur.next = null;
         }
         return cur.value;
      }

      public T popTail(){
          if (tail == null){
              return null;
          }
          DoubleNode<T> cur = tail;
          if (head == tail){
              head = null;
              tail = null;
          }else{
              tail = tail.last;
              tail.next = null;
              cur.last = null;
          }
          return cur.value;
      }

      public boolean isEmpty(){
          return head == null;
      }
  }

  public static class MyQueue<T>{

      DoubleEndQueue<T> queue;

      public MyQueue() {
          this.queue = new DoubleEndQueue<T>();
      }

      public void add(T value){
          queue.addHead(value);
      }

      public T poll(){
          return queue.popTail();
      }

      public boolean isEmpty(){
          return queue.isEmpty();
      }

  }

    public static class MyStack<T>{

        DoubleEndQueue<T> queue;

        public MyStack() {
            this.queue = new DoubleEndQueue<T>();
        }

        public void push(T value){
            queue.addHead(value);
        }

        public T pop(){
            return queue.popHead();
        }

        public boolean isEmpty(){
            return queue.isEmpty();
        }

    }

    public static boolean isEqual(Integer o1, Integer o2) {
        if (o1 == null && o2 != null) {
            return false;
        }
        if (o1 != null && o2 == null) {
            return false;
        }
        if (o1 == null && o2 == null) {
            return true;
        }
        return o1.equals(o2);
    }



}

