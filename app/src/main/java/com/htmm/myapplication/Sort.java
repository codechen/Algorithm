package com.htmm.myapplication;

import java.util.Random;

/**
 * Msg: XXX类 1、提供XXX功能；2、提供XXX方法
 * Update:  2018-3-30
 * Version: 1.0
 * Created by chenchao on 2018-3-30 17:04.
 */
public class Sort {

    public static void main(String args[]) {
        Integer[] arr1 = generateRandomArray(100, 0, 100);
        Integer[] arr2 = copyArray(arr1);
        Integer[] arr3 = copyArray(arr1);
        Integer[] arr4 = copyArray(arr1);
        Integer[] arr5 = copyArray(arr1);
        Integer[] arr6 = copyArray(arr1);
        Integer[] arr7 = copyArray(arr1);

        long time = selectionSort(arr1); // O(n^2)
        long time2 = insertSortOpt(arr2); // O(n^2)
        long time3 = bubbleSortOpt2(arr3); // O(n^2)
        long time4 = shellSort(arr4); // O(n^2)
        long time5 = mergeSort(arr5); // O(nlogn)
        long time6 = quickSort(arr6); // O(nlogn)
        long time7 = heapSort2(arr7); // O(nlogn)

        printArray(arr1, time);
        printArray(arr2, time2);
        printArray(arr3, time3);
        printArray(arr4, time4);
        printArray(arr5, time5);
        printArray(arr6, time6);
        printArray(arr7, time7);
    }

    /**
     * 选择排序
     *
     * @param arr
     */
    public static long selectionSort(Integer[] arr) {
        long startTime = System.currentTimeMillis();
        for (int i = 0, n = arr.length; i < n; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            swap(arr, i, minIndex);
        }
        return System.currentTimeMillis() - startTime;
    }

    /**
     * 插入排序
     *
     * @param arr
     * @return
     */
    public static long insertSort(Integer[] arr) {
        long startTime = System.currentTimeMillis();

        for (int i = 1, len = arr.length; i < len; i++) {

            for (int j = i; j > 0; j--) {

                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                } else {
                    break;
                }
            }
        }

        return System.currentTimeMillis() - startTime;
    }

    /**
     * 插入排序优化
     *
     * @param arr
     * @return
     */
    public static long insertSortOpt(Integer[] arr) {
        long startTime = System.currentTimeMillis();

        for (int i = 1, len = arr.length; i < len; i++) {

            int temp = arr[i];
            int j;
            for (j = i; j > 0; j--) {
                if (arr[j - 1] > temp) {
                    arr[j] = arr[j - 1];
                } else {
                    break;
                }
            }
            arr[j] = temp;
        }

        return System.currentTimeMillis() - startTime;
    }

    /**
     * 冒泡排序
     *
     * @param arr
     * @return
     */
    public static long bubbleSort(Integer[] arr) {
        long startTime = System.currentTimeMillis();

        for (int i = 0, len = arr.length; i < len; i++) {

            for (int j = 1; j < len - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                }
            }
        }

        return System.currentTimeMillis() - startTime;
    }

    /**
     * 冒泡排序优化1
     *
     * @param arr
     * @return
     */
    public static long bubbleSortOpt1(Integer[] arr) {
        long startTime = System.currentTimeMillis();

        int len = arr.length;
        boolean swapped = false;

        do {
            swapped = false;
            for (int i = 1; i < len; i++) {
                if (arr[i - 1] > arr[i]) {
                    swap(arr, i - 1, i);
                    swapped = true;
                }
            }
            len--;
        } while (swapped);

        return System.currentTimeMillis() - startTime;
    }

    /**
     * 冒泡排序优化2
     *
     * @param arr
     * @return
     */
    public static long bubbleSortOpt2(Integer[] arr) {
        long startTime = System.currentTimeMillis();

        int len = arr.length;
        int newN = len; //新排序边界

        do {
            len = newN;
            newN = 0;

            for (int i = 1; i < len; i++) {
                if (arr[i - 1] > arr[i]) {
                    swap(arr, i - 1, i);
                    newN = i;
                }
            }
        } while (newN > 0);

        return System.currentTimeMillis() - startTime;
    }

    /**
     * 希尔排序
     *
     * @param arr
     * @return
     */
    public static long shellSort(Integer[] arr) {
        long startTime = System.currentTimeMillis();

        int n = arr.length;
        int h = 1; // 间隔增量
        while (h <= n / 3) h = h * 3 + 1;

        while (h > 0) {

            for (int i = h; i < n; i++) {
                int temp = arr[i];
                int j;
                for (j = i; j >= h; j -= h) {
                    if (arr[j - h] > temp) {
                        arr[j] = arr[j - h];
                    } else {
                        break;
                    }
                }
                arr[j] = temp;
            }

            h = (h - 1) / 3;
        }

        return System.currentTimeMillis() - startTime;
    }

    /**
     * 归并排序
     *
     * @param arr
     * @return
     */
    public static long mergeSort(Integer[] arr) {
        long startTime = System.currentTimeMillis();

        int n = arr.length;
        mergeSort(arr, 0, n - 1);

        return System.currentTimeMillis() - startTime;
    }

    /**
     * 递归合并
     *
     * @param arr
     * @param l
     * @param r
     */
    private static void mergeSort(Integer arr[], int l, int r) {

        if (l >= r)
            return;

        // 优化
//        if (r - l <= 10) {
//            insertSort(arr, l, r); // 数据量小到一定程序时趋向有序的概率大，这时插入排序的性能更好
//            return;
//        }

        int mid = (l + r) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        if (arr[mid] > arr[mid + 1]) { // 优化
            merge(arr, l, mid, r);
        }
    }

    /**
     * 排序合并
     *
     * @param arr
     * @param l
     * @param mid
     * @param r
     */
    private static void merge(Integer[] arr, int l, int mid, int r) {
//        System.out.println("left=" + l + " mid=" + mid + " right=" + r + "\n");

        int[] tempArr = new int[r - l + 1];
        for (int i = l; i <= r; i++)
            tempArr[i - l] = arr[i];

        int i = l;
        int j = mid + 1;

        for (int k = l; k <= r; k++) {
            if (i > mid) {
                arr[k] = tempArr[j - l];
                j++;
            } else if (j > r) {
                arr[k] = tempArr[i - l];
                i++;
            } else if (tempArr[i - l] < tempArr[j - l]) {
                arr[k] = tempArr[i - l];
                i++;
            } else {
                arr[k] = tempArr[j - l];
                j++;
            }
        }
    }

    /**
     * 自底向上的归并排序
     *
     * @param arr
     */
    private static void mergeSortBU(Integer[] arr) {
        int n = arr.length;
        for (int size = 1; size <= n; size += size)
            for (int i = 0; i + size < n; i += size + size)
                merge(arr, i, i + size - 1, Math.min(i + size + size - 1, n - 1));
    }

    /**
     * 快速排序
     *
     * @param arr
     */
    public static long quickSort(Integer[] arr) {
        long startTime = System.currentTimeMillis();
        quickSort3Ways(arr, 0, arr.length - 1);
        return System.currentTimeMillis() - startTime;
    }

    /**
     * 快速排序
     *
     * @param arr
     * @param l
     * @param r
     */
    private static void quickSort(Integer[] arr, int l, int r) {
        if (l >= r)
            return;

        // 优化
//        if (r - l <= 10) {
//            insertSort(arr, l, r); // 数据量小到一定程序时趋向有序的概率大，这时插入排序的性能更好
//            return;
//        }

        int p = partition2Ways(arr, l, r);
        quickSort(arr, l, p - 1);
        quickSort(arr, p + 1, r);
    }

    /**
     * 快速排序
     *
     * @param arr
     * @param l
     * @param r
     * @return
     */
    private static int partition(Integer[] arr, int l, int r) {
        // 优化,在近乎有序的数据时，时间复杂度趋近O(n^2)
        swap(arr, l, new Random().nextInt(r - l + 1) + l);
        int v = arr[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i] < v) {
                swap(arr, j + 1, i);
                j++;
            }
        }
        swap(arr, l, j);
        return j;
    }

    /**
     * 快速排序优化--双路快速排序（规避大量重复数据导致时间复杂趋向于O(n^2)的问题）
     *
     * @param arr
     * @param l
     * @param r
     * @return
     */
    private static int partition2Ways(Integer[] arr, int l, int r) {
        // 优化,在近乎有序的数据时，时间复杂度趋近O(n^2)
        swap(arr, l, new Random().nextInt(r - l + 1) + l);
        int v = arr[l];
        int i = l + 1;
        int j = r;
        while (true) {
            while (i <= r && arr[i] < v) i++;
            while (j >= l + 1 && arr[j] > v) j--;
            if (i > j) break;
            swap(arr, i, j);
            i++;
            j--;
        }
        swap(arr, l, j);
        return j;
    }

    /**
     * 快速排序优化--三路快速排序（规避大量重复数据导致时间复杂趋向于O(n^2)的问题）
     *
     * @param arr
     * @param l
     * @param r
     * @return
     */
    private static void quickSort3Ways(Integer[] arr, int l, int r) {
        if (l >= r)
            return;

        // 优化
//        if (r - l <= 10) {
//            insertSort(arr, l, r); // 数据量小到一定程序时趋向有序的概率大，这时插入排序的性能更好
//            return;
//        }

        // 优化,在近乎有序的数据时，时间复杂度趋近O(n^2)
        swap(arr, l, new Random().nextInt(r - l + 1) + l);
        int v = arr[l];
        int lt = l;
        int gt = r + 1;
        int i = l + 1;

        while (i < gt) {
            if (arr[i] < v) {
                swap(arr, i, lt + 1);
                lt++;
                i++;
            } else if (arr[i] > v) {
                swap(arr, i, gt - 1);
                gt--;
            } else {
                i++;
            }
        }

        swap(arr, l, lt);
        lt--;
        quickSort3Ways(arr, l, lt);
        quickSort3Ways(arr, gt, r);
    }

    /**
     * 堆排序
     *
     * @param arr
     */
    public static long heapSort(Integer[] arr) {
        long startTime = System.currentTimeMillis();
        int n = arr.length;

        // 初始化堆方式一
//        MaxHeap<Integer> maxHeap = new MaxHeap<Integer>(n);
//        for (int i = 0; i < n; i++) {
//            maxHeap.insert(arr[i]);
//        }

        // 初始化堆方式二
        MaxHeap<Integer> maxHeap = new MaxHeap<Integer>(arr);

        for (int i = n - 1; i >= 0; i--) {
            arr[i] = maxHeap.extractMax();
        }

        return System.currentTimeMillis() - startTime;
    }

    /**
     * 堆排序2
     *
     * @param arr
     */
    public static long heapSort2(Integer[] arr) {
        long startTime = System.currentTimeMillis();

        int n = arr.length;
        for (int i = (n - 1) / 2; i >= 0 ; i--)
            shiftDown(arr, n, i);
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            shiftDown(arr, i, 0);
        }

        return System.currentTimeMillis() - startTime;
    }

    /**
     * 下移操作
     *
     * @param arr
     * @param count
     * @param index
     */
    private static void shiftDown(Integer[] arr, int count, int index) {
        while (index * 2 + 1 < count) {
            int j = index * 2 + 1;

            if (j + 1 < count && arr[j + 1].compareTo(arr[j]) > 0)
                j += 1;

            if (arr[index].compareTo(arr[j]) >= 0)
                break;

            swap(arr, index, j);
            index = j;
        }
    }

    /**
     * 生成随机数组
     *
     * @param len
     * @param rangeL
     * @param rangeR
     * @return
     */
    public static Integer[] generateRandomArray(int len, int rangeL, int rangeR) {
        Integer[] randomArray = new Integer[len];
        for (int i = 0; i < len; i++) {
            randomArray[i] = new Random().nextInt(rangeR - rangeL) + rangeL;
        }
        return randomArray;
    }

    /**
     * 拷贝数组
     *
     * @param arr
     * @return
     */
    public static Integer[] copyArray(Integer[] arr) {
        Integer[] copyArray = new Integer[arr.length];
        for (int i = 0, len = arr.length; i < len; i++) {
            copyArray[i] = arr[i];
        }
        return copyArray;
    }

    /**
     * 换位置
     *
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(Integer[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    /**
     * 打印结果
     *
     * @param arr
     * @param time
     */
    public static void printArray(Integer[] arr, long time) {
        for (int i = 0, n = arr.length; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("\ntime = " + time + " ms");
    }
}
