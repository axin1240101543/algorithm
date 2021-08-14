package com.darren.center.algorithm.code;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2021年08月14日 13:35:42
 **/
public class Problem_004_MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        Solution solution = new Problem_004_MedianOfTwoSortedArrays().new Solution();
        /*for (int i = 0; i < 1000000; i++){
            int num = (int)(Math.random()*10 + 3);
            int[] a = solution.generateArray(num);
            int[] b = solution.generateArray(num);

            int[] a1 = solution.bubbleSort(a);
            int[] b1 = solution.bubbleSort(b);

            //使用排序最好的情况是O(N*logN) 此处我用的是冒泡  O(N^2)
            int[] mArr = solution.sumArray(a1, b1);
            int[] ints = solution.bubbleSort(mArr);
            int f2 = solution.f2(ints);
            int f = solution.f(a1, 0, num - 1, b1, 0, num - 1);
            if (f != f2){
                System.out.println("报错了~~~");
            }
        }*/
        int[] nums1 = {};
        int[] nums2 = {3};
        solution.findMedianSortedArrays(nums1, nums2);
    }
    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int size = nums1.length + nums2.length;
            boolean flag = (size % 2) == 0;
            if (nums1.length != 0 && nums2.length != 0){
                int g1 = g(nums1, nums2, size / 2);
                int g2 = g(nums1, nums2, size / 2 + 1);
                if (flag){
                    return (double)(g1 + g2) / 2;
                }else {
                    return (double)g2;
                }
            }else if(nums1.length != 0){
                int a = nums1[size / 2];
                int b = nums1[(size - 1) / 2];
                if (flag){
                    return (double) (a + b) / 2;
                }else {
                    return (double)b;
                }
            }else if(nums2.length != 0){
                int a = nums2[size / 2];
                int b = nums2[(size - 1) / 2];
                if (flag){
                    return (double)(a + b) / 2;
                }else {
                    return (double)b;
                }
            }
            return 0;
        }

        /**
         * 两个有序不一定等长数组，返回第k小的数
         * @param a 数组1
         * @param b 数组2
         * @param k 第k小
         * @return
         * 时间复杂度为O(log(短数组的长度))
         */
        private int g(int[] a, int[]b, int k){
            int[] longs = a.length >= b.length ? a : b;
            int[] shorts = a.length < b.length ? a : b;
            int l = longs.length;
            int s = shorts.length;
            if (k <= s){
                return f(shorts, 0, k - 1, longs, 0, k - 1);
            }
            if (s < k && k <= l){
                //短数组都有可能
                //判断长数组中最有可能的位置并返回
                if (longs[k - s - 1] >= shorts[s - 1]){
                    return longs[k - s -1];
                }
                return f(shorts, 0, s - 1, longs, k - s, k - 1);
            }
            //k 大于 l 的情况
            if (shorts[k - l -1] >= longs[l - 1]){
                return shorts[k - l - 1];
            }
            if (longs[k - s - 1] >= shorts[s - 1]){
                return longs[k - s - 1];
            }
            return f(shorts, k - l, s - 1, longs, k - s, l - 1);
        }

        /**
         * 两个有序且等长的数组，取出上中位数
         * 等长：(e1 - s1) = (e2 - s2)
         * @param a 数组1
         * @param s1 数组1的开始位置
         * @param e1 数组1的结束位置
         * @param b 数组2
         * @param s2 数组2的开始位置
         * @param e2 数组2的结束位置
         * @return 返回上中位数
         * 时间复杂度为O(log(N))
         *
         * 假如是偶数：
         * [1,2,3,4] 1234表示下标
         * [1',2',3',4'] 1'2'3'4'表示下标
         * if 2 == 2'
         * 则   2或者2'是第四小的数
         * if 2 >= 2'
         * 则分析每个值是第四小的数的可能性
         * 1 yes 3' < 1 < 4'
         * 2 yes 2' < 2 < 3'
         * 3 no 因为 2 < 3 && 1 < 2 && 1' < 2' < 2 超过4个
         * 4 no 和3同理
         * 1' no 1
         * 2' no 1 1'
         * 3' yes 2 < 3' < 3
         * 4' yes 1 < 4' < 2
         *
         * [1,2]
         * [3',4']
         * 同理判断：
         * if 1 == 3'
         *  则 1或者3'是第二小的数
         * if 1 > 3'
         * 1 yes 1 > 3'
         * 2 no 2 > 1 > 3'
         * 3' no
         * 4' yes 3' < 4' < 1
         *
         * [1]
         * [4']
         * 哪个更接近3'，即哪个更小就是第二小的数
         *
         * 假如是奇数：
         * [1,2,3,4，5] 12345表示下标
         * [1',2',3',4'，5'] 1'2'3'4'5'表示下标
         * if 3 == 3'
         * 则 3或者3'是第五小的数
         *
         * if 3 > 3'
         * 1 yes 4' < 1 < 5'
         * 2 yes 3' < 2 < 4'
         * 3 no 1 2 1' 2' 3'
         * 4 no
         * 5 no
         * 1' no 1 2
         * 2' no 1 2 1'
         * 3' yes 2 < 3'
         * 4' yes 1 < 4' < 2
         * 5' yes 5' < 1
         *
         * [1, 2]
         * [3',4',5']
         * 手动剔除3
         * if 3'> 2
         * 则 3'就是第五小的数
         *
         * [1, 2]
         * [4',5']
         * 和上面同理
         */
        private int f(int[] a, int s1, int e1, int[] b, int s2, int e2){
            int mid1 = 0;
            int mid2 = 0;
            while (s1 < e1){
                mid1 = (s1 + e1) / 2;
                mid2 = (s2 + e2) / 2;
                if (a[mid1] == b[mid2]){
                    return a[mid1];
                }

                //奇数 ((e1 - s1 + 1) & 1) == 1
                if (((e1 - s1 + 1) % 2) == 1) {
                    if (a[mid1] > b[mid2]){
                        if (b[mid2] >= a[mid1 - 1]){
                            return b[mid2];
                        }
                        e1 = mid1 - 1;
                        s2 = mid2 + 1;
                    }else{
                        if (a[mid1] >= b[mid2 - 1]){
                            return a[mid1];
                        }
                        s1 = mid1 + 1;
                        e2 = mid2 - 1;
                    }
                }else{
                    if (a[mid1] > b[mid2]){
                        e1 = mid1;
                        s2 = mid2 + 1;
                    }else{
                        s1 = mid1 + 1;
                        e2 = mid2;
                    }
                }
            }
            return Math.min(a[s1], b[s2]);
        }

        public int f2(int[] mArr){
            int mid = (mArr.length - 1) / 2;
            return mArr[mid];
        }


        private int[] generateArray(int num){
            int[] result = new int[num];
            for(int i = 0; i < num; i++){
                result[i] = (int)(Math.random()*10 + 1);
            }
            return result;
        }

        private int[] sumArray(int[] a, int[] b){
            int len = a.length + b.length;
            int[] result = new int[len];
            int r = 0;
            for(int i = 0; i < a.length; i++){
                result[r++] = a[i];
            }
            for(int i = 0; i < b.length; i++){
                result[r++] = b[i];
            }
            return result;
        }

        private int[] bubbleSort(int[] a){
            if (a == null || a.length <= 1){
                return a;
            }
            for (int i = 0; i < a.length ; i++){
                for(int j = i + 1; j < a.length; j++){
                    if (a[i] > a[j]){
                        int temp = a[i];
                        a[i] = a[j];
                        a[j] = temp;
                    }
                }
            }
            return a;
        }

        private void print(int[] a){
            for (int i : a) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}

