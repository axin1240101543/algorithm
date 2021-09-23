package com.darren.center.algorithm.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Author: Darren
 * Date: 2021-09-23 18:28:48
 * Version: 1.0
 * Description:
 */
public class Problem_056_MergeIntervals {

    public static void main(String[] args) {
        Solution solution = new Problem_056_MergeIntervals().new Solution();
    }

    class Solution {

        public class A{
            public int start;
            public int end;

            public A(int start, int end) {
                this.start = start;
                this.end = end;
            }
        }

        public class C implements Comparator<A> {
            @Override
            public int compare(A o1, A o2){
                return o1.start - o2.start;
            }
        }

        public int[][] merge(int[][] intervals) {
            A[] a = new A[intervals.length];
            for (int i = 0; i < intervals.length; i++) {
                a[i] = new A(intervals[i][0], intervals[i][1]);
            }

            Arrays.sort(a, new C());

            int s = a[0].start;
            int e = a[0].end;

            List<A> ans = new ArrayList<>();
            for (int i = 1; i < a.length; i++) {
                if (a[i].start > e){
                    ans.add(new A(s, e));
                    s = a[i].start;
                    e = a[i].end;
                }else{
                    e = Math.max(e, a[i].end);
                }
            }

            ans.add(new A(s, e));

            int[][] res = new int[ans.size()][2];
            for (int i = 0; i < ans.size(); i++) {
                res[i][0] = ans.get(i).start;
                res[i][1] = ans.get(i).end;
            }
            return res;
        }

        //有问题 没有排序的话 无法做到 要根据start进行排序
        //[[1,4],[0,4]]
        public int[][] merge1(int[][] intervals) {
            List<List<Integer>> ans = new ArrayList<>();
            int s = intervals[0][0];
            int e = intervals[0][1];
            for (int i = 1; i < intervals.length; i++) {
                if (intervals[i][0] > e){
                    List<Integer> cur = new ArrayList<>();
                    cur.add(s);
                    cur.add(e);
                    ans.add(cur);
                    s = intervals[i][0];
                    e = intervals[i][1];
                }else{
                    //[[1,4],[2,3]]
                    e = Math.max(e, intervals[i][1]);
                }
            }

            List<Integer> cur = new ArrayList<>();
            cur.add(s);
            cur.add(e);
            ans.add(cur);

            int[][] res = new int[ans.size()][2];
            for (int i = 0; i < ans.size(); i++) {
                res[i][0] = ans.get(i).get(0);
                res[i][1] = ans.get(i).get(1);
            }
            return res;
        }
    }

}
