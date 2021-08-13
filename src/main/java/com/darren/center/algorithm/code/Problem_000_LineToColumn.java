package com.darren.center.algorithm.code;

public class Problem_000_LineToColumn {

    public static void main(String[] args) {
        int[][] nums = {{ 1, 2, 3}, {4,5,6}, {7,8,9} };
        int[][] solution = solution(nums);
        System.out.println();
    }

    private static int[][] solution(int[][] nums){
        if (nums == null || nums.length <= 0){
            return null;
        }
        int[] c1 = new int[3];
        int[] c2 = new int[3];
        int[] c3 = new int[3];
        for (int i = 0; i < nums.length; i++){
            int[] temp = nums[i];
            int a = 0;
            c1[i] = temp[a];
            c2[i] = temp[++a];
            c3[i] = temp[++a];
        }
        int[][] result = {c1, c2, c3};
        return result;
    }
}
