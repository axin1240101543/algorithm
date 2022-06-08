package com.darren.center.algorithm2.class04;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *  逆序对：
 *
 * 左边比右边大的数，有几对
 *
 * 3，1，0，4，3，1
 *
 * 3：31 30 31
 *
 * 1：10
 *
 * 0：
 *
 * 4：43 41
 *
 * 3：31
 *
 * 1：
 *
 * 总共：3 + 1 + 2 + 1 = 7
 *
 * 解释：就是求右组有多少个数比左组的当前数小
 *
 * merge过程：
 * 1 2 3 6 2 4 4 6
 *       p1      p2
 * 从右往左进行判断，填数也是从右往左
 * 6等于6，不产生逆序对，先拷贝右组6
 * 1 2 3 6 2 4 4 6
 *       p1    p2
 * 6
 * ------
 * 6大于4，产生逆序对，拷贝6，4左边有多少个数比它小，p2 - m = 6 - 3 = 3
 * 64 64 62
 * 1 2 3 6 2 4 4 6
 *     p1      p2
 * 6 6
 * ------
 * 1 2 3 6 2 4 4 6
 *     p1      p2
 * 3小于4，不产生逆序对，拷贝4
 * 4 6 6
 * ------
 * 1 2 3 6 2 4 4 6
 *     p1    p2
 * 3小于4，不产生逆序对，拷贝4
 * 4 4 6 6
 * ------
 * 1 2 3 6 2 4 4 6
 *     p1  p2
 * 3大于2，产生逆序对，拷贝3，2左边有多少个数比它小，p2 - m = 4 - 3 = 1
 * 32
 * 3 4 4 6 6
 * ------
 * 1 2 3 6 2 4 4 6
 *   p1    p2
 * 2等于2，不产生逆序对，先拷贝右2
 * 2 3 4 4 6 6
 * ------
 * 右组越界
 * 将左组剩余的1 2拷贝下来
 * 1 2 2 3 4 4 6 6
 *
 * 总结：
 * 逆序对就是找右组中有多少个数比它小，然后从小的数开始往左边算
 * 1、左组比右组小，没有逆序对
 * 2、左组和右组相等，拷贝右组，找到比它小的位置，然后再判断有多少个数比它小
 * 3、左组比右组大，判断有多少个数比它小
 *
 * @author : Darren
 * @date : 2022年06月07日 08:36:07
 **/
public class Code03_ReversePair {

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (reversePair(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

    public static int reversePair(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int l, int r){
        if (l == r){
            return 0;
        }
        int m = l + ((r - l) >> 1);
        return process(arr, l, m) + process(arr, m + 1, r) + merge(arr, l, m, r);
    }

    public static int merge(int[] arr, int l, int m, int r){
         int[] help = new int[r - l + 1];
         int i = help.length - 1;
         int p1 = m;
         int p2 = r;
         int ans = 0;
         while (p1 >= l && p2 >= m + 1){
            ans += arr[p1] > arr[p2] ? (p2 - m) : 0;
            help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
         }
        while (p1 >= l){
            help[i--] = arr[p1--];
        }
        while (p2 >= m + 1){
            help[i--] = arr[p2--];
        }
        for (int j = 0; j < help.length; j++){
            arr[l + j] = help[j];
        }
        return ans;
    }

    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    ans++;
                }
            }
        }
        return ans;
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

