package com.darren.center.algorithm2.class07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <h3>algorithm</h3>
 * <p></p>
 * 1、最大线段重合问题（用堆的实现）
 * 给定很多线段，每个线段都有每个数[start，end]，表示线段开始位置和结束位置，
 * 左右都是闭区间。
 *
 * 规定：
 * 1）线段的开始和结束位置一定都是整数值
 * 2）线段的重合区域的长度必须>=1
 * 返回线段最多重合区域中，包含了几条线段
 *
 * 解法1：
 * 1、找到所有线段中的start的最小值min和end的最大值max
 * 2、然后去递增1.5（不能是整数，整数会重合，不算重合区域），每个点求一个答案，其中有一个正确答案。
 * 时间复杂度：O（（max - min）* N）
 * 如果数据量为N^4 min和max的范围为100，那么上面这个时间复杂度的算法肯定能过
 *
 * 解法2：
 * 1、线段的开始位置从小到大排序
 * 2、求穿过每个线段的开始位置的有多少条线段
 * 准备一个小根堆，如果堆里面不为空，那么将小于等于开始位置的值全部弹出（为什么弹出，因为小根堆里面存的是开始位置小于等于当前线段的之前线段的结束值，如果结束值小于等于当前线段的开始位置，那么肯定不能作为这个线段的答案，穿不过这个线段）
 * 然后将此时这个线段的结束位置放入小根堆，此时堆的大小就是以当前线段开始的答案
 * 3、每个线段求一个答案，得到max
 *
 * @author : Darren
 * @date : 2022年07月13日 08:30:26
 **/
public class Code01_CoverMax {

    public static void main(String[] args) {

        /*Line l1 = new Line(4, 9);
        Line l2 = new Line(1, 4);
        Line l3 = new Line(7, 15);
        Line l4 = new Line(2, 4);
        Line l5 = new Line(4, 6);
        Line l6 = new Line(3, 7);

        // 底层堆结构，heap
        PriorityQueue<Line> heap = new PriorityQueue<>(new StartComparator());
        heap.add(l1);
        heap.add(l2);
        heap.add(l3);
        heap.add(l4);
        heap.add(l5);
        heap.add(l6);

        while (!heap.isEmpty()) {
            Line cur = heap.poll();
            System.out.println(cur.start + "," + cur.end);
        }*/

        System.out.println("test begin");
        int N = 100;
        int L = 0;
        int R = 200;
        int testTimes = 200000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            int ans1 = maxCover1(lines);
            int ans2 = maxCover2(lines);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
    }

    /**
     * 时间复杂度： O（（max - min）* N）
     * @param lines
     * @return
     */
    public static int maxCover1(int[][] lines){
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        // 找到所有线段中的start的最小值min和end的最大值max
        for (int i = 0; i < lines.length; i++){
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }

        int ans = 0;
        // 为什么要+0.5
        // +0.3， +0.8，都是可行的，因为重合区域的长度是整数，这个点不能是整数，否则没有覆盖这个点（穿过这个点）
        for (double i = min + 0.5; i < max; i = i + 1){
            int cur = 0;
            for (int j = 0; j < lines.length; j++){
                // 当前线段是覆盖了这个点（穿过这个点）
                if (lines[j][0] < i && lines[j][1] > i){
                    cur++;
                }
            }
            // 每个点求一个答案
            ans = Math.max(ans, cur);
        }
        return ans;
    }

    /**
     * 时间复杂度：O（N* logN）
     * @param lines
     * @return
     */
    public static int maxCover2(int[][] lines){
        // 将对象封装成一个对象
        Line[] newLines = new Line[lines.length];
        for (int i = 0; i < lines.length; i++){
            newLines[i] = new Line(lines[i][0], lines[i][1]);
        }

        // 线段的开始位置从小到大排序
        Arrays.sort(newLines, (o1, o2) -> {
            return o1.start - o2.start;
        });

        // 求穿过每个线段的开始位置的有多少条线段
        // 准备一个小根堆，如果堆里面不为空，那么将小于等于开始位置的值全部弹出
        // （为什么弹出，因为小根堆里面存的是开始位置小于等于当前线段的之前线段的结束值，
        // 如果结束值小于等于当前线段的开始位置，那么肯定不能作为这个线段的答案，穿不过这个线段）
        // 然后将此时这个线段的结束位置放入小根堆，此时堆的大小就是以当前线段开始的答案
        int ans = 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>(lines.length);
        for (int i = 0; i < newLines.length; i++){
            while (!heap.isEmpty() && heap.peek() <= newLines[i].getStart()){
                heap.poll();
            }
            heap.add(newLines[i].getEnd());
            ans = Math.max(ans, heap.size());
        }
        return ans;
    }

    public static class Line{
        private int start;
        private int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }
    }

    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }

}

