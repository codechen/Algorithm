package com.htmm.myapplication;

import java.util.Random;

/**
 * 普通队列：先进先出
 * 优先队列：优先等级高的先出
 *
 * 堆：树型结构 如：最大二叉堆(1.任何节点不大于父节点 2.完成二叉树（除了最后一层之外，其它的节点数都应该是最大值，而最后一层所有节点都应该从左边开始集中排列）)
 *
 * Msg:最大堆--实现优化队列
 * Update:  2018-7-4
 * Version: 1.0
 * Created by chenchao on 2018-7-4 14:39.
 */
public class MaxHeap<T extends Comparable> {
    private T[] data;
    private int count;
    private int capacity;

    public static void main(String[] args) {
        MaxHeap<Integer> maxHeap = new MaxHeap<Integer>(100);
        int N = 35;
        int M = 100;
        for (int i = 0; i < N; i++) {
            maxHeap.insert(new Integer(new Random().nextInt(M)));
        }
        maxHeap.treePrint();
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
    public MaxHeap(int capacity) {
        data = (T[]) new Comparable[capacity + 1];
        count = 0;
        this.capacity = capacity;
    }

    /**
     * 构造方法
     *
     * @param arr
     */
    public MaxHeap(T[] arr) {
        int n = arr.length;
        data = (T[]) new Comparable[n + 1];
        this.capacity = n;
        for (int i = 0; i < n; i++) {
            data[i + 1] = arr[i];
        }
        count = n;
        // 第一个非叶子节点
        for (int i = count / 2; i > 0; i--) {
            shiftDown(i);
        }
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    // 交换堆中索引为i和j的两个元素
    private void swap(int i, int j) {
        T t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    public void insert(T item) {
//            assert [boolean 表达式]
//            如果[boolean表达式]为true，则程序继续执行。
//            如果为false，则程序抛出AssertionError，并终止执行。
        assert count + 1 <= capacity;
        data[count + 1] = item;
        count++;
        shiftUp(count);
    }

    public T extractMax() {
        assert count > 0;
        T item = data[1];
        swap(1, count);
        count--;
        shiftDown(1);
        return item;
    }

    private void shiftUp(int index) {
        while (index > 1 && data[index / 2].compareTo(data[index]) < 0) {
            swap(index / 2, index);
            index /= 2;
        }
    }

    private void shiftDown(int index) {
        while (index * 2 <= count) {
            int j = index * 2;

            if (j + 1 <= count && data[j + 1].compareTo(data[j]) > 0)
                j += 1;

            if (data[index].compareTo(data[j]) >= 0)
                break;

            swap(index, j);
            index = j;
        }
    }





















    // 以树状打印整个堆结构
    public void treePrint() {

        if (size() >= 100) {
            System.out.println("This print function can only work for less than 100 integer");
            return;
        }

        System.out.println("The max heap size is: " + size());
        System.out.println("Data in the max heap: ");
        for (int i = 1; i <= size(); i++) {
            // 我们的print函数要求堆中的所有整数在[0, 100)的范围内
            assert (Integer) data[i] >= 0 && (Integer) data[i] < 100;
            System.out.print(data[i] + " ");
        }
        System.out.println();
        System.out.println();

        int n = size();
        int maxLevel = 0;
        int numberPerLevel = 1;
        while (n > 0) {
            maxLevel += 1;
            n -= numberPerLevel;
            numberPerLevel *= 2;
        }

        int maxLevelNumber = (int) Math.pow(2, maxLevel - 1);
        int curTreeMaxLevelNumber = maxLevelNumber;
        int index = 1;
        for (int level = 0; level < maxLevel; level++) {

            String line1 = new String(new char[maxLevelNumber * 3 - 1]).replace('\0', ' ');

            int curLevelNumber = Math.min(count - (int) Math.pow(2, level) + 1, (int) Math.pow(2, level));
            boolean isLeft = true;
            for (int indexCurLevel = 0; indexCurLevel < curLevelNumber; index++, indexCurLevel++) {
                line1 = putNumberInLine((Integer) data[index], line1, indexCurLevel, curTreeMaxLevelNumber * 3 - 1, isLeft);
                isLeft = !isLeft;
            }
            System.out.println(line1);

            if (level == maxLevel - 1)
                break;

            String line2 = new String(new char[maxLevelNumber * 3 - 1]).replace('\0', ' ');
            for (int indexCurLevel = 0; indexCurLevel < curLevelNumber; indexCurLevel++)
                line2 = putBranchInLine(line2, indexCurLevel, curTreeMaxLevelNumber * 3 - 1);
            System.out.println(line2);

            curTreeMaxLevelNumber /= 2;
        }
    }

    private String putNumberInLine(Integer num, String line, int indexCurLevel, int curTreeWidth, boolean isLeft) {

        int subTreeWidth = (curTreeWidth - 1) / 2;
        int offset = indexCurLevel * (curTreeWidth + 1) + subTreeWidth;
        assert offset + 1 < line.length();
        if (num >= 10)
            line = line.substring(0, offset + 0) + num.toString()
                    + line.substring(offset + 2);
        else {
            if (isLeft)
                line = line.substring(0, offset + 0) + num.toString()
                        + line.substring(offset + 1);
            else
                line = line.substring(0, offset + 1) + num.toString()
                        + line.substring(offset + 2);
        }
        return line;
    }

    private String putBranchInLine(String line, int indexCurLevel, int curTreeWidth) {

        int subTreeWidth = (curTreeWidth - 1) / 2;
        int subSubTreeWidth = (subTreeWidth - 1) / 2;
        int offsetLeft = indexCurLevel * (curTreeWidth + 1) + subSubTreeWidth;
        assert offsetLeft + 1 < line.length();
        int offsetRight = indexCurLevel * (curTreeWidth + 1) + subTreeWidth + 1 + subSubTreeWidth;
        assert offsetRight < line.length();

        line = line.substring(0, offsetLeft + 1) + "/" + line.substring(offsetLeft + 2);
        line = line.substring(0, offsetRight) + "\\" + line.substring(offsetRight + 1);

        return line;
    }
}
