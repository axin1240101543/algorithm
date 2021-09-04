package com.darren.center.algorithm.base;

import java.util.Stack;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2021年09月02日 21:05:09
 * 给你一个栈，请你逆序这个栈。不能申请额外的数据结构，只能使用递归函数。如何实现？
 **/
public class Base002_ReverseStack {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        reverse(stack);
        stack.forEach(System.out::println);
    }

    public static void reverse(Stack<Integer> stack){
        //f1 last = 3
        //f2 last = 2
        //f3 last = 1
        //f4 return
        //f3 1 stack.push(1) return
        //f2 2 stack.push(2) return
        //f1 3 stack.push(3) return
        if (stack.isEmpty()){
            return;
        }else{
            int last = f(stack);
            reverse(stack);
            stack.push(last);
        }
    }

    /**
     * 将栈底的元素移除 并返回移除的元素
     * @param stack
     * @return
     */
    public static int f(Stack<Integer> stack){
        //f1 result = 1  f2 = ?
        //f2 result = 2  f3 = ?
        //f3 result = 3  stack.isEmpty
        //f2 result = 2 f3 = 3 stack.push(2) return 3
        //f1 result = 1 f2 = 3 stack.push(1) return 3
        Integer result = stack.pop();
        if(stack.isEmpty()){
            return result;
        }else{
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }

}

