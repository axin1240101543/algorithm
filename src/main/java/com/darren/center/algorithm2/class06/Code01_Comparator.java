package com.darren.center.algorithm2.class06;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2022年06月27日 07:15:07
 **/
public class Code01_Comparator {

    public static void main(String[] args) {
        // 默认小根堆，可以通过比较器变成大根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>(new MyComparator());
        heap.add(5);
        heap.add(5);
        heap.add(5);
        heap.add(3);
        //  5 , 3
        System.out.println(heap.peek());
        heap.add(7);
        heap.add(0);
        heap.add(7);
        heap.add(0);
        heap.add(7);
        heap.add(0);
        System.out.println(heap.peek());
        while(!heap.isEmpty()) {
            System.out.println(heap.poll());
        }
    }

    public static class MyComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }

    }

}

