package com.htmm.myapplication;

/**
 * Msg:最大堆
 * Update:  2018-7-4
 * Version: 1.0
 * Created by chenchao on 2018-7-4 14:39.
 */
public class BinarySearch<T extends Comparable> {
    private T[] data;
    private int count;
    private int capacity;

    public static void main(String[] args) {
//        Integer[] arr = Sort.generateRandomArray(20, 0, 100);
//        long time = Sort.quickSort(arr); // O(nlogn)
//        Sort.printArray(arr, time);
//        int index = find2(arr, arr[13]);
//        System.out.println("\ntarget = " + arr[13] + " index=" + index);

//        String a = "123";
//        String b = "123";
//        String c = new String("123");
//
//        System.out.print(a==b);
//        System.out.print(a==c);
//        System.out.print(b==c);
//
//        int x = 9;
//        chage(x);
//        System.out.print("x=" + x);

//        String str2 = new String("str")+new String("01");
//        final String a = "00";
//        String str1 = "str01" + a;
//        String str2 = "str0100";
//        str1.intern();
//        System.out.println(str1==str2);

//        String s1=new String("ja");
//        String s3=s1.intern();
//        System.out.println(s3==s1);

//        String c = "a";
//        String a = new String("ab") + c;
//        String b = new String("ab");
//        String d = b.intern();
//        String c = "ab";
        String d = "a" + "b";
        String e = "b";
        String f = "a" + e;

//        System.out.println(b.intern() == a);
//        System.out.println(b.intern() == c);
//        System.out.println(b.intern() == d);
//        System.out.println(b.intern() == f);
        System.out.println(d == f.intern());


//        System.out.println(Integer.valueOf("F", 16));//15
//        System.out.println(Integer.toHexString(15));//F
//        System.out.println(Integer.toBinaryString(-10));//11111111111111111111111111110110
    }

    private static void chage(int i) {
        i = 100;
    }

    /**
     * 二分查找法--迭代方法
     *
     * @param arr
     * @param target
     * @return
     */
    public static int find(Comparable[] arr, int target) {
        int n = arr.length;
        int l = 0;
        int r = n-1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (arr[mid].compareTo(target) == 0)
                return mid;

            if (arr[mid].compareTo(target) > 0)
                r = mid - 1;
            else
                l = mid + 1;
        }

        return -1;
    }

    /**
     * 二分查找法--递归方法
     *
     * @param arr
     * @param target
     * @return
     */
    public static int find2(Comparable[] arr, int target) {
        return find2(arr, 0, arr.length-1, target);
    }

    private static int find2(Comparable[] arr, int l, int r, int target) {
        if (l > r)
            return -1;

        int mid = l + (r - l) / 2;

        if (arr[mid].compareTo(target) == 0)
            return mid;

        if (arr[mid].compareTo(target) > 0)
            return find2(arr, l, mid - 1, target);
        else
            return find2(arr, mid + 1, r, target);
    }

}
