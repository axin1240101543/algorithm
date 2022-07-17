package com.darren.center.algorithm2.class07;

import java.util.*;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 * 加强堆（小根堆）
 *
 * @author : Darren
 * @date : 2022年07月16日 08:58:13
 **/
public class HeapGreater<T> {

    private List<T> values;
    private Map<T, Integer> indexMap;
    private int heapSize;
    private Comparator<? super T> comparator;

    public HeapGreater(Comparator<? super T> comparator) {
        this.comparator = comparator;
        this.values = new ArrayList<>();
        this.indexMap = new HashMap<>();
        this.heapSize = 0;
    }

    /**
     * 添加一个元素
     * @param value
     */
    public void push(T value){
        values.add(value);
        indexMap.put(value, heapSize);
        // 从下往上调整堆
        heapInsert(heapSize++);
    }

    /**
     * 弹出一个元素
     * @return
     */
    public T pop(){
        T ans = values.get(0);
        // 将最后一个元素交换到0位置
        swap(0, heapSize - 1);
        // 移除最后一个元素 并 heapSize减一
        values.remove(--heapSize);
        indexMap.remove(ans);
        // 从上往下调整堆
        heapify(0);
        return ans;
    }

    /**
     * 拿出堆顶元素
     * @return
     */
    public T peek(){
        return values.get(0);
    }

    /**
     * 移除堆中的一个元素
     * @param value
     */
    public void remove(T value){
        // 记录现场
        T endValue = values.get(heapSize - 1);
        Integer index = indexMap.get(value);

        // 在堆中和索引表中清除这个元素
        values.remove(--heapSize);
        indexMap.remove(value);

        // 如果当前的元素和最后一个元素相等，则不需要调整堆
        if (comparator.compare(value, endValue) != 0){
            // 将最后一个值覆盖到index位置，然后调整堆
            values.set(index, endValue);
            indexMap.put(endValue, index);
            resign(endValue);
        }
    }

    /**
     * 调整堆
     * 两个方法只会执行一个
     * @param value
     */
    public void resign(T value){
        heapInsert(indexMap.get(value));
        heapify(indexMap.get(value));
    }

    /**
     * 判断堆是否为空
     * @return
     */
    public boolean isEmpty(){
        return heapSize == 0;
    }

    /**
     * 判断是否包含元素
     * @param value
     * @return
     */
    public boolean contains(T value){
        return indexMap.containsKey(value);
    }

    /**
     * 返回堆的大小
     * @return
     */
    public int size(){
        return heapSize;
    }

    /**
     * 获取堆中所有元素
     * @return
     */
    public List<T> getAllElement(){
        List<T> ans = new ArrayList<>();
        for (T value : values) {
            ans.add(value);
        }
        return ans;
    }

    /**
     * 从下往上调整堆
     * @param index
     */
    private void heapInsert(int index){
        while (comparator.compare(values.get(index), values.get((index - 1) / 2)) < 0){
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * 从上往下调整堆
     * @param index
     */
    private void heapify(int index){
        int left = index * 2 + 1;
        while (left < heapSize){
            // 右孩子小于左孩子 那么就是右孩子 否则左孩子
            int lesser = left + 1 < heapSize && comparator.compare(values.get(left + 1), values.get(left)) < 0 ? left + 1 : left;
            // 自己小于较小的孩子，那么就是自己，否则就是较小的孩子
            lesser = comparator.compare(values.get(index), values.get(lesser)) < 0 ? index : lesser;
            if (index == lesser){
                break;
            }
            swap(index, lesser);
            index = lesser;
            left = index * 2 + 1;
        }
    }

    /**
     * 交换两个元素
     * @param i
     * @param j
     */
    private void swap(int i, int j){
        T o1 = values.get(i);
        T o2 = values.get(j);
        values.set(j, o1);
        values.set(i, o2);
        indexMap.put(o1, j);
        indexMap.put(o2, i);
    }

}

