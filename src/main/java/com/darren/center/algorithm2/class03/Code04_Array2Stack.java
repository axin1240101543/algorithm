package com.darren.center.algorithm2.class03;

import java.util.Stack;

/**
 * <h3>algorithm</h3>
 * <p>数组实现栈</p>
 *
 * @author : Darren
 * @date : 2022年05月30日 08:11:07
 **/
public class Code04_Array2Stack {

    public static void main(String[] args) throws Exception {
        int oneTestDataNum = 100;
        int value = 10000;
        int testTimes = 100000;
        int max = 10;
        for (int i = 0; i < testTimes; i++) {
            MyStack myStack = new MyStack(oneTestDataNum);
            Stack<Integer> stack = new Stack<>();
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
            }
        }
        System.out.println("finish!");
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

   public static class MyStack{

       public Integer[] arr;
       /**
        * 下一个元素的位置 0 ~ limit
        */
       public int next;
       /**
        * 数组长度
        */
       public int limit;

       public MyStack(int limit) {
           this.arr = new Integer[limit];
           this.next = 0;
           this.limit = limit;
       }

        public void push(Integer value) throws Exception{
           if (next == limit){
               throw new Exception("不能添加元素了");
           }
           arr[next++] = value;
        }

        public Integer pop() throws Exception{
           if (next == 0){
               throw new Exception("没有元素弹出了");
           }
           Integer cur = arr[next - 1];
           arr[next-1] = null;
           next--;
           return cur;
        }
   }

}

