package com.htmm.myapplication;


/**
 * Msg:链表
 * Update:  2018-7-4
 * Version: 1.0
 * Created by chenchao on 2018-7-4 14:39.
 */
public class LinkedList {

    private class Node {
        public Object value;
        public Node next;

        public Node(Object value) {
            this.value = value;
        }
    }

    private Node head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public Object addHead(Object obj) {
        Node node = new Node(obj);
        if (isEmpty()) {
            head = node;
        } else {
            node.next = head;
            head = node;
        }
        size++;
        return obj;
    }

    public Object deleteHead() {
        if (isEmpty())
            return null;
        Object value = head.value;
        head = head.next;
        size--;
        return value;
    }

    public Node find(Object value) {
        Node curNode = head;
        while (curNode != null) {
            if (value.equals(curNode.value)) {
                return curNode;
            }
            curNode = curNode.next;
        }
        return null;
    }

    public boolean delete(Object value) {
        if (isEmpty())
            return false;

        Node curNode = head;
        Node preNode = null;
        while (!value.equals(curNode.value)) {
            if (curNode.next == null) {
                return false;
            }
            preNode = curNode;
            curNode = curNode.next;
        }

        if (curNode == head) {
            head = head.next;
        } else {
            preNode.next = curNode.next;
        }
        size--;
        return true;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void reverseIterator() {
        head = reverseIterator(head);
    }

    public void reverseRecursive() {
        head = reverseRecursive(head);
    }

    /**
     * 链表反转 - 迭代方式
     * @param head
     * @return 反转后的head
     */
    private Node reverseIterator(Node head) {
        Node curNode = head;
        Node preNode = null;
        Node nextNode = null;
        while (curNode != null) {
            nextNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = nextNode;
        }
        return preNode;
    }

    /**
     * 链表反转 - 递归方式
     * @param head
     * @return 反转后的head
     */
    private Node reverseRecursive(Node head) {
        if (head == null || head.next == null)
            return head;

        Node reverseHead = reverseRecursive(head.next); // reverseHead为原链表最后一个节点
        head.next.next = head;
        head.next = null;
        return reverseHead;
    }
}
