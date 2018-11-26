package com.htmm.myapplication;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

/**
 * Msg:稠密图--邻接矩阵表示
 * Update:  2018-7-4
 * Version: 1.0
 * Created by chenchao on 2018-7-4 14:39.
 *
 * 最小生成树：prime、krusal
 *
 * 最短路径：dijkstra、bellman-ford
 *
 */
public class SparseGraphPath {
    private SparseGraph sparseGraph;
    private int v;
    private boolean[] visited;
    private int[] from;
    private int[] ord; // 用于标识原点到其他点的路径长度

    /**
     * 原点
     * @param sparseGraph
     * @param v
     */
    public SparseGraphPath(SparseGraph sparseGraph, int v) {
        assert v >= 0 && v < sparseGraph.V();

        this.sparseGraph = sparseGraph;
        this.v = v;
        this.visited = new boolean[sparseGraph.V()];
        this.from = new int[sparseGraph.V()];
        this.ord = new int[sparseGraph.V()];
        for (int i = 0; i < sparseGraph.V(); i++) {
            visited[i] = false;
            from[i] = -1;
            ord[i] = -1;
        }

        dfs(v);
    }

    /**
     * 深度优先遍历
     *
     * @param v
     */
    private void dfs(int v) {
        visited[v] = true;
        SparseGraph.Iterator sparseIterator = new SparseGraph.Iterator(sparseGraph, v);

        for (int i = sparseIterator.begin(); !sparseIterator.end(); i = sparseIterator.next())
            if (!visited[i]) {
                from[i] = v;
                dfs(i);
            }
    }

    /**
     * 广度优先遍历(层序优先遍历)
     *
     * 可求无权图最短路径
     *
     * @param v
     */
    private void bfs(int v) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(v);
        ord[v] = 0;
        visited[v] = true;
        System.out.println("vetex: ");


        while (!queue.isEmpty()) {
            int vetex = queue.poll();
            System.out.println(String.valueOf(vetex));
            SparseGraph.Iterator sparseIterator = new SparseGraph.Iterator(sparseGraph, vetex);

            for (int i = sparseIterator.begin(); !sparseIterator.end(); i = sparseIterator.next()) {
                if (!visited[i]) {
                    queue.offer(i);
                    ord[i] = ord[vetex] + 1;
                    visited[i] = true;
                    from[i] = vetex;
                }
            }
        }
    }

    public boolean hasPath(int w) {
        assert w >= 0 && w < sparseGraph.V();
        return visited[w];
    }

    public void path(int w, Vector<Integer> vector) {
        Stack<Integer> stack = new Stack<>();

        int p = w;
        while (p != -1) {
            stack.push(p);
            p = from[p];
        }

        vector.clear();
        while (!stack.isEmpty()) {
            vector.add(stack.pop());
        }
    }

    public void showPath(int w) {
        Vector<Integer> vector = new Vector<>();
        path(w, vector);
        for (int i = 0; i < vector.size(); i++) {
            System.out.print(vector.get(i));
            if (i == vector.size() - 1) {
                break;
            }
            System.out.print("->");
        }
    }
}
