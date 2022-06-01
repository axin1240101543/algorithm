package com.darren.center.algorithm2.class04;

import java.util.Stack;

/**
 * <h3>algorithm</h3>
 * <p></p>
 * 如何用栈结构实现队列结构
 *
 * 准备两个栈，一个push栈，一个pop栈：
 * 如何用队列结构实现栈结构的时候，添加到push栈；
 * pop的时候，将push的数据一次性倒入pop栈，然后从pop弹出；
 *
 * 注意：
 * 1、push栈倒数据到pop栈，必须一次性倒完；
 * 2、pop栈没有被拿完不能倒数据；﻿​
 *
 * @author : Darren
 * @date : 2022年05月31日 08:40:54
 **/
public class Code07_TwoStacksImplementQueue {

    public static void main(String[] args) {
        TwoStacksQueue test = new TwoStacksQueue();
        test.add(1);
        test.add(2);
        test.add(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");

        MyQueue test1 = new MyQueue();
        test1.add(1);
        test1.add(2);
        test1.add(3);
        System.out.println(test1.peek());
        System.out.println(test1.poll());
        System.out.println(test1.peek());
        System.out.println(test1.poll());
        System.out.println(test1.peek());
        System.out.println(test1.poll());
    }

    public static class MyQueue{
        private Stack<Integer> pushStack;
        private Stack<Integer> popStack;

        public MyQueue() {
            this.pushStack = new Stack<>();
            this.popStack = new Stack<>();
        }

        public void add(Integer value){
            pushStack.push(value);
            pushData2PopData();
        }

        public Integer poll(){
            if (pushStack.empty() && popStack.empty()) {
                throw new RuntimeException("Queue is empty!");
            }
            Integer ans = popStack.pop();
            pushData2PopData();
            return ans;
        }

        public Integer peek(){
            if (pushStack.empty() && popStack.empty()) {
                throw new RuntimeException("Queue is empty!");
            }
            Integer ans = popStack.peek();
            pushData2PopData();
            return ans;
        }

        /**
         * 倒数据
         */
        private void pushData2PopData(){
            //pop栈没有被拿完不能倒数据；﻿​
            if (popStack.isEmpty()){
                //push栈倒数据到pop栈，必须一次性倒完；
                while (!pushStack.isEmpty()){
                    popStack.push(pushStack.pop());
                }
            }
        }
    }

    public static class TwoStacksQueue {
        public Stack<Integer> stackPush;
        public Stack<Integer> stackPop;

        public TwoStacksQueue() {
            stackPush = new Stack<Integer>();
            stackPop = new Stack<Integer>();
        }

        // push栈向pop栈倒入数据
        private void pushToPop() {
            if (stackPop.empty()) {
                while (!stackPush.empty()) {
                    stackPop.push(stackPush.pop());
                }
            }
        }

        public void add(int pushInt) {
            stackPush.push(pushInt);
            pushToPop();
        }

        public int poll() {
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stackPop.pop();
        }

        public int peek() {
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stackPop.peek();
        }
    }

}

