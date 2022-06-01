package com.darren.center.algorithm2.class04;

import java.util.Stack;

/**
 * <h3>algorithm</h3>
 * <p></p>
 * 实现一个特殊栈，在基本功能的基础上，再实现返回栈中最小元素的功能
 *
 * 要求：
 * 1、pop、push、getMin操作的时间复杂度都是O（1）；
 * 2、设计的栈类型可以使用现成的栈结构；
 *
 * 1、准备两个栈，一个数据栈，最小栈；
 * 2、压入时，数据栈直接压，最小栈判断当前数是否小于最小栈栈顶的数，如果小，压入当前数，否则，压入最小栈栈顶的数，同步压入；
 * 3、弹出时，同步弹出；﻿​
 *
 * @author : Darren
 * @date : 2022年05月31日 07:05:25
 **/
public class Code06_GetMinStack {

    public static void main(String[] args) {
        MyStack stack1 = new MyStack();
        stack1.push(3);
        System.out.println(stack1.getMin());
        stack1.push(4);
        System.out.println(stack1.getMin());
        stack1.push(1);
        System.out.println(stack1.getMin());
        System.out.println(stack1.pop());
        System.out.println(stack1.getMin());

        System.out.println("=============");

        MyStack1 stack2 = new MyStack1();
        stack2.push(3);
        System.out.println(stack2.getmin());
        stack2.push(4);
        System.out.println(stack2.getmin());
        stack2.push(1);
        System.out.println(stack2.getmin());
        System.out.println(stack2.pop());
        System.out.println(stack2.getmin());
    }

    public static class MyStack{
        private Stack<Integer> dataStack;
        private Stack<Integer> minStack;

        public MyStack() {
            this.dataStack = new Stack<>();
            this.minStack = new Stack<>();
        }

        public void push(Integer value){
            dataStack.push(value);
            if (minStack.size() == 0){
                minStack.push(value);
            }else{
                Integer minHead = minStack.peek();
                if (minHead.compareTo(value) >= 0){
                    minStack.push(value);
                }else{
                    minStack.push(minHead);
                }
            }
        }

        public Integer pop(){
            if (minStack.size() == 0){
                throw new RuntimeException("栈为空");
            }
            minStack.pop();
            return dataStack.pop();
        }

        public Integer getMin(){
            if (minStack.size() == 0){
                throw new RuntimeException("栈为空");
            }
            return minStack.peek();
        }
    }

    public static class MyStack1 {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack1() {
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        public void push(int newNum) {
            if (this.stackMin.isEmpty()) {
                this.stackMin.push(newNum);
            } else if (newNum <= this.getmin()) {
                this.stackMin.push(newNum);
            }
            this.stackData.push(newNum);
        }

        public int pop() {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            int value = this.stackData.pop();
            if (value == this.getmin()) {
                this.stackMin.pop();
            }
            return value;
        }

        public int getmin() {
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return this.stackMin.peek();
        }
    }

}

