package com.darren.center.algorithm2.class05;

import com.darren.center.algorithm2.class01.LogarithmMachine;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 * 荷兰国旗问题
 *
 * 题目：
 * 给定一个数组arr，和一个整数num。请把小于等于num的数放在数组左边，大于num的数放在数组的右边。
 * 要求时间复杂度O（N），额外空间复杂度（1）
 *
 * 给定数玩partition流程：
 * 1）将整个数组左边划分为小于等于区域，下标初始值为-1
 * 2）当i位置的数小于等于num时，当前数arr[i]与小于等于区域下一个数交换，小于等于区右扩，i++
 * 3）当i位置的数大于num时，i++
 *
 * [5,3,7,2,3,4,1] i=0 a=-1 num=3（a:左边界）
 *  0 1 2 3 4 5 6
 * 5 > 3  i=1 a=-1
 *
 * 1 < 3  arr[1] swap arr[0] i=2 a=0
 *
 * [3,5,7,2,3,4,1]
 *  0 1 2 3 4 5 6
 * 7 > 3  i=3 a=0
 *
 * 2 < 3  arr[3] swap arr[1] i=4 a=1
 *
 * [3,2,7,5,3,4,1]
 *  0 1 2 3 4 5 6
 * 3 = 3 arr[4] swap arr[2] i=5 a=2
 *
 * [3,2,3,5,7,4,1]
 *  0 1 2 3 4 5 6
 * 4 > 3 i=6 a=2
 *
 * 1 < 3 arr[6] swap arr[3] i=6 a=3
 *
 * [3,2,3,1,7,4,5]
 *  0 1 2 3 4 5 6
 *        a
 * 小于等于3的在左边，大于3的在右边
 *
 * 加强版：
 * 题目：
 * 给定一个数组arr，和一个整数num。请把小于num的数放在数组左边，等于num的数放在中间，大于num的数放在数组的右边。
 * 要求时间复杂度O（N），额外空间复杂度（1）
 *
 * partition流程：
 * 1）将整个数组左边划分为小于区域，下标初始值为-1，右边花费为大于区域，下标初始值为r+1
 * 2）当i位置的数小于num时，当前数arr[i]与小于区域下一个数交换，小于区右扩，i++
 * 3) 当i位置的数大于num时，当前数arr[i]与大于区域上一个数交换，大于区左扩，i原地不变（因为此时i位置的数没有看过）
 * 4）当i位置的数等于num时，i++
 *
 * [5,3,7,2,3,4,1] i=0 a=-1 b=7 num=3(a:左边界  b:右边界)
 *  0 1 2 3 4 5 6
 * 5 > 3  arr[0] swap arr[6] i=0 a=-1 b=6
 *
 * [1,3,7,2,3,4,5]
 *  0 1 2 3 4 5 6
 * 1 < 3 arr[0] swap arr[0] i=1 a=0 b=6
 *
 * [1,3,7,2,3,4,5]
 *  0 1 2 3 4 5 6
 *  3 = 3 i=2 a=0 b=6
 *
 * [1,3,7,2,3,4,5]
 *  0 1 2 3 4 5 6
 * 7 > 3 arr[2] swap arr[5] i=2, a=0, b=5
 *
 * [1,3,4,2,3,7,5]
 *  0 1 2 3 4 5 6
 * 4 > 3 arr[2] swap arr[4] i=2 a=0 b=4
 *
 * [1,3,3,2,4,7,5]
 *  0 1 2 3 4 5 6
 * 3 == 3 i=3 a=0 b=4
 *
 * [1,3,3,2,4,7,5]
 *  0 1 2 3 4 5 6
 *  2 < 3 arr[3] swap arr[1] i=4 a=1 b=4
 *
 * [1,2,3,3,4,7,5]
 *  0 1 2 3 4 5 6
 *    a     b
 * 小于3的在左边，等于3的在中间，大于3的在右边
 *
 * @author : Darren
 * @date : 2022年06月16日 22:31:02
 **/
public class Code02_Partition {

    public static void main(String[] args) {
        int count = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean success = true;
        for (int i = 0; i < count; i++) {
            int[] var1 = LogarithmMachine.generateRandomArray(maxSize, maxValue);
            int[] var2 = LogarithmMachine.copyArray(var1);
            int ans1 = partition1(var1, 0, var1.length - 1);
            int ans2 = partition1Teacher(var2, 0, var2.length - 1);
            if (ans1 != ans2){
                success = false;
                LogarithmMachine.printArray(var1);
                LogarithmMachine.printArray(var2);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println(success ? "success" : "failure");

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        boolean success2 = true;
        for (int i = 0; i < count; i++) {
            int[] var1 = LogarithmMachine.generateRandomArray(maxSize, maxValue);
            int[] var2 = LogarithmMachine.copyArray(var1);
            int[] ans1 = partition2(var1, 0, var1.length - 1);
            int[] ans2 = netherlandsFlag2Teacher(var2, 0, var2.length - 1);
            if (!LogarithmMachine.isEqual(ans1, ans2)){
                success2 = false;
                LogarithmMachine.printArray(var1);
                LogarithmMachine.printArray(var2);
                LogarithmMachine.printArray(ans1);
                LogarithmMachine.printArray(ans2);
                break;
            }
        }
        System.out.println(success2 ? "success" : "failure");
    }

    public static int partition1(int[] arr, int l, int r){
        //无效范围
        if (l > r){
            return -1;
        }
        //只有一个数 返回l
        if (l == r){
            return l;
        }
        // 小于等于区域的右边界
        int leftBoundary = l - 1;
        int index = l;
        //当index到r-1结束
        while (index < r){
            //以arr[r]作为划分值
            // * 1）将整个数组左边划分为小于等于区域，下标初始值为-1
            // * 2）当i位置的数小于等于num时，当前数arr[i]与小于等于区域下一个数交换，小于等于区右扩，i++
            // * 3）当i位置的数大于num时，i++
            if (arr[index] <= arr[r]){
                swap(arr, index, ++leftBoundary);
            }
            index++;
        }
        //最后将arr[r]交换到小于等于区域的下一个位置
        swap(arr, r, ++leftBoundary);
        //返回arr[r]在的位置
        return leftBoundary;
    }


    public static int[] partition2(int[] arr, int l, int r) {
        //无效范围
        if (l > r){
            return new int[]{-1, -1};
        }
        //只有一个数 返回[l, r]
        if (l == r){
            return new int[]{l, r};
        }
        // 小于区域的右边界
        int leftBoundary = l - 1;
        // 大于区域的左边界
        int rightBoundary = r;
        int index = l;
        //当index到右边界-1结束
        while (index < rightBoundary){
            //以arr[r]作为划分值
            // * 1）将整个数组左边划分为小于区域，下标初始值为-1，右边花费为大于区域，下标初始值为r+1
            // * 2）当i位置的数小于num时，当前数arr[i]与小于区域下一个数交换，小于区右扩，i++
            // * 3) 当i位置的数大于num时，当前数arr[i]与大于区域上一个数交换，大于区左扩，i原地不变（因为此时i位置的数没有看过）
            // * 4）当i位置的数等于num时，i++
            if (arr[index] < arr[r]){
                swap(arr, index++, ++leftBoundary);
            }else if (arr[index] > arr[r]){
                swap(arr, index, --rightBoundary);
            }else{
                index++;
            }
        }
        //最后将arr[r]交换到大于区域第一个位置
        swap(arr, r, rightBoundary);
        return new int[]{leftBoundary + 1, rightBoundary};
    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // arr[L..R]上，以arr[R]位置的数做划分值
    // <= X > X
    // <= X X
    public static int partition1Teacher(int[] arr, int L, int R) {
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        int lessEqual = L - 1;
        int index = L;
        while (index < R) {
            if (arr[index] <= arr[R]) {
                swap(arr, index, ++lessEqual);
            }
            index++;
        }
        swap(arr, ++lessEqual, R);
        return lessEqual;
    }

    // arr[L...R] 玩荷兰国旗问题的划分，以arr[R]做划分值
    // <arr[R] ==arr[R] > arr[R]
    public static int[] netherlandsFlag2Teacher(int[] arr, int L, int R) {
        if (L > R) { // L...R L>R
            return new int[] { -1, -1 };
        }
        if (L == R) {
            return new int[] { L, R };
        }
        int less = L - 1; // < 区 右边界
        int more = R; // > 区 左边界
        int index = L;
        while (index < more) { // 当前位置，不能和 >区的左边界撞上
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] < arr[R]) {
//				swap(arr, less + 1, index);
//				less++;
//				index++;
                swap(arr, index++, ++less);
            } else { // >
                swap(arr, index, --more);
            }
        }
        swap(arr, more, R); // <[R]   =[R]   >[R]
        return new int[] { less + 1, more };
    }

}

