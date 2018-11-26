package com.htmm.myapplication;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Msg:二分搜索树
 * Update:  2018-7-4
 * Version: 1.0
 * Created by chenchao on 2018-7-4 14:39.
 */
public class BinarySearchTree<Key extends Comparable, Value> {
    /**
     * 结点
     */
    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;

        private Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node root; // 根节点
    private int count;

    public BinarySearchTree() {
        root = null;
        count = 0;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void insert(Key key, Value value) {
        root = insert(root, key, value);
    }

    public boolean contain(Key key) {
        return contain(root, key);
    }

    public Value search(Key key) {
        return search(root, key);
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    /**
     * 广度优先遍历
     */
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.print(node.key + "=" + node.value + " ");
            if (node.left != null)
                queue.offer(node.left);
            if (node.right != null)
                queue.offer(node.right);
        }
    }

    /**
     * 最大键值
     *
     * @return
     */
    public Key max() {
        assert count > 0;
        Node maxNode = max(root);
        return maxNode.key;
    }

    /**
     * 最小键值
     *
     * @return
     */
    public Key min() {
        assert count > 0;
        Node minNode = min(root);
        return minNode.key;
    }

    public void removeMax() {
        if (root != null)
            root = removeMax(root);
    }

    public void removeMin() {
        if (root != null)
            root = removeMin(root);
    }

    public void remove(Key key) {
        if (root != null)
            root = remove(root, key);
    }

    private Node insert(Node node, Key key, Value value) {
        if (node == null) {
            count++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) == 0)
            node.value = value;
        else if (key.compareTo(node.key) < 0)
            node.left = insert(node.left, key, value);
        else
            node.right = insert(node.right, key, value);

        return node;
    }

    private boolean contain(Node node, Key key) {
        if (node == null)
            return false;

        if (key.compareTo(node.key) == 0)
            return true;
        else if (key.compareTo(node.key) < 0)
            return contain(node.left, key);
        else
            return contain(node.right, key);
    }

    private Value search(Node node, Key key) {
        if (node == null)
            return null;

        if (key.compareTo(node.key) == 0)
            return node.value;
        else if (key.compareTo(node.key) < 0)
            return search(node.left, key);
        else
            return search(node.right, key);
    }

    // 正常的遍历使用前序就可以
    private void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + "=" + node.value + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    // 节点从小到大排序
    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + "=" + node.value + " ");
            inOrder(node.right);
        }
    }

    // 释放二叉树时使用，先遍历当前的左右节点
    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.key + "=" + node.value + " ");
        }
    }

    private Node max(Node node) {
        if (node.right == null)
            return node;
        return max(node.right);
    }

    private Node min(Node node) {
        if (node.left == null)
            return node;
        return min(node.left);
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node = null;
            count--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node = null;
            count--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    private Node remove(Node node, Key key) {
        if (node == null)
            return null;

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node = null;
                count--;
                return rightNode;
            }

            if (node.right == null) {
                Node leftNode = node.left;
                node = null;
                count--;
                return leftNode;
            }

            Node successorNode = min(node.right); // 后继节点，右子树最小值
            count++;
            successorNode.right = removeMin(node.right);
            successorNode.left = node.left;
            node = null;
            count--;
            return successorNode;
        }
    }

    private Node remove2(Node node, Key key) {
        if (node == null)
            return null;

        if (key.compareTo(node.key) < 0) {
            node.left = remove2(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove2(node.right, key);
            return node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node = null;
                count--;
                return rightNode;
            }

            if (node.right == null) {
                Node leftNode = node.left;
                node = null;
                count--;
                return leftNode;
            }

            Node successorNode = max(node.left); // 后继节点，左子树最大值
            count++;
            successorNode.left = removeMax(node.left);
            successorNode.right = node.right;
            node = null;
            count--;
            return successorNode;
        }
    }

    public static void main(String[] arr) {
        BinarySearchTree<Integer, String> bst = new BinarySearchTree();
        bst.insert(20, "mc");
        bst.insert(52, "jack");
        bst.insert(12, "tom");
        bst.insert(93, "jerry");
        bst.insert(13, "lily");
        bst.insert(20, "mike");

        System.out.println("contain 52 = " + bst.contain(20));
        System.out.println("search 20 = " + bst.search(20));
        System.out.println("\n[preOrder]");
        bst.preOrder();
        bst.removeMin();
        System.out.println("\n[inOrder]");
        bst.inOrder();
        bst.removeMax();
        System.out.println("\n[postOrder]");
        bst.postOrder();
        System.out.println("\n[levelOrder]");
        bst.levelOrder();
    }

}
