package com.darren.center.algorithm.ext;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Darren
 * Date: 2021-09-27 17:44:22
 * Version: 1.0
 * Description:
 * 如何用队列结构实现栈结构
 *
 * 准备两个队列
 * 队列 queue 辅助队列 help
 *
 * 当添加的时候 添加到queue中
 * 当弹出的时候 将queue的数据依次添加到help 当只有一个数据时，弹出，然后将help数组中的数据添加到queue中
 */
public class QueueTransferStack {

    class MyStack2{

        private Queue<Integer> queue;
        private Queue<Integer> help;

        public MyStack2() {
            this.queue = new LinkedList<>();
            this.help = new LinkedList<>();
        }

        //void push(int x) 将元素 x 压入栈顶。
        public void push(int x) {
            queue.offer(x);
        }

        //int pop() 移除并返回栈顶元素。
        public int pop() {
            int cur = 0;
            //不断的将queue的数据放入help中 并抓一下最后一个元素
            while (!queue.isEmpty()){
                if (queue.size() == 1){
                    cur = queue.poll();
                }else {
                    help.offer(queue.poll());
                }
            }
            //然后将help里面的元素拷贝到queue中
            while (!help.isEmpty()){
                queue.offer(help.poll());
            }
            return cur;
        }

        //int top() 返回栈顶元素。
        public int top() {
            int cur = 0;
            //不断的将queue的数据放入help中 并抓一下最后一个元素
            while (!queue.isEmpty()){
                if (queue.size() == 1){
                    cur = queue.poll();
                    //弹出之后 然后加入到help里面去
                    help.offer(cur);
                }else {
                    help.offer(queue.poll());
                }
            }
            //然后将help里面的元素拷贝到queue中
            while (!help.isEmpty()){
                queue.offer(help.poll());
            }
            return cur;
        }

        //boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
        public boolean empty() {
            return queue.isEmpty();
        }
    }

    class MyStack1{

        private Queue<Integer> queue;
        private Queue<Integer> help;

        public MyStack1() {
            this.queue = new LinkedList<>();
            this.help = new LinkedList<>();
        }

        public void push(int x) {
            queue.offer(x);
        }

        public int pop() {
            //不断的将queue的数据放入help中 只剩下一个需要弹出的
            while (queue.size() > 1){
                help.offer(queue.poll());
            }
            //弹出 并 抓住这个元素
            Integer cur = queue.poll();
            //然后将help里面的元素拷贝到queue中
            while (!help.isEmpty()){
                queue.offer(help.poll());
            }
            return cur;
        }

        public int top() {
            while (queue.size() > 1){
                help.offer(queue.poll());
            }
            Integer cur = queue.poll();
            //弹出之后 然后加入到help里面去
            help.offer(cur);
            while (!help.isEmpty()){
                queue.offer(help.poll());
            }
            return cur;
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }

    class MyStack {

        private Queue<Integer> queue;
        private Queue<Integer> help;

        public MyStack() {
            this.queue = new LinkedList<>();
            this.help = new LinkedList<>();
        }

        public void push(int x) {
            queue.offer(x);
        }

        public int pop() {
            //不断的将queue的数据放入help中 只剩下一个需要弹出的
            while (queue.size() > 1){
                help.offer(queue.poll());
            }
            //弹出 并 抓住这个元素
            Integer cur = queue.poll();
            //然后将help里面的元素拷贝到queue中
            Queue tem = queue;
            queue = help;
            help = tem;
            return cur;
        }

        public int top() {
            while (queue.size() > 1){
                help.offer(queue.poll());
            }
            Integer cur = queue.poll();
            //弹出之后 然后加入到help里面去
            help.offer(cur);
            Queue tem = queue;
            queue = help;
            help = tem;
            return cur;
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }

}
