package com.darren.center.algorithm2.class03;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <h3>algorithm</h3>
 * <p>数组实现栈</p>
 *
 * @author : Darren
 * @date : 2022年05月30日 08:11:07
 **/
public class Code05_Array2Queue {

    public static void main(String[] args) {
        int oneTestDataNum = 100;
        int value = 10000;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            MyQueue myQueue = new MyQueue(oneTestDataNum);
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTestDataNum; j++) {
                int nums = (int) (Math.random() * value);
                int numq = (int) (Math.random() * value);
                if (queue.isEmpty()) {
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

   public static class MyQueue{
        public Integer[] arr;
       /**
        * 放的index
        */
       public int addIndex;
       /**
        * 拿的index
        */
       public int pollIndex;
       /**
        * 此时数组的长度
        */
       public int size;
       /**
        * 数组长度
        */
        public int limit;

       public MyQueue(int limit) {
           this.arr = new Integer[limit];
           this.addIndex = 0;
           this.pollIndex = 0;
           this.size = 0;
           this.limit = limit;
       }

       public void add(Integer value){
           if (size == limit){
               throw new RuntimeException("队列满了，不能再加了");
           }
           arr[addIndex] = value;
           addIndex = calcNextIndex(addIndex);
           size++;
       }

       public Integer poll(){
           if (size == 0){
               throw new RuntimeException("队列空了，不能再拿了");
           }
           Integer cur = arr[pollIndex];
           pollIndex = calcNextIndex(pollIndex);
           size--;
           return cur;
       }

       //如果已经来到了数组最后的位置 limit - 1，那么回到0位置，否则+1
       public int calcNextIndex(int index){
           return index < limit - 1 ? index + 1 : 0;
       }
   }

}

