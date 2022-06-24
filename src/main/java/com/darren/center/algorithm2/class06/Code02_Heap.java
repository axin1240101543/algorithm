package com.darren.center.algorithm2.class06;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2022年06月24日 08:10:07
 **/
public class Code02_Heap {

    public static void main(String[] args) {
        int value = 1000;
        int limit = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            MyMaxHeap my = new MyMaxHeap(curLimit);
            RightMaxHeap test = new RightMaxHeap(curLimit);
            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (my.isEmpty() != test.isEmpty()) {
                    System.out.println("Oops!");
                }
                if (my.isFull() != test.isFull()) {
                    System.out.println("Oops!");
                }
                if (my.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    my.push(curValue);
                    test.push(curValue);
                } else if (my.isFull()) {
                    if (my.pop() != test.pop()) {
                        System.out.println("Oops!");
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        my.push(curValue);
                        test.push(curValue);
                    } else {
                        if (my.pop() != test.pop()) {
                            System.out.println("Oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");

    }

   public static class MyMaxHeap{

        private int[] values;
        private final int limit;
        private int heapSize;

       public MyMaxHeap(int limit) {
           values = new int[limit];
           this.limit = limit;
           heapSize = 0;
       }

       public void push(int value){
            if (heapSize == limit){
                throw new RuntimeException("heap is full");
            }
            values[heapSize] = value;
           heapInsert(values, heapSize++);
       }

       // 用户此时，让你返回最大值，并且在大根堆中，把最大值删掉
       // 剩下的数，依然保持大根堆组织
       public int pop(){
            int ans = values[0];
            swap(values, 0, --heapSize);
            heapify(values, 0, heapSize);
            return ans;
       }

       public boolean isEmpty(){
           return heapSize == 0;
       }

       public boolean isFull(){
           return heapSize == limit;
       }

       // 新加进来的数，现在停在了index位置，请依次往上移动，
       // 移动到0位置，或者干不掉自己的父亲了，停！
       private static void heapInsert(int[] arr, int index){
           // [index]    [index-1]/2
           // index == 0
           while (arr[index] > arr[(index - 1) / 2]){
               swap(arr, index, (index - 1) / 2);
               index = (index - 1) / 2;
           }
       }

       // 从index位置，往下看，不断的下沉
       // 停：较大的孩子都不再比index位置的数大；已经没孩子了
       private static void heapify(int[] arr, int index, int heapSize){
           int left = 2 * index + 1;
           // 如果有左孩子，有没有右孩子，可能有可能没有！
           while (left < heapSize){
               // 把较大孩子的下标，给largest（通过heapSize判断是否有右孩子）
               int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
               // 自己和较大的孩子PK
               largest = arr[largest] > arr[index] ? largest : index;
               // 没PK过 break
               if (largest == index){
                   break;
               }
               // index和较大孩子，要互换
               swap(arr, index, largest);
               // index来到较大孩子的下标位置
               index = largest;
               // 更新左孩子的下标
               left = 2 * index + 1;
           }
       }
   }

   public static class RightMaxHeap{
       private int[] values;
       private final int limit;
       private int heapSize;

       public RightMaxHeap(int limit) {
           values = new int[limit];
           this.limit = limit;
           heapSize = 0;
       }

       public void push(int value){
           if (heapSize == limit){
               throw new RuntimeException("heap is full");
           }
           values[heapSize++] = value;
       }

       public int pop(){
           int maxIndex = 0;
           for (int i = 1; i < heapSize; i++){
               if (values[i] > values[maxIndex]){
                   maxIndex = i;
               }
           }
           int ans = values[maxIndex];
           values[maxIndex] = values[--heapSize];
           return ans;
       }

       public boolean isEmpty(){
           return heapSize == 0;
       }

       public boolean isFull(){
           return heapSize == limit;
       }
   }

    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}

