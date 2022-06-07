package com.darren.center.algorithm2.class04;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 *  小和问题：
 * 每一个数左边比它小的数都累加起来
 *
 * 4，2，1，3，0，6，3，9
 * merge过程中进行累加
 * --------------------
 * 4，2
 * 4比2小，不产生小和
 * 2，4
 * --------------------
 * 1，3
 * p1 p2
 * 1比3小，产生小和，下标换算，p2 + 1 .... r的个数
 * r - p2 + 1 =   1 - 1 + 1 = 1
 * ans += 1个1 = 1
 * 1，3
 * --------------------
 * 2，4，1，3
 * p1    p2
 * -----
 * 2比1大，不产生小和，拷贝1
 * 2，4，1，3
 * p1       p2
 * -----
 * 2比3小，产生小和，拷贝2，下标换算，p2 + 1 .... r的个数
 * r - p2 + 1 =  3 - 3 + 1 = 1
 * ans += 1个2 = 1 + 2 = 3
 * 2，4，1，3
 *    p1    p2
 * -----
 * 4比3大，不产生小和，拷贝3
 * 最后把4，拷贝下来
 * 1 2 3 4
 * --------------------
 * 0 6 3 9
 * 同理：
 * ans += 一个3 = 3 + 3 = 6
 * ans += 一个9 = 6 + 9 = 15
 * 0 3 6 9
 * --------------------
 * 1 2 3 4 0 3 6 9
 * p1      p2
 * 1比0大，不产生小和，拷贝0
 * 1 2 3 4 0 3 6 9
 * p1        p2
 * ------
 * 1比3小，产生小和，拷贝1，7 - 5 + 1 = 2 + 1 = 3
 * ans += 3个1 = 15 + 3 = 18
 * 1 2 3 4 0 3 6 9
 *   p1      p2
 * ------
 * 2比3小，产生小和，拷贝2，7 - 5 + 1 = 2 + 1 = 3
 * ans += 3个2 = 18 + 6 = 24
 * 1 2 3 4 0 3 6 9
 *     p1    p2
 * ------
 * 3和3相等，拷贝右组的3，p2来到6的位置
 * 1 2 3 4 0 3 6 9
 *     p1      p2
 * ------
 * 3比6小，产生小和，拷贝3，7 - 6 + 1 = 2
 * ans += 2个3 = 24 + 6 = 30
 * 1 2 3 4 0 3 6 9
 *       p1    p2
 * ------
 * 4比6小，产生小和，拷贝4，7 - 6 + 1 = 1 + 1 = 2
 * ans += 2个4 = 30 + 8 = 38
 * 1 2 3 4 0 3 6 9
 *       p1    p2
 * ------
 * 然后左组越界，将剩下的6、9拷贝
 * 0 1 2 3(右组) 3（左组） 4 6 9
 *
 * 总结：
 * 找一个数左边有多少个数比它小，转换思想：
 * 当前这个数，有多少个数比它大，那么就加几个它
 * 也就是说，左组中的某个数，在右组中有多少个数比它大
 * 1、左组比右组小，那么从右组下标开始的往右的数，全部比它大，累加几个它
 * 2、相等时，先拷贝右组，找到一个比它大的数，然后再判断右组还有多少个数比它大
 * 3、左组比右组大，右组没有比它大的数，不产生小和
 *
 * 为什么这么干？
 * 因为左右组是有序的
 *
 * @author : Darren
 * @date : 2022年06月07日 08:21:07
 **/
public class Code02_SmallSum {

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (smallSum(arr1) != comparator(arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

    public static int smallSum(int[] arr){
        if (arr == null || arr.length < 1){
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int l, int r){
        if (l == r){
            return 0;
        }
        int m = l + ((r - l) >> 1);
        return process(arr, l, m) +
        process(arr, m + 1, r) +
        merge(arr, l, m, r);
    }

    public static int merge(int[] arr, int l, int m, int r){
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        int ans = 0;
        while (p1 <= m && p2 <= r){
            ans += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m){
            help[i++] = arr[p1++];
        }
        while (p2 <= r){
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++){
            arr[l + j] = help[j];
        }
        return ans;
    }

    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}

