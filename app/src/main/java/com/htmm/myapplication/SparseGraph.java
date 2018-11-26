package com.htmm.myapplication;

import java.util.Vector;

/**
 * Msg:稀疏图--邻接表表示
 * Update:  2018-7-4
 * Version: 1.0
 * Created by chenchao on 2018-7-4 14:39.
 */
public class SparseGraph {

    private int n; // 顶点数
    private int m; // 边数
    private boolean directed; // 是否是有向图
    private Vector<Vector<Integer>> g; // 矩阵窗口

    public SparseGraph(int n, boolean directed) {
        this.n = n;
        this.m = 0;
        this.directed = directed;
        this.g = new Vector<>();
        for (int i = 0; i < n; i++)
            g.add(new Vector<Integer>()); // 初始化矩阵每一行
    }

    public int V() {
        return n;
    }

    public int E() {
        return m;
    }

    // 允许自环边、不允许平行边
    public void addEdge(int v, int w) {
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;

        // 开销过大
//        if (hasEdge(v, w))
//            return;

        if (g.get(v).contains(w))
            return;

        g.get(v).add(w);
        if (v != w && !directed) // 判断一下v != w，是为了不加入重边
            g.get(w).add(v);
        m++;
    }

    public boolean hasEdge(int v, int w) {
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;

        for (int i = 0; i < g.get(v).size(); i++)
            if (g.get(v).get(i) == w)
                return true;
        return false;
    }

    public static class Iterator {
        private SparseGraph graph;
        private int v;
        private int index;

        public Iterator(SparseGraph graph, int v) {
            this.graph = graph;
            this.v = v;
            this.index = 0;
        }

        public int begin() {
            index = 0;
            if (graph.g.get(v).size() > 0)
                return graph.g.get(v).get(0);
            return -1;
        }

        public int next() {
            index++;
            if (index < graph.g.get(v).size())
                return graph.g.get(v).get(index);
            return -1;
        }

        public boolean end() {
            return index >= graph.g.get(v).size();
        }
    }
}
