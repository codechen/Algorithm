package com.htmm.myapplication;

import java.util.Random;

/**
 * Msg:索引最大堆
 * Update:  2018-7-4
 * Version: 1.0
 * Created by chenchao on 2018-7-4 14:39.
 */
public class IndexMaxHeap<T extends Comparable> {
    private T[] data;
    private int[] indexes;
    private int[] reverse; // 优化--逆向操作
    private int count;
    private int capacity;

    public static void main(String[] args) {
        IndexMaxHeap<Integer> maxHeap = new IndexMaxHeap<Integer>(100);
        int N = 35;
        int M = 100;
        for (int i = 0; i < N; i++) {
            maxHeap.insert(i, new Integer(new Random().nextInt(M)));
        }
        System.out.print("\n[Sort]: ");
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.extractMax() + " ");
        }
    }

    /**
     * 构造方法
     *
     * @param capacity 容量
     */
    public IndexMaxHeap(int capacity) {
        data = (T[]) new Comparable[capacity + 1];
        indexes = new int[capacity + 1];
        reverse = new int[capacity + 1];
        for (int i = 0; i <= capacity; i++)
            reverse[i] = 0; // 初始化索引下标位置
        count = 0;
        this.capacity = capacity;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    // 交换堆中索引为i和j的两个元素
    private void swap(int i, int j) {
        int t = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = t;
    }

    public void insert(int index, T item) {
        assert index + 1 >= 1 && index + 1 <= capacity;
        assert count + 1 <= capacity;

        index++; // 索引从1开始
        data[index] = item;
        indexes[count + 1] = index;
        reverse[index] = count + 1;
        count++;
        shiftUp(count);
    }

    public T extractMax() {
        assert count > 0;
        T item = data[indexes[1]];
        swap(1, count);
        reverse[indexes[1]] = 1;
        reverse[indexes[count]] = 0; // 出堆清除记录
        count--;
        shiftDown(1);
        return item;
    }

    public int extractMaxIndex() {
        assert count > 0;
        int maxIndex = indexes[1] - 1;
        swap(1, count);
        reverse[indexes[1]] = 1;
        reverse[indexes[count]] = 0; // 出堆清除记录
        count--;
        shiftDown(1);
        return maxIndex;
    }

    public T getItem(int index) {
        assert contain(index);
        return data[index + 1];
    }

    public void change(int index, T newItem) {
        assert contain(index);

        index++;
        data[index] = newItem;

        // 更新维护索引堆
//        for (int i = 1; i <= count; i++) {
//            if (indexes[i] == index) {
//                shiftUp(i);
//                shiftDown(i);
//                return;
//            }
//        }

        // 更新维护索引堆--逆向优化
        int i = reverse[index];
        shiftUp(i);
        shiftDown(i);
    }

    private void shiftUp(int index) {
        while (index > 1 && data[indexes[index / 2]].compareTo(data[indexes[index]]) < 0) {
            swap(index / 2, index);
            reverse[indexes[index/2]] = index/2;
            reverse[indexes[index]] = index;
            index /= 2;
        }
    }

    private void shiftDown(int index) {
        while (index * 2 <= count) {
            int j = index * 2;

            if (j + 1 <= count && data[indexes[j + 1]].compareTo(data[indexes[j]]) > 0)
                j += 1;

            if (data[indexes[index]].compareTo(data[indexes[j]]) >= 0)
                break;

            swap(index, j);
            reverse[indexes[index]] = index;
            reverse[indexes[j]] = j;
            index = j;
        }
    }

    /**
     * 是否堆中存在对应索引
     *
     * @param index
     * @return
     */
    private boolean contain(int index) {
        assert index + 1 > 1 && index + 1 <= capacity;
        return reverse[index + 1] != 0;
    }
}
