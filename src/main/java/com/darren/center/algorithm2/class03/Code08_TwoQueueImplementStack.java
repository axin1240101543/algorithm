package com.darren.center.algorithm2.class03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * <h3>algorithm</h3>
 * <p></p>
 * 如何用队列结构（只能从头进从尾出）实现栈结构
 *
 * 准备两个队列，一个A一个B：
 * 1、add的时候添加到A；
 * 2、poll的时候将A的数据都add到B，留下最后一个返回；
 * 3、下次添加的时候添加到B；
 * 4、poll的时候将B的数据都add到A，留下最后一个返回；
 * 5、两个队列来回转；﻿
 *
 * @author : Darren
 * @date : 2022年05月31日 08:49:08
 **/
public class Code08_TwoQueueImplementStack {

    public static void main(String[] args) {
        System.out.println("test begin");
        MyStack myStack = new MyStack();
        Stack<Integer> test = new Stack<>();
        int testTime = 1000000;
        int max = 1000000;
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty()) {
                if (!test.isEmpty()) {
                    System.out.println("Oops");
                }
                int num = (int) (Math.random() * max);
                myStack.push(num);
                test.push(num);
            } else {
                if (Math.random() < 0.25) {
                    int num = (int) (Math.random() * max);
                    myStack.push(num);
                    test.push(num);
                } else if (Math.random() < 0.5) {
                    if (!myStack.peek().equals(test.peek())) {
                        System.out.println("Oops");
                    }
                } else if (Math.random() < 0.75) {
                    if (!myStack.pop().equals(test.pop())) {
                        System.out.println("Oops");
                    }
                } else {
                    if (myStack.isEmpty() != test.isEmpty()) {
                        System.out.println("Oops");
                    }
                }
            }
        }

        System.out.println("test finish!");

    }

    public static class MyStack{
        private Queue<Integer> data;
        private Queue<Integer> help;

        public MyStack() {
            this.data = new LinkedList<>();
            this.help = new LinkedList<>();
        }

        public void push(Integer value){
            data.add(value);
        }

        public Integer pop(){
            if (data.isEmpty()){
                throw new RuntimeException("Stack is empty!");
            }
            while (data.size() > 1){
                help.add(data.poll());
            }
            Integer ans = data.poll();
            Queue<Integer> temp = data;
            data = help;
            help = temp;
            return ans;
        }

        public Integer peek(){
            if (data.isEmpty()){
                throw new RuntimeException("Stack is empty!");
            }
            while (data.size() > 1){
                help.add(data.poll());
            }
            Integer ans = data.poll();
            help.add(ans);
            Queue<Integer> temp = data;
            data = help;
            help = temp;
            return ans;
        }

        public boolean isEmpty(){
            return data.isEmpty();
        }
    }

}

