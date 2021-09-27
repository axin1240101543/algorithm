package com.darren.center.algorithm.ext;

import java.util.Stack;

/**
 * Author: Darren
 * Date: 2021-09-27 16:59:20
 * Version: 1.0
 * Description:
 * 如何用栈结构实现队列结构
 *
 * 准备两个栈
 * 一个push栈 一个pop栈
 * push栈向pop栈倒入数据
 * 1、每次pop栈为空时，才能倒入数据
 * 2、一次性要把push栈的数据倒完
 *
 * 当添加一个数据时，将数据添加到push栈，然后将push栈的数据全部倒入pop栈
 * 当弹出一个数据时，将push栈的数据全部倒入pop栈，然后弹出pop栈的栈顶
 */
public class StackTransferQueue {

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        myQueue.push(3);

        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
    }

    public static class MyQueue{
        //倒入栈
        private Stack<Integer> stackPush;
        //弹出栈
        private Stack<Integer> stackPop;

        public MyQueue() {
            this.stackPush = new Stack<>();
            this.stackPop = new Stack<>();
        }

        public void push2pop(){
            //弹出栈必须为空才能倒入
            if (stackPop.isEmpty()){
                //倒入栈的数据一次性需要压完
                while (!stackPush.isEmpty()){
                    stackPop.push(stackPush.pop());
                }
            }
        }

        //压入倒入栈 然后将数据倒入弹出栈
        //有两种情况：
        //1、倒入失败 因为弹出栈有数据 数据停留在倒入栈中
        //2、倒入成功 倒入栈清空
        public void push(Integer integer){
            stackPush.push(integer);
            push2pop();
        }

        //弹出栈弹出数据
        //导入数据 因为如果有数据还在倒入栈中  因为offer情况1 导致
        //然后弹出栈顶数据
        public int pop(){
            if (stackPop.isEmpty() && stackPush.isEmpty()){
                return 0;
            }
            push2pop();
            return stackPop.pop();
        }

        public int peek() {
            if (stackPop.isEmpty() && stackPush.isEmpty()){
                return 0;
            }
            push2pop();
            return stackPop.peek();
        }

        public boolean empty() {
            return stackPop.isEmpty() && stackPush.isEmpty();
        }
    }

}
