package com.htmm.myapplication;

import java.util.Vector;

/**
 * Msg:稠密图--邻接矩阵表示
 * Update:  2018-7-4
 * Version: 1.0
 * Created by chenchao on 2018-7-4 14:39.
 */
public class DenseGraph {

    private int n; // 顶点数
    private int m; // 边数
    private boolean directed; // 是否是有向图
    private Vector<Vector<Boolean>> g; // 矩阵容器

    public DenseGraph(int n, boolean directed) {
        this.n = n;
        this.m = 0;
        this.directed = directed;
        this.g = new Vector<>();
        for (int i = 0; i < n; i++) {
            Vector<Boolean> defVector = new Vector<Boolean>(n);
            for (int j = 0; j < n; j++) {
                defVector.add(false);
            }
            g.add(defVector); // 初始化矩阵每一行
        }
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

        if (hasEdge(v, w))
            return;

        g.get(v).add(w, true);
        if (!directed)
            g.get(w).add(v, true);
        m++;
    }

    public boolean hasEdge(int v, int w) {
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;

        return g.get(v).get(w);
    }

    public static class Iterator {
        private DenseGraph graph;
        private int v;
        private int index;

        public Iterator(DenseGraph graph, int v) {
            this.graph = graph;
            this.v = v;
            this.index = -1;
        }

        public int begin() {
            index = -1;
            return next();
        }

        public int next() {
            for (index += 1; index < graph.V(); index++)
                if (graph.g.get(v).get(index))
                    return index;
            return -1;
        }

        public boolean end() {
            return index >= graph.V();
        }
    }

}
